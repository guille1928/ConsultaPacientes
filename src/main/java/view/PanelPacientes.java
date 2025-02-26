package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.PacientesControl;
import controller.TextoComprobaciones;
import controller.DniControl;


public class PanelPacientes extends JPanel{
	
	private JPanel tablaPanelpacientes;
	private DniControl dniControl;
	
    public List<Object[]> saberListaPacientes() {
    	
    	List<Object[]> listaPacientes = new ArrayList<Object[]>();		
    	listaPacientes = PacientesControl.listaCompletaPacientes();
    	
    return listaPacientes;	
    	
    	
    }
    
	
    //creo la funcion para guardar pacientes y actualizar el dniBox
	private boolean guardarPacientes (String dni, String nombre, String apellidos) {
			boolean guardar = PacientesControl.guardarPacientesDB(dni, nombre, apellidos);
			if (guardar) {
			dniControl.ActualizarDniModel();
			JOptionPane.showMessageDialog(this, "Se han introducido los datos del paciente");
			
			//llama la funcion para mostrar los datos de todos los pacientes 
			mostrarDatosPacientes(tablaPanelpacientes);
			return true;
			}else {JOptionPane.showMessageDialog(this, "No se han podido guardar los datos");}
		
		return false;
	
 }
	//se crea la funcion para mstrar los datos de pacientes con sus botones adiciones para modificar sus datos personales
    private  List<Object[]> mostrarDatosPacientes (JPanel destinoPanel) {
	    	
	    	List<Object[]> listaPacientes= new ArrayList<>();
	    	List<Object[]> listaParaInforme = new ArrayList<Object[]>();
	    	//llamo a la funcion 
	    	listaPacientes= controller.PacientesControl.listaCompletaPacientes();
	    	listaParaInforme = controller.PacientesControl.listaCompletaPacientes();
	    
	    	if(listaPacientes.isEmpty()) {
	    	JOptionPane.showMessageDialog(this, "No existen pacientes en la base de datos");

	    	}else {
	    		destinoPanel.removeAll();

	    		//definimos los campos para realizar la tabla	
	    		String [] columNombres = {"DNI","Nombre","Apellidos"};
	    		Object [][] datos = listaPacientes.toArray(new Object[0][]);
	    		
	    		//creo el Jtable con los datos y encabezados
	    		JTable tabla = new JTable(datos,columNombres);
	    		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    		
	        	//agrego el Jtabla a un JScrollPane para que sea desplazable 
	    		JScrollPane scrollPane = new JScrollPane(tabla);
	    		  scrollPane.setPreferredSize(new Dimension(600, 350));
	    		
	    		//creo boton de eliminar 
	    		JButton eliminarPaciente = new JButton("Eliminar paciente");
	    		JButton verDatosPaciente = new JButton("Ver datos paciente");
	    	    JPanel panelBotones = new JPanel();
	    		panelBotones.add(eliminarPaciente);
	    		panelBotones.add(verDatosPaciente);

	    		eliminarPaciente.addActionListener(e->{
	    			int rowAfected = tabla.getSelectedRow();
	    			//verifico si hay una fila seleccionada
	    			if (rowAfected != -1 ) {
	    				//con getValueAt Seleccionamos el dni de la lista que eligio el usuario 
	    				String dni = (String) tabla.getValueAt(rowAfected,0);
	    				int confirma = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar el paciente : " + dni);
	    					if(confirma == JOptionPane.YES_OPTION) {
	    						boolean borrar = PacientesControl.borrarPaciente(dni);
	    							if(borrar) {
	    								JOptionPane.showMessageDialog(this, "Se ha borrado el paciente de la base de datos ");
	    								
	    								mostrarDatosPacientes(destinoPanel);
	    								dniControl.ActualizarDniModel();
	    							}else {JOptionPane.showMessageDialog(this, "No se ha podido borrar el paciente");}
	    					}
	    				}else {JOptionPane.showMessageDialog(this, "Por favor selecciona un paciente de la lista");}
	    			
	    			
	    		});

	    		verDatosPaciente.addActionListener(e-> {
	    			destinoPanel.removeAll();
	        		
	    		    List<Object[]> mostrarPacienteCompleto = new ArrayList<Object[]>();
	    		    int rowAffected = tabla.getSelectedRow();
	    		    if(rowAffected < 0) {
	    		        JOptionPane.showMessageDialog(this, "Tienes que seleccionar un paciente de la lista ");
	    		        mostrarDatosPacientes(destinoPanel);
	    		    } else {
	    		        if(rowAffected != -1) {
	    		        /* 
	    		         * Si el usuario medico selecciona una fila de los pacientes y hace click en ver datos paciente
	    		         * Le lleva a este bloque de código, el cual crea los diferentes botones, JLabels para mostrar los datos del paciente
	    		         * 
	    		         * Se crea también una textArea que el usuario puede actualizar para introducir datos del paciente 
	    		         * */
			        	JButton botonGuardaPaciente = new JButton("Introducir datos paciente");

			            String seleccionDni = (String) tabla.getValueAt(rowAffected, 0);
			            mostrarPacienteCompleto = PacientesControl.verDatosPaciente(seleccionDni);
			            
			            JPanel panelContenido = new JPanel();
			            panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
			            panelContenido.setPreferredSize(new Dimension(400, 300));

			           		            
			            //creo variables para recoger los datos de la funcion de lectura de la base de datos. La cual lo guarda en un Object [][]
			            String dni = (String) mostrarPacienteCompleto.get(0)[0];
			            String nombre = (String) mostrarPacienteCompleto.get(0)[1];
			            String apellidos = (String) mostrarPacienteCompleto.get(0)[2];
			            
			            
			            //estilos de los JLabels
			            // Crear un panel con FlowLayout para cada par de etiqueta y valor
			           
			            JPanel panelDni = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));  // Alineación a la izquierda sin espacio adicional
			            JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));  
			            JPanel panelApellidos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));  

			            
	    		         // Etiqueta y valor para DNI
	    		         JLabel labelDni = new JLabel("DNI: ");
	    		         labelDni.setFont(new Font("Arial", Font.BOLD, 14));
	    		         JLabel valorDni = new JLabel(dni);
	    		         valorDni.setFont(new Font("Arial", Font.PLAIN, 14));
	    		         panelDni.add(labelDni);
	    		         panelDni.add(valorDni);

