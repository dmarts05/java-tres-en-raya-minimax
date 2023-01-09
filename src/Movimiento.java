/**
 * Representa un movimiento de 3 en raya.
 */
public class Movimiento {
    /**
     * Fila del movimiento.
     */
    private final int fila;

    /**
     * Columna del movimiento.
     */
    private final int columna;

    /**
     * Ficha del movimiento.
     */
    private final char ficha;

    /**
     * Valor heurístico del movimiento según el estado del tablero.
     * 
     * Este valor solo cambia si una IA realiza el movimiento.
     */
    private int valorHeuristico = 0;

    /**
     * Genera un moviento dada una fila, una columna y la ficha a utilizar.
     * 
     * @param fila    Fila del movimiento.
     * @param columna Columna del movimiento.
     * @param ficha   Ficha del movimiento.
     */
    public Movimiento(int fila, int columna, char ficha) {
        this.fila = fila;
        this.columna = columna;
        this.ficha = ficha;
    }

    /**
     * Obtiene la fila del movimiento.
     * 
     * @return fila Fila del movimiento.
     */
    public int obtenerFila() {
        return this.fila;
    }

    /**
     * Obtiene la columna del movimiento.
     * 
     * @return columna Columna del movimiento.
     */
    public int obtenerColumna() {
        return this.columna;
    }

    /**
     * Obtiene la ficha del movimiento.
     * 
     * @return ficha Ficha del movimiento.
     */
    public char obtenerFicha() {
        return this.ficha;
    }

    /**
     * Obtiene el valor heurístico del movimiento.
     * 
     * @return valorHeuristico Valor heurístico del movimiento.
     */
    public int obtenerValorHeuristico() {
        return this.valorHeuristico;
    }

    /**
     * Cambia el valor heurístico del movimiento por el recibido.
     * 
     * @param nuevoValorHeuristico Nuevo valor heurístico del movimiento.
     */
    public void cambiarValorHeuristico(int nuevoValorHeuristico) {
        this.valorHeuristico = nuevoValorHeuristico;
    }
}
