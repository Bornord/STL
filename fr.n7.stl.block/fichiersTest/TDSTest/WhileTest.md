# Fichier de tests sur les whiles

-   Tests implémentés :

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

## Test qui doivent échouer

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
