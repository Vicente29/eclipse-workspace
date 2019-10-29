package GIMNASIO;

public class Actividad {

	private String id;
	private String nombre;
	private float coste_mensual;
	private String activa;
	
	public Actividad() {
		
	}

	public Actividad(String id, String nombre, float coste_mensual, String activa) {
		this.id = id;
		this.nombre = nombre;
		this.coste_mensual = coste_mensual;
		this.activa = activa;
	}

	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected float getCoste_mensual() {
		return coste_mensual;
	}

	protected void setCoste_mensual(float coste_mensual) {
		this.coste_mensual = coste_mensual;
	}

	protected String getActiva() {
		return activa;
	}

	protected void setActiva(String activa) {
		this.activa = activa;
	}
	
	protected void mostar_Actividad() {
		
		System.out.println("Id: "+id+
				"\tNombre: "+nombre+
				"\tActividad: "+activa+
				"\tCoste mensual: "+coste_mensual);
	}
	
	
}
