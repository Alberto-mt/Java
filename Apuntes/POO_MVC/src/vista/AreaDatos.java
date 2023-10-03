package vista;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Gestiona;

import javax.swing.JTextArea;

public class AreaDatos extends JFrame{
	private JPanel contentPane;
	private JTextArea area;
	
	/**
	 * Ejecutador de area
	 * @param frame - es la ventana
	 */
	public static void ejecutador(AreaDatos frame) {
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
	public AreaDatos() {
		setTitle("JTextArea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 250);
		contentPane.add(scrollPane);
		
		JTextArea area = new JTextArea();
		scrollPane.setViewportView(area);
		
		Gestiona ges = new Gestiona();
		ges.cargaArea(area,ges.cargaProfesores());
	}
}
