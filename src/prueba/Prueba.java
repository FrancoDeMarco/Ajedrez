package prueba;

import juego.Ajedrez;

public class Prueba {

	public static void main(String[] args) {
		while(true) {
			Ajedrez ajedrez = new Ajedrez();
			ajedrez.IniciarJuego();
			ajedrez.Comenzar();
			Ajedrez.setFinalizado(false);
			while(!Ajedrez.getReiniciar()) {}
			Ajedrez.setReiniciar(false);
		}
	}
}