### STL

Projet de STL

## Compiler :

> ant

## Exécuter le fichier input.txt :

> java -cp bin:tools/java-cup-11b-runtime.jar:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar fr.n7.stl.block.Driver

## Lancer le code sur MV TAM :

UNIX :

> java -jar runtam.jar ./fichiersTest/FichiersTAMTests/{fichier}.tam

WINDOWS :

> java -jar runtam.jar .\fichiersTest\FichiersTAMTests\{fichier}.tam

Fichier de configuration :

> runtam fonctionne de sûr avec ./fichiersTest/FichiersTAMTests/exempleTD.tam

## Lancer la MV TAMADRA

MAC :

> java -jar --module-path $HOME/Documents/Config/jfx/lib --add-modules javafx.controls,javafx.fxml itam.jar

## Comment lire :

-   Les classes sont complétés dans fr.n7.stl.block/src/
-   Un dossier fichierTest disponibles dans fr.n7.stl.block/fichierTest
    -   Les tests sont découpés en 3 parties :
        -   GenerationTest qui rescense les tests de génération de code
        -   TDSTest qui rescense les tests sur la sémentique et la gestion de la TDS
        -   TypageTest qui rescense les tests sur les types

## Remarques
