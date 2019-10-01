
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Explicacion_DOM {

	static Scanner leer=new Scanner(System.in);
	static String nombreFT="Almacen.txt";
	 static String nombreXML="Almacen.xml";
	public static void main(String[] args) {
		
		
		int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero xml a partir de fichero texto.");
			System.out.println("2.-Leer/Mostrar.");
			System.out.println("3.-Insertar material sin duplicados.");
			System.out.println("4.-Modificar precio material si no esta dado de baja.");
			System.out.println("5.-Baja material.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			
			case 1:
					crearFicheroXML();
				break;
			case 2:
					
				break;
			case 3:
					
				break;
			case 4:
					
				break;
			case 5:
					
				break;
			
			}
			
			
		}while(opcion!=0);

	}

	private static void crearFicheroXML() {
		
		System.out.println("Introduce el nombre del taller: ");
		String nombreTaller=leer.nextLine();
		
		//Creamos el constructor de documentos.
		//Declaramos y abrimos el fichero de texto para crear los nodos pieza.
		
		BufferedReader fichero=null;
		Document documento=null;
		try {
			DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			//Creamos el documento XML vacio.
			 documento=db.newDocument();
			
			//Establecemos la version del XML.
			documento.setXmlVersion("1.0");
			
			//Vamos a crear el elemento raiz.
			Element raiz=documento.createElement("taller");
			
			//Asignamos la raiz al arbol.
			documento.appendChild(raiz);
			
			//Creamos un elemento nombre.
			Element nombre= documento.createElement("nombre");
			
			//Añadimos el elemento nombre a la raiz.
			raiz.appendChild(nombre);
			
			//Creamos el nodo de texto para el nombre del taller.
			Text tNombre=documento.createTextNode(nombreTaller);
			
			//Añadimos el nodo del texto al elemento nombre.
			nombre.appendChild(tNombre);
			
			//Creamos el elemento piezas.
			Element piezas=documento.createElement("piezas");
			
			//Añadimos el elemento pieza a la raiz.
			raiz.appendChild(piezas);
			
			
			//Abrimnos el fichero.
			fichero=new BufferedReader(new FileReader(nombreFT));
			String linea;
			while((linea=fichero.readLine())!=null) {
				String campos[]=linea.split(";");
				//Creamos el elemento pieza.
				Element pieza=documento.createElement("pieza");
				//Asignamos pieza a piezas.
				piezas.appendChild(pieza);
				//Creamos los atributos de pieza.
				pieza.setAttribute("codigo", campos[0]);
				pieza.setAttribute("alta", campos[4]);
				
				//Creamos el elemento nombre de pieza.
				Element nombrePieza=documento.createElement("nombre");
				
				//Asiganamos nombre pieza a pieza.
				pieza.appendChild(nombrePieza);
				
				//Creamos el nodo texto con el nombre de la pieza.
				nombrePieza.appendChild(documento.createTextNode(campos[1]));
				
				//Hacemos lo mismo con el precio.
				Element precio=documento.createElement("precio");
				pieza.appendChild(precio);
				precio.appendChild(documento.createTextNode(campos[2]));
				//Hacemos lo mismo con el stock.
				Element stock=documento.createElement("stock");
				pieza.appendChild(stock);
				stock.appendChild(documento.createTextNode(campos[3]));
			}
			
			//Se hace fuera del while. Pasamos el arbol DOM a fichero.
			pasarDOMaXML(documento,nombreXML);
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	}

	private static void pasarDOMaXML(Document documento, String nombreXML2) {
		// TODO Auto-generated method stub
		
		//Creamos una fuente con el origen de datos.
		Source fuente=new DOMSource(documento);
		//Crear el destino de la transformacion.
		Result ficheroXML=new StreamResult(new File(nombreXML2));
		//Crear un transformador.
		try {
			Transformer t=TransformerFactory.newInstance().newTransformer();
			t.transform(fuente, ficheroXML);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
