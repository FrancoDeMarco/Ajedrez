package juego;

import java.awt.List;
import java.util.ArrayList;

public class Ajedrez implements IJuegoListener{
	
	private Tablero tablero;
	private Equipo blancas;
	private Equipo negras;
	public static final String BLANCAS = "Blancas";
	public static final String NEGRAS = "Negras";
	private ArrayList<Pieza> piezasBlancas = new ArrayList<Pieza>();
	private ArrayList<Pieza> piezasNegras = new ArrayList<Pieza>();
	public ArrayList<IJuegoListener> juegoListener = new ArrayList<IJuegoListener>();
	private static Ajedrez instancia = new Ajedrez();
	private TableroGUI tableroGUI;
	private static int movimientos = 0;
	private static int piezasBlancasComidas = 0;
	private static int piezasNegrasComidas = 0;
	
	
	public void IniciarJuego() {
		this.tablero = new Tablero();
		tablero.crearCeldas(); 
		this.negras = new Equipo(NEGRAS, this);
		this.blancas = new Equipo(BLANCAS, this);
		this.tableroGUI = new TableroGUI();
		tableroGUI.setVisible(true);
		this.juegoListener.add(tableroGUI);
		this.crearPiezas();
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	
	
	public static String getBlanco() {
		return BLANCAS;
	}

	public ArrayList<Pieza> getPiezasBlancas() {
		return piezasBlancas;
	}

	public void crearPiezas() {
		
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
			
		for (Pieza pieza : piezasBlancas) {
			pieza.addPiezaListener(this.tableroGUI);
		}
		for (Pieza pieza : piezasNegras) {
			pieza.addPiezaListener(this.tableroGUI);
		}
		
		this.blancas.setPiezas(piezasBlancas);
		this.negras.setPiezas(piezasNegras);
		

		for (IJuegoListener escuchador : juegoListener) {
			escuchador.juegoEmpieza(piezasBlancas, piezasNegras);
		}
	
}
	

	private Celda getCelda(int i, int j) {
		return this.tablero.getCelda(i, j);
	}

	public void Espera() {
		System.out.println("\n");
		try {
			Thread.sleep(100);
		}catch(Exception e) {
			System.out.println("\n");
		}
	}
	
	public void Comenzar() {
		while(!this.Finalizar(this.blancas, this.negras)) {
			
			this.darTurno(this.tablero, this.blancas);
			this.Espera();
			
			if(!this.Finalizar(this.negras, this.blancas)) {
				this.darTurno(this.tablero, this.negras);
				this.Espera();
			}
		}
	}
	
	public boolean Finalizar(Equipo blancas, Equipo negras) {
		if ((blancas.getRey().estaViva())&&(negras.getRey().estaViva())){
			return false;
		}else {
			return true;
		}
	}
	
	public Equipo darTurno(Tablero tablero, Equipo equipo) {
		System.out.println("Cediendo turno...");
		Equipo.Jugar(equipo);
		System.out.println("Turno de: " + equipo.getNombre());
		return equipo;
	}
	
	
	public void getGanador() {
		
	}

	
	public static Ajedrez getInstancia() {
		return instancia;
	}	
	
	public static void incrementarMovimientos() {
		Ajedrez.movimientos++;
	}
	
	public static void incrementarPiezasBlancasComidas() {
		Ajedrez.piezasBlancasComidas++;
	}
	
	public static void incrementarPiezasNegrasComidas() {
		Ajedrez.piezasNegrasComidas++;
	}
}