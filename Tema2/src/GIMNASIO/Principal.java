package GIMNASIO;

import java.util.Scanner;

public class Principal {

	public static Scanner leer=new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Modelo gimnasio=new Modelo();
		if(gimnasio.getConexion()!=null) {
			//Pedimos usuario y clave.
			String us,cl;
			System.out.println("Introduce el usuario: ");
			us=leer.nextLine();
			System.out.println("Inroduce la contraseña: ");
			cl=leer.nextLine();
			
				//Hacemos un login para comprobar el usuario y la contraseña.
			if(gimnasio.comprobarDatos(us,cl).equals('A')) {
				
			}else {
				System.err.println("ERROR: Usuario o contraseña invalido.");
			}
			
			
			//Cerramos la base de datos.
			gimnasio.cerrar();
		}else {
			System.err.println("ERROR: No se ha podido conectar a la BD.");
		}
		
		
	}

}
