package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VentanaModificarCliente extends Controlador {

	private VistaGrafica vistaGrafica;
	private Cliente cliente;

	@FXML
	private Button btBorrarCliente;

	@FXML
	private Button btBuscarCliente;

	@FXML
	private Button btCancelar;

	@FXML
	// Botón para aceptar la modificación
	private Button btModificarCliente;

	@FXML
	private TextField tfDniCliente;

	@FXML
	private TextField tfNombreCliente;

	@FXML
	private TextField tfTelefonoCliente;

	@FXML
	private void initialize() {

		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// Comprobamos que cumpla con la Expresión Regular los datos modificados
		Controles.setInvalido(tfNombreCliente);
		tfNombreCliente.textProperty().addListener(
				(observable, oldValue, newValue) -> Controles.validarCampoTexto(Cliente.ER_NOMBRE, tfNombreCliente));

		Controles.setInvalido(tfTelefonoCliente);
		tfTelefonoCliente.textProperty().addListener((observable, oldValue, newValue) -> Controles
				.validarCampoTexto(Cliente.ER_TELEFONO, tfTelefonoCliente));
	}

	@FXML
	private void borrarCliente(ActionEvent event) {

	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Confirmación");
	    alert.setHeaderText("¿Seguro que desea borrar el cliente?");
	    alert.setContentText("Se borrará permanentemente el cliente seleccionado.");

	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        try {
	            vistaGrafica.getControlador().borrar(cliente);
	            Dialogos.mostrarDialogoInformacion("Borrado correcto", "Cliente borrado correctamente", getEscenario());
	        } catch (OperationNotSupportedException | IllegalArgumentException e) {
	            Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
	        }
	    }
	}


	@FXML
	private void buscarCliente(ActionEvent event) {

		Cliente cliente = null;
		String dni = tfDniCliente.getText().trim();
		
		if (!dni.isEmpty()) {
			try {
				cliente = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(dni));
				if (cliente != null) {
					// Si existe el cliente que se ponga en verde el cuadro del DNI y se rellene
					// el nombre y teléfono almacenados para poder modificarlos
					tfNombreCliente.setText(cliente.getNombre());
					tfTelefonoCliente.setText(cliente.getTelefono());
					// Colocar el borde en verde
					tfDniCliente.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				} else {
					Dialogos.mostrarDialogoError("Cliente no encontrado", "No se ha encontrado ningún cliente con el DNI " + dni, null);
					// Colocar el borde en rojo
					tfDniCliente.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				}
			} catch (IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), null);
			}
		} else {
			Dialogos.mostrarDialogoError("DNI vacío", "Por favor, introduzca un DNI válido.", null);
		}
		
		this.cliente = cliente;
	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
		
		// Limpiamos los campos
		tfDniCliente.setText("");
		tfNombreCliente.setText("");
		tfTelefonoCliente.setText("");
		tfDniCliente.setBorder(Border.EMPTY);
	}

	@FXML
	private void modificarCliente(ActionEvent event) {

		// Botón para aceptar y guardar los cambios
		
		if (tfNombreCliente.getStyleClass().contains("valido") && tfTelefonoCliente.getStyleClass().contains("valido")) {
			try {
				vistaGrafica.getControlador().modificar(cliente, tfNombreCliente.getText(), tfTelefonoCliente.getText());
				
				Dialogos.mostrarDialogoInformacion("Modificacion correcta", "Cliente modificado correctamente",
						getEscenario());
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
			}
		} else {
			Dialogos.mostrarDialogoError("ERROR", "Todos los campos deben ser válidos", getEscenario());
		}

	}

}
