package controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import modelo.Madera;
import vista.Mostrar;

public class FicheroNOLegible_Leer {

	/*
	 * ***   public class Maderas implements Serializable{}
	 */
	public ArrayList<Madera> leeFichNoLegMadera() {
		FileInputStream fis = null;
		ArrayList<Madera> maderaFromArchive = new ArrayList();
		try {
			fis = new FileInputStream("maderas.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Madera mad = null;
			while (true) {
				try {
					mad = (Madera) ois.readObject();
					maderaFromArchive.add((Madera) mad);
				} catch (EOFException ex) {
					break;
				}
			}
			ois.close();
			return maderaFromArchive;

		} catch (EOFException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("No se encuentra el fichero");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void mostrarArchivoMadera() {
		ArrayList<Madera> maderaFromArchive = leeFichNoLegMadera();
		mostrar = new Mostrar();
		for (Madera mad : maderaFromArchive) {
			mostrar.mostrarMadera(mad);
		}
	}

	/*********************************************************************************************************/
	
	/**
     * ficheroHTML()- Método que crea un fichero html de un deportista
     * i(insertado), con extensión '.html'.
     */
    public void ficheroHTML() {
        /*Guardo en el objeto 'Deportista' el contenido del ArrayList pilotos
        en cada posición.*/
        Deportista i = new Deportista();
        FileInputStream fis = null;
        Scanner consultaDeportista = new Scanner(System.in);
        System.out.print("Introduzca nombre del deportista: ");
        i.setNombre(consultaDeportista.nextLine());
        String fichero = i.getNombre() + ".olimpiadas";
        boolean ok = false;
        int x = 0;
        try {
            fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);
            i = (Deportista) ois.readObject();
            /*Strings con los que voy a escribir el HTML*/
            
            String line1 = "                   <p id='nombre'>NOMBRE: " + i.getNombre() + "</p>";
            String line2 = "                   <p id='pais'>PAÍS: " + i.getPais() + "</p>";
            String line3 = "                   <p id='numero'>NÚMERO DE MEDALLAS: " + i.getnMedallas() + "</p>";
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(i.getNombre() + ".html"));
            bw.write(line1);
            bw.newLine();
            bw.write(line2);
            bw.newLine();
            bw.write(line3);
            bw.newLine();
            bw.close();

            System.out.println("- Fichero HTML creado correctamente -");
            ok = true;
        } catch (EOFException ex) {
            System.err.println("El fichero esta vacio");
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado(puede que tenga distinto nombre)");
        } catch (NullPointerException ex) {
            System.err.println("El deportista no existe(consulte el nombre)");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                System.err.println("El deportista no existe(consulte el nombre)");
            }
        }
        String fichero2 = i.getNombre() + ".html";
        String nombreSO = System.getProperty("os.name");
        try {
            if (nombreSO.startsWith("Mac OS")) {
                Class manager = Class.forName("com.apple.eio.FileManager");
                Method openURL = manager.getDeclaredMethod("openURL", new Class[]{String.class});
                openURL.invoke(null, new Object[]{fichero2});
            }
            if (nombreSO.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fichero2);
            } else {
                //se asume  Unix or Linux
                String[] navegadores = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
                String navegador = null;
                for (int contador = 0; contador < navegadores.length && navegador == null; contador++) {
                    if (Runtime.getRuntime().exec(new String[]{"which", navegadores[contador]}).waitFor() == 0) {
                        navegador = navegadores[contador];
                    }
                }

                if (navegador == null) {
                    throw new Exception("No se encuentra navegador web");
                } else {
                    Runtime.getRuntime().exec(new String[]{navegador, fichero2});
                }
            }
        } catch (Exception e) {
            System.err.println("Error: al intentar lanzar el navegador web");
        }
    }
}