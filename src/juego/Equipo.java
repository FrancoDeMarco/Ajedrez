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

	public static void Jugar(Equipo equipo) {
		ArrayList<Jugada> jugadas = equipo.calcularJugadasPosibles();
		int x = jugadas.size();
		int azar = ThreadLocalRandom.current().nextInt(0,x);
		Jugada jugada = jugadas.get(azar); //TODO
		System.out.println("jugada: "+jugada);
		System.out.println("Antes de ejecutar: Fila: " +  equipo.getRey().getCelda().getFila()+" ; Columna: " + equipo.getRey().getCelda().getColumna());
		ejecutar(jugada);
		System.out.println("Despues de ejecutar: Fila: "+ equipo.getRey().getCelda().getFila()+" ; Columna: " + equipo.getRey().getCelda().getColumna());
	}

	public Equipo(String nombre, Ajedrez ajedrez) {
		this.nombre = nombre;
		this.ajedrez = ajedrez;
		this.estaEnJaque = false;
	}
	
	public static void ejecutar(Jugada jugada) {
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

	public ArrayList<Jugada> calcularJugadasPosibles() {
		
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
		
		for (Pieza pieza : this.getPiezas()) {
			if(pieza.estaViva()) {	
				ArrayList<Celda> celdasPosibles = pieza.getCeldasPosibles(); //Devuelve un arreglo de Celdas a las que se puede mover la pieza
				for (Celda celda : celdasPosibles) {
					Jugada jugada = new Jugada(pieza, celda.getFila(), celda.getColumna());
					jugadas.add(jugada);
				}
			}
			//voy a recorrer las piezas y verificar todas las que tienen jugada, si tiene jugada, 
			//genero una jugada y la guardo en la collecion de jugadas. 
			
		}
		//cuando tengo la coleccion de jugadas, verifico cual de todas mata a otra pieza.
		
		
		return jugadas;
	}
}