package ExplicacionJaxB;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Principal {

	public static Scanner leer=new Scanner(System.in);
	public static String nombreFO="Almacen.obj";
	public static String nombreFXML="Almacen2.xml";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero binario a partir del fichero de texto.");
			System.out.println("2.-Leer/Mostrar.");
			System.out.println("3.-Insertar material.");
			System.out.println("4.-Modificar material");
			System.out.println("5.-Borrar el fichero.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			
			case 1:
					crearFicheroXML();
				break;
			case 2:
					mostrar();
				break;
			case 3:
					insertar();
				break;
			case 4:
					modificar();
				break;
			case 5:
					borrar();
				break;
			
			}
			
			
		}while(opcion!=0);
		
		
	}
	
	
	
	private static void borrar() {
		// TODO Auto-generated method stub
		Taller t=unmarshal();
		
		//Borramos.
		System.out.println("Introduce el codigo de la pieza a borrar: ");
		String codigo=leer.nextLine();
		
		boolean encontrado=false;
		int contador=0;
		while(contador<t.getPiezas().size() && !encontrado ) {
			
			if(t.getPiezas().get(contador).getCodigo().equalsIgnoreCase(codigo)) {
				t.getPiezas().remove(contador);
				encontrado=true;
			}
			contador++;
		}
		marshal(t);
	}



	private static void modificar() {
		// TODO Auto-generated method stub
		
		Taller t=unmarshal();
		
		System.out.println("Introduce el codigo de la pieza a modificar: ");
		String codigo=leer.nextLine();
		
		//Buscamos la pieza mediante un foreah.
		for(Pieza p:t.getPiezas()) {
			if(p.getCodigo().equalsIgnoreCase(codigo)) {
				System.out.println("Nuevo precio: ");
				p.setPrecio(leer.nextFloat());leer.nextLine();
				break;
			}
		}
		marshal(t);	
	}



	private static void insertar() {
		// TODO Auto-generated method stub
		Taller t=unmarshal();
		
		//Añadimos una pieza.
		Pieza p=new Pieza();
		System.out.println("Introduce el Codigo: ");
		p.setCodigo(leer.nextLine());
		boolean encontrado=false;
		for(Pieza p2:t.getPiezas()) {
			if(p2.getCodigo().equalsIgnoreCase(p.getCodigo())) {
				encontrado=true;
			}
		}
		if(!encontrado) {
			System.out.println("Introduce el nombre: ");
			p.setNombre(leer.nextLine());
			System.out.println("Introduce el precio: ");
			p.setPrecio(leer.nextFloat());leer.nextLine();
			System.out.println("Introduce el stock: ");
			p.setStock(leer.nextInt());leer.nextLine();
			p.setAlta(true);
			//Añadimos la pieza al ArrayList
			t.getPiezas().add(p);
		}else {
			System.err.println("ERROR: Ya existe este codigo.");
		}
		
		marshal(t);
	}



	private static void mostrar() {
		// TODO Auto-generated method stub
		
		Taller t=unmarshal();
		
		t.mostrar();
		
		
	}



	private static Taller unmarshal() {

		Taller resultado=null;
		//Especiificamos la clase raiz.
		try {
			JAXBContext contexto=JAXBContext.newInstance(Taller.class);
			Unmarshaller u=contexto.createUnmarshaller();
			//Trnasformamos.
			resultado=(Taller) u.unmarshal(new File(nombreFXML));
					
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}



	private static void crearFicheroXML() {
		// TODO Auto-generated method stub
		Taller t=new Taller();
		
		System.out.println("Introduce el nombre del taller");
		t.setNombre(leer.nextLine());
		
		//Obtenemos las piezas del fichero de objetos.
		t.setPiezas(obtenerPiezas());
		
		//Generar el fichero XML que es lo mismo que hacer un marshall.
		
		marshal(t);
	}



	private static void marshal(Taller t) {
		// Esprecificar sobre que clase va a hacer el marshal
		try {
			JAXBContext contexto= JAXBContext.newInstance(Taller.class);
			//Creamos el transformador de objeto a xml.
			Marshaller m=contexto.createMarshaller();
			//Hacemos la transformacion.
			m.marshal(t, new File(nombreFXML));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}



	private static ArrayList<Pieza> obtenerPiezas() {
		// TODO Auto-generated method stub
		ArrayList<Pieza>resultado=new ArrayList<Pieza>();
		
		ObjectInputStream fichero=null;
		
		try {
			fichero=new ObjectInputStream(new FileInputStream(nombreFO));
			while(true) {
			Ficheros_de_Objetos.Pieza p=(Ficheros_de_Objetos.Pieza)	fichero.readObject();
			Pieza p2=new Pieza();
			p2.setCodigo(p.getCodigo());
			p2.setNombre(p.getNombre());
			p2.setPrecio(p.getPrecio());
			p2.setStock(p.getStock());
			p2.setAlta(p.isAlta());
			resultado.add(p2);
			}
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return resultado;
	}

}
