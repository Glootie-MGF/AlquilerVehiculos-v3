package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaInsertarCliente extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btAceptarCliente;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	@FXML
	private void initialize() {

		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// Comprobamos que cumpla con la Expresión Regular
		Controles.setInvalido(tfNombre);
		tfNombre.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Cliente.ER_NOMBRE, tfNombre));
		Controles.setInvalido(tfDni);
		tfDni.textProperty()
		.addListener((observable, oldValue, newValue) -> Controles.validarCampoTexto(Cliente.ER_DNI, tfDni));
		Controles.setInvalido(tfTelefono);
		tfTelefono.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Cliente.ER_TELEFONO, tfTelefono));
	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}

	@FXML
	private void guardarCliente(ActionEvent event) {

		if (tfNombre.getStyleClass().contains("valido") && tfDni.getStyleClass().contains("valido")
				&& tfTelefono.getStyleClass().contains("valido")) {
			try {
				vistaGrafica.getControlador()
				.insertar(new Cliente(tfNombre.getText(), tfDni.getText(), tfTelefono.getText()));
				Dialogos.mostrarDialogoInformacion("Inserción correcta", "Cliente insertado correctamente",
						getEscenario());
				((Stage) btAceptarCliente.getParent().getScene().getWindow()).close();
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
			}
		} else {
			Dialogos.mostrarDialogoError("ERROR", "Todos los campos deben de ser válidos", getEscenario());
		}

	}

}
