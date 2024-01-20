package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImagingOpException;
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
import javax.swing.table.TableCellRenderer;

import controlador.GestionaCargaBBDD;
import controlador.GestionaConsultasModelos;
import controlador.GestionaExportarDatos;
import modelo.Eficiencia;
import modelo.Modelo;

/**
 * JPconsultaCEnergetica - Clase que muestra JPanel de consulta de C. Energética
 * 
 * @author Alberto
 *
 */
public class JPconsultaCEnergetica extends JPanel {
	private JComboBox cmbEnergetica;
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
	public JPconsultaCEnergetica() {

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
		JLabel lblTitulo = new JLabel("CONSULTA-CERTIFICACIÓN ENERGÉTICA");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("SELECCIONE LA CERTIFICACI\u00D3N ENERG\u00C9TICA A CONSULTAR ");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 80, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JComboBox - cmbEnergetica
		 */
		JLabel lblEnergetica = new JLabel("1\u00BA - SELECCIONE LA CERTIFICACI\u00D3N:");
		lblEnergetica.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEnergetica.setBounds(10, 117, 300, 14);
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
			public void actionPerformed(ActionEvent arg0) {
				cmbEnergetica.getSelectedIndex();
				cmbEnergetica.getSelectedItem();
			}
		});

		cmbEnergetica.setFont(new Font("Arial Black", Font.BOLD, 12));
		cmbEnergetica.setBounds(351, 119, 300, 32);
		add(cmbEnergetica);

		/*
		 * JScrollPane con su JTable - tableResultado
		 */
		JScrollPane srcResultado = new JScrollPane();
		srcResultado.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51), new Color(204, 0, 51)));
		srcResultado.setBounds(10, 362, 876, 203);
		add(srcResultado);

		tableResultado = new JTable();
		tableResultado.setGridColor(new Color(153, 0, 51));
		tableResultado.setRowSelectionAllowed(false);
		tableResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		srcResultado.setViewportView(tableResultado);
		modeloTabla.setColumnIdentifiers(new Object[] { "MODELO", "CONSUMO", "EMISIONES", "C.ENERGÉTICA" });
		tableResultado.setModel(modeloTabla);

		// Etiqueta de tabla
		lblElegido = new JLabel("");
		lblElegido.setIcon(new ImageIcon("img/iconosclasificacionenergetica/" + obtenerIcono()));
		srcResultado.setRowHeaderView(lblElegido);
		modeloTabla.setRowCount(0);

		// Información
		JLabel lblConsulta = new JLabel("2\u00BA- ELIJE EL TIPO DE CONSULTA:");
		lblConsulta.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsulta.setBounds(10, 178, 287, 14);
		add(lblConsulta);

		/*
		 * Etiqueta con su JButton - btnAscendente
		 */
		JLabel lblAscendente = new JLabel("ASCENDENTE");
		lblAscendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAscendente.setBounds(350, 178, 107, 14);
		add(lblAscendente);

		btnAscendente = new JButton("");
		btnAscendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnAscendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTabla.setRowCount(0);
				String cer = obtenerCertificacion();
				lblElegido.setIcon(new ImageIcon("img/iconosclasificacionenergetica/" + obtenerIcono()));
				for (Modelo m : consul.consultaCEnerAsc(cer)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnAscendente.setForeground(Color.BLACK);
		btnAscendente.setBackground(new Color(153, 0, 51));
		btnAscendente.setBounds(350, 203, 107, 114);
		add(btnAscendente);

		/*
		 * Etiqueta con su JButton - btnDescendente
		 */
		JLabel lblDescendente = new JLabel("DESCENDENTE");
		lblDescendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblDescendente.setBounds(571, 178, 112, 14);
		add(lblDescendente);

		btnDescendente = new JButton("");
		btnDescendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnDescendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTabla.setRowCount(0);
				String cer = obtenerCertificacion();
				lblElegido.setIcon(new ImageIcon("img/iconosclasificacionenergetica/" + obtenerIcono()));
				for (Modelo m : consul.consultaCEnerDesc(cer)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnDescendente.setForeground(Color.BLACK);
		btnDescendente.setBackground(new Color(153, 0, 51));
		btnDescendente.setBounds(571, 203, 107, 114);
		add(btnDescendente);

		/*
		 * JToolBar con botones para exportar tablas a documentos btnExportarXML,
		 * btnExportarSQL, btnExportarXLS
		 */
		JToolBar tlbEsportar = new JToolBar();
		tlbEsportar.setOrientation(SwingConstants.VERTICAL);
		tlbEsportar.setForeground(Color.BLACK);
		tlbEsportar.setFloatable(false);
		tlbEsportar.setBackground(new Color(153, 0, 51));
		tlbEsportar.setBounds(896, 239, 107, 326);
		add(tlbEsportar);

		// Exportar documento XML
		btnExportarXML = new JButton("");
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cer = obtenerCertificacion();
				datos.xmlC_Energetica(cer);
			}
		});
		btnExportarXML.setIcon(new ImageIcon("img/imgForm/xml.png"));
		btnExportarXML.setBackground(new Color(255, 102, 51));
		tlbEsportar.add(btnExportarXML);

		// Exportar documento SQL
		btnExportarSQL = new JButton("");
		btnExportarSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cer = obtenerCertificacion();
				datos.sqlC_Energetica(cer);
			}
		});
		btnExportarSQL.setIcon(new ImageIcon("img/imgForm/sql.png"));
		btnExportarSQL.setBackground(new Color(0, 102, 255));
		tlbEsportar.add(btnExportarSQL);

		// Exportar documento XLS
		btnExportarXLS = new JButton("");
		btnExportarXLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cer = obtenerCertificacion();
				datos.xslCEnergetica(cer);
			}
		});
		btnExportarXLS.setIcon(new ImageIcon("img/imgForm/xls.png"));
		btnExportarXLS.setBackground(new Color(0, 153, 102));
		tlbEsportar.add(btnExportarXLS);

		JLabel lblResultado = new JLabel("DATOS DE CONSULTA (POR TIPO C.EN\u00C9RGETICA Y AGRUPADOS POR MARCA):");
		lblResultado.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblResultado.setBounds(10, 337, 796, 14);
		add(lblResultado);
	}

	/**
	 * obtenerCertificacion() - Método que obtiene el C.Energético y lo devuelve
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
	 * String obtenerIcono() - Método que obtiene el icono seleccionado y lo
	 * devuelve como un String
	 * 
	 * @return icono
	 */
	public String obtenerIcono() {
		String icono = "";
		ArrayList<Eficiencia> arrayEficiencias = db.cargaEficiencia();
		for (int i = 0; i < arrayEficiencias.size(); i++) {
			if (i == cmbEnergetica.getSelectedIndex()) {
				icono = arrayEficiencias.get(i).getIcono();
			}
		}
		return icono;
	}

}
