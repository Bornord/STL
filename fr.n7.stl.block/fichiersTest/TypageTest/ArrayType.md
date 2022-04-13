# Test sur

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Test qui doivent marcher

### Test sur allocation de tableau

```c
test {
	typedef struct structPoint{
		int x;
		int y;
	} Point;

	typedef struct s{
		Point a[];
		<character, int> c;
	} Type1;

	Point arrP[] = new Point[2];

	Point p = {4,5};
	arrP[0] = p;
}
```

```TAM
PUSH 2
;new Point[ 2 ] start
LOADL 2
LOADL 2
SUBR IMul
SUBR MAlloc
;new Point[ 2 ] end
STORE (2) 0[SB]
PUSH 2
;{ 4 ,5 } start
LOADL 4
LOADL 5
;{ 4 ,5 } end
STORE (2) 2[SB]
LOADA 2[SB]
LOADI (2)
; arrP [ 0 ] start
LOADA 0[SB]
LOADL 0
SUBR IAdd
; arrP [ 0 ] end
STOREI (2)
HALT
```

### Test semblable qu'on pousse plus loin

```c
test {
	typedef struct structPoint{
		int x;
		int y;
	} Point;

	typedef struct s{
		Point a[];
		<character, int> c;
	} Type1;

	Point arrP[] = new Point[2];

	Point p = {4,5};
	arrP[0] = p;
	<character, int> a = <'d',2>;
	Type1 d = {arrP,a};

}
```

```TAM
PUSH 2
;new Point[ 2 ] start
LOADL 2
LOADL 2
SUBR IMul
SUBR MAlloc
;new Point[ 2 ] end
STORE (2) 0[SB]
PUSH 2
;{ 4 ,5 } start
LOADL 4
LOADL 5
;{ 4 ,5 } end
STORE (2) 2[SB]
LOADA 2[SB]
LOADI (2)
; arrP [ 0 ] start
LOADA 0[SB]
LOADL 0
SUBR IAdd
; arrP [ 0 ] end
STOREI (2)
PUSH 2
;< 'd', 2> start
LOADL 100
LOADL 2
;< 'd', 2> end
STORE (2) 4[SB]
PUSH 4
;{ arrP ,a } start
LOADA 0[SB]
LOADI (2)
LOADA 4[SB]
LOADI (2)
;{ arrP ,a } end
STORE (4) 6[SB]
HALT
```

## Test qui doivent échouer

### Test sur le typage avant l'allocation

```c
test {
	boolean arrP[] = new int[2];
}
```

### Test sur le mauvais typage d'un élément après allocation

```c
test {
	typedef struct structPoint{
		int x;
		int y;
	} Point;

	typedef struct s{
		Point a[];
		<character, int> c;
	} Type1;

	Point arrP[] = new Point[2];

	Point p = {4,5};
	arrP[0] = 2;

}
```

## Problèmes
