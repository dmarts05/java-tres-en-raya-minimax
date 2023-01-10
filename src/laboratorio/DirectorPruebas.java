package laboratorio;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import juego.*;

public class DirectorPruebas {
    private static Tablero tablero;

    public static String ejecutarPruebaIaVsIa(int tipoAlgoritmo, int profundidadMaxIA, Scanner sc) {
        // Creamos un tablero y unas inteligencias artificiales para la prueba
        tablero = new Tablero();
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA);

        // Realizar prueba
        return ejecutarPrueba(tipoAlgoritmo, ia1, ia2);

    }

    public static String ejecutarPruebaIaVsIaAleatorio(int tipoAlgoritmo, int profundidadMaxIA, Scanner sc) {

        // Creamos un tablero y unas inteligencias artificiales para la prueba
        tablero = new Tablero();
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA);

        // Forzamos que los primeros 4 movimientos sean aleatorios
        for (int i = 0; i < 2; i++) {
            tablero.hacerMovimiento(obtenerMovimientoAleatorio('O'));
            tablero.hacerMovimiento(obtenerMovimientoAleatorio('X'));
        }

        // Realizar prueba
        return ejecutarPrueba(tipoAlgoritmo, ia1, ia2);
    }

    private static String ejecutarPrueba(int tipoAlgoritmo, IA ia1, IA ia2) {
        // Iniciamos la prueba
        int numBusquedasIA1 = 0;
        int numBusquedasIA2 = 0;
        while (true) {
            // Obtener número de búsquedas con el algoritmo elegido para IA 1
            numBusquedasIA1 += ia1.obtenerNumeroBusquedas(tipoAlgoritmo);
            // Efectuar el movimiento de la IA 1
            tablero.hacerMovimiento(ia1.obtenerMejorMovimiento());

            // Comprobar si se ha llegado al fin de la partida
            if (tablero.esFinDePartida()) {
                break;
            }

            // Obtener número de búsquedas con el algoritmo elegido para IA 2
            numBusquedasIA2 += ia2.obtenerNumeroBusquedas(tipoAlgoritmo);
            // Efectuar el movimiento de la IA 2
            tablero.hacerMovimiento(ia2.obtenerMejorMovimiento());

            // Comprobar si se ha llegado al fin de la partida
            if (tablero.esFinDePartida()) {
                break;
            }
        }

        return obtenerResultados(numBusquedasIA1, numBusquedasIA2);
    }

    private static Movimiento obtenerMovimientoAleatorio(char ficha) {
        // Obtener movimientos posibles en el tablero
        LinkedList<Movimiento> movimientosPosibles = tablero.obtenerMovimientosPosibles();

        // Seleccionar uno de estos movimientos aleatoriamente y devolverlo con ficha
        Random rd = new Random();
        Movimiento mov = movimientosPosibles.get(rd.nextInt(movimientosPosibles.size()));
        return new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), ficha);
    }

    private static String obtenerResultados(int numBusquedasIA1, int numBusquedasIA2) {
        return "[RESULTADOS] Resultados de la prueba:" + "\n\t* Número de búsquedas de IA 1: " + numBusquedasIA1 + "."
                + "\n\t* Número de búsquedas de IA 2: " + numBusquedasIA2 + "." + "\n\t* Número de búsquedas totales: "
                + (numBusquedasIA1 + numBusquedasIA2) + ".";
    }
}
