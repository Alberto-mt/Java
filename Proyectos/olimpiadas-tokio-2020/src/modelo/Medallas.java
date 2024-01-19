package modelo;

import java.io.Serializable;

/**
 * Objeto de la clase Medallas.
 * Implemento la interfaz Serializable.
 * @author Alberto 
 */
public class Medallas implements Serializable{

    /**
     * Declaro atributos de la clase Deportista.
     */
    public enum TipoMedalla {
        ORO, PLATA, BRONCE;
    }
    private String paisOlimpiada;
    private int anioOlimpiada;
    private TipoMedalla tipo;

    /**
     * Método constructor por defecto de la clase Medalla.
     */
    public Medallas() {
        paisOlimpiada = "";
        anioOlimpiada = 0;
        tipo = null;
    }
    
    /**
     * Método constructor por parámetros de la clase Medalla
     * @param paisOlimpiada     País donde se ganó la medalla.
     * @param anioOlimpiada     Anio en que se ganó la medalla.
     * @param tipo              Tipo de medalla.
     */
    public Medallas(String paisOlimpiada, int anioOlimpiada, TipoMedalla tipo) {
        this.paisOlimpiada = paisOlimpiada;
        this.anioOlimpiada = anioOlimpiada;
        this.tipo = tipo;
    }
    
    /**
     * Método para leer el país donde se ganó la medalla.
     * @return      paisOlimpiada.
     */
    public String getPaisOlimpiada() {
        return paisOlimpiada;
    }

    /**
     * Método para escribir el país donde se ganó la medalla.
     * @param paisOlimpiada     Pais de la Olimpiada.
     */
    public void setPaisOlimpiada(String paisOlimpiada) {
        this.paisOlimpiada = paisOlimpiada;
    }
    
    /**
     * Método para leer el anio en que se ganó la medalla.
     * @return  anioOlimpiada.
     */
    public int getAnioOlimpiada() {
        return anioOlimpiada;
    }

    /**
     * Método para escribir el anio en que se ganó la medalla.
     * @param anioOlimpiada     Anio de la Olimpiada.
     */
    public void setAnioOlimpiada(int anioOlimpiada) {
        this.anioOlimpiada = anioOlimpiada;
    }
 
    /**
     * Método para leer el tipo de medalla que se ganó.
     * @return  tipo
     */
    public TipoMedalla getTipo() {
        return tipo;
    }

    /**
     * Método para escribir el tipo de medalla que se ganó.
     * @param tipo      Tipo de medallla.
     */
    public void setTipo(TipoMedalla tipo) {
        this.tipo = tipo;
    }  
}
