package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextoComprobaciones {
	//clase creada para establecer las diferentes comprobaciones cuando el usuario introduce los datos 
	
	
	public static void placeHolder (JTextField campoTexto, String textoHolder) {
		
		//funcion para generar un placeholder en los campos vacios que mostramos al usuarios
		//String mostrar = campoTexto.getText();
		campoTexto.addFocusListener(new FocusListener() {
			
			//si se pierde el focus
			public void focusLost(FocusEvent e) {
				if(campoTexto.getText().isEmpty()) {
					campoTexto.setText(textoHolder);
					campoTexto.setBackground(new Color(250,250,250));
					
				}
			}
			
			//si se gana el focus
			public void focusGained(FocusEvent e) {
				if(campoTexto.getText().equals(textoHolder)) {
					campoTexto.setText("");
					campoTexto.setBackground(new Color(230, 230, 230));
					
					
					
				}
			}
		});
		
		
		
	}
	
	
	
	public static void placeHolderBlanco (JTextField campoTexto, String textoHolder) {
		campoTexto.setText(textoHolder);
		campoTexto.setBackground(Color.WHITE);
		
	
	}
}
