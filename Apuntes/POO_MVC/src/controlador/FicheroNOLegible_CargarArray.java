package controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import modelo.Madera;
import vista.Mostrar;

public class FicheroNOLegible_CargarArray {
	/*
	 * ***   public class Maderas implements Serializable{}
	 */
	
	/**
	 * Método que lee de fichero no legible y lo guarda en un array
	 * @return
	 */
	public ArrayList<Madera> leeFichNoLeg() {
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

	/**
	 * Método que muestra el array leido y cargado anteriormente
	 */
	public void mostrarArchivoMadera() {
		ArrayList<Madera> maderaFromArchive = leeFichNoLegMadera();
		mostrar = new Mostrar();
		for (Madera mad : maderaFromArchive) {
			mostrar.mostrarMadera(mad);
		}
	}
}
