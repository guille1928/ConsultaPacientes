package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guillermo fernandez tardon
 */
public class Medico {

	/*
	 * se creará la clase principal del médico con sus funciones y métodos
	 * pertinentes para llamarlas desde otra clase java
	 */

	// creo sus variables privadas

	// creo atributos específicos de cada médico
	private int id;
	private String nombre;
	private String Apellidos;
	private int edad;
	// creo los atributos para conectarme a una base de datos
	private String email;
	private String contrasena;

	/*
	 * Para esta clase medico, creo sus getters y setters para ENCAPSULAR sus
	 * atributos y evitar errores en el futuro. Así puedo ver o modificar estos
	 * atributos atendiendo al modelo de encapsulamiento de POO
	 */

	// método público para que te muestre el id del médico
	public int getId() {
		return id;
	}

	// método público para que se modifique el id del médico
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String Apellidos) {
		this.Apellidos = Apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
