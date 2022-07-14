grammar CSV;

file: header row+ ;

header: row ;

row : field (',' field)* NEWLINE ;

field
    : TEXT         # text
    | STRING       # string
    |              # empty
    ;

TEXT   : ~[,\n\r"]+ ;
STRING : '"' ('""'|~'"')* '"' ; // quote-quote is an escaped quote
NEWLINE : '\r'? '\n' ;