package GIMNASIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {

	private Connection conexion;
	private String url="jdbc:mysql://localhost:3306/gimnasio?serverTimezone=UTC";
	private String usuario="gimnasio";
	private String clave="gimnasio";
	
	public Modelo() {
		//Hacemos la conexion. Cargamos el driver.
		try {
			Class.forName("com.myswl.jdbc.Driver");
			conexion=DriverManager.getConnection(url,usuario,clave);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected Connection getConexion() {
		return conexion;
	}


	protected void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	
	
	//METODOS DE ESTA CLASE.
/*------------------------------------------------------------------------------------------------------*/	
	
	protected Object comprobarDatos(String us, String cl) {
		// TODO Auto-generated method stub
		String resultado="";
		
		try {
			PreparedStatement sentencia=conexion.prepareStatement("select tipo from usuarios where usuario=? and clave=sha2(?,224)");
			sentencia.setString(1, us);
			sentencia.setString(2, cl);
			ResultSet r=sentencia.executeQuery();
			if(r.next()) {
				
			}else {
				System.err.println("Uuario no encontrado.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	
	
	
	
}
