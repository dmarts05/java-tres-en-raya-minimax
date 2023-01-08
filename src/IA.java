/**
 * Representa una Inteligencia Artificial imbatible en el 3 en raya gracias al
 * algoritmo Minimax.
 */
public class IA {
    /**
     * Profundidad máxima de búsqueda en el algoritmo Minimax.
     */
    private static final int PROFUNDIDAD_MAX = 6;
    private int numBusquedas = 0;

    /**
     * Tablero donde se desarrolla la partida de 3 en raya.
     */
    private Tablero tablero;

    /**
     * Ficha utilizada por la IA.
     */
    private char ficha;

    /**
     * Ficha utilizada por el oponente.
     */
    private char fichaOponente;

    /**
     * Genera una IA que jugará en el tablero especificado con la ficha indicada.
     * 
     * @param tablero Tablero en el que jugará la IA.
     * @param ficha   Ficha que utilizará la IA.
     */
    public IA(Tablero tablero, char ficha) {
        this.tablero = tablero;
        this.ficha = ficha;
        this.fichaOponente = this.ficha == 'O' ? 'X' : 'O';
    }

    /**
     * Obtiene el mejor movimiento posible en un instante de la partida según el
     * estado del tablero.
     * 
     * Emplea el algoritmo Minimax para determinar el valor heurístico de cada
     * movimiento y así decantarse por aquel que beneficie más a la IA.
     * 
     * @return
     */
    public Movimiento obtenerMejorMovimiento() {
        int mejorValorHeuristico = Integer.MIN_VALUE;
        Movimiento mejorMovimiento = null;

        // Iterar entre los movimientos posibles del tablero
        for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
            // Colocar ficha de IA en tablero temporalmente
            tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.ficha));

            // Obtener valor heurístico del movimiento realizado
            // int valorHeuristico = minimax(mejorValorHeuristico, false);
            int valorHeuristico = minimaxAlfaBeta(1, false, Integer.MIN_VALUE,
                    Integer.MAX_VALUE);

            // Quitar ficha temporal
            tablero.hacerMovimiento(mov);

            // Consultar si hemos encontrado un movimiento mejor
            if (valorHeuristico > mejorValorHeuristico) {
                // Movimiento mejor, actualizar valores
                mejorValorHeuristico = valorHeuristico;
                mejorMovimiento = new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.ficha);
            }
        }

        return mejorMovimiento;
    }

    /**
     * Calcula el mejor valor heurístico posible según la situación del tablero y si
     * se encuentra maximizando o minimizando.
     * 
     * El algoritmo acaba cuando se alcanza la profundidad máxima o la partida
     * termina.
     * 
     * @param profundidad Profundidad actual en la que se está buscando.
     * @param maximizar   Indica si se está maximizando o minimizando.
     * @return mejorValorHeuristico Valor heurístico del mejor movimiento según el
     *         estado del tablero.
     */
    private int minimax(int profundidad, boolean maximizar) {
        // Comprobar si hemos llegado al tope de profundidad o la partida se ha
        // terminado
        numBusquedas++;
        if (profundidad == PROFUNDIDAD_MAX || tablero.esFinDePartida()) {
            return obtenerValorHeuristico();
        }

        // Comprobar si se desea maximizar o minimizar
        int mejorValorHeuristico = 0;
        if (maximizar) {
            // Maximizar
            mejorValorHeuristico = Integer.MIN_VALUE;

            // Iterar entre los movimientos posibles del tablero
            for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha de IA en tablero temporalmente
                tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.ficha));

                // Obtener valor heurístico del movimiento realizado y compararlo con el mejor
                mejorValorHeuristico = Math.max(mejorValorHeuristico, minimax(profundidad + 1, !maximizar));

                // Quitar ficha temporal
                tablero.hacerMovimiento(mov);
            }
        } else {
            // Minimizar
            mejorValorHeuristico = Integer.MAX_VALUE;

            // Iterar entre los movimientos posibles del tablero
            for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha de oponente en tablero temporalmente
                tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.fichaOponente));

                // Obtener valor heurístico del movimiento realizado y compararlo con el mejor
                mejorValorHeuristico = Math.min(mejorValorHeuristico, minimax(profundidad + 1, !maximizar));

                // Quitar ficha temporal
                tablero.hacerMovimiento(mov);
            }
        }

        return mejorValorHeuristico;
    }

    /**
     * Calcula el mejor valor heurístico posible según la situación del tablero y si
     * se encuentra maximizando o minimizando.
     * 
     * El algoritmo acaba cuando se alcanza la profundidad máxima o la partida
     * termina.
     * 
     * @param profundidad Profundidad actual en la que se está buscando.
     * @param maximizar   Indica si se está maximizando o minimizando.
     * @param alfa        Valor alfa de la poda
     * @param beta        Valor beta de la poda
     * @return mejorValorHeuristico Valor heurístico del mejor movimiento según el
     *         estado del tablero.
     */
    private int minimaxAlfaBeta(int profundidad, boolean maximizar, int alfa, int beta) {
        // Comprobar si hemos llegado al tope de profundidad o la partida se ha
        // terminado
        numBusquedas++;
        if (profundidad == PROFUNDIDAD_MAX || tablero.esFinDePartida()) {
            return obtenerValorHeuristico();
        }

        // Comprobar si se desea maximizar o minimizar
        int mejorValorHeuristico = 0;
        if (maximizar) {
            // Maximizar
            mejorValorHeuristico = Integer.MIN_VALUE;

            // Iterar entre los movimientos posibles del tablero
            for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha de IA en tablero temporalmente
                tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.ficha));

                // Obtener valor heurístico del movimiento realizado y compararlo con el mejor
                mejorValorHeuristico = Math.max(mejorValorHeuristico,
                        minimaxAlfaBeta(profundidad + 1, !maximizar, alfa, beta));
                alfa = Math.max(alfa, mejorValorHeuristico);

                // Quitar ficha temporal
                tablero.hacerMovimiento(mov);

                if (beta <= alfa) {
                    break;
                }

            }
        } else {
            // Minimizar
            mejorValorHeuristico = Integer.MAX_VALUE;

            // Iterar entre los movimientos posibles del tablero
            for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha de oponente en tablero temporalmente
                tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.fichaOponente));

                // Obtener valor heurístico del movimiento realizado y compararlo con el mejor
                mejorValorHeuristico = Math.min(mejorValorHeuristico,
                        minimaxAlfaBeta(profundidad + 1, !maximizar, alfa, beta));
                beta = Math.min(beta, mejorValorHeuristico);
                // Quitar ficha temporal
                tablero.hacerMovimiento(mov);

                if (beta <= alfa) {
                    break;
                }

            }
        }

        return mejorValorHeuristico;
    }

    /**
     * Obtiene el valor heurístico según el estado del tablero.
     * 
     * @return 10 si la IA gana; -10 si gana el oponente; 0 si hay empate o la
     *         partida no ha terminado.
     */
    private int obtenerValorHeuristico() {
        char resultadoPartida = tablero.obtenerResultadoFinDePartida();

        if (resultadoPartida == this.ficha) {
            // IA gana
            return 10;
        } else if (resultadoPartida == this.fichaOponente) {
            // Oponente gana
            return -10;
        } else {
            // La partida no ha terminado o es empate
            return 0;
        }
    }

    /**
     * 
     * @param tipo 1 si es limitado por profundidad; 2 si es poda alfa-beta
     * @return
     */
    public int obtenerNumeroBusquedas(int tipo) {
        numBusquedas = 0;
        if (tipo == 1) {
            minimax(1, false);
        } else if (tipo == 2) {
            minimaxAlfaBeta(1, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        return numBusquedas;

    }
}
