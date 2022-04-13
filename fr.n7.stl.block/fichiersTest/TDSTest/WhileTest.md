# Fichier de tests sur les whiles

-   Tests implémentés :
    -   Robutesse du typage (condition & body)
    -   Affectation et changement dans un while

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Tests qui doivent marcher

### Test sur le typage où la condition n'est pas un booléen explicitement

```c
test {
	boolean bool = true;
	int k = 4;
	while (k < 10) {
		k = k + 3;
	}
}
```

```TAM
PUSH 1
LOADL 1
STORE (1) 0[SB]
PUSH 1
LOADL 4
STORE (1) 1[SB]
;(k < 10)
etiq_cond_tantque_0
LOADA 1[SB]
LOADI (1)
LOADL 10
SUBR ILss
JUMPIF (0) fin_tantQue_0
;(k + 3)
LOADA 1[SB]
LOADI (1)
LOADL 3
SUBR IAdd
LOADA 1[SB]
STOREI (1)
JUMP etiq_cond_tantque_0
fin_tantQue_0
HALT
```

### Test sur un while vide

```c
test {
	boolean bool = true;
	int k = 4;
	while (k < 10) {
	}
}
```

```TAM
PUSH 1
LOADL 1
STORE (1) 0[SB]
PUSH 1
LOADL 4
STORE (1) 1[SB]
;(k < 10)
etiq_cond_tantque_0
LOADA 1[SB]
LOADI (1)
LOADL 10
SUBR ILss
JUMPIF (0) fin_tantQue_0
JUMP etiq_cond_tantque_0
fin_tantQue_0
HALT
```

### test un peu plus complexe avec manipulation de conditions

```c
test {
	boolean bool = true;
	int k = 4;
	while (bool) {
		k = k + 3;
		if (k < 20) {
			bool = false;
		}
	}
}
```

```TAM
PUSH 1
LOADL 1
STORE (1) 0[SB]
PUSH 1
LOADL 4
STORE (1) 1[SB]
etiq_cond_tantque_0
LOADA 0[SB]
LOADI (1)
JUMPIF (0) fin_tantQue_0
;(k + 3)
LOADA 1[SB]
LOADI (1)
LOADL 3
SUBR IAdd
LOADA 1[SB]
STOREI (1)
;(k < 20)
LOADA 1[SB]
LOADI (1)
LOADL 20
SUBR ILss
JUMPIF (0) elseBranch_0
LOADL 0
LOADA 0[SB]
STOREI (1)
JUMP finIf_0
elseBranch_0
finIf_0
JUMP etiq_cond_tantque_0
fin_tantQue_0
HALT
```

### Test sur la redéfinition locale

```c
test {
	int i = 10;
     int j = 5;
     while (i < 100) {
     	int j = i;
     	i = i + 1;
    }
}
```

```TAM
PUSH 1
LOADL 10
STORE (1) 0[SB]
PUSH 1
LOADL 5
STORE (1) 1[SB]
;(i < 100)
etiq_cond_tantque_0
LOADA 0[SB]
LOADI (1)
LOADL 100
SUBR ILss
JUMPIF (0) fin_tantQue_0
PUSH 1
LOADA 0[SB]
STORE (1) 2[SB]
;(i + 1)
LOADA 0[SB]
LOADI (1)
LOADL 1
SUBR IAdd
LOADA 0[SB]
STOREI (1)
JUMP etiq_cond_tantque_0
fin_tantQue_0
HALT
```

## Test qui doivent échouer

### Test sur la redéfinition locale d'expression dans une conditionnelle

```c
test {
	int i = 10;
    while (i = i-1) {
        int i = 0;
    }
}
```

### Test sur le typage de la condition booléenne

```c
test {
	boolean bool = true;
	int k = 4;
	while (k) {
		k = k + 3;
	}
}
```

### Tests sur une codntion de while vides

```c
test {
	boolean bool = true;
	int k = 4;
	while () {
	}
}
```

### test sur un mauvais typage au sein d'un while vides

```c
test {
	boolean bool = false;
	int k = 4;
	while (bool) {
		k = k + 3;
		if (k < 20) {
			bool = true;
		}
		int bool = true;
	}
}
```

### Test sur la définition local qui ne doit pas être propagée globalement

```c
test {
	int i = 1;
	while (i) {
        int j = 2;
        i--;
    }
    j = 3;
}
```
