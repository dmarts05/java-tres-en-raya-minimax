package laboratorio;

import java.util.LinkedList;
import java.util.Random;

import juego.*;

/**
 * Clase encargada de llevar a cabo las distintas pruebas disponibles en el
 * laboratorio.
 * 
 * Las pruebas disponibles se encuentran descritas en:
 * 
 * @see laboratorio.LaboratorioDeRendimiento
 */
public class DirectorPruebas {
    /**
     * Tablero de 3 en raya empleado en las pruebas.
     */
    private static Tablero tablero;

    private DirectorPruebas() {
    }

    /**
     * Ejecuta y devuelve los resultados de una prueba de una partida completa de
     * una IA contra otra IA.
     * 
     * @param tipoAlgoritmo    Tipo del algoritmo que será usado en la prueba
     *                         (básico o poda alfa-beta).
     * @param profundidadMaxIA Profundidad máxima de búsqueda de las IAs.
     * 
     * @return resultado Resultado de la prueba listo para imprimir con detalles
     *         sobre el número de búsquedas de las IAs.
     */
    public static String ejecutarPruebaIaVsIa(int tipoAlgoritmo, int profundidadMaxIA) {
        // Creamos un tablero y unas inteligencias artificiales para la prueba
        tablero = new Tablero();
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA);

        // Realizar prueba
        return ejecutarPrueba(tipoAlgoritmo, ia1, ia2);

    }

    /**
     * Ejecuta y devuelve los resultados de una prueba de una partida de
     * una IA contra otra IA que inicia con las primera ficha colocada de forma
     * aleatoria para obligar al algoritmo Minimax a explorar rutas distintas.
     * 
     * @param tipoAlgoritmo    Tipo del algoritmo que será usado en la prueba
     *                         (básico o poda alfa-beta).
     * @param profundidadMaxIA Profundidad máxima de búsqueda de las IAs.
     * 
     * @return resultado Resultado de la prueba listo para imprimir con detalles
     *         sobre el número de búsquedas de las IAs.
     */
    public static String ejecutarPruebaIaVsIaJugadaAleatoria(int tipoAlgoritmo, int profundidadMaxIA) {

        // Creamos un tablero y unas inteligencias artificiales para la prueba
        tablero = new Tablero();
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA);

        // Forzamos que el primer movimiento sea aleatorio
        tablero.hacerMovimiento(obtenerMovimientoAleatorio('O'));

        // Hacemos que el siguiente movimiento ya sea el mejor posible
        tablero.hacerMovimiento(ia2.obtenerMejorMovimiento());

        // Realizar prueba
        return ejecutarPrueba(tipoAlgoritmo, ia1, ia2);
    }

    /**
     * Simula una partida de 3 en raya hasta su final para obtener el número de
     * búsquedas de cada IA.
     * 
     * @param tipoAlgoritmo Tipo del algoritmo que será usado en la prueba
     *                      (básico o poda alfa-beta).
     * @param ia1           IA que representa al jugador 1.
     * @param ia2           IA que representa al jugador 2.
     * 
     * @return resultado Resultado de la prueba listo para imprimir con detalles
     *         sobre el número de búsquedas de las IAs.
     */
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

        // Obtener resultados de la ejecución
        String resultado = obtenerResultados(numBusquedasIA1, numBusquedasIA2);

        // Comprobar si se llegó a un empate (resultado deseado para el algoritmo
        // Minimax)
        if (tablero.obtenerResultadoFinDePartida() != '-') {
            // No se llegó a un empate, indicarlo antes de los resultados
            resultado = "[RESULTADOS] Las IAs no lograron empatar. El límite de profundidad es demasiado restrictivo...\n"
                    + resultado;

        }

        return resultado;
    }

    /**
     * Obtiene un movimiento aleatorio con la ficha indicada en el tablero.
     * 
     * @param ficha Ficha con la que realizar el movimiento.
     * 
     * @return movimiento Movimiento posible aleatorio en el tablero con la ficha
     *         indicada.
     */
    private static Movimiento obtenerMovimientoAleatorio(char ficha) {
        // Obtener movimientos posibles en el tablero
        LinkedList<Movimiento> movimientosPosibles = tablero.obtenerMovimientosPosibles();

        // Seleccionar uno de estos movimientos aleatoriamente y devolverlo con ficha
        Random rd = new Random();
        Movimiento mov = movimientosPosibles.get(rd.nextInt(movimientosPosibles.size()));
        return new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), ficha);
    }

    /**
     * Dados los números de las búsquedas de las IAs, retorna un cadena que describe
     * los resultados de la prueba.
     * 
     * @param numBusquedasIA1 Búsquedas totales del algoritmo para la IA que
     *                        representa el jugador 1.
     * @param numBusquedasIA2 Búsquedas totales del algoritmo para la IA que
     *                        representa el jugador 2.
     * @return resultado Resultado de la prueba listo para imprimir con detalles
     *         sobre el número de búsquedas de las IAs.
     */
    private static String obtenerResultados(int numBusquedasIA1, int numBusquedasIA2) {
        return "[RESULTADOS] Resultados de la prueba:" + "\n\t* Número de búsquedas de IA 1: " + numBusquedasIA1 + "."
                + "\n\t* Número de búsquedas de IA 2: " + numBusquedasIA2 + "." + "\n\t* Número de búsquedas totales: "
                + (numBusquedasIA1 + numBusquedasIA2) + ".";
    }
}
