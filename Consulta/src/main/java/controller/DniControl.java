package controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class DniControl {

	//
private DefaultComboBoxModel<String> dniModel = new DefaultComboBoxModel<String>();
    


public DniControl() {
	
	 dniModel = new DefaultComboBoxModel<>();
	 ActualizarDniModel();
	
}

// Método para obtener el modelo compartido, getter
public DefaultComboBoxModel<String> getDniModel() {
    return dniModel;
}




public void ActualizarDniModel () {
	
dniModel.removeAllElements();
List<String> listaDni = PacientesControl.saberDniPacientesDB();
if(listaDni==null || listaDni.isEmpty() ) {
	dniModel.addElement("No hay pacientes en la base de datos");
	
}else {
	System.out.println("Vale amigo hecho");
	for (String dni : listaDni) {
		dniModel.addElement(dni);
	}
	
	}	
    
}
	
	
	
	
}
