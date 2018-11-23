package juego;

import java.util.ArrayList;

public class Tablero {
	
	private Celda[][] celdas;
	private Pieza pieza;
	
	
	
	public void crearCeldas() {
		this.celdas = new Celda[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Celda casillero = new Celda(i, j);
				this.celdas[i][j] = casillero;
			}
		}
	}
	
	public Celda[][] getCeldas(){
		return this.celdas;
	}
	
	public Celda getCelda(int i, int j) {
		return this.celdas[i][j];
	}
	
	public Tablero tablero() {
		return null;
		
	}
	
	public void MostrarTablero(Tablero tablero) {
		System.out.println(tablero);
	}
	
	public void mover(Pieza pieza, int fila, int columna) {
	/*	this.celda[pieza.getCelda().getFila()] [pieza.getCelda().getColumna()].setPieza(null);//Borro vieja posición
		pieza.setCelda(this.celda[fila][columna]);
		this.celda[fila][columna].setPieza(pieza);//Agrego nueva posición*/
	}

	public void quienesMatan(Pieza pieza) {
		ArrayList<Pieza> piezas;
	}
	
	public void getPiezas(Equipo equipo) {
		
	}
	
	public void limpiar() {
	
	}
}