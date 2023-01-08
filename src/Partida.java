import java.util.Scanner;

/**
 * Representa el desarrollo de una partida de 3 en raya.
 */
public class Partida {
    /**
     * Scanner para obtener la entrada de los jugadores humanos.
     */
    private Scanner sc;

    /**
     * Tablero en el que se desarrolla la partida.
     */
    private Tablero tablero;

    /**
     * Modo de juego de la partida.
     * 
     * 1 si JvsJ; 2 si JvsIA; 3 si IAvsIA.
     */
    private int modo;

    /**
     * Genera una nueva partida de 3 en raya.
     * 
     * @param tablero Tablero en el que se desarrollará la partida.
     * @param modo    Modo de juego de la partida.
     * @param sc      Scanner para obtener la entrada de los jugadores humanos.
     */
    public Partida(Tablero tablero, int modo, Scanner sc) {
        this.tablero = tablero;
        this.modo = modo;
        this.sc = sc;
    }

    /**
     * Inicia una partida de 3 en raya según las características de la partida.
     */
    public void empezarPartida() {
        // Imprimir tablero inicial
        tablero.imprimirTablero();

        // Iniciar la partida
        switch (modo) {
            case 1:
                empezarJugadorVsJugador();
                break;
            case 2:
                empezarJugadorVsIA();
                break;
            case 3:
                empezarIAVsIA();
            default:
                break;
        }
    }

    /**
     * Inicia una partida de Jugador vs Jugador.
     */
    private void empezarJugadorVsJugador() {
        Movimiento movimiento;
        // Bucle hasta el fin de la patida
        while (true) {
            // Obtener movimiento del jugador O
            movimiento = obtenerMovimientoFichaJugador('O');
            // Agregar movimiento del jugador O al tablero
            tablero.hacerMovimiento(movimiento);

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }

            // Obtener movimiento del jugador X
            movimiento = obtenerMovimientoFichaJugador('X');
            // Agregar movimiento del jugador X al tablero
            tablero.hacerMovimiento(movimiento);

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }
        }
    }

    /**
     * Inicia una partida de Jugador vs IA.
     */
    private void empezarJugadorVsIA() {
        Movimiento movimiento;

        // Crear IA
        IA ia = new IA(tablero, 'X');

        // Bucle hasta el fin de la patida
        while (true) {
            // Obtener movimiento del jugador O
            movimiento = obtenerMovimientoFichaJugador('O');
            // Agregar movimiento del jugador O al tablero
            tablero.hacerMovimiento(movimiento);

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }

            System.out.println("[IA X] Calculando mejor movimiento...");
            // Obtener mejor movimiento de la IA (X)
            movimiento = ia.obtenerMejorMovimiento();
            // Agregar movimiento de la IA (X) al tablero
            tablero.hacerMovimiento(movimiento);

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }
        }
    }

    /**
     * Inicia una partida de IA vs IA.
     */
    private void empezarIAVsIA() {
        Movimiento movimiento;

        // Crear IA
        IA ia1 = new IA(tablero, 'O');
        IA ia2 = new IA(tablero, 'X');

        // Bucle hasta el fin de la patida
        while (true) {
            System.out.println("[IA O] Calculando mejor movimiento...");
            // Obtener mejor movimiento de la IA (O)
            movimiento = ia1.obtenerMejorMovimiento();
            // Agregar movimiento de la IA (O) al tablero
            tablero.hacerMovimiento(movimiento);
            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }

            System.out.println("[IA X] Calculando mejor movimiento...");
            // Obtener mejor movimiento de la IA (X)
            movimiento = ia2.obtenerMejorMovimiento();
            // Agregar movimiento de la IA (X) al tablero
            tablero.hacerMovimiento(movimiento);

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }
        }
    }

    /**
     * Obtiene un movimiento de un jugador a través de Scanner.
     * 
     * @param ficha Ficha del jugador.
     * @return movimiento Movimiento realizado por el jugador.
     */
    private Movimiento obtenerMovimientoFichaJugador(char ficha) {
        int fila;
        int columna;
        boolean esEntradaValida;

        do {
            esEntradaValida = false;

            // Obtener fila
            System.out.printf("[Jugador %c] Fila en la que colocar la ficha:\n", ficha);
            fila = sc.nextInt();
            // Obtener columna
            System.out.printf("[Jugador %c] Columna en la que colocar la ficha:\n", ficha);
            columna = sc.nextInt();

            // Comprobar valores
            if (esValidoMovimiento(fila - 1, columna - 1)) {
                esEntradaValida = true;
            } else {
                System.out.println("Valores incorrectos, vuelva a intentarlo.");
            }
        } while (!esEntradaValida);

        return new Movimiento(fila - 1, columna - 1, ficha);
    }

    /**
     * Comprueba si un determinado movimiento (fila y columna) es válido según la
     * dimensión del tablero y si otra ficha está ya ocupando ese sitio.
     * 
     * @param fila    Fila del movimiento a comprobar.
     * @param columna Columna del movimiento a comprobar.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    private boolean esValidoMovimiento(int fila, int columna) {
        return fila >= 0 && fila < tablero.obtenerDimension() && columna >= 0 && columna < tablero.obtenerDimension()
                && !tablero.estaPosicionOcupada(fila, columna);
    }

    /**
     * Imprime el resultado final de la partida de 3 en raya.
     * 
     * @param resultadoFinDePartida Caracter que representa el resultado final de la
     *                              partida.
     */
    private void imprimirResultadoPartida(char resultadoFinDePartida) {
        switch (resultadoFinDePartida) {
            case 'O':
                System.out.println("[Partida] Ha ganado el jugador O.");
                break;
            case 'X':
                System.out.println("[Partida] Ha ganado el jugador X.");
                break;
            case '-':
                System.out.println("[Partida] Se produjo un empate.");
                break;
            default:
                break;
        }
    }
}
