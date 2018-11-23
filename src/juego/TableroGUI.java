package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Panel;

public class TableroGUI extends JFrame implements IJuegoListener, IPiezaListener {

	private JPanel contentPane;
	private CeldaGUI[][] celdasGUI=new CeldaGUI[8][8];


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroGUI frame = new TableroGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void juegoEmpieza(ArrayList<Pieza> blancas, ArrayList<Pieza> negras) {
		System.out.println("ASDOUJFBOASDFBSAOFBAS");
		CeldaGUI[][] celdas = this.getCeldasGUI();
		for (Pieza pieza : blancas) {
			Celda celda = pieza.getCelda();
			int fila = celda.getFila();
			int columna = celda.getColumna();
			
			this.getCeldaXY(fila, columna).setImagen(pieza.getImagen());;
			this.getCeldaXY(fila, columna).repaint();
			this.repaint();
			
		}
	}

	@Override
	public void piezaComida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {

	}

	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		this.celdasGUI[celdaOrigen.getFila()][celdaOrigen.getColumna()].setImagen(null);
		this.celdasGUI[celdaDestino.getFila()][celdaDestino.getColumna()].setImagen(pieza.getImagen());
	}

	public CeldaGUI[][] getCeldasGUI() {
		return this.celdasGUI;
	}

	public void setCeldasGUI(CeldaGUI[][] celdasGUI) {
		this.celdasGUI = celdasGUI;
	}
	
	public CeldaGUI getCeldaXY(int x, int y) {
		return this.celdasGUI[x][y];
	}

	/**
	 * Create the frame.
	 */
	public TableroGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 810);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnIniciarJuego = new JMenu("Menu");
		menuBar.add(mnIniciarJuego);
		
		JMenu mnIniciarJuego_1 = new JMenu("Iniciar juego");
		mnIniciarJuego.add(mnIniciarJuego_1);
		
		JMenu mnFinalizarJuego = new JMenu("Finalizar Juego");
		mnIniciarJuego.add(mnFinalizarJuego);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new GridLayout(8, 8, 0, 0));
		for(int i=0;i<=7;i++) {
			for(int j=0;j<=7;j++) {
				if(i==0 || i%2==0) {
					if (j==0 || j%2==0) {
						celdasGUI[i][j]= new CeldaGUI(Color.WHITE);
					}
					else {
						celdasGUI[i][j]= new CeldaGUI(Color.GRAY);
					}
				}
				else {
					if (j==0 || j%2==0) {
						celdasGUI[i][j]= new CeldaGUI(Color.GRAY);
					}
					else {
						celdasGUI[i][j]= new CeldaGUI(Color.WHITE);
					}

				}
				celdasGUI[i][j].setFila(i);
				celdasGUI[i][j].setColumna(j);
				celdasGUI[i][j].setVisible(true);
				panel.add(celdasGUI[i][j]);
			}
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
