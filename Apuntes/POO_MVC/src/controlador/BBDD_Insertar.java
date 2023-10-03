package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelo.Marca;
import modelo.Modelo;
import modelo.Producto;
import vista.Mostrar;

public class BBDD_Insertar {

	/*
	 * Declaro objeto teclado para poder introducir datos. Con "ISO-8859-1",
	 * controlo la entrada por teclado de caracteres especiales.
	 */
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	/* Creo objeto 'v' para acceder a los métodos de mostrar */
	Mostrar mostrar = new Mostrar();
	/*
	 * arrayProducto - Este ArrayList es genérico a toda la aplicacion para poder
	 * insertar y mostrar objetos de tipo Producto
	 */
	ArrayList<Producto> arrayProductos = new ArrayList<Producto>();

	/**
	 *  Método que guarda un objeto en BBDD
	 * 
	 * @param arrayProductos
	 */
	public void insertarBBDD(ArrayList<Producto> arrayProductos) {
		boolean ok = false;
		do {
			try {
				int numProductos = 0;
				do {
					Producto i = new Producto();
					v = new Mostrar();
					mostrar = v.pedirDatos();
					mostrar.muestraProducto(i);
					Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_productos", "root","");
					Statement consulta = conexion.createStatement();
					consulta.executeUpdate("insert into producto (nombre, unidades, precio) values('" + i.getNombre()
							+ "'," + i.getUnidades() + "," + i.getPrecio() + ")");
					conexion.close();
					arrayProductos.add(i);
					numProductos++;
					System.out.println("- Producto inscrito correctamente -\n\n\n");
				} while (numProductos > 100);
				teclado.nextLine();
				ok = true;
			} catch (SQLException e) {
				System.err.println("\tError: Producto no inscrito");
				teclado.nextLine();
			}
		} while (!ok);
	}

	/**
	 * Método que muestra tabla de BBDD
	 */
	public void mostrarBBDD() {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_productos", "root", "");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select idProducto, nombre " + "from producto ");
			while (registro.next()) {
				String nom = registro.getString("nombre");
				System.out.println("\nIdProducto: " + registro.getString("idProducto"));
				System.out.println("Nombre: " + registro.getString("nombre"));
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**********************************************************************************************************/
	
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
	 * obtenerIdMarca() - Método con el obtengo el IdMarca y lo devuelve como un int
	 * 
	 * @return id
	 */
	public int obtenerIdMarca() {
		int id = 0;
		ArrayList<Marca> arrayMarcas = db.cargaMarca();
		for (int i = 0; i < arrayMarcas.size(); i++) {
			if ((arrayMarcas.get(i).getNombre()).equalsIgnoreCase((String) cmbMarca.getSelectedItem())) {
				id = arrayMarcas.get(i).getIdMarca();
			}
		}
		return id;
	}
}
