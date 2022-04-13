# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel
    -   imbrication des types
    -   accès aux attributs
        -   Affectation & typage opérationnel

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Test qui doivent marcher

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point p = {a,b};
}
```

```TAM
PUSH 1
LOADL 0
STORE (1) 0[SB]
PUSH 1
LOADL 3
STORE (1) 1[SB]
PUSH 1
LOADL 1
STORE (1) 2[SB]
PUSH 2
;{ a ,b } start
LOADA 1[SB]
LOADI (1)
LOADA 2[SB]
LOADI (1)
;{ a ,b } end
STORE (2) 3[SB]
HALT
```

### Test l'affectation d'un type légèrement dénaturé

```C
test {
int res = 0;

    typedef struct p{
    	int x1;
    	int x2;
    } Point;

    typedef Point po;
    int a = 3;
    int b = 1;

    po p = {a,b};
    Point p2 = p;

}
```

```TAM
PUSH 1
LOADL 0
STORE (1) 0[SB]
PUSH 1
LOADL 3
STORE (1) 1[SB]
PUSH 1
LOADL 1
STORE (1) 2[SB]
PUSH 2
;{ a ,b } start
LOADA 1[SB]
LOADI (1)
LOADA 2[SB]
LOADI (1)
;{ a ,b } end
STORE (2) 3[SB]
HALT
```

### Test sur des définitions de types semblables, mais de noms différents

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	typedef struct p{
		int x1;
		int x2;
	} PointBis;

	int a = 3;
	int b = 1;
	int c = a;
	int d = b;
	Point p = {a,b};

	PointBis p2 = {c,d};

     // ça passe, car les types sont différents mais la définition est la même
	p2 = p;
}
```

```TAM
PUSH 1
LOADL 0
STORE (1) 0[SB]
PUSH 1
LOADL 3
STORE (1) 1[SB]
PUSH 1
LOADL 1
STORE (1) 2[SB]
PUSH 1
LOADA 1[SB]
STORE (1) 3[SB]
PUSH 1
LOADA 2[SB]
STORE (1) 4[SB]
PUSH 2
;{ a ,b } start
LOADA 1[SB]
LOADI (1)
LOADA 2[SB]
LOADI (1)
;{ a ,b } end
STORE (2) 5[SB]
PUSH 2
;{ c ,d } start
LOADA 3[SB]
LOADI (1)
LOADA 4[SB]
LOADI (1)
;{ c ,d } end
STORE (2) 7[SB]
LOADA 5[SB]
LOADI (2)
LOADA 7[SB]
STOREI (2)
HALT
```

### Test sur l'imbrication de Record

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point pp = {a,b};
	Point ppp = {a+1, b+2};

	typedef struct d{
		Point p1;
		Point p2;
	} Droite;

	Droite d = {pp,ppp};
}
```

```TAM
PUSH 1
LOADL 0
STORE (1) 0[SB]
PUSH 1
LOADL 3
STORE (1) 1[SB]
PUSH 1
LOADL 1
STORE (1) 2[SB]
PUSH 2
;{ a ,b } start
LOADA 1[SB]
LOADI (1)
LOADA 2[SB]
LOADI (1)
;{ a ,b } end
STORE (2) 3[SB]
PUSH 2
;(a + 1)
;{ (a + 1) ,(b + 2) } start
LOADA 1[SB]
LOADI (1)
LOADL 1
SUBR IAdd
;{ (a + 1) ,(b + 2) } end
;(b + 2)
LOADA 2[SB]
LOADI (1)
LOADL 2
SUBR IAdd
;{ (a + 1) ,(b + 2) } end
STORE (2) 5[SB]
PUSH 4
;{ pp ,ppp } start
LOADA 3[SB]
LOADI (2)
LOADA 5[SB]
LOADI (2)
;{ pp ,ppp } end
STORE (4) 7[SB]
HALT
```

## Test qui doivent échouer

### Test sur des champs vides

```c
test {
	int res = 0;

	typedef struct p{
	} Point;

}
```

### test sur un champ de la sequence qui est différent du type de la définition.

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	boolean b = false;

	Point p = {a,b};
}
```

### Test d'affectation sur un record dont le type ne correspond pas

```C
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point p = {a,b};
	p.x1 = false ;
}
```

### Test complexe qui pose des problèmes sur le typages sur

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point pp = {a,b};
	Point ppp = {a+1, b+2};

	typedef struct d{
		Point p1;
		Point p2;
	} Droite;

	Droite d = {a,ppp};
}
```

### Test sur l'accès à un attribut

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point pp = {a,b};
	Point ppp = {a+1, b+2};

	typedef struct d{
		Point p1;
		Point p2;
	} Droite;

	Droite d = {pp,ppp};
	d.pp.a = 3;
}
```

### Test sur l'affectation d'un attribut avec le mauvais type

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point pp = {a,b};
	Point ppp = {a+1, b+2};

	typedef struct d{
		Point p1;
		Point p2;
	} Droite;

	Droite d = {pp,ppp};
	d.x1 = 3;
}
```

## Problème

### test sur la définition imbriquée

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		Point p;
	} Point;

	int a = 3;
	int b = 1;

	Point p = {a,null};
	Point p2 = {a, p};
}
```
