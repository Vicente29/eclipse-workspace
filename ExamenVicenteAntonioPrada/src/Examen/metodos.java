package Examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class metodos {

	private  Scanner leer=new Scanner(System.in);
	private String ficheroTexto="productosSistemaAntiguo.txt";
	private String ficheroAleatorio="productos.bin";
	private String ficheroVentasOBJ="ventas.obj";
	private int longNombre=100;
	
	
	
	//METODOS DE ESTA CLASE.
/*----------------------------------------------------------------------------*/	
	protected void crearFicheroYmostrarlo() {
		// En este metodo crearemos el fichero.bin a partir del fichero de texto.
		
		//Si el fichero aleatorio existe lo borramos.
				File fichero=new File(ficheroAleatorio);
				if(fichero.exists()) {
					//fichero.delete();
					if(!fichero.delete()) {
						System.err.println("ERROR: No se ha podido borrar el fichero.");
					}
				}
				
				BufferedReader ficheroT=null;
				RandomAccessFile ficheroA=null;
				
				try {
					ficheroT=new BufferedReader(new FileReader(ficheroTexto));
					ficheroA=new RandomAccessFile(ficheroAleatorio,"rw");
					
					String linea;
					while((linea=ficheroT.readLine())!=null) {
					
						//Obtenemos cada campo de la linea.
						String campos[]=linea.split(";");
						
						if(campos[3].equalsIgnoreCase("0")) {
							//Codigo
							ficheroA.writeInt(Integer.parseInt(campos[0]));
							//Precio
							ficheroA.writeFloat(Float.parseFloat(campos[1]));
							//Stock
							ficheroA.writeInt(Integer.parseInt(campos[2]));
							//Nombre.
							StringBuilder cadena=new StringBuilder(campos[4]);
							cadena.setLength(longNombre);
							ficheroA.writeChars(cadena.toString());
						}
					}
					
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
					if(ficheroA!=null) {
						try {
							ficheroA.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		
		
	}



	protected void mostrar() {
		// TODO Auto-generated method stub
		RandomAccessFile ficheroA=null;
		
		try {
			ficheroA=new RandomAccessFile(ficheroAleatorio,"r");
			
			while(true) {
				Productos p=new Productos();
				p.setCodigo(ficheroA.readInt());
				p.setPrecio(ficheroA.readFloat());
				p.setStock(ficheroA.readInt());
				//Leemos el nombre.
				p.setNombre("");
				for(int i=0;i<longNombre;i++) {
					p.setNombre(p.getNombre()+ficheroA.readChar());
				}
				//Mostramos el fichero.
				p.MostrarProductos();						
			}
			
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ficheroA!=null) {
				try {
					ficheroA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println();
	}



	protected void registrarVentas() {
		/*Se va crear un fichero de objetos con el registro de las ventas.
		 * En caso de que este fichero ya exista se debe solo insertar en el la informacion pedida.*/
		
		File fichero=new File(ficheroVentasOBJ);
		
		/*Pedimos el codigo por teclado.*/
		Productos p=new Productos();
		System.out.println("Introduce el codigo del producto: ");
		p.setCodigo(leer.nextInt());
		
		if(fichero.exists()) {
			if(codigoExiste(p.getCodigo())) {
				
				MyObjectOutputStreamVicente ficheroO=null;			
				RandomAccessFile ficheroA=null;
				
				try {
					ficheroO=new MyObjectOutputStreamVicente(new FileOutputStream(ficheroVentasOBJ,true));
					ficheroA=new RandomAccessFile(ficheroAleatorio,"rw");
					
					/*Pedimos la cantidad de stock que se quiere vender de ese producto.*/
					System.out.println("¿Cuanto se va a vender de ese producto?");
					p.setNumVentas(leer.nextInt());
					ficheroA.seek(ficheroA.getFilePointer()+8);
					int stockLeido=ficheroA.readInt();
					if(stockLeido>=p.getNumVentas()) {
						ficheroA.seek(ficheroA.getFilePointer()-4);
						ficheroA.writeInt(stockLeido-p.getNumVentas());
						ficheroA.seek(ficheroA.getFilePointer()-8);
						float precioLeido=ficheroA.readFloat();
						p.setTotalVenta(p.getNumVentas()*precioLeido);
						
						ficheroO.writeObject(p);
						
					}else {
						System.err.println("ERROR: No hay stock suficiente.");
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(ficheroO!=null) {
						try {
							ficheroO.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(ficheroA!=null) {
						try {
							ficheroA.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}else {
				System.err.println("ERROR: Ese codigo no existe.");
			}
		
			
		}else {
			crearFicheroOBJ();
			registrarVentas();
		}
		
	}



	private void crearFicheroOBJ() {
		// TODO Auto-generated method stub
		RandomAccessFile ficheroA=null;
		ObjectOutputStream ficheroO=null;
		
		try {
			ficheroO=new ObjectOutputStream(new FileOutputStream(ficheroVentasOBJ,false));
			ficheroA=new RandomAccessFile(ficheroAleatorio,"r");
			
			while(true) {
				Productos p=new Productos();
				char letra;
				p.setCodigo(ficheroA.readInt());
				//Leemos el precio.
				p.setNumVentas(p.getNumVentas());
				//Leemos el stock.
				p.setTotalVenta(p.getTotalVenta());
			}
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ficheroA!=null) {
				try {
					ficheroA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}



	private boolean codigoExiste(int codigo2) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		//Declaramos el fichero.
		RandomAccessFile f=null;
		try {
			f=new  RandomAccessFile(ficheroAleatorio,"r");
			
			//Vamos leyendo codigos hasta encontrarlo o llegar fin de fichero.
			while(true && !resultado) {
				//Leemos el codigo.
				int codigo=f.readInt();
				//Comprobamos si el codigo leido es el buscado.
				if(codigo2==codigo) {
					resultado=true;
				}else {
					/*Desplazamos el apuntador del fichero 48 bytes para posicinarlo en el 
					siguiente codigo.*/
					f.seek(f.getFilePointer()+208);
				}
			}
			
		}catch(EOFException e) {
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}



	protected void mostrarFicheroObjetos() {
		// TODO Auto-generated method stub
		
		//Declaramos el fichero.
				ObjectInputStream ficheroO=null;
				
				//Abrimos el fichero.
				try {
					ficheroO=new ObjectInputStream(new FileInputStream(ficheroVentasOBJ));
					//leemos todos los objetos.
					while(true) {
						Productos p=(Productos)ficheroO.readObject();
						
						p.MostrarObjeto();
					}
					
				}catch(EOFException e) {
					
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(ficheroO!=null) {
						try {
							ficheroO.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
		
		
	}



	protected void generarEstadistica() {
		// TODO Auto-generated method stub
		
		Tienda t=new Tienda();
		System.out.println("Introduce el nombre del taller");
		t.setNombre(leer.nextLine());
		
		t.setProducto(obtenerProdcutos());
		
		marshal(t);
	}



	private void marshal(Tienda t) {
		// TODO Auto-generated method stub
		
	}



	private ArrayList<Productos> obtenerProdcutos() {
		// TODO Auto-generated method stub
		
		
		
		
		
		return null;
	}
	
	
}
