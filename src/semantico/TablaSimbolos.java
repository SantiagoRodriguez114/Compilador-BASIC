package semantico;

import java.util.HashMap;

public class TablaSimbolos {
    private HashMap<String, Simbolo> tabla;

    public TablaSimbolos() {
        tabla = new HashMap<>();
    }

    public void insertar(String nombre, String tipo,
                         Object valor, boolean esConstante) {
        if (tabla.containsKey(nombre.toUpperCase())) {
            System.out.println("ERROR SEMANTICO: Variable '"
                    + nombre + "' ya fue declarada.");
            return;
        }
        tabla.put(nombre.toUpperCase(),
                new Simbolo(nombre, tipo, valor, esConstante));
    }

    public void insertarArreglo(String nombre, String tipo, int tamano) {
        if (tabla.containsKey(nombre.toUpperCase())) {
            System.out.println("ERROR SEMANTICO: Variable '"
                    + nombre + "' ya fue declarada.");
            return;
        }
        tabla.put(nombre.toUpperCase(),
                new Simbolo(nombre, tipo, null, false, true, tamano));
    }

    public Simbolo buscar(String nombre) {
        return tabla.get(nombre.toUpperCase());
    }

    public void actualizar(String nombre, Object valor) {
        Simbolo s = buscar(nombre);
        if (s == null) {
            System.out.println("ERROR SEMANTICO: Variable '"
                    + nombre + "' no fue declarada.");
            return;
        }
        if (s.esConstante()) {
            System.out.println("ERROR SEMANTICO: '"
                    + nombre + "' es constante y no puede modificarse.");
            return;
        }
        s.setValor(valor);
    }

    public void mostrar() {
        System.out.println("\n===== TABLA DE SIMBOLOS =====");
        System.out.printf("%-15s %-10s %-15s %-15s%n",
                "Nombre", "Tipo", "Valor", "Categoria");
        System.out.println("-----------------------------------------------");
        for (Simbolo s : tabla.values()) {
            String cat;
            if (s.esConstante()) {
                cat = "CONSTANTE";
            } else if (s.esArreglo()) {
                cat = "ARREGLO[" + s.getTamano() + "]";
            } else {
                cat = "VARIABLE";
            }
            System.out.printf("%-15s %-10s %-15s %-15s%n",
                    s.getNombre(),
                    s.getTipo(),
                    s.getValor() != null ? s.getValor().toString() : "null",
                    cat);
        }
        System.out.println("===============================================");
    }
}