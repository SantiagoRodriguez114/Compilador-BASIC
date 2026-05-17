package analizador;

public enum Tokens {

    /* ===== Palabras reservadas ===== */
    IF, THEN, ELSE, ELSEIF, END,
    FOR, TO, STEP, NEXT,
    WHILE, WEND, DO, LOOP, UNTIL, EXIT,

    DIM, AS, CONST, LET,

    INTEGER, DOUBLE, STRING, BOOLEAN, SINGLE,

    SUB, FUNCTION, RETURN, CALL,

    PRINT, INPUT,

    TRUE, FALSE,

    /* ===== Operadores ===== */
    SUMA, RESTA, MULT, DIV, DIV_ENTERA, POTENCIA, MOD,
    OP_REL,        // =, <>, <, >, <=, >=
    AND, OR, NOT, XOR,
    CONCAT,        // &

    /* ===== Delimitadores ===== */
    PAR_IZQ, PAR_DER,
    COMA, PUNTO_COMA, DOS_PUNTOS,

    /* ===== Literales ===== */
    ENTERO,
    REAL,
    CADENA,

    /* ===== Otros ===== */
    IDENTIFICADOR,
    EOL,
    ERROR
}