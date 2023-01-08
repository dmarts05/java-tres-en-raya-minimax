public class IA {
    private static final int PROFUNDIDAD_MAX = 6;
    private Tablero tablero;
    private char ficha;
    private char fichaOponente;

    public IA(Tablero tablero, char ficha) {
        this.tablero = tablero;
        this.ficha = ficha;
        this.fichaOponente = this.ficha == 'O' ? 'X' : 'O';
    }

    public Movimiento obtenerMejorMovimiento() {
        int mejorValorHeuristico = Integer.MIN_VALUE;
        Movimiento mejorMovimiento = null;

        // Iterar entre los movimientos posibles del tablero
        for (Movimiento mov : tablero.obtenerMovimientosPosibles()) {
            // Colocar ficha de IA en tablero temporalmente
            tablero.hacerMovimiento(new Movimiento(mov.obtenerFila(), mov.obtenerColumna(), this.ficha));

            // Obtener valor heurístico del movimiento realizado
            int valorHeuristico = minimax(1, false);

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

    private int minimax(int profundidad, boolean maximizar) {
        // Comprobar si hemos llegado al tope de profundidad o la partida se ha
        // terminado
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

    private int obtenerValorHeuristico() {
        char resultadoPartida = tablero.obtenerResultadoFinDePartida();

        if (resultadoPartida == this.ficha) {
            // IA gana
            return 10;
        } else if (resultadoPartida == this.fichaOponente) {
            // Jugador opuesto gana
            return -10;
        } else {
            // La partida no ha terminado o es empate
            return 0;
        }
    }
}
