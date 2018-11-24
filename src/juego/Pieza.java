package juego;

import java.util.ArrayList;

import javax.swing.Icon;

public abstract class Pieza{
	private Celda nuevaCelda;
	private Celda celda;
	private Equipo equipo;
	private String nombreEquipoContrario;
	private boolean estaViva;
	private ArrayList<IPiezaListener> piezaListeners;
	private Icon imagen;

	public void getMovimientosPosibles() {
		//ArrayList<Celda>
		
	}
	
	public ArrayList<Celda> getCeldasPosibles() {
		ArrayList<Celda> celdasPosibles = new ArrayList<Celda>();
		Celda[][] celdas = this.getEquipo().getAjedrez().getTablero().getCeldas();
		for (Celda[] arregloCeldas : celdas) {
			for (Celda celda : arregloCeldas) {
				if (this.movimientoValido(celda)) {
					celdasPosibles.add(celda);
				}
			}
		}
		return celdasPosibles;
	}
	
	public abstract boolean movimientoValido(Celda nuevaCelda);
	

	
	public boolean movimientoDentroDelTablero(int fila, int columna) {
		if ((fila<0 || fila > 7)&&(columna<0 || columna > 7)) {
			return false;
		}else{
			return true;
		}
	}
	

	public Pieza(Celda celda, Equipo equipo){
		this.celda = celda;
		this.equipo = equipo;
		this.setNombreEquipoContrario();;
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
	
	public void morir() {
		this.estaViva = false;
		System.out.println("A punto de incrementar comidas");
		if (this.getEquipo().getNombre() == "Blancas") {
			Ajedrez.incrementarPiezasBlancasComidas();
		}else {
			Ajedrez.incrementarPiezasNegrasComidas();
		}
	}

	public String getNombreEquipoContrario() {
		return nombreEquipoContrario;
	}

	public void setNombreEquipoContrario() {
		if (this.getEquipo().getNombre()=="Blanco") {
			this.nombreEquipoContrario = "Negro";
		}else {
			this.nombreEquipoContrario = "Blanco";
		}
	}

	public boolean estaViva() {
		return this.estaViva;
	}

	public void setEstaViva(boolean estaViva) {
		this.estaViva = estaViva;
	}

	/**
	 * Metodo que indica si la pieza se puede mover
	 * @return
	 */
	public boolean tieneJugada() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Metodo que indica si la pieza puede matar a otra
	 * @return
	 */
	public boolean puedeMatar() {
		
		
		return false;
	}

	public Icon getImagen() {
		return imagen;
	}

	public void setImagen(Icon imagen) {
		this.imagen = imagen;
	}

	public void addPiezaListener(IPiezaListener listener) {
		this.piezaListeners.add(listener);
		
	}

	public void moverse(Celda nuevaCelda){ 
		int filaN = nuevaCelda.getFila();
		int columnaN = nuevaCelda.getColumna();
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMINETO SE ENCUENTRE DENTRO DEL TABLERO
			if(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN).getPieza() != null) {//PUEDE ESTAR OCUPADA POR UNO DEL EQUIPO CONTRARIO
				System.out.println("Alguien va a morir");
				this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN).getPieza().morir();
				this.getCelda().setPieza(null); //
				this.setCelda(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN));		
			}else{//LA CELDA PUEDE ESTAR DESOCUPADA
				this.getCelda().setPieza(null);
				this.setCelda(this.getEquipo().getAjedrez().getTablero().getCelda(filaN, columnaN));
			}
			Ajedrez.incrementarMovimientos();
			System.out.println("MOVIMIENTOS: "+ Ajedrez.getMovimientos());
			System.out.println("Piezas blancas comidas:" + Ajedrez.getPiezasBlancasComidas());
			System.out.println("Piezas negras comidas:" + Ajedrez.getPiezasNegrasComidas());
		}
	}
	
	public boolean esRey() {
		return false;
	}
	
	

}
