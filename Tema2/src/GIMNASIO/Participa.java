package GIMNASIO;

public class Participa {

	private Actividad actividad_id;
	private Cliente cliente_id;
	
	public Participa() {
		
	}

	public Participa(Actividad actividad_id, Cliente cliente_id) {
		
		this.actividad_id = actividad_id;
		this.cliente_id = cliente_id;
	}

	protected Actividad getActividad_id() {
		return actividad_id;
	}

	protected void setActividad_id(Actividad actividad_id) {
		this.actividad_id = actividad_id;
	}

	protected Cliente getCliente_id() {
		return cliente_id;
	}

	protected void setCliente_id(Cliente cliente_id) {
		this.cliente_id = cliente_id;
	}
	
	
	
	
	
}
