package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class UtilidadesVehiculo {

	// Clase utilitaria para evitar duplicar el código y 
	// reutilizar la lógica de obtención de los valores de 
	// PMA, plazas y cilindrada para los vehículos en ambas clases.
	
	public static String getPma(Vehiculo vehiculo) {
		String pma = "-";
		if (vehiculo instanceof Furgoneta furgoneta) {
			pma = Integer.toString(furgoneta.getPma());
		}
		return pma;
	}

	public static String getPlazas(Vehiculo vehiculo) {
		String plazas = "-";
		if (vehiculo instanceof Furgoneta furgoneta) {
			plazas = Integer.toString(furgoneta.getPlazas());
		}
		if (vehiculo instanceof Autobus autobus) {
			plazas = Integer.toString(autobus.getPlazas());
		}
		return plazas;
	}

	public static String getCilindrada(Vehiculo vehiculo) {
		String cilindrada = "-";
		if (vehiculo instanceof Turismo turismo) {
			cilindrada = Integer.toString(turismo.getCilindrada());
		}
		return cilindrada;
	}
}
