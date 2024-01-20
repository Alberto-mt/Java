package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import controlador.GestionaCargaBBDD;
import controlador.GestionaConsultasModelos;
import controlador.GestionaExportarDatos;
import modelo.Modelo;

/**
 * JPconsultaConsumo - Clase que muestra JPanel de consulta de Consumo
 * 
 * @author Alberto
 *
 */
public class JPconsultaConsumo extends JPanel {
	private JSlider sldConsumo;
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
	public JPconsultaConsumo() {

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
		JLabel lblTitulo = new JLabel("CONSULTA-CONSUMO");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("SELECCIONE EL CONSUMO A CONSULTAR ");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JSlider - sldConsumo
		 */
		JLabel lblConsumo = new JLabel("1\u00BA - SELECCIONE EL CONSUMO:");
		lblConsumo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsumo.setBounds(10, 105, 299, 14);
		add(lblConsumo);

		sldConsumo = new JSlider();
		sldConsumo.setValue(0);
		sldConsumo.setMinorTickSpacing(1);
		sldConsumo.setPaintTicks(true);
		sldConsumo.setMajorTickSpacing(5);
		sldConsumo.setPaintLabels(true);
		sldConsumo.setForeground(new Color(0, 0, 0));
		sldConsumo.setToolTipText("");
		sldConsumo.setFont(new Font("Arial Black", Font.BOLD, 14));
		sldConsumo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51), new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51)));
		sldConsumo.setMaximum(30);
		sldConsumo.setBounds(350, 105, 452, 50);
		add(sldConsumo);

		/*
		 * JScrollPane con su JTable - tableResultado
		 */
		JLabel lblInformacion2 = new JLabel("DATOS DE CONSULTA (CONSUMO DE VALOR  <=  AL SELECCIONADO):");
		lblInformacion2.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion2.setBounds(10, 335, 796, 14);
		add(lblInformacion2);

		JScrollPane srcResultado = new JScrollPane();
		srcResultado.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51), new Color(204, 0, 51)));
		srcResultado.setBounds(10, 363, 878, 203);
		add(srcResultado);

		tableResultado = new JTable();
		tableResultado.setGridColor(new Color(153, 0, 51));
		tableResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		srcResultado.setViewportView(tableResultado);
		modeloTabla.setColumnIdentifiers(new Object[] { "MODELO", "CONSUMO", "EMISIONES", "C.ENERGÉTICA" });
		tableResultado.setModel(modeloTabla);

		// Etiqueta de tabla
		lblElegido = new JLabel("  CONSUMO  ");
		lblElegido.setForeground(new Color(153, 0, 51));
		lblElegido.setFont(new Font("Arial Black", Font.BOLD, 18));
		srcResultado.setRowHeaderView(lblElegido);
		modeloTabla.setRowCount(0);

		// Información
		JLabel lblConsulta = new JLabel("2\u00BA- ELIJE EL TIPO DE CONSULTA:");
		lblConsulta.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsulta.setBounds(10, 177, 309, 14);
		add(lblConsulta);

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
				Float consumo = (float) sldConsumo.getValue();
				for (Modelo m : consul.consultaConsAsc(consumo)) {
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
				Float consumo = (float) sldConsumo.getValue();
				for (Modelo m : consul.consultaConsDes(consumo)) {
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
		tlbExportar.setOrientation(SwingConstants.VERTICAL);
		tlbExportar.setForeground(Color.BLACK);
		tlbExportar.setFloatable(false);
		tlbExportar.setBackground(new Color(153, 0, 51));
		tlbExportar.setBounds(898, 240, 107, 326);
		add(tlbExportar);

		// Exportar documento XML
		JButton btnExportarXML = new JButton("");
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float consumo = (float) sldConsumo.getValue();
				datos.xmlConsumos(consumo);
			}
		});
		btnExportarXML.setIcon(new ImageIcon("img/imgForm/xml.png"));
		btnExportarXML.setBackground(new Color(255, 102, 51));
		tlbExportar.add(btnExportarXML);

		// Exportar documento SQL
		JButton btnExportarSQL = new JButton("");
		btnExportarSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float consumo = (float) sldConsumo.getValue();
				datos.sqlConsumo(consumo);
			}
		});
		btnExportarSQL.setIcon(new ImageIcon("img/imgForm/sql.png"));
		btnExportarSQL.setBackground(new Color(0, 102, 255));
		tlbExportar.add(btnExportarSQL);

		// Exportar documento XLS
		JButton btnExportarXSL = new JButton("");
		btnExportarXSL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float consumo = (float) sldConsumo.getValue();
				datos.xslConsumo(consumo);
			}
		});
		btnExportarXSL.setIcon(new ImageIcon("img/imgForm/xls.png"));
		btnExportarXSL.setBackground(new Color(0, 153, 102));
		tlbExportar.add(btnExportarXSL);
	}
}
