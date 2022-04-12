# Fichier de tests sur les whiles

-   Tests implémentés :
    -   Robutesse du typage (condition & body)
    -   Affectation et changement dans un while

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

### Test sur un while vide

```c
test {
	boolean bool = true;
	int k = 4;
	while (k < 10) {
	}
}
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
