package semantico;

public class AnalizadorSemantico {
    public TablaSimbolos tabla;

    public AnalizadorSemantico() {
        tabla = new TablaSimbolos();
    }

    // DIM nombre AS tipo
    public void declarar(String tipo, String nombre) {
        tabla.insertar(nombre, tipo, null, false);
        System.out.println("SEMANTICO: Declarada variable '"
                + nombre + "' de tipo " + tipo);
    }

    // DIM nombre(n) AS tipo
    public void declararArreglo(String tipo, String nombre, int tamano) {
        tabla.insertarArreglo(nombre, tipo, tamano);
        System.out.println("SEMANTICO: Declarado arreglo '"
                + nombre + "' de tipo " + tipo
                + " con " + tamano + " elementos");
    }

    // CONST nombre = valor
    public void declararConstante(String nombre, Object valor) {
        tabla.insertar(nombre, inferirTipo(valor), valor, true);
        System.out.println("SEMANTICO: Declarada constante '"
                + nombre + "' = " + valor);
    }

    // nombre = expresion
    public void asignar(String nombre, Object valor) {
        Simbolo s = tabla.buscar(nombre);
        if (s == null) {
            System.out.println("ERROR SEMANTICO: Variable '"
                    + nombre + "' no declarada en asignacion.");
            return;
        }
        if (s.esConstante()) {
            System.out.println("ERROR SEMANTICO: '"
                    + nombre + "' es constante, no se puede asignar.");
            return;
        }
        if (s.esArreglo()) {
            System.out.println("ERROR SEMANTICO: '"
                    + nombre + "' es un arreglo, use indice para asignar.");
            return;
        }
        if (valor == null) {
            System.out.println("ADVERTENCIA SEMANTICA: valor nulo en asignacion de '"
                    + nombre + "' (variable usada antes de inicializar).");
            return;
        }
        if (!tiposCompatibles(s.getTipo(), valor)) {
            System.out.println("ERROR SEMANTICO: Tipo incompatible en '"
                    + nombre + "'. Esperado: " + s.getTipo()
                    + ", recibido: " + inferirTipo(valor));
            return;
        }
        tabla.actualizar(nombre, valor);
        System.out.println("SEMANTICO: Asignado '"
                + nombre + "' = " + valor);
    }

    // nombre(idx) = valor
    public void asignarArreglo(String nombre, Object indice, Object valor) {
        Simbolo s = tabla.buscar(nombre);
        if (s == null) {
            System.out.println("ERROR SEMANTICO: Arreglo '"
                    + nombre + "' no declarado.");
            return;
        }
        if (!s.esArreglo()) {
            System.out.println("ERROR SEMANTICO: '"
                    + nombre + "' no es un arreglo.");
            return;
        }
        if (indice instanceof Integer) {
            int idx = (Integer) indice;
            if (idx < 0 || idx >= s.getTamano()) {
                System.out.println("ERROR SEMANTICO: Indice " + idx
                        + " fuera de rango en arreglo '" + nombre
                        + "' (tamano: " + s.getTamano() + ").");
                // No retorna, continua para no romper el flujo del parser
            } else {
                if (!tiposCompatibles(s.getTipo(), valor)) {
                    System.out.println("ERROR SEMANTICO: Tipo incompatible en arreglo '"
                            + nombre + "'. Esperado: " + s.getTipo()
                            + ", recibido: " + inferirTipo(valor));
                } else {
                    System.out.println("SEMANTICO: Asignado '"
                            + nombre + "[" + idx + "]' = " + valor);
                }
            }
        } else {
            // Indice es variable o expresion, no se verifica estaticamente
            if (!tiposCompatibles(s.getTipo(), valor)) {
                System.out.println("ERROR SEMANTICO: Tipo incompatible en arreglo '"
                        + nombre + "'. Esperado: " + s.getTipo()
                        + ", recibido: " + inferirTipo(valor));
            } else {
                System.out.println("SEMANTICO: Asignado '"
                        + nombre + "[" + indice + "]' = " + valor);
            }
        }
    }

    // Verificar acceso a elemento de arreglo en expresion
    public void verificarAccesoArreglo(String nombre, Object indice) {
        Simbolo s = tabla.buscar(nombre);
        if (s == null) {
            System.out.println("ERROR SEMANTICO: Arreglo '"
                    + nombre + "' no declarado.");
            return;
        }
        if (!s.esArreglo()) {
            System.out.println("ERROR SEMANTICO: '"
                    + nombre + "' no es un arreglo.");
            return;
        }
        if (indice instanceof Integer) {
            int idx = (Integer) indice;
            if (idx < 0 || idx >= s.getTamano()) {
                System.out.println("ERROR SEMANTICO: Indice " + idx
                        + " fuera de rango en arreglo '" + nombre
                        + "' (tamano: " + s.getTamano() + ").");
            }
        }
    }

    // Para variables de control de FOR declaradas implicitamente
    public void declararSiNoExiste(String nombre, String tipo) {
        if (tabla.buscar(nombre) == null) {
            tabla.insertar(nombre, tipo, null, false);
            System.out.println("SEMANTICO: Variable de control '"
                    + nombre + "' declarada implicitamente como " + tipo);
        }
    }

    // Verificar que un identificador fue declarado antes de usarse
    public void verificarUso(String nombre) {
        if (tabla.buscar(nombre) == null) {
            System.out.println("ERROR SEMANTICO: Variable '"
                    + nombre + "' usada sin declarar.");
        }
    }

    // Mostrar tabla al final
    public void mostrarTabla() {
        tabla.mostrar();
    }

    // ── Helpers ──────────────────────────────────────────

    private String inferirTipo(Object valor) {
        if (valor instanceof Integer) return "INTEGER";
        if (valor instanceof Double)  return "DOUBLE";
        if (valor instanceof Boolean) return "BOOLEAN";
        if (valor instanceof String)  return "STRING";
        return "DESCONOCIDO";
    }

    private boolean tiposCompatibles(String tipo, Object valor) {
        if (valor == null) return true;
        switch (tipo) {
            case "INTEGER": return valor instanceof Integer;
            case "DOUBLE":  return valor instanceof Double
                    || valor instanceof Integer;
            case "STRING":  return valor instanceof String;
            case "BOOLEAN": return valor instanceof Boolean;
            case "SINGLE":  return valor instanceof Double
                    || valor instanceof Integer;
            default:        return true;
        }
    }
}