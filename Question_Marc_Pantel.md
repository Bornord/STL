# Pour merge de la classe RecordType: définir un type commun

int\* a = 3;
int a = 4;

-> refus

# ça marche pas

test {

int *a = 2;
*a = 3;
}

-> on associe un int avec un pointeur sur int -> aie.
du coup :
int i = 3;
int *a = &i;
*a = 3;
