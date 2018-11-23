package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Alfil extends Pieza{
	

	public Alfil(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\alfilNegro.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\alfilBlanco.png"));
		}
	}

	//VALIDO QUE EL MOVIMIENTO CORRESPONDA A LA PIEZA
	public boolean movimientoValido(Celda nuevaCelda) {
		if(((this.getCelda().getColumna()-this.getCelda().getFila()) == (nuevaCelda.getColumna()-nuevaCelda.getFila()))||
		((this.getCelda().getColumna()+this.getCelda().getFila()) == (nuevaCelda.getColumna()+nuevaCelda.getFila()))//CONDICION DEL MOVIMIENTO DEL ALFIL
        &&(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))) {//VERIFICO QUE LA CELDA NO ESTE OCUPADA POR UNA DE MIS PIEZAS
			return true;
		}else {
			return false;
		}
	}
	
	
}