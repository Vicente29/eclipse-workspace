package Explicacion_MySql;

public class tipoRep {

	private int codigo;
	private String nombre;
	
	protected tipoRep() {
		
	}

	protected int getCodigo() {
		return codigo;
	}

	protected void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	protected void mostrar() {
		System.out.println("Codigo: "+codigo+
				"\tNombre: "+nombre);
	}
	
}
