package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Alumno;
import modelo.Cosas;
import modelo.Persona;
import modelo.Profesor;

public class Mostrar {
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");
	Profesor pro = new Profesor();
	Alumno alu = new Alumno();
	Persona per = new Persona();
	
	public Profesor rellenaProfesor() {		
		System.out.println("\n- Rellena formulario de un profesor -");
		System.out.print("Introduce nombre: ");
		pro.setNombre(teclado.nextLine());
		System.out.print("Introduce apellido: ");
		pro.setApellido(teclado.nextLine());
		System.out.print("Introduce direccion: ");
		pro.setDireccion(teclado.nextLine());
		System.out.print("Introduce titulacion: ");
		pro.setTitulacion(teclado.nextLine());
		System.out.print("Introduce Experiencia Laboral: ");
		pro.seteLaboral(teclado.nextLine());
		boolean ok = false;
		do {
			try {
				System.out.print("Introduce número de examenes: ");
				pro.setCorregir(teclado.nextInt());
				teclado.nextLine();
				ok = true;
				
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		return pro;
	}
	
	public Alumno rellenaAlumno() {	
		System.out.println("\n- Rellena formulario de un alumno -");
		System.out.print("Introduce nombre: ");
		alu.setNombre(teclado.nextLine());
		System.out.print("Introduce apellido: ");
		alu.setApellido(teclado.nextLine());
		System.out.print("Introduce direccion: ");
		alu.setDireccion(teclado.nextLine());
		System.out.print("Introduce Centro de procedencia: ");
		alu.setProcedencia(teclado.nextLine());
		boolean ok = false;
		do {
			try {
				System.out.print("Introduce número de pagos: ");
				alu.setPagos(teclado.nextInt());
				teclado.nextLine();
				ok = true;
				
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		return alu;
	}
	
	public void mostrarProfesor(Profesor pro) {
		System.out.println("____________________________");
		System.out.println("Nombre: " + pro.getNombre());
		System.out.println("Apellido: " + pro.getApellido());
		System.out.println("Direccion: " + pro.getDireccion());
		System.out.println("Titulacion: " + pro.getTitulacion());
		System.out.println("Experiencia laboral: " + pro.geteLaboral());
		pro.corregirExamenes(pro.getCorregir());
		System.out.println("____________________________");
	}

	public void mostrarAlumno(Alumno alu) {
		System.out.println("____________________________");
		System.out.println("Nombre: " + alu.getNombre());
		System.out.println("Apellido: " + alu.getApellido());
		System.out.println("Direccion: " + alu.getDireccion());
		System.out.println("Centro Procedencia: " + alu.getProcedencia());
		alu.beca(alu.getPagos());
		System.out.println("____________________________");
	}
	/************************************************************************************************************/
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
	/***************************************************************************************/

}
