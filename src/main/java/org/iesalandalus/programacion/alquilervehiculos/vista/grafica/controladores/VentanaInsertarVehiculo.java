package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaInsertarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btAceptarVehiculo;

	@FXML
	private Button btCancelar;

	@FXML
	private ComboBox<TipoVehiculo> cbTipoVehiculo;

	@FXML
	private TextField tfCilindrada;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfPlazas;

	@FXML
	private TextField tfPma;

	@FXML
	private void initialize() {
		// Inicializando la vista
		vistaGrafica = VistaGrafica.getInstancia();

		Controles.setInvalido(tfMarca);
		tfMarca.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Vehiculo.ER_MARCA, tfMarca));
		Controles.setValido(tfModelo);
		Controles.setInvalido(tfMatricula);
		tfMatricula.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatricula));

		// Inicializar los tipos de vehículos que aparecen en el ComboBox
		cbTipoVehiculo.getItems().addAll(TipoVehiculo.values());
	}


	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();

		// Limpiamos campos
		Controles.limpiarCamposTexto(tfMarca, tfModelo, tfMatricula, tfCilindrada, tfPlazas, tfPma);

	}

	@FXML
	private Vehiculo seleccionarTipoVehiculo(ActionEvent event) {

		TipoVehiculo tipoVehiculo = cbTipoVehiculo.getValue();
		Vehiculo vehiculo;
		/*
		 * Según el tipo de vehículo elegido se deshabilitarán los textField que no
		 * harían falta, por ejemplo si elegimos Turismo necesitaremos introducir
		 * "Cilindrada" pero no "Plazas" ni "pma"
		 */

		switch (tipoVehiculo) {
		case TURISMO:
			Controles.deshabilitarCamposTexto(tfPlazas, tfPma);
			Controles.habilitarCamposTexto(tfCilindrada);
			vehiculo = new Turismo(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfCilindrada.getText()),
					tfMatricula.getText());
			Dialogos.mostrarDialogoInformacion("Inserción correcta", "Turismo insertado correctamente", getEscenario());
			break;

		case AUTOBUS:
			Controles.deshabilitarCamposTexto(tfCilindrada, tfPma);
			Controles.habilitarCamposTexto(tfPlazas);
			vehiculo = new Autobus(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfPlazas.getText()),
					tfMatricula.getText());
			Dialogos.mostrarDialogoInformacion("Inserción correcta", "Autobús insertado correctamente", getEscenario());
			break;

		case FURGONETA:
			Controles.deshabilitarCamposTexto(tfCilindrada);
			Controles.habilitarCamposTexto(tfPlazas, tfPma);
			vehiculo = new Furgoneta(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfPma.getText()),
					Integer.parseInt(tfPlazas.getText()), tfMatricula.getText());
			Dialogos.mostrarDialogoInformacion("Inserción correcta", "Furgoneta insertada correctamente",
					getEscenario());
			break;

		default:
			throw new IllegalArgumentException("Tipo de vehículo NO válido.");
		}
		return vehiculo;
	}

	@FXML
	private void guardarVehiculo(ActionEvent event) {

		if (tfMarca.getStyleClass().contains("valido") && tfModelo.getStyleClass().contains("valido")
				&& tfMatricula.getStyleClass().contains("valido")) {
			try {
				Vehiculo vehiculo = seleccionarTipoVehiculo(event);
				vistaGrafica.getControlador().insertar(vehiculo);
				((Stage) btAceptarVehiculo.getParent().getScene().getWindow()).close();

			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
			}
		} else {
			Dialogos.mostrarDialogoError("ERROR", "Todos los campos deben de ser válidos", getEscenario());
		}
		// Limpiamos campos
		Controles.limpiarCamposTexto(tfMarca, tfModelo, tfMatricula, tfCilindrada, tfPlazas, tfPma);
	}

}
