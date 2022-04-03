### STL

Projet de STL

## Compiler :

    ant

## Exécuter le fichier input.txt :

java -cp bin:tools/java-cup-11b-runtime.jar:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar fr.n7.stl.block.Driver

## Comment lire :

-   Les classes sont complétés dans fr.n7.stl.block/src/
-   Un dossier fichierTest disponibles dans fr.n7.stl.block/fichierTest
    -   Les tests sont découpés en 3 parties :
        -   GenerationTest qui rescense les tests de génération de code
        -   TDSTest qui rescense les tests sur la sémentique et la gestion de la TDS
        -   TypageTest qui rescense les tests sur les types

## Remarques

### Fonction de la table

int f() {
return 1
}

1 doit être compatible ave int. la
FunctionDeclaration -> Parcourir Block (returnTo : FunctionDeclaration) -> Parcourir Instruction (returnTo : FunctionDeclaration) -> Parcourir Return -> Retourne un truc.
