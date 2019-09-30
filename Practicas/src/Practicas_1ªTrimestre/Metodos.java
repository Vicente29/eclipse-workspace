package Practicas_1ªTrimestre;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Metodos {

	
	
	
	
	//METODOS.
/*-----------------------------------------------------------------------------------*/	
	public void crearFicheroBinario(String ficheroTxt, String ficheroBin) {
		// Crearemos un fichero binario a partir del fichero de texto.
		//Declaramos las variables que vamos a usar.
		BufferedReader ficheroT=null;
		DataOutputStream ficheroB=null;
		
		try {
			//Abrimos esos ficheros. El de texto como lectura y el binario como escritura.
			ficheroT=new BufferedReader(new FileReader(ficheroTxt));
			ficheroB=new DataOutputStream(new FileOutputStream(ficheroBin));
			
			//Recorremos todas las lineas del fichero de texto.
			//Para ello nos declaramos una variable String linea donde guardaremos la informacion.
			String linea;
			//Usamos un bucle while para recorrer el fichero de texto.
			while((linea=ficheroT.readLine())!=null) {
				//Dentro de un Array de String guardaremos los campos de la linea leida.
				String campos[]=linea.split(";");
				//Escribimos los campos en el fichero binario.
				//Campo codigo.
				ficheroB.writeChars(campos[0]+"\n");
				//Campo Nombre.
				ficheroB.writeChars(campos[1]+"\n");
				//Campo Talla.
				ficheroB.writeChars(campos[2]+"\n");
				//Campo Genero.
				ficheroB.writeChars(campos[3]+"\n");
				//Campo Precio.
				ficheroB.writeFloat(Float.parseFloat(campos[4]+"\n"));
				//Campo Stock.
				ficheroB.writeInt(Integer.parseInt(campos[5]+"\n"));
				//Campo alta.
				if(campos[6].equalsIgnoreCase("Si")) {
					ficheroB.writeBoolean(true);
				}else {
					ficheroB.writeBoolean(false);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//Cerramos el fichero de texto y el binario.
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
