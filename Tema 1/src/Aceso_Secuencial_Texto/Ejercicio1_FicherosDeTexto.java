package Aceso_Secuencial_Texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1_FicherosDeTexto {

	public static Scanner leer=new Scanner(System.in);
	static String nombreF="Almacen.txt";
	
	public static void main(String[] args) {
		// Vamosa usar FileReader y BufferedReader.

	//Este menu sera el que usemos en todos los futuros ejercicios.	
		int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Leer/Mostrar.");
			System.out.println("2.-Insertar/A�adir material.");
			System.out.println("3.-Modificar material.");
			System.out.println("4.-Borrar. material");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			
			case 1:
					mostrarFicheros();
				break;
			case 2:
					insertarMaterial();
				break;
			case 3:
				break;
			case 4:
				break;
			
			}
			
			
		}while(opcion!=0);
		
	}
	
	
	
	
		//METODOS DE ESTA CLASE.
	/*--------------------------------------------------------------------------*/

	private static void mostrarFicheros() {
		//Declaramos el fichero para lectura. Usamos BufferedReader.
		BufferedReader fichero=null;
		
		//Abrimos el fichero.
		try {
			fichero=new BufferedReader(new FileReader(nombreF));
			String linea;
			while((linea=fichero.readLine())!=null) {
				//Obtenemos cada campo de la linea.
				String campos[]=linea.split(";");
			 System.out.println("Codigo: "+campos[0]+
					 "\tNombre: "+campos[1]+
					 "\tPrecio: "+Float.parseFloat(campos[2])+
					 "\tStock: "+Integer.parseInt(campos[3])+
					 "\tBaja: "+campos[4]);
			 System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("ERROR: No se ha encontrado el fichero de texto.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			
			//Cerramos el fichero.
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("ERROR: No se ha cerrado el fichero correctamente.");
				}
			}
			
		}	
	}
	
	
	
	
	
	private static void insertarMaterial() {
		
		//Declaramos el fichero de escritura.
		BufferedWriter fichero=null;
		//Abrimos el fichero.
		try {
			fichero=new BufferedWriter(new FileWriter(nombreF, true));
			
			//Pedimos los datos.
			String linea="";
			System.out.print("Introduce el codigo del producto: ");
			linea+=leer.nextLine();
			System.out.println();
			System.out.print("Introduce el nombre del producto: ");
			linea+=leer.nextLine();
			System.out.println();
			System.out.print("Introduce el precio del producto: ");
			linea+=leer.nextLine();
			System.out.println();
			System.out.println("Introduce el stock del producto: ");
			linea+=leer.nextLine();
			System.out.println();
			
			//Por defecto el material esta de alta.
			linea +=";Si\n";
			
			//Escribimos las lineas.
			fichero.write(linea);
			
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
