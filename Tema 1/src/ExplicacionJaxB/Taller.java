package ExplicacionJaxB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*Anotacion para indicar que este es el elemento raiz.*/
@XmlRootElement()
@XmlType(propOrder = {"nombre","piezas"})
public class Taller {

	private String nombre;
	private ArrayList<Pieza>piezas=new ArrayList<>();
	

	public Taller() {
		
	}
	
	public Taller(String nombre, ArrayList<Pieza> piezas) {
		super();
		this.nombre = nombre;
		this.piezas = piezas;
	}
	/*Importamos de XML .bind*/
	/*Anotacionpara indicar un elemento simple.
	 * El atributo name permite cambiar el nombre en el XML. Es opcional si coincide con el nombre del atributo
	 * de la calse, sino no es necesario ponerlo.*/
	@XmlElement(name="nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*Anotaciones para hacer un elemento que contiene una lista
	 * de otro elemento.*/
	@XmlElementWrapper 
	/*Elemento contenido en la lista. Es necesario poner el name ya uqe si no, pone el nombre del elemento lista
	 * */
	@XmlElement(name="pieza")
	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}
	public void setPiezas(ArrayList<Pieza> piezas) {
		this.piezas = piezas;
	}
	
	protected void mostrar() {
		System.out.println("Nombre: "+nombre);
		System.out.println("Piezas: ");
		for(Pieza p:piezas) {
			p.mostrar();
		}
	}
}
