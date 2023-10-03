package controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Madera;
import modelo.Materiales;
import modelo.Metal;
import vista.Mostrar;

public class Gestiona {
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	Mostrar mostrar = new Mostrar();
	ArrayList<Madera> arrayMadera = new ArrayList();
	ArrayList<Metal> arrayMetal = new ArrayList();

	ArrayList<Madera> maderaFromArchive = new ArrayList();
	ArrayList<Metal> metalFromArchive = new ArrayList();

	public void programa() {
		insertaMadera();
		insertaMadera();
		insertaMetal();
		insertaMetal();
		
		escribeFicheroNoLegMadera(arrayMadera);
		escribeFicheroNoLegMetal(arrayMetal);
		
		//mostrarMaderas();
		//mostrarMetales();
		
		leeFichNoLegMadera();
		leeFichNoLegMetal();
		
		Madera madera = new Madera();
		madera.mostrar();
		madera.calcular();
		mostrarArchivoMadera();
		
		Metal metal = new Metal();
		metal.mostrar();
		metal.calcular();
		mostrarArchivoMetal();
	}

	/*
	 * Insertar
	 */
	public void insertaMadera() {
		System.out.println("-Introduce un objeto madera-");
		boolean ok = false;
		do {
			try {
				/* */
				Madera mad = new Madera();
				mostrar = new Mostrar();
				mad = mostrar.rellenaMadera();
				// Fichero por coche
				arrayMadera.add(mad);
				ok = true;
			} catch (Exception e) {
				System.err.println("\tError: Deportista no inscrito");
				teclado.nextLine();
			}
		} while (!ok);
	}

	public void insertaMetal() {
		System.out.println("-Introduce un objeto metal-");
		boolean ok = false;
		do {
			try {
				/*  */
				Metal met = new Metal();
				mostrar = new Mostrar();
				met = mostrar.rellenaMetal();
				// Fichero por coche
				arrayMetal.add(met);
				ok = true;
			} catch (Exception e) {
				System.err.println("\tError: Deportista no inscrito");
				teclado.nextLine();
			}
		} while (!ok);
	}

	/*
	 * Escribir
	 */
	public void escribeFicheroNoLegMadera(ArrayList<Madera> arrayMadera) {
		ObjectOutputStream ous;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("maderas.txt");
			ous = new ObjectOutputStream(fos);
			for (int x = 0; x < arrayMadera.size(); x++) {
				ous.writeObject(arrayMadera.get(x));
			}
			ous.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Eror: no se ha encontrado la clase");
		} catch (IOException ex) {
			System.err.println("Error: al escribir en el fichero");
		} finally {
			try {
				fos.close();
			} catch (IOException ex) {
				System.err.println("Error: al cerrar en el fichero");
			}
		}
	}

	public void escribeFicheroNoLegMetal(ArrayList<Metal> arrayMetal) {
		ObjectOutputStream ous;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("metales.txt");
			ous = new ObjectOutputStream(fos);
			for (int x = 0; x < arrayMetal.size(); x++) {
				ous.writeObject(arrayMetal.get(x));
			}
			ous.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Eror: no se ha encontrado la clase");
		} catch (IOException ex) {
			System.err.println("Error: al escribir en el fichero");
		} finally {
			try {
				fos.close();
			} catch (IOException ex) {
				System.err.println("Error: al cerrar en el fichero");
			}
		}
	}

	/*
	 * Mostrar
	 */
	public void mostrarMaderas() {
		mostrar = new Mostrar();
		for (Madera mad : arrayMadera) {
			mostrar.mostrarMadera(mad);
		}
	}

	public void mostrarMetales() {
		mostrar = new Mostrar();
		for (Metal met : arrayMetal) {
			mostrar.mostrarMetal(met);
		}
	}

	/*
	 * Leer
	 */
	public ArrayList<Madera> leeFichNoLegMadera() {
		FileInputStream fis = null;
		ArrayList<Madera> maderaFromArchive = new ArrayList();
		try {
			fis = new FileInputStream("maderas.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Madera mad = null;
			while (true) {
				try {
					mad = (Madera) ois.readObject();
					maderaFromArchive.add((Madera) mad);
				} catch (EOFException ex) {
					break;
				}
			}
			ois.close();
			return maderaFromArchive;

		} catch (EOFException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("No se encuentra el fichero");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void mostrarArchivoMadera() {
		ArrayList<Madera> maderaFromArchive = leeFichNoLegMadera();
		mostrar = new Mostrar();
		for (Madera mad : maderaFromArchive) {
			mostrar.mostrarMadera(mad);
		}
	}

	public ArrayList<Metal> leeFichNoLegMetal() {
		FileInputStream fis = null;
		ArrayList<Metal> metalFromArchive = new ArrayList();
		try {
			fis = new FileInputStream("metales.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Metal met = null;
			while (true) {
				try {
					met = (Metal) ois.readObject();
					metalFromArchive.add(met);
				} catch (EOFException ex) {
					break;
				}
			}
			ois.close();
			return metalFromArchive;
		} catch (EOFException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("No se encuentra el fichero");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void mostrarArchivoMetal() {
		ArrayList<Metal> metalFromArchive = leeFichNoLegMetal();
		mostrar = new Mostrar();
		for (Metal met : metalFromArchive) {
			mostrar.mostrarMetal(met);
		}
	}

}
