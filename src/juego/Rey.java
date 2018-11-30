package juego;

import javax.swing.ImageIcon;

public class Rey extends Pieza{
	

	public Rey(Celda celda, Equipo equipo) {
		super(celda, equipo);
	}

	public boolean movimientoValido(Celda nuevaCelda) {
		if(((Math.abs(this.getCelda().getFila() - nuevaCelda.getFila()))<=1)&&
			(Math.abs(this.getCelda().getColumna() - nuevaCelda.getColumna())<=1)&&
			(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))){
			if (nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {
				if (nuevaCelda.getPieza().esRey()) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean esRey() {
		return true;
	}
	
	
	
}
