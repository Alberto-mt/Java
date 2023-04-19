package controlador;

import vista.AreaDatos;
import vista.ComboDatos;
import vista.ListaDatos;
import vista.TablaDatos;

/**
 * Clase que por medio de su método principal ejecuta la aplicación
 * @author Alberto
 *
 */
public class Ejecutador {
	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		Gestiona ges = new Gestiona();
		ges.programa();
		
		TablaDatos tabla = new TablaDatos();
		tabla.ejecutador(tabla);
		
		ListaDatos lista = new ListaDatos();
		lista.ejecutador(lista);
		
		ComboDatos combo = new ComboDatos();
		combo.ejecutador(combo);
		
		AreaDatos area = new AreaDatos();
		area.ejecutador(area);

	}

}
