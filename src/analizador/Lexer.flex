package analizador;
import static analizador.Tokens.*;

%%

%class Lexer
%public
%unicode
%ignorecase
%type Tokens
%line
%column

%{
    public String lexema;
%}

LETRA      = [a-zA-Z]
DIGITO     = [0-9]
ALFANUM    = [a-zA-Z0-9_]
SIGNO_TIPO = [$%!#]
ID         = {LETRA}{ALFANUM}*{SIGNO_TIPO}?
ENTERO     = {DIGITO}+
REAL       = {DIGITO}+\.{DIGITO}+([Ee][+-]?{DIGITO}+)?
CADENA     = \"[^\"]*\"
ESPACIO    = [ \t]+

%%

{ESPACIO}           { }
"REM"[^\n]*         { }
\'[^\n]*            { }
\r?\n               { return EOL; }

"IF"        { lexema = yytext(); return IF; }
"THEN"      { lexema = yytext(); return THEN; }
"ELSE"      { lexema = yytext(); return ELSE; }
"ELSEIF"    { lexema = yytext(); return ELSEIF; }
"END"       { lexema = yytext(); return END; }
"FOR"       { lexema = yytext(); return FOR; }
"TO"        { lexema = yytext(); return TO; }
"STEP"      { lexema = yytext(); return STEP; }
"NEXT"      { lexema = yytext(); return NEXT; }
"WHILE"     { lexema = yytext(); return WHILE; }
"WEND"      { lexema = yytext(); return WEND; }
"DO"        { lexema = yytext(); return DO; }
"LOOP"      { lexema = yytext(); return LOOP; }
"UNTIL"     { lexema = yytext(); return UNTIL; }
"EXIT"      { lexema = yytext(); return EXIT; }
"DIM"       { lexema = yytext(); return DIM; }
"AS"        { lexema = yytext(); return AS; }
"CONST"     { lexema = yytext(); return CONST; }
"LET"       { lexema = yytext(); return LET; }
"INTEGER"   { lexema = yytext(); return INTEGER; }
"DOUBLE"    { lexema = yytext(); return DOUBLE; }
"STRING"    { lexema = yytext(); return STRING; }
"BOOLEAN"   { lexema = yytext(); return BOOLEAN; }
"SINGLE"    { lexema = yytext(); return SINGLE; }
"SUB"       { lexema = yytext(); return SUB; }
"FUNCTION"  { lexema = yytext(); return FUNCTION; }
"RETURN"    { lexema = yytext(); return RETURN; }
"CALL"      { lexema = yytext(); return CALL; }
"PRINT"     { lexema = yytext(); return PRINT; }
"INPUT"     { lexema = yytext(); return INPUT; }
"TRUE"      { lexema = yytext(); return TRUE; }
"FALSE"     { lexema = yytext(); return FALSE; }
"AND"       { lexema = yytext(); return AND; }
"OR"        { lexema = yytext(); return OR; }
"NOT"       { lexema = yytext(); return NOT; }
"XOR"       { lexema = yytext(); return XOR; }
"MOD"       { lexema = yytext(); return MOD; }

"<>"        { lexema = yytext(); return OP_REL; }
"<="        { lexema = yytext(); return OP_REL; }
">="        { lexema = yytext(); return OP_REL; }
"<"         { lexema = yytext(); return OP_REL; }
">"         { lexema = yytext(); return OP_REL; }
"="         { lexema = yytext(); return OP_REL; }

"+"         { lexema = yytext(); return SUMA; }
"-"         { lexema = yytext(); return RESTA; }
"*"         { lexema = yytext(); return MULT; }
"/"         { lexema = yytext(); return DIV; }
"\\"        { lexema = yytext(); return DIV_ENTERA; }
"^"         { lexema = yytext(); return POTENCIA; }
"&"         { lexema = yytext(); return CONCAT; }

"("         { lexema = yytext(); return PAR_IZQ; }
")"         { lexema = yytext(); return PAR_DER; }
","         { lexema = yytext(); return COMA; }
";"         { lexema = yytext(); return PUNTO_COMA; }
":"         { lexema = yytext(); return DOS_PUNTOS; }

{REAL}      { lexema = yytext(); return REAL; }
{ENTERO}    { lexema = yytext(); return ENTERO; }
{CADENA}    { lexema = yytext(); return CADENA; }
{ID}        { lexema = yytext(); return IDENTIFICADOR; }

.           { lexema = yytext(); return ERROR; }