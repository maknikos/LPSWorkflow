grammar LPS;

@header{
package com.LPSWorkflow.antlr;
}

program : (reactiveRules | goals | fluents)* ;

reactiveRules   : 'ReactiveRules' '{' r* '}' ;
r               : formula '->' formula END ;

goals   : 'Goals' '{' g* '}' ;
g       : atom '<-' formula END;

fluents : 'Fluents' '{' (f (',' f)* )? ','? '}' ;
f       : atom ;

domainTheory : 'DomainTheory' '{' d* '}';
d            : (postcond | precond) ;

// the condition conjunction for the precond must have at least 2 conditions to be valid
condition    : atom ;
precond      : 'false' '<-' condition ('&' condition)+ END ;
postcond     : (initiates | terminates) END;
initiates    : 'initiates' '(' atom ')' '<-' condition ('&' condition)+ ;
terminates   : 'terminates' '(' atom ')' '<-' condition ('&' condition)+ ;


formula : formula ',' formula  # Sequence
        | formula ':' formula  # Concurrent
        | formula '||' formula # PartialOrder
        | '(' formula ')'      # Bracket
        | atom                 # Atomic
        ;

END     : '.' ;
atom    : (NEG | ID) ;
ID      : [a-zA-Z0-9_]+ ;
NEG     : '!' ID ;
WS      : [\ \t\r\n]+ -> skip ; 
COMMENT
    :   ( '//' ~[\r\n]* '\r'? '\n'
        | '/*' .*? '*/'
        ) -> skip
    ;