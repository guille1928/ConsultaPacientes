/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author guill
 */
public class Facturas {
	
	//se establece atributos de la clase facturas
	private int idFacturas;
	private double precio;
	private String observaciones;
	
	
	
	//getters y setters para cada valor de sus atributos 
	
	public int getIdFacturas() {
		return idFacturas;
	}
	public void setIdFacturas(int idFacturas) {
		this.idFacturas = idFacturas;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
    
}
