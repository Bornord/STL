# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel
    -   imbrication des types
    -   accès aux attributs
        -   Affectation & typage opérationnel

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

### Test un peu complexe

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
