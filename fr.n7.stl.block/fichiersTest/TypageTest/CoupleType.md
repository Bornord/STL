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

### Test sur l'imbrication des couples

```c
test {
   <int,<int,boolean>> c = <2,<3,true>>;
}
```

### test sur l'imbrication des définitions des couples

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
}
```

### test sur la récupération des attributs

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
	int k = fst c;
	<int,boolean> coupl = snd c;
}
```

## Test qui doivent échouer

### Test sur une définition erronée

```c
test {
	<int,boolean> c = <2,3>;
}
```

### Test sur la mauvaise récupération d'une champ

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
	int k = snd c;
}
```

### Test sur l'aspect non-mutable des champs

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
	fst c = 4;
}
```

### Test sur la mauvaise récupération d'une champ via l'accesseur

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
	int k = fst c;
	<int,int> coupl = snd c;
}
```

## Problème
