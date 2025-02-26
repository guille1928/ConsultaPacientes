/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.Database;

/**
 *
 * @author guill
 */
public class SesionesControl {
    //creo la funcion para mostrar las sesiones al usuario
	
	public static List<Object[]> mostrarSesiones (String dni) {
		List<Object[]> listaSesiones = new ArrayList<Object[]>();
		Connection cone = Database.conectateSQLITE();
		
		try {
		//realizo la consulta recojo los datos de sesiones en un ResultSet
		String sql = "select * from sesiones where dni_pacientes = ? ";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.setString(1, dni);
		ResultSet rs = pStm.executeQuery();
		//ejecuto el ResultSet y lo añado al list con un un objeto 
		while (rs.next()) {
			Object [] sesion = {
				rs.getString("idsesiones"),
				rs.getString("dni_pacientes"),
				rs.getString("tipo"),
								};
			//añado a la lista la sesion
			listaSesiones.add(sesion);
			}
		pStm.close();
 		cone.close();
		}catch (SQLException e ){System.out.println("Hubo un error para mostrar las sesiones : " + e.getMessage());		}
		//retorno las sesiones
		return listaSesiones;
	
	}


	public static boolean guardarSesion (String tipo, String dni) {
		boolean resultado = false;
		Connection cone = Database.conectateSQLITE();
		try {
		String sql = "INSERT INTO sesiones(tipo, dni_pacientes) VALUES (?, ?)";
		PreparedStatement pStm = cone.prepareStatement(sql);
		pStm.setString(1, tipo);
		pStm.setString(2, dni);
		
		int dato = pStm.executeUpdate();
		if (dato >0) {
			resultado = true;
			pStm.close();
	 		cone.close();
		}else {System.out.println("No se encontro el registro con dni_pacientes");}
	
		}catch (SQLException e ) {System.out.println("hubo un error al guardar la sesión : "+e.getMessage());}
		
		
 		
		
		
	return resultado;	
	}


	public static boolean borrarSesion (String dni, int id) {
	Connection cone = Database.conectateSQLITE();
	
	try {
	String sql = "DELETE FROM sesiones WHERE idsesiones = ? and dni_pacientes = ?";
	PreparedStatement sTm = cone.prepareStatement(sql);
	sTm.setInt(1, id);
	sTm.setString(2, dni);
	sTm.executeUpdate();
	
	sTm.close();
	cone.close();
	return true;
		
		
		
		
	}catch (SQLException e) {System.out.println("Hubo un error al borrar la sesion : "+e.getMessage());}
		
		
		return false;
	}


	
	
	public static boolean modificarSesion (String dni, String tipo, int id) {
		Connection cone = Database.conectateSQLITE();
		
		try {
		String sql = "UPDATE sesiones SET tipo= ? WHERE dni_pacientes = ? AND idsesiones = ? ";	
		
		PreparedStatement pSt = cone.prepareStatement(sql);
		pSt.setString(1, tipo);
		pSt.setString(2,dni);
		pSt.setInt(3, id);
		
		pSt.executeUpdate();
		
		pSt.close();
 		cone.close();
		return true;
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
}
