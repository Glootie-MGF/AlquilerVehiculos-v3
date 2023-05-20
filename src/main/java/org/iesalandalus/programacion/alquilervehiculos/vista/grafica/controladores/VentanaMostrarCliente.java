package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaMostrarCliente extends Controlador {

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
    private void initialize() {
        // Inicializar tabla de clientes
    	tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    public void setCliente(Cliente cliente) {
        tvClientes.getItems().clear();
        if (cliente != null) {
            tvClientes.getItems().add(cliente);
        }
    }
	@FXML
	private void cancelar(ActionEvent event) {

		// Cerramos ventana
		((Stage) btCancelar.getParent().getScene().getWindow()).close();
	}
}

