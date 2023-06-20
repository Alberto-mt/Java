package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Gestiona;



public class TablaDatos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};

	/**
	 * Ejecutador de la tabla
	 * @param frame - es la ventana
	 */
	public static void ejecutador(TablaDatos frame) {
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
	public TablaDatos() {
		setTitle("JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla.setColumnIdentifiers(new Object[]{"Nombre", "Apellido", "Titulacion"});
		table.setModel(modeloTabla);
		
		Gestiona ges = new Gestiona();
		ges.cargaTabla(modeloTabla, ges.cargaProfesores());
	}

}
