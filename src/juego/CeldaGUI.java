package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class CeldaGUI extends JButton{
	private Icon imagen;
	private int fila;
	private int columna;
	
	//TODO [CORRECCION] Falta nivel de accesibiliadd
	CeldaGUI(Color colorCelda){
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO [CORRECCION] No hacen nada?
			}
		});
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
