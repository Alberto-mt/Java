package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objeto de la clase deportista.
 * Implemento la interfaz Serializable.
 * @author Alberto
 */
public class Deportista implements Serializable{

    /**
     * Declaro atributos de la clase Deportista.
     */
    private String nombre;
    private String pais;
    private int nMedallas;
    private ArrayList<Medallas> arrayMedallas;

    /**
     * Método constructor por defecto de la clase Deportista.
     */
    public Deportista() {
        nombre = "";
        pais = "";
        nMedallas = 0;
        arrayMedallas = null;
    }

    /**
     * Método constructor por parámetros de la clase Deportista.
     *
     * @param nombre Nombre del deportista.
     * @param pais País que representa el deportista.
     * @param nMedallas Número de medallas conseguidas por el deportista.
     * @param arrayMedallas Array de medallas conseguidas por el deportista.
     */
    public Deportista(String nombre, String pais, int nMedallas, ArrayList<Medallas> arrayMedallas) {
        this.nombre = nombre;
        this.pais = pais;
        this.nMedallas = nMedallas;
        this.arrayMedallas = arrayMedallas;
    }

    /**
     * Método para leer el nombre del deportista.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para escribir el nombre del deportista.
     *
     * @param nombre Nombre del deportista.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para leer el país del deportista.
     *
     * @return país
     */
    public String getPais() {
        return pais;
    }

    /**
     * Método para escribir el país del deportista.
     *
     * @param pais País del deportista.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Método para leer el número de medallas del deportista.
     *
     * @return nMedallas
     */
    public int getnMedallas() {
        return nMedallas;
    }

    /**
     * Método para escribir el número de medallas del deportista.
     *
     * @param nMedallas Número de medallas del deportista
     */
    public void setnMedallas(int nMedallas) {
        this.nMedallas = nMedallas;
    }

    /**
     * Método para leer el Array de medallas del deportista.
     *
     * @return arrayMedallas
     */
    public ArrayList<Medallas> getArrayMedallas() {
        return arrayMedallas;
    }

    /**
     * Método para escribir el array de medallas del deportista.
     *
     * @param arrayMedallas Array de medallas del deportista
     */
    public void setArrayMedallas(ArrayList<Medallas> arrayMedallas) {
        this.arrayMedallas = arrayMedallas;
    }
}
