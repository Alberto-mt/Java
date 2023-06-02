package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelo.Cosas;
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
}
