package juego;

import java.util.LinkedList;

/**
 * Representa el tablero de una partida de 3 en raya.
 */
public class Tablero {
    /**
     * Dimensión del tablero (en este caso, 3x3);
     */
    private final int DIMENSION = 3;

    /**
     * Matriz que representa las fichas introducidas en el tablero.
     */
    private char[][] fichas;

    /**
     * Genera un tablero de 3 en raya.
     */
    public Tablero() {
        // Inicializar tablero
        this.fichas = new char[this.DIMENSION][this.DIMENSION];
        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                fichas[i][j] = ' ';
            }
        }
    }

    /**
     * Lleva a cabo el movimiento especificado en el tablero.
     * 
     * @param movimiento Movimiento a realizar.
     */
    public void hacerMovimiento(Movimiento movimiento) {
        fichas[movimiento.obtenerFila()][movimiento.obtenerColumna()] = movimiento.obtenerFicha();
    }

    /**
     * Calcula según el estado del tablero quién ha ganado la partida, si hay empate
     * o aún no se ha terminado.
     * 
     * @return 'O' si gana O; 'X' si gana X; '-' si empate; ' ' si partida sin
     *         terminar.
     */

    public char obtenerResultadoFinDePartida() {
        // Comprobar si jugador O gana
        if (fichas[0][0] == 'O' && fichas[0][1] == 'O' && fichas[0][2] == 'O'
                || fichas[1][0] == 'O' && fichas[1][1] == 'O' && fichas[1][2] == 'O'
                || fichas[2][0] == 'O' && fichas[2][1] == 'O' && fichas[2][2] == 'O'
                || fichas[0][0] == 'O' && fichas[1][0] == 'O' && fichas[2][0] == 'O'
                || fichas[0][1] == 'O' && fichas[1][1] == 'O' && fichas[2][1] == 'O'
                || fichas[0][2] == 'O' && fichas[1][2] == 'O' && fichas[2][2] == 'O'
                || fichas[0][0] == 'O' && fichas[1][1] == 'O' && fichas[2][2] == 'O'
                || fichas[2][0] == 'O' && fichas[1][1] == 'O' && fichas[0][2] == 'O') {
            // Jugador O gana
            return 'O';
        }
        // Comprobar si jugador X gana
        else if (fichas[0][0] == 'X' && fichas[0][1] == 'X' && fichas[0][2] == 'X'
                || fichas[1][0] == 'X' && fichas[1][1] == 'X' && fichas[1][2] == 'X'
                || fichas[2][0] == 'X' && fichas[2][1] == 'X' && fichas[2][2] == 'X'
                || fichas[0][0] == 'X' && fichas[1][0] == 'X' && fichas[2][0] == 'X'
                || fichas[0][1] == 'X' && fichas[1][1] == 'X' && fichas[2][1] == 'X'
                || fichas[0][2] == 'X' && fichas[1][2] == 'X' && fichas[2][2] == 'X'
                || fichas[0][0] == 'X' && fichas[1][1] == 'X' && fichas[2][2] == 'X'
                || fichas[2][0] == 'X' && fichas[1][1] == 'X' && fichas[0][2] == 'X') {
            // Jugador X gana
            return 'X';
        } else {
            // Comprobar si es empate
            for (int i = 0; i < fichas.length; i++) {
                for (int j = 0; j < fichas.length; j++) {
                    if (fichas[i][j] == ' ') {
                        // No hay empate y nadie ha ganado
                        return ' ';
                    }
                }
            }

            // Hay empate
            return '-';
        }
    }

    /**
     * Comprueba si se ha llegado al final de la partida.
     * 
     * @return true si se ha llegado al final de la partida, false en el caso
     *         contrario.
     */
    public boolean esFinDePartida() {
        return obtenerResultadoFinDePartida() != ' ';
    }

    /**
     * Obtiene la dimensión del tablero.
     * 
     * @return Dimensión del tablero.
     */
    public int obtenerDimension() {
        return this.DIMENSION;
    }

    /**
     * Obtiene todos los movimientos que se pueden hacer en el tablero en un
     * determinado instante.
     * 
     * @return Lista con todos los movimientos posibles.
     */
    public LinkedList<Movimiento> obtenerMovimientosPosibles() {
        LinkedList<Movimiento> movimientosPosibles = new LinkedList<Movimiento>();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                if (fichas[i][j] == ' ') {
                    // Guardar movimiento posible
                    movimientosPosibles.addLast(new Movimiento(i, j, ' '));
                }
            }
        }

        return movimientosPosibles;
    }

    /**
     * Determina si una posición en el tablero está ya ocupada por otra ficha.
     * 
     * @param fila    Fila de la posición a comprobar.
     * @param columna Columna de la posición a comprobar.
     * @return true si la posición está ocupada, false en caso contrario
     */
    public boolean estaPosicionOcupada(int fila, int columna) {
        return fichas[fila][columna] != ' ';
    }

    /**
     * Imprime los contenidos del tablero.
     * 
     * p. ej.:
     * O | O | X
     * X | X | O
     * O | X | O
     */
    public void imprimirTablero() {
        System.out.println();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                // Imprimir ficha actual
                System.out.print(" " + fichas[i][j] + " ");

                // Imprimir separador
                if (j < fichas.length - 1) {
                    System.out.print("|");
                }
            }

            // Salto de línea entre filas
            System.out.println();
        }

        System.out.println();

    }
}
