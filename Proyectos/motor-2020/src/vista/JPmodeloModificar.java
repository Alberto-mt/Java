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
import javax.swing.border.BevelBorder;

import controlador.GestionaCargaBBDD;
import controlador.GestionaMantenimientoBBDD;
import modelo.Eficiencia;
import modelo.Marca;
import modelo.Modelo;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;

/**
 * JPmodeloModificar - Clase que muestra un panel, que permite seleccionar un
 * modelo de coche a partir de su marca, a continuación muestra un formulario,
 * para rellenar y modificar los datos de la clase modelo en la BBDD
 * 
 * @author Alberto
 *
 */
public class JPmodeloModificar extends JPanel {
	private JComboBox cmbModelo;
	private JComboBox cmbMarca;
	private JComboBox cmbMarcaNueva;
	private JTextField txtModeloNuevo;
	private JSpinner spnConsumoNuevo;
	private JSpinner spnEmisionesNuevo;
	private JComboBox cmbEnergeticaNuevo;
	private JTextField txtResultadoNuevo;

	/* Creo dos objetos para acceder a sus métodos */
	Modelo modMdf = new Modelo();
	GestionaCargaBBDD db = new GestionaCargaBBDD();
	GestionaMantenimientoBBDD mante = new GestionaMantenimientoBBDD();

