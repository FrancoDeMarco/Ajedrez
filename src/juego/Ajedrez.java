package juego;

import java.util.ArrayList;

//TODO {CORREGIDO} [CORRECCION] Para que Ajedrez implementa IJuegoListener?, 
public class Ajedrez{
	private Tablero tablero;
	private Equipo blancas;
	private Equipo negras;
	public static final String BLANCAS = "Blancas";
	public static final String NEGRAS = "Negras";
	//TODO {CORREGIDO}[CORRECCION] Las piezas son de cada equipo, no del Ajedrez
	public ArrayList<IJuegoListener> juegoListener = new ArrayList<IJuegoListener>();
	private static Ajedrez instancia = new Ajedrez();
	//TODO {CORREGIDO}[CORRECCION] El Ajedrez no puede tener una referencia al TableroGUI	
	//TODO {CORREGIDO}[CORRECCION] Estas variables no pueden ser de clase 
	private int movimientos = 0;
	private int piezasBlancasComidas = 0;
	private int piezasNegrasComidas = 0;
	private boolean finalizado = false;
	private boolean reiniciado = false;
	private boolean iniciado = false;
	private boolean usuarioJuega = false;
	private boolean usuarioJugo = false;
	private Pieza piezaMoviendose;

	/**
	 * Llama al tablero, crea las celdas, crea los equipos y crea las piezas. 
	 * 
	 */	
	public void IniciarJuego() {
		this.tablero = new Tablero();
		tablero.crearCeldas(); 
		this.negras = new Equipo(NEGRAS, this);
		this.blancas = new Equipo(BLANCAS, this);
		//TODO {CORREGIDO}[CORRECCION] Desde Ajedrez, no puede existir nada referente a la interfaz grafica.
		this.crearPiezas();
	}
	
	/**
	 * Reinicia los movimientos, la cantidad de piezas blancas y negras comidas, resetea el tablero y crea de nuevo las celdas y los equipo al igual
	 * que las piezas.
	 */
	public void reiniciarJuego() {
		this.movimientos = 0;
		this.piezasBlancasComidas = 0;
		this.piezasNegrasComidas = 0;
		this.tablero = new Tablero();
		tablero.crearCeldas(); 
		this.negras = new Equipo(NEGRAS, this);
		this.blancas = new Equipo(BLANCAS, this);
		//TODO {CORREGIDO}[CORRECCION] Desde Ajedrez, no puede existir nada referente a la interfaz grafica.
		this.crearPiezas();
	}
	
	public void agregarJuegoListener(IJuegoListener escuchador) {
		this.juegoListener.add(escuchador);
	}
	
	public Equipo getEquipoContrario(Equipo equipo) {
		if (equipo == blancas) {
			return negras;
		}else {
			return blancas;
		}
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public Equipo getEquipoBlancas() {
		return this.blancas;
	}
	
	
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Equipo getEquipoNegras() {
		return this.negras;
	}

	/**
	 * Crea las piezas y las coloca en las celdas.
	 * Para los peones, los recorre con un for y al final setea las piezas.
	 */
	public void crearPiezas() {
		
		ArrayList<Pieza> piezasBlancas = new ArrayList<Pieza>();
		ArrayList<Pieza> piezasNegras = new ArrayList<Pieza>();
		
		Rey rey = new Rey(getCelda(0, 4), blancas);
		System.out.println(rey.getCelda());
		piezasBlancas.add(rey);
		
		Alfil alfil1 = new Alfil(getCelda(0,2), blancas);
		piezasBlancas.add(alfil1);
		Alfil alfil2 = new Alfil(getCelda(0,5), blancas);
		piezasBlancas.add(alfil2);
		
		Caballo caballo1 = new Caballo(getCelda(0,1), blancas);
		piezasBlancas.add(caballo1);
		Caballo caballo2 = new Caballo(getCelda(0,6), blancas);
		piezasBlancas.add(caballo2);
		
		for (int i = 0; i < 8; i++) {
			Peon peon = new Peon(getCelda(1, i), blancas);
			piezasBlancas.add(peon);
		}
		
		Reina reina = new Reina(getCelda(0, 3), blancas);
		piezasBlancas.add(reina);
		
		Torre torre1 = new Torre(getCelda(0, 0), blancas);
		piezasBlancas.add(torre1);
		Torre torre2 = new Torre(getCelda(0, 7), blancas);
		piezasBlancas.add(torre2);
		
		Rey rey2 = new Rey (getCelda(7, 4), negras);
		piezasNegras.add(rey2);

		Alfil alfil3 = new Alfil(getCelda(7,2), negras);
		piezasNegras.add(alfil3);
		Alfil alfil4 = new Alfil(getCelda(7,5),negras);
		piezasNegras.add(alfil4);
		
		Caballo caballo3 = new Caballo(getCelda(7,1),negras);
		piezasNegras.add(caballo3);
		Caballo caballo4 = new Caballo(getCelda(7,6),negras);
		piezasNegras.add(caballo4);
		
		for (int i = 0; i < 8; i++) {
			Peon peon = new Peon(getCelda(6, i), negras);
			piezasNegras.add(peon);
		}
		
		Reina reina2 = new Reina (getCelda(7, 3), negras);
		piezasNegras.add(reina2);
		
		Torre torre3 = new Torre (getCelda(7, 0), negras);
		piezasNegras.add(torre3);
		Torre torre4 = new Torre (getCelda(7, 7), negras);
		piezasNegras.add(torre4);
			
		
		this.blancas.setPiezas(piezasBlancas);
		this.negras.setPiezas(piezasNegras);
		
	
}
	

	private Celda getCelda(int i, int j) {
		return this.tablero.getCelda(i, j);
	}

	//TODO {CORREGIDO} [CORRECCION] Utilizar notacion camel
	public void espera() {
		System.out.println("\n");
		try {
			Thread.sleep(100);
		}catch(Exception e) {
			System.out.println("\n");
		}
	}
	
	public void setUsuarioJugo(boolean v) {
		this.usuarioJugo = v;
	}
	
	/**
	 * Comienza el juego. No finaliza mientras el juego sea distinto de Finalizar.
	 * Llama a la espera, cede los turnos, incrementa los movimientos y realiza una segunda espera
	 */
	public void Comenzar() {
		for (IJuegoListener escuchador : this.juegoListener) {
			escuchador.juegoEmpieza(this.getEquipoBlancas().getPiezas(), this.getEquipoNegras().getPiezas());
		}
		while(!this.finalizado()) {
			this.espera();
			if (!this.usuarioJuega) {
				this.darTurno(this.tablero, this.blancas);
			} else {
				while(!this.usuarioJugo) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//ESPERANDO QUE EL USUARIO JUEGUE
				}
				this.usuarioJugo = false;
			}
			this.incrementarMovimientos();
			this.espera();
			if(!this.finalizado()) {
				this.darTurno(this.tablero, this.negras);
			}
		}
		System.out.println("FINALIZA EL JUEGO");
	}
	
