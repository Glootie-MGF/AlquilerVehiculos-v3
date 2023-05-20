package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LanzadorVentanaPrincipal extends Application {

	private static final String TITULO = "Vista Ventanas Alquiler de Vehículos";

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {

		try {

			/*
			 * FXMLLoader cargadorVentanaPrincipal = new
			 * FXMLLoader(LocalizadorRecursos.class.getResource(
			 * "vistas/VentanaPrincipal.fxml")); Parent raiz
			 * =cargadorVentanaPrincipal.load(); Scene escena = new Scene(raiz);
			 * escenarioPrincipal.setTitle("Vista Gráfica Alquiler de vehículos");
			 * escenarioPrincipal.setScene(escena); escenarioPrincipal.show();
			 */
			Controlador ventanaPrincipal = Controladores.get("vistas/VentanaPrincipal.fxml", TITULO, null);
			ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e));
			ventanaPrincipal.getEscenario().show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método (de clase) que se ocupa de preparar la ventana que queremos mostrar
	public static void comenzar() {
		launch(LanzadorVentanaPrincipal.class);
	}

	public static void confirmarSalida(Stage escenario, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				escenario)) {
			escenario.close();
		} else {
			e.consume();
		}
	}
}
