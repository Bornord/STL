# Test sur les records types

-   Tests implémentés :
    -   affectation & typage simple

## Test qui doivent marcher

### Test sur l'affectation de pointer

```c
test {
	int i = 3;
	int *a = &i;
	*a = 3;
}
```

### Test sur l'affectation de pointer

```c
test {
	int i = 3;
	int *a = &i;
	*a = 3;
}
```

### Test complexe des

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