	    		         // Etiqueta y valor para Nombre
	    		         JLabel labelNombre = new JLabel("Nombre: ");
	    		         labelNombre.setFont(new Font("Arial", Font.BOLD, 14));
	    		         JLabel valorNombre = new JLabel(nombre);
	    		         valorNombre.setFont(new Font("Arial", Font.PLAIN, 14));
	    		         panelNombre.add(labelNombre);
	    		         panelNombre.add(valorNombre);

	    		         // Etiqueta y valor para Apellidos
	    		         JLabel labelApellidos = new JLabel("Apellidos: ");
	    		         labelApellidos.setFont(new Font("Arial", Font.BOLD, 14));
	    		         JLabel valorApellidos = new JLabel(apellidos);
	    		         valorApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
	    		         panelApellidos.add(labelApellidos);
	    		         panelApellidos.add(valorApellidos);
	    		         
	    		         // Añadir cada panel de par etiqueta-valor al panel principal
	    		         panelContenido.add(panelDni);
	    		         panelContenido.add(panelNombre);
	    		         panelContenido.add(panelApellidos);
	   
	    		            // Crear el JTextArea para la descripción
	    		            JTextArea textDescripcion = new JTextArea(45, 50);
	    		            textDescripcion.setText((String) mostrarPacienteCompleto.get(0)[3]);
	    		            textDescripcion.setWrapStyleWord(true);
	    		            textDescripcion.setLineWrap(true);
	    		            textDescripcion.setEditable(true);
	    		            
	    		            
	    		            // Crear el JScrollPane para el JTextArea
	    		            JScrollPane descripcionScroll = new JScrollPane(textDescripcion);
	    		            descripcionScroll.setPreferredSize(new Dimension(400, 100));
	    		            

