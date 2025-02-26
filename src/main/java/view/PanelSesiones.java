package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.DniControl;
import controller.PacientesControl;
import controller.SesionesControl;
import controller.TextoComprobaciones;

public class PanelSesiones extends JPanel{
private JPanel tablaPanelSesiones;
private JTextField tipoSesion;
private String dniSeleccionado;
private DniControl dniControl;

	  /*creo este metodo para actualizar ambos  combobox de la clase crudvista
 private void actualizarDniBox () {
	box1.removeAllItems();
	box2.removeAllItems();

	List<String> listaDni = new ArrayList<String>();
	
	listaDni = PacientesControl.saberDniPacientesDB();
	
	
	if(listaDni== null || listaDni.isEmpty()) {
		box1.addItem("No hay pacientes en la base de datos");
		box2.addItem("No hay pacientes en la base de datos");
    		box1.setEnabled(false);
    		box2.setEnabled(false);
    	}else {
    		box1.setEnabled(true);
			box2.setEnabled(true);
    		for (String dni : listaDni) {
    			box1.addItem(dni);
    			box2.addItem(dni);
    			
    		}
    		
    	}
    	
    }
 */   
  //creo otra funcion para mostrar en una tabla las sesiones
private void mostrarSesionesPacientes () {
	//creo una lista de objetos
	
	List<Object[]> sesiones = new ArrayList<Object[]>();
	sesiones = SesionesControl.mostrarSesiones(dniSeleccionado);
	//meto en la lista la funcion que lee las sesiones de la DB
	if(sesiones.isEmpty()) {
		
		
		JOptionPane.showMessageDialog(this, "El paciente no tiene sesiones");
	}else {
	
    	//creo las columnas	
    	String [] columNombre = {"ID", "DNI Paciente", "Tipo de sesión"};
    	//creo los datos con la list de sesiones
    	Object [][] datos = sesiones.toArray(new Object[0][]);
    	//añado ambos campos a una tabla con JTable
    	JTable tabla = new JTable(datos,columNombre);
    	tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	//añado los datos al ScrollPane
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		//creo boton de eliminar sesion
		JButton eliminarSesion = new JButton("Eliminar sesión");
		JButton modificarSesion = new JButton("Modificar sesión");
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.add(eliminarSesion);
		botonesPanel.add(modificarSesion);
		//creo el actionlistener al boton
		eliminarSesion.addActionListener(e-> {
			int rowAfected = tabla.getSelectedRow();
			
			if (rowAfected != -1 ) {
				String id = (String) tabla.getValueAt(rowAfected, 0);
				int id1 = Integer.parseInt(id);
				String dni = (String) tabla.getValueAt(rowAfected,1 );
				int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas eliminar la sesion con ID : " + id1 + " del paciente : "+dni);
				if(confirm == JOptionPane.YES_OPTION) {
					//llamo a la funcion de borrar la sesion 
					SesionesControl.borrarSesion(dni, id1);

					}
				
				
				}else {JOptionPane.showMessageDialog(this, "Tienes que seleccionar una sesión de la lista");}
		
			tablaPanelSesiones.removeAll();
	
				
			 // Refrescar el subpanel
			tablaPanelSesiones.revalidate();
			tablaPanelSesiones.repaint();
			mostrarSesionesPacientes();
			
			});
		
		modificarSesion.addActionListener(e-> {
			String sesionHolder = "Sesiones";
			int rowAfected = tabla.getSelectedRow();
			
			if (rowAfected != -1) {
				String id = (String) tabla.getValueAt(rowAfected, 0);
				String dniselec = (String) tabla.getValueAt(rowAfected, 1);
				
				int id1 = Integer.parseInt(id);
				String tipoSesionForm = tipoSesion.getText().trim();
				if(tipoSesionForm.isEmpty() || tipoSesionForm.equals(sesionHolder)) {
					JOptionPane.showMessageDialog(this, "Debes introducir un tipo de sesión");
				}
					else {
						//llamo a la funcion de modificar la sesion de sesioesControl.java
						boolean exito = SesionesControl.modificarSesion(dniselec, tipoSesionForm, id1);
						//le paso el dni seleccionado de la fila de la tabla, el tipo de sesion del Textfield y el id de la tabla 
						if(exito) {
							JOptionPane.showMessageDialog(this, "Se ha modificado con exito la sesión del paciente con dni : "+dniselec);
							tablaPanelSesiones.removeAll();
							mostrarSesionesPacientes();
						}else {JOptionPane.showMessageDialog(this, "Hubo un error al modificar la factura");}
					}
					
					
					
					
				}else {JOptionPane.showMessageDialog(this, "Tienes que seleccionar una sesión de la lista");}
				
			
			
		});
		
		//añado el boton de eliminar al panel 
		JPanel paneBoton = new JPanel();
		paneBoton.add(scrollPane);
		paneBoton.add(botonesPanel);
		
		tablaPanelSesiones.removeAll();
		tablaPanelSesiones.add(paneBoton, BorderLayout.CENTER);
			
		 // Refrescar el subpanel
			tablaPanelSesiones.revalidate();
			tablaPanelSesiones.repaint();
    		}
    	
    }
  
    
    	
public PanelSesiones (DniControl dniControl) {
	
	
	this.dniControl = dniControl;
   	JComboBox<String> dniBox = new JComboBox<String>(dniControl.getDniModel());
	// Crear un panel con FlowLayout para organizar los componentes de manera fluida

	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	
	// Crear el subpanel para el formulario
	JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	formPanel.setPreferredSize(new Dimension(500, 150)); //
    
	 tablaPanelSesiones = new JPanel(new BorderLayout());
	 this.add(tablaPanelSesiones, BorderLayout.CENTER);

	//creo los text y botones pertinentes 
   
   
    tipoSesion = new JTextField(14);
    String sesionHolder= "Sesiones";
    tipoSesion.setText(sesionHolder);
    TextoComprobaciones.placeHolder(tipoSesion, sesionHolder);
    
    
    JButton mostrar = new JButton("Mostrar sesiones");
    JButton guardar = new JButton("Guardar sesiones");
    

    	  // Crear un subpanel para los botones
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    buttonPanel.add(guardar);
    buttonPanel.add(mostrar);	
 
   
    // Añadir el formulario y los botones al panel principal (parte superior)
    this.add(formPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.EAST);
   	
    //añadimos los siguientes componentes al formPanel 
    formPanel.add(new JLabel("DNI paciente :"));
    formPanel.add(dniBox);
    formPanel.add(new JLabel("Tipo sesión"));
    formPanel.add(tipoSesion);
  
    //creo evento para recoger que dni selecciona el usuario de la lista 
    dniBox.addActionListener(e ->{
 	   //recojo el dni seleccionado 
 	  dniSeleccionado= (String) dniBox.getSelectedItem();
 	  //refresco y cierro el panel
 	  tablaPanelSesiones.removeAll();
 	  tablaPanelSesiones.revalidate();
 	  tablaPanelSesiones.repaint();
 
    });
    
    //añado un listener para que cuando de a mostrar conociendo el dniSeleccionado le muestra las sesiones 
    mostrar.addActionListener(e-> {
    	dniSeleccionado = (String) dniBox.getSelectedItem();
    	mostrarSesionesPacientes();
    	
    });
    
    guardar.addActionListener(e->{
    	
    	dniSeleccionado = (String) dniBox.getSelectedItem();
    	String tipo2 = tipoSesion.getText().trim();
    	if(dniSeleccionado== null || dniSeleccionado.isEmpty() || tipo2.isEmpty() || tipo2.equals(sesionHolder)) {
    			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
    			return;
    	}else {
    		boolean exito = SesionesControl.guardarSesion(tipo2,dniSeleccionado);
    		//llamo a la funcion de guardar sesiones
	    	if (exito) {
	    		JOptionPane.showMessageDialog(this, "Sesión guardada correctamente");
	    		mostrarSesionesPacientes();
	    		}else {
	    			JOptionPane.showMessageDialog(this, "Hubo un error al guardar la sesión 2");
	    			}
    	}
    	
    });
    

   
    }
	
	
	
}	

