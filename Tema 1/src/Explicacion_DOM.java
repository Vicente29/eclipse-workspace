
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

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
					mostrarFicheroXML();
				break;
			case 3:
					insertarFicheroXML();
				break;
			case 4:
					modificarFicheroXML();
				break;
			case 5:
					borrarFicheroXML();
				break;
			
			}
			
			
		}while(opcion!=0);

	}

	

	private static void borrarFicheroXML() {
		// TODO Auto-generated method stub
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento=db.parse(nombreXML);
			
			System.out.println("Introduce el codigo: ");
			String codigo=leer.nextLine();
			
			//Obtenemos los elemntos del nodo pieza.
			NodeList nodosPieza=documento.getElementsByTagName("pieza");
			for(int i=0;i<nodosPieza.getLength();i++) {
				Element pieza=(Element) nodosPieza.item(i);
				
				//Comprobamos el atributo codigo.
				if(pieza.getAttribute("codigo").equalsIgnoreCase(codigo)) {
					//Borramos el nodo pieza.
					pieza.getParentNode().removeChild(pieza);
					pasarDOMaXML(documento, nombreXML);
					break;
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	private static void mostrarFicheroXML() {
		
		try {
			DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document documento=db.parse(nombreXML);
			
			//Obtenemos la raiz.
			Element raiz=documento.getDocumentElement();
			//Mostramos la raiz con un metodo recursivo que pinta un nodo y sus hijos.
			mostrarElemento(raiz,0);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void mostrarElemento(Element nodo,int tabulaciones) {
		
		for(int i=0;i<tabulaciones;i++) {
			System.out.print("\t");
		}
		
		//Pintamos el nombre del nodo.
		System.out.print(nodo.getNodeName()+"(");
		
		//Obtenemos los atributos.
		NamedNodeMap atributo=nodo.getAttributes();
		//Mostramos los atributos.
		for(int i=0;i<atributo.getLength();i++) {
			System.out.print(atributo.item(i).getNodeName()+"="+
						atributo.item(i).getNodeValue());
		}
		System.out.println(")");
		
		//Obtenemos los hijos del nodo para llamar de forma recursiva a cada  hijo.
		NodeList nodos=nodo.getChildNodes();
		for(int i=0;i<nodos.getLength();i++) {
			//Comprobamos si el hijo es de tipo texto o de tipo elemento.
			if(nodos.item(i).getNodeType()==Node.TEXT_NODE) {
				for(int j=0;j<tabulaciones+1;j++) {
					System.out.print("\t");
				}
				System.out.println(nodos.item(i).getNodeValue());
			}else {
				mostrarElemento((Element)nodos.item(i),tabulaciones+1);
			}
		}
		
	}
	
	private static void insertarFicheroXML() {
		
		
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento=db.parse(nombreXML);
			
			//Pedimos el codigo.
			System.out.println("Introduce el codigo: ");
			String codigo=leer.nextLine();
			if(!existe(documento,codigo)) {
				
				//Creamos el elemento y lo rellenamos con los datos.
				Element pieza=documento.createElement("pieza");
				//Creamos atributos.
				pieza.setAttribute("codigo", codigo);
				pieza.setAttribute("alta", "Si");
				//Añadimos pieza a piezas.
				documento.getDocumentElement().getElementsByTagName("piezas").item(0).appendChild(pieza);
				
				//nombre de la pieza.
				Element nombre=documento.createElement("nombre");
				pieza.appendChild(nombre);
				System.out.println("Introduce el nombre de la pieza:");
				Text nombrePieza =documento.createTextNode(leer.nextLine());
				nombre.appendChild(nombrePieza);
				
				//Añadimos el precio.
				Element precio=documento.createElement("precio");
				pieza.appendChild(precio);
				System.out.println("Introduce el precio de la pieza:");
				Text precioPieza =documento.createTextNode(Float.toString(leer.nextFloat()));leer.nextLine();
				precio.appendChild(precioPieza);
				
				
				//Añadimos el Stock.
				Element stock=documento.createElement("stock");
				pieza.appendChild(precio);
				System.out.println("Introduce el stock:");
				Text stockPieza =documento.createTextNode(Integer.toString(leer.nextInt()));leer.nextLine();
				stock.appendChild(stockPieza);
		
				pasarDOMaXML(documento,nombreXML);
				
			}else {
				System.err.println("ERROR: Existe ya el codigo.");
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}


	private static boolean existe(Document documento, String codigo) {
		
		boolean resultado=false;
		
		//Obtenemos el nodo pieza.
		NodeList piezas=documento.getElementsByTagName("pieza");
		//Para cada pieza comprobamos si el atributo codigo coincide con el buscado.
		int contador=0;
		while(!resultado && contador<piezas.getLength()) {
			NamedNodeMap atributos=piezas.item(contador).getAttributes();
			for(int i=0;i<atributos.getLength();i++) {
				if(atributos.item(i).getNodeName().equals("codigo")) {
					if(atributos.item(i).getNodeValue().equals(codigo)){
						resultado=true;
					}
				}
			}
			contador++;
		}
		
		return resultado;
	}

	

	private static void modificarFicheroXML() {
		
		
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento=db.parse(nombreXML);
			
			//Pedimos el codigo de la pieza la cual vamos a modificar su precio.
			System.out.println("Introduce el codigo");
			String codigo=leer.nextLine();
			
			//Recuperamos los nodos pieza.
			NodeList nodosPieza=documento.getElementsByTagName("pieza");
			for(int i=0;i<nodosPieza.getLength();i++) {
				//Obtenemos los atributos.
				NamedNodeMap atributos=nodosPieza.item(i).getAttributes();
					if(atributos.getNamedItem("codigo").getNodeValue().equalsIgnoreCase(codigo)) {
						//Pedimos el nuevo precio.
							System.out.println("Introduce el nuevo precio para el preducto: ");
							float precio=leer.nextFloat();leer.nextLine();
							
							//Recuperamos el nodo precio.
							Element nodoPrecio=(Element) nodosPieza.item(i).getChildNodes().item(1);
							nodoPrecio.setTextContent(Float.toString(precio));
							pasarDOMaXML(documento, nombreXML);
							break;
					}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	
	
}