	    		            // Agregar el JScrollPane al panel
	    		            panelContenido.add(descripcionScroll);
	    		            panelContenido.add(botonGuardaPaciente);
	    		           
	    		            //si guarda, modifica el textArea de descripcion de los pacientes 
	    		            botonGuardaPaciente.addActionListener(i-> {
	    		            	String cajaTexto = textDescripcion.getText();
	    		            	if (cajaTexto.isEmpty()) {
	    		            		JOptionPane.showMessageDialog(this, "Debes introducir datos en su descripción");
	    	
	    		            	}else {
	    		            		//llamo a la funcion de UPDATE de la DB para guardar los nuevos datos en la caja de texto 
	    		            		boolean exito = PacientesControl.guardarInfoPaciente(cajaTexto, seleccionDni);
	    		            		if (exito) {
	    		            			JOptionPane.showMessageDialog(this, "Se actualizaron los datos del paciente correctamente");
	    		            			PacientesControl.verDatosPaciente(seleccionDni);
	
	    		            		}
	    		            		
	    		            		
	    		            	}
		
	    		            });

	    		            // Finalmente, añadir el panelContenido al panel principal (tablaPanelpacientes)
	    		            destinoPanel.add(panelContenido, BorderLayout.NORTH);
	    		            
	    		            
	    		            //tablaPanelpacientes.add(panelContenido, BorderLayout.NORTH);
	    		            
