package Aceso_Secuencial_Texto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
			System.out.println("3.-Modificar material.");
			System.out.println("4.-Borrar. material");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			
			case 1:
					crearFicheroBinario();
				break;
			case 2:
					
				break;
			case 3:
					
				break;
			case 4:
					
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
				
				//Obtenemos los campos de la linea de texto le�da.
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
	
	
	
}