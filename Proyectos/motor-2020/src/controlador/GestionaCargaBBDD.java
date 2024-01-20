package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Eficiencia;
import modelo.Marca;
import modelo.Modelo;
import vista.JFprograma;

/**
 * GestionaCargaBBDD - Clase que gestiona cargar la aplicación desde BBDD
 * 
 * @author Alberto
 *
 */
public class GestionaCargaBBDD {
	
	/**
	 * conectandoBBDD() - Método que retorna la conexión a BBDD
	 * 
	 * @return conectando
	 */
	public String conectandoBBDD() {
		String conectando = "";
		String host = "jdbc:mysql://localhost/motor2020";
		String usuario = "motor2020";
		String contraseña = "motor2020";	
		conectando = host + usuario + contraseña;
		return conectando;
	}

	/**
	 * runMotor2020() - Método que ejecuta la aplicación
	 */
	public void runMotor2020() {
		try {
			// Cargo tablas
			cargaMarca();
			cargaEficiencia();
			/*
			 * Creo objeto JFprograma para acceder a sus métodos Ejecuto el JFrame ventana
			 * (la aplicación en si)
			 */
			JFprograma ventana = new JFprograma();
			ventana.cargaVentana(ventana);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR: PROBLEMA DE EJECUCIÓN", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

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

	/**
	 * cargaEficiencia() - Método que carga la tabla eficiencias a un ArrayList
	 * 
	 * @return arrayEficiencias
	 */
	public ArrayList<Eficiencia> cargaEficiencia() {
		ArrayList<Eficiencia> arrayEficiencias = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM eficiencias");
			while (rs.next()) {
				Eficiencia e = new Eficiencia();
				e.setcEnergetica(rs.getString("C_ENERGETICA"));
				e.setDescripcion(rs.getString("DESCRIPCION"));
				e.setIcono(rs.getString("ICONO"));
				arrayEficiencias.add(e);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayEficiencias;
	}

	/**
	 * cargaModelo() - Método que carga la tabla modelos a un ArrayList
	 * 
	 * @return arrayModelos
	 */
	public ArrayList<Modelo> cargaModelo() {
		ArrayList<Modelo> arrayModelos = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM modelos");
			while (rs.next()) {
				Modelo mod = new Modelo();
				mod.setId(rs.getInt("ID"));
				mod.setIdMarca(rs.getInt("ID_MARCA"));
				mod.setModelo(rs.getString("MODELO"));
				mod.setConsumo(rs.getFloat("CONSUMO"));
				mod.setEmisiones(rs.getFloat("EMISIONES"));
				mod.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelos.add(mod);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelos;
	}

	/**
	 * cargaModeloModificar(String marca) - Método que carga la tabla modelos a un
	 * ArrayList pasandole por parámetro la marca
	 * 
	 * @param marca - Marca de coche
	 * @return arrayModelosMod
	 */
	public ArrayList<Modelo> cargaModeloModificar(String marca) {
		ArrayList<Modelo> arrayModelosMod = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM modelos, marcas WHERE marcas.MARCA = '" + marca
					+ "' and marcas.ID=modelos.ID_MARCA ");
			while (rs.next()) {
				Modelo modMod = new Modelo();
				modMod.setModelo(rs.getString("MODELO"));
				;
				arrayModelosMod.add(modMod);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosMod;
	}
}
