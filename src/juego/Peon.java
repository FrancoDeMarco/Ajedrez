package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Peon extends Pieza{
	
	private boolean seMovio;
	

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.seMovio = false;
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\peonNegro.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\peonBlanco.png"));
		}
	}
	
	public boolean movimientoValido(Celda nuevaCelda) {//EL PEON SOLO SE MUEVE DE A UNA CASILLA PERO ME FALTA QUE COMA, QUE SE MUEVA DE A DOS EL PRIMER MOVIMIENTO
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
				(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo()))){// Y QUE NO SE MUEVA HACIA ATRAS
			return true;
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
		if (this.seMovio = false) {
			this.seMovio = true;
		}
	}
	
	
	
	/*public void moverse(Celda nuevaCelda){ 
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