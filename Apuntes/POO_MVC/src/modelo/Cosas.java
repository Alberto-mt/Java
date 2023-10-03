package modelo;

import modelo.Cosas.Tipo;

public class Cosas {
	public enum Tipo {
		ANIMALES, VEGETALES, MINERALES
	}

	private Tipo dato;
	private String nombre;
	private int numero;

	public Cosas() {
		dato = null;
		nombre = "";
		numero = 0;
	}

	public Cosas(Tipo dato, String nombre, int numero) {
		this.dato = dato;
		this.nombre = nombre;
		this.numero = numero;
	}

	public Tipo getDato() {
		return dato;
	}

	public void setDato(Tipo dato) {
		this.dato = dato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
