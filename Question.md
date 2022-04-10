# Erreurs

## Différence Collect vs collectResolve vs Resolve.

## FieldAccess

Erreur sur les FieldAccess.
Je n'arrive pas à mettre les fields dans la tds.

```C
p.x1 = 5
```

-> classe assignable : puis j'ai accès au field Assignement, mais pas aux labels. Ils ne sont pas présents dans la tds

-> même problème sur le recordType :
J'arrive jusqu'au traitement naturel ele = naturel.UN;
Mais je n'arrive pas à avoir accès à naturel.UN et le faire rentrer dans la tds

## Pointeur

Dans l'assignement, je n'ai pas accès au nom de la variable.

---

---

# Traité

## Pour merge de la classe RecordType: définir un type commun

int\* a = 3;
int a = 4;

-> refus compilateur

## ça marche pas

test {

int *a = 2;
*a = 3;
}

-> on associe un int avec un pointeur sur int -> aie.
du coup :
int i = 3;
int *a = &i;
*a = 3;

# erreur linux
