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
public class Main {
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
        System.out.println("Seleccione uno de los siguientes modos de juego:");
        System.out.println("\t1. Jugador vs Jugador.");
        System.out.println("\t2. Jugador vs IA.");
        System.out.println("\t3. IA vs IA.");
        System.out.println("\t4. Salir.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucle de partidas hasta que el usuario quiera salir
        boolean terminar = false;
        while (!terminar) {
            imprimirMensajeDeBienvenida();

            // Obtener valores requeridos para empezar una partida de 3 en raya
            int modo = 0;
            while (true) {
                // Obtener modo a jugar
                imprimirMensajeDeSeleccionDeModo();
                try {
                    modo = sc.nextInt();
                } catch (InputMismatchException e) {
                    // Se ha introducido un valor que no es un número
                    System.out.println("Valor de modo icorrecto, ¿ha introducido un número entero?");

                    // Resetear buffer y volver a pedir datos
                    sc.nextLine();
                    continue;
                }

                // Comprobar valores inválidos para modo
                if (modo < 1 || modo > 4) {
                    // Valor inválido, volver a pedir datos
                    System.out.println("Valor de modo incorrecto, recuerde que debe encontrarse entre 1 y 4...");
                    continue;
                }

                // Valor válido, se sale del bucle
                break;
            }

            // Salir si es lo que el usuario ha elegido
            if (modo == 4) {
                terminar = true;
            } else {
                // Empezar partida con los parámetros especificados
                Tablero tablero = new Tablero();
                Partida partida = new Partida(tablero, modo, sc);
                partida.empezarPartida();

                // Fin de la partida, ¿quiere el usuario seguir jugando?
                while (true) {
                    System.out.println("Partida terminada, ¿jugar otra vez? (1: seguir jugando; 2: salir):");
                    try {
                        int salir = sc.nextInt();
                        switch (salir) {
                            case 1:
                                terminar = false;
                                break;

                            case 2:
                                terminar = true;
                                break;
                            default:
                                // Valor inválido
                                continue;
                        }

                        break;
                    } catch (InputMismatchException e) {
                        // Se ha introducido un valor que no es un número
                        System.out.println("Valor de salir icorrecto, ¿ha introducido un número entero?");

                        // Resetear buffer y volver a pedir datos
                        sc.nextLine();
                        continue;
                    }
                }

            }
        }

        // Cerrar Scanner
        sc.close();
    }
}