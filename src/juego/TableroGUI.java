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
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TableroGUI extends JFrame implements IJuegoListener, IPiezaListener {

	private JPanel contentPane;
	private CeldaGUI[][] celdasGUI=new CeldaGUI[8][8];
	private Ajedrez ajedrez;
	private final JLabel label = new JLabel("");
	private JLabel lblPiezasBlancasComidas;
	private JLabel lblMovimientos;
	private JLabel lblTurno;
	private JLabel lblPiezasNegrasComidas;


	//TODO {CORREGIDO}[CORRECCION] Tienen 2 main, dejen uno solo
	public static void Inicializar(Ajedrez ajedrez) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroGUI frame = new TableroGUI(ajedrez);
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
			
			this.getCeldaXY(fila, columna).setImagen(pieza);;
			this.getCeldaXY(fila, columna).repaint();
			this.repaint();
			
		}
		for (Pieza pieza : negras) {
			Celda celda = pieza.getCelda();
			int fila = celda.getFila();
			int columna = celda.getColumna();
			
			this.getCeldaXY(fila, columna).setImagen(pieza);;
			this.getCeldaXY(fila, columna).repaint();
			this.repaint();
			
		}
	}

	@Override
	public void piezaComida(Pieza pieza) {
		//TODO [CORRECCION] No pregunten por el nombre, pregunten por la instancia -> pieza.getEquipo() == ajedrez.getEquipoBlancas()
		if(pieza.getEquipo()==this.ajedrez.getEquipoBlancas()) {
			this.lblPiezasBlancasComidas.setText("Piezas Blancas Comidas: " + this.ajedrez.getPiezasBlancasComidas() +"       ");
			this.lblPiezasBlancasComidas.repaint();
		}else {
			this.lblPiezasNegrasComidas.setText("Piezas Negras Comidas: " + this.ajedrez.getPiezasNegrasComidas() +"       ");
			this.lblPiezasNegrasComidas.repaint();
		}
	}
	
	

	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		this.celdasGUI[celdaOrigen.getFila()][celdaOrigen.getColumna()].setImagen(null);
		this.celdasGUI[celdaDestino.getFila()][celdaDestino.getColumna()].setImagen(pieza);
		this.celdasGUI[celdaOrigen.getFila()][celdaOrigen.getColumna()].repaint();
		this.celdasGUI[celdaDestino.getFila()][celdaDestino.getColumna()].repaint();
		this.repaint();
		this.lblMovimientos.setText("Movimientos: " + this.ajedrez.getMovimientos()+"       ");
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
	public TableroGUI(Ajedrez ajedrez) {
		
		this.ajedrez = ajedrez;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 850);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnIniciarJuego = new JMenu("Menu");
		menuBar.add(mnIniciarJuego);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Iniciar");
		
		mnIniciarJuego.add(mntmNewMenuItem);
		
		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mnIniciarJuego.add(mntmReiniciar);
		
		JMenuItem mntmFinalizar = new JMenuItem("Finalizar");
		mntmFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajedrez.setFinalizado(true);
			}
		});
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
		menuBar.add(label);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new GridLayout(8, 8, 0, 0));
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		this.lblPiezasNegrasComidas = new JLabel("Piezas Negras Comidas: 0       " );
		panel_1.add(lblPiezasNegrasComidas);
		
		this.lblPiezasNegrasComidas.setMaximumSize(new Dimension(10, 10));
		this.lblPiezasNegrasComidas.setMinimumSize(new Dimension(10, 10));
		
		this.lblTurno = new JLabel("Turno:          ");
		panel_1.add(lblTurno);
		
				
				this.lblTurno.setMaximumSize(new Dimension(10, 10));
				this.lblTurno.setMinimumSize(new Dimension(10, 10));
				
				this.lblMovimientos = new JLabel("Movimientos:  0       ");
				panel_1.add(lblMovimientos);
				
				this.lblMovimientos.setBounds(10, 10, 10, 10);
				
				this.lblPiezasBlancasComidas = new JLabel("Piezas Blancas Comidas: 0        ");
				panel_1.add(lblPiezasBlancasComidas);
				lblPiezasBlancasComidas.setHorizontalAlignment(SwingConstants.CENTER);
				
				this.lblPiezasBlancasComidas.setMaximumSize(new Dimension(10, 10));
				this.lblPiezasBlancasComidas.setMinimumSize(new Dimension(10, 10));
		
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
		
		ArrayList<Pieza> piezasBlancas = this.ajedrez.getEquipoBlancas().getPiezas();
		ArrayList<Pieza> piezasNegras = this.ajedrez.getEquipoNegras().getPiezas();
		
		for (Pieza pieza : piezasBlancas) {
			pieza.addPiezaListener(this);
		}
		for (Pieza pieza : piezasNegras) {
			pieza.addPiezaListener(this);
		}
		this.ajedrez.agregarJuegoListener(this);
	}

	@Override
	public void turnoActual(Equipo equipo) {
		if(equipo.getNombre()=="Blancas") {
			this.lblTurno.setText("Turno Blancas       ");
		}else{
			this.lblTurno.setText("Turno Negras       ");
		}
	}

	//TODO {CORREGIDO}[CORRECCION] No se utiliza nunca esto
	
	/*
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
	*/
}
