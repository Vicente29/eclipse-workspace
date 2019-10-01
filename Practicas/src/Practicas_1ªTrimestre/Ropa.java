package Practicas_1ªTrimestre;

import java.io.Serializable;

public class Ropa implements Serializable{
	
	private String codigo,nombre, talla,genero;
	private Float precio;
	private int stock;
	private boolean alta;
	
	public Ropa() {
		
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



	public String getTalla() {
		return talla;
	}



	public void setTalla(String talla) {
		this.talla = talla;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
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
	public void mostraRopa() {
		System.out.println("Codigo: "+codigo+
				"\tNombre: "+nombre+
				"\tTalla: "+talla+
				"\tGenero: "+genero+
				"\tPrecio: "+precio+
				"\tStock: "+stock+
				"\tAlta: "+alta);
	}
	

}
