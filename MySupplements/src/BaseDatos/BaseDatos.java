package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clases.Cliente;
import Clases.Pedido;

public class BaseDatos {
	private static Connection con;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	public static void initBD(String nombreBD) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
			logger.log(Level.INFO, "Conexión establecida con jdbc:sqlite:"+nombreBD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static TreeMap<String, Double> mapaClientes(Connection con){
		String sent = "SELECT * FROM Cliente";
		Statement stmnt = null;
		TreeMap <String, Cliente> mapaClientes = new TreeMap<>();
		try {
			stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sent);
			while(rs.next()) {
				String d = rs.getString("dni");
				String n = rs.getString("nom");
				String co = rs.getString("con");
				Date fnac = rs.getDate("fechanac");
				Cliente c = new Cliente(d, n, co, fnac);
				mapaClientes.put(d, c);
			}
			rs.close();
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
		return mapaClientes;
	}*/

	public static void closeBD() {
		if(con!=null) {
			try {
				con.close();
				logger.log(Level.INFO, "Conexión cerrada");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas() {
		String sent1 = "CREATE TABLE IF NOT EXISTS Cliente(nom String, con String, dni String,fnac String)";
		Statement st = null;
	//	String sent2 = "CREATE TABLE IF NOT EXISTS Pedido(";
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

	public static void insertarCliente(String nom, String c,String d, String ed) {//nombre,contraseña,dni y fecha de nacimiento
		
		String sent = "INSERT INTO Cliente VALUES('"+nom+"','"+c+"','"+d+"','"+ed+"')";
		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método que comprueba si un Cliente está registrado en la BBDD
	 * @param nom Nombre que ha insertado el Cliente en el jTexfield
	 * @param con Contraseña que ha insertado el Cliente en el jTexfield
	 * @return 0 si el Cliente no existe
	 * 		   1 si el Cliente sí existe pero su contraseña no es correcta
	 * 	       2 si el Cliente y la contraseña son correctos
	 */
	public static int existeCliente(String nom, String c) {
		String sent = "SELECT * FROM Cliente WHERE nom='"+nom+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) {
				String contra = rs.getString("con");
				if(contra.equals(c)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resul;
	}
/*	public static TreeSet<Producto> obtenerConjuntoProductos(){
		String s = "SELECT * FROM Producto";
		Statement stmnt = null;
		TreeSet<Producto>conjuntoProductos = new TreeSet<>();
		try {
			stmnt = con.createStatement();
			ResulSet rs = stmnt.executeQuery(s);
			while(rs.next()) {
				String cod = rs.getString("cod");
				float precio = rs.getFloat("precio");
				String nombre = rs.getString("nombre")
				Producto p = new Producto(cod, precio, nombre);
			}
			rs.close();
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmnt!=null) {
				try {
					st.close();
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return conjuntoProductos;
	}*/
	
	public static Cliente ObtenerCliente(String nom) throws SQLException{
		
			Statement statement = con.createStatement();
			String sent = "SELECT * FROM Cliente WHERE nom='"+nom+"'";
			ResultSet rs = statement.executeQuery(sent);
			Cliente c = null;
			if(rs.next()) {
				String con = rs.getString("con");
				String dni = rs.getString("dni");
				Date fecha = rs.getDate("fechanac");
				 c = new Cliente(nom, con, dni, fecha);
			}
			rs.close();
			logger.log(Level.INFO, "Cliente obtenido");
			return c;
	}}
	/*public static void eliminarCliente(String dni) throws SQLException {
		Statement stmnt = con.createStatement();
		String s = "DELETE FROM CLIENTE WHERE ID = " + dni;
		stmnt.executeUpdate(s);
		logger.log(Level.INFO, "EL cliente ha sido eliminado de la base de datos");
	}}*/
	
	/*public static void guardarPedido(Pedido p) {
		String s = "INSERT INTO PEDIDO VALUES('"+c.getCod()+"','"+c.getFec()+"', '"+c.getCliente()+"' , '"c.getPedido"')";
		Statement stmnt = null;
		
		try {
			stmnt = con.createStatement();
			stmnt.executeUpdate(s);
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}*/
	
/*	public static void borrarPedido( Pedido pedido) throws SQLException {
		Statement stmnt = con.createStatement();
		String s = "DELETE FROM PEDIDO WHERE cod = " + pedido.getCod() + ";";
		logger.log(Level.INFO, "Statement: " + s);
		int eliminados = stmnt.executeUpdate(s);
		if(eliminados == 0) throw new SQLException("Ningun pedido ha sido eliminado cuyo id = " + pedido.getCod());
		stmnt.close();
		
	}
	
}*/
