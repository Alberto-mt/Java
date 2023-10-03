package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import modelo.Informatico;
/**
 * Clase Ordenar
 * @author Alberto
 *
 */
public class OrdenarArray {
	/**
	 * Metodo que ordena
	 * @param arrayInformatico
	 */
	public static void ordenarArrayList(ArrayList<Informatico> arrayInformatico) {
		Collections.sort(arrayInformatico, new Comparator<Informatico>() {
			
			public int compare(Informatico e1, Informatico e2) {
				return new String(e2.getApellidos().concat(e2.getNombre()))
						.compareTo(new String(e1.getApellidos().concat(e1.getNombre())));
			}
		});
	}
}
