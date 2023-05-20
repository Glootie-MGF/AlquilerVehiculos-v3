package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.UtilidadesVehiculo;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaBuscarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btBuscarVehiculo;

	@FXML
	private Button btCancelar;

	@FXML
	private TableColumn<Vehiculo, String> tcCilindrada;

	@FXML
	private TableColumn<Vehiculo, String> tcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcModelo;

	@FXML
	private TableColumn<Vehiculo, String> tcPlazas;

	@FXML
	private TableColumn<Vehiculo, String> tcPma;

	@FXML
	private TextField tfMatriculaBuscar;

	@FXML
	private TableView<Vehiculo> tvVehiculos;

	@FXML
	private void initialize() {
		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// Inicializando la tabla de los vehículos
		tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		tcPlazas.setCellValueFactory(fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getPlazas(fila.getValue())));
		tcPma.setCellValueFactory(fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getPma(fila.getValue())));
		tcCilindrada.setCellValueFactory(
				fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getCilindrada(fila.getValue())));

		tvVehiculos.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getVehiculos()));
	}

	@FXML
	private void buscarVehiculoConMatricula(ActionEvent event) {

		Vehiculo vehiculoBuscado = null;
		String matricula = tfMatriculaBuscar.getText();

		if (!matricula.isEmpty()) {
			try {
				vehiculoBuscado = vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula));

				if (vehiculoBuscado != null) {
					VentanaMostrarVehiculo ventanaMostrarVehiculo = (VentanaMostrarVehiculo) Controladores
							.get("vistas/VentanaMostrarVehiculo.fxml", "Mostrar Vehículo", getEscenario());
					ventanaMostrarVehiculo.getEscenario().setResizable(false);
					ventanaMostrarVehiculo.setVehiculo(vehiculoBuscado);
					ventanaMostrarVehiculo.getEscenario().showAndWait();
					tvVehiculos.getItems().clear();
					tvVehiculos
					.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getVehiculos()));

				} else {
					Dialogos.mostrarDialogoError("Vehículo no encontrado",
							"No existe ningún vehículo con esa matrícula", null);
				}
			} catch (IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("Matrícula vacía", "Por favor, introduzca una matrícula válida.", null);
			}

		}
	}

	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();

		// Limpiamos los campos
		tfMatriculaBuscar.setText("");
	}

}