	/**
	 * Create the panel.
	 */
	public JPmodeloModificar() {

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
		JLabel lblTitulo = new JLabel("MANTENIMIENTO INTEGRAL-MODIFICAR");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("SELECCIONE EL MODELO A MODIFICAR EN LA BBDD ");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JComboBox - cmbModelo
		 */
		JLabel lblModelo = new JLabel("2\u00BA- ELIJE EL MODELO:");
		lblModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblModelo.setBounds(10, 119, 452, 14);
		add(lblModelo);

		cmbModelo = new JComboBox();
		cmbModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbModelo.getSelectedItem();
			}
		});
		cmbModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbModelo.setBounds(10, 144, 600, 20);
		add(cmbModelo);

		/*
		 * Etiqueta con su JComboBox - cmbMarca
		 */
		JLabel lblMarca = new JLabel("1\u00BA - ELIJE LA MARCA:");
		lblMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblMarca.setBounds(10, 94, 226, 14);
		add(lblMarca);

		cmbMarca = new JComboBox();
		/* Cargo cmbMarca */
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			cmbMarca.addItem(arrayMarcas.get(i).getNombre());
		}
		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String marca = (String) cmbMarca.getSelectedItem();
				/*
				 * Cada vez que elijo una marca, borro el listado de modelos que existía y le
				 * añado el propio
				 */
				cmbModelo.removeAllItems();
				/* Cargo cmbModelo */
				ArrayList<Modelo> arrayModelos = db.cargaModeloModificar(marca);
				for (int i = 0; i < arrayModelos.size(); i++) {
					cmbModelo.addItem(arrayModelos.get(i).getModelo());
				}
			}
		});
		cmbMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbMarca.setBounds(310, 94, 300, 20);
		add(cmbMarca);

		/*
		 * Información - Una vez seleccionado el modelo, relleno formulario de
		 * modificaciones
		 */
		JLabel lblInformacion2 = new JLabel("RELLENE EL FORMULARIO DEL VEHICULO PARA MODIFICAR LOS DATOS EN LA BBDD");
		lblInformacion2.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion2.setBounds(10, 175, 796, 14);
		add(lblInformacion2);

		/*
		 * Etiqueta con su JComboBox - cmbMarcaNueva
		 */
		JLabel lblMarcaNueva = new JLabel("1\u00BA - ELIJE LA MARCA:");
		lblMarcaNueva.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblMarcaNueva.setBounds(10, 198, 452, 14);
		add(lblMarcaNueva);

		cmbMarcaNueva = new JComboBox();
		/* Cargo cmbMarca */
		ArrayList<Marca> arrayMarcaNueva = db.cargaMarca();
		for (int i = 0; i < arrayMarcaNueva.size(); i++) {
			cmbMarcaNueva.addItem(arrayMarcaNueva.get(i).getNombre());
		}
		cmbMarcaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbMarcaNueva.getSelectedItem();
				txtModeloNuevo.setText(cmbMarcaNueva.getSelectedItem() + " ");
			}
		});
		cmbMarcaNueva.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbMarcaNueva.setBounds(10, 223, 300, 20);
		add(cmbMarcaNueva);

		/*
		 * Etiqueta con su JTextField - txtModeloNuevo
		 */
		JLabel lblModeloNuevo = new JLabel("2\u00BA- RELLENE LOS DATOS DEL MODELO A CONTINUACI\u00D3N DE LA MARCA"
				+ " (BORRAR PARA RELLENAR DE NUEVO):");
		lblModeloNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblModeloNuevo.setBounds(10, 254, 849, 14);
		add(lblModeloNuevo);

		txtModeloNuevo = new JTextField();
		txtModeloNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		txtModeloNuevo.setColumns(10);
		txtModeloNuevo.setBounds(10, 279, 600, 20);
		add(txtModeloNuevo);

		/*
		 * Etiqueta con su JSpinner - spnConsumoNuevo
		 */
		JLabel lblConsumoNuevo = new JLabel("3\u00BA- INTRODUZCA EL CONSUMO DEL VEHICULO:");
		lblConsumoNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsumoNuevo.setBounds(10, 310, 452, 14);
		add(lblConsumoNuevo);

		spnConsumoNuevo = new JSpinner();
		spnConsumoNuevo.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(30), new Float(1)));
		spnConsumoNuevo.setBackground(new Color(255, 255, 255));
		spnConsumoNuevo.setFont(new Font("Arial Black", Font.BOLD, 14));
		spnConsumoNuevo.setBounds(10, 335, 226, 20);
		add(spnConsumoNuevo);

		/*
		 * Etiqueta con su JSpinner - spnEmisionesNuevo
		 */
		JLabel lblEmisionesNuevo = new JLabel("4\u00BA - INTRODUZCA LAS EMISIONES DEL VEHICULO:");
		lblEmisionesNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEmisionesNuevo.setBounds(10, 366, 452, 14);
		add(lblEmisionesNuevo);

		spnEmisionesNuevo = new JSpinner();
		spnEmisionesNuevo.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(300), new Float(1)));
		spnEmisionesNuevo.setFont(new Font("Arial Black", Font.BOLD, 14));
		spnEmisionesNuevo.setBounds(10, 391, 226, 20);
		add(spnEmisionesNuevo);

		/*
		 * Etiqueta con su JComboBox - cmbEnergeticaNuevo
		 */
		JLabel lblEnergeticaNuevo = new JLabel("5\u00BA - ELIJE LA CLASIFICACI\u00D3N ENERG\u00C9TICA DEL VEHICULO:");
		lblEnergeticaNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEnergeticaNuevo.setBounds(10, 422, 452, 14);
		add(lblEnergeticaNuevo);

		cmbEnergeticaNuevo = new JComboBox();
		cmbEnergeticaNuevo.setBackground(new Color(204, 0, 51));
		/* Cargo cmbEnergetica */
		ArrayList<Eficiencia> arrayEficiencias = db.cargaEficiencia();
		for (int i = 0; i < arrayEficiencias.size(); i++) {
			cmbEnergeticaNuevo
					.addItem(new ImageIcon("img/iconosclasificacionenergetica/" + arrayEficiencias.get(i).getIcono()));
		}
		cmbEnergeticaNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbEnergeticaNuevo.getSelectedIndex();
			}
		});
		cmbEnergeticaNuevo.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbEnergeticaNuevo.setBounds(10, 447, 300, 40);
		add(cmbEnergeticaNuevo);

		/*
		 * Etiqueta con su JTextField - txtResultado
		 */
		JLabel lblResultado = new JLabel(
				"ULTIMOS DATOS INTRODUCIDOS EN EL FORMULARIO (BORRAR PARA RELLENAR DE NUEVO):");
		lblResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblResultado.setBounds(10, 498, 849, 14);
		add(lblResultado);

		JLabel label = new JLabel(
				"**IMPORTANTE: SI AL INSERTAR NO ACEPTA, NO ESTAR\u00C1N INTRODUCIDOS LOS DATOS EN LA BBDD**");
		label.setFont(new Font("Arial Black", Font.BOLD, 12));
		label.setBounds(10, 514, 953, 14);
		add(label);

		txtResultadoNuevo = new JTextField();
		txtResultadoNuevo.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtResultadoNuevo.setColumns(10);
		txtResultadoNuevo.setBounds(10, 539, 997, 33);
		add(txtResultadoNuevo);

		/*
		 * Etiqueta con su JButton - btnBorrar
		 */
		JLabel lblBorrar = new JLabel("    BORRAR");
		lblBorrar.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblBorrar.setBounds(900, 194, 107, 14);
		add(lblBorrar);

		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtModeloNuevo.setText("");
				txtResultadoNuevo.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon("img/imgForm/fileDelete.png"));
		btnBorrar.setForeground(Color.BLACK);
		btnBorrar.setBackground(Color.WHITE);
		btnBorrar.setBounds(900, 218, 107, 114);
		add(btnBorrar);

		/*
		 * Etiqueta con su JButton - btnModificar
		 */
		JLabel lblModificar = new JLabel("   MODIFICAR");
		lblModificar.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblModificar.setBounds(900, 343, 107, 14);
		add(lblModificar);

		JButton btnModificar = new JButton("");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = modeloAModificar();
				rellenarFormularioModificado();
				mante.modificarBBDD(id, modMdf);
				mostrarModificado(modMdf);
			}
		});
		btnModificar.setIcon(new ImageIcon("img/imgForm/modificar.png"));
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setBackground(new Color(153, 0, 51));
		btnModificar.setBounds(900, 368, 107, 114);
		add(btnModificar);
	}

	/**
	 * obtenerIdMarcaModificado() - Método con el obtengo el IdMarca y lo devuelve
	 * como un int
	 * 
	 * @return idMdf
	 */
	public int obtenerIdMarcaModificado() {
		int idMdf = 0;
		ArrayList<Marca> arrayMarcasMdf = db.cargaMarca();
		for (int i = 0; i < arrayMarcasMdf.size(); i++) {
			if ((arrayMarcasMdf.get(i).getNombre()).equalsIgnoreCase((String) cmbMarcaNueva.getSelectedItem())) {
				idMdf = arrayMarcasMdf.get(i).getIdMarca();
			}
		}
		return idMdf;
	}

	/**
	 * obtenerCertificacionModificado() - Método con el obtengo el C.Energético y lo
	 * devuelve como un String
	 * 
	 * @return cerMdf
	 */
	public String obtenerCertificacionModificado() {
		String cerMdf = "";
		ArrayList<Eficiencia> arrayEficienciasMdf = db.cargaEficiencia();
		for (int i = 0; i < arrayEficienciasMdf.size(); i++) {
			if (i == cmbEnergeticaNuevo.getSelectedIndex()) {
				cerMdf = arrayEficienciasMdf.get(i).getcEnergetica();
			}
		}
		return cerMdf;
	}

	/**
	 * rellenarFormularioModificado() - Método con el que relleno un formulario y
	 * devuelve un objeto Modelo
	 * 
	 * @return modMdf
	 */
	public Modelo rellenarFormularioModificado() {
		modMdf.setIdMarca(obtenerIdMarcaModificado());
		modMdf.setModelo(txtModeloNuevo.getText());
		modMdf.setConsumo((float) spnConsumoNuevo.getValue());
		modMdf.setEmisiones((float) spnEmisionesNuevo.getValue());
		modMdf.setcEnergetica(obtenerCertificacionModificado());
		return modMdf;
	}

	/**
	 * mostrarModificado(Modelo mod) - Método que muestra en txtResultado un objeto
	 * Modelo que se le pasa como parámetro
	 * 
	 * @param modMdf Objeto Modelo
	 */
	public void mostrarModificado(Modelo modMdf) {
		txtResultadoNuevo.setText("  IdMarca: " + modMdf.getIdMarca() + " - " + modMdf.getModelo() + " - "
				+ modMdf.getConsumo() + " - " + modMdf.getEmisiones() + " - " + modMdf.getcEnergetica());
	}

	/**
	 * modeloAModificar - Método que devuelve el id de un modelo de coche a
	 * modificar
	 * 
	 * @return id
	 */
	public int modeloAModificar() {
		int id = 0;
		ArrayList<Modelo> arrayModelos = db.cargaModelo();
		for (int i = 0; i < arrayModelos.size(); i++) {
			if ((arrayModelos.get(i).getModelo()).equalsIgnoreCase((String) cmbModelo.getSelectedItem())) {
				id = arrayModelos.get(i).getId();
			}
		}
		return id;
	}
}
