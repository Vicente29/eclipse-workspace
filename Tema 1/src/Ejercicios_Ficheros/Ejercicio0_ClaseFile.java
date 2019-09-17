package Ejercicios_Ficheros;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio0_ClaseFile {

		//Declaracion del teclado.
		public static	Scanner leer=new Scanner(System.in);

		//Declaramos el formato de fecha con el que trabajaremos.
		public static SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
	public static void main(String[] args) throws IOException {
		// Ejercicio de la Clase File PAQUETE java.io

		int opcion=0;
		//Hacemos un do while como bucle de este ejecicio.
		do {
			System.out.println("0.-Salir.");
			System.out.println("1.-Mostrar la ruta absoluta de la carpeta actual.");
			System.out.println("2.-Pedimos y Mostramos la informacion de la ruta.");
			System.out.println("3.-Mostrar contenido de carpeta.");
			System.out.println("4.-Crear/renombrar/Borrar.");
			System.out.println();
			System.out.print("¿Cual va a ser su seleccion? : ");
			opcion=leer.nextInt();leer.nextLine();
			switch(opcion) {
			case 1:
					mostrarRutaActual();
				break;
			case 2: 
					InformacionRuta();
				break;
			case 3:
					mostrarContenido();
				break;
			case 4:
					CrearRenombrarBorrar();
				break;
			}
			
		}while(opcion!=0);
	}
	
	
	


	//METODOS.
/*---------------------------------------------------------------------------------*/	
	private static void mostrarRutaActual() {
		//Declaramos un objeto file que apunte a la carpeta actual.
		
		File carpeta=new File(".");
		//Mostramos la ruta absoluta de mi carpeta actual.
		System.out.println("Mi ruta: "+carpeta.getAbsolutePath());
		System.out.println("");
	}

	
	private static void InformacionRuta() {

		System.out.print("Introduce una ruta: ");
		String ruta=leer.nextLine();
		
		//Creamos objeto file a la ruta introducida.
		File fRuta=new File(ruta);
		
		//Comprobamos si existe
		if(fRuta.exists()) {
			//Mostramos si es fichero o carpeta.
			if( fRuta.isDirectory()) {
				System.out.println("La ruta es una carpeta.");
			}else {
				System.out.println("Es un fichero.");
			}
			//Mostramos la fecha de modificacion.
			Date fecha=new Date(fRuta.lastModified());
			System.out.println("Fecha ultima modificacion: "+df.format(fecha));
			
			//Mostramos el tamaño
			System.out.println("Tamaño: "+fRuta.length());
			System.out.println("");
			
		}
		else {
			System.err.println("ERROR: La ruta introducida no existe.");
		}
	}
	
	
	private static void mostrarContenido() {
		
		//Pedimos la ruta de una carpeta.
		System.out.print("Introduce la ruta de una carpeta: ");
		String ruta=leer.nextLine();
		
		//Creamos el objeto file de la ruta.
		File fRuta=new File(ruta);
		
		//Comprobamos si existe y si es una carpeta.
		if(fRuta.exists() && fRuta.isDirectory()) {
			
			/*Obtenemos el contenido de la carpeta. Se guarda en un array de File(archivos)
			 * en una nueva variable.*/
		File[] contenido=fRuta.listFiles();
			
		/*Recorremos el array contenido con un foreach con el cual podremos mostrar su contenido.*/
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
		}
			
		}else {
			System.err.println("ERROR: La ruta no existe o no es una carpeta.");
			System.out.println();
		}

	}
	
	private static void CrearRenombrarBorrar() throws IOException {
		
		System.out.print("Introduce la ruta de la carpeta/archivo: ");
		String ruta=leer.nextLine();
		System.out.println();
		
		//Creamos el objeto file.
		File fRuta=new File(ruta);
		//Comprobamos si existe 
		if(fRuta.exists()) {
			System.out.print("¿1.-Renombrar o 2.- Borrar?: ");
			int opcion=leer.nextInt();leer.nextLine();
			if(opcion==1) {
				//renombrar.
				//Creamos un nuevo objeto File para la nueva carpeta.
				System.out.print("Introduce el nuevo nombre de la carpeta/archivo: ");
				File nuevo=new File(leer.nextLine());
				System.out.println();
				
				if(!nuevo.exists()) {
					if(!fRuta.renameTo(nuevo)) {
						System.err.println("ERROR: 404 no se pudo renombrar.");
					}
				}else {
					System.err.println("ERROR: El nuevo nombre ya existe.");
				}
			}else {
				//Borrar.
				
				if(!fRuta.delete()) {
					System.err.println("ERROR: No se pudo borrar el archivo.");
				}
				
			}
			
		}else {
			//Sino existe el archivo lo crea.
			//Pedimos tipo
			System.out.println("1.-Carpeta / 2.-Archivo: ");
			int opcion=leer.nextInt();leer.nextLine();
			
			if(opcion==1) {
				//Creamos.
				if(!fRuta.mkdir()) {
					System.err.println("ERROR: No se pudo crear la carpeta.");
				}
			}else {
				//Archivo.
				if(!fRuta.createNewFile()) {
					System.err.println("ERROR: No se ha podido crear el archivo.");
				}
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
