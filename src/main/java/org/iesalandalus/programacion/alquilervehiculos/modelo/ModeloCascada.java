package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	// Constructor por defecto
	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {

		super(factoriaFuenteDatos);
	}

	// Métodos
	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		getVehiculos().insertar(Vehiculo.copiar(vehiculo));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		Cliente clienteBuscado = getClientes().buscar(alquiler.getCliente());

		if (clienteBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}

		Vehiculo vehiculoBuscado = getVehiculos().buscar(alquiler.getVehiculo());

		if (vehiculoBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}

		getAlquileres().insertar(new Alquiler(clienteBuscado, vehiculoBuscado, alquiler.getFechaAlquiler()));
	}

	/*
	 * Crea los diferentes métodos buscar, que devolverá una nueva instancia del
	 * elemento encontrado si éste existe.
	 */
	@Override
	public Cliente buscar(Cliente cliente) {
		return new Cliente(getClientes().buscar(cliente));
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		return Vehiculo.copiar(getVehiculos().buscar(vehiculo));
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	// Crea el método modificar que invocará a su homólogo en la clase de negocio.
	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		getClientes().modificar(cliente, nombre, telefono);
	}

	// Crea los diferentes métodos devolver
	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		getAlquileres().devolver(cliente, fechaDevolucion);

	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		getAlquileres().devolver(vehiculo, fechaDevolucion);

	}

	/*
	 * Crea los diferentes métodos borrar, teniendo en cuenta que los borrados se
	 * realizarán en cascada, es decir, si borramos un cliente también borraremos
	 * todos sus alquileres y lo mismo pasará con los turismos.
	 */
	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		for (Alquiler alquilerAux : getAlquileres().get(cliente)) {

			getAlquileres().borrar(alquilerAux);
		}
		getClientes().borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		for (Alquiler alquilerAux : getAlquileres().get(vehiculo)) {

			getAlquileres().borrar(alquilerAux);
		}
		getVehiculos().borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		getAlquileres().borrar(alquiler);
	}

	/*
	 * Crea los diferentes métodos get, que deben devolver una nueva lista pero que
	 * contenga nuevas instancias no una copia de los elementos.
	 */
	@Override
	public List<Cliente> getListaClientes() {

		List<Cliente> listaClientes = new ArrayList<>();

		for (Cliente cliente : getClientes().get()) {
			listaClientes.add(new Cliente(cliente));
		}
		return listaClientes;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {

		List<Vehiculo> listaVehiculos = new ArrayList<>();

		for (Vehiculo vehiculo : getVehiculos().get()) {
			listaVehiculos.add(Vehiculo.copiar(vehiculo));
		}
		return listaVehiculos;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {

		List<Alquiler> listaAlquileres = new ArrayList<>();

		for (Alquiler alquiler : getAlquileres().get()) {
			listaAlquileres.add(new Alquiler(alquiler));
		}
		return listaAlquileres;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {

		List<Alquiler> listaAlquileresConCliente = new ArrayList<>();

		for (Alquiler alquilerAux : getAlquileres().get(cliente)) {
			listaAlquileresConCliente.add(new Alquiler(alquilerAux));
		}
		return listaAlquileresConCliente;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {

		List<Alquiler> listaAlquileresConVehiculo = new ArrayList<>();

		for (Alquiler alquilerAux : getAlquileres().get(vehiculo)) {
			listaAlquileresConVehiculo.add(new Alquiler(alquilerAux));
		}
		return listaAlquileresConVehiculo;
	}
}
