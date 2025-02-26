/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author guill
 */
public class Paciente {
    
    
//declaro los atributos privados del paciente, atendiendo a la encapsulación de POO
    
private String nombre;
private String apellidos;
private int id; 
private String dni;
private String historia;
public String getNombre() {
	return nombre;
}

//genero los setters y getters para saber o establecer los atributos 

public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellidos() {
	return apellidos;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getHistoria() {
	return historia;
}
public void setHistoria(String historia) {
	this.historia = historia;
}


    
    
}
