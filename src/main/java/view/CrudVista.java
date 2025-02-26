package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import controller.FacturasControl;
import controller.PacientesControl;
import controller.DniControl;





public class CrudVista extends JFrame {
	private DniControl dniControl;

	
    // Panel principal que usa CardLayout
    private JPanel cards; // El panel de las tarjetas
    private CardLayout cardLayout; // Layout que controla las tarjetas
  
   
    // Botones y paneles
    private JButton btnAddPaciente, btnAddFactura, btnAddSesion, btnCerrar, btnInformes;  
   
 
    /*
     * Después de crear las variables para los botones, paneles y el Card 
     * Se crea las funciones para limpiar el codigo del CrudVista*/
    
    // Constructor de la ventana
    
     public CrudVista() {
    	 dniControl = new DniControl();
 
        // Configuraciones iniciales de la ventana
        setTitle("Formulario CRUD");
        setSize(1020, 1020);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        UIManager.put("Panel.background", new Color(245, 245, 220));
      //llamo a setIcone para establecer la imagen del icono de la ventana 
      		setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());
        
      	//creo un panel superior para los botones y las imagees 
  		JPanel contenedorSuperior = new JPanel();
  		contenedorSuperior.setLayout(new BoxLayout(contenedorSuperior, BoxLayout.Y_AXIS)); // Layout en eje vertical
  		contenedorSuperior.setBackground(new Color(245, 245, 220));
      		
  		//add las 3 imagenes a otro subpanel
  		JPanel imagenes = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
  		JLabel imagen1 = new JLabel(new ImageIcon(getClass().getResource("/images/doctorVisita.png")));
  		JLabel imagen2 = new JLabel(new ImageIcon(getClass().getResource("/images/factura.png")));
  		JLabel imagen3 = new JLabel(new ImageIcon(getClass().getResource("/images/oficina.png")));
  		imagenes.add(imagen1);
  		imagenes.add(imagen2);
  		imagenes.add(imagen3);
  		
 
        // Crear un panel con CardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        
        // Crear los botones de navegación
        btnAddPaciente = new JButton("Pacientes");
        btnAddFactura = new JButton("Facturas");
        btnAddSesion = new JButton("Sesiones");
        btnInformes = new JButton("Informes");
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color (220, 70, 50));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setPreferredSize(new Dimension(130,25));
        JPanel panelBtCerrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtCerrar.add(btnCerrar);
        
        // Añadir los paneles de formularios a la vista
        cards.add("AddPaciente", new PanelPacientes(dniControl));
        cards.add("AddFactura", new PanelFacturas(dniControl));
        cards.add("AddSesion", new PanelSesiones(dniControl));
        cards.add("AddInforme",new PanelInformes(dniControl));
        

        // Configuramos el panel principal de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnAddPaciente);
        buttonPanel.add(btnAddFactura);
        buttonPanel.add(btnAddSesion);
        buttonPanel.add(btnInformes);
        
        //add los botones e imagenes al contenedor superior
        contenedorSuperior.add(buttonPanel);
        contenedorSuperior.add(imagenes);
        // Colocamos los botones y las tarjetas en la ventana
        getContentPane().add(contenedorSuperior, BorderLayout.NORTH);
        getContentPane().add(panelBtCerrar, BorderLayout.SOUTH);
        getContentPane().add(cards, BorderLayout.CENTER);
     

        // Definir los ActionListeners para los botones
        btnAddPaciente.addActionListener(e -> {
            // Cuando se haga clic en el botón "Añadir Paciente", cambiará a la tarjeta correspondiente
            cardLayout.show(cards, "AddPaciente");
        });

        btnAddFactura.addActionListener(e -> {
        	
            // Cuando se haga clic en el botón "Añadir Factura", cambiará a la tarjeta correspondiente
            cardLayout.show(cards, "AddFactura");
           
        });

        btnAddSesion.addActionListener(e -> {
        	
            // Cuando se haga clic en el botón "Añadir Sesión", cambiará a la tarjeta correspondiente
            cardLayout.show(cards, "AddSesion");
           
        });
        
        btnInformes.addActionListener(e-> {
        	 
        	cardLayout.show(cards, "AddInforme");   
        	
        	
        });
        
        btnCerrar.addActionListener(e->{
        	//cerramos la app con un mensaje de confirmación
        	int cerrar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?");
        	if(cerrar == JOptionPane.YES_OPTION) {
        		System.exit(ABORT);
        		}
        	  });
        
    }

    
    public static void main (String [] args) {
    	CrudVista ventana = new CrudVista();
    	
    	ventana.setVisible(true);
    	
    	
    	
    }
    
}
