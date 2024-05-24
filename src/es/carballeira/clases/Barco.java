package es.carballeira.clases;

public class Barco {
    private int tamaño;
    private int[] fila;
    private int[] columna;
    private int impactos;

    public Barco(int tamaño) {
        this.tamaño = tamaño;
        this.fila = new int[tamaño];
        this.columna = new int[tamaño];
        this.impactos = 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setPosicion(int index, int fila, int columna) {
        this.fila[index] = fila;
        this.columna[index] = columna;
    }

    public boolean estaHundido() {
        return impactos == tamaño;
    }

    public boolean verificarImpacto(int fila, int columna) {
        for (int i = 0; i < tamaño; i++) {
            if (this.fila[i] == fila && this.columna[i] == columna) {
                impactos++;
                return true;
            }
        }
        return false;
    }
}