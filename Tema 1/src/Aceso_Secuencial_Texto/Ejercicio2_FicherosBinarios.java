package Aceso_Secuencial_Texto;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2_FicherosBinarios {
	
 public static Scanner leer=new Scanner(System.in);
 static String nombreF="Almacen.txt";
 static String nombreFB="Almacen.bin";
 
 
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
					crearFicheroBinario();
				break;
			case 2:
					mostrarFicheroBinario();
				break;
			case 3:
					insertarFicheroBinario();
				break;
			case 4:
					modificarBorrarFicheroBinario(false);
				break;
			case 5:
					modificarBorrarFicheroBinario(true);
				break;
			
			}
			
			
		}while(opcion!=0);
		
	}
	
	//METODOS.	
/*-----------------------------------------------------------------------------------*/		

	public static void crearFicheroBinario() {
		
		//Declaramos los objetos para poder trabajar con ficheros.
		BufferedReader ficheroT=null;
		DataOutputStream ficheroB=null;
		
		//Abrimos los ficheros.
		try {
			ficheroT=new BufferedReader(new FileReader(nombreF));
			ficheroB=new DataOutputStream(new FileOutputStream(nombreFB,false));
			
			//Recorremos todas las lineas del fichero de texto.
			String linea;
			while((linea=ficheroT.readLine())!=null) {
				
				//Obtenemos los campos de la linea de texto leída.
				String campos[]=linea.split(";");
				//Escribimos los campos en el fichero binario.
				//Codigo.
				ficheroB.writeChars(campos[0]+"\n");
				//Nombre.
				ficheroB.writeChars(campos[1]+"\n");
				//Precio.
				ficheroB.writeFloat(Float.parseFloat(campos[2]));
				//Stock.
				ficheroB.writeInt(Integer.parseInt(campos[3]));
				//Alta.
				if(campos[4].equalsIgnoreCase("Si")) {
					ficheroB.writeBoolean(true);
				}else {
					ficheroB.writeBoolean(false);
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("ERRRO: No se ha encontrado el fichero.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(ficheroT!=null) {
				try {
					ficheroT.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ficheroB!=null) {
				try {
					ficheroB.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		}
		
		
	}
	
	
	
	private static void mostrarFicheroBinario() {
		
		//Declaramos el fichero.
		DataInputStream fichero=null;
		//Abrimos el fichero.
		try {
			fichero=new DataInputStream(new FileInputStream(nombreFB));
			
			while(true) {
				
				//Leemos en el mismo orden que estan los datos en el fichero.
				
				//Leemos el codigo.
				String codigo="";
				char letra;
				while((letra=fichero.readChar())!='\n') {
					codigo+=letra;
				}
				
				//Leemos el nombre.
				String nombre="";
				while((letra=fichero.readChar())!='\n') {
					nombre+=letra;
				}
				
				//Leemos el precio.
				float precio=fichero.readFloat();
				
				//Leemos el Stock.
				int stock=fichero.readInt();
				
				//Leemos el alta.
				boolean alta=fichero.readBoolean();
				
				//Mostramos el registro.
				System.out.println("Codigo: "+codigo+
						"\tNombre: "+nombre+
						"\tPrecio: "+precio+
						"\tStock: "+stock+
						"\tAlta: "+alta);
				
			}
			//Capturamos la excepcion al llegar al final del fichero.
		}	
		catch(EOFException e) {
			
		}	
		 catch (FileNotFoundException e) {
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
	
	
	private static void insertarFicheroBinario() {
		
		//Declaramos el fichero.
		DataOutputStream fichero=null;
		//Abrimos el fichero binario.
		try {
			fichero=new DataOutputStream(new FileOutputStream(nombreFB,true));
			
			//Pedimos los datos.
			System.out.println("Introduce el Codigo: ");
			fichero.writeChars(leer.nextLine()+"\n");
			System.out.println("Introduce el Nombre: ");
			fichero.writeChars(leer.nextLine()+"\n");
			System.out.println("Introduce el Precio: ");
			fichero.writeFloat(leer.nextFloat());leer.nextLine();
			System.out.println("Introduce el Stock: ");
			fichero.writeFloat(leer.nextFloat());leer.nextLine();
			
			//Alta siempre a true.
			fichero.writeBoolean(true);
			
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

	private static void modificarBorrarFicheroBinario(boolean borrar) {
		
		//Declaramos los ficheros.
		DataInputStream fichero=null;
		DataOutputStream ficheroTmp=null;
		
		//Abrimos los ficheros.
		try {
			
			fichero=new DataInputStream(new FileInputStream(nombreFB));
						/*Append = false para * sobreescribir el fichero si existe.*/
			ficheroTmp=new DataOutputStream(new FileOutputStream("almacen.tmp",false));
			
			//Pedimos el registro a tratar.
			System.out.println("Introduce el codigo que queremos borrar/modificar: ");
			String codigo=leer.nextLine();
			//Leemos todos los registros del fichero original.
			while(true) {
				
				//Leemos el codigo.
				String codigo2="";
				char letra;
				while((letra=fichero.readChar())!='\n') {
					codigo2+=letra;
				}
				
				//leemos el nombre
				String nombre="";
				while((letra=fichero.readChar())!='\n') {
					nombre+=letra;
				}
				
				//Leemos el precio.
				float precio=fichero.readFloat();
				
				//Leemos el stock.
				float stock=fichero.readFloat();
				
				//Leemos el alta.
				boolean alta=fichero.readBoolean();
				if(codigo.equalsIgnoreCase(codigo2)) {
					
					if(!borrar) {
						//Modificamos solamente el nombre.
						System.out.println("Introduce un nuevo nombre: ");
						nombre=leer.nextLine();
						
						ficheroTmp.writeChars(codigo2+"\n");
						ficheroTmp.writeChars(nombre+"\n");
						ficheroTmp.writeFloat(precio);
						ficheroTmp.writeFloat(stock);
						ficheroTmp.writeBoolean(alta);
						
					}
					
					
				}else {
					//Escribimos los datos en el fichero temporal tal y como se han leido.
					ficheroTmp.writeChars(codigo2+"\n");
					ficheroTmp.writeChars(nombre+"\n");
					ficheroTmp.writeFloat(precio);
					ficheroTmp.writeFloat(stock);
					ficheroTmp.writeBoolean(alta);
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
			
			if(ficheroTmp!=null) {
				try {
					ficheroTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		//Borramos el fichero original y Renombramos el ficheroTmp.
		
		File fO=new File(nombreFB);
		if(!fO.delete()) {
			System.err.println("ERROR: Se hizo mal la operacion de borrado.");
		}
		
		File fTmp=new File("almacen.tmp");
		if(!fTmp.renameTo(fO)) {
			System.err.println("ERROR: No se ha renombrado el fichero.");
		}
		
	}
	
	

	
	
	
	
}
