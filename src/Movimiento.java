public class Movimiento {
    private int fila;
    private int columna;
    private char ficha;
    private int valorHeuristico = 0;

    public Movimiento(int fila, int columna, char ficha) {
        this.fila = fila;
        this.columna = columna;
        this.ficha = ficha;
    }

    public int obtenerFila() {
        return this.fila;
    }

    public int obtenerColumna() {
        return this.columna;
    }

    public char obtenerFicha() {
        return this.ficha;
    }

    public int obtenerValorHeuristico() {
        return this.valorHeuristico;
    }

    public void cambiarValorHeuristico(int nuevoValorHeuristico) {
        this.valorHeuristico = nuevoValorHeuristico;
    }
}
