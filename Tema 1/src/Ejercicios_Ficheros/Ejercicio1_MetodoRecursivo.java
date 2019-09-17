package Ejercicios_Ficheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1_MetodoRecursivo {

	public static Scanner leer=new Scanner(System.in);
	
	
	public static void main(String[] args) {
		/* Mostraremos el contenido de una carpeta introducida por teclado asi como 
		 * el contenido de sus hijos.*/

		System.out.print("Introduce la ruta de una carpeta: ");
		String ruta=leer.nextLine();

		metodoRecursivo(ruta);
	}
	
		//METODOS
	/*----------------------------------------------------------------------*/
	
	private static void metodoRecursivo(String ruta) {
		//Pedimos la ruta de una carpeta.
		
		
		//Creamos el objeto file de la ruta.
		File fRuta=new File(ruta);
		
		//Comprobamos si existe y si es una carpeta.
			if(fRuta.exists() && fRuta.isDirectory()) {
			
			/*Obtenemos el contenido de la carpeta. Se guarda en un array de File(archivos)
			 * en una nueva variable.*/
		File[] contenido=fRuta.listFiles();
			
		/*Recorremos el array contenido con un foreach con el cual podremos
		 *  mostrar su contenido.*/
		for(File elemento:contenido) {
			//Nombre.
			System.out.print(elemento.getName()+"\t");
			//Tipo.
			if(elemento.isDirectory()) {
				System.out.print("D"+"\t");
			}else {
				System.out.print("F"+"\t");
			}
			//Permiso de lectura.
			if(elemento.canRead()) {
				System.out.print("r");
			}else {
				System.out.print("-");
			}
			
			//Permiso de escritura.
			if(elemento.canWrite()) {
				System.out.print("w");
			}else {
				System.out.print("-");
			}
			
			//Permiso de ejecucion.
			if(elemento.canExecute()) {
				System.out.println("x");
			}else {
				System.out.println("-");
			}
			
			if(elemento.isDirectory()) {
				metodoRecursivo(elemento.getAbsolutePath());
			}
			
			
		}
			
		}else {
			System.err.println("ERROR: La ruta no existe o no es una carpeta.");
			System.out.println();
		}	
	}


}
