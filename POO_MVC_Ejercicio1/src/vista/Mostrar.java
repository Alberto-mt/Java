package vista;

import modelo.Cosas;

public class Mostrar {
	/**
	 * Método que muestra menú principal.
	 */
	public void muestraMenu() {
		System.out.println("\n_____________________________________________\n");
		System.out.println("\t\tMENU ");
		System.out.println("_____________________________________________\n");
		System.out.println("1. Lee Animales.txt");
		System.out.println("2. Lee Vegetales.txt");
		System.out.println("3. Lee Minerales.txt");
		System.out.println("0. Salir\n");
		System.out.print("Elija una opcion: ");
	}

	/**
	 * despedida() - Método que muestra un mensaje de despedida.
	 */
	public void despedida() {
		System.out.println("\n- Hasta pronto -");
	}
	
	/***************************************************************************************/
	public void mostrarCosas(Cosas c) {
		System.out.println("____________________________");
		System.out.println("Dato: " + c.getDato());
		System.out.println("Nombre: " + c.getNombre());
		System.out.println("Numero: " + c.getNumero());
		System.out.println("____________________________");
	}
	
	public void mostrarAnimal(Cosas a) {
		System.out.println("____________________________");
		System.out.println("Dato: " + Cosas.Tipo.ANIMALES);
		System.out.println("Nombre: " + a.getNombre());
		System.out.println("Numero: " + a.getNumero());
		System.out.println("____________________________");
	}
	
	public void mostrarVegetal(Cosas v) {
		System.out.println("____________________________");
		System.out.println("Dato: " + Cosas.Tipo.VEGETALES);
		System.out.println("Nombre: " + v.getNombre());
		System.out.println("Numero: " + v.getNumero());
		System.out.println("____________________________");
	}
	
	public void mostrarMineral(Cosas m) {
		System.out.println("____________________________");
		System.out.println("Dato: " + Cosas.Tipo.MINERALES);
		System.out.println("Nombre: " + m.getNombre());
		System.out.println("Numero: " + m.getNumero());
		System.out.println("____________________________");
	}
}
