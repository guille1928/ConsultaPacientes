/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.RegistroControl;


/**
 *
 * @author guill
 */
public class RegistroVista extends JFrame{
	
	//declaro las variables a utilizar en la ventana de registro 
	private JTextField email,email2;
	private JPasswordField contrasenia1,contrasenia2 ; 
	private JButton ok, btnCerrar; 
	private JButton volver;
	public RegistroVista () {
		
		//defino las características de la ventana de registro
		setTitle("Registro");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		 setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		//declaramos el titulo de la ventana login, le marcamos un tamaño y cuando cerrar
		 getContentPane().setBackground(new Color(245, 245, 220));
		//llamo a setIcone para establecer la imagen del icono de la ventana 
			setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());

	        // Inicializo los componentes de la ventana
	        email = new JTextField(26); // Ajuste del tamaño
	        email2 = new JTextField(20); // Ajuste del tamaño
	        contrasenia1 = new JPasswordField(26); // Ajuste del tamaño
	        contrasenia2 = new JPasswordField(20); // Ajuste del tamaño
	        ok = new JButton("Enviar");
	        volver = new JButton("Volver al login");
	        btnCerrar = new JButton("Cerrar");
	        btnCerrar.setBackground(new Color(220, 70, 50));
	        btnCerrar.setForeground(Color.WHITE);

	     
	        // Añadir las etiquetas y campos en el layout
	        JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
	        panelEmail.add(new JLabel("Correo electrónico: "));
	        panelEmail.add(email);
	        
	        JPanel panelEmail2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
	        panelEmail2.add(new JLabel("Confirma el correo electrónico: "));
	        panelEmail2.add(email2);
	        
	        JPanel panelContrasenia = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
	        panelContrasenia.add(new JLabel("Contraseña: "));
	        panelContrasenia.add(contrasenia1);
	        
	        JPanel panelContrasenia2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
	        panelContrasenia2.add(new JLabel("Confirma la contraseña: "));
	        panelContrasenia2.add(contrasenia2);
	        
	        // Añadir los botones en el layout
	        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Alineado al centro
	        panelBotones.add(ok);
	        panelBotones.add(volver);
	        panelBotones.add(btnCerrar);
	        
	        //asigno un color al fondo 
	        panelEmail.setBackground(new Color(245, 245, 220));
	        panelEmail2.setBackground(new Color(245, 245, 220));
	        panelContrasenia.setBackground(new Color(245, 245, 220));
	        panelContrasenia2.setBackground(new Color(245, 245, 220));
	        panelBotones.setBackground(new Color(245, 245, 220));
	        
	        
	        

	        // Añadir los paneles al JFrame
	        add(panelEmail);
	        add(panelEmail2);
	        add(panelContrasenia);
	        add(panelContrasenia2);
	        add(panelBotones);
		
		//creo un evento para realizar una accion cuando haga click 
		
		ok.addActionListener (e ->
		{
	
			//creo las variables para recoger lo que introduce en la ventana
			String email1 = email.getText();
			String email3 = email2.getText();
			char[] contrasenia = contrasenia1.getPassword();
			char[] contrasenia3 = contrasenia2.getPassword();
				//controlo con un condicional que introduzca ambos campos
				if(email1.isEmpty() || contrasenia.length ==0 || email3.isEmpty() || contrasenia3.length ==0) {
					JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
				}
				else {
					boolean correctoEmail = RegistroControl.comprobarEmail(email1);
					boolean correctoEmailRepe = RegistroControl.comprobarEmail(email3);
					if(!correctoEmail && !correctoEmailRepe) {
						
						JOptionPane.showMessageDialog(this, "Tienes que introducir una dirección de correo electrónico válida");
					}else {
						
						//comparo ambos emails y contraseñas para introducir los datos en la bsae de datos 
						if(email1.equals(email3) && Arrays.equals(contrasenia, contrasenia3)) {
							//llamo al metodo y le paso el email y contrasenia
							boolean exito = RegistroControl.registroMedico(email1,contrasenia);
							if(exito) {
									JOptionPane.showMessageDialog(this, "Te has registrado correctamente");
									//a borrar funcion de leerdatos
									RegistroControl.leerDatosMedico();
							}else {JOptionPane.showMessageDialog(this, "Hubo un error en el registro");
									}
						
							} else {JOptionPane.showMessageDialog(this, "El email o contraseña no coinciden");}
						}
						
				}
		});	
		//evento listener del boton cerrar
		 btnCerrar.addActionListener(e->{
			   int cerrar= JOptionPane.showConfirmDialog(this, "¿Estás seguro de cerrar?");
			   if (cerrar == JOptionPane.YES_OPTION) {
				   System.exit(ABORT);
			   }
		   });	
		//evento listener del boton volver
		 volver.addActionListener(e->{
			 
			 LoginVista ventana = new LoginVista();
			 ventana.setVisible(true);
			 dispose();
			 
		 });
		 
		 		 
		 
	}
	
	
}
