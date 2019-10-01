package Practicas_1ªTrimestre;

import java.util.Scanner;

public class PracticaFicheros_Main {

	static Scanner leer=new Scanner(System.in);
	static Metodos metod=new Metodos();
	public static void main(String[] args) {
		/*Ejercicio el cual implementara todos los tipos de ficheros que hemos estado viendo,
		 * ello incluye ficheros de texto, binario, de objetos y de acceso aleatorio.*/
		int opcion=0;
		do {
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero de acceso aleatorio a partir del de texto.");
			System.out.println("2.-Mostrar toda la ropa que hay en la tienda "
									+ "(primero debe haber un XML creado).");
			System.out.println("3.-Introducir nueva ropa.");
			System.out.println("4.-Modificar Talla, Precio y Stock.");
			System.out.println("5.-Dar de baja o dar de alta una prenda de ropa.");
			System.out.println("6.-Crear un fichero XML.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			case 1:				
					metod.crearFicheroObjeto();
				break;
			case 2:
					metod.mostrarRopaTienda();
				break;
			case 3:
					metod.insertarRopaTienda();
				break;
			case 4:
					
				break;
			case 5:
					
				break;
			case 6:
				break;
			
			}
		}while(opcion!=0);
	}

}
