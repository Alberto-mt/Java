package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.Mostrar;

public class BBDD_Eliminar {

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
	ArrayList<Producto> arrayProductos = new ArrayList();
	
	//Llamada a método
	eliminarBBDD();
	
	/**
	 * Método que elimina de BBDD por id
	 */
	public void eliminarBBDD() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nIntroduzca el idProducto a borrar: ");
		int idProducto = teclado.nextInt();	
		//String nombre = teclado.nextLine();
		boolean ok = false;
		do {
			try {
				int opcion;
				do {
					System.out.println("¿Realmente desea eliminarlo?");
					System.out.println("1. Si");
					System.out.println("2. No");
					opcion = teclado.nextInt();
					switch (opcion) {
					case 1:
						Connection conexion;
						try {
							conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_productos", "root", "");
							Statement consulta = conexion.createStatement();

							int valor = consulta.executeUpdate("delete from producto where idProducto= " + idProducto);
							if (valor == 1) {
								System.out.println("- Producto borrado correctamente -");	
							} else {
								System.out.println("- No existe un producto con dicho identificador -");
							}
							conexion.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 2:
						System.out.println("- Producto no borrado -");
						break;					
					default:
						System.err.println("\nError: Opción incorrecta");
					}
				} while ((opcion != 1 && opcion != 2));
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e) {
				System.err.println("\tError: Solo puedes escribir números");
				teclado.nextLine();
			}
		} while (!ok);	
	}
	
	/*****************************************************************************************************/
	
	/**
	 * Método al que le paso un identificador como parametro,
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
	
	
	/**
	 * modeloAEliminar - Método que devuelve el identificador de modelo de coche a
	 * eliminar
	 * 
	 * @return id
	 */
	public int modeloAEliminar() {
		int id = 0;
		ArrayList<Modelo> arrayModelos = db.cargaModelo();
		for (int i = 0; i < arrayModelos.size(); i++) {
			if ((arrayModelos.get(i).getModelo()).equalsIgnoreCase((String) cmbModelo.getSelectedItem())) {
				id = arrayModelos.get(i).getId();
			}
		}
		return id;
	}
}
