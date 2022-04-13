# Test sur les records types

-   Tests implémentés :
    -   affectation & typage simple
    -   imbrication de pointeurs
    -   comparaisons et typages entre pointeurs

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Test qui doivent marcher

### Test sur l'affectation de pointer

```c
test {
	int i = 3;
	int *a = &i;
	*a = 3;
}
```

```TAM
PUSH 1
LOADL 3
STORE (1) 0[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 1[SB]
LOADL 3
;(* a ) start
LOADA 1[SB]
LOADI (1)
;(* a ) end
STOREI (1)
HALT
```

### Test sur l'affectation de pointer

```c
test {
	int i = 3;
	int *a = &i;
	*a = 3;
}
```

```TAM
PUSH 1
LOADL 3
STORE (1) 0[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 1[SB]
LOADL 3
;(* a ) start
LOADA 1[SB]
LOADI (1)
;(* a ) end
STOREI (1)
HALT
```

### Test complexe des égalités et de typages

```c
test {
	int i = 3;
	int j = 6;
	int k = 7;

	int *a = &i;
	int *b = &i;

	*a = 1;
	*b = 2;

	if (*a == *b) {
		a = &k;
	} else {
	}
}
```

```TAM
PUSH 1
LOADL 3
STORE (1) 0[SB]
PUSH 1
LOADL 6
STORE (1) 1[SB]
PUSH 1
LOADL 7
STORE (1) 2[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 3[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 5[SB]
LOADL 1
;(* a ) start
LOADA 3[SB]
LOADI (1)
;(* a ) end
STOREI (1)
LOADL 2
;(* b ) start
LOADA 5[SB]
LOADI (1)
;(* b ) end
STOREI (1)
;(*a) start
;((*a) == (*b))
LOADA 3[SB]
LOADI (1)
LOADI (2)
;(*a) end
;(*b) start
LOADA 5[SB]
LOADI (1)
LOADI (2)
;(*b) end
SUBR IEq
JUMPIF (0) elseBranch_0
LOADA 2[SB]
LOADI (2)
LOADA 3[SB]
STOREI (2)
JUMP finIf_0
elseBranch_0
finIf_0
HALT
```

### Test sur les pointeurs de pointeurs

```c
test {
	int i = 3;
	int j = 6;
	int k = 7;

	int *a = &i;
	int *b = &i;

	int* * c = &a;

	// Mauvaise affectation
	// c = b;
	c = &b;
}
```

```TAM
PUSH 1
LOADL 3
STORE (1) 0[SB]
PUSH 1
LOADL 6
STORE (1) 1[SB]
PUSH 1
LOADL 7
STORE (1) 2[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 3[SB]
PUSH 2
LOADA 0[SB]
STORE (2) 5[SB]
PUSH 3
LOADA 3[SB]
STORE (3) 7[SB]
LOADA 5[SB]
LOADI (3)
LOADA 7[SB]
STOREI (3)
HALT
```

## Test qui doivent échouer

### Tests pour vérifier que le typage fonctionne sur les pointeurs

```c
test {
	int i = 3;
	int *a = &i;
	*a = false;
}
```

### tests de vérification des types pointés

```c
test {
	int i = 3;
	int j = 6;

	int *a = &i;
	boolean *b = &i;

	*a = 3;
	*b = true;

	if (*a == *b) {
	}
}
```

# Test pour allouer un pointeur sur un boolean avec une erreur de typage

```c
test {
	int i = 3;
	int j = 6;
	int k = 7;
	boolean bool = true;

	int *a = &i;
	int *b = &bool;

	*b = 3;
}
```

## Problèmes

### Problème au niveau de la conversion de type, notamment via l'appel à la fonction merge de

```c
test {
	int i = 3;
	int j = 6;
	int k = 7;

	int *a = &i;
	int *b = &i;

	*a = 1;
	*b = 2;

	if (*a == *b) {
		a = &k;
	} else {
		if (a == &j) {
			a = i;
		}
	}
}
```
