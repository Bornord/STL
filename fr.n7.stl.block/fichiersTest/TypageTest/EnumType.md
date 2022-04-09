# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel

## Test qui doivent marcher

### Test sur la définition classique

```c
test {
	typedef enum nat { ZERO, UN, DEUX } naturel;
	naturel ele = naturel.UN;
}
```

## Test qui doivent échouer

### Test sur
