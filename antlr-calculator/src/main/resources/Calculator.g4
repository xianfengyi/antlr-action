grammar Calculator;

/** 起始规则 语法分析器起点 */
expr:	expr op=('*'|'/') expr  # MulDiv
    |	expr op=('+'|'-') expr  # AddSub
    |	number                  # num
    |	'(' expr ')'            # parens
    ;

number
    : INT | decimal
    ;

decimal
    : INT DOT INT
    ;

INT     : [0-9]+ ;
DOT     : '.';

MUL     : '*' ;
DIV     : '/' ;
Add     : '+' ;
SUB     : '-' ;
