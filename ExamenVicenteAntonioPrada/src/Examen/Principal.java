package Examen;

import java.util.Scanner;

public class Principal {

	private static Scanner leer=new Scanner(System.in);
	private static metodos metod=new metodos();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

int opcion=0;
		
		do {
			
			System.out.println("0.-Salir");
			System.out.println("1.-Cargar datos del sistema antiguo.");
			System.out.println("2.-Registrar Venta.");
			System.out.println("3.-Generar estadistica.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			
			case 1:
					metod.crearFicheroYmostrarlo();
					metod.mostrar();
				break;
			case 2:
					metod.registrarVentas();
					metod.mostrarFicheroObjetos();
					
				break;
			case 3:
					metod.generarEstadistica();
				break;
			}
			
			
		}while(opcion!=0);
		
		
	}

}
