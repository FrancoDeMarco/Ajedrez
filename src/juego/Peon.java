package juego;


import javax.swing.ImageIcon;

public class Peon extends Pieza{
	
	private boolean seMovio;
	

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.seMovio = false;
	}
	/**
	 * Valida el movimiento del pe�n.
	 * El auxiliar representa la cantidad de movimientos que se har�n. Si la pieza ya se movi� anteriormente, estos peones se moveran una sola casilla
	 * y dependiendo si son blancas o negras se moveran 1 (Negra) o -1(blancas) en el tablero. Si es la primera vez que se mueven, se moveran en 
	 * -2 o 2.
	 */
	public boolean movimientoValido(Celda nuevaCelda) {
		int aux;
		if(this.seMovio) {
			if (this.getEquipo().getNombre() == "Negras") {
				aux = 1;
			}else {
				aux = -1;
			}
		}else {
			if (this.getEquipo().getNombre() == "Negras") {
				aux = 2;
			}else {
				aux = -2;
			}
		}
		if((this.getCelda().getColumna() == nuevaCelda.getColumna())&&
			(this.getCelda().getFila()-nuevaCelda.getFila()==aux)&&
			(!(nuevaCelda.getPieza()!=null))){
				if(Math.abs(aux) == 2) {
					if (this.getEquipo().getAjedrez().getTablero().getCelda(this.getCelda().getFila()-(aux/2), this.getCelda().getColumna()).getPieza() == null) { //que no salte piezas
						return true;
					} else {
						return false;
					}
				}else {
					return true;
				}
		}else {
			if (Math.abs((this.getCelda().getColumna() - nuevaCelda.getColumna())) == 1  && //Si la columna es alguna de las que tiene a los costados
					(this.getCelda().getFila()-nuevaCelda.getFila())==aux && 				//si no se mueve para atras
					(Math.abs(aux) != 2) && 												//si no quiere moverse mas de dos casillas
					(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo()))){ 			//si esta ocupado por el equipo contrario
						return true;
			} else {
				return false;
			}
		}
	}

	
	/**
	 * Este metodo sirve para saber si se cumpli� el primer movimiento del pe�n.
	 * De esta forma sabemos si el pe�n se puede mover una o dos posiciones.
	 */
	@Override
	public void moverse(Celda nuevaCelda) {
		super.moverse(nuevaCelda);
		if (this.seMovio == false) {
			this.seMovio = true;
		}
	}
	
	
}