package es.carballeira.clases;

import java.util.Scanner;
/**
 * Clase Juego que permite inicializar una partida
 */
public class Juego {
    private Tablero tablero;
    private int intentos;

    public Juego(int tamanyo, int numeroDeBarcos, int intentos) {
        this.tablero = new Tablero(tamanyo, numeroDeBarcos);
        this.intentos = intentos;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido a Hundir la Flota!");
        while (intentos > 0) {
            tablero.mostrarTablero();
            System.out.println("Intentos restantes: " + intentos);
            System.out.print("Ingresa la fila (0-" + (tablero.getTamanyo() - 1) + "): ");
            int fila = scanner.nextInt();
            System.out.print("Ingresa la columna (0-" + (tablero.getTamanyo() - 1) + "): ");
            int columna = scanner.nextInt();

            if (tablero.disparar(fila, columna)) {
                System.out.println("¡Tocado!");
            } else {
                System.out.println("¡Agua!");
            }

            intentos--;

            if (juegoTerminado()) {
                System.out.println("¡Has ganado! Todos los barcos han sido hundidos.");
                break;
            }
        }

        if (intentos == 0) {
            System.out.println("Se han acabado los intentos. ¡Juego terminado!");
        }

        tablero.mostrarTablero();
    }

    private boolean juegoTerminado() {
        for (Barco barco : tablero.getBarcos()) {
            if (!barco.estaHundido()) {
                return false;
            }
        }
        return true;
    }
}