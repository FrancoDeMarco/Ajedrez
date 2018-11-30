package juego;

import javax.swing.ImageIcon;

public class Torre extends Pieza{
	

	public Torre(Celda celda, Equipo equipo) {
		super(celda, equipo);
	}


	
	public boolean movimientoValido(Celda nuevaCelda) {
		if (((this.getCelda().getFila()==nuevaCelda.getFila()))||(this.getCelda().getColumna()==nuevaCelda.getColumna())){
			if (!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {
				int filaN = nuevaCelda.getFila();
				int columnaN = nuevaCelda.getColumna();
				int filaV = this.getCelda().getFila();
				int columnaV = this.getCelda().getColumna();
				if (columnaV == columnaN) { //^v
					if (filaV > filaN) { //^
						for (int i = 1; i < Math.abs(filaV-filaN); i++) {
							System.out.println(this.getEquipo().getAjedrez().getTablero().getCelda(filaV-i, columnaV).getPieza());
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV-i, columnaV).getPieza() != null) {
								return false;
							}
						}
					} else {//v
						for (int i = 1; i < Math.abs(filaV-filaN); i++) {
							System.out.println(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV).getPieza() );
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV).getPieza() != null) {
								return false;
							}
						}
					}
				} else { //<--->
					if (columnaV > columnaN) { // <
						for (int i = 1; i < Math.abs(columnaV-columnaN); i++) {
							System.out.println(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV-i).getPieza());
							if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV-i).getPieza() != null) {
								return false;
							}
						}
					}else { // >
						for (int i = 1; i < Math.abs(columnaV-columnaN); i++) {
							System.out.println(this.getEquipo().getAjedrez().getTablero().getCelda(filaV, columnaV+i).getPieza() );
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
