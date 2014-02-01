grammar LPS;

program : (reactiveRules | goals)* ;

reactiveRules   : 'ReactiveRules' '{' (r (END r)* )?  END? '}' ;
r               : formula '->' formula ;

goals   : 'Goals' '{' (g (END g)* )? END? '}' ;
g       : atom '<-' formula ;

formula : formula ',' formula
        | formula ':' formula
        | formula '||' formula
        | '(' formula ')'
        | atom
        ;

END     : '.' ;
atom    : (NEG | ID) ;
ID      : [a-zA-Z0-9_]+ ;
NEG     : '!' ID ;
WS      : [\ \t\r\n]+ -> skip ; 
