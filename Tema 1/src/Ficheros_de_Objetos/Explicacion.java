package Ficheros_de_Objetos;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Explicacion {

	public static Scanner leer=new Scanner(System.in);
	 static String nombreFO="Almacen.obj";
	 static String nombreFB="Almacen.bin";
	 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero objeto a partir de fichero binario.");
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
					crearFicheroObjeto();
				break;
			case 2:
					mostrarFicheroBinario();
				break;
			case 3:
					insertarFicheroObjeto();
				break;
			case 4:
					
				break;
			case 5:
					
				break;
			
			}
			
			
		}while(opcion!=0);
	}

	//METODOS.
/*--------------------------------------------------------------------------*/
	
	private static void crearFicheroObjeto() {
		//Declaramos el fichero.
		DataInputStream fBinario=null;
		ObjectOutputStream fObjetos=null;
		
		//Abrimos los ficheros.
		try {
			fBinario=new DataInputStream(new FileInputStream(nombreFB));
			//Añadir a falso. Siempre se crear el fichero.
			fObjetos=new ObjectOutputStream(new FileOutputStream(nombreFO,false));
			
			//Leemos los datos del binario
			while(true) {
				Pieza p=new Pieza();
				char letra;
				//Leemos el codigo.
				p.setCodigo("");
				while((letra=fBinario.readChar())!='\n') {
					p.setCodigo(p.getCodigo()+letra);
				}
				//Leemos el nombre.
				p.setNombre("");
				while((letra=fBinario.readChar())!='\n') {
					p.setNombre(p.getNombre()+letra);
				}
				//Leemos el precio.
				p.setPrecio(fBinario.readFloat());
				//Leemos el stock.
				p.setStock(fBinario.readInt());
				//Leemos el alta.
				p.setAlta(fBinario.readBoolean());
				
				
				//Guardamos el objeto p en el fichero de objetos.
				fObjetos.writeObject(p);
				
			}
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(fBinario!=null) {
				try {
					fBinario.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fObjetos!=null) {
				try {
					fObjetos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private static void mostrarFicheroBinario() {
		
		//Declaramos el fichero.
		ObjectInputStream fObjeto=null;
		
		//Abrimos el fichero.
		try {
			fObjeto=new ObjectInputStream(new FileInputStream(nombreFO));
			//leemos todos los objetos.
			while(true) {
				Pieza p=(Pieza)fObjeto.readObject();
				
				p.mostra();
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
			if(fObjeto!=null) {
				try {
					fObjeto.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
	
	private static void insertarFicheroObjeto() {
		
		//Comprobamos si existe el fichero de objetos
		File f=new File(nombreFO);
		
		Pieza p=new Pieza();
		
		System.out.println("Introduce el Codigo: ");
		p.setCodigo(leer.nextLine());
		System.out.println("Introduce el Nombre: ");
		p.setNombre(leer.nextLine());
		System.out.println("Introduce el Precio: ");
		p.setPrecio(leer.nextFloat());
		System.out.println("Introduce el Stock: ");
		p.setStock(leer.nextInt());
		p.setAlta(true);
		
		
		if(f.exists()) {
			//Declaramos de la clase MyObjectOutputStreamVicente.
			MyObjectOutputStreamVicente fichero=null;
			
			//Abrimos el fichero.
			try {
				fichero=new MyObjectOutputStreamVicente(new FileOutputStream(nombreFO,true));
				
				fichero.writeObject(p);
				
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
			
		}else {
			
			//Sino existe de ObjectOutputStream.
			ObjectOutputStream fichero=null;
			
			//Abrimos el fichero para sobreescribir.
			try {
				fichero=new ObjectOutputStream(new FileOutputStream(nombreFO,false));
				
				fichero.writeObject(p);
				
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
		
		
		
		
}
	
	
	
}
