package modelo;

/**
 * Marca - Clase en la que defino el objeto Marca, as� como sus m�todos de
 * escritura, de lectura
 * 
 * @author Alberto
 *
 */
public class Marca {
	/**
	 * Declaro atributos de la clase Marca.
	 */
	private int idMarca;
	private String nombre;

	/**
	 * Marca() - Constructor por defecto.
	 */
	public Marca() {
		int idMarca = 0;
		String nombre = "";
	}

	/**
	 * Marca(int idMarca, String nombre) - Constructor por par�metros.
	 * 
	 * @param idMarca - Primary key del objeto Marca
	 * @param nombre  - Nombre del objeto Marca
	 */
	public Marca(int idMarca, String nombre) {
		this.idMarca = idMarca;
		this.nombre = nombre;
	}

	/**
	 * getIdMarca() - M�todo que obtiene el idMarca de una Marca.
	 * 
	 * @return idMarca
	 */
	public int getIdMarca() {
		return idMarca;
	}

	/**
	 * setIdMarca(int idMarca) - M�todo que establece el idMarca de una Marca.
	 * 
	 * @param idMarca - Identificador de la marca
	 */
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * getNombre() - M�todo que obtiene el nombre de una Marca.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * setNombre(String nombre) - M�todo que establece el nombre de una Marca.
	 * 
	 * @param nombre - Nombre de la marca
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
