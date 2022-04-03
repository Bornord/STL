# Test sur les records types

-   Tests implémentés :
    -   affectations & imbrications de types
    -   système de typage opérationnel

## Test qui doivent marcher

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	int a = 3;
	int b = 1;

	Point p = {a,b};
}
```

### test sur la définition imbriquée

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		Point p;
	} Point;

	int a = 3;
	int b = 1;

	Point p = {a,null};
	Point p2 = {a, p};
}
```

### Test

## Test qui doivent échouer

### Test sur la comparaison de deux types différents

```c
test {
	int res = 0;

	typedef struct p{
		int x1;
		int x2;
	} Point;

	typedef struct p{
		int x1;
		int x2;
	} PointBis;

	int a = 3;
	int b = 1;
	int c = a;
	int d = b;
	Point p = {a,b};

	PointBis p2 = {c,d};

     // ça ne passe pas, car les types sont différents
	p2 = p;
}
```

### Test sur des champs vides

```c
test {
	int res = 0;

	typedef struct p{
	} Point;

}
```
