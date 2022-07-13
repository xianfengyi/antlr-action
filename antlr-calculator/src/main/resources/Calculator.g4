grammar Calculator;

/** 起始规则 语法分析器起点 */
prog:	expr NEWLINE ;

expr:	expr op=('*'|'/') expr  # MulDiv
    |	expr op=('+'|'-') expr  # AddSub
    |	INT                     # int
    |	'(' expr ')'            # parens
    ;

INT     : [0-9]+ ;      // 匹配整数
NEWLINE : '\r'? '\n' ;     // 新行 即语句终止标志

MUL     : '*' ;
DIV     : '/' ;
Add     : '+' ;
SUB     : '-' ;