import java.util.LinkedList;

public class Tablero {
    private final int DIMENSION = 3;
    /**
     * Matriz que representa las fichas introducidas en el tablero
     */
    private char[][] fichas;

    public Tablero() {
        // Inicializar tablero
        this.fichas = new char[this.DIMENSION][this.DIMENSION];
        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                fichas[i][j] = ' ';
            }
        }
    }

    public void agregarFicha(int fila, int columna, char jugador) {
        fichas[fila - 1][columna - 1] = jugador;
    }

    public char obtenerResultadoFinDePartida() {
        // Comprobar si jugador 1 gana
        if (fichas[0][0] == 'O' && fichas[0][1] == 'O' && fichas[0][2] == 'O'
                || fichas[1][0] == 'O' && fichas[1][1] == 'O' && fichas[1][2] == 'O'
                || fichas[2][0] == 'O' && fichas[2][1] == 'O' && fichas[2][2] == 'O'
                || fichas[0][0] == 'O' && fichas[1][0] == 'O' && fichas[2][0] == 'O'
                || fichas[0][1] == 'O' && fichas[1][1] == 'O' && fichas[2][1] == 'O'
                || fichas[0][2] == 'O' && fichas[1][2] == 'O' && fichas[2][2] == 'O'
                || fichas[0][0] == 'O' && fichas[1][1] == 'O' && fichas[2][2] == 'O'
                || fichas[2][0] == 'O' && fichas[1][1] == 'O' && fichas[0][2] == 'O') {
            // Jugador 1 gana
            return 'O';
        }
        // Comprobar si jugador 2 gana
        else if (fichas[0][0] == 'X' && fichas[0][1] == 'X' && fichas[0][2] == 'X'
                || fichas[1][0] == 'X' && fichas[1][1] == 'X' && fichas[1][2] == 'X'
                || fichas[2][0] == 'X' && fichas[2][1] == 'X' && fichas[2][2] == 'X'
                || fichas[0][0] == 'X' && fichas[1][0] == 'X' && fichas[2][0] == 'X'
                || fichas[0][1] == 'X' && fichas[1][1] == 'X' && fichas[2][1] == 'X'
                || fichas[0][2] == 'X' && fichas[1][2] == 'X' && fichas[2][2] == 'X'
                || fichas[0][0] == 'X' && fichas[1][1] == 'X' && fichas[2][2] == 'X'
                || fichas[2][0] == 'X' && fichas[1][1] == 'X' && fichas[0][2] == 'X') {
            // Jugador 2 gana
            return 'X';
        } else {
            // Comprobar si es empate
            for (int i = 0; i < fichas.length; i++) {
                for (int j = 0; j < fichas.length; j++) {
                    if (fichas[i][j] == ' ') {
                        // No hay empate ni nadie ha ganado
                        return ' ';
                    }
                }
            }

            // Hay empate
            return '-';
        }
    }

    public boolean esFinDePartida() {
        return obtenerResultadoFinDePartida() != ' ';
    }

    public char obtenerFichaPorCoordenadas(int fila, int columna) {
        return fichas[fila - 1][columna - 1];
    }

    public int obtenerDimension() {
        return this.DIMENSION;
    }

    public LinkedList<int[]> obtenerMovimientosPosibles() {
        LinkedList<int[]> movimientosPosibles = new LinkedList<int[]>();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                if (fichas[i][j] == ' ') {
                    // Guardar movimiento posible
                    int[] movPos = { i + 1, j + 1 };
                    movimientosPosibles.addLast(movPos);
                }
            }
        }

        return movimientosPosibles;
    }

    public boolean estaPosicionOcupada(int fila, int columna) {
        return fichas[fila - 1][columna - 1] != ' ';
    }

    public Tablero obtenerCopiaTablero() {
        Tablero copia = new Tablero();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                copia.agregarFicha(i + 1, j + 1, fichas[i][j]);
            }
        }

        return copia;
    }

    public void imprimirTablero() {
        System.out.println();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                // Imprimir ficha actual
                System.out.print(" " + fichas[i][j] + " ");

                // Imprimir separador
                if (j < 2) {
                    System.out.print("|");
                }
            }

            // Salto de línea entre filas
            System.out.println();
        }

        System.out.println();

    }
}
