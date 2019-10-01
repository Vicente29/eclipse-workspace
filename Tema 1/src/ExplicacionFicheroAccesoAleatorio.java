import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import Ficheros_de_Objetos.Pieza;

public class ExplicacionFicheroAccesoAleatorio {

	public static Scanner leer=new Scanner(System.in);
	 static String nombreFO="Almacen.obj";
	 static String nombreFA="Almacen.fa";
	 //Fijamos la longitud del campo codigo y el campo nombre.
	 //Ya que los String deben de tener una longitud fija.
	static int lCodigo=5,lNombre=20;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero aleatorio a partir de fichero objetos.");
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
					crearFicheroAleatorio();
				break;
			case 2:
					mostrarFicheroAleatorio();
				break;
			case 3:
					insertarFicheroAleatorio();
				break;
			case 4:
					modificarFicheroAleatorio();
				break;
			case 5:
					bajaMaterialFicheroAleatorio();
				break;
			
			}
			
			
		}while(opcion!=0);
		
		
	}

	//METODOS.	
/*---------------------------------------------------------------------------------------*/
	
	private static void crearFicheroAleatorio() {
		
		//Si el fichero aleatorio existe lo borramos.
		File fichero=new File(nombreFA);
		if(fichero.exists()) {
			if(!fichero.delete()) {
				System.err.println("ERROR: No se ha podido borrar el fichero.");
			}
		}
		//Declaramos los ficheros.
		ObjectInputStream fO=null;
		RandomAccessFile fA=null;
		
		//Abrimos fichero.
		try {
			fO=new ObjectInputStream(new FileInputStream(nombreFO));
			fA=new RandomAccessFile(nombreFA, "rw");
			
			//Leemos los registros FO y los pasamos a fA.
			while(true) {
				
				Pieza p= (Pieza) fO.readObject();
				
				//Escribimos el codigo.
				//Creamos un codigo de 5 caracteres.
				StringBuilder cadena=new StringBuilder(p.getCodigo());
				
				cadena.setLength(lCodigo);
				fA.writeChars(cadena.toString());
				
				//Creamos un Nombre de 20 caracteres.
				 cadena=new StringBuilder(p.getNombre());
				 
				cadena.setLength(lNombre);
				fA.writeChars(cadena.toString());
				
				fA.writeFloat(p.getPrecio());
				fA.writeInt(p.getStock());
				fA.writeBoolean(p.isAlta());
				
				
				
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
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fA!=null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	private static void mostrarFicheroAleatorio() {
		
		RandomAccessFile f=null;
		try {
			f=new RandomAccessFile(nombreFA, "r");
			//Llegamos hasta el final del fichero.
			while(true) {
				Pieza p=new Pieza();
				p.setCodigo("");
				for(int i=0;i<lCodigo;i++) {
					p.setCodigo(p.getCodigo()+f.readChar());
				}
				
				//Leemos el nombre.
				p.setNombre("");
				for(int i=0;i<lNombre;i++) {
					p.setNombre(p.getNombre()+f.readChar());
				}
				
				p.setPrecio(f.readFloat());
				p.setStock(f.readInt());
				p.setAlta(f.readBoolean());
				
				//Mostramos el fichero.
				p.mostra();
			}
			
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(f!=null) {
				try {
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void insertarFicheroAleatorio() {
		
		//Pedimos el codigo.
		Pieza p=new Pieza();
		StringBuilder cadena ;
		System.out.println("Introduce el codigo: ");
		p.setCodigo(leer.nextLine());
		cadena=new StringBuilder(p.getCodigo());
		cadena.setLength(lCodigo);
		
		//Comprobamos si el codigo existe.
		if(!codigoExiste(cadena.toString())) {
			
			p.setCodigo(cadena.toString());
			System.out.println("Introduce el Nombre: ");
			cadena=new StringBuilder(leer.nextLine());
			cadena.setLength(lNombre);
			p.setNombre(cadena.toString());
			System.out.println("Introduce el Precio: ");
			p.setPrecio(leer.nextFloat());
			System.out.println("Introduce el Stock: ");
			p.setStock(leer.nextInt());
			p.setAlta(true);
			
			//Nos queda escribir el objeto en el fichero.
			RandomAccessFile f=null;
			
			try {
				f=new RandomAccessFile(nombreFA,"rw");
				//Desplazamos el apuntador del fichero al final.
				f.seek(f.getFilePointer()+f.length());
				
				//Escribimos el registro.
				f.writeChars(p.getCodigo());
				f.writeChars(p.getNombre());
				f.writeFloat(p.getPrecio());
				f.writeInt(p.getStock());
				f.writeBoolean(p.isAlta());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(f!=null) {
					try {
						f.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}else {
			System.err.println("ERROR: El codigo ya existe.");
		
		}
		
		
	}

	
	private static boolean codigoExiste(String codigoBuscado) {
		boolean resultado=false;
		
		//Declaramos el fichero.
		RandomAccessFile f=null;
		try {
			f=new  RandomAccessFile(nombreFA,"r");
			
			//Vamos leyendo codigos hasta encontrarlo o llegar fin de fichero.
			while(true && !resultado) {
				//Leemos el codigo.
				String codigo="";
				for(int i= 0;i<lCodigo;i++) {
					codigo +=f.readChar();
				}
				//Comprobamos si el codigo leido es el buscado.
				if(codigo.equalsIgnoreCase(codigoBuscado)) {
					resultado=true;
				}else {
					/*Desplazamos el apuntador del fichero 48 bytes para posicinarlo en el 
					siguiente codigo.*/
					
					f.seek(f.getFilePointer()+49);
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
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}
	
	
	
	private static void modificarFicheroAleatorio() {
		
		//Pedimos el codigo a modificar.
		System.out.println("Introduce el nombre del codigo: ");
		StringBuilder codigo=new StringBuilder(leer.nextLine());
		codigo.setLength(lCodigo);
		//Declaramos fichero.
		RandomAccessFile fichero=null;
		
		//Lo abrimos.
		try {
			fichero=new RandomAccessFile(nombreFA,"rw");
			
			//Leemos los registros hasta encontrar el que queremos.
			while(true) {
				//Leemos el codigo.
				String codFich="";
				//Hacemos un bucle con la longitud del String.
				for(int i=0;i<lCodigo;i++) {
					//Concatenamos en cada vuelta de bucle.
					codFich+=fichero.readChar();
				}
				//Comprobamos si el codigo leido es el buscado.
				if(codigo.toString().equalsIgnoreCase(codFich)) {
					//Comprobamos si esta de alta.
					//Desplazamos el puntero para leer el campo alta. 48 Bytes.
					fichero.seek(fichero.getFilePointer()+48);
					//Comprobamos si esta de alta.
					if(fichero.readBoolean()) {
						//Pedimos el nuevo precio.
						System.out.println("Introduce el nuevo precio: ");
						float nuevoPrecio=leer.nextFloat();leer.nextLine();
						//Desplazamso el puntero del fichero 9 Bytes hacia atras.
						fichero.seek(fichero.getFilePointer()-9);
						//Escribimos en el fichero el nuevo precio.
						fichero.writeFloat(nuevoPrecio);
						//Finalizamos el bucle.
						break;
					}
					
				}else {
					//Si el codigo no es el buscado nos saltamos los bytes para posicionarnos
					//en el siguiente codigo. En este caso 49 Bytes.
					fichero.seek(fichero.getFilePointer()+49);
					
				}
			}
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
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
	
	private static void bajaMaterialFicheroAleatorio() {
		
		//Pedimos el codigo a modificar.
				System.out.println("Introduce el nombre del codigo: ");
				StringBuilder codigo=new StringBuilder(leer.nextLine());
				codigo.setLength(lCodigo);
				//Declaramos fichero.
				RandomAccessFile fichero=null;
				
				//Lo abrimos.
				try {
					fichero=new RandomAccessFile(nombreFA,"rw");
					
					//Leemos los registros hasta encontrar el que queremos.
					while(true) {
						//Leemos el codigo.
						String codFich="";
						//Hacemos un bucle con la longitud del String.
						for(int i=0;i<lCodigo;i++) {
							//Concatenamos en cada vuelta de bucle.
							codFich+=fichero.readChar();
						}
						//Comprobamos si el codigo leido es el buscado.
						if(codigo.toString().equalsIgnoreCase(codFich)) {
							
							//Nos desplazamos hasta el campo alta y lo modificamos. 48 Bytes
							fichero.seek(fichero.getFilePointer()+48);
							//Escribimos Alta = false para dar de baja.
							fichero.writeBoolean(false);
							//Finalizamos el bucle.
							break;
								
						}else {
							//Si el codigo no es el buscado nos saltamos los bytes para posicionarnos
							//en el siguiente codigo. En este caso 49 Bytes.
							fichero.seek(fichero.getFilePointer()+49);
							
						}
					}
					
				}catch(EOFException e) {
					
				}catch (FileNotFoundException e) {
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
	
	
	
	
}
