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
	
	CeldaGUI(Color colorCelda){
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.setBorder(new LineBorder(Color.DARK_GRAY));
		this.setBackground(colorCelda);
		this.setMaximumSize(new Dimension(64, 64));
		this.setMinimumSize(new Dimension(64, 64));
		this.setPreferredSize(new Dimension(40, 40));
		this.setVisible(true);
	}

	public void hardcodearImagen() {
		this.setIcon(new ImageIcon("C:\\Users\\Franco\\eclipse-workspace\\AjedrezV.1\\img\\alfilNegro.png"));
	}
	
	public Icon getImagen() {
		return imagen;
	}

	public void setImagen(Icon icono) {
		this.setIcon(icono);
		this.repaint();
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
