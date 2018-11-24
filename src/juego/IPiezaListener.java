package juego;

public interface IPiezaListener {
	
	
	public default void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		
	}
	
	public default void piezaComida(Pieza pieza) {
		
	}
	
}
