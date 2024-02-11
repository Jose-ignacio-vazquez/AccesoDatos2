package com.unir.jiv.presentacion;

import java.util.Scanner;
import com.unir.jiv.entidad.Coche;
import com.unir.jiv.persistencia.CocheDaoBBDD;

public class VistaCoche {
	
	static CocheDaoBBDD cd = new CocheDaoBBDD();

	public static void main(String[] args) {
		
		mostrarMenu();
		

	}

	private static void mostrarMenu() {
		Scanner sc =new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("Menu coches");
			System.out.println("1.Añadir nuevo coche");
			System.out.println("2.Borrar coche por id.");
			System.out.println("3.Consultar coche por id");
			System.out.println("4.Modificar coche por id");
			System.out.println("5.Listar coches");
			System.out.println("6.Terminar el programa.");
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
				System.out.println("Fin del programa");
				break;
				default:
					System.out.println("Opcion no valida.");
					System.out.println("Introduzca un numero del 1 al 5, para terminar elija 6.");
			}			
		}while(opcion != 6);
		
		sc.close();
		
	}

}
