package modelo;

public class Alumno extends Persona implements MetodosInterface{
	private String procedencia;
	private int pagos;
	
	public Alumno() {
		super();
		procedencia = "";
		pagos = 0;
	}
	public Alumno(String procedencia, int pagos) {
		super();
		this.procedencia = procedencia;
		this.pagos = pagos;
	}

	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public int getPagos() {
		return pagos;
	}
	public void setPagos(int pagos) {
		this.pagos = pagos;
	}
	

	@Override
	public void beca(int pagos) {
		boolean ok = false;
		if (pagos == 0) {
			System.out.println("Tiene: " +  pagos  + " pagos pendientes.Recibe beca");
			ok = true;
		}else {
			System.out.println("Tiene: " +  pagos  + " pagos pendientes.No recibe beca");
		}
		
	}
	@Override
	public void corregirExamenes(int corregir) {
		// TODO Auto-generated method stub
		
	}
}
