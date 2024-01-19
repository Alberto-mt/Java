package controlador;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Deportista;
import modelo.Medallas;
import vista.Mostrar;

/**
 * GestionaOlimpiadas - Clase que gestiona la parte no visible de la aplicación.
 *
 * @author Alberto
 */
public class GestionaOlimpiadas {

    /*Declaro objeto teclado para poder introducir datos.
        Con "ISO-8859-1", controlo la entrada por teclado de caracteres
        especiales.
     */
    Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    /*Creo objeto 'v' para acceder a los métodos de mostrar*/
    Mostrar v = new Mostrar();
    /*arrayDeportista - Este ArrayList es genérico a toda la aplicacion
      para poder insertar y mostrar objetos de tipo Deportista*/
    ArrayList<Deportista> arrayDeportista = new ArrayList<Deportista>();
    
    /**
     * index() - Método que muestra menú, con sus opciones y te permite elegir
     * entre ellas.
     */
    public void index() {
        boolean ok = false;
        do {
            try {
                int opcion;
                do {
                    v.muestraMenu();
                    opcion = teclado.nextInt();
                    switch (opcion) {
                        case 1:
                            cls();
                            insertaDeportista();
                            break;
                        case 2:
                            cls();
                            v.consultaDeportista();
                            break;
                        case 3:
                            cls();
                            ficheroHTML();
                            break;
                        case 4:
                            cls();
                            v.despedida();
                            break;
                        default:
                            cls();
                            System.err.println("Error: Opción incorrecta");
                            System.out.print("Elija una opcion: ");
                    }
                } while ((opcion >= 1 || opcion <= 3) && opcion != 4);
                teclado.nextLine();
                ok = true;
            } catch (InputMismatchException e) {
                System.err.println("\tError: Solo puedes escribir números");
                teclado.nextLine();
            }
        } while (!ok);
    }

    /**
     * insertaDeportista() - Método que añade un objeto al arrayList de
     * Deportista, una vez rellenado en la clase Mostrar.
     */
    public void insertaDeportista() {
        boolean ok = false;
        do {
            try {
                int inscritos = 0;
                do {
                    /* Guardo Deportista d dentro del método*/
                    Deportista i = v.inscripcion();
                    ficheroDeportista(i);
                    ficheroSQL(i);
                    v.muestraDeportista(i);
                    /*Añado un deportista 'd' a arrayDeportista*/
                    arrayDeportista.add(i);
                    inscritos++;
                    System.out.println("- Deportista inscrito correctamente -\n\n\n");
                } while (inscritos > 100);
                teclado.nextLine();
                ok = true;
            } catch (Exception e) {
                System.err.println("\tError: Deportista no inscrito");
                teclado.nextLine();
            }
        } while (!ok);

    }

