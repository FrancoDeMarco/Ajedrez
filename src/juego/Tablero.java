package juego;

public class Tablero {
	
	private Celda[][] celdas;
	private int cantFilas = 8;
	private int cantColumnas = 8;
	
	
	
	public void crearCeldas() {
		this.celdas = new Celda[this.cantFilas][this.cantColumnas];
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
	
	

	
	public void getPiezas(Equipo equipo) {
		
	}
	
	public void limpiar() {
	
	}
}