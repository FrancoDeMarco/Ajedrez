package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class CeldaGUI extends JButton{
	private Icon imagen;
	private int fila;
	private int columna;
	private Ajedrez ajedrez;
	
	//TODO {CORREGIDO}[CORRECCION] Falta nivel de accesibiliadd
	public CeldaGUI(Color colorCelda, Ajedrez ajedrez){
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ajedrez.getUsuarioJuega()) {
					//TODO [CORRECCION] No hacen nada?
					CeldaGUI celdaGUI = ((CeldaGUI) e.getSource());
					int fila = celdaGUI.fila;
					int columna = celdaGUI.columna;
					Celda celdaClickeada = celdaGUI.ajedrez.getTablero().getCelda(fila, columna);
					if (!ajedrez.isFinalizado()) {
						if (ajedrez.getPiezaMoviendose() == null && celdaClickeada.getPieza() != null) {
							if (celdaClickeada.getPieza().getEquipo() == celdaClickeada.getPieza().getEquipo().getAjedrez().getEquipoBlancas()) {
								ajedrez.setPiezaMoviendose(celdaClickeada.getPieza());
							}
						}else {
							if (ajedrez.getPiezaMoviendose() != null &&  celdaClickeada.getPieza() == null) {
								//moverse
								if (ajedrez.getPiezaMoviendose().movimientoValido(celdaClickeada)) {
									ajedrez.getPiezaMoviendose().moverse(celdaClickeada);
									ajedrez.setPiezaMoviendose(null);
									ajedrez.setUsuarioJugo(true);
								}

							}else {
								if (ajedrez.getPiezaMoviendose() != null &&  celdaClickeada.getPieza() != null) {
									Pieza piezaMoviendose = ajedrez.getPiezaMoviendose();
									if (piezaMoviendose.getEquipo() != celdaClickeada.getPieza().getEquipo()) {
										//va a comer
										if (ajedrez.getPiezaMoviendose().movimientoValido(celdaClickeada)) {
											ajedrez.getPiezaMoviendose().moverse(celdaClickeada);
											ajedrez.setPiezaMoviendose(null);
											ajedrez.setUsuarioJugo(true);
										}
									}else {
										ajedrez.setPiezaMoviendose(celdaClickeada.getPieza());
									}
								}
								//comer
							}
						}	
					}
					
				}
				
				
			}
		});
		
		
		this.ajedrez = ajedrez;
		this.setBorder(new LineBorder(Color.DARK_GRAY));
		this.setBackground(colorCelda);
		this.setMaximumSize(new Dimension(10, 10));
		this.setMinimumSize(new Dimension(10, 10));
		this.setPreferredSize(new Dimension(10, 10));
		this.setVisible(true);
	}

	
	
	public Icon getImagen() {
		return imagen;
	}

	public void setImagen(Pieza pieza) {	
		if (pieza != null) {
			String tipoPieza = pieza.toString();
			String [] textoDividido = tipoPieza.split("@");
			textoDividido = textoDividido[0].split("\\.");
			tipoPieza = textoDividido[1];
			this.setIcon(new ImageIcon("img/"+pieza.getEquipo().getNombre()+"/"+tipoPieza+".png"));
			this.repaint();
		}else {
			this.setIcon(null);
		}
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}


}
