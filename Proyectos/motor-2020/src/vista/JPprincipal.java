package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * JPprincipal - Clase que muestra el JPanel de presentación de la aplicación
 * 
 * @author Alberto
 *
 */
public class JPprincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPprincipal() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 0, 51), new Color(204, 0, 51),
				new Color(204, 0, 51), new Color(204, 0, 51)));
		setBackground(new Color(204, 204, 204));
		setLayout(null);

		JLabel lblPortada = new JLabel("");
		lblPortada.setIcon(new ImageIcon("img/logo/logo.png"));
		lblPortada.setBounds(238, 88, 916, 350);
		add(lblPortada);

		JLabel lblTitulo = new JLabel("MOTOR 2020");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 80));
		lblTitulo.setBounds(218, 410, 672, 89);
		add(lblTitulo);

		JLabel lblAmt = new JLabel("AMT - PRO 3T");
		lblAmt.setVerticalAlignment(SwingConstants.TOP);
		lblAmt.setAlignmentY(Component.TOP_ALIGNMENT);
		lblAmt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblAmt.setForeground(new Color(204, 0, 51));
		lblAmt.setBounds(134, 494, 327, 79);
		add(lblAmt);
	}
}
