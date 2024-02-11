package com.unir.jiv.persistencia.interfaz;

import java.util.List;

import com.unir.jiv.entidad.Coche;

public interface DaoCoche {

	boolean abrirConexion();

	boolean cerrarConexion();

	Coche getCochePorId(int id);
	List<Coche> listarCoches();
	Coche borrarCoche(int id);
	Coche addCoche();
	Coche editarCoche(int id);
	
}
