package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.JobAttributes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.RegistroControl;

public class LoginAdmin extends JFrame{
	//variables
	private JTextField Usuario;
	private JPasswordField Contrasenia;
	private JButton OK;
	private JButton btnCerrar, btnVolver;


	public LoginAdmin () {
	//características de la ventana
		
		setTitle("Admin");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		//llamo a setIcone para establecer la imagen del icono de la ventana 
		setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());
		
		Usuario = new JTextField(14);
		Contrasenia = new JPasswordField(14);
		OK = new  JButton("Enviar");
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color (220, 70, 50));
	    btnCerrar.setForeground(Color.WHITE);
		btnVolver = new JButton("Volver al login");
		
	    JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JPanel panelContrasenia = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		panelUsuario.add(new JLabel("Usuario"));
		panelUsuario.add(Usuario);
		panelContrasenia.add(new JLabel("Contraseña"));
		panelContrasenia.add(Contrasenia);
		panelButton.add(OK);
		panelButton.add(btnCerrar);
		panelButton.add(btnVolver);
		
		add(Usuario);
		add(Contrasenia);
		add(OK);
		add(btnCerrar);
		add(btnVolver);
			
		OK.addActionListener(e->{
			char[] passAdmin = Contrasenia.getPassword();
			String user = Usuario.getText();
			
			if(user.isEmpty() || passAdmin.length == 0) {
				JOptionPane.showMessageDialog(this, "Por favor rellene todos los campos");
				}else {
				boolean exito = RegistroControl.autentificarAdmin(user, passAdmin);
				if(exito) {
					AdminVista ventana = new AdminVista();
					ventana.setVisible(true);
					dispose();
				}else {JOptionPane.showMessageDialog(this, "Hubo un error en la verificación del admin");}
			}
			
		});
		
		btnCerrar.addActionListener(e-> {
			int salir = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas salir?");
			if(salir == JOptionPane.YES_OPTION) {
				System.exit(ABORT);
			}
			
		});
		
		btnVolver.addActionListener(e-> {
			LoginVista ventana = new LoginVista();
			ventana.setVisible(true);
			dispose();
		});
		
	
	}
	

}
