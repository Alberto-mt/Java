package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Informatico;
import modelo.Profesor;

public class Gestiona {
	ArrayList<Informatico> arrayInformatico = new ArrayList();
	Informatico informatico = new Informatico();
	OrdenarArray ordenar = new OrdenarArray();
	public void programa() {
		ficheroLegible_CargarArray();
		ordenar.ordenarArrayList(arrayInformatico);
		mostrarArray();
	}
	
	public static void error(String linea) throws Excepciones {
		if (linea == "ERROR") {
			throw new Excepciones("Error encontrado");
		}
	}
	
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
	/**
	 * Método que carga Jtable
	 * @param modeloTabla
	 * @param arrayProfesor
	 */
	public void cargaTabla(DefaultTableModel modeloTabla, ArrayList<Informatico> arrayInformatico) {
		modeloTabla.setRowCount(0);
		for (Informatico in : arrayInformatico) {
            modeloTabla.addRow(new Object[]{
					in.getNombre(),
					in.getApellidos(),
					in.getCategoría(),
					in.getSalario()
			});

        }	
	}
}
