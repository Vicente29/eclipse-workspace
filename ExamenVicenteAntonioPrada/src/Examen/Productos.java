package Examen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {""})
public class Productos implements Serializable{

	private int codigo;
	private float precio;
	private int stock;
	private boolean alta;
	private String nombre;
	private float totalVenta;
	private int numVentas; 
	
	public Productos() {
		
	}
	
	
	@XmlElement
	protected int getNumVentas() {
		return numVentas;
	}



	protected void setNumVentas(int numVentas) {
		this.numVentas = numVentas;
	}



	protected float getTotalVenta() {
		return totalVenta;
	}


	protected void setTotalVenta(float totalVenta) {
		this.totalVenta = totalVenta;
	}

	@XmlAnyAttribute
	protected int getCodigo() {
		return codigo;
	}
	protected void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@XmlElement
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
	protected String getNombre() {
		return nombre;
	}
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	protected void MostrarProductos() {
		System.out.println("Codigo:"+codigo+
				"\tPrecio: "+precio+
				"\tStock: "+stock+
				"\tNombre: "+nombre);
	}
	
	protected void MostrarObjeto() {
		
		System.out.println("Codigo: "+codigo+
				"\tNumero de ventas: "+numVentas+
				"\tTotal de la ventas: "+totalVenta);
		
	}
	
	
}
