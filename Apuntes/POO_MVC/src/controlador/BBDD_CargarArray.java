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

import javax.swing.JOptionPane;

import modelo.Cosas;
import modelo.Marca;
import vista.Mostrar;

public class BBDD_CargarArray {
	/*
	 * Array con el que se trabaja
	 */
	ArrayList<Cosas> arrayCosas = new ArrayList();
	
	/*
	 * Llamada a función - colocar en programa
	 */
	cargaBBDD();
	
	cargarArrayDesdeArray();
	
	/*
	 * fichero propiedades
	 */
	/**
	 * Método que carga propiedades de fichero de propiedades
	 * @param propiedad
	 * @return	variable
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
	//Variables cargadas de fichero de propiedades
	String nombre = ficheroPropiedades("bbdd");
	String host = ("jdbc:mysql://localhost/" + nombre);
	String user = ficheroPropiedades("user");
	String pass = ficheroPropiedades("pass");
	
	/*
	 * Constantes - para conexión de BBDD
	 */
	public static final String HOST = "jdbc:mysql://localhost/db_anivegmin";
	public static final String USUARIO = "alberto";
	public static final String CONTRASENIA = "alberto";
	
	/**
	 * Método que carga array de BBDD
	 * @return	arrayCosas
	 */
	public ArrayList<Cosas> cargaBBDD() {
		try {
			//Conexión desde fichero de propiedades
			Connection conexion = (Connection) DriverManager.getConnection(host, user, pass);
			//Conexión porConstantes
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
	
	/**
	 * Método que muestra array
	 */
	public void mostrarArrayCosas() {
		System.out.println("--Cargando de BBDD--");
		mostrar = new Mostrar();
		for (Cosas c : arrayCosas) {
			mostrar.mostrarCosas(c);
		}
	}
	
/************************************************************************************************************/
	//Array sobre el que se trabaja - la clase Cosas es la misma
	ArrayList<Cosas> arrayAnimales = new ArrayList();
	
	/**
	 * Método que carga array desde otro ya cargado
	 * @return	arrayAnimales
	 */
	public ArrayList<Cosas> cargarArrayDesdeArray() {
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
	
	/**
	 * Método que muestra array
	 */
	public void mostrarArrayAnimales() {
		mostrar = new Mostrar();
		for (Cosas a : arrayAnimales) {
			mostrar.mostrarAnimal(a);
		}
	}
	
	/*************************************************************************************************************/
	
	/**
	 * cargaMarca() - Método que carga la tabla marcas a un ArrayList
	 * 
	 * @return arrayMarcas
	 */
	public ArrayList<Marca> cargaMarca() {
		ArrayList<Marca> arrayMarcas = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM marcas");
			while (rs.next()) {
				Marca mar = new Marca();
				mar.setIdMarca(rs.getInt("ID"));
				mar.setNombre(rs.getString("MARCA"));
				arrayMarcas.add(mar);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayMarcas;
	}

}
