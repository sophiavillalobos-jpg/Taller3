package taller3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	static Scanner scanner =new Scanner (System.in);
	
	
	public static void main(String[] args) {
		Sistema sistema=new Sistema();
		System.out.println("Cargando archivos...");
		leerMonstruos(sistema);
		leerRegitros(sistema);

		if(sistema.getMonstruos().size() == 0) {
			System.out.println("[!] No se encontraron monstruos validos en Monstruos.txt. El programa se cerrara.");
			return;
		}

		// R2. Inicio de sesion
		String mensajeSesion;
		do {
			System.out.print("\nIngrese su nombre de usuario: ");
			String nombre = scanner.nextLine();
			mensajeSesion = sistema.iniciarSesion(nombre);
			System.out.println(mensajeSesion);
		} while(mensajeSesion.startsWith("[!]"));

		// R3. Menu principal
		desplegarMenu(sistema);
	}

	private static void leerRegitros(Sistema sistema) {
		try {
			File arch=new File ("Registros.txt");
			Scanner lector=new Scanner (arch);
			
			while(lector.hasNextLine()) {
				String linea=lector.nextLine();
				String []partes=linea.split(";");
				String nombrejugador=partes[0];
				int puntajeMaximo= Integer.parseInt(partes[1]);
				
				sistema.añadirJugador(nombrejugador, puntajeMaximo); 
			
			}
			System.out.println(" > " + sistema.getJugadores().size() + " jugadores cargados desde Registros.txt");	
		}catch(FileNotFoundException e) {
			
		}
	}

	private static void leerMonstruos(Sistema sistema) {
		try {
			File arch=new File ("Monstruos.txt");
			Scanner lector=new Scanner (arch);
			
			while(lector.hasNextLine()) {
				String linea=lector.nextLine();
				String []partes=linea.split(";");
				String tipo=partes[0];
				int vida= Integer.parseInt(partes[1]);
				int velocidad= Integer.parseInt(partes[2]);
				int fuerza= Integer.parseInt(partes[3]);
				int resistencia= Integer.parseInt(partes[4]);
				
				switch(tipo) {
				case"Zombi":
					int fuerzaDeMordida=Integer.parseInt(partes[5]);
					Zombi zombi=new Zombi(tipo,vida,velocidad,fuerza,resistencia,fuerzaDeMordida);
					sistema.añadirMonstruo(zombi);
					break;
				case"Vampiro":
					int velocidadDeVuelo=Integer.parseInt(partes[5]);
				
					int resistenciaAlSol=Integer.parseInt(partes[6]);
					Vampiro vampiro=new Vampiro(tipo,vida,velocidad,fuerza,resistencia,velocidadDeVuelo,resistenciaAlSol);
					sistema.añadirMonstruo(vampiro);
					break;
				case"Momia":
					int longitudVendajes=Integer.parseInt(partes[5]);
					Momia momia=new Momia(tipo,vida,velocidad,fuerza,resistencia,longitudVendajes);
					sistema.añadirMonstruo(momia);
					break;
				case"HombreLobo":
					int ferocidad=Integer.parseInt(partes[5]);
					int faseLunar=Integer.parseInt(partes[6]);
					HombreLobo hombreLobo=new HombreLobo(tipo,vida,velocidad,fuerza,resistencia,ferocidad,faseLunar);
					sistema.añadirMonstruo(hombreLobo);
					break;
				default:
					System.out.print("el tipo no existeee");
				
				}
			}
			System.out.println(" > " + sistema.getMonstruos().size() + " monstruos cargados desde Monstruos.txt");
		}catch(FileNotFoundException e) {
			
		}
	}

	private static void desplegarMenu(Sistema sistema) {
		boolean salir = false;

		while(!salir) {
			System.out.println("\n------ MENÚ PRINCIPAL (" + sistema.getJugadorActual().getNombre() + ") ------");
			System.out.println("1. Jugar un minijuego");
			System.out.println("2. Ver catálogo de monstruos");
			System.out.println("3. Ver historial de la sesión");
			System.out.println("4. Ver ranking de jugadores");
			System.out.println("5. Terminar sesión");
			System.out.println("6. Salir");
			System.out.print("Seleccione una opción: ");

			String opcionStr = scanner.nextLine();
			int opcion;

			try {
				opcion = Integer.parseInt(opcionStr);
			} catch(NumberFormatException e) {
				System.out.println("[!] Debe ingresar un número entero.");
				continue;
			}

			switch(opcion) {
			case 1:
				System.out.println("[i] Opción Jugar un minijuego (Pendiente R4)");
				break;
			case 2:
				System.out.println("[i] Opción Ver catálogo (Pendiente R5)");
				break;
			case 3:
				System.out.println("[i] Opción Ver historial (Pendiente R6)");
				break;
			case 4:
				System.out.println("[i] Opción Ver ranking (Pendiente R7)");
				break;
			case 5:
				System.out.println("[i] Opción Terminar sesión (Pendiente R8)");
				break;
			case 6:
				System.out.println("¡Gracias por jugar!");
				salir = true;
				break;
			default:
				System.out.println("[!] Opción fuera de rango.");
				break;
			}
		}
	}
}