package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FicheroLegible_Leer {

	public void leerBuffer() {
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
}