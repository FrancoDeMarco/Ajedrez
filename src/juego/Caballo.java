package juego;



import javax.swing.ImageIcon;

public class Caballo extends Pieza{
	

	public Caballo(Celda celda, Equipo equipo) {
		super(celda, equipo);
	}

	/**
	 * Valido el movmiento del Caballo.
	 * Verifico que movimiento corresponda a la pieza y como el Caballo se mueve salteado no necesito verificar que no haya piezas
	 * entre el origen y el destino.
	 */
	public boolean movimientoValido(Celda nuevaCelda) {//VALIDA EL MOVIMIENTO DEL CABALLO
		if((this.getCelda().getFila() - nuevaCelda.getFila())*((this.getCelda().getFila()-nuevaCelda.getFila()))+
				(this.getCelda().getColumna()-nuevaCelda.getColumna())*(this.getCelda().getColumna()-nuevaCelda.getColumna())==5
				&&(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))) {
			//verificar que la celda destino se pueda mover
			return true;
		}else{
			return false;
		}
	}
	

}
