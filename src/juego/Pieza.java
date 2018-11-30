package juego;

import java.util.ArrayList;

import javax.swing.Icon;

public abstract class Pieza{
	private Celda nuevaCelda;
	private Celda celda;
	private Equipo equipo;
	//TODO {CORREGIDO}[CORRECCION] Para esto tenemos el metodo "ajedrez.getEqupioContrario"
	private boolean estaViva;
	private ArrayList<IPiezaListener> piezaListeners;
	//TODO {CORREGIDO}[CORRECCION] No puede haber nada de interfaz gráfica aca

	
	/**
	 * Devuelve un arreglo de celdas posibles a los que se puede mover la pieza.
	 * @return
	 */
	public ArrayList<Celda> getCeldasPosibles() {
		ArrayList<Celda> celdasPosibles = new ArrayList<Celda>();
		Celda[][] celdas = this.getEquipo().getAjedrez().getTablero().getCeldas();
		for (Celda[] arregloCeldas : celdas) {
			for (Celda celda : arregloCeldas) {
				if(this.getEquipo().getAjedrez().getTablero().movimientoDentroDelTablero(celda.getFila(), celda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMIENTO SE ENCUENTRE DENTRO DEL TABLERO
					if (this.movimientoValido(celda)) {
						celdasPosibles.add(celda);
					}
				}
			}
		}
		return celdasPosibles;
	}
	
	public abstract boolean movimientoValido(Celda nuevaCelda);
	

	public Pieza(Celda celda, Equipo equipo){
		this.celda = celda;
		this.equipo = equipo;
		//TODO {CORREGIDO}[CORRECCION] Todos los set, debe tener el parametro que se desea setear
		this.estaViva = true;
		this.piezaListeners = new ArrayList<IPiezaListener>();
		celda.setPieza(this);
	}

	public Celda getCelda() {
		return celda;
	}

	public void setCelda(Celda celda) {
		for (IPiezaListener escuchador : piezaListeners) {
			escuchador.piezaMovida(this, this.celda, celda);
		}
		this.celda = celda;
		celda.setPieza(this);
	}

	public Celda getNuevaCelda() {
		return nuevaCelda;
	}

	public void setNuevaCelda(Celda nuevaCelda) {
		this.nuevaCelda = nuevaCelda;
	}

	

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipoMio(Equipo equipoMio) {
		this.equipo = equipoMio;
	}
	
	/**
	 * Mata a la pieza e incrementa las cantidad de piezas comidas al equipo correspondiente.
	 */
	public void morir() {
		this.estaViva = false;
		System.out.println("A punto de incrementar comidas");
		if (this.getEquipo().getNombre() == "Blancas") {
			this.getEquipo().getAjedrez().incrementarPiezasBlancasComidas();
		}else {
			this.getEquipo().getAjedrez().incrementarPiezasNegrasComidas();
		}
		for (IPiezaListener escuchador : piezaListeners) {
			escuchador.piezaComida(this);;
		}
	}


	public boolean estaViva() {
		return this.estaViva;
	}

	public void setEstaViva(boolean estaViva) {
		this.estaViva = estaViva;
	}

	public void addPiezaListener(IPiezaListener listener) {
		this.piezaListeners.add(listener);
		
	}

	/**
	 * La pieza se mueve.
	 * Si la celda está ocupada por una pieza del equipo contrario, entonces la pieza que se mueve la mata.
	 * Si no, solo se mueve, ocupa la nueva celda y la vieja celda queda en null.
	 * @param nuevaCelda
	 */
	public void moverse(Celda nuevaCelda){ 
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		if(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN).getPieza() != null) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
			System.out.println("Alguien va a morir");
			this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN).getPieza().morir();
			this.getCelda().setPieza(null); //
			this.setCelda(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN));		
		}else{//LA CELDA PUEDE ESTAR DESOCUPADA
			this.getCelda().setPieza(null);
			this.setCelda(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN));
		}
		//TODO {CORREGIDO}[CORRECCION] La cantidad de movimientos realizados deberia controlarlo el Ajedrez, no la pieza
	
	}
	
	public boolean esRey() {
		return false;
	}
	
	

}
