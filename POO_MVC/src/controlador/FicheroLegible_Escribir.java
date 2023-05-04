package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import modelo.Cosas;

public class FicheroLegible_Escribir {

	ArrayList<Cosas> arrayAnimales = new ArrayList();
	/**
	 * Método que escribe un fichero legible con los datos de un array
	 * @param arrayAnimales
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
	
	/*******************************************************************************************************************/
	/**
	 * Método que escribe un fichero legible con los datos de un objeto
	 * @param i
	 */
	public void legibleEscribefichero(Coche i) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String data = "Agregando datos a fichero SQL...";
            String fichero = "COCHES.txt";
            File file = new File(fichero);
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            /*Flag true, indica adjuntar información al fichero*/
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(i.getMarca());
            bw.newLine();
            bw.write(i.getModelo());
            bw.newLine();
            bw.write("" + i.getAnio());
            bw.newLine();
            bw.write("" + i.getTipo());
            bw.newLine();

            //Información agregada al fichero
            System.out.println("\n- Insertado en fichero -\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("- Error: al crear fichero, fichero no creado");
        } finally {
            try {
                /*Cierra instancias de FileWriter y BufferedWriter*/
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: al cerrar el fichero");
            }
        }
    }
}
