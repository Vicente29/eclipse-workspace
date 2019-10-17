package Explicacion_MySql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {

	private Connection conexion=null;
	private String url="jdbc:mysql://localhost:3306/taller";
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

	
	
	
	
}
