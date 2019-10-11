package Explicacion_MySql;

import java.util.Scanner;

public class Principal {

	private static Scanner leer=new Scanner(System.in);
	private static Modelo bdTaller= new Modelo();
	
	public static void main(String[] args) {
		
		if(bdTaller!=null) {
			
			int opcion=0;
			
			do {
				
				System.out.println("0.-Salir");
				System.out.println("1.-Informacion sobre el servidor.");
				System.out.println("2.-Ver tablas de la base de datos.");
				System.out.println("3.-Ver campos de una tabla.");
				System.out.println("4.-Informacion campos de una consulta.");
				//System.out.println("5.-.");
				System.out.println();
				System.out.print("Introduce una de las opciones anteriores: ");
				opcion=leer.nextInt();leer.nextLine();
				System.out.println();
				switch(opcion) {
				
				case 1:
						bdTaller.infoServidor();
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
		}else {
			System.err.println("ERROR: No se ha podido conectar a la base de datos.");
		}

		
	}

}
