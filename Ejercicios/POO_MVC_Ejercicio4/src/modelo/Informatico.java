package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Informatico extends Empleado implements Imprimible {

	private String nombre;
	private String apellidos;
	private String categoría;
	private int tarifa;
	private int salario;
	
	/**
	 * Método que carga propiedades de fichero de propiedades
	 * @param propiedad
	 * @return	variable
	 */
	public String ficheroPropiedades(String propiedad) {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String variable = "";
		try {
			// Objeto InputStream con el conexion.properties
			entrada = new FileInputStream("fichero.properties");
			// Cargar el archivo .properties
			propiedades.load(entrada);
			// Obtener las propiedades
			variable = propiedades.getProperty(propiedad);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return variable;
	}
	//Variables cargadas de fichero de propiedades	
	String cat1 = ficheroPropiedades("RESPONSABLE");
	String cat2 = ficheroPropiedades("DIRECTOR");
	String cat3 = ficheroPropiedades("PROGRAMADOR");
	
	@Override
	public void calcularSalario(String categoria, int tarifa) {		
		if (categoria.equalsIgnoreCase("RESPONSABLE")) {
			tarifa *= Integer.parseInt(cat1);
			System.out.println("Salario: " + tarifa);
		}
		if (categoria.equalsIgnoreCase("RESPONSABLE")) {
			tarifa *= Integer.parseInt(cat2);
			System.out.println("Salario: " + tarifa);
		}
		if (categoria.equalsIgnoreCase("RESPONSABLE")) {
			tarifa *= Integer.parseInt(cat3);
			System.out.println("Salario: " + tarifa);
		}
	}

	@Override
	public void mostrar(Informatico inf) {
		System.out.println("____________________________");
		System.out.println("Nombre: " + inf.getNombre());
		System.out.println("Apellidos: " + inf.getApellidos());
		System.out.println("Categoría: " + inf.getCategoría());
		System.out.println("Tarifa: " + inf.getTarifa());
		//inf.calcularSalario(inf.getCategoría(), inf.getTarifa());				
		System.out.println("____________________________");
		
	}

}
