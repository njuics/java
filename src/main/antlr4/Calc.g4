grammar Calc;

@header {
package com.example.antlr;
}

expr:   expr '+' term    # Add
    |   expr '-' term    # Sub
    |   term             # TermValue
    ;

term:   term '*' factor  # Mul
    |   term '/' factor  # Div
    |   factor           # FactorValue
    ;

factor: NUMBER           # Number
    |   '(' expr ')'    # Parens
    ;

NUMBER: [0-9]+ ('.' [0-9]+)?;
WS: [ \t\r\n]+ -> skip;
