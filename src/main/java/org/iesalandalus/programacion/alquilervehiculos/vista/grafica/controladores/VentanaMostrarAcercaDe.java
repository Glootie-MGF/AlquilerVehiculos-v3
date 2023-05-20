package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VentanaMostrarAcercaDe {

    @FXML
    private Button btCancelar;

    @FXML
	void cancelar(ActionEvent event) {
		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}

}

