package controlador;

import vista.TablaDatos;

/**
 * 
 * @author Alberto
 *
 */
public class Ejecutador {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Gestiona ges = new Gestiona();
		ges.programa();
		TablaDatos tabla = new TablaDatos();
		tabla.ejecutador(tabla);
	}
}
