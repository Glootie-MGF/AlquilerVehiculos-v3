package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {

	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre NO puede ser nulo.");
		}
		if (nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre NO puede estar en blanco.");
		}
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {

		return ordinal >= 0 && TipoVehiculo.values().length > ordinal;
	}

	public static TipoVehiculo get(int ordinal) {

		if (!(esOrdinalValido(ordinal))) {
			throw new IllegalArgumentException("ERROR: El ordinal NO es válido.");
		}
		return TipoVehiculo.values()[ordinal];
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
