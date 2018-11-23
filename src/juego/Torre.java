package juego;

import javax.swing.ImageIcon;

public class Torre extends Pieza{
	

	public Torre(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("img/torreNegra.png"));
		}else {
			this.setImagen(new ImageIcon("img/torreBlanca.png"));
		}
	}

	
	/*public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("La Torre de "+ this.getEquipo().getNombre()+"comi� la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);		
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						this.setCelda(nuevaCelda);
						System.out.println("La Torre se movi�.");
					}
				}	
			}
		}
	}*/
	
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


	
	
	/*@Override
	public String toString() {
		return "T" + super.toString();
	}*/
}
