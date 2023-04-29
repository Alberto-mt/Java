package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Controlador.OrdenarArrays;
import modelo.Cosas;

public class OrdenarArray {
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
	
	/*
	 * Gestiona
	 */
	OrdenarArrays ordenar = new OrdenarArrays();
	
	cargarArrayAnimales();
	ordenar.ordenarArrayAnimalPorNombre(arrayAnimales);
	printWritterAnimales(arrayAnimales);
	
	cargarArrayVegetales();
	ordenar.ordenarArrayVegetalPorNombre(arrayVegetales);
	printWritterVegetales(arrayVegetales);
	
	cargarArrayMinerales();
	ordenar.ordenarArrayMineralPorNombre(arrayMinerales);
	printWritterMinerales(arrayMinerales);
	
	
	/***************************************************************************************************************************/
	public static void ordenarArrayListPorApellido(ArrayList<Empleado> arrayEmpleados) {
		Collections.sort(arrayEmpleados, new Comparator<Empleado>() {
			
			public int compare(Empleado e1, Empleado e2) {
				return new String(e1.getApellidoUno().concat(e1.getApellidoDos()))
						.compareTo(new String(e2.getApellidoUno().concat(e2.getApellidoDos())));
			}
		});
	}

	public static void ordenarArrayListPorApellidoDescendente(ArrayList<Empleado> arrayEmpleados) {
		Collections.sort(arrayEmpleados, new Comparator<Empleado>() {
			
			public int compare(Empleado e1, Empleado e2) {
				return new String(e2.getApellidoUno().concat(e2.getApellidoDos()))
						.compareTo(new String(e1.getApellidoUno().concat(e1.getApellidoDos())));
			}
		});
	}

}
