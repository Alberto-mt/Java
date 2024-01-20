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
import javax.swing.border.BevelBorder;

import controlador.GestionaCargaBBDD;
import controlador.GestionaMantenimientoBBDD;
import modelo.Marca;
import modelo.Modelo;

import javax.swing.JTextField;

/**
 * JPmodeloEliminar - Clase que me permite eliminar un modelo de coche de la
 * BBDD, seleccionando un módelo de coche a partir de su marca
 * 
 * @author Alberto
 *
 */
public class JPmodeloEliminar extends JPanel {
	private JComboBox cmbModelo;
	private JComboBox cmbMarca;
	private JTextField txtResultadoEliminado;

	/* Creo dos objetos para acceder a sus métodos */
	Modelo modElim = new Modelo();
	GestionaCargaBBDD db = new GestionaCargaBBDD();
	GestionaMantenimientoBBDD mante = new GestionaMantenimientoBBDD();

	/**
	 * Create the panel.
	 */
	public JPmodeloEliminar() {

		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51), new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51)));
		setBackground(new Color(204, 204, 204));
		setLayout(null);

		// Logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img/logo/logoN.png"));
		lblLogo.setBounds(897, 11, 94, 95);
		add(lblLogo);

		// Título
		JLabel lblTitulo = new JLabel("MANTENIMIENTO INTEGRAL-ELIMINAR");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Informmación
		JLabel lblInformacion = new JLabel("SELECCIONE EL MODELO A ELIMINAR EN LA BBDD (ACTUALIZAR PRIMERO)");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JComboBox - cmbModelo
		 */
		JLabel lblModelo = new JLabel("2\u00BA- ELIJE EL MODELO:");
		lblModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblModelo.setBounds(10, 150, 452, 14);
		add(lblModelo);

		cmbModelo = new JComboBox();
		cmbModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modelo = (String) cmbModelo.getSelectedItem();
			}
		});
		cmbModelo.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbModelo.setBounds(10, 175, 600, 20);
		add(cmbModelo);

		/*
		 * Etiqueta con su JComboBox - cmbMarca
		 */
		JLabel lblMarca = new JLabel("1\u00BA - ELIJE LA MARCA:");
		lblMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblMarca.setBounds(10, 94, 452, 14);
		add(lblMarca);

		JComboBox cmbMarca = new JComboBox();
		/* Cargo cmbMarca */
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			cmbMarca.addItem(arrayMarcas.get(i).getNombre());
		}
		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = (String) cmbMarca.getSelectedItem();
				/*
				 * Cada vez que elijo una marca, borro el listado de modelos que existía y le
				 * añado el propio
				 */
				txtResultadoEliminado.setText("");
				cmbModelo.removeAllItems();
				/* Cargo cmbModelo */
				ArrayList<Modelo> arrayModelos = db.cargaModeloModificar(marca);
				for (int i = 0; i < arrayModelos.size(); i++) {
					cmbModelo.addItem(arrayModelos.get(i).getModelo());
				}
			}
		});
		cmbMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbMarca.setBounds(10, 119, 300, 20);
		add(cmbMarca);

		// Información
		JLabel lblEliminarPermanentemente = new JLabel("3\u00BA- ELIMINAR PERMANENTEMENTE DE LA BBDD:");
		lblEliminarPermanentemente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEliminarPermanentemente.setBounds(10, 218, 452, 14);
		add(lblEliminarPermanentemente);

		/*
		 * Etiqueta con su JButton - btnBorrar
		 */
		JLabel lblEliminar = new JLabel("   ELIMINAR");
		lblEliminar.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEliminar.setBounds(897, 175, 107, 14);
		add(lblEliminar);

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon("img/imgForm/eliminar.png"));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = modeloAEliminar();
				obtenerModeloEliminado();
				mante.eliminarBBDD(id);
				mostrarElminado(modElim);
			}
		});
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setBackground(new Color(153, 0, 51));
		btnEliminar.setBounds(897, 200, 107, 114);
		add(btnEliminar);

		/*
		 * Etiqueta con su JTextField - txtResultado
		 */
		JLabel lblResultado = new JLabel("ULTIMOS DATOS ELIMINADOS DE LA BBDD:");
		lblResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblResultado.setBounds(10, 341, 953, 14);
		add(lblResultado);

		txtResultadoEliminado = new JTextField();
		txtResultadoEliminado.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtResultadoEliminado.setColumns(10);
		txtResultadoEliminado.setBounds(10, 385, 997, 33);
		add(txtResultadoEliminado);

		JLabel lblimportanteSiAl = new JLabel(
				"**IMPORTANTE: SI AL ELIMINAR NO ACEPTA , NO ESTAR\u00C1N ELIMINADOS LOS DATOS EN LA BBDD**");
		lblimportanteSiAl.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblimportanteSiAl.setBounds(10, 355, 953, 14);
		add(lblimportanteSiAl);
	}

	/**
	 * modeloAEliminar - Método que devuelve el identificador de modelo de coche a
	 * eliminar
	 * 
	 * @return id
	 */
	public int modeloAEliminar() {
		int id = 0;
		ArrayList<Modelo> arrayModelos = db.cargaModelo();
		for (int i = 0; i < arrayModelos.size(); i++) {
			if ((arrayModelos.get(i).getModelo()).equalsIgnoreCase((String) cmbModelo.getSelectedItem())) {
				id = arrayModelos.get(i).getId();
			}
		}
		return id;
	}

	/**
	 * obtenerModeloEliminado() - Método con el que un modelo de coche entero a
	 * partir de un String de módelo
	 * 
	 * @return modElim
	 */
	public Modelo obtenerModeloEliminado() {
		ArrayList<Modelo> arrayModeloElim = db.cargaModelo();
		for (int i = 0; i < arrayModeloElim.size(); i++) {
			if ((arrayModeloElim.get(i).getModelo()).equalsIgnoreCase((String) cmbModelo.getSelectedItem())) {
				modElim.setId(arrayModeloElim.get(i).getId());
				modElim.setIdMarca(arrayModeloElim.get(i).getIdMarca());
				modElim.setModelo(arrayModeloElim.get(i).getModelo());
				modElim.setConsumo(arrayModeloElim.get(i).getConsumo());
				modElim.setEmisiones(arrayModeloElim.get(i).getEmisiones());
				modElim.setcEnergetica(arrayModeloElim.get(i).getcEnergetica());
			}
		}
		return modElim;
	}

	/**
	 * mostrarElminado(Modelo modElim) - Método que muestra en txtResultado un
	 * objeto Modelo que se le pasa como parámetro
	 * 
	 * @param modElim -  Objeto Modelo
	 */
	public void mostrarElminado(Modelo modElim) {
		txtResultadoEliminado.setText("  Id: " + modElim.getId() + " - " + "IdMarca: " + modElim.getIdMarca() + " - "
				+ modElim.getModelo() + " - " + modElim.getConsumo() + " - " + modElim.getEmisiones() + " - "
				+ modElim.getcEnergetica());
	}
}
