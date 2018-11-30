package juego;


import javax.swing.ImageIcon;

public class Peon extends Pieza{
	
	private boolean seMovio;
	

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.seMovio = false;
	}
	
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

	@Override
	public void moverse(Celda nuevaCelda) {
		super.moverse(nuevaCelda);
		if (this.seMovio == false) {
			this.seMovio = true;
		}
	}
	
	
}