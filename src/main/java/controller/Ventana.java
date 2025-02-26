package controller;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Ventana extends JFrame{

	public Ventana() {
		
		setSize(600, 400);
		//llamo a setIcone para establecer la imagen del icono de la ventana 
		setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		UIManager.put("Panel.background", new Color(245, 245, 220));
		getContentPane().setBackground(new Color(245, 245, 220)); 
		
		
		
	}
	
	
	
}
