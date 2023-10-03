package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import modelo.Profesor;

public class GestionaGraficos {

	ArrayList<Profesor> arrayProfesor = new ArrayList();
	public ArrayList<Profesor> cargaProfesores() {
		// ArrayList<Profesor> arrayProfesor = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection(host, user, pass);
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM profesor");
			while (rs.next()) {
				Profesor pro = new Profesor();
				pro.setTitulacion(rs.getString("titulacion"));
				pro.seteLaboral(rs.getString("eLaboral"));
				pro.setCorregir(rs.getInt("corregir"));
				pro.setNombre(rs.getString("nombre"));
				pro.setApellido(rs.getString("apellido"));
				pro.setDireccion(rs.getString("direccion"));
				arrayProfesor.add(pro);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayProfesor;
	}
	
	/**
	 * Método que carga Jtable
	 * @param modeloTabla
	 * @param arrayProfesor
	 */
	public void cargaTabla(DefaultTableModel modeloTabla, ArrayList<Profesor> arrayProfesor) {
		modeloTabla.setRowCount(0);
		for (Profesor pro : arrayProfesor) {
            modeloTabla.addRow(new Object[]{
					pro.getNombre(),
					pro.getApellido(),
					pro.getTitulacion()
			});

        }	
	}
	
	/**
	 * Método que carga JList
	 * @param modeloLista
	 * @param arrayProfesor
	 */
	public void cargaLista(DefaultListModel modeloLista,ArrayList<Profesor> arrayProfesor) {
		modeloLista.setSize(0);
		for (Profesor pro : arrayProfesor) {
            modeloLista.addElement(pro.getNombre()
            		+ "   " + pro.getApellido()
            		+ "   " + pro.getTitulacion());
        }	
	}
	
	
	/**
	 * Método que carga JComboBox
	 * @param combo
	 * @param arrayProfesor
	 */
	public void cargaCombo(JComboBox combo,ArrayList<Profesor> arrayProfesor) {
		for (Profesor pro : arrayProfesor) {
            combo.addItem(pro.getNombre()
            		+ "   " + pro.getApellido()
            		+ "   " + pro.getTitulacion());
        }	
	}
	
	
	/**
	 * Método que carga JTextArea 
	 * @param area
	 * @param arrayProfesor
	 */
	public void cargaArea(JTextArea area,ArrayList<Profesor> arrayProfesor) {
		for (int i = 0; i < arrayProfesor.size(); i++) {
			area.setText("Nombre: " +arrayProfesor.get(1).getNombre() +"\r\n"
	        + "Apellido: " + arrayProfesor.get(1).getApellido() +"\r\n"
	        + "Titulación: " + arrayProfesor.get(1).getTitulacion() +"\r\n");
		
		}		
	}
}
