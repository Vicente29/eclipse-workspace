package Explicacion_MySql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				 System.out.println(bds.getString(1));
			 }
			 System.out.println();
			 System.out.println("Tablas de la BD: ");
			 ResultSet tablas=metadatos.getTables("taller", null, null, null);
			 while(tablas.next()) {
				 System.out.println(tablas.getString(3));
			 }
			 System.out.println();
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
}
