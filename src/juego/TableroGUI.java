package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
		ArrayList<Pieza> piezasBlancas = this.ajedrez.getEquipoBlancas().getPiezas();
		ArrayList<Pieza> piezasNegras = this.ajedrez.getEquipoNegras().getPiezas();
		
		for (Pieza pieza : piezasBlancas) {
			pieza.addPiezaListener(this);
		}
		for (Pieza pieza : piezasNegras) {
			pieza.addPiezaListener(this);
		}
		
		for (CeldaGUI[] arregloCeldas : this.celdasGUI) {
			for (CeldaGUI celda : arregloCeldas) {
				celda.setIcon(null);	//limpia todo el tablero gráfico
				celda.repaint();
			}
		}
		
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
		this.ajedrez.agregarJuegoListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 850);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnIniciarJuego = new JMenu("Menu");
		menuBar.add(mnIniciarJuego);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Iniciar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mnIniciarJuego.getItem(0).setEnabled(false);
				mnIniciarJuego.getItem(2).setEnabled(true);
				mnIniciarJuego.getItem(1).setEnabled(true);
				Object[] options = {"PC vs PC",
                "Jugador vs PC Ofensiva", "Jugador vs PC Master"};
					Component frame = null;
					int n = JOptionPane.showOptionDialog(frame,
					"Elija un modo de juego",
					"Iniciar Juego",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
				if (n == 0) {
					ajedrez.setIniciado(true);
					ajedrez.setUsuarioJuega(false);
				}else {
					if (n == 2) {
						ajedrez.setDificultadMaestra(true);
					}else {
						ajedrez.setDificultadMaestra(false);
					}
					ajedrez.setIniciado(true);
					ajedrez.setUsuarioJuega(true);
				}
			}
		});
		
		mnIniciarJuego.add(mntmNewMenuItem);
		
		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mntmReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajedrez.setFinalizado(true);
				ajedrez.setUsuarioJugo(true);
				Object[] options = {"PC vs PC",
                "Jugador vs PC Ofensiva", "Jugador vs PC Master"};
					Component frame = null;
					int n = JOptionPane.showOptionDialog(frame,
					"Elija un modo de juego",
					"Iniciar Juego",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
				if (n == 0) {
					ajedrez.setUsuarioJuega(false);
				}else {
					if (n == 2) {
						ajedrez.setDificultadMaestra(true);
					}else {
						ajedrez.setDificultadMaestra(false);
					}
					ajedrez.setUsuarioJuega(true);
				}

				try {
					Thread.sleep(300); //Sleep para que los elementos gráficos carguen correctamente
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ajedrez.setReiniciado(true);
			}
		});
		
		mntmReiniciar.setEnabled(false);
		mnIniciarJuego.add(mntmReiniciar);
		
		JMenuItem mntmFinalizar = new JMenuItem("Finalizar");
		mntmFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mnIniciarJuego.getItem(2).setEnabled(false);
				mnIniciarJuego.getItem(0).setEnabled(true);
				mnIniciarJuego.getItem(1).setEnabled(false);
				ajedrez.setFinalizado(true);
				ajedrez.setUsuarioJugo(true);
			}
		});
		mntmFinalizar.setEnabled(false);
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
						celdasGUI[i][j]= new CeldaGUI(Color.WHITE, ajedrez);
					}
					else {
						celdasGUI[i][j]= new CeldaGUI(Color.GRAY, ajedrez);
					}
				}
				else {
					if (j==0 || j%2==0) {
						celdasGUI[i][j]= new CeldaGUI(Color.GRAY, ajedrez);
					}
					else {
						celdasGUI[i][j]= new CeldaGUI(Color.WHITE, ajedrez);
					}

				}
				celdasGUI[i][j].setFila(i);
				celdasGUI[i][j].setColumna(j);
				celdasGUI[i][j].setVisible(true);
				panel.add(celdasGUI[i][j]);
			}
		}
		System.out.println("FIN creacion tablero");
	}

	@Override
	public void turnoActual(Equipo equipo) {
		if(equipo.getNombre()=="Blancas") {
			this.lblTurno.setText("Turno Blancas       ");
		}else{
			this.lblTurno.setText("Turno Negras       ");
		}
	}


	@Override
	public void juegoTermina(Equipo ganador) {
		if (ganador != null) {
			JOptionPane.showMessageDialog(null, "Juego terminado. El ganador es el equipo de las: "+ ganador.getNombre());
			
		}else {
			JOptionPane.showMessageDialog(null, "Juego terminado.");
		}
		
	}

	
	
	//TODO {CORREGIDO}[CORRECCION] No se utiliza nunca esto
}
