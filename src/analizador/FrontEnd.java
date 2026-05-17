package analizador;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontEnd {

    public String resultado = "";

    public void analizarCodigo() {

        try {
            Reader lector = new BufferedReader(
                    new FileReader("Pruebas/prueba1.txt")
            );
            Lexer lexer = new Lexer(lector);
            String resultado = "";

            while (true) {
                Tokens tokens = lexer.yylex();

                if (tokens == null) {
                    resultado += "FIN\n";
                    this.resultado = resultado;
                    return;
                }

                switch (tokens) {
                    case ERROR:
                        resultado += "Símbolo no identificado: "
                                + lexer.lexema + "\n";
                        break;
                    case IDENTIFICADOR:
                        resultado += lexer.lexema + ": Es un IDENTIFICADOR\n";
                        break;
                    case ENTERO:
                    case REAL:
                        resultado += lexer.lexema + ": Es un NUMERO\n";
                        break;
                    case CADENA:
                        resultado += lexer.lexema + ": Es una CADENA\n";
                        break;
                    case EOL:
                        break;
                    default:
                        resultado += "Token: " + tokens + "\n";
                        break;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontEnd.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        FrontEnd fe = new FrontEnd();
        fe.analizarCodigo();
        System.out.println(fe.resultado);
    }
}