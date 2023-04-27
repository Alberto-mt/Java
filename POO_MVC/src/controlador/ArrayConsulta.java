package controlador;

public class ArrayConsulta {

	public void consultaArray(ArrayList<Coche> arrayCoche) {
        mostrar = new Mostrar();
        String consulta;
        teclado = new Scanner(System.in);
        System.out.print("Introduce la marca del coche: ");
        consulta = teclado.nextLine().toUpperCase();
        for (Coche i : arrayCoche) {

            if (i.getMarca().equalsIgnoreCase(consulta)) {
                mostrar.muestraCoche(i);
            }
        }
    }
}
