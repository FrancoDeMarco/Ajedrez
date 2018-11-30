package juego;

public class Celda {
	
	private int fila;
	private int columna;
	private Pieza pieza;
	
	
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	
	/**
	 * Compruebo que la celda a la que quiere ingresar la pieza este desocupada u ocupada por una pieza del equipo contrario para validar
	 * el ingreso de la misma.
	 * @param pieza
	 * @return
	 */
	public boolean ingresoValido(Pieza pieza) {
		if (this.getPieza() != null) {
			if (this.getPieza().getEquipo().getNombre() != pieza.getEquipo().getNombre()) {
				return true;
			}else{
				return false;
			}
		}return true;
	}
	
	
	
	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	public Pieza getPieza() {
		return this.pieza;
	}
	
	/**
	 * Reviso si la celda está ocupada por el equipo contrario.
	 * @param equipo
	 * @return
	 */
	public boolean estaOcupadaEquipoContrario(Equipo equipo) {
		if (this.getPieza() != null) {
			if(equipo.getNombre() != this.getPieza().getEquipo().getNombre()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Reviso si la celda está ocupada por nuestro equipo.
	 * @param equipo
	 * @return
	 */
	public boolean estaOcupadaPorElMismoEquipo(Equipo equipo) {
		if (this.getPieza() != null) {
			if(equipo.getNombre() == this.getPieza().getEquipo().getNombre()){
				return true;
			}
		}
		return false;
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
