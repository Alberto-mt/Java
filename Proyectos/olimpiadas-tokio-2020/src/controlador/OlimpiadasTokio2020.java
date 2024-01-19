package controlador;

import java.util.Scanner;
import vista.Mostrar;

/**
 * Clase que muestra el método principal de la aplicación por consola.
 * @author Alberto
 */
public class OlimpiadasTokio2020 {

    /**
     * Muestra el menú y permite elegir opciones.
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        /*creo objeto de la clase Mostrar, para poder acceder a sus métodos*/
        GestionaOlimpiadas ges = new GestionaOlimpiadas();
        ges.index();  
    }
}
