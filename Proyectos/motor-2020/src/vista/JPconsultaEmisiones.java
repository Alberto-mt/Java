package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.CMMException;
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
import javax.swing.ScrollPaneConstants;

/**
 * JPconsultaEmisiones - Clase que muestra JPanel de consulta de Emisión
 * 
 * @author Alberto
 *
 */
public class JPconsultaEmisiones extends JPanel {
	private JSlider sldEmision;
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
	public JPconsultaEmisiones() {

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
		JLabel lblTitulo = new JLabel("CONSULTA-EMISIONES");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 11, 849, 58);
		add(lblTitulo);

		// Información
		JLabel lblInformacion = new JLabel("SELECCIONE LA EMISI\u00D3N A CONSULTAR ");
		lblInformacion.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInformacion.setBounds(10, 69, 796, 14);
		add(lblInformacion);

		/*
		 * Etiqueta con su JSlider - sldEmision
		 */
		JLabel lblEmision = new JLabel("1\u00BA - SELECCIONE LA EMISI\u00D3N:");
		lblEmision.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEmision.setBounds(10, 105, 265, 14);
		add(lblEmision);

		sldEmision = new JSlider();
		sldEmision.setValue(0);
		sldEmision.setToolTipText("");
		sldEmision.setPaintTicks(true);
		sldEmision.setPaintLabels(true);
		sldEmision.setMinorTickSpacing(10);
		sldEmision.setMaximum(300);
		sldEmision.setMajorTickSpacing(50);
		sldEmision.setForeground(Color.BLACK);
		sldEmision.setFont(new Font("Arial Black", Font.BOLD, 14));
		sldEmision.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51), new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51)));
		sldEmision.setBounds(348, 105, 452, 50);
		add(sldEmision);

		/*
		 * JScrollPane con su JTable - tableResultado
		 */
		JLabel lblResultado = new JLabel("DATOS DE CONSULTA (EMISIONES DE VALOR  <=  AL SELECCIONADO):");
		lblResultado.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblResultado.setBounds(10, 330, 796, 14);
		add(lblResultado);

		JScrollPane srcResultado = new JScrollPane();
		srcResultado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		srcResultado.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51), new Color(204, 0, 51)));
		srcResultado.setBounds(10, 361, 879, 203);
		add(srcResultado);

		tableResultado = new JTable();
		tableResultado.setGridColor(new Color(153, 0, 51));
		tableResultado.setFont(new Font("Arial Black", Font.BOLD, 12));
		srcResultado.setViewportView(tableResultado);
		modeloTabla.setColumnIdentifiers(new Object[] { "MODELO", "CONSUMO", "EMISIONES", "C.ENERGÉTICA" });
		tableResultado.setModel(modeloTabla);
		modeloTabla.setRowCount(0);

		// Etiqueta de tabla
		lblElegido = new JLabel(" EMISIONES ");
		lblElegido.setForeground(new Color(153, 0, 51));
		lblElegido.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblElegido.setBackground(new Color(0, 0, 0));
		srcResultado.setRowHeaderView(lblElegido);
		modeloTabla.setRowCount(0);

		// Información
		JLabel lblConsulta = new JLabel("2\u00BA- ELIJE EL TIPO DE CONSULTA:");
		lblConsulta.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblConsulta.setBounds(10, 180, 291, 14);
		add(lblConsulta);

		/*
		 * Etiqueta con su JButton - btnAscendente
		 */
		JLabel lblAscendente = new JLabel("ASCENDENTE");
		lblAscendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAscendente.setBounds(347, 180, 107, 14);
		add(lblAscendente);

		btnAscendente = new JButton("");
		btnAscendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableResultado.setModel(modeloTabla);
				modeloTabla.setRowCount(0);
				Float emision = (float) sldEmision.getValue();
				for (Modelo m : consul.consultaEmiAsc(emision)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnAscendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnAscendente.setForeground(Color.BLACK);
		btnAscendente.setBackground(new Color(153, 0, 51));
		btnAscendente.setBounds(347, 205, 107, 114);
		add(btnAscendente);

		/*
		 * Etiqueta con su JButton - btnDescendente
		 */
		JLabel lblDescendente = new JLabel("DESCENDENTE");
		lblDescendente.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblDescendente.setBounds(568, 180, 112, 14);
		add(lblDescendente);

		btnDescendente = new JButton("");
		btnDescendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableResultado.setModel(modeloTabla);
				modeloTabla.setRowCount(0);
				Float emision = (float) sldEmision.getValue();
				for (Modelo m : consul.consultaEmiDes(emision)) {
					modeloTabla.addRow(
							new Object[] { m.getModelo(), m.getConsumo(), m.getEmisiones(), m.getcEnergetica() });
				}
			}
		});
		btnDescendente.setIcon(new ImageIcon("img/imgForm/consultar.png"));
		btnDescendente.setForeground(Color.BLACK);
		btnDescendente.setBackground(new Color(153, 0, 51));
		btnDescendente.setBounds(568, 205, 107, 114);
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
		tlbExportar.setBounds(899, 238, 107, 326);
		add(tlbExportar);

		// Exportar documento XML
		btnExportarXML = new JButton("");
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float emision = (float) sldEmision.getValue();
				datos.xmlEmisiones(emision);
			}
		});
		btnExportarXML.setIcon(new ImageIcon("img/imgForm/xml.png"));
		btnExportarXML.setBackground(new Color(255, 102, 51));
		tlbExportar.add(btnExportarXML);

		// Exportar documento SQL
		btnExportarSQL = new JButton("");
		btnExportarSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float emision = (float) sldEmision.getValue();
				datos.sqlEmisiones(emision);
			}
		});
		btnExportarSQL.setIcon(new ImageIcon("img/imgForm/sql.png"));
		btnExportarSQL.setBackground(new Color(0, 102, 255));
		tlbExportar.add(btnExportarSQL);

		// Exportar documento XLS
		btnExportarXLS = new JButton("");
		btnExportarXLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float emision = (float) sldEmision.getValue();
				datos.xslEmision(emision);
			}
		});
		btnExportarXLS.setIcon(new ImageIcon("img/imgForm/xls.png"));
		btnExportarXLS.setBackground(new Color(0, 153, 102));
		tlbExportar.add(btnExportarXLS);
	}
}
