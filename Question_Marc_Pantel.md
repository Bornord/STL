# Pas traité pour

-   ça doit marcher ?

test {
int res = 0;

    typedef struct p{
    	int x1;
    	Point p;
    } Point;

    typedef Point po;
    int a = 3;
    int b = 1;

    po p = {a,null};

    Point p2 = p;

}

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
