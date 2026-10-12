# Compilador-BASIC

## Descripción general

Este repositorio contiene una implementación académica de un compilador para un subconjunto del lenguaje BASIC, desarrollada en Java. El proyecto tiene un enfoque didáctico y está orientado a demostrar los conceptos fundamentales de la construcción de compiladores: análisis léxico, análisis sintáctico, comprobación semántica y manejo de errores.

El objetivo principal del proyecto es procesar programas escritos en un lenguaje BASIC simplificado, validar su estructura y detectar errores de sintaxis y semántica durante la compilación.

## Objetivo del proyecto

El compilador permite:

- reconocer palabras reservadas del lenguaje BASIC
- interpretar expresiones aritméticas y lógicas
- validar sentencias de control como IF, FOR, WHILE y DO
- declarar variables, constantes y arreglos
- detectar errores de uso y tipo
- ejecutar un flujo de análisis completo sobre archivos de entrada

## Arquitectura del compilador

La solución se divide en módulos con responsabilidades bien definidas:

### 1. Análisis léxico

La parte léxica se define en `src/analizador/Lexer.flex` y es generada con JFlex. Su función es transformar el código fuente en una secuencia de tokens reconocibles por el compilador.

Se identifican elementos como:

- palabras reservadas (`IF`, `THEN`, `ELSE`, `FOR`, `NEXT`, `PRINT`, `INPUT`, etc.)
- identificadores de variables
- literales enteros y reales
- cadenas de texto
- operadores aritméticos y relacionales
- delimitadores y símbolos de agrupación
- comentarios y fin de línea

También se reportan errores léxicos cuando se encuentra un carácter no válido.

### 2. Análisis sintáctico

La gramática del lenguaje está definida en `src/analizador/Parser.cup` y se procesa mediante CUP. El parser valida que el flujo de tokens cumpla con la estructura del lenguaje BASIC implementado.

Entre las sentencias soportadas se encuentran:

- declaraciones de variables y constantes
- asignaciones
- expresiones aritméticas
- expresiones lógicas y de comparación
- estructuras condicionales (`IF`, `ELSEIF`, `ELSE`)
- ciclos (`FOR`, `WHILE`, `DO`)
- subrutinas y funciones (`SUB`, `FUNCTION`, `CALL`, `RETURN`)
- entrada y salida (`PRINT`, `INPUT`)
- control de flujo (`END`, `EXIT`)

### 3. Análisis semántico

La validación semántica se realiza en `src/semantico/AnalizadorSemantico.java` junto con las clases `TablaSimbolos.java` y `Simbolo.java`.

Su responsabilidad es verificar que:

- las variables hayan sido declaradas antes de usarse
- no se reasigne el valor de una constante
- los tipos de datos sean compatibles
- los arreglos tengan índices válidos
- el acceso a elementos de un arreglo sea correcto
- se mantenga una tabla de símbolos consistente durante la compilación

### 4. Generación del lexer y parser

La clase `src/analizador/Principal.java` genera automáticamente los archivos Java del lexer y del parser desde sus definiciones respectivas:

- `Lexer.flex`
- `Parser.cup`

Esto permite automatizar la producción de los artefactos necesarios para la ejecución del compilador.

### 5. Front-end del compilador

`src/analizador/FrontEnd.java` actúa como punto de entrada del análisis. Lee un archivo fuente, crea el lexer y el parser, y ejecuta la compilación del programa.

## Estructura del repositorio

```text
Compilador-BASIC/
├── .gitignore
├── Analizador Lexico Basic v1.pdf
├── Pruebas/
│   └── prueba1.txt
├── lib/
├── src/
│   ├── analizador/
│   │   ├── FrontEnd.java
│   │   ├── Lexer.flex
│   │   ├── Lexer.java
│   │   ├── Parser.cup
│   │   ├── Parser.java
│   │   ├── Principal.java
│   │   ├── Tokens.java
│   │   └── sym.java
│   └── semantico/
│       ├── AnalizadorSemantico.java
│       ├── Simbolo.java
│       └── TablaSimbolos.java
└── README.md
```

## Componentes clave

### `src/analizador/Lexer.flex`
Es la especificación del analizador léxico. Define las expresiones regulares para cada token y las reglas de reconocimiento del lenguaje.

### `src/analizador/Parser.cup`
Define la gramática del lenguaje y el comportamiento del parser. Incluye las reglas para las sentencias y expresa la lógica de validación estructural.

### `src/semantico/AnalizadorSemantico.java`
Contiene la lógica de comprobación semántica. Verifica relaciones entre identificadores, tipos, constantes y arreglos.

### `src/semantico/TablaSimbolos.java`
Sirve como estructura central para registrar todas las variables, constantes y arreglos del programa.

### `Pruebas/prueba1.txt`
Es un archivo de pruebas que contiene ejemplos de uso del lenguaje y casos de error. Permite validar el comportamiento del compilador frente a situaciones normales y no normales.

## Ejemplos de lenguaje soportado

El compilador reconoce instrucciones de estilo BASIC, por ejemplo:

```basic
DIM contador AS INTEGER
DIM precio AS DOUBLE
DIM nombre AS STRING
CONST PI = 3.1416

contador = (5 + 3) * 2
precio = 15.75 ^ 2
nombre = "Pepito" & " Perez"

IF contador > 5 THEN
    PRINT contador
END IF

FOR i = 1 TO 10
    PRINT i
NEXT

WHILE contador < 20
    contador = contador + 1
WEND
```

## Capacidades del compilador

El proyecto implementa un subconjunto funcional del lenguaje BASIC, incluyendo:

- variables enteras, reales, de cadena y booleanas
- declaraciones con `DIM` y `CONST`
- asignaciones con `LET` o directamente
- estructuras condicionales
- ciclos `FOR`, `WHILE` y `DO`
- expresiones con operadores aritméticos y lógicos
- manejo de arreglos
- control de tipos y errores semánticos

## Flujo de ejecución

El flujo general del compilador es el siguiente:

1. El archivo de entrada se lee.
2. El lexer genera tokens.
3. El parser valida la sintaxis.
4. El analizador semántico verifica reglas de tipo y uso.
5. Se muestran mensajes de error o éxito según corresponda.

## Tecnologías utilizadas

- Java
- JFlex
- CUP
- Estructuras tradicionales de análisis léxico y sintáctico

## Aplicación académica

Este proyecto es un ejemplo clásico de compilador didáctico. Su objetivo no es reemplazar un lenguaje completo, sino mostrar de manera clara cómo se implementan las etapas fundamentales de un compilador y cómo se integran en una solución real con Java.

## Conclusión

`Compilador-BASIC` es un proyecto orientado al aprendizaje y la práctica de compiladores. Su valor principal radica en la integración de análisis léxico, análisis sintáctico y análisis semántico dentro de una misma solución, permitiendo comprender cómo un programa fuente se transforma y valida antes de ser ejecutado.
