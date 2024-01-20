package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Modelo;

/**
 * GestionaMantenimientoBBDD - Clase que gestiona métodos de mantenimiento de
 * BBDD insertar, modificar y eliminar en BBDD
 * 
 * @author Alberto
 *
 */
public class GestionaMantenimientoBBDD {
	/*
	 * Declaro objeto para acceder a sus métodos
	 */
	GestionaCargaBBDD db = new GestionaCargaBBDD();

	/**
	 * insertarBBDD(Modelo mod) - Método al que le paso un modelo de coche por
	 * parámetro y lo inserta en la base de datos.
	 * 
	 * @param mod - Modelo de coche
	 */
	public void insertarBBDD(Modelo mod) {
		int res = JOptionPane.showConfirmDialog(null, "¿QUIERE INSERTAR DATOS EN LA BBDD?", "INSERTAR EN BBDD",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			try {
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

				Statement consulta = conexion.createStatement();
				consulta.executeUpdate("insert into modelos (ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA)"
						+ " values (" + mod.getIdMarca() + ",'" + mod.getModelo() + "'," + mod.getConsumo() + ","
						+ mod.getEmisiones() + ",'" + mod.getcEnergetica() + "')");
				conexion.close();
				JOptionPane.showMessageDialog(null, "DATOS INSERTADOS CORRECTAMENTE", "INFORMACION",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERROR: NO SE HA INSERTADO EN BBDD", "AVISO",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * modificarBBDD(int id,Modelo modMdf) - Método al que le paso un ID modelo
	 * elegido y Modelo nuevo como parámetros, para modificar los datos en BBDD
	 * 
	 * @param id     - ID de modelo elegido
	 * @param modMdf - Modelo modificado
	 */
	public void modificarBBDD(int id, Modelo modMdf) {
		int res = JOptionPane.showConfirmDialog(null, "¿QUIERE MODIFICAR DATOS EN LA BBDD?", "INSERTAR EN BBDD",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			try {
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
				Statement consulta = conexion.createStatement();

				int valor = consulta.executeUpdate(
						"update modelos set ID_MARCA = " + modMdf.getIdMarca() + ", MODELO = '" + modMdf.getModelo()
								+ "', CONSUMO = " + modMdf.getConsumo() + ", EMISIONES = " + modMdf.getEmisiones()
								+ ", C_ENERGETICA = '" + modMdf.getcEnergetica() + "' where ID = " + id);
				if (valor == 1) {
					JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CORRECTAMENTE", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: EN ID DE MODELO", "AVISO", JOptionPane.WARNING_MESSAGE);
				}
				conexion.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERROR: NO SE HA MODIFICADO EN BBDD", "AVISO",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * eliminarBBDD(int id) - Método al que le paso un identificador como parametro,
	 * para eliminar un registro
	 * 
	 * @param id - ID de modelo elegido
	 */
	public void eliminarBBDD(int id) {
		int res = JOptionPane.showConfirmDialog(null, "¿QUIERE ELIMINAR DATOS EN LA BBDD?", "ELIMINAR EN BBDD",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			Connection conexion;
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
				Statement consulta = conexion.createStatement();
				int valor = consulta.executeUpdate("delete from modelos where ID =" + id);
				if (valor == 1) {
					JOptionPane.showMessageDialog(null, "MODELO ELIMINADO CORRECTAMENTE", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: EN ID DE MODELO", "AVISO", JOptionPane.WARNING_MESSAGE);
				}
				conexion.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERROR: NO SE HA ELIMINADO DE BBDD", "AVISO",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
