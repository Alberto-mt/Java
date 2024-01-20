package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 * JFprograma - Clase que muestra un JFrame, con un menú y una barra de
 * herramientas, que permite elegir entre los diferentes paneles, mediante el
 * método cambiaPanel()
 * 
 * @author Alberto
 *
 */
public class JFprograma extends JFrame {
	/*
	 * Contenedor de Paneles
	 */
	private JPanel contentPane;
	/*
	 * Declaro los JPanel que asignare a cada JMenuItem
	 */
	JPprincipal principal = new JPprincipal();
	JPmodeloInsertar insertar = new JPmodeloInsertar();
	JPmodeloModificar modificar = new JPmodeloModificar();
	JPmodeloEliminar eliminar = new JPmodeloEliminar();
	JPconsultaMarca marca = new JPconsultaMarca();
	JPconsultaConsumo consumo = new JPconsultaConsumo();
	JPconsultaEmisiones emisiones = new JPconsultaEmisiones();
	JPconsultaCEnergetica cEnergetica = new JPconsultaCEnergetica();
	JPcreditos creditos = new JPcreditos();

	/**
	 * Launch the application.
	 * @param frame - Ventana
	 */
	public static void cargaVentana(JFprograma frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFprograma() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo/logo1.png"));
		setForeground(Color.WHITE);
		setTitle("MOTOR 2020");
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		/* Establecer Jframe a pantalla completa al iniciar */
		this.setExtendedState(MAXIMIZED_BOTH);

		/*
		 * Barra de menú superior, donde se alojan las opciones de menú de primer nivel.
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnHome = new JMenu("HOME");
		mnHome.setFont(new Font("Arial Black", Font.BOLD, 16));
		mnHome.setBackground(Color.WHITE);
		menuBar.add(mnHome);

		// Opción de 2º nivel
		JMenuItem mntmPrincipal = new JMenuItem("PRINCIPAL");
		mntmPrincipal.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiaPanel(principal);
			}
		});
		mnHome.add(mntmPrincipal);

		/*
		 * Opción de 1º nivel, con 3 JPanel: insertar, modificar y borrar
		 */
		JMenu mnMantenimiento = new JMenu("MANTENIMIENTO");
		mnMantenimiento.setFont(new Font("Arial Black", Font.BOLD, 16));
		mnMantenimiento.setBackground(Color.WHITE);
		menuBar.add(mnMantenimiento);

		// Opción de 2º nivel
		JMenuItem mntmInsertarEnBbdd = new JMenuItem("INSERTAR EN BBDD");
		mntmInsertarEnBbdd.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmInsertarEnBbdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(insertar);
			}
		});
		mnMantenimiento.add(mntmInsertarEnBbdd);

		// Opción de 2º nivel
		JMenuItem mntmModificarEnBbdd = new JMenuItem("MODIFICAR EN BBDD");
		mntmModificarEnBbdd.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmModificarEnBbdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(modificar);
			}
		});
		mnMantenimiento.add(mntmModificarEnBbdd);

		// Opción de 2º nivel
		JMenuItem mntmEliminarEnBbdd = new JMenuItem("ELIMINAR EN BBDD");
		mntmEliminarEnBbdd.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmEliminarEnBbdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(eliminar);
			}
		});
		mnMantenimiento.add(mntmEliminarEnBbdd);

		/*
		 * Opción de 1º nivel, con 4 JPanel: marca, consumo, emisiones y cEnergetica
		 */
		JMenu mnConsultar = new JMenu("CONSULTAR");
		mnConsultar.setFont(new Font("Arial Black", Font.BOLD, 16));
		mnConsultar.setBackground(Color.WHITE);
		menuBar.add(mnConsultar);

		// Opción de 2º nivel
		JMenuItem mntmMarca = new JMenuItem("MARCA");
		mntmMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(marca);
			}
		});
		mnConsultar.add(mntmMarca);

		// Opción de 2º nivel
		JMenuItem mntmConsumo = new JMenuItem("CONSUMO");
		mntmConsumo.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(consumo);
			}
		});
		mnConsultar.add(mntmConsumo);

		// Opción de 2º nivel
		JMenuItem mntmEmisiones = new JMenuItem("EMISIONES");
		mntmEmisiones.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmEmisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(emisiones);
			}
		});
		mnConsultar.add(mntmEmisiones);

		// Opción de 2º nivel
		JMenuItem mntmClasificacinEnergtica = new JMenuItem("CLASIFICACION ENERGETICA");
		mntmClasificacinEnergtica.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmClasificacinEnergtica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(cEnergetica);
			}
		});
		mnConsultar.add(mntmClasificacinEnergtica);

		/*
		 * Opción de 1º nivel, con 1 JPanel: creditos
		 */
		JMenu mnAyuda = new JMenu("AYUDA");
		mnAyuda.setFont(new Font("Arial Black", Font.BOLD, 16));
		mnAyuda.setBackground(Color.WHITE);
		menuBar.add(mnAyuda);

		// Opción de 2º nivel
		JMenuItem mntmCreditos = new JMenuItem("CREDITOS");
		mntmCreditos.setFont(new Font("Arial Black", Font.BOLD, 12));
		mntmCreditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(creditos);
			}
		});
		mnAyuda.add(mntmCreditos);

		/*
		 * JToolbar - Barra de herramientas del programa con acceso directo mediante
		 * botones a los diferentes JPanels.
		 */
		JToolBar tbBarraHerramientas = new JToolBar();
		tbBarraHerramientas.setEnabled(false);
		menuBar.add(tbBarraHerramientas);

		/*
		 * Acceso directo a panel principal.
		 */
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiaPanel(principal);
			}
		});
		btnHome.setToolTipText("HOME");
		btnHome.setIcon(new ImageIcon("img/img JToolBar/home.png"));
		tbBarraHerramientas.add(btnHome);

		/*
		 * Acceso directo a panel insertar.
		 */
		JButton btnInsertar = new JButton("");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(insertar);
			}
		});
		btnInsertar.setToolTipText("INSERTAR EN BBDD");
		btnInsertar.setIcon(new ImageIcon("img/img JToolBar/edit.png"));
		tbBarraHerramientas.add(btnInsertar);

		/*
		 * Acceso directo a panel modificar.
		 */
		JButton btnModificar = new JButton("");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(modificar);
			}
		});
		btnModificar.setToolTipText("MODIFICAR EN BBDD");
		btnModificar.setIcon(new ImageIcon("img/img JToolBar/settings.png"));
		tbBarraHerramientas.add(btnModificar);

		/*
		 * Acceso directo a panel eliminar.
		 */
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(eliminar);
			}
		});
		btnEliminar.setToolTipText("ELIMINAR EN BBDD");
		btnEliminar.setIcon(new ImageIcon("img/img JToolBar/remove.png"));
		tbBarraHerramientas.add(btnEliminar);

		/*
		 * Acceso directo a panel marca.
		 */
		JButton btnBMarca = new JButton("");
		btnBMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(marca);
			}
		});
		btnBMarca.setToolTipText("CONSULTAR MARCA");
		btnBMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnBMarca.setIcon(new ImageIcon("img/img JToolBar/search.png"));
		tbBarraHerramientas.add(btnBMarca);

		/*
		 * Acceso directo a panel consumo.
		 */
		JButton btnbConsumo = new JButton("");
		btnbConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(consumo);
			}
		});
		btnbConsumo.setToolTipText("CONSULTAR CONSUMO");
		btnbConsumo.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnbConsumo.setIcon(new ImageIcon("img/img JToolBar/search.png"));

		/*
		 * Acceso directo a panel emisiones.
		 */
		tbBarraHerramientas.add(btnbConsumo);
		JButton btnEmisiones = new JButton("");
		btnEmisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(emisiones);
			}
		});
		btnEmisiones.setToolTipText("CONSULTAR EMISIONES");
		btnEmisiones.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnEmisiones.setIcon(new ImageIcon("img/img JToolBar/search.png"));
		tbBarraHerramientas.add(btnEmisiones);

		/*
		 * Acceso directo a panel cEnergetica.
		 */
		JButton btnEnergetica = new JButton("");
		btnEnergetica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(cEnergetica);
			}
		});
		btnEnergetica.setToolTipText("CONSULTAR CERTIFICACI\u00D3N ENERG\u00C9TICA");
		btnEnergetica.setIcon(new ImageIcon("img/img JToolBar/search.png"));
		btnEnergetica.setFont(new Font("Arial Black", Font.BOLD, 12));
		tbBarraHerramientas.add(btnEnergetica);

		/*
		 * Acceso directo a panel creditos.
		 */
		JButton btnAyuda = new JButton("");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaPanel(creditos);
			}
		});
		btnAyuda.setToolTipText("CREDITOS");
		btnAyuda.setIcon(new ImageIcon("img/img JToolBar/help.png"));
		btnAyuda.setFont(new Font("Arial Black", Font.BOLD, 12));
		tbBarraHerramientas.add(btnAyuda);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/*
		 * Panel principal es el primero por defecto
		 */
		cambiaPanel(principal);
		principal.setLayout(new BorderLayout(0, 0));
	}

	/**
	 * Metodo que cambia paneles en un contenedor.
	 * 
	 * @param panel - Nombre del JPanel al que se cambia.
	 */
	public void cambiaPanel(JPanel panel) {
		contentPane.removeAll();
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(panel);
		contentPane.repaint();
		contentPane.revalidate();
	}
}
