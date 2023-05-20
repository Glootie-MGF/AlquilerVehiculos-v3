package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVista;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class MainApp {

	public static void main(String[] args) {
		/*
		 * Crea la clase MainApp con un único método main que será el método de entrada
		 * a nuestra aplicación. Este método simplemente creará una vista, un modelo y
		 * un controlador, pasándoles las instancias antes creadas. Luego simplemente
		 * invocará al método comenzar del controlador. Realiza las pruebas que estimes
		 * oportunas y cuando consideres quetodo es correcto, realiza el último commit y
		 * seguidamente realiza el push a tu repositorio remoto.
		 */

		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		Vista vista = procesarArgumentos(args);
		Controlador controlador = new Controlador(modelo, vista);

		vista.setControlador(controlador);
		controlador.comenzar();

		// Vista vista = FactoriaVista.TEXTO.crear();
		// Vista vista = FactoriaVista.GRAFICA.crear();
	}

	private static Vista procesarArgumentos(String[] args) {
		
		Vista vista = FactoriaVista.GRAFICA.crear();
		int indice = (args.length - 1);
		
		if (args[indice].equals("vGrafica")) {
			vista = FactoriaVista.GRAFICA.crear();
			
		} else if (args[indice].equals("vTexto")) {
			vista = FactoriaVista.TEXTO.crear();
		}
		
		return vista;
	}
}
