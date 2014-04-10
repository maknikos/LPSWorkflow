grammar LPS;

@header{
package com.LPSWorkflow.antlr;
}

program : (reactiveRules | goals | fluents | domainTheory)* ;

reactiveRules   : 'ReactiveRules' '{' r* '}' ;
r               : formula '->' formula END ;

goals   : 'Goals' '{' g* '}' ;
g       : atom '<-' formula END;

fluents : 'Fluents' '{' (f (',' f)* )? ','? '}' ;
f       : atom ;

formula : formula ',' formula  # Sequence
        | formula ':' formula  # Concurrent
        | formula '||' formula # PartialOrder
        | '(' formula ')'      # Bracket
        | atom                 # Atomic
        ;

domainTheory : 'DomainTheory' '{' d* '}';
d            : (postcond | precond) ;
conjunction  : atom ('&' atom)* ;
precond      : 'false' '<-' conjunction END ;
postcond     : (initiates | terminates) END;
initiates    : 'initiates' '(' atom ')' '<-' conjunction ;
terminates   : 'terminates' '(' atom ')' '<-' conjunction ;

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