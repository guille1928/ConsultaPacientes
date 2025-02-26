/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.xdevapi.PreparableStatement;

import database.*;

/**
 *
 * @author guill
 */
public class RegistroControl {
    //vamos a crear una función para controlar el registro de los medicos en la base de datos 
	
	//creo un metodo para hashear la contraseña
	public static String hashPassword (char [] password) {
		try {
			
		//convertimos el char[] a String
		String passwordStr =  new String(password);
		
		//obtengo la instancia de MessaDigest
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		//obtengo el hash de la contraseña
		byte[] hashBytes = digest.digest(passwordStr.getBytes());
		
		//creo una instancia de StringBuilder para crear el hexadecimal 
		StringBuilder hexString = new StringBuilder();
		 for (byte b : hashBytes) {
	            hexString.append(String.format("%02x", b));
	        	}
	      
		return hexString.toString();
		
	
		}catch (NoSuchAlgorithmException e) {throw new RuntimeException("Hubo un error en crear el hash de la contraseña" , e);}
		
		
	}
	
	//le paso los valores al metodo 
	public static  boolean registroMedico (String email, char [] contrasenia) {
		Connection cone = Database.conectateSQLITE();
		//me conecto a la base de datos 
	if(cone == null) {
			System.out.println("hubo un error en la conexión a la base de datos");
			return false;
	}
		try {
			
		//hasheamos la contraseña para evitar vulnerabilidades de seguridad
		String contraseniaStringHash = hashPassword(contrasenia);
		
		//se procede a preparar la consulta para evitar inyecciones sql 
		String sql = "insert into personal_medico (email,medico_password) values (?,?)";
		//preparo la consulta 
		PreparedStatement pstm = cone.prepareStatement(sql);
		//creo las variables para recibir los valores del usuario por el formulario 
		
		
		//asigno los valores al primer valor ? 
		pstm.setString(1, email);
		pstm.setString(2, contraseniaStringHash);
		//ejecuto el update
		pstm.executeUpdate();
		//realizo la consulta 
		
		///cerramos el PStatement
		pstm.close();
		
		System.out.println("Se introdujeron los datos correctamente");
		return true;
			
		}catch (SQLException e) {System.out.println("Hubo un erro en el registro : " + e.getMessage());
			return false;}
	finally {
				try {	
						if(cone != null) {
							cone.close();
							//cerramos la conexión
							
							}
					}catch (SQLException e) {System.out.println("Hubo un error al cerrar la conexión"+e.getMessage());}
				
				
		
			}
	}
	
	//creo metodo para realizar un query en la DB
	public static Connection leerDatosMedico () {
try {
	Connection cone = Database.conectateSQLITE();
	
	String sql1 = "select * from personal_medico";
	PreparedStatement pstm1 = cone.prepareStatement(sql1);
	//preparo la conexión
	ResultSet rs = pstm1.executeQuery();
	//lo recojo en un resultSet que leo después iterando con un while 
	while (rs.next()) {
			System.out.println("Email : "+ rs.getString("email") + "\n Contraseña : " + rs.getString("medico_password"));
			
			}
	
	pstm1.close();
	cone.close();

	}catch (SQLException e) {System.out.println("No se pueden leer los datos del médico "+ e.getMessage());}

return null;

}
	
	
	
	//creo esta funcion para registrar a un admin
	public static boolean registrarAdmin (String user, char [] password) {
		Connection cone = Database.conectateSQLITE();
		try {
		
		//Pasamos la pass a String
		String password1 = RegistroControl.hashPassword(password);
		//creo la consula y la ejecuto bindeada
		String sql = "INSERT INTO admin_consulta values (?,?)";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.setString(1,user );
		pStm.setString(2, password1);
		int exito = pStm.executeUpdate();
		if(exito>0) {
			System.out.println("Se guardo correctamente el admin");
			pStm.close();
	 		cone.close();
			return true;
		}
		
		}catch (Exception e) { System.out.println("Hubo un error al registrarte como admin");}
		
		return false;
	}

	
	//creo esta funcion para que el admin se autentifique
	public static boolean autentificarAdmin (String user,char [] password) {
		Connection cone = Database.conectateSQLITE();
		try {
		String password1 = RegistroControl.hashPassword(password);
		String sql ="SELECT user, password FROM admin_consulta";
		PreparedStatement pSt = cone.prepareStatement(sql);
		ResultSet rs = pSt.executeQuery();
		while (rs.next()) {
				String userDB = rs.getString("user");
				String passDB = rs.getString("password");
				
				if(user.equals(userDB) || password1.equals(passDB)) {
					System.out.println("Se validó correctamente el admin");
					pSt.close();
			 		cone.close();
					return true;		
				}
			
		}
		
			
		}catch (Exception e) { System.out.println("Hubo un error al registrarte como admin");}
		
		return false;
	}
	//funcion para comprobar que en el registro introduzca solo el formato válido
	public static boolean comprobarEmail (String email) {
		 String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		boolean exito =matcher.matches();
		
		return exito;
				
		
	}
	
	
	
}