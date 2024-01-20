package modelo;

/**
 * Eficiencia - Clase en la que defino el objeto Eficiencia, as� como sus
 * m�todos de escritura y de lectura
 * 
 * @author Alberto
 *
 */
public class Eficiencia {

	/**
	 * Declaro atributos de la clase Eficiencia.
	 */
	private String cEnergetica;
	private String descripcion;
	private String icono;

	/**
	 * Eficiencia() - Constructor por defecto.
	 */
	public Eficiencia() {
		String cEnergetica = "";
		String descripcion = "";
		String icono = "";
	}

	/**
	 * Eficiencia(String cEnergetica, String descripcion, String icono) -
	 * Constructor por par�metros.
	 * 
	 * @param cEnergetica - Primary key del objeto Eficiencia
	 * @param descripcion - Descripcion del objeto Eficiencia
	 * @param icono       - Nombre del fichero gr�fico con la imagen correspondiente
	 *                    a la clasificaci�n energ�tica
	 */
	public Eficiencia(String cEnergetica, String descripcion, String icono) {
		this.cEnergetica = cEnergetica;
		this.descripcion = descripcion;
		this.icono = icono;
	}

	/**
	 * getcEnergetica() - M�todo que obtiene la cEnergetica de Eficiencia.
	 * 
	 * @return cEnergetica
	 */
	public String getcEnergetica() {
		return cEnergetica;
	}

	/**
	 * setcEnergetica(String cEnergetica) - M�todo que establece la cEnergetica de
	 * Eficiencia.
	 * 
	 * @param cEnergetica - Identificador de clasificaci�n energ�tica
	 */
	public void setcEnergetica(String cEnergetica) {
		this.cEnergetica = cEnergetica;
	}

	/**
	 * String getDescripcion() - M�todo que obtiene la descripci�n de Eficiencia.
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * setDescripcion(String descripcion) - M�todo que establece la descripci�n de
	 * Eficiencia.
	 * 
	 * @param descripcion - Descripci�n de clasificaci�n energ�tica
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * getIcono() - M�todo que obtiene el icono de Eficiencia.
	 * 
	 * @return icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * setIcono(String icono) - M�todo que establece el icono de Eficiencia.
	 * 
	 * @param icono - Icono de clasificaci�n energ�tica
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
