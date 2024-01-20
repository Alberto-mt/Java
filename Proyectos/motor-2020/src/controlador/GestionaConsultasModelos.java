package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import modelo.Modelo;

/**
 * GestionaConsultasModelos - Clase que gestiona m�todos de consulta de BBDD
 * 
 * @author Alberto
 *
 */
public class GestionaConsultasModelos {
	/*
	 * Declaro objeto para acceder a sus m�todos
	 */
	GestionaCargaBBDD db = new GestionaCargaBBDD();

	/**
	 * consultaMarcAsc(String marca) - M�todo que devuelve un Array de modelos de un
	 * marca, en orden ascendente, pasandole por par�metro la marca
	 * 
	 * @param marca - marca elegida
	 * @return arrayModelosMod
	 */
	public ArrayList<Modelo> consultaMarcAsc(String marca) {
		ArrayList<Modelo> arrayModelosMod = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM modelos, marcas WHERE marcas.MARCA = '" + marca
					+ "' and marcas.ID=modelos.ID_MARCA");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosMod.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosMod;
	}

	/**
	 * consultaMarcDes(String marca) - M�todo que devuelve un Array de modelos de un
	 * marca, en orden descendente, pasandole por par�metro la marca
	 * 
	 * @param marca - marca elegida
	 * @return arrayModelosMod
	 */
	public ArrayList<Modelo> consultaMarcDes(String marca) {
		ArrayList<Modelo> arrayModelosMod = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM modelos, marcas WHERE marcas.MARCA = '" + marca
					+ "' and marcas.ID=modelos.ID_MARCA ORDER BY modelos.ID DESC");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosMod.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosMod;
	}

	/**
	 * consultaConsAsc(float consumo) - M�todo que devuelve un Array de modelos de
	 * menor o igual consumo al elegido, en orden ascendente, pasandole por
	 * par�metro el consumo
	 * 
	 * @param consumo - consumo elegido
	 * @return arrayModelosCon
	 */
	public ArrayList<Modelo> consultaConsAsc(float consumo) {
		ArrayList<Modelo> arrayModelosCon = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE CONSUMO <= " + consumo + "ORDER BY CONSUMO");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosCon.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosCon;
	}

	/**
	 * consultaConsDes(float consumo) - M�todo que devuelve un Array de modelos de
	 * menor o igual consumo al elegido, en orden descendente, pasandole por
	 * par�metro el consumo
	 * 
	 * @param consumo - consumo elegido
	 * @return arrayModelosCon
	 */
	public ArrayList<Modelo> consultaConsDes(float consumo) {
		ArrayList<Modelo> arrayModelosCon = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE CONSUMO <= " + consumo + "ORDER BY CONSUMO DESC");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosCon.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosCon;
	}

	/**
	 * consultaEmiAsc(float emision) - M�todo que devuelve un Array de modelos de
	 * menor o igual emisi�n a la elegida, en orden ascendente, pasandole por
	 * par�metro la emisi�n
	 * 
	 * @param emision - emisi�n elegida
	 * @return arrayModelosEmi
	 */
	public ArrayList<Modelo> consultaEmiAsc(float emision) {
		ArrayList<Modelo> arrayModelosEmi = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE EMISIONES <= " + emision + "ORDER BY EMISIONES");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosEmi.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosEmi;
	}

	/**
	 * consultaEmiDes(float emision) - M�todo que devuelve un Array de modelos de
	 * menor o igual emisi�n a la elegida, en orden descendente, pasandole por
	 * par�metro la emisi�n
	 * 
	 * @param emision - emisi�n elegida
	 * @return arrayModelosEmi
	 */
	public ArrayList<Modelo> consultaEmiDes(float emision) {
		ArrayList<Modelo> arrayModelosEmi = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");

			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE EMISIONES <= " + emision + "ORDER BY EMISIONES DESC");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosEmi.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosEmi;
	}

	/**
	 * consultaCEnerAsc(String cer) - M�todo que devuelve un Array de modelos de un
	 * clasificaci�n energetica, en orden ascendente, pasandole por par�metro la la
	 * clasificaci�n
	 * 
	 * @param cer - C_Energetica elegida
	 * @return arrayModelosCEner
	 */
	public ArrayList<Modelo> consultaCEnerAsc(String cer) {
		ArrayList<Modelo> arrayModelosCEner = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE C_ENERGETICA = '" + cer + "'ORDER BY ID_MARCA");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosCEner.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosCEner;
	}

	/**
	 * consultaCEnerDesc(String cer) - M�todo que devuelve un Array de modelos de un
	 * clasificaci�n energetica, en orden descendente, pasandole por par�metro la la
	 * clasificaci�n
	 * 
	 * @param cer - C_Energetica elegida
	 * @return arrayModelosCEner
	 */
	public ArrayList<Modelo> consultaCEnerDesc(String cer) {
		ArrayList<Modelo> arrayModelosCEner = new ArrayList();
		try {
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/motor2020", "motor2020", "motor2020");
			Statement sentencia = (Statement) conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia
					.executeQuery("SELECT * FROM modelos WHERE C_ENERGETICA = '" + cer + "'ORDER BY ID_MARCA DESC");
			while (rs.next()) {
				Modelo cm = new Modelo();
				cm.setId(rs.getInt("ID"));
				cm.setIdMarca(rs.getInt("ID_MARCA"));
				cm.setModelo(rs.getString("MODELO"));
				cm.setConsumo(rs.getFloat("CONSUMO"));
				cm.setEmisiones(rs.getFloat("EMISIONES"));
				cm.setcEnergetica(rs.getString("C_ENERGETICA"));
				arrayModelosCEner.add(cm);
			}
			conexion.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXI�N A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL C�DIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosCEner;
	}
}
