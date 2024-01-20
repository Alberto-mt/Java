package modelo;

/**
 * Modelo - Clase en la que defino el objeto Marca, as� como sus m�todos de
 * escritura y de lectura
 * 
 * @author Alberto
 *
 */
public class Modelo {
	/**
	 * Declaro atributos de la clase Marca.
	 */
	private int id;
	private int idMarca;
	private String modelo;
	private float consumo;
	private float emisiones;
	private String cEnergetica;

	/**
	 * Modelo() - Constructor por defecto.
	 */
	public Modelo() {
		int id = 0;
		int idMarca = 0;
		String modelo = "";
		float consumo = 0.0f;
		float emisiones = 0.0f;
		this.cEnergetica = "";
	}

	/**
	 * Modelo(int id, int idMarca, String modelo, float consumo, float emisiones,
	 * String cEnergetica) - Constructor por par�metros, que hereda de la clase
	 * Marca.
	 * 
	 * @param id          - Primary key del objeto Modelo
	 * @param idMarca     - Foreign key del objeto Modelo
	 * @param modelo      - Nombre del objeto Modelo
	 * @param consumo     - Consumo del objeto Modelo
	 * @param emisiones   - Emisiones del objeto Modelo
	 * @param cEnergetica - Calificaci�n Energ�tica del objeto Modelo
	 */
	public Modelo(int id, int idMarca, String modelo, float consumo, float emisiones, String cEnergetica) {
		this.id = id;
		this.idMarca = idMarca;
		this.modelo = modelo;
		this.consumo = consumo;
		this.emisiones = emisiones;
		this.cEnergetica = cEnergetica;
	}

	/**
	 * getId() - M�todo que obtiene el id de un Modelo.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setIdModelo(int id) - M�todo que establece el id de un Modelo.
	 * 
	 * @param id - Identificador de coche
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getIdMarca() - M�todo que obtiene el idMarca de un Modelo.
	 * 
	 * @return id
	 */
	public int getIdMarca() {
		return idMarca;
	}

	/**
	 * setIdModelo(int idMarca) - M�todo que establece el idMarca de un Modelo.
	 * 
	 * @param idMarca - Identificador de la marca de coche
	 */
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * getModelo() - M�todo que obtiene el modelo de un Modelo.
	 * 
	 * @return modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * setModelo(String modelo) - M�todo que establece el modelo de un Modelo.
	 * 
	 * @param modelo - Modelo de coche
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * getConsumo() - M�todo que obtiene el consumo de un Modelo.
	 * 
	 * @return consumo
	 */
	public float getConsumo() {
		return consumo;
	}

	/**
	 * setConsumo(float consumo) - M�todo que establece el consumo de un Modelo.
	 * 
	 * @param consumo - Consumo de coche
	 */
	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	/**
	 * getEmisiones() - M�todo que obtiene las emisiones de un Modelo.
	 * 
	 * @return emisiones
	 */
	public float getEmisiones() {
		return emisiones;
	}

	/**
	 * setEmisiones(float emisiones) - M�todo que establece las emisiones de un
	 * Modelo.
	 * 
	 * @param emisiones - Emisiones de coche
	 */
	public void setEmisiones(float emisiones) {
		this.emisiones = emisiones;
	}

	/**
	 * String getcEnergetica() - M�todo que obtiene la calificaci�n Energ�tica de un
	 * Modelo.
	 * 
	 * @return cEnergetica
	 */
	public String getcEnergetica() {
		return cEnergetica;
	}

	/**
	 * setcEnergetica(String cEnergetica) - M�todo que establece la calificaci�n
	 * Energ�tica de un Modelo.
	 * 
	 * @param cEnergetica - Clasificaci�n Energ�tica
	 */
	public void setcEnergetica(String cEnergetica) {
		this.cEnergetica = cEnergetica;
	}
}
