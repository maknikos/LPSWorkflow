grammar LPS;

@header{
package com.LPSWorkflow.antlr;
}

program : (reactiveRules | goals | fluents)* ;

reactiveRules   : 'ReactiveRules' '{' (r (END r)* )?  END? '}' ;
r               : formula '->' formula ;

goals   : 'Goals' '{' (g (END g)* )? END? '}' ;
g       : atom '<-' formula ;

fluents : 'Fluents' '{' (f (',' f)* )? ','? '}' ;
f       : atom ;

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
