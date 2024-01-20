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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import controlador.GestionaCargaBBDD;
import controlador.GestionaConsultasModelos;
import controlador.GestionaExportarDatos;
import controlador.GestionaMantenimientoBBDD;
import modelo.Eficiencia;
import modelo.Marca;
import modelo.Modelo;
import java.awt.Dimension;

/**
 * JPconsultaMarca - Clase que muestra JPanel de consulta de Marca
 * 
 * @author Alberto
 *
 */
public class JPconsultaMarca extends JPanel {
	private JComboBox cmbMarca;
	private JLabel lblElegido;
	private JTable tableResultado;
	private JButton btnAscendente;
	private JButton btnDescendente;
	private JButton btnExportarXML;
	private JButton btnExportarSQL;
	private JButton btnExportarXLS;
	DefaultTableModel modeloTabla = new DefaultTableModel();

	/* Creo objetos para acceder a sus métodos */
	GestionaCargaBBDD db = new GestionaCargaBBDD();
	GestionaConsultasModelos consul = new GestionaConsultasModelos();
	GestionaExportarDatos datos = new GestionaExportarDatos();

	/**
	 * Create the panel.
	 */
	public JPconsultaMarca() {
		setForeground(new Color(0, 0, 0));

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
		JLabel lblTitulo = new JLabel("CONSULTA-MARCA");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("SELECCIONE LA MARCA A CONSULTAR ");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JComboBox - cmbMarca
		 */
		JLabel lblMarca = new JLabel("1\u00BA - ELIJE LA MARCA:");
		lblMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblMarca.setBounds(10, 109, 452, 14);
		add(lblMarca);

		cmbMarca = new JComboBox();
		/* Cargo cmbMarca */
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			cmbMarca.addItem(arrayMarcas.get(i).getNombre());
		}
		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbMarca.getSelectedItem();
			}
		});
		cmbMarca.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbMarca.setBounds(10, 134, 452, 20);
		add(cmbMarca);

		// Información
		JLabel lblConsulta = new JLabel("2\u00BA- ELIJE EL TIPO DE CONSULTA:");
		lblConsulta.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsulta.setBounds(10, 177, 264, 14);
		add(lblConsulta);

		/*
		 * JScrollPane con su JTable - tableResultado
		 */
		JScrollPane scrResultado = new JScrollPane();
		scrResultado.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51), new Color(204, 0, 51)));
		scrResultado.setBounds(10, 362, 877, 203);
		add(scrResultado);

		tableResultado = new JTable();
		tableResultado.setGridColor(new Color(153, 0, 51));
		tableResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		scrResultado.setViewportView(tableResultado);
		modeloTabla.setColumnIdentifiers(new Object[] { "MODELO", "CONSUMO", "EMISIONES", "C.ENERGÉTICA" });
		tableResultado.setModel(modeloTabla);

		// Etiqueta de tabla
		lblElegido = new JLabel("   ");
		lblElegido.setIcon(new ImageIcon("img/iconosmarcas/" + obtenerId() + ".gif"));
		scrResultado.setRowHeaderView(lblElegido);
		modeloTabla.setRowCount(0);

		/*
		 * Etiqueta con su JButton - btnAscendente
		 */
		JLabel lblAscendente = new JLabel("ASCENDENTE");
		lblAscendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAscendente.setBounds(350, 177, 107, 14);
		add(lblAscendente);

		btnAscendente = new JButton("");
		btnAscendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableResultado.setModel(modeloTabla);
				modeloTabla.setRowCount(0);
				String marca = (String) cmbMarca.getSelectedItem();
				lblElegido.setIcon(new ImageIcon("img/iconosmarcas/" + obtenerId() + ".gif"));
				for (Modelo m : consul.consultaMarcAsc(marca)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnAscendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnAscendente.setForeground(Color.BLACK);
		btnAscendente.setBackground(new Color(153, 0, 51));
		btnAscendente.setBounds(350, 202, 107, 114);
		add(btnAscendente);

		/*
		 * Etiqueta con su JButton - btnDescendente
		 */
		JLabel lblDescendente = new JLabel("DESCENDENTE");
		lblDescendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblDescendente.setBounds(571, 177, 112, 14);
		add(lblDescendente);

		btnDescendente = new JButton("");
		btnDescendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableResultado.setModel(modeloTabla);
				modeloTabla.setRowCount(0);
				String marca = (String) cmbMarca.getSelectedItem();
				lblElegido.setIcon(new ImageIcon("img/iconosmarcas/" + obtenerId() + ".gif"));
				for (Modelo m : consul.consultaMarcDes(marca)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnDescendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnDescendente.setForeground(Color.BLACK);
		btnDescendente.setBackground(new Color(153, 0, 51));
		btnDescendente.setBounds(571, 202, 107, 114);
		add(btnDescendente);

		/*
		 * JToolBar con botones para exportar tablas a documentos btnExportarXML,
		 * btnExportarSQL, btnExportarXLS
		 */
		JToolBar tlbExportar = new JToolBar();
		tlbExportar.setFloatable(false);
		tlbExportar.setOrientation(SwingConstants.VERTICAL);
		tlbExportar.setBackground(new Color(153, 0, 51));
		tlbExportar.setForeground(new Color(0, 0, 0));
		tlbExportar.setBounds(897, 239, 107, 326);
		add(tlbExportar);

		// Exportar documento XML
		btnExportarXML = new JButton("");
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = (String) cmbMarca.getSelectedItem();
				datos.xmlMarcas(marca);
			}
		});
		btnExportarXML.setBackground(new Color(255, 102, 51));
		btnExportarXML.setIcon(new ImageIcon("img/imgForm/xml.png"));
		tlbExportar.add(btnExportarXML);

		// Exportar documento SQL
		btnExportarSQL = new JButton("");
		btnExportarSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = (String) cmbMarca.getSelectedItem();
				datos.sqlMarcas(marca);
			}
		});

		btnExportarSQL.setBackground(new Color(0, 102, 255));
		btnExportarSQL.setIcon(new ImageIcon("img/imgForm/sql.png"));
		tlbExportar.add(btnExportarSQL);

		// Exportar documento XLS
		JButton btnExportarXLS = new JButton("");
		btnExportarXLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = (String) cmbMarca.getSelectedItem();
				datos.xslMarcas(marca);
			}
		});
		btnExportarXLS.setBackground(new Color(0, 153, 102));
		btnExportarXLS.setIcon(new ImageIcon("img/imgForm/xls.png"));
		tlbExportar.add(btnExportarXLS);
	}

	/**
	 *Método que obtiene el id de una marca selecionada y lo devuelve
	 * como entero
	 * 
	 * @return id
	 */
	public int obtenerId() {
		int id = 0;
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			if (arrayMarcas.get(i).getNombre().equalsIgnoreCase((String) cmbMarca.getSelectedItem())) {
				id = arrayMarcas.get(i).getIdMarca();
			}
		}
		return id;
	}
}
