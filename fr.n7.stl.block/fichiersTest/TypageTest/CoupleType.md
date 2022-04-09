# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel

## Test qui doivent marcher

### Test sur la définition classique

```c
test {
	<int,int> c = <2,3>;
}
```

## Test qui doivent échouer

### Test sur une définition erronée

```c
test {
	<int,boolean> c = <2,3>;
}
```
