package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Peon extends Pieza{
	
	

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\peonNegro.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\peonBlanco.png"));
		}
	}
	
	public boolean movimientoValido(Celda nuevaCelda) {//EL PEON SOLO SE MUEVE DE A UNA CASILLA PERO ME FALTA QUE COMA, QUE SE MUEVA DE A DOS EL PRIMER MOVIMIENTO
		if((this.getCelda().getColumna() == nuevaCelda.getColumna())&&
				(this.getCelda().getFila()-this.getCelda().getFila()<=1)&&
				(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))){// Y QUE NO SE MUEVA HACIA ATRAS
			return true;
		}else {
			return false;
		}
	}
	
	public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					nuevaCelda.getPieza().morir();
					System.out.println("El peon de "+ this.getEquipo().getNombre()+"comió la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
					this.setCelda(nuevaCelda);		
				}else{//LA CELDA PUEDE ESTAR DESOCUPADA
					this.setCelda(nuevaCelda);
					System.out.println("El peon se movio.");
				}
			}
		}
	}
	/*@Override
	public String toString() {
		return "P" + super.toString();
	}*/
}