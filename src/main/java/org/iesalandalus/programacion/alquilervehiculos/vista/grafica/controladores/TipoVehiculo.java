package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {

	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {

		this.nombre = nombre;
	}

	public static TipoVehiculo get(Vehiculo vehiculo) {

		TipoVehiculo tipo = null;

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El vehículo NO puede ser nulo.");
		}
		if (vehiculo instanceof Turismo) {
			tipo = TipoVehiculo.TURISMO;
		} else if (vehiculo instanceof Autobus) {
			tipo = TipoVehiculo.AUTOBUS;
		} else if (vehiculo instanceof Furgoneta) {
			tipo = TipoVehiculo.FURGONETA;
		}
		return tipo;
	}

	@Override
	public String toString() {
		return String.format("%d. %s", ordinal() + 1, nombre);
	}
}
