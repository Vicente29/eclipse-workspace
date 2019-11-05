package GIMNASIO;

public class Usuario {

	private String usuario;
	private String tipo;
	
	public Usuario(String ususario, String tip) {
		this.usuario=ususario;
		this.tipo=tipo;
	}
	public Usuario() {
		
	}

	protected String getUsuario() {
		return usuario;
	}

	protected void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	protected String getTipo() {
		return tipo;
	}

	protected void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

	
	
	
	
	
	
	
}
