package juego;

import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Reina extends Pieza{
	
	

	public Reina(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("img/reinaNegra.png"));
		}else {
			this.setImagen(new ImageIcon("img/reinaBlanca.png"));
		}
	}
	
	/*public void moverse(Celda nuevaCelda){ 
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.movimientoValido(nuevaCelda)) {//VERIFICO QUE EL MOVIMIENTO CORRESPONDA
				if(nuevaCelda.estaOcupadaEquipoContrario(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
					if(!nuevaCelda.estaOcupadaPorElMismoEquipo(this.getEquipo())) {//PUEDE ESTAR OCUPADA POR UNA DE MIS PIEZAS
						nuevaCelda.getPieza().morir();
						System.out.println("La Reina de "+ this.getEquipo().getNombre()+"comi� la pieza de " + nuevaCelda.getPieza().getNombreEquipoContrario()+".");
						this.setCelda(nuevaCelda);		
					}else{//LA CELDA PUEDE ESTAR DESOCUPADA
						System.out.println("La Reina se movi�.");
						this.setCelda(nuevaCelda);
					}
				}	
			}
		}
	}*/

	public boolean movimientoValido(Celda nuevaCelda) {//EL MOVIMIENTO DE LA REINA ES UNA COMBINACION ENTRE EL ALFIL Y LA TORRE
		if((this.getCelda().getFila()==nuevaCelda.getFila()||(this.getCelda().getColumna() == nuevaCelda.getColumna())) 
		||((this.getCelda().getFila()-nuevaCelda.getFila())==(this.getCelda().getColumna() - nuevaCelda.getColumna())
		||(this.getCelda().getFila()+nuevaCelda.getFila())==(this.getCelda().getColumna()+nuevaCelda.getColumna()))
		&&(!nuevaCelda.estaOcupadaPorElMismoEquipo(getEquipo()))){
			return true;
		}else {
			return false;
		}
		
	}
	
	/*@Override
	public String toString() {
		return "D" + super.toString();
	}*/
}
