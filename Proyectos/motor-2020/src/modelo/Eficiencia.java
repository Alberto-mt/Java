package modelo;

/**
 * Eficiencia - Clase en la que defino el objeto Eficiencia, así como sus
 * métodos de escritura y de lectura
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
	 * Constructor por parámetros.
	 * 
	 * @param cEnergetica - Primary key del objeto Eficiencia
	 * @param descripcion - Descripcion del objeto Eficiencia
	 * @param icono       - Nombre del fichero gráfico con la imagen correspondiente
	 *                    a la clasificación energética
	 */
	public Eficiencia(String cEnergetica, String descripcion, String icono) {
		this.cEnergetica = cEnergetica;
		this.descripcion = descripcion;
		this.icono = icono;
	}

	/**
	 * getcEnergetica() - Método que obtiene la cEnergetica de Eficiencia.
	 * 
	 * @return cEnergetica
	 */
	public String getcEnergetica() {
		return cEnergetica;
	}

	/**
	 * setcEnergetica(String cEnergetica) - Método que establece la cEnergetica de
	 * Eficiencia.
	 * 
	 * @param cEnergetica - Identificador de clasificación energética
	 */
	public void setcEnergetica(String cEnergetica) {
		this.cEnergetica = cEnergetica;
	}

	/**
	 * String getDescripcion() - Método que obtiene la descripción de Eficiencia.
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * setDescripcion(String descripcion) - Método que establece la descripción de
	 * Eficiencia.
	 * 
	 * @param descripcion - Descripción de clasificación energética
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * getIcono() - Método que obtiene el icono de Eficiencia.
	 * 
	 * @return icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * setIcono(String icono) - Método que establece el icono de Eficiencia.
	 * 
	 * @param icono - Icono de clasificación energética
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
