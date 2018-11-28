package juego;

public class Celda {
	
	private int fila;
	private int columna;
	private Pieza pieza;
	
	
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
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
