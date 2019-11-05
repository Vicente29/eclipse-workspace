package GIMNASIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Modelo {

	private Connection conexion;
	private String url="jdbc:mysql://localhost:3306/gimnasio?serverTimezone=UTC";
	private String usuario="gimnasio";
	private String clave="gimnasio";
	private static Scanner leer=new Scanner(System.in);
	public Modelo() {
		//Hacemos la conexion. Cargamos el driver.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
	
	protected String comprobarDatos(String us, String cl) {
		// TODO Auto-generated method stub
		String resultado="";
		/*Devuelve el tipo del ususario si el usuario existe.*/
		try {
			PreparedStatement sentencia=conexion.prepareStatement("select tipo from usuarios where usuario=? and clave=sha2(?,224)");
			sentencia.setString(1, us);
			sentencia.setString(2, cl);
			ResultSet r=sentencia.executeQuery();
			if(r.next()) {
				resultado=r.getString(1);
			}else {
				System.err.println("Uuario no encontrado.");
			/*Si no se encuentra el usuario se devuelve NE= No Encontrado.*/	
				resultado="NE";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	protected void menuAdmin() {
		// TODO Auto-generated method stub
		
		int opcion=0;
		do {
			System.out.println("0.-Salir");
			System.out.println("1.-Gestionar clientes.");
			System.out.println("2.-Gestionar actividades.");
			System.out.println("3.-Generar recibos.");
			System.out.println("4.-Pagar recibos.");
			System.out.println("5.-Mostrar Recibos.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			case 1:
					gestionarClientes();
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
		
	}

	//METODOS DE ADMINISTRADOR  --------->   menuAdmin();
/*---------------------------------------------------------------------------------------------------*/
	private void gestionarClientes() {
		// TODO Auto-generated method stub
		
		int opcion=0;
		do {
			System.out.println("0.-Salir");
			System.out.println("1.-Mostrar clientes.");
			System.out.println("2.-Insertar clientes.");
			System.out.println("3.-Modificar clientes.");
			System.out.println("4.-Borrar clientes.");
			System.out.println();
			System.out.print("Introduce una de las opciones anteriores: ");
			opcion=leer.nextInt();leer.nextLine();
			System.out.println();
			switch(opcion) {
			case 1:
					mostrarCLientes();
				break;
			case 2:
					Cliente c=new Cliente();
					System.out.println("Introduce el nombre del USUARIO: ");
					c.setUsuario(new Usuario());
					c.getUsuario().setUsuario(leer.nextLine());
					if(!existeUs(c.getUsuario().getUsuario())) {
						System.out.println("Introduce el DNI: ");
						c.setDni(leer.nextLine());
						System.out.println("Introduce el nombre del cliente: ");
						c.setNombre(leer.nextLine());
						System.out.println("Introduce los apellidos: ");
						c.setApellido(leer.nextLine());
						System.out.println("Introduce el telefono:");
						c.setTelf_contacto(leer.nextLine());
						c.setBaja(true);
						c.getUsuario().setTipo("C");
						int id=insertarCliente(c);
						if(id==-1) {
							System.out.println("ERROR: No se ha podido crear el nuevo cliente.");
						}else {
							System.out.println("Cliente numero "+id+" insertado.");
						}
					}else {
						System.err.println("ERROR 404");
					}
					
				break;
			case 3:
					
				break;
			case 4:
					
				break;
			}
		}while(opcion!=0);
		
		
	}


	private int insertarCliente(Cliente c) {
		/*Devuelve -1 si va mal, y devuelve el id del cliente creado si la cosa va bien.*/
		//Hay que actualizar dos tablas con lo cual hay que usar las transacciones.
		int resultado=-1;
		try {
			conexion.setAutoCommit(false);
			PreparedStatement sentencia=conexion.prepareStatement("insert into usuarios values(?,sha2(?,224),?)");
			sentencia.setString(1, c.getUsuario().getUsuario());
			sentencia.setString(2, c.getUsuario().getUsuario());
			sentencia.setString(3, c.getUsuario().getTipo());
			//Como es un insert devuelve el numero de filas por eso usamos el int r.
			int r=sentencia.executeUpdate();
			if(r==1) {
				sentencia=conexion.prepareStatement("insert into cliente values(null,?,?,?,?,?,true)",Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, c.getUsuario().getUsuario());
				sentencia.setString(2, c.getDni());
				sentencia.setString(3, c.getApellido());
				sentencia.setString(4, c.getNombre());
				sentencia.setString(5, c.getTelf_contacto());
				sentencia.setBoolean(6, true);
				r=sentencia.executeUpdate();
				if(r==1) {
					conexion.commit();
					ResultSet ids=sentencia.getGeneratedKeys();
					if(ids.next()){
						resultado=ids.getInt(1);
					}
				}else {
					conexion.rollback();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	private boolean existeUs(String usuario) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			PreparedStatement sentencia=conexion.prepareStatement("select * from usuarios where usuario=?");
			sentencia.setString(1, usuario);
			ResultSet r= sentencia.executeQuery();
			if(r.next()) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}


	private void mostrarCLientes() {
		// TODO Auto-generated method stub
		try {
			Statement sentencia=conexion.createStatement();
			ResultSet r=sentencia.executeQuery("select * from cliente");
			while(r.next()) {
				GIMNASIO.Cliente c=new GIMNASIO.Cliente(r.getInt(1), 
						new Usuario(r.getString(2),null), 
						r.getString(3), 
						r.getString(4), 
						r.getString(5), 
						r.getString(6), 
						r.getBoolean(7));
				c.mostrar_Cliente();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
	
	//FIN DE LOS METODOS DEL ADMINISTRADOR.
/*---------------------------------------------------------------------------------------------------------*/	
	
	protected void menuClientes() {
		// TODO Auto-generated method stub
		
	}

	
}
