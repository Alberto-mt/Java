package controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Madera;

public class FicheroNOLegible_escribir {
	
	/*
	 * ***   public class Maderas implements Serializable{}
	 * ***   public class Deportista implements Serializable{}
	 */
	
	/**
	 * Método que escribe un fichero no legible de un array
	 * @param arrayMadera
	 */
	public void escribeFicheroNoLeg(ArrayList<Madera> arrayMadera) {
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
	
	/**************************************************************************************************/
	
	/**
	 * Método que escribe un fichero no legible de un objeto pasado por parámetro
	 * @param i
	 */
	public void ficheroDeportista(Deportista i) {
        FileOutputStream fichero = null;
        ObjectOutputStream tuberia = null;
        try {
            String data = "Agregando datos a fichero deportista...";
            fichero = new FileOutputStream(i.getNombre() + ".olimpiadas");
            tuberia = new ObjectOutputStream(fichero);

            tuberia.writeObject(i);
            //Información agregada al fichero
            System.out.println("- Ficha de inscripción creada -");
        } catch (IOException e) {
            System.err.println("Error: al crear fichero, fichero no creado");
        } finally {
            try {
                /*Cierra instancias de FileWriter y BufferedWriter*/
                if (fichero != null) {
                    fichero.close();
                }
                if (tuberia != null) {
                    tuberia.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: al cerrar el fichero");
            }
        }
    }
}
