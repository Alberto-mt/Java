package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import modelo.Cosas;

public class OrdenarArrays {
	// Ordenar
		public static void ordenarArrayAnimalPorNombre(ArrayList<Cosas> arrayAnimales) {
			Collections.sort(arrayAnimales, new Comparator<Cosas>() {
				public int compare(Cosas a1, Cosas a2) {
					return new String(a1.getNombre()).compareTo(new String(a2.getNombre()));
				}
			});
		}
		
		public static void ordenarArrayVegetalPorNombre(ArrayList<Cosas> arrayVegetales) {
			Collections.sort(arrayVegetales, new Comparator<Cosas>() {
				public int compare(Cosas a1, Cosas a2) {
					return new String(a1.getNombre()).compareTo(new String(a2.getNombre()));
				}
			});
		}
		
		public static void ordenarArrayMineralPorNombre(ArrayList<Cosas> arrayMinerales) {
			Collections.sort(arrayMinerales, new Comparator<Cosas>() {
				public int compare(Cosas a1, Cosas a2) {
					return new String(a1.getNombre()).compareTo(new String(a2.getNombre()));
				}
			});
		}

		/*public int compare(Cosas arg0, Cosas arg1) {
			// TODO Auto-generated method stub
			return 0;
		}*/
}
