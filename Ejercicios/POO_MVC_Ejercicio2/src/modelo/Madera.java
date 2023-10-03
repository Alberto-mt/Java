package modelo;

import java.io.Serializable;

public class Madera implements Materiales, Serializable{
	public enum Tipo{
		DM, CONTRACHAPADO, AGLOMERADO
	}
	
	private Tipo tipo;
	private float precio;
	private float peso;
	private int unidades;
	
	public Madera() {
		super();
		tipo = null;
		precio = 0;
		peso = 0;
		unidades = 0;
	}

	public Madera(Tipo tipo, float precio, float peso, int unidades) {
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
		System.out.println("Mostrar Maderas");
	}

	@Override
	public void calcular() {
		// TODO Auto-generated method stub
		System.out.println("Calcular Maderas");
	}

}
