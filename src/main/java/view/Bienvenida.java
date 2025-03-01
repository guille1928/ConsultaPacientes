package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import controller.Ventana;

public class Bienvenida extends Ventana{

	//creo esta clase para mostrar una ventana de bienvenida al usuario a nuestra apliacion 

	//prueba de comentario en bienvenida 

	public Bienvenida() {
		//creamos las caracterias de la ventana
	

	//logo puesto en las ventanas de la aplicacion
	JPanel logoPane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
	logoPane.setBackground(new Color(245, 245, 220));
	JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/images/doctor.png")));
	logoPane.add(logo);
	
	
	//titulo creado aliniado al centro y con un formato específico 
	JPanel titulo = new JPanel();
	JLabel tituloVentana = new JLabel("Consulta Médica - Gestión de Pacientes");
	titulo.setLayout(new javax.swing.BoxLayout(titulo, javax.swing.BoxLayout.Y_AXIS));
	tituloVentana.setAlignmentX(CENTER_ALIGNMENT);
	tituloVentana.setFont(new Font("Arial", Font.BOLD, 20));
	titulo.add(tituloVentana);
	titulo.add(javax.swing.Box.createVerticalStrut(40));	
	
	
	//croe el panel para el contenido ajustado
	JPanel contenido = new JPanel();
	contenido.setLayout(new javax.swing.BoxLayout(contenido, BoxLayout.Y_AXIS));
	contenido.setBackground(new Color(245, 245, 220));
	
	
	
	//creo otro panel para dar forma y posicion al texto
	JPanel textoPane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
	JLabel mensajeTexto = new JLabel("<html>"
            + "<div style='text-align: center; width: 300px;'>" // Caja de 300px de ancho
            + "<p style='font-size: 12px; font-family: Arial;'>"
            + "Bienvenido al sistema de gestión de pacientes.<br>"
            + "Desde aquí podrás gestionar la información de los pacientes,"
            + "sus sesiones y facturas de manera sencilla."
            + "</p>"
            + "</div></html>");
	mensajeTexto.setFont(new Font("Arial", Font.PLAIN, 3));
	mensajeTexto.setAlignmentX(CENTER_ALIGNMENT);
	textoPane.add(mensajeTexto);
	
	
	//le add al JPanel de contenido el texto y el logo 
	contenido.add(textoPane);
	contenido.add(logoPane);
	
	
	
	//panel botones
	JButton acceder = new JButton("Acceder");
	JButton salir = new JButton("Salir");
	//panel de los botones de la ventana
	JPanel botones = new JPanel();
	botones.add(acceder);
	botones.add(salir);
	
	//add paneles y propiedades al panel 
	add(titulo, BorderLayout.NORTH);
	add(contenido, BorderLayout.CENTER);
	add(botones, BorderLayout.SOUTH);
	
	

	//creo ambos eventos de escucha para los botones de acceder y salir de la aplicacion
	acceder.addActionListener(e-> {
		
		LoginVista ventana = new LoginVista();
		ventana.setVisible(true);
		dispose();
		});
	
	salir.addActionListener(e-> {
		int salir1 = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?");
		if(salir1 == JOptionPane.YES_OPTION) {
			System.exit(ABORT);
			
			}
			
		});
	

	}
	
	
	 public static void main(String[] args) {	
	
	 Bienvenida ventana = new Bienvenida();
	 ventana.setVisible(true);
		 
	 }	
	
	
	}
	
	
	

