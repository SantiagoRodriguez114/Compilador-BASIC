package semantico;

public class Simbolo {
    private String nombre;
    private String tipo;
    private Object valor;
    private boolean esConstante;
    private boolean esArreglo;
    private int tamano;

    public Simbolo(String nombre, String tipo, Object valor, boolean esConstante) {
        this.nombre      = nombre;
        this.tipo        = tipo;
        this.valor       = valor;
        this.esConstante = esConstante;
        this.esArreglo   = false;
        this.tamano      = 0;
    }

    public Simbolo(String nombre, String tipo, Object valor,
                   boolean esConstante, boolean esArreglo, int tamano) {
        this.nombre      = nombre;
        this.tipo        = tipo;
        this.valor       = valor;
        this.esConstante = esConstante;
        this.esArreglo   = esArreglo;
        this.tamano      = tamano;
    }

    public String  getNombre()    { return nombre; }
    public String  getTipo()      { return tipo; }
    public Object  getValor()     { return valor; }
    public boolean esConstante()  { return esConstante; }
    public boolean esArreglo()    { return esArreglo; }
    public int     getTamano()    { return tamano; }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        String cat = esConstante ? "CONSTANTE" : (esArreglo ? "ARREGLO[" + tamano + "]" : "VARIABLE");
        return nombre + " | " + tipo + " | " + valor + " | " + cat;
    }
}