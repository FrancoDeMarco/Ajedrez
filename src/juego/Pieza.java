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
	private Icon imagen; //TODO [CORRECCION] No puede haber nada de interfaz gráfica aca

	
	
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
		//TODO [CORRECCION] no utilizar numeros fijos, 
		// En todo caso consular al tablero la dimension para saber si esta afuera o no 
		//TODO [CORRECCION] Esto deberia ser responsabilidad del tablero, saber si una fila/columna esta adentro o afuera.
		if ((fila<0 || fila > 7)&&(columna<0 || columna > 7)) {
			return false;
		}else{
			return true;
		}
	}
	

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
		if(this.movimientoDentroDelTablero(nuevaCelda.getFila(), nuevaCelda.getColumna())) {//VERIFICO QUE EL NUEVO MOVIMIENTO SE ENCUENTRE DENTRO DEL TABLERO
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
	}
	
	public boolean esRey() {
		return false;
	}
	
	

}
