package controlador;

import vista.JFprograma;

/**
 * Motor2020 - Clase que ejecuta la aplicaci�n a traves de su m�todo principal.
 * 
 * @author Alberto
 *
 */
public class Motor2020 {
	/**
	 * main - M�todo principal.
	 * 
	 * @param args JFprograma y su m�todo cargaVentana(ventana).
	 */
	public static void main(String[] args) {
		/*
		 * Creo objeto para acceder a sus m�todos. Ejecuto aplicaci�n.
		 */
		GestionaCargaBBDD motor2020 = new GestionaCargaBBDD();
		motor2020.runMotor2020();
	}
}
