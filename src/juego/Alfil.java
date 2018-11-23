package juego;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Alfil extends Pieza{
	

	public Alfil(Celda celda, Equipo equipo) {
		super(celda, equipo);
		if(equipo.getNombre() == "Negras") {
			this.setImagen(new ImageIcon("img/alfilNegro.png"));
		}else {
			this.setImagen(new ImageIcon("img/alfilBlanco.png"));
		}
	}

	//VALIDO QUE EL MOVIMIENTO CORRESPONDA A LA PIEZA
	public boolean movimientoValido(Celda nuevaCelda) {
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		int filaV = this.getCelda().getFila();
		int columnaV = this.getCelda().getColumna();
		System.out.println("Celda "+nuevaCelda);
		System.out.println("Celda "+this.getCelda());
		System.out.println("Pieza"+this);
		System.out.println("Pieza en celda "+nuevaCelda.getPieza());
		System.out.println(this.getEquipo().getNombre());
		System.out.println(nuevaCelda.getPieza().getEquipo().getNombre());
		if(((this.getCelda().getColumna()-this.getCelda().getFila()) == (nuevaCelda.getColumna()-nuevaCelda.getFila()))||
		((this.getCelda().getColumna()+this.getCelda().getFila()) == (nuevaCelda.getColumna()+nuevaCelda.getFila()))//CONDICION DEL MOVIMIENTO DEL ALFIL
        &&(this.getEquipo()!=nuevaCelda.getPieza().getEquipo())) {//VERIFICO QUE LA CELDA NO ESTE OCUPADA POR UNA DE MIS PIEZAS
			int i = 0;
			int j = 0;
			if (columnaV < columnaN) { // -> +
				if (filaV < filaN) { // v +
					for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j++;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
						}
					}
				} else { // ^ -
					for (i = -1;  filaV+i < filaN;  i--) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j++;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
							return false;
						}
					}
				}
			} else { // <- -
				if (filaV < filaN) { // v +
					for (i = 1;  filaV+i < filaN;  i++) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j--;
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
						}
					}					
				} else { // ^ -
					for (i = -1;  filaV+i < filaN;  i--) { //Recorro el camino, si esta ocupado por otra pieza (de cualquier equipo) retorna falso
						j--;
						System.out.println("Fila nueva "+filaN);
						System.out.println("Columna nueva "+columnaN);
						System.out.println("Falla en fila "+filaV+i);
						System.out.println("Falla en columna"+columnaV+j);
						if(this.getEquipo().getAjedrez().getTablero().getCelda(filaV+i, columnaV+j).getPieza() != null) {
								return false;
						}
					}
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	
}