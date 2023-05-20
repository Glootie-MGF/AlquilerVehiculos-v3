package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles.FormateadorCeldaFecha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaMostrarAlquilerVehiculo extends Controlador {

	@FXML
	private Button btCancelar;

	@FXML
	private TableColumn<Alquiler, Cliente> tcCliente;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaAlquiler;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, Integer> tcPrecio;

	@FXML
	private TableColumn<Alquiler, Vehiculo> tcVehiculo;

	@FXML
	private TextField tfDniBuscar;

	@FXML
	private TextField tfMatriculaBuscar;

	@FXML
	private TableView<Alquiler> tvAlquileresVehiculo;

	@FXML
	private void initialize() {

		// Inicializar tabla de alquileres
		tcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tcVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
		tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tcFechaAlquiler.setCellFactory(cell -> new FormateadorCeldaFecha());
		tcFechaDevolucion.setCellFactory(cell -> new FormateadorCeldaFecha());

	}

	@FXML
	void cancelar(ActionEvent event) {
		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}

	public void setAlquileres(List<Alquiler> alquileres) {

		tvAlquileresVehiculo.getItems().setAll(alquileres);

	}

}
