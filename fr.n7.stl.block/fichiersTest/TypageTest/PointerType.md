# Test sur les records types

-   Tests implémentés :
    -   affectation & typage simple

## Test qui doivent marcher

### Test basique

```c
test {
	int *b = 3;
}
```

## Test qui doivent échouer

### Tests pour vérifier que le typage fonctionne sur les pointeurs

```c
test {
	int *b = false;
}
```
