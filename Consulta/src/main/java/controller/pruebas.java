package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;



public class pruebas {
	private String apellidos;
	private String nombreDesdeHija;
	
	PruebasPadre2 clasePadre = new PruebasPadre2();
	
	public String apellidos () {
		
		
		
	return apellidos;	
	}
	
	
	public String mostrarNombre (String nombre) {
		
		this.nombreDesdeHija = nombre;
		return nombreDesdeHija;
	}
	
	
	public void modificarNombre (String nombre) {
		clasePadre.modificarNombre(nombre);
		
	}
	
	
	
	
	//funcion de prueba para crear un list
	
	public static List<Object[]> pruebaLista (){
		List <Object[]> lista = new ArrayList<Object[]>();
		
		Object[] saludo = {"Hola","Adios","Buenas tardes"};
		Object[] numeros= {1,2,3,4,5,6,7};
		lista.add(saludo);
		lista.add(numeros);
	

		return lista;
		
	}
	
	 
	
	public static int[] ordena (int[] num) {
		
		Arrays.sort(num);
				
		return num;
	}
	


	public static void main (String[] args) {
		
		//vamos a crear un nuevo array para probar recorrer un array
		
		String [] peliculas = {"El padrino","Batman","Gladiator","Dune"};
		
		
		for (int i= 0; i<peliculas.length; i++) {
			
			System.out.println("Película número : " + i +" "+  peliculas[i]);
			
		}
		
		
		//segunda lectura sin indice para leer el array con un foreach 
		
		for (String pelis : peliculas) {
			System.out.println(" ");
			System.out.println("Película titulada : "+ pelis);
		}
		
	

		int [] numerosLista = {3,4,2,5,1,7,6,8,9};
		ordena(numerosLista);
		
		for (int i =0; i<numerosLista.length;i++) {
			
			System.out.println(numerosLista[i]);
			
			
		}
		
		

		System.out.println("");
		
		for(int lista : numerosLista) {
			
			System.out.println(lista);
			
		}
		
	}
	
}