	    		            // Refrescar la vista
	    		            destinoPanel.revalidate();  // Esto asegura que el panel se vuelva a renderizar
	    		            destinoPanel.repaint();
            
	    		        }else {JOptionPane.showMessageDialog(this, "Por favor selecciona un paciente de la lista");}
	    		    }
	    		});

	    		
	    		// Crear un nuevo JPanel con BoxLayout para apilar la tabla y el botón
	    		JPanel panelConTabla = new JPanel();
	    		panelConTabla.setLayout(new BoxLayout(panelConTabla, BoxLayout.Y_AXIS));

	    		// Añadir la tabla al panel
	    		panelConTabla.add(scrollPane);
	    		panelConTabla.add(panelBotones);

	    		/* Limpiar el subpanel de la tabla y añadir el nuevo panel con tabla y botón
	    		tablaPanelpacientes.removeAll();
	    		tablaPanelpacientes.add(panelConTabla, BorderLayout.NORTH);
	    		*/
	    		
	    		destinoPanel.removeAll();
	    		destinoPanel.add(panelConTabla, BorderLayout.NORTH);
	    		
	    		}
		    	 // Refrescar el subpanel
	    	destinoPanel.revalidate();  // Esto asegura que el panel se vuelva a renderizar
	        destinoPanel.repaint();
	        
	        
	        return listaParaInforme;
	    	}

	
	 //creo el panel para pasarle los combobox como referencia para la otras clase
	 public PanelPacientes(DniControl dniControl) {
		 this.dniControl = dniControl;
		 
		 	
		    //llamamos a la otra clase para decirle a que hace referencia esta variable
		    
		    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		    // Crear un subpanel para el formulario com su espacio correspondiente
		    JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		    formPanel.setPreferredSize(new Dimension(500,150));
		    
		    // Crear los campos de formulario
		    JTextField nombrePaciente = new JTextField(15);
		    JTextField dniPaciente = new JTextField(15);
		    JTextField apellidosPaciente = new JTextField(15);
		    //comprobaciones del texto 
		    String nombreHolder = "Nombre";
		    nombrePaciente.setText(nombreHolder);
		    TextoComprobaciones.placeHolder(nombrePaciente, nombreHolder);
		    
		    String dniHolder = "Dni del paciente";
		    dniPaciente.setText(dniHolder);
		    TextoComprobaciones.placeHolder(dniPaciente, dniHolder);
		    
		       
		    String apellidosPacienteHolder = "Apellidos";
		    apellidosPaciente.setText(apellidosPacienteHolder);
		    TextoComprobaciones.placeHolder(apellidosPaciente, apellidosPacienteHolder);
    
		    JButton guardar = new JButton("Guardar paciente");
		    JButton mostrar = new JButton("Mostrar Pacientes");
		    //JButton borrar = new JButton("Borrar paciente");
		    
		    // Agregar los componentes al panel para cada valor con su etiqueta a mostrar
		    formPanel.add(new JLabel("DNI: "));
		    formPanel.add(dniPaciente);
		    formPanel.add(new JLabel("Nombre: "));
		    formPanel.add(nombrePaciente);
		    formPanel.add(new JLabel("Apellidos: "));
		    formPanel.add(apellidosPaciente);
		    
		    
		    // Crear un subpanel para los botones
		    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		    buttonPanel.add(mostrar);
		    buttonPanel.add(guardar);
		    //buttonPanel.add(borrar);
		    formPanel.add(buttonPanel);
		    
		    // Añadir el formulario en la parte superior (NORTH) del panel principal
		    this.add(formPanel, BorderLayout.NORTH);
		    
		    // Inicializar el atributo global tablaPanel
		    tablaPanelpacientes = new JPanel(new BorderLayout());
		    this.add(tablaPanelpacientes, BorderLayout.CENTER);
    
		    guardar.addActionListener(e ->{
		    	
		    	
		    	if(dniPaciente.getText().equals(dniHolder) || nombrePaciente.getText().equals(nombreHolder) || apellidosPaciente.getText().equals(apellidosPacienteHolder)) {
		    		
		    		JOptionPane.showMessageDialog(this, "Tienes que rellenar todos los campos");
		   
		    	}else {
		    	//realizo un metodo para simplificar el codigo en el formulario , lo llamo aqui 	
		    	boolean exito= guardarPacientes(dniPaciente.getText(),nombrePaciente.getText().trim(),apellidosPaciente.getText().trim());	
		    	if(exito) {
		    		TextoComprobaciones.placeHolderBlanco(dniPaciente, dniHolder);
		    		TextoComprobaciones.placeHolderBlanco(nombrePaciente, nombreHolder);
		    		TextoComprobaciones.placeHolderBlanco(apellidosPaciente, apellidosPacienteHolder);
		    		
		    		
		    		}
		    	
		    	    	}
		   
		    	});
		    
    
		    //creo unas limitaciones para no poder añadir numeros a los campos 
		    KeyAdapter keyAdapter = new KeyAdapter() {
		    	public void keyTyped(KeyEvent e) {
		    		char c = e.getKeyChar();
		    		if(Character.isDigit(c))
		    		//si el caracter es un numero 
		    			{
		    			e.consume(); //se cancela la entrada del carácter
		    		}
		    		
		    	}
				};
		    //añado el keylistener a los 2 campos del formulario 	
		    nombrePaciente.addKeyListener(keyAdapter);
		    apellidosPaciente.addKeyListener(keyAdapter);
		  
		  //creo un evento de keylistener para que no pueda introducir mas de 9 caracteres o caracteres especiales
		    dniPaciente.addKeyListener(new KeyAdapter() {
		 	public void keyTyped (KeyEvent e) {
		 		char c = e.getKeyChar();
		 		if(!Character.isLetterOrDigit(c) || dniPaciente.getText().length() >=9) {
		 			e.consume();
		 			}
		 		
		 	}
		    });
		    
		    mostrar.addActionListener(e-> {
		    	//llamo a la funcion mostrarDatosPacientes de la clase 
		    	mostrarDatosPacientes(tablaPanelpacientes);
		    	});


		    }
		    
		    	

}
