# Fichier de tests sur les fonctions

-   Tests implémentés :
    -   fonction contenant plusieurs return
    -   fonction sans return
    -   aspect local des définitions des sous-blocs
    -   surcharge

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.
Dans ce cas, comme la génération de code du return n'est pas effective, les champs de tests sont moindre en terme de génération de code.
De ce fait, le code TAM est souvent absent dans ce fichier précis.

## Tests qui doivent marcher

### Test sur une fonction avec plusieurs return

```c
test {
	boolean premier (int n) {
        return true;
    }
}
```

### Test sur une fonction qui appelle une fonction

```c
test {
	int double (int n) {
        return 2*n;
    }

    boolean test() {
        return double(5) == 10;
    }
}
```

### Test sur une fonction qui utilise une variable extérieure

```c
test {
	int i = 0;
    int incr(int n) {
        return n+1;
    }
    int test() {
        return incr(i);
    }
}
```

### Test sur une fonction qui renvoie un résultat de façon conditionnnelle

```C
test {
	int vrai() {
        int i = 3;
        int j = 4;
        int k = 5;
        boolean bool = false;
        if (k >0) {
            return i;
        } else {
            return j;
        }
    }
}
```

### Test sur une fonction qui n'a pas de return

```c
test {
	void double(int n) {
        int res = 2*n;
    }
}
```

```TAM
double
PUSH 1
;(2 * n)
LOADL 2
;fr.n7.stl.block.ast.expression.accessible.ParameterAccess@768debd start
LOADA -1[LB]
;fr.n7.stl.block.ast.expression.accessible.ParameterAccess@768debd end
LOADI (1)
SUBR IMul
STORE (1) 3[LB]
RETURN (0) 1
HALT
```

## Test qui doivent échouer

### Test sur une fonction qui n'a pas de return

```c
test {
	int double(int n) {
        int res = 2*n;
    }
}
```

### Test sur la surcharge : le collect doit échouer

```c
test {
	int somme (int n1, int n2) {
        return n1 + n2;
    }

    int somme (int n1, int n2, int n3) {
        return n1 + n2 + n3;
    }
}
```

### Test sur un appel de fonction avec pas le bon type des paramètres

```C
test {
	bool testParam(int n, float r, bool b) {
        return true
    }

    bool b = testParam(2, 3.0, "Not a boolean");

}
```

### Tests sur une erreur du nombre d'arguments

```c
test {
	boolean testParam(int n, float r, boolean b) {
        return true;
    }

    boolean b = testParam(2, 3.0);

}
```

### Test sur une fonction de type int, qui renvoie un type de façon conditionnelle

```C
test {
	int vrai() {
        int i = 3;
        int j = 4;
        int k = 5;
        boolean bool = false;
        if (k >0) {
            return i;
        } else {
            return bool;
        }
    }
}
```

### Test sur une fonction de type int, qui renvoie de façon alternative un type

```C
test {
	int vrai() {
        int i = 3;
        int j = 4;
        int k = 5;
        boolean bool = false;
        if (k >0) {
            return i;
        } else {
            int j = 3;
        }
    }
}
```

## Problèmes qui doivent

### Test sur une fonction sans return

// TODO : pas implémenté

```c
test {
    void afficher (String message) {
        print(message);
    }
    afficher();
}
```

### Test sur une fonction qui peut ne rien retourner

```c
test {
	boolean pair(int n) {
        if (n%2 == 0) {
            return true;
        }
    }
}
```
