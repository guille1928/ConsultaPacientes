/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import org.jdatepicker.JDatePicker;

import database.*;

/**
 *
 * @author guill
 */
public class PacientesControl {
	
	
	//creamos los metodos para introducir en la DB los datos de los pacientes. 
	
	
	
	public static boolean guardarPacientesDB (String dni,String nombre, String apellidos) {
		
	//nos conectamos a la DB
	Connection cone = Database.conectateSQLITE();
	try {
	//tengo que preparar los Stament para evitar inyecciones en la DB
	String sql = "INSERT INTO pacientes(dni,nombre,apellidos) values (?,?,?)";
	PreparedStatement pstm = cone.prepareStatement(sql);
	pstm.setString(1, dni);
	pstm.setString(2, nombre);
	pstm.setString(3, apellidos);
	pstm.executeUpdate();
	//ejecuto el update
	
	//cierro las conexiones 
	cone.close();
	pstm.close();
	
	return true;
	
	}catch (SQLException e) {System.out.println("Hubo un error al introducir los datos del paciente");}
			
	return false;
	}
	
	
	
	public static List<String> saberDniPacientesDB () {
	
	List<String> dniLista = new ArrayList<>();
	//creo la conexion a la DB 
	Connection cone = Database.conectateSQLITE();
	
	try {
		//preparamos la consulta y generamos el select de los dnis de la DB
		String sql = "select dni from pacientes";
		PreparedStatement pStm = cone.prepareStatement(sql);
		ResultSet rs = pStm.executeQuery();
		//mostrarlo en consola
		
		
		//cuando tenga valores los añado al list creado
		while (rs.next()) {
			dniLista.add(rs.getString("dni"));
			
			
		}
		pStm.close();
 		cone.close();
		
	}catch (SQLException e) {
		System.out.println("No se pudieron recuperar los dni de los pacientes " + e.getMessage());
			}
		
		
	return dniLista;
	}
 
	
	public static List<Object[]> listaCompletaPacientes () {
	Connection cone = Database.conectateSQLITE();
	//me conecto a la DB
	List<Object[]> listaPacientes = new ArrayList <>();
	//creo un array para guardar la lista de pacientes
	try {
		String sql = "select dni,nombre,apellidos from pacientes";
		PreparedStatement pStm = cone.prepareStatement(sql);
		ResultSet rs =  pStm.executeQuery();
		//ejecuto la consulta preparada
		
		//cuando el ResultSet tenga datos los añado a la List
	while (rs.next()) {
		//defino un objeto [] para utilizar filas y hacer una tabla en el Jpanel 
		Object[] paciente = {
		//añado cada campo al List 
		rs.getString("dni"),
		rs.getString("nombre"),
		rs.getString("apellidos"),
				
		};
		listaPacientes.add(paciente);
		}
	cone.close();
	pStm.close();
}catch (SQLException e) {System.out.println("Hubo un error : " + e.getMessage());}
	 

return listaPacientes;

}


	

	public static boolean borrarPaciente (String dni) {
		Connection cone = Database.conectateSQLITE();
		
		try {
		String sql = "DELETE FROM pacientes where dni = ? ";
		PreparedStatement pSt = cone.prepareStatement(sql);
		pSt.setString(1, dni);
		pSt.executeUpdate();
		
		pSt.close();
 		cone.close();
		
		return true;
			
		}catch(SQLException e) {System.out.println("Hubo un error la borrar el paciente : " + e.getMessage());}
				
		return false;
	}


	
	public static List <Object[]> verDatosPaciente (String dni) {
		Connection cone = Database.conectateSQLITE();
		List<Object[]> listaDatosPaciente = new ArrayList<Object[]>();
		//sacamos los datos de cada paciente de la DB
		try {
			
			String sql = "SELECT * FROM pacientes where dni = ?";
			PreparedStatement pSt = cone.prepareStatement(sql);
			pSt.setString(1, dni);
			ResultSet rs = pSt.executeQuery();
			while (rs.next()) {
			Object[] lista= {
				rs.getString("dni"),
				rs.getString("nombre"),
				rs.getString("apellidos"),
				rs.getString("descripcion"),
				};
			
			listaDatosPaciente.add(lista);
			}
			
			cone.close();
			pSt.close();	
		} catch (Exception e) {
			 System.out.println("Error al obtener los datos del paciente: " + e.getMessage());
			// TODO: handle exception
		}
		
		return listaDatosPaciente;
	}
	
	
	public static boolean guardarInfoPaciente (String descripcion, String dni )  {
		Connection cone = Database.conectateSQLITE();
		//nos conectamos a la base de datos
		
		try {
		//en un bloque try catch , creamos la consulta de update para la DB
		String sql = "UPDATE pacientes SET descripcion = ? where dni = ? ";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.setString(1, descripcion);
		pStm.setString(2,dni);
		pStm.execute();
		cone.close();
		return true;
			
		
		} 
		
		catch (SQLException e) {
			System.out.println("Hubo un error al introducir la descripcion en la base de datos : " +e.getMessage());
			
		}
		

		return false;
	}
	
	
	
	
	
}
