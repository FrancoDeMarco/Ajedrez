package juego;



import javax.swing.ImageIcon;

public class Caballo extends Pieza{
	

	public Caballo(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("img/caballoNegro.png"));
		}else {
			this.setImagen(new ImageIcon("img/caballoBlanco.png"));
		}
	}

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