	//TODO {CORREGIDO}[CORRECCION] Utilizar notacion camel
	//TODO {CORREGIDO}[CORRECCION] Para que recibe "blancas" y "negras" si el Ajedrez ya las conoce
	public boolean finalizado() {
		if ((this.blancas.getRey().estaViva())&&(this.negras.getRey().estaViva())&&!(this.finalizado)){
			return false;
		}else {
			for (IJuegoListener escuchador : juegoListener) {
				escuchador.juegoTermina(this.getGanador());
			}
			return true;
		}
	}
	
	public void setFinalizado(Boolean v) {
		this.finalizado = v;
	}
	
	public boolean getUsuarioJuega() {
		return this.usuarioJuega;
	}
	
	public Equipo darTurno(Tablero tablero, Equipo equipo) {
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.turnoActual(equipo);
		}
		System.out.println("Cediendo turno...");
		//TODO {CORREGIDO} [CORRECCION] ?? por que llamar a un metodo de clase para esto?
		equipo.jugar();
		System.out.println("Turno de: " + equipo.getNombre());
		return equipo;
	}
	
	
	public Equipo getGanador() {
		//TODO {CORREGIDO/usar}[CORRECCION] ???
		if (!this.blancas.getRey().estaViva()) {
			return this.negras;
		}else {
			if (!this.negras.getRey().estaViva()) {
				return this.blancas;
			}
			else {
				return null;
			}
		}
	}

	
	public static Ajedrez getInstancia() {
		return instancia;
	}	
	
	//TODO {CORREGIDO}[CORRECCION] Todos estos no pueden ser metodos publicos ni de clase
	public void incrementarMovimientos() {
		System.out.println("ENTRE A INCREMENTAR");
		this.setMovimientos(this.getMovimientos() + 1);
	}
	
	public void incrementarPiezasBlancasComidas() {
		this.setPiezasBlancasComidas(this.getPiezasBlancasComidas() + 1);
	}
	
	public void incrementarPiezasNegrasComidas() {
		this.setPiezasNegrasComidas(this.getPiezasNegrasComidas() + 1);
	}

	public int getPiezasBlancasComidas() {
		return this.piezasBlancasComidas;
	}

	public void setPiezasBlancasComidas(int piezasBlancasComidas) {
		this.piezasBlancasComidas = piezasBlancasComidas;
	}

	public int getPiezasNegrasComidas() {
		return this.piezasNegrasComidas;
	}

	public void setPiezasNegrasComidas(int piezasNegrasComidas) {
		this.piezasNegrasComidas = piezasNegrasComidas;
	}

	public int getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}

	public boolean isReiniciado() {
		return this.reiniciado;
	}

	public void setReiniciado(boolean reiniciar) {
		this.reiniciado = reiniciar;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciar) {
		this.iniciado = iniciar;
	}

	public boolean isUsuarioJuega() {
		return usuarioJuega;
	}

	public void setUsuarioJuega(boolean usuarioJuega) {
		this.usuarioJuega = usuarioJuega;
	}

	public Pieza getPiezaMoviendose() {
		return piezaMoviendose;
	}

	public void setPiezaMoviendose(Pieza piezaMoviendose) {
		this.piezaMoviendose = piezaMoviendose;
	}
}