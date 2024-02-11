package com.unir.jiv.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.unir.jiv.entidad.Pasajero;
import com.unir.jiv.persistencia.interfaz.DaoPasajero;

public class PasajeroDao implements DaoPasajero{

	private Connection conexion;
	private static Scanner leer = new Scanner(System.in);
	public PasajeroDao() {
        abrirConexion();
    }
	
	@Override
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/gestion_coches";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public Pasajero nuevoPasajero() {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("gestion_coches");
		EntityManager em = factoria.createEntityManager();
		
		Pasajero p = new Pasajero();
		System.out.println("Introducir nombre de pasajero:");
		String nombre = leer.next();
		System.out.println("Introducir edad de pasajero:");
		int edad = leer.nextInt();
		System.out.println("Introducir peso de pasajero:");
		double peso = leer.nextDouble();
		
		String query = "insert into pasajeros (nombre,edad,peso) values(?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setDouble(3,peso);
			
			ps.executeUpdate();
			
			Pasajero pasajero = new Pasajero();
			pasajero.setNombre(nombre);
			pasajero.setEdad(edad);
			pasajero.setPeso(peso);
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(pasajero);
			et.commit();
			
			em.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	@Override
	public Pasajero borrarPasajero(int idPasajero) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pasajero consultarPasajero(int id) {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("gestion_coches");
		EntityManager em = factoria.createEntityManager();
		
		Pasajero p = null;
		
		String query = "select id,nombre,edad,peso from pasajeros where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setPeso(rs.getDouble(4));
			}
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(p);
			et.commit();
			
			em.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	@Override
	public List<Pasajero> listarPasajeros() {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("gestion_coches");
		EntityManager em = factoria.createEntityManager();
		
		List<Pasajero> listaPasajeros = em.createQuery("from Pasajero p").getResultList();
		for(Pasajero p:listaPasajeros) {
			System.out.println(p);
		}
		em.close();
		return null;
	}
	@Override
	public Pasajero addPasajeroACoche(int idPasajero, int idCoche) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pasajero eliminarPasajeroCoche(int idPasajero) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pasajero listarPasajerosCoche(int idCoche) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
