package analizador;

import java.io.*;

public class FrontEnd {

    private static final String ARCHIVO = "Pruebas/prueba1.txt";

    public void analizarConParser() {
        System.out.println("=== Analizador===\n");
        try {
            Reader lector = new BufferedReader(new FileReader(ARCHIVO));
            Parser parser = new Parser(new Lexer(lector));
            parser.parse();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: archivo no encontrado → " + ARCHIVO);
        } catch (Exception ex) {
            System.out.println("Error durante el análisis: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        FrontEnd fe = new FrontEnd();
        fe.analizarConParser();
    }
}
