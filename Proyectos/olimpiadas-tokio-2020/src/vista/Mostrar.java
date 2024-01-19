package vista;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Deportista;
import modelo.Medallas;

/**
 * Mostrar - Clase que muestra la cara visible de la aplicación.
 *
 * @author Alberto
 */
public class Mostrar {

    /*Declaro objeto teclado para poder introducir datos.
        Con "ISO-8859-1", controlo la entrada por teclado de caracteres
        especiales.
     */
    Scanner teclado = new Scanner(System.in, "ISO-8859-1");

    /* Creo objeto Deportista, al que puedo agregar datos*/
    Deportista d = new Deportista();

    /**
     * Método que muestra menú principal.
     */
    public void muestraMenu() {
        System.out.println("\n_____________________________________________\n");
        System.out.println("\t\tMENU PRINCIPAL");
        System.out.println("_____________________________________________\n");
        System.out.println("1. Insertar deportista");
        System.out.println("2. Consultar deportista");
        System.out.println("3. Generar HTML ");
        System.out.println("4. Salir\n");
        System.out.print("Elija una opcion: ");
    }

    /**
     * Método que rellena los datos de un deportista.
     *
     * @return d
     */
    public Deportista rellenaDeportista() {
        //Introduce nombre
        System.out.println("\n\nInsertando nuevo deportista >>");
        System.out.print("Introduce el nombre del deportista: ");
        d.setNombre(teclado.nextLine().toUpperCase());
        //Introduce país
        System.out.print("Introduce el país del deportista: ");
        d.setPais(teclado.nextLine().toUpperCase());
        //Introduce número de medallas conseguidas
        boolean ok = false;
        do {
            do {
                try {
                    System.out.print("Introduce el número de medallas conseguidas: ");
                    d.setnMedallas(teclado.nextInt());
                    teclado.nextLine();
                    ok = true;
                } catch (InputMismatchException e) {
                    System.err.println("Error: Solo puede escribir números");
                    teclado.nextLine();
                }
            } while (!ok);
            ok = false;
            if (d.getnMedallas() < 0) {
                System.err.println("Error: Ha introducido un número negativo");
            }
        } while (d.getnMedallas() < 0);
        return d;
    }

    /**
     * rellenaMedallas() - Método que rellena los datos de una medalla y los
     * añade a un ArrayList siempre que el deportista hubiera ganado una medalla
     * en el pasado.
     *
     * @return arrayMedallas
     */
    public ArrayList<Medallas> rellenaMedallas() {
        /*arrayMedallas - Este ArrayList me permite insertar datos en un objeto
        de tipo Medallas*/
        ArrayList<Medallas> arrayMedallas = new ArrayList<Medallas>();
        for (int numMedallas = 0; numMedallas < d.getnMedallas(); ++numMedallas) {
            Medallas m = new Medallas();
            //Introduce país medalla
            System.out.println(">> Medalla: " + (numMedallas + 1));
            System.out.print("\tIntroduce el país donde se ganó: ");
            m.setPaisOlimpiada(teclado.nextLine().toUpperCase());
            //Introduce año medalla
            boolean ok = false;
            do {
                do {
                    try {
                        System.out.print("\tIntroduce el año que consiguió la medalla: ");
                        m.setAnioOlimpiada(teclado.nextInt());
                        teclado.nextLine();
                        ok = true;
                    } catch (InputMismatchException e2) {
                        System.err.println("\tError: Solo puedes escribir numeros");
                        teclado.nextLine();
                    }
                } while (!ok);
                ok = false;                
                if ( m.getAnioOlimpiada() < 1945 ||  m.getAnioOlimpiada() > 2020) {
                    System.err.println("\tError: Solo puede introducir fechas entre 1945 y 2020");
                    ok = true;
                }
            } while (m.getAnioOlimpiada() < 1945 ||  m.getAnioOlimpiada() > 2020);
            //Introduce tipo de medalla
            ok = false;
            int tipo;
            do {
                try {
                    System.out.println("\t\tIntroduce el tipo de medalla: ");
                    System.out.println("\t\t1. ORO");
                    System.out.println("\t\t2. Plata");
                    System.out.println("\t\t3. BRONCE");
                    System.out.print("\t\tMedalla: ");
                    tipo = teclado.nextInt();
                    switch (tipo) {
                        case 1:
                            m.setTipo(Medallas.TipoMedalla.ORO);
                            arrayMedallas.add(m);
                            teclado.nextLine();
                            ok = true;
                            break;

                        case 2:
                            m.setTipo(Medallas.TipoMedalla.PLATA);
                            arrayMedallas.add(m);
                            teclado.nextLine();
                            ok = true;
                            break;

                        case 3:
                            m.setTipo(Medallas.TipoMedalla.BRONCE);
                            arrayMedallas.add(m);
                            teclado.nextLine();
                            ok = true;
                            break;
                    }
                } catch (InputMismatchException e2) {
                    System.err.println("- Solo puede escribir números -");
                    teclado.nextLine();
                }
                if (numMedallas > 1) {
                    System.out.println("\n\t\t____________________________________________________");
                } else {
                    System.out.println("");
                }
            } while (!ok);
        }
        return arrayMedallas;
    }

