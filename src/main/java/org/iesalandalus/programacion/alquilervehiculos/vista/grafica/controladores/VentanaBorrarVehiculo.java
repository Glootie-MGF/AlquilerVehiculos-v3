package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
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

public class VentanaBorrarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica;
	private Vehiculo vehiculo;
	
    @FXML
    private Button btBorrar;

    @FXML
    private Button btCancelar;
    
    @FXML
    private Button btOk;

    @FXML
    private TextField tfMatricula;

    @FXML
	private void initialize() {

		// Inicializar vista gráfica
		vistaGrafica = VistaGrafica.getInstancia();
		
    }
    
    @FXML
    private void BorrarVehiculo(ActionEvent event) {

    	Alert alert = new Alert(AlertType.CONFIRMATION);
 	    alert.setTitle("Confirmación");
 	    alert.setHeaderText("¿Seguro que desea borrar el vehículo?");
 	    alert.setContentText("Se borrará permanentemente el vehículo seleccionado.");

 	    Optional<ButtonType> result = alert.showAndWait();
 	    if (result.isPresent() && result.get() == ButtonType.OK) {
 	        try {
 	            vistaGrafica.getControlador().borrar(vehiculo);
 	            Dialogos.mostrarDialogoInformacion("Borrado correcto", "Vehículo borrado correctamente", getEscenario());
 	        } catch (OperationNotSupportedException | IllegalArgumentException e) {
 	            Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
 	        }
 	    }
    }
    
    @FXML
    void buscarMatricula(ActionEvent event) {

    	Vehiculo vehiculo = null;
		String matricula = tfMatricula.getText().trim();
		
		if (!matricula.isEmpty()) {
			try {
				vehiculo = vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula));
				if (vehiculo != null) {

					// Colocar el borde en verde si existe el vehiculo
					tfMatricula.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				} else {
					Dialogos.mostrarDialogoError("Vehículo no encontrado", "No se ha encontrado ningún vehículo con la matrícula " + matricula, null);
					// Colocar el borde en rojo
					tfMatricula.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				}
			} catch (IllegalArgumentException e) {
				Dialogos.mostrarDialogoError("ERROR", e.getMessage(), null);
			}
		} else {
			Dialogos.mostrarDialogoError("Matrícula vacía", "Por favor, introduzca una matrícula válida.", null);
		}
		
		this.vehiculo = vehiculo;
    	
    }

    @FXML
    private void cancelar(ActionEvent event) {

    	// Cerramos ventana
    	((Stage) btCancelar.getParent().getScene().getWindow()).close();
    			
    	// Limpiamos los campos
    	tfMatricula.setText("");
    }

}
