package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	private List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;

	private static final File FICHERO_ALQUILERES = new File(
			String.format("%s%s%s", "datos", File.separator, "alquileres.xml"));
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "cliente";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";

	// Constructor por defecto
	private Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	// Métodos
	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}
		return instancia;
	}

	public void comenzar() {

		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES);

		if (documento != null) {

			leerDom(documento);
			System.out.print("Documento leído correctamente.");
		} else {

			System.out.print("ERROR: problema al leer el documento.");
		}
	}

	private void leerDom(Document documentoXml) {

		NodeList lista = documentoXml.getElementsByTagName(ALQUILER);

		for (int i = 0; i < lista.getLength(); i++) {
			Node nodo = lista.item(i);

			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				try {
					insertar(getAlquiler((Element) nodo));

				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.printf("Ha habido un error al procesar el alquiler : %d --> %s%n", i, e.getMessage());
				}
			}
		}
	}

	private Alquiler getAlquiler(Element elemento) throws OperationNotSupportedException {

		Alquiler alquiler = null;
		String fechaDevolucion = elemento.getAttribute(FECHA_DEVOLUCION);

		Cliente cliente = Clientes.getInstancia().buscar(Cliente.getClienteConDni(elemento.getAttribute(CLIENTE)));
		Vehiculo vehiculo = Vehiculos.getInstancia()
				.buscar(Vehiculo.getVehiculoConMatricula(elemento.getAttribute(VEHICULO)));

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: El cliente NO existe");
		}
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: El vehiculo NO existe");
		}

		alquiler = new Alquiler(cliente, vehiculo,
				LocalDate.parse(elemento.getAttribute(FECHA_ALQUILER), FORMATO_FECHA));

		if (!fechaDevolucion.isBlank()) {

			alquiler.devolver(LocalDate.parse(fechaDevolucion, FORMATO_FECHA));

		}
		return alquiler;
	}

	public void terminar() {

		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);
	}

	private Document crearDom() {

		Document nuevoDocumento = UtilidadesXml.crearConstructorDocumentoXml().newDocument();
		Element nuevaRaiz = nuevoDocumento.createElement(RAIZ);
		nuevoDocumento.appendChild(nuevaRaiz);

		for (Alquiler alquiler : coleccionAlquileres) {
			nuevaRaiz.appendChild(getElemento(nuevoDocumento, alquiler));
		}

		return nuevoDocumento;
	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {

		Element elemento = documentoXml.createElement(ALQUILER);
		elemento.setAttribute(CLIENTE, alquiler.getCliente().getDni());
		elemento.setAttribute(FECHA_ALQUILER, alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		elemento.setAttribute(VEHICULO, alquiler.getVehiculo().getMatricula());

		if (alquiler.getFechaDevolucion() != null) {

			elemento.setAttribute(FECHA_DEVOLUCION, alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}

		return elemento;
	}

	@Override
	public List<Alquiler> get() {

		return new ArrayList<>(coleccionAlquileres);
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> listaAuxCliente = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {

				listaAuxCliente.add(alquiler);
			}
		}
		return listaAuxCliente;
	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> listaAuxVehiculo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {

				listaAuxVehiculo.add(alquiler);
			}
		}
		return listaAuxVehiculo;
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());

		coleccionAlquileres.add(alquiler);
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		/*
		 * Comprobará que en la lista no existe ningún alquiler sin devolver ni para el
		 * cliente ni para el turismo y que tampoco hay un alquiler devuelto, del
		 * cliente o del turismo, con fecha de devolución posterior o igual a la fecha
		 * en la que se pretende alquilar.
		 */
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				// Alquileres sin devolver
				if (cliente.equals(alquiler.getCliente())) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (vehiculo.equals(alquiler.getVehiculo())) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
				// Alquileres devueltos
			} else if (cliente.equals(alquiler.getCliente())
					&& alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
			}
			if (vehiculo.equals(alquiler.getVehiculo())
					&& alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
				throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
			}
		}
	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		Alquiler alquilerBuscado = getAlquilerAbierto(cliente);

		if (alquilerBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		alquilerBuscado.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {

		Alquiler alquilerAbierto = null;

		for (Iterator<Alquiler> iterador = coleccionAlquileres.iterator(); iterador.hasNext() && alquilerAbierto == null;) {

			Alquiler alquiler = iterador.next();

			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto = alquiler;
			}
		}
		return alquilerAbierto;
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		Alquiler alquilerBuscado = getAlquilerAbierto(vehiculo);

		if (alquilerBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		alquilerBuscado.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {

		Alquiler alquilerAbierto = null;

		for (Iterator<Alquiler> iterador = coleccionAlquileres.iterator(); iterador.hasNext() && alquilerAbierto == null;) {

			Alquiler alquiler = iterador.next();

			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto = alquiler;
			}
		}
		return alquilerAbierto;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);

		Alquiler aux = null; // Alquiler auxiliar

		if (indice != -1) {
			aux = coleccionAlquileres.get(indice);
		}
		return aux;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

}
