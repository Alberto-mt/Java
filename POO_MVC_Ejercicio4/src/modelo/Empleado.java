package modelo;

public abstract class Empleado {
	private String nombre;
	private String apellidos;
	private String categoría;
	private int tarifa;
	private int salario;
	/**
	 * 
	 */
	public Empleado() {
		super();
		nombre = "";
		apellidos = "";
		categoría = "";
		tarifa = 0;
		salario = 0;
	}
	/**
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param categoría
	 * @param tarifa
	 * @param salario
	 */
	public Empleado(String nombre, String apellidos, String categoría, int tarifa, int salario) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoría = categoría;
		this.tarifa = tarifa;
		this.salario = salario;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * 
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * 
	 * @return
	 */
	public String getCategoría() {
		return categoría;
	}
	/**
	 * 
	 * @param categoría
	 */
	public void setCategoría(String categoría) {
		this.categoría = categoría;
	}
	/**
	 * 
	 * @return
	 */
	public int getTarifa() {
		return tarifa;
	}
	/**
	 * 
	 * @param tarifa
	 */
	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}
	/**
	 * 
	 * @return
	 */
	public int getSalario() {
		return salario;
	}
	/**
	 * 
	 * @param salario
	 */
	public void setSalario(int salario) {
		this.salario = salario;
	}
	/**
	 * 
	 */
	public abstract void calcularSalario(String categoria,int tarifa);
	
}
