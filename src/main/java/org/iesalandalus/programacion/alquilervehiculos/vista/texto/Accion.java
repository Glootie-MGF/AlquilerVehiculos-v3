package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {

	SALIR("Salir") {
		@Override
		public void ejecutar() {
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarCliente();

		}
	},
	INSERTAR_VEHICULO("Insertar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarVehiculo();

		}
	},
	INSERTAR_ALQUILER("Insertar alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {
		@Override
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar vehiculo") {
		@Override
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar alquiler") {
		@Override
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler de un cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerCliente();

		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler de un vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerVehiculo();

		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarAlquiler();

		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		@Override
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Listar vehículos") {
		@Override
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar alquileres") {
		@Override
		public void ejecutar() {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres de un CLIENTE") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres de un VEHÍCULO") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresVehiculo();
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadísticas mensuales") {
		@Override
		public void ejecutar() {
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};

	private String texto;
	private static VistaTexto vista;

	private Accion(String texto) {
		this.texto = texto;
	}

	static void setVista(VistaTexto vista) {
		Accion.vista = vista;
	}

	public abstract void ejecutar() throws OperationNotSupportedException;

	private static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal < Accion.values().length);
	}

	public static Accion get(int ordinalEscogido) {

		if (!esOrdinalValido(ordinalEscogido)) {
			throw new IllegalArgumentException("ERROR: Opción escogida NO válida.");
		}
		return Accion.values()[ordinalEscogido];
	}

	@Override
	public String toString() {
		return String.format("%d.-%s,", ordinal(), texto);
	}
}
