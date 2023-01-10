package rendimiento;

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
 * - IA vs IA con jugadas aleatorias hasta 3ra ficha aleatorias: permite forzar
 * al algoritmo a explorar rutas distintas automáticamente (no partida
 * completa).
 * 
 * @author gmartm08
 * @author dmarts05
 * @author mlopeb04
 */
public class LaboratorioDeRendimiento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucle de pruebas hasta que el usuario quiera salir
        boolean terminar = false;
        while (!terminar) {
            imprimirMensajeDeBienvenida();

            int modo = obtenerEntradaModo(sc);

            if (modo == 3) {
                // Salir si es lo que el usuario ha elegido
                terminar = true;
            } else {
                // Obtener datos necesarios para iniciar la prueba
                int tipoAlgoritmo = obtenerEntradaTipoAlgoritmo(sc);
                int profundidadMaxIA = obtenerEntradaProfundidad(sc);

                String resultado = "";

                // Ejecutar prueba según el modo seleccionado
                switch (modo) {
                    case 1:
                        resultado = DirectorPruebas.ejecutarPruebaIaVsIa(tipoAlgoritmo, profundidadMaxIA, sc);
                        break;
                    case 2:
                        resultado = DirectorPruebas.ejecutarPruebaIaVsIaAleatorio(tipoAlgoritmo, profundidadMaxIA, sc);
                        break;
                }

                // Mostrar los resultados de la prueba
                System.out.println(resultado);
            }
        }

        // Cerrar Scanner
        sc.close();
    }

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
        System.out.println("[INICIO] Se comparan los algoritmos Minimax básico y Minimax con poda alfa-beta.");
    }

    /**
     * Imprime el mensaje de selección de modo.
     */
    private static void imprimirMensajeDeSeleccionDeModo() {
        System.out.println("[INICIO] Seleccione uno de los siguientes modos de prueba:");
        System.out.println("\t1. IA vs IA.");
        System.out.println("\t2. IA vs IA con jugadas hasta 3ra ficha aleatorias.");
        System.out.println("\t3. Salir.");
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
                System.out.println("[ERROR] Valor de modo incorrecto, ¿ha introducido un número entero?");

                // Resetear buffer y volver a pedir datos
                sc.nextLine();
                continue;
            }

            // Comprobar valores inválidos para modo
            if (modo < 1 || modo > 3) {
                // Valor inválido, volver a pedir datos
                System.out
                        .println("[ERROR] Valor de modo incorrecto, recuerde que debe encontrarse entre 1 y 3...");
                continue;
            }

            // Valor válido
            return modo;
        }
    }

    /**
     * Imprime el mensaje de selección de profundidad máxima de búsqueda de la IA.
     */
    private static void imprimirMensajeDeSeleccionDeProfundidad() {
        System.out.println("[INICIO] Nota: La profundidad recomendada es 6.");
        System.out.println("[INICIO] Indique la profundidad máxima para el algoritmo Minimax de la IA:");
    }

    /**
     * Obtiene la profundidad máxima de búsqueda de la IA preguntando al usuario con
     * Scanner.
     * 
     * @param sc Scanner para obtener la entrada del usuario.
     * @return profundidadMaxIA Profundidad máxima de búsqueda de la IA seleccionada
     *         por el usuario.
     */
    private static int obtenerEntradaProfundidad(Scanner sc) {
        int profundidadMaxIA = 0;

        while (true) {
            imprimirMensajeDeSeleccionDeProfundidad();

            try {
                profundidadMaxIA = sc.nextInt();
            } catch (InputMismatchException e) {
                // Se ha introducido un valor que no es un número
                System.out.println("[ERROR] Valor de profundidad incorrecto, ¿ha introducido un número entero?");

                // Resetear buffer y volver a pedir datos
                sc.nextLine();
                continue;
            }

            // Comprobar valores inválidos para profundidad
            if (profundidadMaxIA <= 0) {
                // Valor inválido, volver a pedir datos
                System.out
                        .println("[ERROR] Valor de profundidad incorrecto, recuerde que debe ser mayor que 0...");
                continue;
            }

            // Valor válido
            return profundidadMaxIA;
        }
    }

    /**
     * Imprime el mensaje de selección de tipo de algoritmo.
     */
    private static void imprimirMensajeDeSeleccionDeTipoAlgoritmo() {
        System.out.println("[INICIO] Seleccione uno de los siguientes algoritmos a probar:");
        System.out.println("\t1. Minimax básico.");
        System.out.println("\t2. Minimax con poda alfa-beta.");
    }

    /**
     * Obtiene el tipo de algoritmo a usar preguntando al usuario con Scanner.
     * 
     * @param sc Scanner para obtener la entrada del usuario.
     * @return tipoAlgoritmo Tipo de algoritmo seleccionado por el usuario.
     */
    private static int obtenerEntradaTipoAlgoritmo(Scanner sc) {
        int tipoAlgoritmo = 0;

        while (true) {
            imprimirMensajeDeSeleccionDeTipoAlgoritmo();

            try {
                tipoAlgoritmo = sc.nextInt();
            } catch (InputMismatchException e) {
                // Se ha introducido un valor que no es un número
                System.out.println("[ERROR] Valor de tipo de algoritmo incorrecto, ¿ha introducido un número entero?");

                // Resetear buffer y volver a pedir datos
                sc.nextLine();
                continue;
            }

            // Comprobar valores inválidos para tipo de algoritmo
            if (tipoAlgoritmo < 1 || tipoAlgoritmo > 2) {
                // Valor inválido, volver a pedir datos
                System.out
                        .println(
                                "[ERROR] Valor de tipo de algoritmo incorrecto, recuerde que debe encontrarse entre 1 y 2...");
                continue;
            }

            // Valor válido
            return tipoAlgoritmo;
        }
    }
}
