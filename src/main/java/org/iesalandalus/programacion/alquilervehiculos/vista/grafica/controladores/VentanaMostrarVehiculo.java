package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.UtilidadesVehiculo;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaMostrarVehiculo extends Controlador {

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
	private TableView<Vehiculo> tvVehiculos;


	@FXML
	private void initialize() {
		// Inicializando la tabla de vehículos
		tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		tcPlazas.setCellValueFactory(fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getPlazas(fila.getValue())));
		tcPma.setCellValueFactory(fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getPma(fila.getValue())));
		tcCilindrada.setCellValueFactory(fila -> new SimpleObjectProperty<>(UtilidadesVehiculo.getCilindrada(fila.getValue())));

	}

	public void setVehiculo(Vehiculo vehiculoBuscado) {

		tvVehiculos.getItems().clear();
		if (vehiculoBuscado != null) {
			tvVehiculos.getItems().add(vehiculoBuscado);
		}
	}

	@FXML
	private void cancelar(ActionEvent event) {
		// Cerramos la ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}
	
}
