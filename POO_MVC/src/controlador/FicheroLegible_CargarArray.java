package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Informatico;


public class FicheroLegible_CargarArray {

	ArrayList<Cosas> arrJu = new ArrayList();
	
	/**
	 * Método que carga un array desde un fchero legible
	 */
	public void ficheroLegible_CargarArray() {
		//Declaramos en un String el nombre del fichero
		String fichero = "Jugadores.txt";
		//Variable que declaramos para recoger las lineas
		String linea = "";
		//Declaramos un contador para saber cuando ya hemos leido un Jugador
		int contador = 0;
		//Variables donde guardaremos las lineas
		String nombre = "";
		String apellidos = "";
		String edad = "";
		int edad1 = 0;
		String pais = "";
		String equipo = "";
		String posicion = "";
		String dorsal = "";
		int dorsal1 = 0;
		Tipo tipo = null;
		String tipo1 = "";
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			while((linea = br.readLine()) != null) {
				if(contador%8==0) {
					nombre = linea;
				}else if(contador%8==1) {
					apellidos = linea;
				}else if(contador%8==2) {
					edad = linea;
				}else if(contador%8==3) {
					pais = linea;
				}else if(contador%8==4) {
					equipo = linea;
				}else if(contador%8==5) {
					posicion = linea;
				}else if(contador%8==6) {
					dorsal = linea;
				}else if(contador%8==7) {
					tipo1 = linea;
					Jugador j = new Jugador();
					j.setNombre(nombre);
					j.setApellidos(apellidos);
					edad1 = Integer.parseInt(edad);
					j.setEdad(edad1);
					j.setPais(pais);
					j.setEquipo(equipo);
					j.setPosicion(posicion);
					dorsal1 = Integer.parseInt(dorsal);
					j.setDorsal(dorsal1);
					tipo = Tipo.valueOf(tipo1);
					j.setTipo(tipo);
					arrJu.add(j);
				}
				contador++;
			}		
			fr.close();
			System.out.println("Fichero leido correctamente.");
		}catch(Exception e) {
			System.err.println("Ha ocurrido un error al leer el fichero.");
		}
	}
	
	/******************************************************************************************************/
	
	/**
	 * Método que carga un array desde un fchero legible
	 */
	public void ficheroLegible_CargarArray() {
		//Declaramos en un String el nombre del fichero
		String fichero = "empleados.dat";
		//Variable que declaramos para recoger las lineas
		String linea = "";
		//Declaramos un contador para saber cuando ya hemos leido un Jugador
		int contador = 0;
		//Variables donde guardaremos las lineas
		String nombre = "";
		String apellidos = "";
		String categoria = "";
		int tarifa = 0;
		int salario = 0;
		String mensaje = "";
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			while((linea = br.readLine()) != null) {
					
					if(contador%4==0) {
						nombre = linea;
					}else if(contador%4==1) {
						apellidos = linea;
					}else if(contador%4==2) {
						tarifa = Integer.parseInt(linea);							
					}else if(contador%4==3) {
						categoria = linea;			
						Informatico inf = new Informatico();
						inf.setNombre(nombre);
						inf.setApellidos(apellidos);
						inf.setCategoría(categoria);
						inf.setTarifa(tarifa);
						arrayInformatico.add(inf);
					}
					contador++;	
			}	
			
			fr.close();
			System.out.println("Fichero leido correctamente.");
		}catch(Exception e) {
			System.err.println("Ha ocurrido un error al leer el fichero.");
		}
	}
	
	public void mostrarArray() {
		System.out.println("--CargandoD--");
		informatico = new Informatico();
		for (Informatico i : arrayInformatico) {
			informatico.mostrar(i);
		}
	}
}
