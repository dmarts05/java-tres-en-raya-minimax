package rendimiento;

import java.util.Scanner;

import juego.*;

public class DirectorPruebas {
    public static String ejecutarPruebaIaVsIa(int tipoAlgoritmo, int profundidadMaxIA, Scanner sc) {
        // Creamos tablero e inteligencias artificiales para la prueba
        Tablero tablero = new Tablero();
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA);

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

    public static String ejecutarPruebaIaVsIaAleatorio(int tipoAlgoritmo, int profundidadMaxIA, Scanner sc) {
        return obtenerResultados(0, 0);
    }

    private static String obtenerResultados(int numBusquedasIA1, int numBusquedasIA2) {
        return "Resultados de la prueba:" + "\n\tNúmero de búsquedas de IA 1: " + numBusquedasIA1
                + "\n\tNúmero de búsquedas de IA 2: " + numBusquedasIA2 + "\n\tNúmero de búsquedas totales: "
                + (numBusquedasIA1 + numBusquedasIA2);
    }

    private class MovimientoAleatorio extends Movimiento {
        public MovimientoAleatorio(int fila, int columna, char ficha) {
            super(fila, columna, ficha);
        }
    }
}
