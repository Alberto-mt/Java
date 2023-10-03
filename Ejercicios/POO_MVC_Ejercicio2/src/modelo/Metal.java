package modelo;

import java.io.Serializable;

public class Metal implements Materiales, Serializable{
	public enum Tipo{
		ALUMINIO, ACERINOX, HIERRO
	}
	
	private Tipo tipo;
	private float precio;
	private float peso;
	private int unidades;
	
	public Metal() {
		super();
		tipo = null;
		precio = 0;
		peso = 0;
		unidades = 0;
	}
	
	public Metal(Tipo tipo, float precio, float peso, int unidades) {
		super();
		this.tipo = tipo;
		this.precio = precio;
		this.peso = peso;
		this.unidades = unidades;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub
		System.out.println("Mostrar Metales");
		
	}

	@Override
	public void calcular() {
		// TODO Auto-generated method stub
		System.out.println("Calcular Metales");
	}

}
