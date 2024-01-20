package controlador;

import vista.JFprograma;

/**
 * Motor2020 - Clase que ejecuta la aplicación a traves de su método principal.
 * 
 * @author Alberto
 *
 */
public class Motor2020 {
	/**
	 * main - Método principal.
	 * 
	 * @param args JFprograma y su método cargaVentana(ventana).
	 */
	public static void main(String[] args) {
		/*
		 * Creo objeto para acceder a sus métodos. Ejecuto aplicación.
		 */
		GestionaCargaBBDD motor2020 = new GestionaCargaBBDD();
		motor2020.runMotor2020();
	}
}
