package Explicacion_MySql;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class Modelo {

	private Connection conexion=null;
	private String url="jdbc:mysql://localhost:3306/taller?serverTimezone=UTC";
	private String usuario="root";
	private String contraseña="root";
	
	
	public Modelo() {
	
		try {
			//Cargamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creamos la conexion a la base de datos.
			conexion=DriverManager.getConnection(url,usuario,contraseña);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR: No se ha cargado bien el driver.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("FATAL ERROR 404");
		}
		
		
	}
	
	protected Connection getConexion() {
		return conexion;
	}

	protected void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	protected void infoServidor() {
		//Obetenemos los metadatos.
		try {
			 DatabaseMetaData metadatos= conexion.getMetaData();
			 System.out.println("Sistema gestor BD: "+metadatos.getDatabaseProductName()+
					 "\nVersion del sistema BD: "+metadatos.getDatabaseProductVersion()+
					 "\nVersion del driver: "+metadatos.getDriverVersion());
			 System.out.println();
			 System.out.println("Nombre de la basdes de datos que tenemos: ");
			 ResultSet bds=metadatos.getCatalogs();
			 while(bds.next()) {
				 /*Mostramos el dato que hay en la primera columna del resultset.
				  * Hay que ver la ayuda ya que cada resultset tiene una estructura,
				  * en este caso hay una unica columna con el nombre de la base de datos.*/
				 System.out.println(bds.getString(1));
			 }
			 System.out.println();
			 System.out.println("Tablas de la BD: ");
			 /*El metodo necesita 4 parametros que son: ----> ver la ayuda.
			  * 1-La base de datos de la que queremos reculperar las tablas o null para todas las bases de datos.  
			  * 2-Patron con el nombre del esquema o null para todos.
			  * 3-Patron con el nombre de las tablas o null para todas.
			  * 4-Tipo de tablao null para todo tipo de tablas.*/
			 ResultSet tablas=metadatos.getTables("taller", null, null, null);
			 while(tablas.next()) {
				 /*Mostramos la columna 3 del resultset porque es donde esta la columna.*/
				 System.out.println(tablas.getString(3));
			 }
			 System.out.println();
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void verCamposTabla(String nombreTabla) {
		
		//Obtenemos los metadatos.
		try {
			DatabaseMetaData metadatos= conexion.getMetaData();
			/*Obtenmos los campos de la tabla.*/
		ResultSet campos=metadatos.getColumns("taller", null, nombreTabla, null);
		while(campos.next()) {
			System.out.println("Campos: "+campos.getString(4)+
					"\tTipo SQL: "+campos.getInt(5)+
					"\tTipo: "+campos.getString(6)+
					"\tTamaño: "+campos.getInt(7));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void verCamposConsulta(String consulta) {
		// TODO Auto-generated method stub
		
		/*Declaramos la consulta a ejecutar.*/
		
		try {
			Statement c=conexion.createStatement();
			ResultSet resultado=c.executeQuery(consulta);
			/*Obtenemos los metadatos.*/
		ResultSetMetaData metadatos=resultado.getMetaData();
		
		System.out.println("Columnas: "+metadatos.getColumnCount());
		//Datos de las columnas.
		for(int i=1;i<metadatos.getColumnCount();i++) {
			System.out.println("Campo: "+metadatos.getColumnName(i)+
					"\tTipo: "+metadatos.getColumnTypeName(i)+
					"\tTabla: "+metadatos.getTableName(i));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	protected void cargarPiezas() {
		// TODO Auto-generated method stub
		
		ObjectInputStream fichero=null;
		String consulta="insert into pieza values ";
		boolean okeyConsulta=false;
		try {
			fichero=new ObjectInputStream(new FileInputStream("Almacen.obj"));
			
			while(true) {
				okeyConsulta=true;
		Ficheros_de_Objetos.Pieza p= (Ficheros_de_Objetos.Pieza) fichero.readObject();
				consulta+="(null,'"+p.getNombre()+"',"+p.getPrecio()+","+p.getStock()+","+p.isAlta()+"),";
				
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
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(okeyConsulta) {
			try {
				//Vamos a quitar la ultima coma de la consulta.
				consulta=consulta.substring(0, consulta.length()-1);
				Statement sentencia=conexion.createStatement();
				int ok=sentencia.executeUpdate(consulta);
				System.out.println("Se han creado "+ok+" piezas");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	protected void mostrarPiezas() {
		// TODO Auto-generated method stub
		
		try {
			Statement sentencia=conexion.createStatement();
		ResultSet resultado=sentencia.executeQuery("select * from pieza");
			while(resultado.next()) {
				Pieza p=new Pieza();
				p.setCodigo(resultado.getInt(1));
				p.setNombre(resultado.getString(2));
				p.setPrecio(resultado.getFloat(3));
				p.setStock(resultado.getInt(4));
				p.setAlta(resultado.getBoolean(5));
				p.mostrar();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void estadisticaPieza() {
		// TODO Auto-generated method stub
		try {
			/*Creamos una sentencia con un parametro where que se ponen con ?*/
			PreparedStatement sentencia=conexion.prepareStatement("select count(*), max(precio), avg(precio) from pieza where alta=? ");
			//Antes de ejecutar rellenamos los parametros.
			sentencia.setBoolean(1, true);
		ResultSet r=sentencia.executeQuery();
			//Esta consulta solo devuelve un maximo de una fila con lo cual no hace falta hacer el while,
		//Se puede hacer un if pero hay que estar muy seguros que solo devuelve una fila como en este caso.
			if(r.next()) {
				System.out.println("Numero de piezas: "+r.getLong(1)+
						"\tPrecio Maximo: "+r.getFloat(2)+
						"\tPrecio medio: "+r.getFloat(3));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void mostrarTiposRep() {
		// TODO Auto-generated method stub
		
		try {
			Statement sentencia=conexion.createStatement();
		ResultSet resultado=sentencia.executeQuery("select * from tiporep");
			while(resultado.next()) {
				tipoRep t=new tipoRep();
				t.setCodigo(resultado.getInt(1));
				t.setNombre(resultado.getString(2));
				
				t.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void mostrarCoches() {
		// TODO Auto-generated method stub
		
		try {
			Statement sentencia=conexion.createStatement();
		ResultSet resultado=sentencia.executeQuery("select * from coche join cliente on dni=cliente");
			while(resultado.next()) {
				Coche c=new Coche();
				c.setMatricula(resultado.getString(1));
				c.setMarca(resultado.getString(2));
				c.setModelo(resultado.getString(3));
				c.setCliente(new Cliente());
				c.getCliente().setDni(resultado.getString(4));
				c.getCliente().setNombre(resultado.getString(6));
				c.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected boolean crearReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		try {
			PreparedStatement sentencia=conexion.prepareStatement("insert into reparacion values (null,?,?,?)");
			/*Rellenamos los parametros.*/
			sentencia.setInt(1, r.getTipo().getCodigo());
			sentencia.setString(2, r.getMatricula().getMatricula());
			/*Esta fecha nosotros la tenemos importada de java util y en este caso tenemos que 
			 * usar el java.sql.Date. Para ello usamos la siguiente sentencia: */
			sentencia.setDate(3, new java.sql.Date(r.getFecha().getTime()));
			int numReg=sentencia.executeUpdate();
			
			if(numReg==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	
	
	
	
}
