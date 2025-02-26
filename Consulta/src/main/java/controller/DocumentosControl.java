package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controller.*;

public class DocumentosControl {
//tratamos de la clase para generar archivos de informes para el usuario 
	
	

//creo una funcion para generar un informe en archivo pdf con Itext
	
	
public static boolean generaInforme (List <Object[]> datos, String nombre) {
//recojo el texto y el nombre del documento que nos manda el usuario 
String nombreDocumento = nombre;
if(!nombreDocumento.toLowerCase().endsWith(".pdf")) {
	nombreDocumento+=".pdf";

Document documento = new Document();

//itero sobre los datos para leerlos 

//llamo a pdfWriter

try {
	
    PdfWriter.getInstance(documento, new FileOutputStream(nombreDocumento));
    documento.open();
	
	for (Object[] fila : datos) {
		StringBuilder linea = new StringBuilder();
		for (Object campo :fila) {
			linea.append(campo.toString()).append(" ");
			
		}
		documento.add(new Paragraph(linea.toString().trim()));
			
	}
	return true;
} catch (Exception e) {
	System.out.println("error : "+ e.getMessage());

}finally {
	//cerramos el documento
	documento.close();}

}

//llamaremos al Itext para crear el archivo 

return false;	

}
	

//pruebas en main para generar archivos 

public static void main (String[] args) {

	//vamos a crear un documento de prueba para realizar pruebas
	
	Document documento = new Document();
	
	
	String texto = "prueba escritura en pdf con itext";
	
	try {
		
		PdfWriter.getInstance(documento, new FileOutputStream("text.pdf"));
		documento.open();
		documento.add(new Paragraph(texto));
		
		
	} catch (FileNotFoundException | DocumentException e ) {
		System.out.println(e.getMessage()); 
			
		}
		finally {documento.close();}
		// cerramos el documento 
	}
	
		
}
	
	

