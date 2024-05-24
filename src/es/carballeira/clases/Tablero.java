package es.carballeira.clases;

import java.util.Random;

public class Tablero {
    private char[][] tablero;
    private Barco[] barcos;
    private int tamanyo;
    private Random rand;
/**
 * Constructor con argumento tamaño y numero de Barcos.
 */
    public Tablero(int tamanyo, int numeroDeBarcos) {
        this.tamanyo = tamanyo;
        this.tablero = new char[tamanyo][tamanyo];
        this.barcos = new Barco[numeroDeBarcos];
        this.rand = new Random();
        inicializarTablero();
        colocarBarcos();
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;
    }

    private void inicializarTablero() {
        for (int i = 0; i < tamanyo; i++) {
            for (int j = 0; j < tamanyo; j++) {
                tablero[i][j] = '~';  // Agua
            }
        }
    }

    private void colocarBarcos() {
        for (int i = 0; i < barcos.length; i++) {
            barcos[i] = new Barco(3); // Todos los barcos tienen tamaño 3 para simplificar
            boolean colocado = false;
            while (!colocado) {
                int fila = rand.nextInt(tamanyo);
                int columna = rand.nextInt(tamanyo);
                boolean horizontal = rand.nextBoolean();

                if (sePuedeColocarBarco(fila, columna, barcos[i].getTamaño(), horizontal)) {
                    colocarBarco(fila, columna, barcos[i], horizontal);
                    colocado = true;
                }
            }
        }
    }

    private boolean sePuedeColocarBarco(int fila, int columna, int tamanyo, boolean horizontal) {
        if (horizontal) {
            if (columna + tamanyo > this.tamanyo) return false;
            for (int i = 0; i < tamanyo; i++) {
                if (tablero[fila][columna + i] != '~') return false;
            }
        } else {
            if (fila + tamanyo > this.tamanyo) return false;
            for (int i = 0; i < tamanyo; i++) {
                if (tablero[fila + i][columna] != '~') return false;
            }
        }
        return true;
    }

    private void colocarBarco(int fila, int columna, Barco barco, boolean horizontal) {
        for (int i = 0; i < barco.getTamaño(); i++) {
            if (horizontal) {
                tablero[fila][columna + i] = 'B';
                barco.setPosicion(i, fila, columna + i);
            } else {
                tablero[fila + i][columna] = 'B';
                barco.setPosicion(i, fila + i, columna);
            }
        }
    }

    public boolean disparar(int fila, int columna) {
        for (Barco barco : barcos) {
            if (barco.verificarImpacto(fila, columna)) {
                tablero[fila][columna] = 'X';
                return true;
            }
        }
        tablero[fila][columna] = 'O'; // Agua
        return false;
    }

    public void mostrarTablero() {
        for (int i = 0; i < tamanyo; i++) {
            for (int j = 0; j < tamanyo; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
}