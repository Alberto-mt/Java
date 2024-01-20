package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import controlador.GestionaCargaBBDD;
import controlador.GestionaMantenimientoBBDD;
import modelo.Eficiencia;
import modelo.Marca;
import modelo.Modelo;

/**
 * JPmodeloInsertar - Clase que muestra un panel con un formulario, para
 * rellenar e insertar datos de la clase modelo en la BBDD
 * 
 * @author Alberto
 *
 */
public class JPmodeloInsertar extends JPanel {
	private JComboBox cmbMarca;
	private JTextField txtModelo;
	private JSpinner spnConsumo;
	private JSpinner spnEmisiones;
	private JComboBox cmbEnergetica;
	private JTextField txtResultado;
	private JButton btnBorrar;
	private JButton btnInsertar;

	/* Creo dos objetos para acceder a sus métodos */
	Modelo mod = new Modelo();
	GestionaCargaBBDD db = new GestionaCargaBBDD();
	GestionaMantenimientoBBDD mante = new GestionaMantenimientoBBDD();

	/**
	 * Create the panel.
	 */
	public JPmodeloInsertar() {

		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51), new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51)));
		setBackground(new Color(204, 204, 204));
		setLayout(null);

		// Logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img/logo/logoN.png"));
		lblLogo.setBounds(897, 11, 94, 95);
		add(lblLogo);

		// Titulo
		JLabel lblTitulo = new JLabel("MANTENIMIENTO INTEGRAL-INSERTAR");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("RELLENE EL FORMULARIO DEL VEHICULO PARA INSERTAR LOS DATOS EN LA BBDD");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JComboBox - cmbMarca
		 */
		JLabel lblMarca = new JLabel("1\u00BA - ELIJE LA MARCA:");
		lblMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblMarca.setBounds(10, 110, 452, 14);
		add(lblMarca);

		cmbMarca = new JComboBox();
		cmbMarca.setBackground(new Color(255, 255, 255));
		/* Cargo cmbMarca */
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			cmbMarca.addItem(arrayMarcas.get(i).getNombre());
		}
		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbMarca.getSelectedItem();
				txtModelo.setText(cmbMarca.getSelectedItem() + " ");
			}
		});
		cmbMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbMarca.setBounds(10, 135, 300, 20);
		add(cmbMarca);

		/*
		 * Etiqueta con su JTextField - txtModelo
		 */
		JLabel lblModelo = new JLabel(
				"2\u00BA- RELLENE LOS DATOS DEL MODELO A CONTINUACI\u00D3N DE LA MARCA (BORRAR PARA RELLENAR DE NUEVO):");
		lblModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblModelo.setBounds(10, 166, 836, 14);
		add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		txtModelo.setBounds(10, 191, 600, 20);
		add(txtModelo);
		txtModelo.setColumns(10);

		/*
		 * Etiqueta con su JSpinner - spnConsumo
		 */
		JLabel lblConsumo = new JLabel("3\u00BA- INTRODUZCA EL CONSUMO DEL VEHICULO:");
		lblConsumo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsumo.setBounds(10, 222, 452, 14);
		add(lblConsumo);

		spnConsumo = new JSpinner();
		spnConsumo.setBackground(new Color(255, 255, 255));
		spnConsumo.setFont(new Font("Arial Black", Font.BOLD, 14));
		spnConsumo.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(30), new Float(1)));
		spnConsumo.setBounds(10, 247, 226, 20);
		add(spnConsumo);

		/*
		 * Etiqueta con su JSpinner - spnEmisiones
		 */
		JLabel lblEmisiones = new JLabel("4\u00BA - INTRODUZCA LAS EMISIONES DEL VEHICULO:");
		lblEmisiones.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEmisiones.setBounds(10, 278, 452, 14);
		add(lblEmisiones);

		spnEmisiones = new JSpinner();
		spnEmisiones.setBackground(new Color(255, 255, 255));
		spnEmisiones.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(300), new Float(1)));
		spnEmisiones.setFont(new Font("Arial Black", Font.BOLD, 14));
		spnEmisiones.setBounds(10, 303, 226, 20);
		add(spnEmisiones);

		/*
		 * Etiqueta con su JComboBox - cmbEnergetica
		 */
		JLabel lblEnergetica = new JLabel("5\u00BA - ELIJE LA CLASIFICACI\u00D3N ENERG\u00C9TICA DEL VEHICULO:");
		lblEnergetica.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEnergetica.setBounds(10, 334, 452, 14);
		add(lblEnergetica);

		cmbEnergetica = new JComboBox();
		cmbEnergetica.setBackground(new Color(204, 0, 51));
		cmbEnergetica.setToolTipText("");
		/* Cargo cmbEnergetica */
		ArrayList<Eficiencia> arrayEficiencias = db.cargaEficiencia();
		for (int i = 0; i < arrayEficiencias.size(); i++) {
			cmbEnergetica
					.addItem(new ImageIcon("img/iconosclasificacionenergetica/" + arrayEficiencias.get(i).getIcono()));
		}
		cmbEnergetica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbEnergetica.getSelectedIndex();
			}
		});
		cmbEnergetica.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbEnergetica.setBounds(10, 359, 300, 40);
		add(cmbEnergetica);

		/*
		 * Etiquetas con su JTextField - txtResultado
		 */
		JLabel lblResultado = new JLabel(
				"ULTIMOS DATOS INTRODUCIDOS EN EL FORMULARIO (BORRAR PARA RELLENAR DE NUEVO):");
		lblResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblResultado.setBounds(10, 464, 953, 14);
		add(lblResultado);

		JLabel lblReultado2 = new JLabel(
				"**IMPORTANTE: SI AL INSERTAR NO ACEPTA, NO ESTAR\u00C1N INTRODUCIDOS LOS DATOS EN LA BBDD**");
		lblReultado2.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblReultado2.setBounds(10, 478, 953, 14);
		add(lblReultado2);

		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setBackground(new Color(255, 255, 255));
		txtResultado.setForeground(new Color(0, 0, 0));
		txtResultado.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtResultado.setBounds(10, 503, 997, 38);
		add(txtResultado);
		txtResultado.setColumns(10);

		/*
		 * Etiqueta con su JButton - btnBorrar
		 */
		JLabel lblClear = new JLabel("    BORRAR");
		lblClear.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblClear.setBounds(900, 110, 107, 14);
		add(lblClear);

		btnBorrar = new JButton("");
		btnBorrar.setBackground(new Color(255, 255, 255));
		btnBorrar.setForeground(new Color(0, 0, 0));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtModelo.setText("");
				txtResultado.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon("img/imgForm/FileDelete.png"));
		btnBorrar.setBounds(900, 134, 107, 114);
		add(btnBorrar);

		/*
		 * Etiqueta con su JButton - btnInsertar
		 */
		JLabel lblInsertar = new JLabel("   INSERTAR");
		lblInsertar.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblInsertar.setBounds(900, 259, 107, 14);
		add(lblInsertar);

		btnInsertar = new JButton("");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rellenarFormulario();
				mante.insertarBBDD(mod);
				mostrar(mod);
			}
		});
		btnInsertar.setIcon(new ImageIcon("img/imgForm/a\u00F1adir.png"));
		btnInsertar.setForeground(new Color(0, 0, 0));
		btnInsertar.setBackground(new Color(153, 0, 51));
		btnInsertar.setBounds(900, 284, 107, 114);
		add(btnInsertar);
	}

	/**
	 * obtenerIdMarca() - Método con el obtengo el IdMarca y lo devuelve como un int
	 * 
	 * @return id
	 */
	public int obtenerIdMarca() {
		int id = 0;
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			if ((arrayMarcas.get(i).getNombre()).equalsIgnoreCase((String) cmbMarca.getSelectedItem())) {
				id = arrayMarcas.get(i).getIdMarca();
			}
		}
		return id;
	}

	/**
	 * obtenerCertificacion() - Método con el obtengo el C.Energético y lo devuelve
	 * como un String
	 * 
	 * @return cer
	 */
	public String obtenerCertificacion() {
		String cer = "";
		ArrayList<Eficiencia> arrayEficiencias = db.cargaEficiencia();
		for (int i = 0; i < arrayEficiencias.size(); i++) {
			if (i == cmbEnergetica.getSelectedIndex()) {
				cer = arrayEficiencias.get(i).getcEnergetica();
			}
		}
		return cer;
	}

	/**
	 * rellenarFormulario() - Método con el que relleno un formulario y devuelve un
	 * objeto Modelo
	 * 
	 * @return mod
	 */
	public Modelo rellenarFormulario() {
		mod.setIdMarca(obtenerIdMarca());
		mod.setModelo(txtModelo.getText());
		mod.setConsumo((float) spnConsumo.getValue());
		mod.setEmisiones((float) spnEmisiones.getValue());
		mod.setcEnergetica(obtenerCertificacion());
		return mod;
	}

	/**
	 * mostrar(Modelo mod) - Método que muestra en txtResultado un objeto Modelo que
	 * se le pasa como parámetro
	 * 
	 * @param mod Objeto Modelo
	 */
	public void mostrar(Modelo mod) {
		txtResultado.setText("  IdMarca: " + mod.getIdMarca() + " - " + mod.getModelo() + " - " + mod.getConsumo()
				+ " - " + mod.getEmisiones() + " - " + mod.getcEnergetica());
	}
}
