qpackage modelo;

public class Profesor extends Persona implements MetodosInterface{
	private String titulacion;
	private String eLaboral;
	private int corregir;
	
	public Profesor() {
		super();
		titulacion = "";
		eLaboral = "";
		corregir = 0;
	}
	
	public Profesor(String titulacion, String eLaboral, int corregir) {
		super();
		this.titulacion = titulacion;
		this.eLaboral = eLaboral;
		this.corregir = corregir;
	}
	
	
	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public String geteLaboral() {
		return eLaboral;
	}

	public void seteLaboral(String eLaboral) {
		this.eLaboral = eLaboral;
	}

	public int getCorregir() {
		return corregir;
	}

	public void setCorregir(int corregir) {
		this.corregir = corregir;
	}

	@Override
	public void corregirExamenes(int corregir) {
		int tiempo = corregir * 15;
		int horas = (tiempo)/60;
		int minutos = tiempo%60;
		System.out.println("En corregir: " + corregir +" exámenes tarda: " + horas + "h " + minutos + "min");
		
	}
	@Override
	public void beca(int pagos) {
		// TODO Auto-generated method stub
		
	}
}
