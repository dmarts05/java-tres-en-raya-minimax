import java.util.Scanner;

public class Partida {
    private Scanner sc;
    private Tablero tablero;
    private int modo;

    public Partida(Tablero tablero, int modo, Scanner sc) {
        this.tablero = tablero;
        this.modo = modo;
        this.sc = sc;
    }

    public void empezarPartida() {
        // Imprimir tablero inicial
        tablero.imprimirTablero();

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

    private void empezarIAVsIA() {
    }

    private Movimiento obtenerMovimientoFichaJugador(char jugador) {
        int fila;
        int columna;
        boolean esEntradaValida;

        do {
            esEntradaValida = false;

            // Obtener fila
            System.out.printf("[Jugador %c] Fila en la que colocar la ficha:\n", jugador);
            fila = sc.nextInt();
            // Obtener columna
            System.out.printf("[Jugador %c] Columna en la que colocar la ficha:\n", jugador);
            columna = sc.nextInt();

            // Comprobar valores
            if (esValidoMovimiento(fila - 1, columna - 1)) {
                esEntradaValida = true;
            } else {
                System.out.println("Valores incorrectos, vuelva a intentarlo.");
            }
        } while (!esEntradaValida);

        return new Movimiento(fila - 1, columna - 1, jugador);
    }

    private boolean esValidoMovimiento(int fila, int columna) {
        return fila >= 0 && fila < tablero.obtenerDimension() && columna >= 0 && columna < tablero.obtenerDimension()
                && !tablero.estaPosicionOcupada(fila, columna);
    }

    private void imprimirResultadoPartida(char resultadoFinDePartida) {
        switch (resultadoFinDePartida) {
            case 'O':
                System.out.println("[Juego] Ha ganado el jugador O.");
                break;
            case 'X':
                System.out.println("[Juego] Ha ganado el jugador X.");
                break;
            case '-':
                System.out.println("[Juego] Se produjo un empate.");
                break;
            default:
                break;
        }
    }
}
