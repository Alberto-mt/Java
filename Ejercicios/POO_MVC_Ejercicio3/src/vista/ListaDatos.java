package vista;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Gestiona;

public class ListaDatos extends JFrame{
	private JPanel contentPane;
	private JList lista;
	DefaultListModel modeloLista = new DefaultListModel();
	
	/**
	 * Ejecutador de la tabla
	 * @param frame - es la ventana
	 */
	public static void ejecutador(ListaDatos frame) {
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
	public ListaDatos() {
		setTitle("JList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 250);
		contentPane.add(scrollPane);
		
		lista = new JList();
		scrollPane.setViewportView(lista);
		
		lista.setModel(modeloLista);
		Gestiona ges = new Gestiona();
		ges.cargaLista(modeloLista, ges.cargaProfesores());
	}
}
