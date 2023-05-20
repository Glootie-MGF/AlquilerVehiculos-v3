package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.LanzadorVentanaPrincipal;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class VentanaPrincipal extends Controlador {

	// private VistaGrafica vistaGrafica;

	@FXML
	private Button btAniadirAlquiler;

	@FXML
	private Button btAniadirCliente;

	@FXML
	private Button btAniadirVehiculo;

	@FXML
	private Button btListarAlquiler;

	@FXML
	private Button btListarCliente;

	@FXML
	private Button btListarVehiculo;

	@FXML
	private Button btModificarAlquiler;

	@FXML
	private Button btModificarCliente;

	@FXML
	private Button btModificarVehiculo;

	@FXML
	private MenuItem mbAcercaDe;

	@FXML
	private Menu mbAlquileres;

	@FXML
	private Menu mbAyuda;

	@FXML
	private Menu mbClientes;

	@FXML
	private MenuItem mbEstadisticas;

	@FXML
	private MenuItem mbSalir;

	@FXML
	private MenuItem mbVerClientes;

	@FXML
	private void initialize() {

		// Inicializar vista gráfica
		// vistaGrafica = VistaGrafica.getInstancia();

	}

	// ---------- AÑADIR ----------//
	@FXML
	void aniadirAlquiler(ActionEvent event) {

		// Ventana Insertar Alquiler Nuevo
		VentanaInsertarAlquiler ventanaInsertarAlquiler = (VentanaInsertarAlquiler) Controladores
				.get("vistas/VentanaInsertarAlquiler.fxml", "Nuevo Alquiler", getEscenario());
		ventanaInsertarAlquiler.getEscenario().setResizable(false);
		ventanaInsertarAlquiler.getEscenario().showAndWait();
	}

	@FXML
	void aniadirCliente(ActionEvent event) {

		// Ventana Insertar Cliente Nuevo
		VentanaInsertarCliente ventanaInsertarCliente = (VentanaInsertarCliente) Controladores
				.get("vistas/VentanaInsertarCliente.fxml", "Nuevo Cliente", getEscenario());
		ventanaInsertarCliente.getEscenario().setResizable(false);
		ventanaInsertarCliente.getEscenario().showAndWait();
	}

	@FXML
	void aniadirVehiculo(ActionEvent event) {

		// Ventana Insertar Vehículo Nuevo
		VentanaInsertarVehiculo ventanaInsertarVehiculo = (VentanaInsertarVehiculo) Controladores
				.get("vistas/VentanaInsertarVehiculo.fxml", "Nuevo Vehículo", getEscenario());
		ventanaInsertarVehiculo.getEscenario().setResizable(false);
		ventanaInsertarVehiculo.getEscenario().showAndWait();

	}

	// ---------- ESTADÍSTICAS ----------//
	@FXML
	void estadisticasAlquileres(ActionEvent event) {

		Parent raiz = null;
		try {
			raiz = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/VentanaMostrarEstadisticas.fxml"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Scene escena = new Scene(raiz);
		Stage escenario = new Stage();
		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.showAndWait();

	}

	// ---------- LISTAR ----------//
	@FXML
	void listarAlquiler(ActionEvent event) {

		// Ventana para Buscar Alquileres
		VentanaBuscarAlquiler ventanaBuscarAlquiler = (VentanaBuscarAlquiler) Controladores
				.get("vistas/VentanaBuscarAlquiler.fxml", "Listado de Alquileres", getEscenario());
		ventanaBuscarAlquiler.getEscenario().setResizable(false);
		ventanaBuscarAlquiler.getEscenario().showAndWait();

	}

	@FXML
	void listarCliente(ActionEvent event) {

		// Ventana para Buscar Clientes
		VentanaBuscarCliente ventanaBuscarCliente = (VentanaBuscarCliente) Controladores
				.get("vistas/VentanaBuscarCliente.fxml", "Listado de Clientes", getEscenario());
		ventanaBuscarCliente.getEscenario().setResizable(false);
		ventanaBuscarCliente.getEscenario().showAndWait();
	}

	@FXML
	void listarVehiculo(ActionEvent event) {

		// Ventana para Buscar Vehículos
		VentanaBuscarVehiculo ventanaBuscarVehiculo = (VentanaBuscarVehiculo) Controladores
				.get("vistas/VentanaBuscarVehiculo.fxml", "Listado de Vehículos", getEscenario());
		ventanaBuscarVehiculo.getEscenario().setResizable(false);
		ventanaBuscarVehiculo.getEscenario().showAndWait();
	}

	// ---------- MODIFICAR ----------//
	@FXML
	void modificarAlquiler(ActionEvent event) {

		// Ventana para BORRAR Y DEVOLVER un alquiler
		VentanaModificarAlquiler ventanaModificarAlquiler = (VentanaModificarAlquiler) Controladores
				.get("vistas/VentanaModificarAlquiler.fxml", "Modificar Alquiler", getEscenario());
		ventanaModificarAlquiler.getEscenario().setResizable(false);
		ventanaModificarAlquiler.getEscenario().showAndWait();

	}

	@FXML
	void modificarCliente(ActionEvent event) {

		// Ventana para BORRAR o MODIFICAR clientes
		VentanaModificarCliente ventanaModificarCliente = (VentanaModificarCliente) Controladores
				.get("vistas/VentanaModificarCliente.fxml", "Modificar Cliente", getEscenario());
		ventanaModificarCliente.getEscenario().setResizable(false);
		ventanaModificarCliente.getEscenario().showAndWait();
	}

	@FXML
	void modificarVehiculo(ActionEvent event) {

		// Ventana para BORRAR Vehículos
		VentanaBorrarVehiculo ventanaBorrarVehiculo = (VentanaBorrarVehiculo) Controladores
				.get("vistas/VentanaBorrarVehiculo.fxml", "Borrar Vehículo", getEscenario());
		ventanaBorrarVehiculo.getEscenario().setResizable(false);
		ventanaBorrarVehiculo.getEscenario().showAndWait();

	}

	// ---------- OTROS ----------//
	@FXML
	void mostrarMiInfo(ActionEvent event) {

		// mbAcercaDe
		Parent raiz = null;
		try {
			raiz = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/VentanaMostrarAcercaDe.fxml"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Scene escena = new Scene(raiz);
		Stage escenario = new Stage();
		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.showAndWait();

	}

	@FXML
	void terminarPrograma(ActionEvent event) {
		// mbSalir
		LanzadorVentanaPrincipal.confirmarSalida(getEscenario(), null);
	}

}
