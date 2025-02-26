package controller;

public class PruebasPadre2 {

	private static String nombre = "Manuel";
	
	
	public String saberNombre (){
	return nombre;	
		
		
	}
	
	public void modificarNombre (String nom) {
		
	nombre= nom;
		
		
		
	}
	
	
	public static void main (String[] args) {
	
	pruebas hija = new pruebas();
	String nombreRef = hija.mostrarNombre(nombre);
	System.out.println("El nombre es : "+ nombreRef);
	
	String nuevoNombre = "nuevo";
	
	hija.modificarNombre(nuevoNombre);
	String nuevoNombre2 = hija.mostrarNombre(nombre);
	System.out.println("El nombre es : "+ nuevoNombre2);
	
	}
	
	
}
