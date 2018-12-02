package juego;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private ArrayList<Pieza> piezas = new ArrayList<Pieza>();

	public String getNombre() {
		return nombre;
	}

	/**
	 * Calcula las jugadas posibles y las ejecuta.
	 * Los println fueron en un principio para poder ver si se estaba ejecutando por consola.
	 */
	public void jugar() {
		ArrayList<Jugada> jugadas = this.calcularJugadasPosibles();
		Jugada jugada;
		if (this.getAjedrez().isDificultadMaestra()) {
			ArrayList<Jugada> jugadasQueComenMasImportantes = new ArrayList<Jugada>();
			ArrayList<Jugada> jugadasQueComenMenosImportantes = new ArrayList<Jugada>();
			for (Jugada j : jugadas) {
				Pieza p = this.getAjedrez().getTablero().getCelda(j.getFila(), j.getColumna()).getPieza();
				if (p != null) {
					if (p.getEquipo() != this) {
						if (p instanceof Reina || p instanceof Torre || p instanceof Rey) {
							jugadasQueComenMasImportantes.add(j);
						}else {
							jugadasQueComenMenosImportantes.add(j);
						}
					}
				}
				
			}
			int z = jugadasQueComenMasImportantes.size();
			if (z != 0) {
				int azar = ThreadLocalRandom.current().nextInt(0,z);
				jugada = jugadasQueComenMasImportantes.get(azar);
			} else {
				int y = jugadasQueComenMenosImportantes.size();
				if (y != 0) {
					int azar = ThreadLocalRandom.current().nextInt(0,y);
					jugada = jugadasQueComenMenosImportantes.get(azar);
				}else {
					int x = jugadas.size();
					int azar = ThreadLocalRandom.current().nextInt(0,x);
					jugada = jugadas.get(azar);
				}
			}

		} else {
			ArrayList<Jugada> jugadasQueComen = new ArrayList<Jugada>();
			for (Jugada j : jugadas) {
				Pieza p = this.getAjedrez().getTablero().getCelda(j.getFila(), j.getColumna()).getPieza();
				if (p != null) {
					if (p.getEquipo() != this) {
						jugadasQueComen.add(j);
					}
				}
				
			}
			int y = jugadasQueComen.size();
			if (y != 0) {
				int azar = ThreadLocalRandom.current().nextInt(0,y);
				jugada = jugadasQueComen.get(azar);
			}else {
				int x = jugadas.size();
				int azar = ThreadLocalRandom.current().nextInt(0,x);
				jugada = jugadas.get(azar);
			}
			
		}
		System.out.println("jugada: "+jugada);
		System.out.println("Antes de ejecutar: Fila: " +  this.getRey().getCelda().getFila()+" ; Columna: " + this.getRey().getCelda().getColumna());
		this.ejecutar(jugada);
		System.out.println("Despues de ejecutar: Fila: "+ this.getRey().getCelda().getFila()+" ; Columna: " + this.getRey().getCelda().getColumna());
	}

	public Equipo(String nombre, Ajedrez ajedrez) {
		this.nombre = nombre;
		this.ajedrez = ajedrez;
	}
	
	//TODO {CORREGIDO}[CORRECCION] No puede ser un metodo de clase 
	/**
	 * Se ejecuta la jugada.
	 * Los println fueron de nuevo, hechos para ver si se ejecutaba por consola.
	 * @param jugada
	 */
	public void ejecutar(Jugada jugada) {
		System.out.println("La pieza está en la fila "+ jugada.getPieza().getCelda().getFila() + " columna " + jugada.getPieza().getCelda().getColumna());
		jugada.getPieza().moverse(new Celda(jugada.getFila(), jugada.getColumna()));
		System.out.println("La pieza pasó a la fila "+ jugada.getPieza().getCelda().getFila() + " columna " + jugada.getPieza().getCelda().getColumna());
	}

	public Ajedrez getAjedrez() {
		return ajedrez;
	}

	public Rey getRey() {
		return (Rey) this.piezas.get(0);
	}

	public void setPiezas(ArrayList<Pieza> piezas) {
		this.piezas = piezas;
	}
	
	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}

	
	/**
	 * Revisa que cada pieza esté viva y si es así devuelve un arreglo de Celdas a las que se puede mover esta.
	 * Luego, genera una jugada y la guarda en una colleción de jugadas
	 * Cuando tengo la coleccion de jugadas, verifico cual de todas mata a otra pieza.
	 * @return
	 */
	public ArrayList<Jugada> calcularJugadasPosibles() {
		
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
		
		for (Pieza pieza : this.getPiezas()) {
			if(pieza.estaViva()) {	
				ArrayList<Celda> celdasPosibles = pieza.getCeldasPosibles();
				for (Celda celda : celdasPosibles) {
					Jugada jugada = new Jugada(pieza, celda.getFila(), celda.getColumna());
					jugadas.add(jugada);
				}
			}
		}
		return jugadas;
	}
	
	/**
	 * Metodo que me permite calcular cuando el Rey esta en Jaque, este metodo me sirve para luego evaluar el JaqueMate
	 * @return
	 */
	public boolean estaEnJaque() {
		
		Celda posicionRey = this.getRey().getCelda();
		Equipo contrario = this.getAjedrez().getEquipoContrario(this);
		ArrayList<Jugada> jugadasContrarios = contrario.calcularJugadasPosibles();
		
		for (Jugada jugada : jugadasContrarios) {
			int fila = jugada.getFila();
			int columna = jugada.getColumna();
			if (posicionRey == this.getAjedrez().getTablero().getCelda(fila, columna)) {
				System.out.println("Jaque");
				return true;
			}
		}
		 return false;
	}
	
	
	/**
	 * Tomo los movimientos posibles del Rey y los del equipo contrario. Reviso todos los movimientos del Rey e itero
	 * sobre las jugadas del equipo contrario. Si veo que el movimiento que va a hacer el Rey está entre las jugadas
	 * del equipo contrario entonces invalido el posible movimiento del Rey.
	 * Si luego de hacer todos esos chequeos, veo que el Rey no tiene movimientos validos,entonces es JaqueMate.
	 * @return
	 */
	public boolean esJaqueMate() {
		if (!this.getRey().estaViva()) {
			return true;
		}
		if (this.estaEnJaque()) {
			ArrayList<Celda> movimientosPosibles = this.getRey().getCeldasPosibles(); //agarra los movimientos posibles del rey
			Equipo contrario = this.getAjedrez().getEquipoContrario(this);
			ArrayList<Jugada> jugadasContrarios = contrario.calcularJugadasPosibles(); //agarra los movimientos posibles del equipo contrario
			boolean vaBien;
			for (Celda mov : movimientosPosibles) { //revisa cada movimiento del rey
				vaBien = true;
				for (Jugada jugada : jugadasContrarios) { //itera sobre las jugadas del equipo contrario
					int fila = jugada.getFila();
					int columna = jugada.getColumna();
					if (mov == this.getAjedrez().getTablero().getCelda(fila, columna)) { //si encuentra que el movimiento esta entre las jugadas posibles del equipo contrario, entonces ya no es valido
						vaBien = false;
						break;
					}
				}
				if (vaBien) { //si en ningun momento se delclaro el movimiento como no valido, entonces el rey puede salir del jaque
					return false;
				}
			}
			System.out.println("Jaque Mate");
			return true; //si no se encontro movimiento para salir del jaque, es jaque mate
		}
		return false;
	}
}