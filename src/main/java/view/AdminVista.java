package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.AdminControl;
import controller.FacturasControl;
import controller.PacientesControl;
import controller.SesionesControl;

public class AdminVista extends JFrame{

    // Panel principal que usa CardLayout
    private JPanel cards; // El panel de las tarjetas
    private CardLayout cardLayout; // Layout que controla las tarjetas
 
    // Botones y paneles
    private JButton MostrarMedico, btnCerrar, btnVolver;
    private JPanel tablaPanelMedicos;
   
    
    
    //creo otra funcion para limpiar el listener y mostrar pacientes
    private void mostrarDatosmedicos () {
    	List<Object[]> listaMedicos= new ArrayList<>();
    	//llamo a la funcion 
    	listaMedicos= AdminControl.listaMedicos();
    	
    
    	if(listaMedicos.isEmpty()) {
    	JOptionPane.showMessageDialog(this, "No existen pacientes en la base de datos");
    		
    	}else {
    		// Limpiar el contenido anterior de tablaPanel
    		tablaPanelMedicos.removeAll();
    		
    		//definimos los campos para realizar la tabla	
    		String [] columNombres = {"ID","Email","Password"};
    		Object [][] datos = listaMedicos.toArray(new Object[0][]);
    		
    		//creo el Jtable con los datos y encabezados
    		JTable tabla = new JTable(datos,columNombres);
    		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        	
        	//agrego el Jtabla a un JScrollPane para que sea desplazable 
    		JScrollPane scrollPane = new JScrollPane(tabla);
    		
    		//creo boton de eliminar 
    		JButton eliminarMedico = new JButton("Eliminar medico");
    		
    		
    		// Crear un nuevo JPanel con BoxLayout para apilar la tabla y el botón
    		JPanel panelConTabla = new JPanel();
    		panelConTabla.setLayout(new BoxLayout(panelConTabla, BoxLayout.Y_AXIS));

    		// Añadir la tabla al panel
    		panelConTabla.add(scrollPane);

    		// Opcional: agregar un pequeño espacio entre la tabla y el botón
    		panelConTabla.add(Box.createVerticalStrut(10));

    		// Añadir el botón al panel
    		panelConTabla.add(eliminarMedico);
    		eliminarMedico.addActionListener(e->{
    			//seleciono la fila de la tabla del object[] leido de los medicos
    			int rowElected = tabla.getSelectedRow();
    			if (rowElected != -1){
    				//creo las variables para recoger los datos del row
    				int idelegido = (int) tabla.getValueAt(rowElected, 0);
    				String email = (String) tabla.getValueAt(rowElected, 1);
    				int confirma = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere eliminar el medico con email : " + email);
    				if(confirma == JOptionPane.YES_OPTION) {
    					AdminControl.eliminarMedico(idelegido);
    					mostrarDatosmedicos();
    					  }
    			}
    			
    			
    		});
    		// Limpiar el subpanel de la tabla y añadir el nuevo panel con tabla y botón
    		tablaPanelMedicos.removeAll();
    		tablaPanelMedicos.add(panelConTabla, BorderLayout.CENTER);
    		
    		}
    	
    	
	    	 // Refrescar el subpanel
    	tablaPanelMedicos.revalidate();
    	tablaPanelMedicos.repaint();
    
    }

    
    // Crear paneles (tarjetas) con formularios

    private JPanel createAddPacienteForm() {
 // Crear un panel con FlowLayout para organizar los componentes de manera fluida
    	// Cambiar a BorderLayout para una mejor distribución
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un subpanel para el formulario com su espacio correspondiente
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.setPreferredSize(new Dimension(500,150));
        
  
    // Crear los campos de formulario
    
  
    JButton mostrar = new JButton("Mostrar Medicos");
    //JButton borrar = new JButton("Borrar paciente");
 
   
  
    // Crear un subpanel para los botones
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    buttonPanel.add(mostrar);
    formPanel.add(buttonPanel);
    
    // Añadir el formulario en la parte superior (NORTH) del panel principal
    panel.add(formPanel, BorderLayout.NORTH);
    
    // Inicializar el atributo global tablaPanel
    tablaPanelMedicos = new JPanel(new BorderLayout());
    panel.add(tablaPanelMedicos, BorderLayout.CENTER);
    
   
    
    mostrar.addActionListener(e-> {
    	//llamo a la funcion mostrarDatosPacientes de la clase 
    	mostrarDatosmedicos();
    	});

	return panel;

    }
    
      
    
    // Constructor de la ventana
    public AdminVista() {
        // Configuraciones iniciales de la ventana
        setTitle("Formulario CRUD");
        setSize(1020, 1020);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        UIManager.put("Panel.background", new Color(245, 245, 220));
      //llamo a setIcone para establecer la imagen del icono de la ventana 
      		setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());

        // Crear un panel con CardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        
        // Crear los botones de navegación
        MostrarMedico = new JButton("Pacientes");
        
        
        btnVolver = new JButton("Gestión Pacientes");
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color (220, 70, 50));
        btnCerrar.setForeground(Color.WHITE);
 
        // Añadir los paneles de formularios a la vista
        cards.add(createAddPacienteForm(),"AddPaciente");
       

        // Configuramos el panel principal de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnCerrar);
        buttonPanel.add(btnVolver);
        
        // Colocamos los botones y las tarjetas en la ventana
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(cards, BorderLayout.CENTER);
     
        
        // Definir los ActionListeners para los botones
        MostrarMedico.addActionListener(e -> {
            // Cuando se haga clic en el botón "Añadir Paciente", cambiará a la tarjeta correspondiente
            cardLayout.show(cards, "AddPaciente");
        });

       
        
        btnCerrar.addActionListener(e->{
        	//cerramos la app con un mensaje de confirmación
        	int cerrar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?");
        	if(cerrar == JOptionPane.YES_OPTION) {
        		System.exit(ABORT);
        		}
        	  });
        
        btnVolver.addActionListener(e ->{
        	CrudVista ventana = new CrudVista();
        	ventana.setVisible(true);
        	dispose();
        	
        });
        
        
        
    }

    public static void main(String[] args) {
    	
    	//llamamos a la clase para que se inicie 
    	//creo una instancia de la clase
    	AdminVista ventana = new AdminVista();
    	
    	//inicio la ventana 
    	ventana.setVisible(true);
    	
    	
    }

}
