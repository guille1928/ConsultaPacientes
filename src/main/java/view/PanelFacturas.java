package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.DniControl;
import controller.FacturasControl;
import controller.PacientesControl;
import controller.TextoComprobaciones;

public class PanelFacturas extends JPanel{
	
	private JTextField pagoTotal; 
	private JPanel tablaPanelfacturas;
	private String dniSeleccionado;
	private DniControl dniControl;

	private void mostrarFacturasPacientes () {
    	List<Object[]> listaFacturas = new ArrayList<Object[]>();
    	listaFacturas = FacturasControl.facturaPaciente(dniSeleccionado);
    	
    	if (listaFacturas.isEmpty()) {
    		JOptionPane.showMessageDialog(this, "El paciente no tiene facturas");		
    	}else {
    		// Limpiar el contenido anterior de tablaPanel
    		tablaPanelfacturas.removeAll();
    		//definimos los campos para realizar la tabla	
    		String [] nombreColumna = {"ID factura", "DNI Paciente", "Fecha de pago", "Pago total"};
    		
    		Object [][] datos = listaFacturas.toArray(new Object [0][]);
    		//creo la tabla con los valores y columnas
    		JTable tabla = new JTable(datos,nombreColumna);
    		//añado los datos al ScrollPane
    		JScrollPane scrollPane = new JScrollPane(tabla);
    		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		
    		JButton eliminarFactura = new JButton("Eliminar factura");
    		JButton modificarFactura = new JButton("Modificar factura");
    		 JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    		 formPanel.add(eliminarFactura);
    		 formPanel.add(modificarFactura);
    		//creo el listener para borrar la factura 
    		eliminarFactura.addActionListener(e-> {
    			int rowAfected = tabla.getSelectedRow();
    			if(rowAfected != -1) {
    				String id =(String) tabla.getValueAt(rowAfected, 0);
    				String dni = (String) tabla.getValueAt(rowAfected, 1);
    				//tengo que parsear el id a int
    				int id1 = Integer.parseInt(id);
    				int confirma = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres borrar la factura del paciente : " +dni);
    				//confirmamos con el usuario si quiere borrar al usuario y si elige si
    				if (confirma== JOptionPane.YES_OPTION) {
    					//llamamos a la consulta delete de la base de datos 
    					boolean exito = FacturasControl.borrarFactura(dni,id1);
    					if(exito) {
    						JOptionPane.showMessageDialog(this, "Se ha borrado la factura correctamente");
    						tablaPanelfacturas.removeAll();
    						
    					}
    					}
    				
    			}else {JOptionPane.showMessageDialog(this, "Tienes que seleccionar una factura de la lista");}
    	
    		tablaPanelfacturas.removeAll();
    		tablaPanelfacturas.revalidate();
    		tablaPanelfacturas.repaint();
    		
    		mostrarFacturasPacientes();
    		
    			
    		});
    		
    		modificarFactura.addActionListener(e-> {
    			//aqui modificaremos la factura 
    			String pagoHolder = "Pago en Euros";
    			int rowAfected = tabla.getSelectedRow();
    			if(rowAfected != -1) {
					String id =(String) tabla.getValueAt(rowAfected, 0);
					String dni = (String) tabla.getValueAt(rowAfected, 1);
					String pagoTotal1 = pagoTotal.getText().trim();
				
					//tengo que parsear el id a int
					int id1 = Integer.parseInt(id);
	      
					//llamo a la funcion para modificar la factura correspondiente 
					if(pagoTotal1.isEmpty() || pagoTotal1.equals(pagoHolder)) {
			        	   JOptionPane.showMessageDialog(this, " Debes introducir un valor de pago total  ");
			        	   
			        	  //aqui se me queda el codigo, no me da error pero no hacer nada y tengo un valor en el patoTotal1 y la fecha seleccionada 
			        	   
					}	else { 
								
			        	   	   boolean modificarFactura1 = FacturasControl.modificarFactura(dni, id1, pagoTotal1);
			        	       //si el boleano es true muestro un mensaje y las facturas 
				        	   if(modificarFactura1) {
				        		   JOptionPane.showMessageDialog(this, "Se ha modificado con éxito la factura al paciente con DNI : " +dniSeleccionado);
				        		   tablaPanelfacturas.removeAll();
				        		   
				        		   mostrarFacturasPacientes();
				        	   		}else { JOptionPane.showMessageDialog(this, "Ocurrió un error al modificar la factura");}
				        	   
				        	   }
    			}else {JOptionPane.showMessageDialog(this, "Tienes que seleccionar una factura de la lista");}
			           
    		});
    		//añado el boton de eliminar factura al panel
    		JPanel panelButton = new JPanel();
    		panelButton.add(scrollPane);
    		panelButton.add(formPanel);
    		
    			
    		
    		tablaPanelfacturas.removeAll();
    		tablaPanelfacturas.add(panelButton, BorderLayout.CENTER);
    			}
    	 // Refrescar el subpanel
    	tablaPanelfacturas.revalidate();
    	tablaPanelfacturas.repaint();
    }

    
	
public PanelFacturas (DniControl dniControl) {
	this.dniControl= dniControl;
	JComboBox<String> dniBox = new JComboBox<String>(dniControl.getDniModel());
	
    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Crear el subpanel para el formulario
    JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    formPanel.setPreferredSize(new Dimension(500, 150));

   
    // Crear los campos de formulario
  //  actualizarDniBox();
   
  
   pagoTotal = new JTextField(14);
   String pagoHolder= "Pago en Euros";
   pagoTotal.setText(pagoHolder);
   TextoComprobaciones.placeHolder(pagoTotal, pagoHolder);
   
   
   JButton guardar1 = new JButton("Guardar factura");
   JButton mostrar = new JButton("Mostrar facturas");
   tablaPanelfacturas = new JPanel(new BorderLayout());
   this.add(tablaPanelfacturas, BorderLayout.CENTER);
   
   
  
   //al importar el Jar para la fecha dinámica confecciono el datePicker para añadirlo al panel1 
  // Inicializar con una fecha específica
  // Crear las propiedades para el JDatePicker
   Properties properties = new Properties();
   properties.put("text.today", "Hoy");
   properties.put("text.month", "Mes");
   properties.put("text.year", "Año");
   UtilDateModel model = new UtilDateModel();
   model.setDate(2023, 10, 20); // Año, mes (0=Enero), día
   model.setSelected(true); // Seleccionar esta fecha por defecto
   JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
   JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new org.jdatepicker.impl.DateComponentFormatter());
   //necesitamos crear un comboBox para mostrar los dnis existentes al usuario 
   
