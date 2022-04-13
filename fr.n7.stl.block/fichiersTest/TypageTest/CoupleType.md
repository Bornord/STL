# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Test qui doivent marcher

### Test sur la définition classique

```c
test {
	<int,int> c = <2,3>;
}
```

```TAM
PUSH 2
;< 2, 3> start
LOADL 2
LOADL 3
;< 2, 3> end
STORE (2) 0[SB]
HALT
```

### Test sur l'imbrication des couples

```c
test {
   <int,<int,boolean>> c = <2,<3,true>>;
}
```

```TAM
PUSH 3
;< 2, < 3, true>> start
LOADL 2
;< 3, true> start
LOADL 3
LOADL 1
;< 3, true> end
;< 2, < 3, true>> end
STORE (3) 0[SB]
HALT
```

### test sur l'imbrication des définitions des couples

```c
test {
	<int,boolean> t = <4,false>;
	<int,<int,boolean>> c = <2,t>;
}
```

```TAM
PUSH 2
;< 4, false> start
LOADL 4
LOADL 0
;< 4, false> end
STORE (2) 0[SB]
PUSH 3
;< 2, t> start
LOADL 2
LOADA 0[SB]
LOADI (2)
;< 2, t> end
STORE (3) 2[SB]
HALT
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

```TAM
PUSH 2
;< 4, false> start
LOADL 4
LOADL 0
;< 4, false> end
STORE (2) 0[SB]
PUSH 3
;< 2, t> start
LOADL 2
LOADA 0[SB]
LOADI (2)
;< 2, t> end
STORE (3) 2[SB]
PUSH 1
;(fstc) start
LOADA 2[SB]
LOADI (1)
;(fstc) end
STORE (1) 5[SB]
PUSH 2
;(sndc) start
LOADA 2[SB]
LOADI (2)
;(sndc) end
STORE (2) 6[SB]
HALT
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

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

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
