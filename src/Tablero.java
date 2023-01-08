public class Tablero {
    private int dimensionTablero;
    /**
     * Matriz que representa las fichas introducidas en el tablero
     */
    private int[][] fichasTablero;

    public Tablero(int dimensionTablero) {
        // Inicializar tablero
        this.dimensionTablero = dimensionTablero;
        this.fichasTablero = new int[this.dimensionTablero][this.dimensionTablero];
        for (int i = 0; i < fichasTablero.length; i++) {
            for (int j = 0; j < fichasTablero.length; j++) {
                fichasTablero[i][j] = 0;
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < fichasTablero.length; i++) {
            for (int j = 0; j < fichasTablero.length; j++) {
                // Imprimir ficha actual
                switch (fichasTablero[i][j]) {
                    case 1:
                        System.out.print("X");
                        break;
                    case 2:
                        System.out.print("O");
                        break;
                    case 0:
                        System.out.print(" ");
                        break;
                }

                // Imprimir separador
                if (j < 2) {
                    System.out.print("|");
                }
            }

            // Salto de línea entre filas
            if (i < 2) {
                System.out.println();
            }
        }
    }
}
