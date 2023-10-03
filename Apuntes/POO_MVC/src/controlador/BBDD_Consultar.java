package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelo.Cosas;
import modelo.Marca;
import modelo.Modelo;
import modelo.Producto;
import vista.Mostrar;

public class BBDD_Consultar {

	/*
	 * Declaro objeto teclado para poder introducir datos. Con "ISO-8859-1",
	 * controlo la entrada por teclado de caracteres especiales.
	 */
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	/* Creo objeto 'v' para acceder a los métodos de mostrar */
	Mostrar mostrar = new Mostrar();
	/*
	 * ArrayList es genérico a toda la aplicacion para poder insertar y mostrar objetos 
	 */
	ArrayList<Producto> arrayProductos = new ArrayList<Producto>();
	
	//Llamada a método
	consultarBBDD();
	
	/**
	 * Método de consulta de BBDD por id
	 */
	public void consultarBBDD() {
		System.out.print("\nIntroduzca el idProducto a consultar: ");
		int idProducto = teclado.nextInt();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_productos", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery(
					"select * from producto where idProducto = " + idProducto);
			if (registro.next()) {
				String nom = registro.getString("nombre");
				System.out.println("IdProducto: " + registro.getInt("idProducto"));
				System.out.println("Nombre: " + registro.getString("nombre"));
				System.out.println("Unidades: " + registro.getString("unidades"));
				System.out.println("Precio: " + registro.getInt("precio"));
			} else {
				System.out.println("- idProducto no encontrado -");
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Para recoger enumerados
	 */

	enumerado = "";
	enumerado = rs.getString("tipo").toUpperCase();
	c.setTipo(Cosas.Tipo.valueOf(enumerado));
	
	/*******************************************************************************************************/
	
	/**
	 * consultaMarcAsc(String marca) - Método que devuelve un Array de modelos de un
	 * marca, en orden ascendente, pasandole por parámetro la marca
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
			JOptionPane.showMessageDialog(null, "ERROR TIPO SQL: SIN CONEXIÓN A BBDD", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL CÓDIGO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return arrayModelosMod;
	}

	/**
	 *Método que obtiene el id de una marca selecionada y lo devuelve
	 * como entero
	 * 
	 * @return id
	 */
	public int obtenerId() {
		int id = 0;
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			if (arrayMarcas.get(i).getNombre().equalsIgnoreCase((String) cmbMarca.getSelectedItem())) {
				id = arrayMarcas.get(i).getIdMarca();
			}
		}
		return id;
	}
}
