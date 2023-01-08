public class Minimax {
    private static final int PROFUNDIDAD_MAX = 6;

    private Minimax() {
    }

    public static int[] obtenerMejorMovimiento(Tablero tablero, char jugador) {
        char jugadorOponente = (jugador == 'O') ? 'X' : 'O';

        int[] mejorMovimiento = { -1, -1 };
        int valorHeuristicoMax = Integer.MIN_VALUE;

        for (int[] movimiento : tablero.obtenerMovimientosPosibles()) {
            int valorHeuristico;

            // Colocar ficha temporalmente
            tablero.agregarFicha(movimiento[0], movimiento[1], jugador);

            valorHeuristico = Math.max(valorHeuristicoMax, minimaxIntento2(tablero,
                    1, false, jugadorOponente));

            // Eliminar ficha temporal
            tablero.agregarFicha(movimiento[0], movimiento[1], ' ');

            if (valorHeuristico > valorHeuristicoMax) {
                mejorMovimiento[0] = movimiento[0];
                mejorMovimiento[1] = movimiento[1];
                valorHeuristicoMax = valorHeuristico;
            }
        }

        return mejorMovimiento;
    }

    private static int minimaxIntento2(Tablero tablero, int profundidad, boolean maximizar, char jugador) {
        char jugadorOponente = (jugador == 'O') ? 'X' : 'O';

        int valorHeuristico = obtenerValorHeuristico(tablero, jugador);

        if (tablero.esFinDePartida() || profundidad == PROFUNDIDAD_MAX) {
            return valorHeuristico;
        }

        if (maximizar) {
            int valorHeuristicoMax = Integer.MIN_VALUE;

            for (int[] movimiento : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha temporalmente
                tablero.agregarFicha(movimiento[0], movimiento[1], jugador);

                valorHeuristicoMax = Math.max(valorHeuristicoMax, minimaxIntento2(tablero,
                        profundidad + 1, false, jugadorOponente));

                // Eliminar ficha temporal
                tablero.agregarFicha(movimiento[0], movimiento[1], ' ');
            }

            return valorHeuristicoMax;
        } else {
            int valorHeuristicoMin = Integer.MAX_VALUE;

            for (int[] movimiento : tablero.obtenerMovimientosPosibles()) {
                // Colocar ficha temporalmente
                tablero.agregarFicha(movimiento[0], movimiento[1], 'X');

                valorHeuristicoMin = Math.min(valorHeuristicoMin, minimaxIntento2(tablero,
                        profundidad + 1, true, jugadorOponente));

                // Eliminar ficha temporal
                tablero.agregarFicha(movimiento[0], movimiento[1], ' ');
            }

            return valorHeuristicoMin;
        }
    }

    public static int minimaxBasico(Tablero tablero, int profundidad, char jugador, boolean turno) {
        if (profundidad == PROFUNDIDAD_MAX || tablero.esFinDePartida()) {
            return obtenerValorHeuristico(tablero, jugador);
        }

        if (turno) {
            return maximizarBasico(tablero, profundidad, jugador, turno);
        } else {
            return minimizarBasico(tablero, profundidad, jugador, turno);
        }

    }

    private static int maximizarBasico(Tablero tablero, int profundidad, char jugador, boolean turno) {
        int mejorResultado = Integer.MIN_VALUE;
        int[] mejorMovimiento = { -1, -1 };

        for (int[] movimiento : tablero.obtenerMovimientosPosibles()) {

            Tablero tableroAux = tablero.obtenerCopiaTablero();
            tableroAux.agregarFicha(movimiento[0], movimiento[1], jugador);

            int resultado = minimaxBasico(tableroAux, ++profundidad, jugador, !turno);

            // Actualizar mejor movimiento y resultado si el movimiento actual es mejor
            if (resultado >= mejorResultado) {
                mejorResultado = resultado;
                mejorMovimiento[0] = movimiento[0];
                mejorMovimiento[1] = movimiento[1];
            }

        }

        tablero.agregarFicha(mejorMovimiento[0], mejorMovimiento[1], jugador);
        return mejorResultado;
    }

    private static int minimizarBasico(Tablero tablero, int profundidad, char jugador, boolean turno) {
        int mejorResultado = Integer.MAX_VALUE;
        int[] mejorMovimiento = { -1, -1 };

        for (int[] movimiento : tablero.obtenerMovimientosPosibles()) {

            Tablero tableroAux = tablero.obtenerCopiaTablero();
            tableroAux.agregarFicha(movimiento[0], movimiento[1], jugador);

            int resultado = minimaxBasico(tableroAux, ++profundidad, jugador, !turno);

            // Actualizar mejor movimiento y resultado si el movimiento actual es mejor
            if (resultado <= mejorResultado) {
                mejorResultado = resultado;
                mejorMovimiento[0] = movimiento[0];
                mejorMovimiento[1] = movimiento[1];
            }

        }

        tablero.agregarFicha(mejorMovimiento[0], mejorMovimiento[1], jugador);
        return mejorResultado;
    }

    private static int obtenerValorHeuristico(Tablero tablero, char jugador) {
        char jugadorOponente = (jugador == 'O') ? 'X' : 'O';

        if (tablero.obtenerResultadoFinDePartida() == jugador) {
            return 10;
        } else if (tablero.obtenerResultadoFinDePartida() == jugadorOponente) {
            return -10;
        } else {
            return 0;
        }
    }
}
