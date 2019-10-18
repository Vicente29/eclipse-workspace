package Ficheros_de_Objetos;

import java.io.Serializable;

public class Pieza implements Serializable{
	
	private String codigo,nombre;
	private Float precio;
	private int stock;
	private boolean alta;
	
	public Pieza() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	//METODOS.
/*------------------------------------------------------------------------------*/	
	public void mostra() {
		System.out.println("Codigo: "+codigo+
				"\tNombre: "+nombre+
				"\tPrecio: "+precio+
				"\tStock: "+stock+
				"\tAlta: "+alta);
	}
	

}
