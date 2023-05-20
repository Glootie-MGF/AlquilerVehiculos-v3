package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VentanaModificarAlquiler extends Controlador {

	private VistaGrafica vistaGrafica;
	private Alquiler alquiler;
	private Cliente cliente;

	@FXML
	private Button btBorrarAlquiler;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btDevolverAlquiler;

	@FXML
	private Button btOk; // Método buscarAlquiler

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfMatricula;

	@FXML
	private void initialize() {

		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// El botón 'btDevolverAlquiler' estará deshabilitado hasta que se compruebe que
		// el alquiler EXISTE

		activarFechaDevolucion(false);
	}

	@FXML
	private void borrarAlquiler(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que desea borrar el alquiler?");
		alert.setContentText("Se borrará permanentemente el alquiler seleccionado.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				vistaGrafica.getControlador().borrar(alquiler);
				Dialogos.mostrarDialogoInformacion("Borrado correcto", "Alquiler borrado correctamente",
						getEscenario());
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
			}
		}
	}

	@FXML
	private void buscarAlquiler(ActionEvent event) {

		try {
			alquiler = vistaGrafica.getControlador().buscar(new Alquiler(Cliente.getClienteConDni(tfDni.getText()),
					Vehiculo.getVehiculoConMatricula(tfMatricula.getText()), dpFechaAlquiler.getValue()));
			cliente = alquiler.getCliente();
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}
		if (alquiler != null) {
			tfMatricula.setBorder(new Border(
					new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			tfDni.setBorder(new Border(
					new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			dpFechaAlquiler.setBorder(new Border(
					new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			activarFechaDevolucion(true); 

		} else {
			Dialogos.mostrarDialogoError("Alquiler no encontrado", "No existe ningún alquiler con esos datos", null);
			tfMatricula.setBorder(new Border(
					new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			tfDni.setBorder(new Border(
					new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			activarFechaDevolucion(false);
		}
	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();

		// Limpiamos campos
		Controles.limpiarCamposTexto(tfDni, tfMatricula);
	}

	@FXML
	private void devolverAlquiler(ActionEvent event) {

		LocalDate fechaDevolucion = dpFechaDevolucion.getValue();

		try {
			vistaGrafica.getControlador().devolver(cliente, fechaDevolucion);
			Dialogos.mostrarDialogoInformacion("Devolución correcta", "El vehículo ha sido devuelto correctamente",
					getEscenario());
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}

	}

	private void activarFechaDevolucion(boolean activacion) {

		if (activacion) {
			btDevolverAlquiler.setDisable(false);
			dpFechaDevolucion.setDisable(false);

		} else {
			btDevolverAlquiler.setDisable(true);
			dpFechaDevolucion.setDisable(true);
		}
	}

}
