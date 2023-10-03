package vista;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Gestiona;

public class ComboDatos extends JFrame{
	private JPanel contentPane;
	private JComboBox combo;
	/**
	 * Ejecutador del ComboBox
	 * @param frame - es la ventana
	 */
	public static void ejecutador(ComboDatos frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public ComboDatos() {
		setTitle("JComboBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		combo = new JComboBox();
		combo.setBounds(25, 45, 400, 20);
		contentPane.add(combo);
		
		Gestiona ges = new Gestiona();
		ges.cargaCombo(combo,ges.cargaProfesores());
	}
}
