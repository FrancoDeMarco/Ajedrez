package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Alfil extends Pieza{
	

	public Alfil(Celda celda, Equipo equipo) {
		super(celda, equipo);
		//TODO {CORREGIDO}[CORRECCION] Esto no va
	}

	
	/**
	 * Valido el movimiento del alfil.
	 * Primero me aseguro que el movimiento a hacer corresponda y luego con los "for" me aseguro que no haya ninguna pieza entre el origen y el
	 * destino del alfil.
	 */
	public boolean movimientoValido(Celda nuevaCelda) {
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		int filaV = this.getCelda().getFila();
		int columnaV = this.getCelda().getColumna();
		if(((this.getCelda().getColumna()-this.getCelda().getFila()) == (nuevaCelda.getColumna()-nuevaCelda.getFila()))||
		((this.getCelda().getColumna()+this.getCelda().getFila()) == (nuevaCelda.getColumna()+nuevaCelda.getFila()))) {//CONDICION DEL MOVIMIENTO DEL ALFIL
	        if (!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {
				int i = 0;
				int j = 0;
				if (columnaV < columnaN) { // -> +
					if (filaV < filaN) { // v +
						for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
							j++;
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
									return false;
							}
						}
					} else { // ^ -
						for (i = -1;  filaV+i > filaN;  i--) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
							j++;
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
							}
						}
					}
				} else { // <- -
					if (filaV < filaN) { // v +
						for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
							j--;
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
									return false;
							}
						}					
					} else { // ^ -
						for (i = -1;  filaV+i > filaN;  i--) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
							j--;

							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
									return false;
							}
						}
					}
				}
				return true;
	        }else {
	        	return false;
	        }
		}else {
			return false;
		}
	}
	
	
}