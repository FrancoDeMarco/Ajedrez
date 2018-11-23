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
	public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("El alfil de "+ this.getEquipo().getNombre()+"comió la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						this.setCelda(nuevaCelda);
						System.out.println("El alfil se movió.");
					}
				}
			}
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

	/*@Override
	public String toString() {
		return "A" + super.toString();
	}*/
	
	
}