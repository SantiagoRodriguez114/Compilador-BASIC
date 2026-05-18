package analizador;
import java_cup.runtime.*;

%%
%class Lexer
%public
%unicode
%ignorecase
%cup
%line
%column

%{
    public Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    public Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LETRA      = [a-zA-Z]
DIGITO     = [0-9]
ALFANUM    = [a-zA-Z0-9_]
SIGNO_TIPO = [$%!#]
ID         = {LETRA}{ALFANUM}*{SIGNO_TIPO}?
ENTERO_LIT = {DIGITO}+
REAL_LIT   = {DIGITO}+\.{DIGITO}+([Ee][+-]?{DIGITO}+)?
CADENA_LIT = \"[^\"]*\"
ESPACIO    = [ \t]+

%%

{ESPACIO}           { /* ignorar */ }
"REM"[^\n]*         { /* ignorar comentarios REM */ }
\'[^\n]*            { /* ignorar comentarios ' */ }
\r?\n               { return symbol(sym.EOL); }

"IF"        { return symbol(sym.IF);     }
"THEN"      { return symbol(sym.THEN);   }
"ELSE"      { return symbol(sym.ELSE);   }
"ELSEIF"    { return symbol(sym.ELSEIF); }
"END"       { return symbol(sym.END);    }
"FOR"       { return symbol(sym.FOR);    }
"TO"        { return symbol(sym.TO);     }
"STEP"      { return symbol(sym.STEP);   }
"NEXT"      { return symbol(sym.NEXT);   }
"WHILE"     { return symbol(sym.WHILE);  }
"WEND"      { return symbol(sym.WEND);   }
"DO"        { return symbol(sym.DO);     }
"LOOP"      { return symbol(sym.LOOP);   }
"UNTIL"     { return symbol(sym.UNTIL);  }
"EXIT"      { return symbol(sym.EXIT);   }
"DIM"       { return symbol(sym.DIM);    }
"AS"        { return symbol(sym.AS);     }
"CONST"     { return symbol(sym.CONST);  }
"LET"       { return symbol(sym.LET);    }
"INTEGER"   { return symbol(sym.INTEGER); }
"DOUBLE"    { return symbol(sym.DOUBLE);  }
"STRING"    { return symbol(sym.STRING);  }
"BOOLEAN"   { return symbol(sym.BOOLEAN); }
"SINGLE"    { return symbol(sym.SINGLE);  }
"SUB"       { return symbol(sym.SUB);      }
"FUNCTION"  { return symbol(sym.FUNCTION); }
"RETURN"    { return symbol(sym.RETURN);   }
"CALL"      { return symbol(sym.CALL);     }
"PRINT"     { return symbol(sym.PRINT); }
"INPUT"     { return symbol(sym.INPUT); }
"TRUE"      { return symbol(sym.TRUE);  }
"FALSE"     { return symbol(sym.FALSE); }
"AND"       { return symbol(sym.AND); }
"OR"        { return symbol(sym.OR);  }
"NOT"       { return symbol(sym.NOT); }
"XOR"       { return symbol(sym.XOR); }
"MOD"       { return symbol(sym.MOD); }

"<>"        { return symbol(sym.OP_REL, yytext()); }
"<="        { return symbol(sym.OP_REL, yytext()); }
">="        { return symbol(sym.OP_REL, yytext()); }
"<"         { return symbol(sym.OP_REL, yytext()); }
">"         { return symbol(sym.OP_REL, yytext()); }
"="         { return symbol(sym.IGUAL); }

"+"         { return symbol(sym.SUMA);       }
"-"         { return symbol(sym.RESTA);      }
"*"         { return symbol(sym.MULT);       }
"/"         { return symbol(sym.DIV);        }
"\\"        { return symbol(sym.DIV_ENTERA); }
"^"         { return symbol(sym.POTENCIA);   }
"&"         { return symbol(sym.CONCAT);     }
"("         { return symbol(sym.PAR_IZQ);    }
")"         { return symbol(sym.PAR_DER);    }
","         { return symbol(sym.COMA);       }
";"         { return symbol(sym.PUNTO_COMA); }
":"         { return symbol(sym.DOS_PUNTOS); }

{REAL_LIT}   { return symbol(sym.REAL,          Double.parseDouble(yytext())); }
{ENTERO_LIT} { return symbol(sym.ENTERO,        Integer.parseInt(yytext()));   }
{CADENA_LIT} { return symbol(sym.CADENA,        yytext());                     }
{ID}         { return symbol(sym.IDENTIFICADOR, yytext());                     }


. {
    System.out.println(
        "Error léxico: '" + yytext()
        + "' en línea " + (yyline + 1)
        + ", columna " + (yycolumn + 1)
    );
}