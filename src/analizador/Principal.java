package analizador;

import jflex.exceptions.SilentExit;
import java.io.File;
import java_cup.Main;

public class Principal {

    public static void main(String[] args) {

        generarLexer("src/analizador/Lexer.flex");
        generarParser("src/analizador/Parser.cup");
    }

    public static void generarLexer(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("No existe el archivo: " + ruta);
            return;
        }

        try {

            jflex.Main.generate(new String[]{ruta});

            System.out.println("Lexer.java generado correctamente.");

        } catch (Exception e) {

            System.out.println("Error al generar el lexer.");
            e.printStackTrace();
        }
    }

    public static void generarParser(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("No existe el archivo: " + ruta);
            return;
        }

        try {

            Main.main(new String[]{
                    "-package", "analizador",
                    "-parser", "Parser",
                    "-symbols", "sym",
                    "-destdir", "src/analizador",
                    ruta
            });

            System.out.println("Parser.java y sym.java generados correctamente.");

        } catch (Exception e) {

            System.out.println("Error al generar el parser.");
            e.printStackTrace();
        }
    }
}