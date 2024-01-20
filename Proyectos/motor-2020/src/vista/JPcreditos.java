package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;

/**
 * JPcreditos - Clase que muestra JPanel de Creditos
 * 
 * @author Alberto
 *
 */
public class JPcreditos extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPcreditos() {
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
		JLabel lblTitulo = new JLabel("CREDITOS");
		lblTitulo.setForeground(new Color(204, 0, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitulo.setBounds(10, 35, 849, 58);
		add(lblTitulo);

		JLabel lblTitulo2 = new JLabel("MOTOR 2020");
		lblTitulo2.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 30));
		lblTitulo2.setBounds(47, 127, 253, 32);
		add(lblTitulo2);

		JLabel lblTitulo22 = new JLabel("AMT - PRO 3T");
		lblTitulo22.setForeground(new Color(204, 0, 51));
		lblTitulo22.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		lblTitulo22.setBounds(20, 159, 136, 15);
		add(lblTitulo22);

		// Información
		JLabel lblTexto1 = new JLabel(
				"MOTOR 2020 ES UNA APLICACI\u00D3N GR\u00C1FICA DE JAVA, DESARROLLADA CON LA LIBRERIA JAVAX-SWING.");
		lblTexto1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto1.setBounds(50, 209, 913, 14);
		add(lblTexto1);

		JLabel lblTexto2 = new JLabel("EL SOFTWARE PERMITE LAS SIGUIENTES FUNCIONABILIDADES:");
		lblTexto2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto2.setBounds(50, 234, 913, 14);
		add(lblTexto2);

		JLabel lblTexto3 = new JLabel(
				"\u00B7 MANTENIMIENTO INTEGRAL DE LA BBDD: INSERTAR, MODIFICAR Y ELIMINAR REGISTROS DE LA TABLA MODELO. ");
		lblTexto3.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto3.setBounds(71, 259, 892, 14);
		add(lblTexto3);

		JLabel lblTexto4 = new JLabel(
				"\u00B7 REALIZAR CONSULTAS  DE LA TABLA MODELO Y EXPORTAR TABLAS A FORMATO:  XML, SQL Y XLS.");
		lblTexto4.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto4.setBounds(71, 284, 892, 14);
		add(lblTexto4);

		JLabel lblTexto5 = new JLabel("MOTOR 2020");
		lblTexto5.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto5.setBounds(318, 334, 645, 14);
		add(lblTexto5);

		JLabel lblTexto6 = new JLabel("VERSI\u00D3N:");
		lblTexto6.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto6.setBounds(50, 334, 260, 14);
		add(lblTexto6);

		JLabel lblTexto7 = new JLabel("DESARROLLO DE LA APLICACI\u00D3N:");
		lblTexto7.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto7.setBounds(50, 359, 260, 14);
		add(lblTexto7);

		JLabel lblTexto8 = new JLabel("ALBERTO MONTEALEGRE TOLEDO");
		lblTexto8.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto8.setBounds(318, 359, 645, 14);
		add(lblTexto8);

		JLabel lblTexto9 = new JLabel("DISE\u00D1O DE APLICACI\u00D3N:");
		lblTexto9.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto9.setBounds(50, 384, 260, 14);
		add(lblTexto9);

		JLabel lblTexto10 = new JLabel("ALBERTO MONTEALEGRE TOLEDO");
		lblTexto10.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto10.setBounds(318, 385, 645, 14);
		add(lblTexto10);

		JLabel lblTexto11 = new JLabel("FECHA DE CREACI\u00D3N:");
		lblTexto11.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto11.setBounds(50, 409, 260, 14);
		add(lblTexto11);

		JLabel lblTexto12 = new JLabel("2020");
		lblTexto12.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTexto12.setBounds(318, 410, 645, 14);
		add(lblTexto12);
	}
}
