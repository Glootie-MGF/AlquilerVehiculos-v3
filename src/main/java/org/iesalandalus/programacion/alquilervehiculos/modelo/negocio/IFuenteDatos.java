package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

public interface IFuenteDatos {

	// Métodos
	IClientes crearClientes();

	IVehiculos crearVehiculos();

	IAlquileres crearAlquileres();

}