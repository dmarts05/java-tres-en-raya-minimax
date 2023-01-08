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
        int[] movimiento;
        // Bucle hasta el fin de la patida
        while (true) {
            // Obtener valores de fila y columna del primer jugador
            movimiento = obtenerMovimientoFichaJugador(1);
            // Agregar movimiento del jugador 1 al tablero
            tablero.agregarFicha(movimiento[0], movimiento[1], 'O');

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }

            // Obtener valores de fila y columna del segundo jugador
            movimiento = obtenerMovimientoFichaJugador(2);
            // Agregar movimiento del jugador 1 al tablero
            tablero.agregarFicha(movimiento[0], movimiento[1], 'X');

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
        int[] movimiento;
        // Bucle hasta el fin de la patida
        while (true) {
            // Obtener valores de fila y columna del primer jugador
            movimiento = obtenerMovimientoFichaJugador(1);
            // Agregar movimiento del jugador 1 al tablero
            tablero.agregarFicha(movimiento[0], movimiento[1], 'O');

            // Imprimir nueva situación del tablero
            tablero.imprimirTablero();

            // Salir si es fin de partida
            if (tablero.esFinDePartida()) {
                imprimirResultadoPartida(tablero.obtenerResultadoFinDePartida());
                break;
            }

            // Hacer movimiento de la IA
            movimiento = Minimax.obtenerMejorMovimiento(tablero, 'X');
            tablero.agregarFicha(movimiento[0], movimiento[1], 'X');
            // Minimax.minimaxBasico(tablero, modo, 'X', true);

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

    private int[] obtenerMovimientoFichaJugador(int jugador) {
        // [0] - fila; [1] - columna
        int[] coordenadas = { -1, -1 };
        boolean esEntradaValida;

        do {
            esEntradaValida = false;

            // Obtener fila
            System.out.printf("[Jugador %d] Fila en la que colocar la ficha:\n", jugador);
            coordenadas[0] = sc.nextInt();
            // Obtener columna
            System.out.printf("[Jugador %d] Columna en la que colocar la ficha:\n", jugador);
            coordenadas[1] = sc.nextInt();

            // Comprobar valores
            if (esValidoMovimiento(coordenadas[0], coordenadas[1])) {
                esEntradaValida = true;

            } else {
                System.out.println("Valores incorrectos, vuelva a intentarlo.");
            }
        } while (!esEntradaValida);

        return coordenadas;
    }

    private boolean esValidoMovimiento(int fila, int columna) {
        return fila >= 1 && fila <= tablero.obtenerDimension() && columna >= 1 && columna <= tablero.obtenerDimension()
                && !tablero.estaPosicionOcupada(fila, columna);
    }

    private void imprimirResultadoPartida(char resultadoFinDePartida) {
        switch (resultadoFinDePartida) {
            case 'O':
                System.out.println("[Juego] Ha ganado el jugador 1.");
                break;
            case 'X':
                System.out.println("[Juego] Ha ganado el jugador 2.");
                break;
            case '-':
                System.out.println("[Juego] Se produjo un empate.");
                break;
            default:
                break;
        }
    }
}
