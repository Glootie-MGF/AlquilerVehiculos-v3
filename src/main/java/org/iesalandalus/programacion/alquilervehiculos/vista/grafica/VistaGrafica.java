package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaGrafica extends Vista{

	private static VistaGrafica instancia;
	
	// Implementar patrón Singleton 
	public static VistaGrafica getInstancia() {
		if (instancia == null) {
			instancia = new VistaGrafica();
		}
		return instancia;
	}
	
		// Otra forma sería:
		// private static final VistaGrafica instancia = new VistaGrafica();
		// public static VistaGrafica getInstancia() {
		// 		return instancia; }
	
	@Override
	public void comenzar() {

		LanzadorVentanaPrincipal.comenzar();
		getControlador().terminar();
		
	}

	@Override
	public void terminar() {

		System.out.println("Chau Bacalau!");
		
	}
}
