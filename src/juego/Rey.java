package juego;

import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Rey extends Pieza{
	

	public Rey(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\reyNegro.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\reyBlanco.png"));
		}
	}

	public boolean movimientoValido(Celda nuevaCelda) {
		if(((this.getCelda().getFila() - nuevaCelda.getFila())<=1)&&((this.getCelda().getColumna() - nuevaCelda.getColumna())<=1)&&(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))) {
			return true;
		}else {
			return false;
		}
	}
	
	public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("El Rey de "+ this.getEquipo().getNombre()+"comió la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);		
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						this.setCelda(nuevaCelda);
						System.out.println("El Rey se movió.");
					}
				}	
			}
		}
	}
	
	/*@Override
	public String toString() {
		return "R" + super.toString();
	}*/
}
