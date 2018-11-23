package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Torre extends Pieza{
	

	public Torre(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\torreNegra.png"));
		}else {
			this.setImagen(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\torreBlanca.png"));
		}
	}

	
	public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("La Torre de "+ this.getEquipo().getNombre()+"comió la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);		
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						this.setCelda(nuevaCelda);
						System.out.println("La Torre se movió.");
					}
				}	
			}
		}
	}
	
	public boolean movimientoValido(Celda nuevaCelda) {
		if (((this.getCelda().getFila()==nuevaCelda.getFila()))||(this.getCelda().getColumna()==nuevaCelda.getColumna())
				&&(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))){
			return true;
		}else {
			return  false;
		}
	}


	
	
	/*@Override
	public String toString() {
		return "T" + super.toString();
	}*/
}
