package juego;

import java.util.ArrayList;

public interface IJuegoListener {
	
	public default void juegoEmpieza(ArrayList<Pieza> blancas, ArrayList<Pieza> negras) {
		
	}
	
	public default void juegoTermina() {
		
	}
	
	public default void piezaComida(Pieza pieza) {
		
	}
	
	public default void turnoActual(Equipo equipo) {
		
	}
}