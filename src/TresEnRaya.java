import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 3 en raya con Inteligencia Artificial basada en el algoritmo Minimax.
 * 
 * Se ha implementado el algoritmo Minimax con límite de profundidad y una
 * versión más avanzada con poda alfa y beta a mayores, que reduce
 * sustancialmente el número de búsquedas para encontrar el movimiento ideal.
 * 
 * @author gmartm08
 * @author dmarts05
 * @author mlopeb04
 */
public class TresEnRaya {
    /**
     * Imprime el mensaje de bienvenida.
     */
    private static void imprimirMensajeDeBienvenida() {
        System.out.print("  ____                                         \n"
                + "  |___ \\                                        \n"
                + "    __) |    ___ _ __     _ __ __ _ _   _  __ _ \n"
                + "   |__ <    / _ \\ '_ \\   | '__/ _` | | | |/ _` |\n"
                + "   ___) |  |  __/ | | |  | | | (_| | |_| | (_| |\n"
                + "  |____/    \\___|_| |_|  |_|  \\__,_|\\__, |\\__,_|\n"
                + "                                     __/ |      \n"
                + "                                    |___/       \n");

    }

    /**
     * Imprime el mensaje de selección de modo.
     */
    private static void imprimirMensajeDeSeleccionDeModo() {
        System.out.println("[INICIO] Seleccione uno de los siguientes modos de juego:");
        System.out.println("\t1. Jugador vs Jugador.");
        System.out.println("\t2. Jugador vs IA.");
        System.out.println("\t3. IA vs IA.");
        System.out.println("\t4. Salir.");
    }

    /**
     * Obtiene el modo preguntando al usuario con Scanner.
     * 
     * @param sc Scanner para obtener la entrada del usuario.
     * @return modo Modo seleccionado por el usuario.
     */
    private static int obtenerEntradaModo(Scanner sc) {
        int modo = 0;

        while (true) {
            imprimirMensajeDeSeleccionDeModo();

            try {
                modo = sc.nextInt();
            } catch (InputMismatchException e) {
                // Se ha introducido un valor que no es un número
                System.out.println("[ERROR] Valor de modo icorrecto, ¿ha introducido un número entero?");

                // Resetear buffer y volver a pedir datos
                sc.nextLine();
                continue;
            }

            // Comprobar valores inválidos para modo
            if (modo < 1 || modo > 4) {
                // Valor inválido, volver a pedir datos
                System.out.println("[ERROR] Valor de modo incorrecto, recuerde que debe encontrarse entre 1 y 4...");
                continue;
            }

            // Valor válido
            return modo;
        }
    }

    /**
     * Obtiene la profundidad máxima de la IA preguntando al usuario con Scanner.
     * 
     * @param sc Scanner para obtener la entrada del usuario.
     * @return profundidadMax Profundidad máxima de la IA seleccionada por el
     *         usuario.
     */
    private static int obtenerEntradaProfundidadMaxIA(Scanner sc) {
        int profundidadMaxIA = 0;

        while (true) {
            System.out
                    .println("[INICIO] Indique el valor de la profundidad máxima para la IA (valor recomendado -> 6):");
            try {
                profundidadMaxIA = sc.nextInt();
            } catch (InputMismatchException e) {
                // Se ha introducido un valor que no es un número
                System.out.println("[ERROR] Valor de profundidad icorrecto, ¿ha introducido un número entero?");

                // Resetear buffer y volver a pedir datos
                sc.nextLine();
                continue;
            }

            // Comprobar valores inválidos para profundidad
            if (profundidadMaxIA <= 0) {
                // Valor inválido, volver a pedir datos
                System.out.println("[ERROR] Valor de profundidad incorrecto, recuerde que debe ser mayor que 0...");
                continue;
            }

            // Valor válido
            return profundidadMaxIA;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucle de partidas hasta que el usuario quiera salir
        boolean terminar = false;
        while (!terminar) {
            imprimirMensajeDeBienvenida();

            int modo = obtenerEntradaModo(sc);

            if (modo == 4) {
                // Salir si es lo que el usuario ha elegido
                terminar = true;
            } else {
                // Crear tablero de la partida
                Tablero tablero = new Tablero();

                // Crear partida
                Partida partida;
                // Comprobar si la partida involucra IAs para pedir profundiadMaxIA al usuario
                if (modo == 2 || modo == 3) {
                    // IAs involucradas
                    int profundidadMaxIA = obtenerEntradaProfundidadMaxIA(sc);
                    // Crear partida con profundidad indicada
                    partida = new Partida(tablero, modo, profundidadMaxIA, sc);
                } else {
                    // No hay IAs involucradas
                    // Crear partida sin profundidad
                    partida = new Partida(tablero, modo, sc);
                }

                // Empezar partida con los parámetros especificados
                partida.empezarPartida();
            }
        }

        // Cerrar Scanner
        sc.close();
    }
}