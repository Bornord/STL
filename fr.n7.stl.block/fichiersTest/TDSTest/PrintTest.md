# Fichier de tests sur les print

-   Tests implémentés :
    -   affectations mutliples & redéfinitions locales
    -   typage des sous-blocs
    -   aspect local des définitions des sous-blocs
    -   typage des conditions

## Tests qui doivent marcher

### Test basique sur un entier

```c
test {
    int i = 1;
	printf("%i \n", i);
}
```


## Test qui doivent échouer


### Test sur une déclaration locale

```c
test {
	printf("%i \n", int i=1);
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

### Test sur un mauvais type de condition de boucle

```c
test {
	float r = 3.14;
    while (r) {
        printf("Rentré dans la boucle");
    }
}
```
