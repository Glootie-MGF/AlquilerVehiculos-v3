package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VentanaMostrarEstadisticas extends Controlador {

	private VistaGrafica vistaGrafica;

	@FXML
	private Button btCancelar;

	@FXML
	private PieChart pcEstadisticas;

	@FXML
	private void initialize() {
		// Inicialiar vista
		vistaGrafica = VistaGrafica.getInstancia();

		for (Map.Entry<String, Integer> entry : inicializarEstadisticas().entrySet()) {
			pcEstadisticas.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
		}

	}

	private Map<String, Integer> inicializarEstadisticas() {

		Map<String, Integer> estadisticas = new TreeMap<>();

		int turismos = 0;
		int furgonetas = 0;
		int autobuses = 0;

		List<Alquiler> alquileres = vistaGrafica.getControlador().getAlquileres();

		for (Alquiler alquiler : alquileres) {

			Vehiculo vehiculo = alquiler.getVehiculo();

			if (vehiculo instanceof Turismo) {
				turismos += 1;
			} else if (vehiculo instanceof Autobus) {
				autobuses += 1;
			} else if (vehiculo instanceof Furgoneta) {
				furgonetas += 1;
			}
		}

		estadisticas.put(
				String.format("Turismos: %s%%", turismos == 0 ? turismos : turismos * 100 / alquileres.size()),
				turismos);
		estadisticas.put(
				String.format("Autobuses: %s%%", autobuses == 0 ? autobuses : autobuses * 100 / alquileres.size()),
				autobuses);
		estadisticas.put(
				String.format("Furgonetas: %s%%", furgonetas == 0 ? furgonetas : furgonetas * 100 / alquileres.size()),
				furgonetas);

		return estadisticas;
	}

	@FXML
	private void cancelar() {
		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}

}