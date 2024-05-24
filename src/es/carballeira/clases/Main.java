package es.carballeira.clases;
/**
 * Clase principal que inicializa el juego.
 */
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego(10, 5, 20); // Tablero de 10x10, 5 barcos, 20 intentos
        juego.iniciar();
    }
}