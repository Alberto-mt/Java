package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelo.Modelo;
import modelo.Producto;
import vista.Mostrar;

public class BBDD_Modificar {
	/*
	 * Declaro objeto teclado para poder introducir datos. Con "ISO-8859-1",
	 * controlo la entrada por teclado de caracteres especiales.
	 */
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	/* Creo objeto 'v' para acceder a los métodos de mostrar */
	Mostrar v = new Mostrar();
	/*
	 * arrayProducto - Este ArrayList es genérico a toda la aplicacion para poder
	 * insertar y mostrar objetos de tipo Producto
	 */
	ArrayList<Producto> arrayProductos = new ArrayList();
	
	/**
	 * Método que modifica un objeto de BBDD
	 */
	public void modificarBBDD() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nIntroduzca el idProducto a modificar: ");
		int idProducto = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Introduzca el nombre a modificar: ");
		String nombre = teclado.nextLine();
		System.out.print("Introduzca las unidades a modificar: ");
		int unidades = teclado.nextInt();
		System.out.print("Introduzca el precio a modificar: ");
		float precio = teclado.nextFloat();

		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_productos", "root", "");
			Statement consulta = conexion.createStatement();
			int valor = consulta.executeUpdate("update producto set nombre = '" + nombre + "', unidades = " + unidades
					+ ", precio = " + precio + " where idProducto = " + idProducto);
			if (valor == 1) {
				System.out.println("- Producto modificado correctamente -");
			} else {
				System.out.println("- No existe un producto con dicho identificador -");
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*******************************************************************************************************/
	
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
}
