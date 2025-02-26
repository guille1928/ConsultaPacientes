/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author guill
 */



import java.awt.Color;
import java.awt.FlowLayout;
import java.util.concurrent.Flow;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.LoginControl;


//creo una clase LoginVista que extiende de JFrame para crear la ventana para el usuario 

public class LoginVista extends JFrame {
	//creo las variables privada de LoginVista 
private JTextField Usuario;
private JPasswordField Contrasenia;
private JButton OK;
private JButton registro1;
private JButton btnCerrar;
private JButton Admin;



public LoginVista () {
	setTitle("Login");
	setSize(600, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	// Configuramos el FlowLayout (centrado, con 10 px de espacio entre componentes)
	setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
	//declaramos el titulo de la ventana login, le marcamos un tamaño y cuando cerrar
	getContentPane().setBackground(new Color(245, 245, 220));
	//llamo a setIcone para establecer la imagen del icono de la ventana 
			setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());
	
	
	//creamos nuevas instancias de los atributos del Jframe
	Usuario = new JTextField(15);
	Contrasenia = new JPasswordField(15);
	OK = new JButton("Inicia sesión");
	registro1 = new JButton("No estoy registrado");
	Admin = new JButton("Admin");
	Admin.setBackground(new Color (60, 179, 113));
	Admin.setForeground(Color.WHITE);
	
	btnCerrar = new JButton("Cerrar");
    btnCerrar.setBackground(new Color (220, 70, 50));
    btnCerrar.setForeground(Color.WHITE);
	
	// Creamos paneles para agrupar los componentes
    JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panelContrasenia = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panelRegistro1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel panelAdmin = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    
    
    panelUsuario.setBackground(new Color(245, 245, 220));
    panelContrasenia.setBackground(new Color(245, 245, 220));
    panelRegistro1.setBackground(new Color(245, 245, 220));
    panelCerrar.setBackground(new Color(245, 245, 220));
    panelAdmin.setBackground(new Color(245, 245, 220));
    
	//añado con add() al JFrame las nuevas instancias 
	
   panelUsuario.add(new JLabel("Usuario :")) ;
   panelUsuario.add(Usuario);
   panelContrasenia.add(new JLabel("Contraseña :"));
   panelContrasenia.add(Contrasenia);
   panelRegistro1.add(new JLabel("Registro"));
   panelRegistro1.add(registro1);
   panelRegistro1.add(new JLabel("Cerrar"));
   panelRegistro1.add(btnCerrar);
   panelAdmin.add(new JLabel("Admin"));
   panelAdmin.add(Admin);
 
   
   add(panelUsuario);
   add(panelContrasenia);
   add(OK);
   add(registro1);
   add(btnCerrar);
   add(Admin);
   
   
   //creo un eventListener para recoger la info introducida por el usuario y realizar unas comprobaciones 
   OK.addActionListener(e ->
   	{
	   
	   String datoUsuario = Usuario.getText();
	   char[] passUsuario = Contrasenia.getPassword();
	   if(datoUsuario.isEmpty() || passUsuario.length ==0) {
		   //tengo que mostrar un mensaje al usuario para que anote todos los datos 
		
		 JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
		   
	   	} else {
		   boolean verificaUser= LoginControl.validarDatosUsuario(datoUsuario, passUsuario);
		   		//me conectaré aqui a mysql para comprobar datos 
		   		if(verificaUser) {
		   			CrudVista ventana2 = new CrudVista();
		   			ventana2.setVisible(true);
		   			dispose();
		   			//tengo que crear una referencia a una nueva ventana 
		   			}else {System.out.println("Acceso incorrecto");}
		   
	   		}
	  
   	});
   
   btnCerrar.addActionListener(e->{
	   int cerrar= JOptionPane.showConfirmDialog(this, "¿Estás seguro de cerrar?");
	   if (cerrar == JOptionPane.YES_OPTION) {
		   System.exit(ABORT);
	   }
   });
   
   registro1.addActionListener(e-> 
   
   {
	   //al hacer click con el raton nos lleva a la clase RegistroVisto
	   
	    RegistroVista ventana1 = new RegistroVista();
	    //muestro la ventana de registro 
	    ventana1.setVisible(true);
	    //abro la ventana de registro
	    dispose();
	    //cierro la ventana de login
	     }); 
 

   Admin.addActionListener(e->{
	   
	   LoginAdmin ventana3 = new LoginAdmin();
	   ventana3.setVisible(true);
	   dispose();
	   
   });
   
}



public static void main (String[]args) {
	LoginVista ventana = new LoginVista();
	
	ventana.setVisible(true);
	
}
	
	
	
}



