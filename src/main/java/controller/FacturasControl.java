package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class FacturasControl {
	
	//esta clase gestiona la funcion para introducir datos en las facturas de cada paciente 
	public static List<Object[]> facturaPaciente (String dni) {
	Connection cone = database.Database.conectateSQLITE();
	List<Object[]> listaFacturas = new ArrayList<>();
	try {
	String sql = "select * FROM facturas WHERE dni_pacientes = ?";
	PreparedStatement pStm = cone.prepareStatement(sql);
	pStm.setString(1, dni);
	ResultSet rs = pStm.executeQuery();
	//cuando el resultSet tenga datos lo añadimos al array
	while (rs.next()){	
		Object[] facturas = {
			rs.getString("id_factura"),
			rs.getString("dni_pacientes"),
			rs.getString("fecha_pago"),
			rs.getString("pago_total")
			
		};	
		listaFacturas.add(facturas);
		//añado el objeto  al array 
	}
	pStm.close();
	cone.close();
	}catch (SQLException e ) {System.out.println("Hubo un error al insertar la factura"+e.getMessage());}
	//retorno el array 
	return listaFacturas;
		
	}
	
	public static boolean guardarFactura (String pago_total, java.util.Date fecha_pago, String dni_pacientes) {
		
		Connection cone = Database.conectateSQLITE();
		boolean resultado = false;
		try {
			
		String sql = "insert into facturas (pago_total, fecha_pago, dni_pacientes) values (?,?,?)";
		PreparedStatement pStm = cone.prepareStatement(sql);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_formateada = sdf.format(fecha_pago);
		

		pStm.setString(1, pago_total);
		pStm.setString(2, fecha_formateada); 
		pStm.setString(3, dni_pacientes);
		int rowsHechas = pStm.executeUpdate();
		
		if (rowsHechas > 0) {
			resultado = true;
		}
		
		pStm.close();
 		cone.close();
			
		}catch (SQLException e ) {System.out.println("Hubo un error al guardar la factura : " + e.getMessage());}
			
		
		return resultado;	
			
		}
	
	public static boolean borrarFactura (String dni,int id) {
		Connection cone = Database.conectateSQLITE();
	
		try {
		String sql = "DELETE FROM facturas WHERE dni_pacientes = ? and id_factura = ?";
		PreparedStatement pSt = cone.prepareStatement(sql);
		pSt.setString(1, dni);
		pSt.setInt(2, id);
		pSt.executeUpdate();
		
		pSt.close();
 		cone.close();
		
		return true;
		
			
		}catch(SQLException e) {System.out.println("Hubo un error al borrar la factura : " + e.getMessage());}
		
		return false;
	}


	public static boolean modificarFactura (String dni, int id, String pagoTotal) {
		Connection cone = Database.conectateSQLITE();
		//preparo la conexion
		try {
			//creo la consulta para el update de las facturas
		String sql = "UPDATE facturas SET pago_total = ? WHERE dni_pacientes = ? AND id_factura = ?";
		PreparedStatement pSt = cone.prepareStatement(sql);
	
		pSt.setString(1, pagoTotal);
		pSt.setString(2, dni);
		pSt.setInt(3, id);
		pSt.executeUpdate();
		//ejecuto la actualizacion 
		
		pSt.close();
 		cone.close();
		
		return true;
		} catch (SQLException e) {
			System.out.println("Hubo un error al actualizar la factura" + e.getMessage());
			// TODO: handle exception
		}
		
		
		return false;
	};

}
