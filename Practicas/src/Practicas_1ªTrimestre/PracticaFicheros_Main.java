package Practicas_1ªTrimestre;

import java.util.Scanner;

public class PracticaFicheros_Main {

	static Scanner leer=new Scanner(System.in);
	static Metodos metod=new Metodos();
	static String ficheroTxt="tienda.txt";
	static String ficheroBin="tienda.bin";
	static String ficheroObj="tienda.obj";
	public static void main(String[] args) {
		/*Ejercicio el cual implementara todos los tipos de ficheros que hemos estado viendo,
		 * ello incluye ficheros de texto, binario, de objetos y de acceso aleatorio.*/
		int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Crear fichero (tres formas de hacerlo).");
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
				
					int salir=0;
					do {
						
						System.out.println("0.-Salir");
						System.out.println("1.-De fichero de texto a fichero binario.");
						System.out.println("2.-De fichero de texto a fichero de objeto.");
						System.out.println("3.-De fichero de texto a fichero de acceso aleatorio.");
						System.out.print("Elije una de las 3 ociones: ");
						salir=leer.nextInt();
						System.out.println();
						switch(salir) {
						case 1:
								metod.crearFicheroBinario(ficheroTxt,ficheroBin);
							break;
						case 2:
								
							break;
						case 3:
								
							break;
						}
					}while(salir!=0);
					
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

}
