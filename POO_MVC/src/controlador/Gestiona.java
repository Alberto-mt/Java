package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Controlador.OrdenarArrays;
import modelo.Alumno;
import modelo.Cosas;
import modelo.Persona;
import modelo.Profesor;
import vista.Mostrar;

public class Gestiona {
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");

	ArrayList<Cosas> arrayCosas = new ArrayList();
	ArrayList<Cosas> arrayAnimales = new ArrayList();
	ArrayList<Cosas> arrayVegetales = new ArrayList();
	ArrayList<Cosas> arrayMinerales = new ArrayList();

	Mostrar mostrar = new Mostrar();
	OrdenarArrays ordenar = new OrdenarArrays();
	
	public static final String HOST = "jdbc:mysql://localhost/db_anivegmin";
	public static final String USUARIO = "alberto";
	public static final String CONTRASENIA = "alberto";
	
	public void programa() {
		cargaCosas();
		mostrarArrayCosas();
		
		cargarArrayAnimales();
		ordenar.ordenarArrayAnimalPorNombre(arrayAnimales);
		printWritterAnimales(arrayAnimales);
		
		cargarArrayVegetales();
		ordenar.ordenarArrayVegetalPorNombre(arrayVegetales);
		printWritterVegetales(arrayVegetales);
		
		cargarArrayMinerales();
		ordenar.ordenarArrayMineralPorNombre(arrayMinerales);
		printWritterMinerales(arrayMinerales);
		
		boolean ok = false;
		do {
			try {
				int opcion;
				do {
					mostrar.muestraMenu();
					opcion = teclado.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("\n- Lectura fichero legible -\n");
						leerBufferAnimales();
						break;
					case 2:
						System.out.println("\n- Lectura fichero legible -\n");
						leerBufferVegetales();
						break;
					case 3:
						System.out.println("\n- Lectura fichero legible -\n");
						leerBufferMinerales();
						break;
					case 0:
						mostrar.despedida();
						break;
					default:
						System.out.println("Error: Opción incorrecta");
						System.out.print("Elija una opcion: ");
					}
				} while ((opcion >= 1 || opcion <= 3) && opcion != 0);
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("\tError: Solo puedes escribir números");
				teclado.nextLine();
			}
		} while (!ok);
	}

	/*
	 * CARGAR ARRAY DE BBDD
	 **************************************************************************************/
	public ArrayList<Cosas> cargaCosas() {
		try {
			Connection conexion = (Connection) DriverManager.getConnection(HOST,USUARIO,CONTRASENIA);
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM cosas");
			String enumerado = "";
			while (rs.next()) {
				Cosas c = new Cosas();
				// Para recoger enumerado
				enumerado = "";
				enumerado = rs.getString("dato").toUpperCase();
				c.setDato(Cosas.Tipo.valueOf(enumerado));
				c.setNombre(rs.getString("nombre"));
				c.setNumero(rs.getInt("numero"));
				arrayCosas.add(c);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayCosas;
	}

	public void mostrarArrayCosas() {
		System.out.println("--Cargando de BBDD--");
		mostrar = new Mostrar();
		for (Cosas c : arrayCosas) {
			mostrar.mostrarCosas(c);
		}
	}

	/*
	 * CARGAR ANIMALES ARRAY DESDE ARRAY CARGADO Y MOSTRAR
	 **************************************************************************************/
	public ArrayList<Cosas> cargarArrayAnimales() {
		for (int i = 0; i < arrayCosas.size(); i++) {
			Cosas a = new Cosas();
			if (arrayCosas.get(i).getDato().equals(Cosas.Tipo.ANIMALES)) {
				a.setDato(arrayCosas.get(i).getDato());
				a.setNombre(arrayCosas.get(i).getNombre());
				a.setNumero(arrayCosas.get(i).getNumero());
				arrayAnimales.add(a);
			}
		}
		return arrayAnimales;
	}

	public void mostrarArrayAnimales() {
		mostrar = new Mostrar();
		for (Cosas a : arrayAnimales) {
			mostrar.mostrarAnimal(a);
		}
	}

	/*
	 * CARGAR VEGETALES ARRAY DESDE ARRAY CARGADO Y MOSTRAR
	 **************************************************************************************/
	public ArrayList<Cosas> cargarArrayVegetales() {
		for (int i = 0; i < arrayCosas.size(); i++) {
			Cosas v = new Cosas();
			if (arrayCosas.get(i).getDato().equals(Cosas.Tipo.VEGETALES)) {
				v.setDato(arrayCosas.get(i).getDato());
				v.setNombre(arrayCosas.get(i).getNombre());
				v.setNumero(arrayCosas.get(i).getNumero());
				arrayVegetales.add(v);
			}
		}
		return arrayVegetales;
	}

	public void mostrarArrayVegetales() {
		mostrar = new Mostrar();
		for (Cosas v : arrayVegetales) {
			mostrar.mostrarVegetal(v);
		}
	}

	/*
	 * CARGAR MINERALES ARRAY DESDE ARRAY CARGADO Y MOSTRAR
	 **************************************************************************************/
	public ArrayList<Cosas> cargarArrayMinerales() {
		for (int i = 0; i < arrayCosas.size(); i++) {
			Cosas m = new Cosas();
			if (arrayCosas.get(i).getDato().equals(Cosas.Tipo.MINERALES)) {
				m.setDato(arrayCosas.get(i).getDato());
				m.setNombre(arrayCosas.get(i).getNombre());
				m.setNumero(arrayCosas.get(i).getNumero());
				arrayMinerales.add(m);
			}
		}
		return arrayMinerales;
	}

	public void mostrarArrayMinerales() {
		mostrar = new Mostrar();
		for (Cosas m : arrayMinerales) {
			mostrar.mostrarMineral(m);
		}
	}

	/*
	 * Escribir Ficheros
	 */

	public void printWritterAnimales(ArrayList<Cosas> arrayAnimales) {
		File archivo = new File("animales.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			for (Cosas a : arrayAnimales) {
				/* Escribir en fichero */
				pw.println("" + a.getDato());
				pw.println(a.getNombre());
				pw.println("" + a.getNumero());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			/* Cierra instancias de FileWriter y BufferedWriter */
			if (pw != null) {
				pw.close();
			}
		}
	}

	public void printWritterVegetales(ArrayList<Cosas> arrayVegetales) {
		File archivo = new File("vegetales.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			for (Cosas v : arrayVegetales) {
				/* Escribir en fichero */
				pw.println("" + v.getDato());
				pw.println(v.getNombre());
				pw.println("" + v.getNumero());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			/* Cierra instancias de FileWriter y BufferedWriter */
			if (pw != null) {
				pw.close();
			}
		}
	}

	public void printWritterMinerales(ArrayList<Cosas> arrayMinerales) {
		File archivo = new File("minerales.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(archivo);
			for (Cosas m : arrayMinerales) {
				/* Escribir en fichero */
				pw.println("" + m.getDato());
				pw.println(m.getNombre());
				pw.println("" + m.getNumero());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			/* Cierra instancias de FileWriter y BufferedWriter */
			if (pw != null) {
				pw.close();
			}
		}
	}

	/*
	 * Leer Ficheros
	 */
	public void leerBufferAnimales() {
		String cadena;
		FileReader f = null;
		try {
			f = new FileReader("animales.txt");
			BufferedReader br = new BufferedReader(f);
			while ((cadena = br.readLine()) != null) {
				System.out.println(cadena);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void leerBufferVegetales() {
		String cadena;
		FileReader f = null;

		try {
			f = new FileReader("vegetales.txt");
			BufferedReader br = new BufferedReader(f);
			while ((cadena = br.readLine()) != null) {
				System.out.println(cadena);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void leerBufferMinerales() {
		String cadena;
		FileReader f = null;
		try {
			f = new FileReader("minerales.txt");
			BufferedReader br = new BufferedReader(f);
			while ((cadena = br.readLine()) != null) {
				System.out.println(cadena);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
/**************************************************************************************************************************/
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
	
	/**********************************************************************************************************************/
	 /**
     * cls()- Método que limpia consola.
     */
    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
    }
}