   // Añadir los componentes al subpanel del formulario
   formPanel.add(new JLabel("DNI paciente : "));
   formPanel.add(dniBox);
   formPanel.add(new JLabel("Pago total : "));
   formPanel.add(pagoTotal);
   formPanel.add(new JLabel("Fecha de pago : "));
   formPanel.add(datePicker);
  
   
 
   // Crear un subpanel para los botones
   JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
   buttonPanel.add(guardar1);
   buttonPanel.add(mostrar);
   
   // Añadir el formulario y los botones al panel principal (parte superior)
   this.add(formPanel, BorderLayout.NORTH);
   this.add(buttonPanel, BorderLayout.EAST);
   
   // Restringir la entrada a números y punto decimal en el campo de pago total
       pagoTotal.addKeyListener(new KeyAdapter() {
       
       public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!Character.isDigit(c) && c != '.') {
               e.consume();
           }
       }
       });
       
       //creo evento para recoger que dni selecciona el usuario de la lista 
       dniBox.addActionListener(e ->{
    	   //selecciono el dni con un getSelectedItem
    	   dniSeleccionado= (String) dniBox.getSelectedItem();
    	   //refresco y renuevo el panel de la tabla 
    	
	 	  tablaPanelfacturas.removeAll();
		  tablaPanelfacturas.revalidate();
	 	  tablaPanelfacturas.repaint();
       });
       
       
       
       //creo el evento para guardar las facturas
       guardar1.addActionListener(e ->{
       String pagoTotal1 = pagoTotal.getText().trim();
       Date fechaSeleccionada = (Date) datePicker.getModel().getValue();
       //si hay fecha seleccionada creo la variable de pago y se la paso a la funcion guardarFactura
       if(fechaSeleccionada != null ) {
    	   
    	   if(pagoTotal1.isEmpty() || pagoTotal1.equals(pagoHolder)) {
    		   JOptionPane.showMessageDialog(this, "Tienes que introducir un valor en pago total");
    	   	}	else { 
    	   	   boolean guardado = FacturasControl.guardarFactura(pagoTotal1,fechaSeleccionada,dniSeleccionado);
    	       //si el boleano es true muestro un mensaje y las facturas 
        	   if(guardado) {
        		   JOptionPane.showMessageDialog(this, "Se guardado con éxito la factura al paciente con DNI : " +dniSeleccionado);
        		   mostrarFacturasPacientes();
        	   		}
        	   }
       }else {JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha válida");}
       
       });
       
     
       mostrar.addActionListener(e ->{ 
    	   mostrarFacturasPacientes(); });
       
  

}

	
	
}	
	
	
	

