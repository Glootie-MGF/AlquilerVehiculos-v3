package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaInsertarAlquiler extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btAceptarAlquiler;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfMatricula;

	@FXML
	private void initialize() {
		// Inicializando la vista
		vistaGrafica = VistaGrafica.getInstancia();

		Controles.setInvalido(tfMatricula);
		tfMatricula.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatricula));
		Controles.setInvalido(tfDni);
		tfDni.textProperty()
		.addListener((observable, oldValue, newValue) -> Controles.validarCampoTexto(Cliente.ER_DNI, tfDni));

	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();

		// Limpiamos campos
		Controles.limpiarCamposTexto(tfDni, tfMatricula);
	}

	@FXML
	private void guardarAlquiler(ActionEvent event) {

		try {
			vistaGrafica.getControlador().insertar(new Alquiler(Cliente.getClienteConDni(tfDni.getText()),
					Vehiculo.getVehiculoConMatricula(tfMatricula.getText()), dpFechaAlquiler.getValue()));
			Dialogos.mostrarDialogoInformacion("Inserción correcta", "Alquiler insertado correctamente",
					getEscenario());
			((Stage) btAceptarAlquiler.getParent().getScene().getWindow()).close();
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}

	}

}
