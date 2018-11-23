package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
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
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

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
		CeldaGUI[][] celdas = this.getCeldasGUI();
		System.out.println(celdas);
		for (Pieza pieza : blancas) {
			Celda celda = pieza.getCelda();
			int fila = celda.getFila();
			int columna = celda.getColumna();
			
			this.getCeldaXY(fila, columna).setImagen(pieza.getImagen());;
			this.getCeldaXY(fila, columna).repaint();
			this.repaint();
			
		}
		for (Pieza pieza : negras) {
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
		this.celdasGUI[celdaOrigen.getFila()][celdaOrigen.getColumna()].repaint();
		this.celdasGUI[celdaDestino.getFila()][celdaDestino.getColumna()].repaint();
		this.repaint();
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Iniciar");
		mnIniciarJuego.add(mntmNewMenuItem);
		
		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mnIniciarJuego.add(mntmReiniciar);
		
		JMenuItem mntmFinalizar = new JMenuItem("Finalizar");
		mnIniciarJuego.add(mntmFinalizar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnIniciarJuego.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Integrantes del grupo: De Marco Andrada, Franco Guillermo");
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		
		
		JButton btnNewButton = new JButton("Turno de las Blancas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Turno de las Negras");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnNewButton_1);
		
		JButton btnPiezasComidasBlancas = new JButton("Piezas comidas Blancas: "+ Ajedrez.getPiezasBlancasComidas());
		btnPiezasComidasBlancas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnPiezasComidasBlancas);
		
		JButton btnPiezasComidasNegras = new JButton("Piezas comidas Negras: "+ Ajedrez.getPiezasNegrasComidas());
		btnPiezasComidasNegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnPiezasComidasNegras);
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
	
	/*public void Turno(Equipo equipo){
		if(equipo.getNombre() = "Blancas"){
			this.btnTurnoNegras=
		}
	}*/
}
