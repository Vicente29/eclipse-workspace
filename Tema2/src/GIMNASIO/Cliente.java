package GIMNASIO;

public class Cliente {

	private int id;
	private Usuario usuario;
	private String dni;
	private String apellido;
	private String nombre;
	private String telf_contacto;
	private boolean baja;
	
	public Cliente() {
		
	}

	public Cliente(int id, Usuario usuario, String dni, String apellido, String nombre, String telf_contacto,boolean baja) {

		this.id = id;
		this.usuario = usuario;
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.telf_contacto = telf_contacto;
		this.baja = baja;
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected Usuario getUsuario() {
		return usuario;
	}

	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	protected String getDni() {
		return dni;
	}

	protected void setDni(String dni) {
		this.dni = dni;
	}

	protected String getApellido() {
		return apellido;
	}

	protected void setApellido(String apellido) {
		this.apellido = apellido;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getTelf_contacto() {
		return telf_contacto;
	}

	protected void setTelf_contacto(String telf_contacto) {
		this.telf_contacto = telf_contacto;
	}

	protected boolean isBaja() {
		return baja;
	}

	protected void setBaja(boolean baja) {
		this.baja = baja;
	}

	
	protected void mostrar_Cliente() {
		
		System.out.println("Cliente id: "+id+
				"\tUsuario: "+usuario.getUsuario()+
				"\tNombre: "+nombre+" "+apellido+
				"\tTelefono contacto: "+telf_contacto+
				"\tBaja: "+baja);
	}
	
	
	
	
}
