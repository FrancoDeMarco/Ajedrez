package prueba;

import juego.Ajedrez;
import juego.TableroGUI;

public class Prueba {

	//TODO [CORRECCION] Falta toda la documentacion 
	
	public static void main(String[] args) {
		while(true) {
			Ajedrez ajedrez = new Ajedrez();
			ajedrez.IniciarJuego();
			TableroGUI.Inicializar(ajedrez);
			ajedrez.Comenzar();
			
			//TODO {CORREGIDO}[CORRECCION] No puede ser un metodo de clase el "finalizado"
			ajedrez.setFinalizado(false);
			
			
			//TODO {CORREGIDO}[CORRECCION] No pueden ser metodo de clases estos de aca
			while(!ajedrez.getReiniciar()) {}
			ajedrez.setReiniciar(false);
		}
	}
}