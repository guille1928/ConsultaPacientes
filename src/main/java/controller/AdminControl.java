package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class AdminControl {
	
	public static boolean crearTabla () {
		try {
			Connection cone = Database.conectateSQLITE();
			
			String sql = "CREATE TABLE IF NOT EXISTS admin_consulta ("
	                   + "id_admin INTEGER PRIMARY KEY AUTOINCREMENT, "
	                   + "user TEXT NOT NULL, "
	                   + "password TEXT NOT NULL);";
			
			PreparedStatement pstm1 = cone.prepareStatement(sql);
			
			pstm1.executeUpdate();
			
			

			}catch (SQLException e) {System.out.println("No se pueden leer los datos del médico "+ e.getMessage());}
		
		
		return true;
	}
	
	public static boolean insertaAdmin () {
		//prueba basica para crear un admin, no considero medidas de seguridad. 
		try {
			Connection cone = Database.conectateSQLITE();
			String nombre = "admin";
			char[] pass = "admin".toCharArray();
			String has = RegistroControl.hashPassword(pass);
			String sql = "INSERT INTO admin_consulta (user,password) values (?,?)";
			PreparedStatement pstm1 = cone.prepareStatement(sql);
			pstm1.setString(1, nombre);
			pstm1.setString(2, has);
			pstm1.executeUpdate();
			
			pstm1.close();
	 		cone.close();

			}catch (SQLException e) {System.out.println("No se pueden leer los datos del médico "+ e.getMessage());}
		
		
		return true;
	}
	

	
	public static Connection leerDatosAdmin () {
		try {
			Connection cone = Database.conectateSQLITE();
			
			String sql1 = "select * from admin_consulta";
			PreparedStatement pstm1 = cone.prepareStatement(sql1);
			//preparo la conexión
			ResultSet rs = pstm1.executeQuery();
			//lo recojo en un resultSet que leo después iterando con un while 
			while (rs.next()) {
					System.out.println("Email : "+ rs.getString("user") + "\n Contraseña : " + rs.getString("password"));
					
					}
			pstm1.close();
	 		cone.close();
			}catch (SQLException e) {System.out.println("No se pueden leer los datos del médico "+ e.getMessage());}

		return null;

		}
	
	public static boolean borrarAdmin () {
		//para borrar el admin 
		try {
			Connection cone = Database.conectateSQLITE();
			
			String sql = "DELETE FROM admin_consulta WHERE user='admin' ";
			PreparedStatement pstm1 = cone.prepareStatement(sql);
			
			pstm1.executeUpdate();
			pstm1.close();
	 		cone.close();
			

			}catch (SQLException e) {System.out.println("No se pueden borrar los datos del médico "+ e.getMessage());}
		
		
		return true;
	}
	
	
	
	public static List<Object[]> listaMedicos () {
		Connection cone = Database.conectateSQLITE();
		List<Object[]> listMedico = new ArrayList<Object[]>();
		
		try {
		String sql = "SELECT * FROM personal_medico";
		
		PreparedStatement sTm = cone.prepareStatement(sql);
		ResultSet rs = sTm.executeQuery();
		while (rs.next()) {
				Object[] nuevo ={
					rs.getInt("id"),
					rs.getString("email"),
					rs.getString("medico_password")
			
					};
				listMedico.add(nuevo);
			
			
		}
		sTm.close();
 		cone.close();	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return listMedico;
	}
	
	
	public static boolean eliminarMedico (int id) {
	Connection cone = Database.conectateSQLITE();
	
	try {
		
		String sql = "DELETE FROM personal_medico where id = ?";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.setInt(1, id);
		int exito = pStm.executeUpdate();
		if(exito >0) {
			return true;
		}
		
		pStm.close();
 		cone.close();	
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	
		return false;
	}
	
	
}
