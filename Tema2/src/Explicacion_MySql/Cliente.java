package Explicacion_MySql;

public class Cliente {

	private String dni;
	private String nombre;
	private String telefono;
	
	protected Cliente() {
		
	}
	

	public Cliente(String dni, String nombre, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
	}


	protected String getDni() {
		return dni;
	}

	protected void setDni(String dni) {
		this.dni = dni;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getTelefono() {
		return telefono;
	}

	protected void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
}
