package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Caballo extends Pieza{
	

	public Caballo(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\caballoNegro.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\caballoBlanco.png"));
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
	
	public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("El caballo de "+ this.getEquipo().getNombre()+"comió la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);		
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						this.setCelda(nuevaCelda);
						System.out.println("El caballo se movió.");
					}
				}	
			}
		}
	}
	/*@Override
	public String toString() {
		return "C" + super.toString();
	}*/
}
