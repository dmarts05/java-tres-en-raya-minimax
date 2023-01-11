package juego;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Representa el desarrollo de una partida de 3 en raya.
 */
public class Partida {
    /**
     * Scanner para obtener la entrada de los jugadores humanos.
     */
    private final Scanner sc;

    /**
     * Tablero en el que se desarrolla la partida.
     */
    private final Tablero tablero;

    /**
     * Modo de juego de la partida.
     * 
     * 1 si JvsJ; 2 si JvsIA; 3 si IAvsIA.
     */
    private final int modo;

    /**
     * Profundidad máxima de búsqueda en el algoritmo Minimax de la IA.
     * 
     * Por defecto es 6.
     */
    private final int profundidadMaxIA;

    /**
     * Tipo de algoritmo utilizado por la IA (1: básico o 2: poda alfa-beta).
     * 
     * Por defecto usa la poda alfa-beta.
     */
    private final int tipoAlgoritmo;

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
        this.profundidadMaxIA = 6;
        this.tipoAlgoritmo = 1;
        this.sc = sc;
    }

    /**
     * Genera una nueva partida de 3 en raya con más personalización para la IA
     * 
     * @param tablero
     * @param modo
     * @param profundidadMaxIA
     * @param tipoAlgoritmo
     * @param sc
     */
    public Partida(Tablero tablero, int modo, int profundidadMaxIA, int tipoAlgoritmo, Scanner sc) {
        this.tablero = tablero;
        this.modo = modo;
        this.profundidadMaxIA = profundidadMaxIA;
        this.tipoAlgoritmo = tipoAlgoritmo;
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
        IA ia = new IA(tablero, 'X', profundidadMaxIA, tipoAlgoritmo);

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
        IA ia1 = new IA(tablero, 'O', profundidadMaxIA, tipoAlgoritmo);
        IA ia2 = new IA(tablero, 'X', profundidadMaxIA, tipoAlgoritmo);

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
            while (true) {
                System.out.printf("[JUGADOR %c] Fila en la que colocar la ficha:\n", ficha);
                try {
                    fila = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    // Se ha introducido un valor que no es un número
                    System.out.println("[ERROR] Valor de fila icorrecto, ¿ha introducido un número entero?");

                    // Volver a imprimir situación del tablero
                    tablero.imprimirTablero();

                    // Resetear buffer y volver a pedir datos
                    sc.nextLine();
                    continue;
                }
            }

            // Obtener columna
            while (true) {
                System.out.printf("[JUGADOR %c] Columna en la que colocar la ficha:\n", ficha);
                try {
                    columna = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    // Se ha introducido un valor que no es un número
                    System.out.println("[ERROR] Valor de fila icorrecto, ¿ha introducido un número entero?");

                    // Volver a imprimir situación del tablero
                    tablero.imprimirTablero();

                    // Resetear buffer y volver a pedir datos
                    sc.nextLine();
                    continue;
                }
            }

            // Comprobar valores
            if (esValidoMovimiento(fila - 1, columna - 1)) {
                esEntradaValida = true;
            } else {
                System.out.println("[ERROR] Valores incorrectos o movimiento no posible, vuelva a intentarlo.");
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
                System.out.println("[PARTIDA] Ha ganado el jugador O.");
                break;
            case 'X':
                System.out.println("[PARTIDA] Ha ganado el jugador X.");
                break;
            case '-':
                System.out.println("[PARTIDA] Se produjo un empate.");
                break;
            default:
                break;
        }
    }
}
