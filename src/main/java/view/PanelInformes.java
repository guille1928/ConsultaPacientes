package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controller.DniControl;
import controller.DocumentosControl;
import controller.FacturasControl;
import controller.SesionesControl;
import controller.TextoComprobaciones;



public class PanelInformes extends JPanel{

 

private DniControl dniControl;


private JPanel crearBoton (JPanel contenidoPanel, String textoBoton) {
	JPanel panel = new JPanel(new BorderLayout());
	
	panel.add(contenidoPanel, BorderLayout.CENTER);

	JButton guardar = new JButton(textoBoton);
	String botonPacientes = "Guardar informe pacientes";
	String botonFacturas = "Guardar informe facturas";
	String botonSesiones = "Guardar informe sesiones";
	
	JTextField nombreArchivo= new JTextField(14);
	String archivoHolder = "nombre archivo";
	nombreArchivo.setText(archivoHolder);
    TextoComprobaciones.placeHolder(nombreArchivo, archivoHolder);
			
			
		//creo un event listener para el boton con 3 opciones de boton segun en que pane este
	guardar.addActionListener(e -> {
		String nom = nombreArchivo.getText().trim();
		//si el texto boton es igual a pacientes realizar una accion
		if (textoBoton.equals(botonPacientes.trim()) ) {
			if(nom.isEmpty() || nom.equals(archivoHolder)) {
				JOptionPane.showMessageDialog(this, "Tienes que introducir un nombre de archivo");
			}else {//creo un objeto de otra clase y llamo a su metodo 
				PanelPacientes panel1 = new PanelPacientes(dniControl);
				boolean exito = DocumentosControl.generaInforme(panel1.saberListaPacientes(), nom);	
				if(exito) {JOptionPane.showMessageDialog(this, "Se ha guardado correctamente");
				TextoComprobaciones.placeHolderBlanco(nombreArchivo, archivoHolder);	}	
				}			
		}else if (textoBoton.equals(botonFacturas.trim())) {
			System.out.println("Hola desde facturas");
			if(nom.isEmpty() || nom.equals(archivoHolder)) {
				JOptionPane.showMessageDialog(this, "Tienes que introducir un nombre de archivo");
			}else {
				//accion para boton facturas
				
				DniControl dnis = new DniControl();
				
				
				List<Object[]> listadni = new ArrayList<Object[]>();
				String dni = (String) dnis.getDniModel().getSelectedItem();
				listadni = FacturasControl.facturaPaciente(dni);
				DocumentosControl.generaInforme(listadni, nom);
			}
		}else {
			System.out.println("Adios desde sesiones");
			if(nom.isEmpty() || nom.equals(archivoHolder)) {
				JOptionPane.showMessageDialog(this, "Tienes que introducir un nombre de archivo");	
			}else {
				//accion para boton sesiones
			
			
				List<Object[]> listafc = new ArrayList<Object[]>();
				DniControl dnis = new DniControl();
				String dni = (String) dnis.getDniModel().getSelectedItem();
				
				
				listafc = SesionesControl.mostrarSesiones(dni);
				DocumentosControl.generaInforme(listafc, nom);
			}
		}
		
	});
	
	JPanel botonPanel = new JPanel();
	botonPanel.add(guardar);
	botonPanel.add(nombreArchivo);
	panel.add(botonPanel,BorderLayout.SOUTH);
	

	return panel;
}


public PanelInformes (DniControl dniControl) {

this.dniControl = dniControl;

this.setLayout(new BorderLayout());





JTabbedPane tabbedPane = new JTabbedPane();

tabbedPane.setPreferredSize(new Dimension(800,800));
//no puedo crear 2 veces el mixmo JCombox o se muestra aqui o en al panel principal de cards 
tabbedPane.addTab("Pacientes",crearBoton(new PanelPacientes(dniControl),"Guardar informe pacientes"));
tabbedPane.addTab("Facturas", crearBoton(new PanelFacturas(dniControl),"Guardar informe facturas"));
tabbedPane.addTab("Sesiones", crearBoton(new PanelSesiones(dniControl),"Guardar informe sesiones"));





this.add(tabbedPane,BorderLayout.CENTER);

  
    	
    		
	
	
	
	
	
}	
	
	
	
	
	
	
	
}
