## Question 1 : l'expression suivante n'est pas valide, avec une erreur de parser, 
## alors que ce cas est déclarer avec UL_Enregistrement

int i; 

# Error :
ang3-3.7.jar:tools/commons-text-1.2.jar fr.n7.stl.block.Driver
Syntax error for input symbol "Point Virgule" spanning from null to null
instead expected token classes are [UL_Crochet_Ouvrant]
Couldn't repair and continue parse for input symbol "Point Virgule" spanning from null to null
Exception in thread "main" java.lang.Exception: Can't recover from previous error(s)
        at java_cup.runtime.lr_parser.report_fatal_error(lr_parser.java:392)
        at java_cup.runtime.lr_parser.unrecovered_syntax_error(lr_parser.java:539)
        at java_cup.runtime.lr_parser.parse(lr_parser.java:731)
        at fr.n7.stl.block.Driver.main(Driver.java:9)

## Question 2 : Lors du fullResolve, la table est vide
int i = 3;
int k = i;

# Commentaire :
Je regarde dans le fullResolve, mais je dois ajouter à une TDS ? Car celle de la résolution est vide.
Ce qui est étrange, c'est que je n'ai aucun message d'erreur. 

# Error : 
Error : The identifier i has not been found.
Exception in thread "main" fr.n7.stl.util.BlockSemanticsError
        at fr.n7.stl.util.Logger.error(Logger.java:18)
        at fr.n7.stl.block.ast.expression.accessible.IdentifierAccess.fullResolve(IdentifierAccess.java:74)
        at fr.n7.stl.block.ast.instruction.declaration.VariableDeclaration.fullResolve(VariableDeclaration.java:127)
        at fr.n7.stl.block.ast.Block.resolve(Block.java:81)
        at fr.n7.stl.block.Parser$CUP$Parser$actions.CUP$Parser$do_action_part00000000(Parser.java:754)
        at fr.n7.stl.block.Parser$CUP$Parser$actions.CUP$Parser$do_action(Parser.java:2316)
        at fr.n7.stl.block.Parser.do_action(Parser.java:662)
        at java_cup.runtime.lr_parser.parse(lr_parser.java:699)
        at fr.n7.stl.block.Driver.main(Driver.java:9)