    /**
     * inscripcion() - Método que crea un objeto deportista al que se le añade
     * el array de medallas, para que forme parte del mismo.
     *
     * @return i
     */
    public Deportista inscripcion() {
        Deportista i = rellenaDeportista();
        ArrayList<Medallas> arrayMedallas = rellenaMedallas();
        i.setArrayMedallas(arrayMedallas);
        return i;
    }

    /**
     * muestraDeportista(Deportista i) - Método que muestra un deportista
     * inscrito.
     *
     * @param i
     */
    public void muestraDeportista(Deportista i) {
        System.out.println("\n__________________________________________________");
        System.out.println("Datos del deportista:");
        System.out.println("Nombre: " + i.getNombre());
        System.out.println("País: " + i.getPais());
        System.out.println("Número de medallas: " + i.getnMedallas());
        for (int numMedallas = 0; numMedallas < i.getnMedallas(); numMedallas++) {
            System.out.println("\n\t> Medalla: " + (numMedallas + 1));
            System.out.println("\tPais: " + i.getArrayMedallas().get(numMedallas).getPaisOlimpiada());
            System.out.println("\tAño: " + i.getArrayMedallas().get(numMedallas).getAnioOlimpiada());
            System.out.println("\tTipo medalla: " + i.getArrayMedallas().get(numMedallas).getTipo());
            if (numMedallas > 1) {
                System.out.println("\t____________________________________________________");
            } else {
                System.out.println("");
            }
        }
        System.out.println("\n__________________________________________________\n");
    }

    /**
     * consultaDeportista() - Método que pide al usuario el nombre de un
     * deportista inscrito anteriormente; Si existe lo muestra, si no, muestra
     * un mensaje del problema.
     */
    public void consultaDeportista() {
        /*Creo un objeto de deportista del que puedo leer datos*/
        Deportista consulta = new Deportista();
        FileInputStream fis = null;
        /*Pido datos de consulta*/
        System.out.print("\nIntroduzca nombre del deportista: ");
        consulta.setNombre(teclado.nextLine().toUpperCase());
        /*Leo nombre de fichero creado anteriormente*/
        String fichero = consulta.getNombre() + ".olimpiadas";
        try {
            fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);
            consulta = (Deportista) ois.readObject();
            System.out.println("- Datos de consulta -");
            System.out.println("Nombre: " + consulta.getNombre());
            System.out.println("País: " + consulta.getPais());
            System.out.println("Número de medallas: " + consulta.getnMedallas());
            for (int numMedallas = 0; numMedallas < consulta.getnMedallas(); numMedallas++) {
                System.out.println("\n\t> Medalla: " + (numMedallas + 1));
                System.out.println("\tPais: " + consulta.getArrayMedallas().get(numMedallas).getPaisOlimpiada());
                System.out.println("\tAño: " + consulta.getArrayMedallas().get(numMedallas).getAnioOlimpiada());
                System.out.println("\tTipo medalla: " + consulta.getArrayMedallas().get(numMedallas).getTipo());
            }
        } catch (EOFException ex) {
            System.err.println("Error: El fichero está vacío");
        } catch (FileNotFoundException ex) {
            System.err.println("Error: Archivo no encontrado(puede que tenga distinto"
                    + " nombre) -");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * despedida() - Método que muestra un mensaje de despedida.
     */
    public void despedida(){
        System.out.println("\n\t. . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t. . @ @ . . . @ . . . . @ . . . . @ . .");
        System.out.println("\t. @ . . . . . @ . . . @ . @ . . @ . @ .");
        System.out.println("\t. @ . . . . . @ . . . @ @ @ . . @ . @ .");
        System.out.println("\t. @ . . . . . @ . . . @ . @ . . @ . @ .");
        System.out.println("\t. @ @ @ . . . @ . . . @ . @ . . . @ . .");
        System.out.println("\t. . . . . . . . . . . . . . . . . . . .");
        System.out.println("\t. . . . . . . . * . * . * . . . . . . .");
        System.out.println("\t. . . . . . . . * . * . * . . . . . . .");
        System.out.println("\t. . . . . . . * . * . * . . . . . . . .");
        System.out.println("\t. . . . . @ @ @ @ @ @ @ @ @ . . . . . .");
        System.out.println("\t. . . . . @ @ @ @ @ @ @ @ . @ . . . . .");
        System.out.println("\t. . . . . @ @ @ @ @ @ @ @ @ . . . . . .");
        System.out.println("\t. . . . . . @ @ @ @ @ @ . . . . . . . .");
        System.out.println("\t. . . . . . . @ @ @ @ . . . . . . . . .");
        System.out.println("\t. . . . . @ @ @ @ @ @ @ @ . . . . . . .");
        System.out.println("\t. . . . . . . . . . . . . . . . . . . .");
    }
}
