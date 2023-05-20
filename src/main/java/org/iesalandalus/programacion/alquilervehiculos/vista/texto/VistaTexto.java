package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public VistaTexto() {
		Accion.setVista(this);
	}

	@Override
	public void comenzar() {
		/*
		 * Mostrará el menú, leerá una opción de consola y la ejecutará. Repetirá este
		 * proceso mientras la opción elegida no sea la correspondiente a SALIR.
		 * Utilizará los correspondientes métodos de la clase Consola y llamará al
		 * método ejecutar.
		 */
		Accion accion;

		do {
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirAccion();
			try {
				Consola.mostrarCabecera(accion.toString());
				accion.ejecutar();
			} catch (IllegalArgumentException | OperationNotSupportedException e) {
				System.out.printf("%n%s", e.getMessage());
			}
		} while (accion != Accion.SALIR);

	}

	@Override
	public void terminar() {
		// Mostrará un mensaje de despedida por consola.
		System.out.print("La vista ha terminado, ¡hasta la próxima!.");
	}

	/*
	 * Métodos asociados a cada una de las opciones. Estos métodos deberán mostrar
	 * una cabecera informando en que opción nos encontramos, pedirnos los datos
	 * adecuados y realizar la operación adecuada llamando al método correspondiente
	 * de nuestro controlador. También deben controlar todas las posibles
	 * excepciones.
	 */
	public void insertarCliente() {
		Consola.mostrarCabecera("|| INSERTAR CLIENTE ||");
		try {
			getControlador().insertar(Consola.leerCliente());
			System.out.printf("%n¡Se ha insertado el cliente correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}

	}

	public void insertarVehiculo() {
		Consola.mostrarCabecera("|| INSERTAR VEHÍCULO ||");
		try {
			getControlador().insertar(Consola.leerVehiculo());
			System.out.printf("%n¡Se ha insertado el vehículo correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void insertarAlquiler() {
		Consola.mostrarCabecera("|| INSERTAR ALQUILER ||");
		try {
			getControlador().insertar(Consola.leerAlquiler());
			System.out.printf("%n¡Se ha insertado el alquiler correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarCliente() {
		Consola.mostrarCabecera("|| BUSCAR CLIENTE ||");
		try {
			System.out.printf("%n%s", getControlador().buscar(Consola.leerClienteDni()));
			System.out.printf("%n¡Se ha buscado el cliente correctamente!%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarVehiculo() {
		Consola.mostrarCabecera("|| BUSCAR VEHÍCULO ||");
		try {
			System.out.printf("%n%s%n", getControlador().buscar(Consola.leerVehiculoMatricula()));
			System.out.printf("%n¡Se ha buscado el vehículo correctamente!%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarAlquiler() {
		Consola.mostrarCabecera("|| BUSCAR ALQUILER ||");
		try {
			System.out.printf("%n%s", getControlador().buscar(Consola.leerAlquiler()));
			System.out.printf("%n¡Se ha buscado el alquiler correctamente!%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void modificarCliente() {
		Consola.mostrarCabecera("|| MODIFICAR CLIENTE ||");
		try {
			getControlador().modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.printf("%n¡Se ha modificado el cliente correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void devolverAlquilerCliente() {
		Consola.mostrarCabecera("|| DEVOLVER ALQUILER DE CLIENTE ||");
		try {
			getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.printf("%n¡Se ha cerrado el alquiler correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("|| DEVOLVER ALQUILER DE VEHÍCULO ||");
		try {
			getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.printf("%n¡Se ha cerrado el alquiler correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarCliente() {
		Consola.mostrarCabecera("|| BORRAR CLIENTE ||");
		try {
			getControlador().borrar(Consola.leerClienteDni());
			System.out.printf("%n¡Se ha borrado el cliente correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarVehiculo() {
		Consola.mostrarCabecera("|| BORRAR VEHÍCULO ||");
		try {
			getControlador().borrar(Consola.leerVehiculoMatricula());
			System.out.printf("%n¡Se ha borrado el vehículo correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarAlquiler() {
		Consola.mostrarCabecera("|| BORRAR ALQUILER ||");
		try {
			getControlador().borrar(Consola.leerAlquiler());
			System.out.printf("%n¡Se ha borrado el alquiler correctamente!%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarClientes() {
		Consola.mostrarCabecera("|| LISTA DE CLIENTES ||"); // Lista de clientes ordenada alfabéticamente por nombre y
		// en caso de nombres iguales ordenada por DNI.

		List<Cliente> listaAuxiliar = getControlador().getClientes();
		listaAuxiliar.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));

		System.out.printf(listaAuxiliar.isEmpty() ? "No hay clientes para listar aún." : "%n%s", listaAuxiliar);
	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("|| LISTA DE VEHÍCULOS ||"); // Lista de vehículos ordenada alfabéticamente por marca,
		// modelo y matrícula.

		List<Vehiculo> listaAuxiliar = getControlador().getVehiculos();
		listaAuxiliar.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
				.thenComparing(Vehiculo::getMatricula));

		System.out.print(listaAuxiliar.isEmpty() ? "No hay vehículos para listar aún." : listaAuxiliar);
	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("|| LISTA DE ALQUILERES ||"); // Lista de alquileres ordenada por fecha de alquiler y
		// por cliente (utilizando el mismo criterio anterior).

		Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);

		List<Alquiler> listaAuxiliarAlquiler = getControlador().getAlquileres();
		listaAuxiliarAlquiler.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
				comparadorCliente));

		System.out
		.print(listaAuxiliarAlquiler.isEmpty() ? "No hay alquileres para listar aún." : listaAuxiliarAlquiler);
	}

	public void listarAlquileresCliente() {
		Consola.mostrarCabecera("|| LISTAR ALQUILERES POR CLIENTE ||");
		try {
			Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre)
					.thenComparing(Cliente::getDni);

			List<Alquiler> listaAuxiliarAlquiler = getControlador().getAlquileres(Consola.leerClienteDni());
			listaAuxiliarAlquiler.sort(Comparator.comparing(Alquiler::getFechaAlquiler)
					.thenComparing(Alquiler::getCliente, comparadorCliente));

			System.out.println(
					listaAuxiliarAlquiler.isEmpty() ? "No hay alquileres para listar aún." : listaAuxiliarAlquiler);

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("|| LISTAR ALQUILERES POR VEHÍCULO ||");
		try {
			Comparator<Vehiculo> comparadorVehiculo = Comparator.comparing(Vehiculo::getMarca)
					.thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula);

			List<Alquiler> listaAuxiliarAlquiler = getControlador().getAlquileres(Consola.leerVehiculoMatricula());
			listaAuxiliarAlquiler.sort(Comparator.comparing(Alquiler::getFechaAlquiler)
					.thenComparing(Alquiler::getVehiculo, comparadorVehiculo));

			System.out.println(
					listaAuxiliarAlquiler.isEmpty() ? "No hay clientes para listar aún." : listaAuxiliarAlquiler);

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		
		Map<TipoVehiculo, Integer> mapa = inicializarEstadisticas();
		
		try {
			LocalDate mesDeseado = Consola.leerMes();
			
			for (Alquiler alquiler : getControlador().getAlquileres()) {
				
				if (alquiler.getFechaAlquiler().getMonth().equals(mesDeseado.getMonth())
						&& alquiler.getFechaAlquiler().getYear() == mesDeseado.getYear()) {
					
					mapa.put(TipoVehiculo.get(alquiler.getVehiculo()), mapa.get(TipoVehiculo.get(alquiler.getVehiculo())) + 1);
				}
			}
		} catch (IllegalArgumentException e) {
			mapa = null;
		}

		if (mapa == null) {
			System.out.println("¡Lo sentimos! No existen estadísticas para el mes deseado.");
			
		} else {
			for (Map.Entry<TipoVehiculo, Integer> entrada : mapa.entrySet()) {
				
				System.out.printf("%s alquilados: %s%n", entrada.getKey(), entrada.getValue());
			}
		}

	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {

		Map<TipoVehiculo, Integer> mapa = new EnumMap<>(TipoVehiculo.class);

		for (int i = 0; i < TipoVehiculo.values().length; i++) {
			mapa.put(TipoVehiculo.get(i), 0);
		}

		return mapa;
	}

}
