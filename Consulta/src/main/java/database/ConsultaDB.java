package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConsultaDB {

	public static void main(String[] args) {
		String url ="jdbc:sqlite:db/consulta.db";
		try {
			
		//tengo que crear la conexion para la DB
		Connection cone = DriverManager.getConnection(url);
		//creo todas las tablas e inserciones 
		
		String sqlFacturas = "CREATE TABLE facturas (\r\n"
				+ "  id_factura INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  pago_total DECIMAL(10,2) NOT NULL,\r\n"
				+ "  fecha_pago TEXT,\r\n"
				+ "  dni_pacientes TEXT NOT NULL\r\n"
				+ ");";
		
		
		String sqlPacientes = "CREATE TABLE pacientes (\r\n"
				+ "  dni TEXT PRIMARY KEY,\r\n"
				+ "  nombre TEXT NOT NULL,\r\n"
				+ "  apellidos TEXT NOT NULL\r\n"
				+ ");";
		
		String sqlPersonalMedico = "CREATE TABLE personal_medico (\r\n"
				+ "  id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  email TEXT NOT NULL,\r\n"
				+ "  medico_password TEXT NOT NULL\r\n"
				+ ");";
		String sqlSesiones = "CREATE TABLE sesiones (\r\n"
				+ "  idsesiones INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  tipo TEXT NOT NULL,\r\n"
				+ "  dni_pacientes TEXT NOT NULL\r\n"
				+ ");";
		
		String sqlInsertFacturas = "INSERT INTO facturas VALUES \r\n"
				+ "(14,250.00,'2022-01-05','71650206E'),\r\n"
				+ "(15,35.00,'2022-01-05','71650244E'),\r\n"
				+ "(17,25.00,'2022-01-05','72617288P'),\r\n"
				+ "(18,2500.00,'2022-01-05','71240206E'),\r\n"
				+ "(19,5000.00,'2022-01-05','0123456A'),\r\n"
				+ "(20,3333.00,'2022-01-05','0123456A');";
		
		String sqlInsertPacientes = "INSERT INTO pacientes VALUES \r\n"
				+ "('0123456A','Prueba ','Prueba'),\r\n"
				+ "('71240206E','Sara','Suarez Fdez'),\r\n"
				+ "('71650206E','Manuel','Fdez Gomez'),\r\n"
				+ "('71650244E','Ismael','Del Mar Fdez'),\r\n"
				+ "('71650246F','Antonio','Garcia Gomez'),\r\n"
				+ "('72617288P','Claudia','Martinez Vazques');";
		
		String sqlInsertSesiones = "INSERT INTO sesiones VALUES \r\n"
				+ "(2,'2 sesiones','71240206E'),\r\n"
				+ "(3,'3 consultas','72617288P');";
		
		String sqlSelectPacientes = "select * from pacientes";
		String sqlSelectFacturas = "select * from facturas";
		//Consulta relacional 
		String sqlSelectPaFacturas = "select * from pacientes inner join facturas where facturas.dni_pacientes = pacientes.dni ";
		String sqlSelectPersonalM = "select * from personal_medico";
		
		//creamos los 2 statements para cada resultSet y hacer las consultas 
		Statement stm = cone.createStatement();
		Statement stm1 = cone.createStatement();
		Statement stm2 = cone.createStatement();
		
		//creo los 2 result set 
		ResultSet rs =  stm.executeQuery(sqlSelectPacientes);
		ResultSet rs1 =  stm1.executeQuery(sqlSelectFacturas);
		ResultSet rs3 = stm2.executeQuery(sqlSelectPersonalM);
		
		
        
		System.out.println("Tabla Pacientes:");
        while (rs.next()) {
            System.out.println("DNI: " + rs.getString("dni") +
                    " Nombre y Apellidos: " + rs.getString("nombre") +
                    " " + rs.getString("apellidos"));
        }

        // Procesar resultados de facturas
        System.out.println("\nTabla Facturas:");
        while (rs1.next()) {
            System.out.println("ID Factura: " + rs1.getInt("id_factura") +
                    " Pago Total: " + rs1.getDouble("pago_total") +
                    " Fecha: " + rs1.getString("fecha_pago") +
                    " DNI Paciente: " + rs1.getString("dni_pacientes"));
        }
		System.out.println("\n");
        System.out.println("Nueva consulta " + "\n");
        
        ResultSet rs2 = stm.executeQuery(sqlSelectPaFacturas);
        while (rs2.next() ) {
        	System.out.println("==============================================");
        	System.out.print("FACTURA | ");
        	System.out.print("ID Factura: " + rs2.getInt("id_factura") + " | ");
        	System.out.print("Pago Total: " + rs2.getDouble("pago_total") + " | ");
        	System.out.println("Fecha de Pago: " + rs2.getString("fecha_pago"));
        	System.out.print("PACIENTE | ");
        	System.out.print("DNI: " + rs2.getString("dni") + " | ");
        	System.out.println("Nombre y Apellidos: " + rs2.getString("nombre") + " " + rs2.getString("apellidos"));
        	System.out.println("==============================================");

        }
        
        
        while (rs3.next()) {
        	System.out.println("Datos Medicos : \n ");
        	System.out.println("los datos son " + rs3.getInt("id") + "\n " + rs3.getString("email") + "\n" + rs3.getString("medico_password"));
        	
        }
		rs.close();
        rs1.close();
        stm.close();
        stm1.close();
		
		}catch (SQLException e) {
			System.out.println("Hubo un error : " + e.getMessage() );
		}
		
		
		
	}
	
	
	
	
	
}
