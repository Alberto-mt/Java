package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.ExcepcionesPropias;
import modelo.Madera;
import modelo.Metal;

public class Mostrar {
	Scanner teclado = new Scanner(System.in, "ISO-8859-1");

	Madera mad = new Madera();
	Metal met = new Metal();

	/*
	 * excepciones propias
	 */
	public static void intervaloPrecio(float precio) throws ExcepcionesPropias {
		if (precio < 0 || precio > 999) {
			throw new ExcepcionesPropias("precio fuera de rango");
		}
	}

	public Madera rellenaMadera() {
		String mensaje = "";
		// Introduce tipo
		boolean ok = false;
		int tipo;
		do {
			try {
				System.out.println("\tIntroduce el tipo de madera: ");
				System.out.println("\t1. DM");
				System.out.println("\t2. CONTRACHAPADO");
				System.out.println("\t3. AGLOMERADO");
				System.out.print("\tTipo: ");
				tipo = teclado.nextInt();
				switch (tipo) {
				case 1:
					mad.setTipo(Madera.Tipo.DM);
					teclado.nextLine();
					ok = true;
					break;

				case 2:
					mad.setTipo(Madera.Tipo.CONTRACHAPADO);
					teclado.nextLine();
					ok = true;
					break;

				case 3:
					mad.setTipo(Madera.Tipo.AGLOMERADO);
					teclado.nextLine();
					ok = true;
					break;
				default:
					System.out.println("Error: Opción incorrecta");
					System.out.print("Elija una opcion: \n");
				}
			} catch (InputMismatchException e2) {
				System.out.println("- Solo puede escribir números -");
				teclado.nextLine();
			}
		} while (!ok);
		// Introduce precio
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce precio: ");
				mad.setPrecio(teclado.nextFloat());
				teclado.nextLine();
				ok = true;
				
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		/*
		 * Controlar Excepcion Propia
		 ************************************************/
		try {			
			intervaloPrecio(mad.getPrecio());
		} catch (ExcepcionesPropias ee) {
			mensaje = ee.getMessage();
			System.out.println(mensaje);
		}
		/************************************************/
		// Introduce peso
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce peso: ");
				mad.setPeso(teclado.nextFloat());
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		// Introduce unidades
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce unidades: ");
				mad.setUnidades(teclado.nextInt());
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		return mad;
	}

	public Metal rellenaMetal() {
		String mensaje = "";
		// Introduce tipo
		boolean ok = false;
		int tipo;
		do {
			try {
				System.out.println("\tIntroduce el tipo de madera: ");
				System.out.println("\t1. ALUMINIO");
				System.out.println("\t2. ACERINOX");
				System.out.println("\t3. HIERRO");
				System.out.print("\tTipo: ");
				tipo = teclado.nextInt();

				switch (tipo) {
				case 1:
					met.setTipo(Metal.Tipo.ALUMINIO);
					teclado.nextLine();
					ok = true;
					break;

				case 2:
					met.setTipo(Metal.Tipo.ACERINOX);
					teclado.nextLine();
					ok = true;
					break;

				case 3:
					met.setTipo(Metal.Tipo.HIERRO);
					teclado.nextLine();
					ok = true;
					break;
				default:
					System.out.println("Error: Opción incorrecta");
					System.out.print("Elija una opcion: \n");
				}
			} catch (InputMismatchException e2) {
				System.out.println("- Solo puede escribir números -");
				teclado.nextLine();
			}
		} while (!ok);
		// Introduce precio
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce precio: ");
				met.setPrecio(teclado.nextFloat());
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e2) {
				System.out.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		/*
		 * Controlar Excepcion Propia
		 ************************************************/
		try {			
			intervaloPrecio(met.getPrecio());

		} catch (ExcepcionesPropias ee) {
			mensaje = ee.getMessage();
			System.out.println(mensaje);
		}
		/************************************************/
		// Introduce peso
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce peso: ");
				met.setPeso(teclado.nextFloat());
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e2) {
				System.err.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		// Introduce unidades
		ok = false;
		do {
			try {
				System.out.print("\tIntroduce unidades: ");
				met.setUnidades(teclado.nextInt());
				teclado.nextLine();
				ok = true;
			} catch (InputMismatchException e2) {
				System.err.println("\tError: Solo puedes escribir numeros");
				teclado.nextLine();
			}
		} while (!ok);
		return met;
	}

	/*
	 * Mostrar
	 */

	public void mostrarMadera(Madera mad) {
		System.out.println("____________________________");
		System.out.println("Tipo: " + mad.getTipo());
		System.out.println("Precio: " + mad.getPrecio());
		System.out.println("Numero: " + mad.getPeso());
		System.out.println("Unidades: " + mad.getUnidades());
		System.out.println("____________________________");
	}

	public void mostrarMetal(Metal met) {
		System.out.println("____________________________");
		System.out.println("Tipo: " + met.getTipo());
		System.out.println("Precio: " + met.getPrecio());
		System.out.println("Numero: " + met.getPeso());
		System.out.println("Unidades: " + met.getUnidades());
		System.out.println("____________________________");
	}
}
