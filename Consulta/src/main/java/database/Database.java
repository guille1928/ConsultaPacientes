package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//importo la clase para conectarme a la DB

public class Database {

//Clase creada para controlar la conexion a la DB

// private static final String url="localhost";
	private static final String url = "jdbc:mysql://localhost:3306/consulta_medica";

	private static final String nombreDB = "consulta_medica";
	private static final String usuario = "root";
	private static final String password = "1234";
	private static final int puerto = 3306;
//creo sus atributos privados 

//creo los constructores para introducir los datos 

	public static Connection conectateSQLITE () {
		Connection cone = null; 
		//String url = "jdbc:sqlite:db/consulta.db";
		try {
			cone = DriverManager.getConnection("jdbc:sqlite:db/consulta.db");
			
			
			} catch (SQLException e) {
				System.out.println("Hubo un error en la conexión con SQLite" + e.getMessage());
			
			}
		
		return cone;	
		}	
	
	
	
	
	

public static Connection conectate () {
	Connection cone = null; 
	
	try {
		cone = DriverManager.getConnection(url, usuario, password);
		
		System.out.println("conectado");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return cone;
	
	
}
	

public static Connection mostrardatosDB() {

		Connection conecto = null;

		try {
			// la variable nueva del objeto connection utilizamos drivermanager
			conecto = DriverManager.getConnection(url, usuario, password);
			/*
			 * para utilizar el drivermanager tengo que darle la url, usuario y password de
			 * la base de datos que queramos utilizar
			 */

			// creo el string para la consulta sql
			var sql = "select * from personal_medico;";
			// lo preparo para evitar inyecciones
			PreparedStatement ps = conecto.prepareStatement(sql);
			// lo recogo en un resultSet
			ResultSet rs = ps.executeQuery();
			ArrayList<String> arrayEmail = new ArrayList<>();

			System.out.printf("%-10s %-20s %-15s%n", "ID", "Email", "Contraseña");
			System.out.println("---------------------------------------------");
			while (rs.next()) {
				// cuando el ResultSet tenga valores los itera
				String email = rs.getString("email");
				String id = rs.getString("id");
				String pass = rs.getString("medico_password");
				arrayEmail.add(email);
				arrayEmail.add(id);
				arrayEmail.add(pass);

				System.out.printf("%-10s %-20s %-15s%n", id, email, pass);

			}

			for (int i = 0; i < arrayEmail.size(); i++) {
				
			}

			rs.close();
			ps.close();
			conecto.close();
		} catch (SQLException e) {
			System.out.println("Hubo un error en la conexion : " + e);
		}

		return conecto;

	}


public static Connection conectDB () {
Connection conecta = null;

try {

conecta= DriverManager.getConnection(url, usuario, password);




System.out.println("La conexión fue correcta");
}catch (SQLException e) {
	System.out.println("Hubo un error en la conexión" + e);
}


	
return conecta;	

}



//alguna consulta para la base de datos 
public static boolean modificarBasedeDatos () {
	Connection cone = conectateSQLITE();
	try {
		
		String sql = "ALTER TABLE pacientes ADD COLUMN descripcion TEXT";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.executeUpdate();
		return true;
	} catch (Exception e) {
		System.out.println(e.getMessage());
		// TODO: handle exception
	}
	
	return false;
}
public static void main(String[] args) {
	
    
}
}
