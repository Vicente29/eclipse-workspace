package Examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"nombre","producto"})
public class Tienda {

	private  String nombre;
	private ArrayList<Productos> producto=new ArrayList<>();
	
	@XmlElement(name="nombre")
	protected String getNombre() {
		return nombre;
	}
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@XmlElementWrapper 
	@XmlElement(name="productos")
	protected ArrayList<Productos> getProducto() {
		return producto;
	}
	protected void setProducto(ArrayList<Productos> producto) {
		this.producto = producto;
	}
	public Tienda() {
		
	}
	
	protected void mostrar() {
		System.out.println("Nombre: "+nombre);
		System.out.println("Prodctos: ");
		for(Productos p:producto) {
			p.MostrarObjeto();
		}
	}
	
	
	
}
