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
				System.out.println("2.-Ver los campos de una tabla.");
				System.out.println("3.-Ver campos de una tabla.");
				System.out.println("4.-Cargar piezas.");
				System.out.println("5.-Mostrar piezas.");
				System.out.println("6.-Crear Reparacion.");
				System.out.println("7.-Añadir pieza a reparacion.");
				System.out.println("8.-Modifcar datos del cliente.");
				System.out.println("9.-Virus borrar todos los cliente.");
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
						System.out.println("Introduce una tabla: ");
						String nombreTabla=leer.nextLine();
						bdTaller.verCamposTabla(nombreTabla);
					break;
				case 3:
						System.out.println("Introduce una consulta sql: ");
						String consulta=leer.nextLine();
						bdTaller.verCamposConsulta(consulta);
					break;
				case 4:
						
					break;
				case 5:
						
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				}
			}while(opcion!=0);
		}else {
			System.err.println("ERROR: No se ha podido conectar a la base de datos.");
		}

		
	}

}
