package ExplicacionJaxB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlAttribute;

@XmlType(propOrder = {"nombre","precio","stock"})
public class Pieza {

	private String nombre, codigo;
	private float precio;
	private int stock;
	private boolean alta;
	
	
	
	
	public Pieza() {
		
	}
	
	public Pieza(String nombre, String codigo, float precio, int stock, boolean alta) {
		
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.stock = stock;
		this.alta = alta;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlAttribute
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@XmlElement
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@XmlElement
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isAlta() {
		return alta;
	}
	@XmlAttribute
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	
}
