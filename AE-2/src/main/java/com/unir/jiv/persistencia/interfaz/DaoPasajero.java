package com.unir.jiv.persistencia.interfaz;

import java.util.List;

import com.unir.jiv.entidad.Pasajero;

public interface DaoPasajero {
	
	boolean abrirConexion();

	boolean cerrarConexion();
	
	Pasajero nuevoPasajero();
	Pasajero borrarPasajero(int idPasajero);
	Pasajero consultarPasajero(int idPasajero);
	List<Pasajero> listarPasajeros();
	Pasajero addPasajeroACoche(int idPasajero, int idCoche);
	Pasajero eliminarPasajeroCoche(int idPasajero);
	Pasajero listarPasajerosCoche(int idCoche);
}
