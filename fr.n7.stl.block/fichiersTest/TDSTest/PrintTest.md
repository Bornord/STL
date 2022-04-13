# Test sur

Les tests implantés portent sur : -

La génération de code associée à chaque programme a été copiée depuis notre terminal à la suite du code C.

## Test qui doivent marcher

### Test sur l'affichage d'une variable

```c
test {
	int a = 3;
	print a;
}
```

```TAM
PUSH 1
LOADL 3
STORE (1) 0[SB]
LOADA 0[SB]
LOADI (1)
SUBR IOut
HALT
```

### Test sur l'affichage d'une fonction

```c
test {
	int b = 1;
	void f (int a) {
		a = a+ 1;
	}
	print f(b);
}
```

PUSH 1
LOADL 1
STORE (1) 0[SB]
;fr.n7.stl.block.ast.expression.accessible.ParameterAccess@66133adc start
;(a + 1)
f
LOADA -1[LB]
;fr.n7.stl.block.ast.expression.accessible.ParameterAccess@66133adc end
LOADI (1)
LOADL 1
SUBR IAdd
LOADA -1[LB]
STOREI (1)
RETURN (0) 1
;void f( int a ){
a = (a + 1);
}
( b) start
LOADA 0[SB]
LOADI (1)
CALL (SB) f
;void f( int a ){
a = (a + 1);
}
( b) end
SUBR SOut
HALT

### Test sur l'affichage d'une constante

```c
test {

	print 2;
}
```

```TAM
LOADL 2
SUBR IOut
HALT
```

## Test qui doivent échouer

### Test interdit par le parser

```c
test {
	print int i = 3;
}
```
