package controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.Persona;
import modelo.Profesor;
import vista.Mostrar;

public class Gestiona {
	
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	Mostrar mostrar = new Mostrar();
	
	ArrayList<Persona> arrayPersona = new ArrayList();
	ArrayList<Profesor> arrayProfesor = new ArrayList();
	ArrayList<Alumno> arrayAlumno = new ArrayList();
	/*
	 * fichero propiedades
	 */

	public String ficheroPropiedades(String propiedad) {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String variable = "";
		try {
			// Objeto InputStream con el conexion.properties
			entrada = new FileInputStream("conexion.properties");
			// Cargar el archivo .properties
			propiedades.load(entrada);
			// Obtener las propiedades
			variable = propiedades.getProperty(propiedad);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return variable;
	}

	String nombre = ficheroPropiedades("bbdd");
	String host = ("jdbc:mysql://localhost/" + nombre);
	String user = ficheroPropiedades("user");
	String pass = ficheroPropiedades("pass");
	/*
	 * 
	 */
	public void programa() {

		// insertaProfesor();
		// insertaAlumno();
		// numeroVeces();

		cargaProfesores();
		cargaAlumnos();

		mostrarProfesores();
		mostrarAlumnos();
		
	}

	public void numeroVeces() {
		for (int i = 0; i < 3; i++) {
			insertaProfesor();
			insertaAlumno();
		}
	}

	public void insertaProfesor() {
		try {
			Profesor pro = new Profesor();
			mostrar = new Mostrar();
			pro = mostrar.rellenaProfesor();
			Connection conexion = DriverManager.getConnection(host, user, pass);
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(
					"insert into profesor (titulacion,eLaboral,corregir,nombre,apellido,direccion) values('"
							+ pro.getTitulacion() + "','" + pro.geteLaboral() + "'," + pro.getCorregir() + ",'"
							+ pro.getNombre() + "','" + pro.getApellido() + "','" + pro.getDireccion() + "')");

			consulta.executeUpdate("insert into persona (nombre,apellido,direccion) values('" + pro.getNombre() + "','"
					+ pro.getApellido() + "','" + pro.getDireccion() + "');");
			conexion.close();
			System.out.println("- Profesor insertado correctamente -");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertaAlumno() {
		try {
			Alumno alu = new Alumno();
			mostrar = new Mostrar();
			alu = mostrar.rellenaAlumno();
			Connection conexion = DriverManager.getConnection(host, user, pass);
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into alumno (procedencia,pagos,nombre,apellido,direccion) values('"
					+ alu.getProcedencia() + "'," + alu.getPagos() + ",'" + alu.getNombre() + "','" + alu.getApellido()
					+ "','" + alu.getDireccion() + "')");

			consulta.executeUpdate("insert into persona (nombre,apellido,direccion) values('" + alu.getNombre() + "','"
					+ alu.getApellido() + "','" + alu.getDireccion() + "');");
			conexion.close();
			System.out.println("- Alumno insertado correctamente -");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 */
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

	public ArrayList<Alumno> cargaAlumnos() {
		// ArrayList<Alumno> arrayAlumno = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection(host, user, pass);

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM alumno");
			while (rs.next()) {
				Alumno alu = new Alumno();
				alu.setProcedencia(rs.getString("procedencia"));
				alu.setPagos(rs.getInt("pagos"));
				alu.setNombre(rs.getString("nombre"));
				alu.setApellido(rs.getString("apellido"));
				alu.setDireccion(rs.getString("direccion"));
				arrayAlumno.add(alu);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayAlumno;
	}

	/*
	 * 
	 */
	public void mostrarProfesores() {
		System.out.println("\n- Mostrar Array Profesores -");
		mostrar = new Mostrar();
		for (Profesor pro : arrayProfesor) {
			mostrar.mostrarProfesor(pro);
		}
	}

	public void mostrarAlumnos() {
		System.out.println("\n- Mostrar Array Alumnos -");
		mostrar = new Mostrar();
		for (Alumno alu : arrayAlumno) {
			mostrar.mostrarAlumno(alu);
		}
	}
	
	/*
	 * Graficos 
	 * 
	 */
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
