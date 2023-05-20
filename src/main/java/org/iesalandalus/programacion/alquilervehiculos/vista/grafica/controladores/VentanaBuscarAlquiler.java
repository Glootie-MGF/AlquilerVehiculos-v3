package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles.FormateadorCeldaFecha;
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

public class VentanaBuscarAlquiler extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btBuscarAlquilerCliente;

	@FXML
	private Button btBuscarAlquilerVehiculo;

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
	private TableView<Alquiler> tvAlquileres;

	@FXML
	private void initialize() {
		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();

		// Inicializar tabla de alquileres 
		tcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tcVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
		tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tcFechaAlquiler.setCellFactory(cell -> new FormateadorCeldaFecha());
		tcFechaDevolucion.setCellFactory(cell -> new FormateadorCeldaFecha());

		tvAlquileres.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getAlquileres()));

	}

	@FXML
	void buscarAlquilerConDni(ActionEvent event) {

		String dni = tfDniBuscar.getText();

        if (!dni.isEmpty()) {
            Cliente clienteBuscado = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(dni));

            if (clienteBuscado != null) {
                VentanaMostrarAlquilerCliente ventanaMostrarAlquilerCliente = (VentanaMostrarAlquilerCliente) Controladores
                        .get("vistas/VentanaMostrarAlquilerCliente.fxml", "Mostrar Alquileres Cliente", getEscenario());
                ventanaMostrarAlquilerCliente.getEscenario().setResizable(false);
                ventanaMostrarAlquilerCliente.setAlquileres(vistaGrafica.getControlador().getAlquileres(clienteBuscado));
                ventanaMostrarAlquilerCliente.getEscenario().showAndWait();
                tvAlquileres.getItems().clear();
                tvAlquileres.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getAlquileres()));
            } else {
                Dialogos.mostrarDialogoError("Alquiler no encontrado",
                        "No existe ningún alquiler registrado con ese DNI.", null);
            }
        } else {
            Dialogos.mostrarDialogoError("Campo vacío", "Por favor, introduzca un DNI válido.", null);
        }
		
		
	}

	 @FXML
	    void buscarAlquilerConMatricula(ActionEvent event) {
	        String matricula = tfMatriculaBuscar.getText();

	        if (!matricula.isEmpty()) {
	            Vehiculo vehiculoBuscado = vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula));

	            if (vehiculoBuscado != null) {
	                VentanaMostrarAlquilerVehiculo ventanaMostrarAlquilerVehiculo = (VentanaMostrarAlquilerVehiculo) Controladores
	                        .get("vistas/VentanaMostrarAlquilerVehiculo.fxml", "Mostrar Alquileres Vehículo", getEscenario());
	                ventanaMostrarAlquilerVehiculo.getEscenario().setResizable(false);
	                ventanaMostrarAlquilerVehiculo.setAlquileres(vistaGrafica.getControlador().getAlquileres(vehiculoBuscado));
	                ventanaMostrarAlquilerVehiculo.getEscenario().showAndWait();
	                tvAlquileres.getItems().clear();
	                tvAlquileres.setItems(FXCollections.observableArrayList(vistaGrafica.getControlador().getAlquileres()));
	            } else {
	                Dialogos.mostrarDialogoError("Alquiler no encontrado",
	                        "No existe ningún alquiler registrado con esa matrícula.", null);
	            }
	        } else {
	            Dialogos.mostrarDialogoError("Campo vacío", "Por favor, introduzca una matrícula válida.", null);
	        }
	    }

	@FXML
	void cancelar(ActionEvent event) {
		
		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();

		// Limpiamos campos
		Controles.limpiarCamposTexto(tfDniBuscar, tfMatriculaBuscar);
	}

}
