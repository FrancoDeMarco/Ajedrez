package juego;


import javax.swing.ImageIcon;

public class Peon extends Pieza{
	
	private boolean seMovio;
	

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.seMovio = false;
	}
	/**
	 * Valida el movimiento del peón.
	 * El auxiliar representa la cantidad de movimientos que se harán. Si la pieza ya se movió anteriormente, estos peones se moveran una sola casilla
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
		if((this.getCelda().getColumna() == nuevaCelda.getColumna())&& //Si va a moverse hacia adelante
			(this.getCelda().getFila()-nuevaCelda.getFila()==aux)&& //Si se va a mover las casillas indicadas por aux (segun si es su primer movimiento o no)
			(!(nuevaCelda.getPieza()!=null))){ //Si no come
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
					(Math.abs(this.getCelda().getFila()-nuevaCelda.getFila()) != 2) && 		//si no quiere moverse mas de dos casillas
					(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo()))){ 			//si esta ocupado por el equipo contrario (come)
						return true;
			} else {
				if (!seMovio && 												//si es su primer movimiento
					(this.getCelda().getColumna() == nuevaCelda.getColumna())&& //si se mueve para adelante
					(this.getCelda().getFila()-nuevaCelda.getFila()==aux/2)&& //si quiere moverse una sola casilla (aunque sea su primer turno)
					(!(nuevaCelda.getPieza()!=null))) { //si no come
						return true;
				} else {
					if (!seMovio &&																//si no se movio
						Math.abs((this.getCelda().getColumna() - nuevaCelda.getColumna())) == 1  && //Si la columna es alguna de las que tiene a los costados
						(this.getCelda().getFila()-nuevaCelda.getFila())==aux/2 && 				//si no se mueve para atras
						(Math.abs(this.getCelda().getFila()-nuevaCelda.getFila()) != 2) && 		//si no quiere moverse mas de dos casillas
						(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo()))) {			//si come (en su primer movimiento)
							return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	
	/**
	 * Este metodo sirve para saber si se cumplió el primer movimiento del peón.
	 * De esta forma sabemos si el peón se puede mover una o dos posiciones.
	 */
	@Override
	public void moverse(Celda nuevaCelda) {
		super.moverse(nuevaCelda);
		if (this.seMovio == false) {
			this.seMovio = true;
		}
	}
	
	
}