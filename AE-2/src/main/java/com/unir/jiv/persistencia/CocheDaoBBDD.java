package com.unir.jiv.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.unir.jiv.entidad.Coche;
import com.unir.jiv.persistencia.interfaz.DaoCoche;


public class CocheDaoBBDD implements DaoCoche{
	
	private Connection conexion;
	private static Scanner leer = new Scanner(System.in);
	public CocheDaoBBDD() {
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
	public Coche getCochePorId(int id) {
		if(!abrirConexion()) {
			return null;
		}
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,ANIO,KM from coches"
				+" where id = ?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAnio(rs.getInt(4));
				coche.setKm(rs.getDouble(5));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		System.out.println(coche);
		return coche;
		
	}

	@Override
	public List<Coche> listarCoches() {
		String query = "select * from coches";
		List<Coche> lista = new ArrayList<>();
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setMarca(rs.getString("marca"));
	            coche.setModelo(rs.getString("modelo"));
	            coche.setAnio(rs.getInt("anio"));
	            coche.setKm(rs.getDouble("km"));
				lista.add(coche);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		for(Coche ele:lista) {
			System.out.println(ele);
		}
		return lista;
	}
	
	@Override
	public Coche editarCoche(int id) {
		Coche coche = null;
		Scanner leer = new Scanner(System.in);
		System.out.println("Ingresar nombre de marca:");
		String marca = leer.next();
		System.out.println("Ingresar modelo:");
		String modelo = leer.next();
		System.out.println("Introducir año");
		int anio = leer.nextInt();
		System.out.println("Introducir numero de kilometros");
		double km = leer.nextDouble();
		
		String query = "update coches set marca =?,modelo=?,anio=?,km=?"
				+ " where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, marca);
			ps.setString(2,modelo);
			ps.setInt(3, anio);
			ps.setDouble(4, km);
			ps.setInt(5, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return coche;
		
	}

	@Override
	public Coche borrarCoche(int id) {
		if(!abrirConexion()) {
			return null;
		}
		
		String query = "delete from coches where id =?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Coche eliminado");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Coche addCoche() {
		if(!abrirConexion()) {
			return null;
		}
		
		Coche coche = new Coche();
		System.out.println("Ingresar nombre de marca:");
		String marca = leer.next();
		System.out.println("Ingresar modelo:");
		String modelo = leer.next();
		System.out.println("Introducir año");
		int anio = leer.nextInt();
		System.out.println("Introducir numero de kilometros");
		double km = leer.nextDouble();
		
		String query = "insert into coches (marca,modelo,anio,km) values (?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, marca);
			ps.setString(2,modelo);
			ps.setInt(3, anio);
			ps.setDouble(4, km);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return coche;
	}
	
	
}
