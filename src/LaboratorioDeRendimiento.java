import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Laboratorio de pruebas de rendimiento del 3 en raya con IA Minimax.
 * 
 * Las pruebas se basan en el número de búsquedas que realiza cada algorimo en
 * una partida.
 * 
 * Se comparan los algoritmos Minimax básico y Minimax poda alfa-beta.
 * 
 * Modos:
 * - IA vs IA: ofrecerá siempre el mismo resultado porque se realizan los mismos
 * movimientos siempre.
 * - Jugador vs IA: permite forzar al algoritmo a explorar rutas distintas
 * manualmente.
 * - IA vs IA con jugadas aleatorias hasta 3ra ficha aleatorias: permite forzar
 * al algoritmo a explorar rutas distintas automáticamente (no partida
 * completa).
 * 
 * @author gmartm08
 * @author dmarts05
 * @author mlopeb04
 */
public class LaboratorioDeRendimiento {
    /**
     * Imprime el mensaje de bienvenida.
     */
    private static void imprimirMensajeDeBienvenida() {
        System.out.print("   _           _     \n"
                + "  | |         | |    \n"
                + "  | |     __ _| |__  \n"
                + "  | |    / _` | '_ \\ \n"
                + "  | |___| (_| | |_) |\n"
                + "  |______\\__,_|_.__/ \n\n");

        System.out.println(
                "[INICIO] Las pruebas se basan en el número de búsquedas que realiza cada algorimo en una partida.");
        System.out.println("[INICIO] Se comparan los algoritmos Minimax básico y Minimax poda alfa-beta.");
    }

    /**
     * Imprime el mensaje de selección de modo.
     */
    private static void imprimirMensajeDeSeleccionDeModo() {
        System.out.println("[INICIO] Seleccione uno de los siguientes modos de prueba:");
        System.out.println("\t1. IA vs IA.");
        System.out.println("\t2. Jugador vs IA.");
        System.out.println("\t3. IA vs IA con jugadas hasta 3ra ficha aleatorias.");
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
                System.out
                        .println("[ERROR] Valor de modo incorrecto, recuerde que debe encontrarse entre 1 y 4...");
                continue;
            }

            // Valor válido
            return modo;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucle de pruebas hasta que el usuario quiera salir
        boolean terminar = false;
        while (!terminar) {
            imprimirMensajeDeBienvenida();

            int modo = obtenerEntradaModo(sc);

            if (modo == 4) {
                // Salir si es lo que el usuario ha elegido
                terminar = true;
            } else {

            }
        }

        // Cerrar Scanner
        sc.close();
    }

    private class MovimientoAleatorio extends Movimiento {
        public MovimientoAleatorio() {
            super(0, 0, '0');
        }
    }
}
