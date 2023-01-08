public class Tablero {
    private int dimension;
    /**
     * Matriz que representa las fichas introducidas en el tablero
     */
    private int[][] fichas;

    public Tablero(int dimensionTablero) {
        // Inicializar tablero
        this.dimension = dimensionTablero;
        this.fichas = new int[this.dimension][this.dimension];
        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                fichas[i][j] = 0;
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                // Imprimir ficha actual
                switch (fichas[i][j]) {
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
