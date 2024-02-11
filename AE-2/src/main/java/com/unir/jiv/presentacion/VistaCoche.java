package com.unir.jiv.presentacion;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.unir.jiv.entidad.Coche;
import com.unir.jiv.persistencia.CocheDaoBBDD;
import com.unir.jiv.persistencia.PasajeroDao;

public class VistaCoche {
	
	static CocheDaoBBDD cd = new CocheDaoBBDD();
	static PasajeroDao pd = new PasajeroDao();

	public static void main(String[] args) {
		
		mostrarMenu();
		

	}

	private static void mostrarMenu() {
		Scanner sc =new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("Menu coches");
			System.out.println("1.Añadir nuevo coche");
			System.out.println("2.Borrar coche por id.");
			System.out.println("3.Consultar coche por id");
			System.out.println("4.Modificar coche por id");
			System.out.println("5.Listar coches");
			System.out.println("6.Gestionar pasajeros.");
			System.out.println("7.Terminar el programa.");
			System.out.println("Selecciona una opcion:");
			opcion = sc.nextInt();
			
			switch(opcion) {
			case 1:
				cd.addCoche();
				break;
			case 2:
				System.out.println("Introduce el ID del coche a borrar:");
                int id = sc.nextInt();
                cd.borrarCoche(id);
				break;
			case 3:
				System.out.println("Introduce el ID del coche a consultar:");
                int idConsultar = sc.nextInt();
                cd.getCochePorId(idConsultar);
				break;
			case 4:
				System.out.println("Introduce el ID del coche a modificar:");
                int idModificar = sc.nextInt();
                cd.editarCoche(idModificar);
                break;
			case 5:
				cd.listarCoches();
				break;
			case 6:
				gestionPasajeros();
				break;
			case 7:
				System.out.println("Fin del programa");
				break;
				default:
					System.out.println("Opcion no valida.");
					System.out.println("Introduzca un numero del 1 al 7.");
			}			
		}while(opcion != 7);
		
		sc.close();
		
	}

	private static void gestionPasajeros() {
		
		Scanner sc =new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("****************************************************************");
			System.out.println("Menu pasajeros");
			System.out.println("1.Crear nuevo pasajero");
			System.out.println("2.Borrar pasajero por id.");
			System.out.println("3.Consultar pasajero por id");
			System.out.println("4.Listar pasajeros");
			System.out.println("5.Añadir pasajero a coche");
			System.out.println("6.Eliminar pasajero de un coche.");
			System.out.println("7.Listar todos los pasajeros de un coche.");
			System.out.println("8.Salir");
			System.out.println("Selecciona una opcion:");
			opcion = sc.nextInt();
			
			switch(opcion) {
			case 1:
				pd.nuevoPasajero();
				break;
			case 2:
				System.out.println("Introduce el ID del pasajero a borrar:");
                int id = sc.nextInt();
				pd.borrarPasajero(id);
				break;
			case 3:
				System.out.println("Introduce el ID del pasajero a buscar:");
                int idPasajero = sc.nextInt();
                pd.consultarPasajero(idPasajero);
                break;
			case 4:
				pd.listarPasajeros();
				break;
			case 5:
				System.out.println("Introduce id del pasajero a añadir");
				int idPersona = sc.nextInt();
				System.out.println("Introduce id del coche");
				int idCoche = sc.nextInt();
				pd.addPasajeroACoche(idPersona, idCoche);
				break;
			case 6:
				System.out.println("Introduce la id del pasajero a borrar");
				int idPasajeroBorrar = sc.nextInt();
				pd.eliminarPasajeroCoche(idPasajeroBorrar);
				break;
			case 7:
				System.out.println("Introduce id del coche");
				int idCocheListar = sc.nextInt();
				pd.listarPasajerosCoche(idCocheListar);
				break;
			case 8:
				System.out.println("Salir del menu pasajero");
				break;
			default:
				System.out.println("Opcion no valida.");
				System.out.println("Introduzca un numero del 1 al 8.");
			}
		}while(opcion != 8);
		
	}

}