    /**
     * ficheroDeportista(Deportista i) - Método que crea un fichero de un
     * deportista i(insertado), con extensión '.olimpiadas'.
     */
    public void ficheroDeportista(Deportista i) {
        FileOutputStream fichero = null;
        ObjectOutputStream tuberia = null;
        try {
            String data = "Agregando datos a fichero deportista...";
            fichero = new FileOutputStream(i.getNombre() + ".olimpiadas");
            tuberia = new ObjectOutputStream(fichero);

            tuberia.writeObject(i);
            //Información agregada al fichero
            System.out.println("- Ficha de inscripción creada -");
        } catch (IOException e) {
            System.err.println("Error: al crear fichero, fichero no creado");
        } finally {
            try {
                /*Cierra instancias de FileWriter y BufferedWriter*/
                if (fichero != null) {
                    fichero.close();
                }
                if (tuberia != null) {
                    tuberia.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: al cerrar el fichero");
            }
        }
    }

    /**
     * ficheroSQL(Deportista i) - Método que crea un fichero SQL de un
     * deportista i(insertado), con extensión '.sql'.
     */
    public void ficheroSQL(Deportista i) {

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String data = "Agregando datos a fichero SQL...";
            String fichero = "olimpiadas.sql";
            File file = new File(fichero);
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            /*Flag true, indica adjuntar información al fichero*/
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            if (arrayDeportista.isEmpty()) {
                bw.write("\nCREATE TABLE DEPORTISTAS(");
                bw.newLine();
                bw.write("\n\tDEPORTISTA_COD INT IDENTITY(1,1) NOT NULL,");
                bw.newLine();
                bw.write("\n\tNOMBRE VARCHAR(30) NOT NULL,");
                bw.newLine();
                bw.write("\n\tPAÍS VARCHAR(30) NOT NULL,");
                bw.newLine();
                bw.write("\n\tNÚMERO_MEDALLAS TINYINT NOT NULL,");
                bw.newLine();
                bw.write("\n\tCONSTRAINT PK_DEPORTISTAS_NOMBRE PRIMARY KEY (NOMBRE)");
                bw.newLine();
                bw.write("\n);");
                bw.newLine();
                bw.write("\nCREATE TABLE MEDALLAS(");
                bw.newLine();
                bw.write("\n\tMEDALLA_COD INT IDENTITY(1,1) NOT NULL,");
                bw.newLine();
                bw.write("\n\tNOMBRE VARCHAR(30) NOT NULL,");
                bw.newLine();
                bw.write("\n\tPAÍS_OLIMPIADA VARCHAR(30) NOT NULL,");
                bw.newLine();
                bw.write("\n\tAÑO_OLIMPIADA DATE NOT NULL,");
                bw.newLine();
                bw.write("\n\tTIPO_MEDALLA VARCHAR(10) NOT NULL,");
                bw.newLine();
                bw.write("\n\tCONSTRAINT PK_MEDALLAS_MEDALLA_COD PRIMARY KEY "
                        + "(MEDALLA_COD,NOMBRE),");
                bw.newLine();
                bw.write("\n\tCONSTRAINT FK_MEDALLAS_NOMBRE FOREIGN KEY (NOMBRE) "
                        + "REFERENCES DEPORTISTAS(NOMBRE)");
                bw.newLine();
                bw.write("\n);");
                bw.newLine();
                bw.write("\n\nINSERT INTO DEPORTISTAS VALUES ('" + i.getNombre()
                        + "','" + i.getPais() + "'," + i.getnMedallas() + ");");
                bw.newLine();
                for (int numMedallas = 0; numMedallas < i.getnMedallas(); numMedallas++) {
                    bw.write("\nINSERT INTO MEDALLAS VALUES (" + (numMedallas + 1) + ",'"
                            + i.getNombre() + "','" + i.getArrayMedallas().get(numMedallas).getPaisOlimpiada()
                            + "'," + i.getArrayMedallas().get(numMedallas).getAnioOlimpiada()
                            + ",'" + i.getArrayMedallas().get(numMedallas).getTipo() + "');");
                    bw.newLine();
                }
            } else {
                bw.write("\n\nINSERT INTO DEPORTISTAS VALUES ('" + i.getNombre()
                        + "','" + i.getPais() + "'," + i.getnMedallas() + ");");
                bw.newLine();
                for (int numMedallas = 0; numMedallas < i.getnMedallas(); numMedallas++) {
                    bw.write("\nINSERT INTO MEDALLAS VALUES (" + (numMedallas + 1) + ",'"
                            + i.getNombre() + "','" + i.getArrayMedallas().get(numMedallas).getPaisOlimpiada()
                            + "'," + i.getArrayMedallas().get(numMedallas).getAnioOlimpiada()
                            + ",'" + i.getArrayMedallas().get(numMedallas).getTipo() + "');");
                    bw.newLine();
                }
            }
            //Información agregada al fichero
            System.out.println("\n- Deportista insertado en fichero SQL -\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("- Error: al crear fichero, fichero no creado");
        } finally {
            try {
                /*Cierra instancias de FileWriter y BufferedWriter*/
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: al cerrar el fichero");
            }
        }
    }

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
            String line1 = "<!DOCTYPE html>";
            String line2 = "<html lang='es'>";
            String line3 = "  <meta charset='UTF-8'>";
            String line4 = "  <title>" + i.getNombre() + "</title>";
            String line5 = "  <link rel='stylesheet' type='text/css' href='estilos.css'>";
            String line6 = "</head>";
            String line7 = "<body>";
            String line8 = "  <div id='contenedor'>";
            String line9 = "        <header>";
            String line10 = "          <div id='menu'>";
            String line11 = "               <ul>";
            String line12 = "                   <li><a href=''>HOME</a></li>";
            String line13 = "                   <li><a href=\"\">NEWS</a></li>";
            String line14 = "                   <li><a href=''>THE GAMES</a></li>";
            String line15 = "                   <li><a href=''>GET INVOLVED</a></li>";
            String line16 = "                   <li><a href=\"\">ABOUT US</a></li>";
            String line17 = "               </ul>";
            String line18 = "          </div>";
            String line19 = "          <div id='logo'>";
            String line20 = "               <img src='imagenes/tokyo2.jpg' alt='tokyo2'>";
            String line21 = "          </div>";
            String line22 = "       </header>";
            String line23 = "       <section>";
            String line24 = "          <div id='deportista'>";
            String line25 = "              <img src='imagenes/perfil.png' alt='perfil'>";
            String line26 = "              <div id='deport'>";
            String line27 = "                   <p id='nombre'>NOMBRE: " + i.getNombre() + "</p>";
            String line28 = "                   <p id='pais'>PAÍS: " + i.getPais() + "</p>";
            String line29 = "                   <p id='numero'>NÚMERO DE MEDALLAS: " + i.getnMedallas() + "</p>";
            String line30 = "              </div>";
            String line31 = "          </div>";
            String line32 = "";
            String line33 = "";
            String line34 = "           <div id='meda'>";
            String line35 = "";
            String line36 = "";
            String line37 = "";
            String line38 = "           </div>";
            String line39 = "      </div>";
            String line40 = "       </section>";
            String line41 = "   <footer>";
            String line42 = "       <div class='enlaces'>";
            String line43 = "           <p class='titulo'>THE GAMES</p>";
            String line44 = "           <p class='enlace'><a href=''>Games Vision</a></p>";
            String line45 = "           <p class='enlace'><a href=''>OCOG and Other Entities Budget</a></p>";
            String line46 = "           <p class='enlace'><a href=''>Olympic and Paralympic Games</a></p>";
            String line47 = "           <p class='enlace'><a href=''>Tokyo 2020 Schedule</a></p>";
            String line48 = "           <p class='enlace'><a href=''>The Caring Games</a></p>";
            String line49 = "       </div>";
            String line50 = "       <div class='enlaces'>";
            String line51 = "           <p class='titulo'>GET INVOLVED</p>";
            String line52 = "           <p class='enlace'><a href=''>Overseas Spectators</a></p>";
            String line53 = "           <p class='enlace'><a href=''>Countdown Events</a></p>";
            String line54 = "           <p class='enlace'><a href=''>Tokyo 2020 NIPPON FESTIVAL</a></p>";
            String line55 = "       </div>";
            String line56 = "       <div class='enlaces'>";
            String line57 = "           <p class='titulo'>ABOUT US</p>";
            String line58 = "           <p class='enlace'><a href=''>Organisational Structure and List of Members</a></p>";
            String line59 = "           <p class='enlace'><a href=''>Accessibility</a></p>";
            String line60 = "           <p class='enlace'><a href=''>Tokyo 2020 Official Contributors</a></p>";
            String line61 = "           <p class='enlace'><a href=''>Staff recruitment</a></p>";
            String line62 = "       </div>";
            String line63 = "       <img src='imagenes/social.png' alt='social'>";
            String line64 = "       <div id='autor'>WEB DISEGN: ALBERTO MONTEALEGRE</div>";
            String line65 = "      </footer>";
            String line66 = "     </div>";
            String line67 = "   </body>";
            String line68 = "</html>";

            BufferedWriter bw = new BufferedWriter(new FileWriter(i.getNombre() + ".html"));
            bw.write(line1);
            bw.newLine();
            bw.write(line2);
            bw.newLine();
            bw.write(line3);
            bw.newLine();
            bw.write(line4);
            bw.newLine();
            bw.write(line5);
            bw.newLine();
            bw.write(line6);
            bw.newLine();
            bw.write(line7);
            bw.newLine();
            bw.write(line8);
            bw.newLine();
            bw.write(line9);
            bw.newLine();
            bw.write(line10);
            bw.newLine();
            bw.write(line11);
            bw.newLine();
            bw.write(line12);
            bw.newLine();
            bw.write(line13);
            bw.newLine();
            bw.write(line14);
            bw.newLine();
            bw.write(line15);
            bw.newLine();
            bw.write(line16);
            bw.newLine();
            bw.write(line17);
            bw.newLine();
            bw.write(line18);
            bw.newLine();
            bw.write(line19);
            bw.newLine();
            bw.write(line20);
            bw.newLine();
            bw.write(line21);
            bw.newLine();
            bw.write(line22);
            bw.newLine();
            bw.write(line23);
            bw.newLine();
            bw.write(line24);
            bw.newLine();
            bw.write(line25);
            bw.newLine();
            bw.write(line26);
            bw.newLine();
            bw.write(line27);
            bw.newLine();
            bw.write(line28);
            bw.newLine();
            bw.write(line29);
            bw.newLine();
            bw.write(line30);
            bw.newLine();
            bw.write(line31);
            bw.newLine();
            for (int numMedallas = 0; numMedallas < i.getnMedallas(); numMedallas++) {
                bw.write(line32 = "      <div id='medallas'");
                bw.newLine();
                bw.write(line33 = "           <img src='imagenes/oro.jpg' alt='oro'>");
                bw.newLine();
                bw.write(line34);
                bw.newLine();
                bw.write(line35 = "               <p id='mPais'>PAÍS: " + i.getArrayMedallas().get(numMedallas).getPaisOlimpiada() + "</p>");
                bw.newLine();
                bw.write(line36 = "               <p id='mAño'>AÑO: " + i.getArrayMedallas().get(numMedallas).getAnioOlimpiada() + "</p>");
                bw.newLine();
                bw.write(line37 = "               <p id='mTipo'>MEDALLA DE:" + i.getArrayMedallas().get(numMedallas).getTipo() + "</p>");
                bw.newLine();
                bw.write(line38);
                bw.newLine();
                bw.write(line39);
                bw.newLine();
            }
            bw.write(line40);
            bw.newLine();
            bw.write(line41);
            bw.newLine();
            bw.write(line42);
            bw.newLine();
            bw.write(line43);
            bw.newLine();
            bw.write(line44);
            bw.newLine();
            bw.write(line45);
            bw.newLine();
            bw.write(line46);
            bw.newLine();
            bw.write(line47);
            bw.newLine();
            bw.write(line48);
            bw.newLine();
            bw.write(line49);
            bw.newLine();
            bw.write(line50);
            bw.newLine();
            bw.write(line51);
            bw.newLine();
            bw.write(line52);
            bw.newLine();
            bw.write(line53);
            bw.newLine();
            bw.write(line54);
            bw.newLine();
            bw.write(line55);
            bw.newLine();
            bw.write(line56);
            bw.newLine();
            bw.write(line57);
            bw.newLine();
            bw.write(line58);
            bw.newLine();
            bw.write(line59);
            bw.newLine();
            bw.write(line60);
            bw.newLine();
            bw.write(line61);
            bw.newLine();
            bw.write(line62);
            bw.newLine();
            bw.write(line63);
            bw.newLine();
            bw.write(line64);
            bw.newLine();
            bw.write(line65);
            bw.newLine();
            bw.write(line66);
            bw.newLine();
            bw.write(line67);
            bw.newLine();
            bw.write(line68);
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

    /**
     * cls()- Método que limpia consola.
     */
    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
    }
}
