package juego;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
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
		int x = jugadas.size();
		int azar = ThreadLocalRandom.current().nextInt(0,x);
		Jugada jugada = jugadas.get(azar);
		System.out.println("jugada: "+jugada);
		System.out.println("Antes de ejecutar: Fila: " +  this.getRey().getCelda().getFila()+" ; Columna: " + this.getRey().getCelda().getColumna());
		this.ejecutar(jugada);
		System.out.println("Despues de ejecutar: Fila: "+ this.getRey().getCelda().getFila()+" ; Columna: " + this.getRey().getCelda().getColumna());
	}

	public Equipo(String nombre, Ajedrez ajedrez) {
		this.nombre = nombre;
		this.ajedrez = ajedrez;
		this.estaEnJaque = false;
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
}