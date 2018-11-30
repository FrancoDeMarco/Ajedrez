package juego;

public class Tablero {
	
	private Celda[][] celdas;
	private int cantFilas = 8;
	private int cantColumnas = 8;
	
	
	/**
	 * Crea las celdas del tablero.
	 */
	public void crearCeldas() {
		this.celdas = new Celda[this.cantFilas][this.cantColumnas];
		for (int i = 0; i < this.cantFilas; i++) {
			for (int j = 0; j < this.cantColumnas; j++) {
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
	
	
	/**
	 * Revisa que los movimiento se hagan dentro del tablero.
	 * @param fila
	 * @param columna
	 * @return
	 */
	public boolean movimientoDentroDelTablero(int fila, int columna) {
		//TODO {CORREGIDO}[CORRECCION] no utilizar numeros fijos, 
		// En todo caso consular al tablero la dimension para saber si esta afuera o no 
		//TODO {CORREGIDO}[CORRECCION] Esto deberia ser responsabilidad del tablero, saber si una fila/columna esta adentro o afuera.
		if ((fila<0 || fila > this.cantFilas-1)&&(columna<0 || columna > this.cantColumnas-1)) {
			return false;
		}else{
			return true;
		}
	}	

	
	public void getPiezas(Equipo equipo) {
		
	}
	
	public void limpiar() {
	
	}
}