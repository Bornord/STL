# STL
Projet de STL

Compiler : 
    ant
Exécuter le fichier input.txt : 
    java -cp bin:tools/java-cup-11b-runtime.jar:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar fr.n7.stl.block.Driver

TP1 : découverte => Expression Binaire & Unaire
=> ajouter if sans else & le while à la grammaire

TP2 : implémenter la gestion de la table des symboles
=> définir collectAndPartielResolve & fullResolve
départ => input.txt : ajout "i=1; if ... then ... else ... "
=> variable Délcaration -> collect, resolve, conditional
=> implanter le typages
-> collect : création table des symboles
-> resolve : exploitation de la table des symboles

