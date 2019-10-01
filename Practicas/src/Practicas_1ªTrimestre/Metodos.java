package Practicas_1ªTrimestre;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Metodos {

	private int longCodigo=5; 
	private int longNombre=20;
	private int longTalla=15;
	private int longGenero=10;
	private Scanner leer=new Scanner(System.in);
	private  String ficheroTxt="tienda.txt";
	private String ficheroRan="tienda.ran";
	
	
	//METODOS.
/*-----------------------------------------------------------------------------------*/	
	protected void crearFicheroObjeto() {
		// TODO Auto-generated method stub
		//Declaramos los ficheros que vamos a usar.
		BufferedReader ficheroT=null;
		RandomAccessFile ficheroA=null;
		
		//Abrimos los dos ficheros.
		
			try {
				ficheroT=new BufferedReader(new FileReader(ficheroTxt));
				ficheroA=new RandomAccessFile(ficheroRan,"rw");
				
				String linea;
				while((linea=ficheroT.readLine())!=null) {
					
					String campos[]=linea.split(";");
					
					//Leemos el codigo del fichero de texto y lo escribimos en el Aleatorio.
					StringBuilder cadena=new StringBuilder(campos[0]);
					cadena.setLength(longCodigo);
					ficheroA.writeChars(cadena.toString());
					
					//Hacemos lo mismo para el nombre y para los demas String.
					cadena=new StringBuilder(campos[1]);
					cadena.setLength(longNombre);
					ficheroA.writeChars(cadena.toString());
					
					//Talla.
					cadena=new StringBuilder(campos[2]);
					cadena.setLength(longTalla);
					ficheroA.writeChars(cadena.toString());
					
					//Genero.
					cadena=new StringBuilder(campos[3]);
					cadena.setLength(longGenero);
					ficheroA.writeChars(cadena.toString());
					
					
					//Precio.
					ficheroA.writeFloat(Float.parseFloat(campos[4]));
					//Stock.
					ficheroA.writeInt(Integer.parseInt(campos[5]));
					//Alta.
					if(campos[6].equalsIgnoreCase("Si")) {
						ficheroA.writeBoolean(true);
					}else {
						ficheroA.writeBoolean(false);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//Cerramos los ficheros.
				if(ficheroT!=null) {
					try {
						ficheroT.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(ficheroA!=null) {
					try {
						ficheroA.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
	}

	protected void mostrarRopaTienda() {
		// Mostraremos toda la ropa que hay dentro del fichero.
		//Declaramos el fichero.
		RandomAccessFile ficheroA=null;
		//Abrimos el fichero para lectura.
		try {
			ficheroA=new RandomAccessFile(ficheroRan, "r");
			
			//Recorremos mediante un bucle el fichero hasta el final.
			while(true) {
				//Creamos un objeto de tipo ropa.
				Ropa r=new Ropa();
				//Iniciamos el CODIGO a cadena vacia.
				r.setCodigo("");
				//Con el bucle for lo vamos rellenando.
				for(int i=0;i<longCodigo;i++) {
					r.setCodigo(r.getCodigo()+ficheroA.readChar());
				}
				//Hacemos exactamente igual con el resto de String.
				//NOMBRE.
				r.setNombre("");
				for(int i=0;i<longNombre;i++) {
					r.setNombre(r.getNombre()+ficheroA.readChar());
				}
				//TALLA.
				r.setTalla("");
				for(int i=0;i<longTalla;i++) {
					r.setTalla(r.getTalla()+ficheroA.readChar());
				}
				//GENERO.
				r.setGenero("");
				for(int i=0;i<longGenero;i++) {
					r.setGenero(r.getGenero()+ficheroA.readChar());
				}
				//PRECIO.
				r.setPrecio(ficheroA.readFloat());
				//STOCK.
				r.setStock(ficheroA.readInt());
				//ALTA.
				r.setAlta(ficheroA.readBoolean());
				
				r.mostraRopa();	
			}
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ficheroA!=null) {
				try {
					ficheroA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	protected void insertarRopaTienda() {
		/*Antes de hacer nada como vamos a introducir sin duplicar el codigo
		 * como el una tienda real, vamos a pedir el codigo por teclado y 
		 * cotejarlo con los que hay en el fichero para saber si esta o no repetido.*/
		
		Ropa r=new Ropa();
		StringBuilder cadena;
		System.out.println("Introduce el codigo del nuevo producto: ");
		r.setCodigo(leer.nextLine());
		cadena=new StringBuilder(r.getCodigo());
		cadena.setLength(longCodigo);
		
		//Comprobamos si el codigo existe o no.
		if(!existeCodigo(cadena.toString())) {
			
			//Usamos el codigo ya introducido anteriormente.
			r.setCodigo(cadena.toString());
			//Pedimos el Nombre.
			System.out.println("Introduce Nombre de la nueva prenda: ");
			cadena=new StringBuilder(leer.nextLine());
			cadena.setLength(longNombre);
			r.setNombre(cadena.toString());
			//Pedimos la Talla.
			System.out.println("Introduce que tallas o talla tiene: ");
			cadena=new StringBuilder(leer.nextLine());
			cadena.setLength(longTalla);
			r.setTalla(cadena.toString());
			//Pedimos el Genero.
			System.out.println("Introduce el genero al que va dirigido: ");
			cadena=new StringBuilder(leer.nextLine());
			cadena.setLength(longGenero);
			r.setGenero(cadena.toString());
			System.out.println("Introduce el Precio: ");
			r.setPrecio(leer.nextFloat());
			System.out.println("Introduce el Stock: ");
			r.setStock(leer.nextInt());
			r.setAlta(true);
			
			//Declaramos el fichero.
			RandomAccessFile ficheroA=null;
			//Abrimos el fichero.
			try {
				ficheroA=new RandomAccessFile(ficheroRan,"rw");
				
				//Desplazamos el apuntador del fichero al final.
				ficheroA.seek(ficheroA.getFilePointer()+ficheroA.length());
				
				//Escribimos el registro.
				ficheroA.writeChars(r.getCodigo());
				ficheroA.writeChars(r.getNombre());
				ficheroA.writeChars(r.getTalla());
				ficheroA.writeChars(r.getGenero());
				ficheroA.writeFloat(r.getPrecio());
				ficheroA.writeInt(r.getStock());
				ficheroA.writeBoolean(r.isAlta());
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ficheroA!=null) {
					try {
						ficheroA.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else {
			System.err.println("ERROR: No se ha podido hacer la insercion.");
		}
	}

	private boolean existeCodigo(String codigoTeclado) {
		boolean resultado=false;
		
		//Declaramos el fichero.
				RandomAccessFile ficheroA=null;
				try {
					ficheroA=new  RandomAccessFile(ficheroRan,"r");
					
					//Vamos leyendo codigos hasta encontrarlo o llegar fin de fichero.
					while(true && !resultado) {
						//Leemos el codigo.
						String codigo="";
						for(int i= 0;i<longCodigo;i++) {
							codigo +=ficheroA.readChar();
						}
						//Comprobamos si el codigo leido es el buscado.
						if(codigo.equalsIgnoreCase(codigoTeclado)) {
							resultado=true;
						}else {
/*Desplazamos el apuntador del fichero(40 Nombre + 30 Talla + 20 Genero + 4 Precio + 4 Stock + 1 Alta = 99) bytes 
							 * para posicinarlo en el 
							siguiente codigo.*/
							
							ficheroA.seek(ficheroA.getFilePointer()+99);
						}
					}
					
				}catch(EOFException e) {
					
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(ficheroA!=null) {
						try {
							ficheroA.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		
		return resultado;
	}

	
	
	
	
}
