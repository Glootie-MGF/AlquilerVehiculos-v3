package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;

public abstract class Vista {

	Controlador controlador;
	
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {  // Asignará el controlador pasado al atributo si éste no es nulo.
		
		if (controlador == null) {
			
			throw new NullPointerException("ERROR: El controlador NO puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public abstract void comenzar();
	
	public abstract void terminar();
}