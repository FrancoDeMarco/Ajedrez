package juego;

import javax.swing.ImageIcon;

public class Reina extends Pieza{
	
	

	public Reina(Celda celda, Equipo equipo) {
		super(celda, equipo);
		//TODO {CORREGIDO}[CORRECCION] Esto no va 
	}
	
	
	//TODO {CORREGIDO}[CORRECCION] Generar codigo mas legible.

	
	/**
	 * El movimiento de la Reina es una combinación de la Torre y el Alfil.
	 * 
	 */
 	public boolean movimientoValido(Celda nuevaCelda) {//EL MOVIMIENTO DE LA REINA ES UNA COMBINACION ENTRE EL ALFIL Y LA TORRE
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		int filaV = this.getCelda().getFila();
		int columnaV = this.getCelda().getColumna();
		
		//SI SE VA A MOVER COMO ALFIL
		if(((this.getCelda().getColumna()-this.getCelda().getFila()) == (nuevaCelda.getColumna()-nuevaCelda.getFila()))||
		((this.getCelda().getColumna()+this.getCelda().getFila()) == (nuevaCelda.getColumna()+nuevaCelda.getFila()))) {//CONDICION DEL MOVIMIENTO DEL ALFIL
	        return this.chequeoDiagonal(nuevaCelda, columnaV, columnaN, filaV, filaN);
		}else { //SI SE VA A MOVER COMO TORRE
			return this.chequeoHorizontal(nuevaCelda, columnaV, columnaN, filaV, filaN);
		}
	}
	
 	/**
 	 * Este método chequea los movimientos diagonales del mismo modo que el alfil.
 	 * 
 	 * @param nuevaCelda
 	 * @param columnaV
 	 * @param columnaN
 	 * @param filaV
 	 * @param filaN
 	 * @return
 	 */
	public boolean chequeoDiagonal(Celda nuevaCelda, int columnaV, int columnaN, int filaV, int filaN){
		if (!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {
			int i = 0;
			int j = 0;
			if (columnaV < columnaN) { // -> + SE MUEVE HACIA LA DERECHA
				if (filaV < filaN) { // v + SE MUEVE HACIA ABAJO A LA DERECHA
					for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j++;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
						}
					}
				} else { // ^ - SE MUEVE HACIA ARRIBA A LA DERECHA
					for (i = -1;  filaV+i > filaN;  i--) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j++;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
							return false;
						}
					}
				}
			} else { // <- - SE MUEVE A LA IZQUIERDA
				if (filaV < filaN) { // v + SE MUEVE HACIA ABAJO A LA IZQUIERDA
					for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j--;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
						}
					}					
				} else { // ^ - SE MUEVE HACIA ARRIBA A LA IZQUIERDA
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
	}

 	/**
 	 * Chequea los movimientos horizontales del mismo modo que la torre.
 	 * @param nuevaCelda
 	 * @param columnaV
 	 * @param columnaN
 	 * @param filaV
 	 * @param filaN
 	 * @return
 	 */
	public boolean chequeoHorizontal(Celda nuevaCelda, int columnaV, int columnaN, int filaV, int filaN){
		if (((this.getCelda().getFila()==nuevaCelda.getFila()))||(this.getCelda().getColumna()==nuevaCelda.getColumna())){
			if (!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {
				if (columnaV == columnaN) { //^v
					if (filaV > filaN) { //^ SE MUEVE HACIA ARRIBA
						for (int i = 1; i < Math.abs(filaV-filaN); i++) {
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV-i, columnaV).getPieza() != null) {
								return false;
							}
						}
					} else {//v SE MUEVE HACIA ABAJO
						for (int i = 1; i < Math.abs(filaV-filaN); i++) {
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV).getPieza() != null) {
								return false;
							}
						}
					}
				} else { //<--->
					if (columnaV > columnaN) { // < SE MUEVE HACIA LA IZQUIERDA
						for (int i = 1; i < Math.abs(columnaV-columnaN); i++) {
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV-i).getPieza() != null) {
								return false;
							}
						}
					}else { // > SE MUEVE HACIA LA DERECHA
						for (int i = 1; i < Math.abs(columnaV-columnaN); i++) {
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV+i).getPieza() != null) {
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
			return  false;
		}
	}
	
	
}
