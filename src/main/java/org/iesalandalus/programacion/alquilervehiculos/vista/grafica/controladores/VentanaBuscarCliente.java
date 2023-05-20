package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaBuscarCliente extends Controlador {
	
	private VistaGrafica vistaGrafica;

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private TableView<Cliente> tvClientes;

	@FXML
	private TableColumn<Cliente, String> tcDni;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;

	@FXML
	private TextField tfDniBuscar;

	@FXML
	private void initialize() {
		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// Inicializando la tabla de los clientes
		tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		tvClientes.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getClientes()));
	}

	@FXML
	private void buscarClienteDni(ActionEvent event) {

		String dni = tfDniBuscar.getText().trim();
		
		if (!dni.isEmpty()) {
			try {
				Cliente clienteBuscado = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(dni));
				
				if (clienteBuscado != null) {
					VentanaMostrarCliente ventanaMostrarCliente = (VentanaMostrarCliente) Controladores
							.get("vistas/VentanaMostrarCliente.fxml", "Mostrar cliente", getEscenario());
					ventanaMostrarCliente.getEscenario().setResizable(false);
					ventanaMostrarCliente.setCliente(clienteBuscado);
					ventanaMostrarCliente.getEscenario().showAndWait();
					tvClientes.getItems().clear();
					tvClientes.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getClientes()));
				} else {
					Dialogos.mostrarDialogoError("Cliente no encontrado", "No existe ningún cliente con ese DNI", null);
				}
			} catch (IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), null);
			}
		} else {
			Dialogos.mostrarDialogoError("DNI vacío", "Por favor, introduzca un DNI válido.", null);
		}
	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
		
		// Limpiamos los campos
		tfDniBuscar.setText("");
	}
}
