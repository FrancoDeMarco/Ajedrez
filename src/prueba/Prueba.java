package prueba;

import juego.Ajedrez;
import juego.TableroGUI;

public class Prueba {

	//TODO [CORRECCION] Falta toda la documentacion 
	
	public static void main(String[] args) {
		Ajedrez ajedrez = new Ajedrez();
		ajedrez.IniciarJuego();
		TableroGUI.Inicializar(ajedrez);
		while(true) {
			try {
				Thread.sleep(100); //Sleep para que los elementos gráficos carguen correctamente
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ajedrez.isReiniciado()) {
				ajedrez.reiniciarJuego();
				ajedrez.setIniciado(true);
				ajedrez.setReiniciado(false);
			}
			if (ajedrez.isIniciado()) {
				ajedrez.reiniciarJuego();
				ajedrez.setIniciado(false);
				ajedrez.Comenzar();
				//TODO {CORREGIDO}[CORRECCION] No puede ser un metodo de clase el "finalizado"
				ajedrez.setFinalizado(false);
				//TODO {CORREGIDO}[CORRECCION] No pueden ser metodo de clases estos de aca
			}
		}
	}
}