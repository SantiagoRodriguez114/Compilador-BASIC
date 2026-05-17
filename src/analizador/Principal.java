package analizador;

import java.io.File;
import jflex.exceptions.SilentExit;

public class Principal {

    public static void main(String[] args) {
        String ruta = "src/analizador/Lexer.flex";
        generarLexer(ruta);
    }

    public static void generarLexer(String ruta) {
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("No existe el archivo");
            return;
        }

        try {
            jflex.Main.generate(new String[]{ ruta });
            System.out.println("Lexer generado correctamente");
        } catch (SilentExit e) {
            System.out.println("Error al generar lexer");
        }
    }
}