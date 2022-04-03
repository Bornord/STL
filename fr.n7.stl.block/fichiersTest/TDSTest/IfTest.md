# Fichier de tests sur les if

-   Tests implémentés :
    -   affectations mutliples & redéfinitions locales
    -   typage des sous-blocs
    -   aspect local des définitions des sous-blocs
    -   typage des conditions

## Tests qui doivent marcher

### Test sur l'affectation multiples & redéfinitions locales

```c
test {
	int i = 1;
	int k = 2;
	int j = i;
	if (i == 2) {
		int k = 4;
	} else {
		int j = 5;
	}
}
```

### Test sans branche else

```c
test {
	int i = 1;
	int k = 2;
	int j = i;
	if (i == 2) {
		int k = 4;
	}
}
```

### Test pour vérifier que les mémoires locales if & else sont distinctes

```c
test {
	int i = 1;
	if (i != 1) {
		boolean j = true;
	} else {
		int j = 3;
	}
}
```

### Test pour vérifier qu une variable peut conditionner le if

```c
test {
	int i = 1;
	boolean bool = true;
	if (bool) {
		boolean j = true;
	} else {
		int j = 3;
	}
}
```

## Test qui doivent échouer

### Test sur la définition local qui ne doit pas être propagée globalement

```c
test {
	int i = 1;
	if (i == 2) {
		int j = 2;
	} else {
		// rien
	}
	// ça passe pas
	j = 3;
}
```

### Test sur la condition du if

```c
test {
	int i = 1;
	if (2) {
		boolean j = true;
	} else {
		int j = true;
	}
}
```

### Test sur le bon typage du block else

```c
test {
	int i = 1;
	if (i == 2) {
		4;
	} else {
		true;
	}
}
```

### Test d exploitation d une varibale hors de sa zone de défintion.

```C
test {
	int i = 1;
	int k = 2;
	int j = i;
	if (i == 2) {
		int g = 4;
	} else {
		int g = 5;
	}
	g = 3;
}
```
