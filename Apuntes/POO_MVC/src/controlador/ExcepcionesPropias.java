package controlador;

import java.util.InputMismatchException;

public class ExcepcionesPropias {
	public ExcepcionesPropias(String mensaje) {
        super(mensaje);
    }
	
	/****************************************************************/
	
	/*
	 * En Mostrar
	 */
	//Primero
	/*
	 * excepciones propias
	 */
	public static void intervaloPrecio(float precio) throws ExcepcionesPropias {
		if (precio < 0 || precio > 999) {
			throw new ExcepcionesPropias("precio fuera de rango");
		}
	}
	
	//Segundo - en formulario
	
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
}
