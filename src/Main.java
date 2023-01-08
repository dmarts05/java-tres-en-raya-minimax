import java.util.Scanner;

public class Main {
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

    private static void imprimirMensajeDeSeleccionDeModo() {
        System.out.println("Seleccione uno de los siguientes modos de juego:");
        System.out.println("\t1. Jugador vs Jugador.");
        System.out.println("\t2. Jugador vs IA.");
        System.out.println("\t3. IA vs IA.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        imprimirMensajeDeBienvenida();

        // Obtener valores requeridos para empezar una partida de 3 en raya
        int modo;
        while (true) {
            // Obtener modo a jugar
            imprimirMensajeDeSeleccionDeModo();
            modo = sc.nextInt();

            // Comprobar valores inválidos para modo
            if (modo < 1 || modo > 3) {
                // Valor inválido, volver a pedir datos
                System.out.println("Valor de modo incorrecto, recuerde que debe encontrarse entre 1 y 3...");
                continue;
            }

            // Valor válido, se sale del bucle
            break;
        }

        // Empezar partida con los parámetros especificados
        Tablero tablero = new Tablero();
        Partida partida = new Partida(tablero, modo, sc);
        partida.empezarPartida();

        sc.close();
    }
}