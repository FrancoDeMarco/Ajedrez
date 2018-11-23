package juego;

public class Jugada {

	private Pieza pieza;
	private int fila;
	private int columna;
	
	public Jugada(Pieza pieza, int fila, int columna) {
		this.pieza = pieza;
		this.fila = fila;
		this.columna = columna;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
}