package Explicacion_MySql;

public class Pieza {

	private int codigo;
	private String nombre;
	private float precio;
	private int stock;
	private boolean alta;
	
	protected Pieza() {
		
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
	protected float getPrecio() {
		return precio;
	}
	protected void setPrecio(float precio) {
		this.precio = precio;
	}
	protected int getStock() {
		return stock;
	}
	protected void setStock(int stock) {
		this.stock = stock;
	}
	protected boolean isAlta() {
		return alta;
	}
	protected void setAlta(boolean alta) {
		this.alta = alta;
	}


	protected void mostrar() {
		// TODO Auto-generated method stub
		System.out.println("COdigo: "+codigo+
				"\tNombre: "+nombre+
				"\tPrecio: "+precio+
				"\tStock: "+stock+
				"\tAlta: "+alta);
	}
	
	
	
	
}
