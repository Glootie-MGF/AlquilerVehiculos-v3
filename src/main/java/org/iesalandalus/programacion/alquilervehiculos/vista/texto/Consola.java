package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_MES = "MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	// Constructor por defecto
	private Consola() {

	}

	// Métodos
	public static void mostrarCabecera(String mensaje) {
		/*
		 * Mostrará por pantalla el mensaje pasado por parámetro y luego mostrará un
		 * subrayado compuesto de guiones con su misma longitud.
		 */
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.printf(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static void mostrarMenuAcciones() {
		/*
		 * Mostrará una cabecera informando del cometido de la aplicación y mostrará las
		 * diferentes opciones del menú.
		 */
		mostrarCabecera("BIENVENIDO, ¿QUÉ DESEA HACER?");
		
		for (Accion opcion : Accion.values()) {
			System.out.printf("%n%s%n", opcion);
		}
	}

	public static Accion elegirAccion() {
		/*
		 * Leerá un entero (utilizando el método anteriormente creado) asociado a la
		 * opción y devolverá la opción correspondiente. Si el entero introducido no se
		 * corresponde con ninguna opción deberá volver a leerlo hasta que éste sea
		 * válido.
		 */
		int eleccion = 0;
		Accion opcionElegida = null;

		do {
			eleccion = leerEntero("Introduzca una opción, por favor: ");
			try {
				opcionElegida = Accion.get(eleccion);
			} catch (IllegalArgumentException e) {
				System.out.printf("%s", e.getMessage());
			}
		} while (opcionElegida == null);

		return opcionElegida;
	}

	private static String leerCadena(String mensaje) {

		System.out.printf("%n%s", mensaje);
		return Entrada.cadena();
	}

	private static Integer leerEntero(String mensaje) {
		System.out.printf("%n%s", mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje, String patron) {

		LocalDate fecha = null;

		System.out.printf("%n%s (%s)",mensaje, patron);

		try {
			if (patron.equals(PATRON_FECHA)) {
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			}
			if (patron.equals(PATRON_MES)) {
				fecha = LocalDate.parse("01/"+ Entrada.cadena(), FORMATO_FECHA);
			}

		} catch (DateTimeParseException e) {
			System.out.printf("%nFormato incorrecto para la fecha.");
		}

		return fecha;
	}

	public static Cliente leerCliente() {
		return new Cliente(leerNombre(), leerCadena("Introduzca DNI: "), leerTelefono());
	}

	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduzca DNI: "));
	}

	public static String leerNombre() {
		return leerCadena("Introduzca el nombre: ");
	}

	public static String leerTelefono() {
		return leerCadena("Introduzca el teléfono de contacto: ");
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("TIPOS DE VEHÍCULOS DISPONIBLES");

		for (TipoVehiculo tipos : TipoVehiculo.values()) {
			System.out.printf("%s%n", tipos);
		}
	}

	private static TipoVehiculo elegirTipoVehiculo() {

		return TipoVehiculo.get(leerEntero("Introduzca el tipo de vehículo deseado: ")- 1);
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {

		String marca = leerCadena("Introduzca la marca del vehículo: ");
		String modelo = leerCadena("Introduzca el modelo del vehículo: ");
		String matricula = leerCadena("Introduzca la matrícula del vehículo: ");

		Vehiculo vehiculoLeer = null;

		if (tipoVehiculo == TipoVehiculo.TURISMO) {
			vehiculoLeer = new Turismo(marca, modelo, leerEntero("Introduzca la cilidrada: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			vehiculoLeer = new Autobus(marca, modelo, leerEntero("Introduzca las plazas: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.FURGONETA) {
			vehiculoLeer = new Furgoneta(marca, modelo, leerEntero("Introduzca el PMA: "),
					leerEntero("Introduzca las plazas: "), matricula);
		}
		return vehiculoLeer;
	}

	public static Vehiculo leerVehiculoMatricula() {
		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduzca matrícula: "));
	}

	public static Alquiler leerAlquiler() {
		return new Alquiler(leerClienteDni(), leerVehiculoMatricula(),
				leerFecha("Introduzca la fecha para el alquiler (%s): ", PATRON_FECHA));
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Introduzca la fecha de devolución del alquiler (%s): ", PATRON_FECHA);
	}

	public static LocalDate leerMes() {
		return leerFecha("Introduzca el mes (%s): ", PATRON_MES);
	}

}
