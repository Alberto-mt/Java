package modelo;

public abstract class Empleado {
	private String nombre;
	private String apellidos;
	private String categor�a;
	private int tarifa;
	private int salario;
	/**
	 * 
	 */
	public Empleado() {
		super();
		nombre = "";
		apellidos = "";
		categor�a = "";
		tarifa = 0;
		salario = 0;
	}
	/**
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param categor�a
	 * @param tarifa
	 * @param salario
	 */
	public Empleado(String nombre, String apellidos, String categor�a, int tarifa, int salario) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categor�a = categor�a;
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
	public String getCategor�a() {
		return categor�a;
	}
	/**
	 * 
	 * @param categor�a
	 */
	public void setCategor�a(String categor�a) {
		this.categor�a = categor�a;
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
