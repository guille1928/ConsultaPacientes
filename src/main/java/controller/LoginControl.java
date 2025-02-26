/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author guill
 */
public class LoginControl {
	
//vamos a crear un metodo para controlar los datos introducidos por el usuario 
		
public static boolean validarDatosUsuario (String email ,char [] password) {
	
	//necesito comparar el email y la contraseña con datos almacenados en la base de datos 
	Connection cone = Database.conectateSQLITE();
    if (cone == null) {
        System.out.println("Hubo un error en la conexión a la base de datos");
        return false;
    }
    
	try {
		
		 //creo la consulta para verificar que el email existe en la base de datos 
		 String sql = "select email,medico_password from personal_medico where email = ? ";
		 PreparedStatement pstm = cone.prepareStatement(sql);
		 pstm.setString(1, email);
		 ResultSet rs = pstm.executeQuery();
		 
		 if(rs.next()) {
			 String emailDBase = rs.getString("email");
			 System.out.println("El email está en la DB : " + email);
			 if(email.equals(emailDBase)){
				 System.out.println("El email coincide");
					 String constraseniaBaseDatos = rs.getString("medico_password");
					 	//comparo si las contraseñas son iguales 
					//creo el string de la contraseña hasheada con el metodo propio
					 String contraseniaStringHash = RegistroControl.hashPassword(password);
				 		if(contraseniaStringHash.equals(constraseniaBaseDatos)) {
				 		System.out.println("El usuario es válido");
				 		pstm.close();
				 		cone.close();
				 		
				 		return true;
				 		}else {
				 			//damos mensaje de error cuando la contraseña no coincida 
				 			JOptionPane.showMessageDialog(null, "La contraseña no coincide","Error", JOptionPane.ERROR_MESSAGE);
				 			return false;
				 			
				 			}
				 	
				 
			 	}
		 }else {
			 	//damos mensaje de error cuando el email no coincida 
		 		JOptionPane.showMessageDialog(null, "El email no se encuentra registrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
		 		System.out.println("El email no coincide");
		 	}
		
	}catch (SQLException e) {System.out.println("hubo un error" + e.getMessage());}
	
	//rescato email y contraseña de la base de datos 
	
	
	return false;
	



}
  

}
