package juego;

import javax.swing.ImageIcon;

public class Reina extends Pieza{
	
	

	public Reina(Celda celda, Equipo equipo) {
		super(celda, equipo);
		//TODO [CORRECCION] Esto no va 
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("img/reinaNegra.png"));
		}else {
			this.setImagen(new ImageIcon("img/reinaBlanca.png"));
		}
	}
	
	
	//TODO [CORRECCION] Generar codigo mas legible

 	public boolean movimientoValido(Celda nuevaCelda) {//EL MOVIMIENTO DE LA REINA ES UNA COMBINACION ENTRE EL ALFIL Y LA TORRE
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		int filaV = this.getCelda().getFila();
		int columnaV = this.getCelda().getColumna();
		
		//SI SE VA A MOVER COMO ALFIL
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
		}else { //SI SE VA A MOVER COMO TORRE
			if (((this.getCelda().getFila()==nuevaCelda.getFila()))||(this.getCelda().getColumna()==nuevaCelda.getColumna())){
				if (!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {
					if (columnaV == columnaN) { //^v
						if (filaV > filaN) { //^
							for (int i = 1; i < Math.abs(filaV-filaN); i++) {
								if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV-i, columnaV).getPieza() != null) {
									return false;
								}
							}
						} else {//v
							for (int i = 1; i < Math.abs(filaV-filaN); i++) {
								if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV).getPieza() != null) {
									return false;
								}
							}
						}
					} else { //<--->
						if (columnaV > columnaN) { // <
							for (int i = 1; i < Math.abs(columnaV-columnaN); i++) {
								if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV-i).getPieza() != null) {
									return false;
								}
							}
						}else { // >
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
	
	

}
