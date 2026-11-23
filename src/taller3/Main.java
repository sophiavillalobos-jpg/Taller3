package taller3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		Sistema sistema=new Sistema();
		System.out.println("Cargando archivos...");
		leerMonstruos(sistema);
		leerRegitros(sistema);

		if (sistema.getMonstruos().size()==0) {
			System.out.println("[!] No se encontraron monstruos válidos en Monstruos.txt. El programa se cerrará.");
			return;
		}
		System.out.println("\n=========================================");
		System.out.println("             SPOOKY SEASON               ");
		System.out.println("=========================================");

		boolean continuarPrograma=true;
		while (continuarPrograma) {
			String mensajeSesion;
			do {
				System.out.print("\nIngrese su nombre de usuario: ");
				String nombre=scanner.nextLine();
				mensajeSesion=sistema.iniciarSesion(nombre);
				System.out.println(mensajeSesion);
			} while (mensajeSesion.startsWith("[!]"));

			boolean enSesion=true;
			while (enSesion) {
				System.out.println("\n------ MENÚ PRINCIPAL (" + sistema.getJugadorActual().getNombre() + ") ------");
				System.out.println("1. Jugar un minijuego");
				System.out.println("2. Ver catálogo de monstruos");
				System.out.println("3. Ver historial de la sesión");
				System.out.println("4. Ver ranking de jugadores");
				System.out.println("5. Terminar sesión");
				System.out.println("6. Salir");
				System.out.print("Seleccione una opción: ");

				String opcionStr=scanner.nextLine();
				int opcion;

				try {
					opcion=Integer.parseInt(opcionStr);
				} catch (NumberFormatException e) {
					System.out.println("[!] Debe ingresar un número entero.");
					continue;
				}
				switch (opcion) {
				case 1:
					ejecutarMinijuego(sistema);
					break;
				case 2:
					mostrarCatalogoMonstruos(sistema);
					break;
				case 3:
					mostrarHistorialSesion(sistema);
					break;
				case 4:
					mostrarRanking(sistema);
					break;
				case 5:
					String resSesion=sistema.finalizarSesion();
					System.out.println("\n" + resSesion);
					guardarRegistros(sistema);
					enSesion=false;
					break;
				case 6:
					String resSalir=sistema.finalizarSesion();
					System.out.println("\n" + resSalir);
					guardarRegistros(sistema);
					enSesion=false;
					continuarPrograma=false;
					System.out.println("¡Gracias por jugar!");
					break;
				default:
					System.out.println("[!] Opción fuera de rango.");
					break;
				}
			}
		}
	}

	private static void leerRegitros(Sistema sistema) {
		try {
			File arch=new File("Registros.txt");
			Scanner lector=new Scanner(arch);

			while (lector.hasNextLine()) {
				String linea=lector.nextLine();
				String[] partes=linea.split(";");
				if (partes.length == 2) {
					try {
						String nombrejugador=partes[0];
						int puntajeMaximo=Integer.parseInt(partes[1]);
						sistema.añadirJugador(nombrejugador, puntajeMaximo);
					} catch (NumberFormatException e) {
					}
				}
			}
			lector.close();
			System.out.println(" > "+sistema.getJugadores().size()+" jugadores cargados desde Registros.txt");
		} catch (FileNotFoundException e) {

		}
	}
	private static void leerMonstruos(Sistema sistema) {
		try {
			File arch=new File("Monstruos.txt");
			Scanner lector=new Scanner(arch);

			while (lector.hasNextLine()) {
				String linea=lector.nextLine();
				String[] partes=linea.split(";");
				if (partes.length < 6) {
					continue;
				}

				try {
					String tipo=partes[0];
					int vida=Integer.parseInt(partes[1]);
					int velocidad=Integer.parseInt(partes[2]);
					int fuerza=Integer.parseInt(partes[3]);
					int resistencia=Integer.parseInt(partes[4]);

					switch (tipo) {
					case "Zombi":
						if (partes.length == 6) {
							int fuerzaDeMordida=Integer.parseInt(partes[5]);
							sistema.añadirZombi(tipo, vida, velocidad, fuerza, resistencia, fuerzaDeMordida);
						}
						break;
					case "Vampiro":
						if (partes.length == 7) {
							int velocidadDeVuelo=Integer.parseInt(partes[5]);
							int resistenciaAlSol=Integer.parseInt(partes[6]);
							sistema.añadirVampiro(tipo, vida, velocidad, fuerza, resistencia, velocidadDeVuelo, resistenciaAlSol);
						}
						break;
					case "Momia":
						if (partes.length == 6) {
							int longitudVendajes=Integer.parseInt(partes[5]);
							sistema.añadirMomia(tipo, vida, velocidad, fuerza, resistencia, longitudVendajes);
						}
						break;
					case "HombreLobo":
						if (partes.length == 7) {
							int ferocidad=Integer.parseInt(partes[5]);
							int faseLunar=Integer.parseInt(partes[6]);
							sistema.añadirHombreLobo(tipo, vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);
						}
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {

				}
			}
			lector.close();
			System.out.println(" > " + sistema.getMonstruos().size() + " monstruos cargados desde Monstruos.txt");
		} catch (FileNotFoundException e) {

		}
	}

	private static void guardarRegistros(Sistema sistema) {
		try {
			FileWriter writer=new FileWriter("Registros.txt");
			for (Jugador j : sistema.getJugadores()) {
				writer.write(j.getNombre() + ";" + j.getPuntajeMaximo() + "\n");
			}
			writer.close();
			System.out.println("[i] Cambios guardados en Registros.txt");
		} catch (IOException e) {
			System.out.println("[!] Error al guardar Registros.txt");
		}
	}

	private static void ejecutarMinijuego(Sistema sistema) {
		String[] nombresMinijuegos={
			"Carrera del Cementerio",
			"Caza de Calabazas",
			"Lanzamiento de Lápidas",
			"Sobrevivir al Amanecer"
		};

		System.out.println("\n------ MINIJUEGOS ------");
		for(int i=0;i<4;i++) {
			String estado="PENDIENTE";
			if (sistema.isMinijuegoJugado(i + 1)) {
				estado="JUGADO - "+sistema.getPuntajeMinijuego(i+1)+" pts";
			}
			System.out.println((i+1)+". "+nombresMinijuegos[i]+" [" + estado + "]");
		}
		System.out.println("5. Volver");

		int minijuegoOpt=-1;
		while (true) {
			System.out.print("Elige una opción: ");
			String optStr=scanner.nextLine();
			try {
				minijuegoOpt=Integer.parseInt(optStr);
			} catch (NumberFormatException e) {
				System.out.println("[!] Debe ingresar un número entero.");
				continue;
			}

			if (minijuegoOpt==5) {
				return;
			}

			if (minijuegoOpt<1||minijuegoOpt>4) {
				System.out.println("[!] Opción fuera de rango. Intente nuevamente.");
				continue;
			}

			if (sistema.isMinijuegoJugado(minijuegoOpt)) {
				System.out.println("[!] Ya jugaste "+nombresMinijuegos[minijuegoOpt-1]+" en esta sesión.");
				continue;
			}

			break;
		}

		ArrayList<Monstruo> disponibles=sistema.getMonstruosDisponibles();
		if (disponibles.isEmpty()) {
			System.out.println("[!] No te quedan monstruos disponibles para jugar en esta sesión.");
			return;
		}

		switch (minijuegoOpt) {
		case 1:
			System.out.println("\nCarrera del Cementerio: recorre el cementerio de punta a punta antes de que el sepulturero te vea.");
			break;
		case 2:
			System.out.println("\nCaza de Calabazas: muerde la mayor cantidad de calabazas del huerto en 60 segundos.");
			break;
		case 3:
			System.out.println("\nLanzamiento de Lápidas: lanza una lápida lo más lejos posible.");
			break;
		case 4:
			System.out.println("\nSobrevivir al Amanecer: aguanta en pie cuando salgan los primeros rayos de sol.");
			break;
		}

		// 2. Mostrar lista de monstruos UNA sola vez
		System.out.println("\nElige el monstruo en el que te transformarás:");
		for(int i=0;i<disponibles.size();i++) {
			Monstruo m=disponibles.get(i);
			String tipoAlineado=String.format("%-10s", m.getTipo());
			System.out.print((i + 1) + ") "+tipoAlineado+" | Vida "+m.getVida()+" | Vel "+m.getVelocidad()+" | Fue "+m.getFuerza()+" | Res " + m.getResistencia());

			if (m instanceof Zombi) {
				System.out.println(" | Mordida "+((Zombi) m).getFuerzaDeMordida());
			} else if (m instanceof Vampiro) {
				Vampiro v=(Vampiro) m;
				System.out.println(" | Vuelo "+v.getVelocidadDeVuelo()+" | Sol "+v.getResistenciaAlSol());
			} else if (m instanceof Momia) {
				System.out.println(" | Vendajes "+((Momia) m).getLongitudVendajes());
			} else if (m instanceof HombreLobo) {
				HombreLobo h=(HombreLobo) m;
				System.out.println(" | Ferocidad "+h.getFerocidad()+" | Luna "+h.getFaseLunar());
			}
		}
		System.out.println("0) Volver");

		Monstruo elegido=null;
		while (true) {
			System.out.print("> ");
			String optMStr=scanner.nextLine();
			int optM;
			try {
				optM=Integer.parseInt(optMStr);
			} catch (NumberFormatException e) {
				System.out.println("[!] Debe ingresar un número entero.");
				continue;
			}

			if (optM==0) {
				return;
			}

			if (optM <1||optM> disponibles.size()) {
				System.out.println("[!] Opción fuera de rango. Intente nuevamente.");
				continue;
			}

			elegido=disponibles.get(optM-1);
			break;
		}

		double potencial=sistema.calcularPotencial(elegido, minijuegoOpt);

		System.out.printf("\nTe comes un dulce de dudosa procedencia... Potencial para este minijuego: %.2f\n", potencial);

		ArrayList<String> habilidadesDisponibles=new ArrayList<>();
		if (elegido instanceof Mordedor) {
			habilidadesDisponibles.add("Morder");
		}
		if (elegido instanceof Volador) {
			habilidadesDisponibles.add("Volar");
		}
		if (elegido instanceof Regenerable) {
			habilidadesDisponibles.add("Regenerarse");
		}
		if (elegido instanceof Enfurecerse) {
			habilidadesDisponibles.add("Enfurecerse");
		}

		System.out.println("\n¿Quieres usar una habilidad?");
		for(int i=0;i<habilidadesDisponibles.size();i++) {
			System.out.println((i + 1) + ". " + habilidadesDisponibles.get(i));
		}
		System.out.println((habilidadesDisponibles.size() + 1) + ". No usar habilidad");

		String habilidadSeleccionada="Ninguna";
		while (true) {
			System.out.print("Selecciona una opción: ");
			String optHabStr=scanner.nextLine();
			int optHab;
			try {
				optHab=Integer.parseInt(optHabStr);
			} catch (NumberFormatException e) {
				System.out.println("[!] Debe ingresar un número entero.");
				continue;
			}

			if (optHab < 1 || optHab > habilidadesDisponibles.size() + 1) {
				System.out.println("[!] Opción fuera de rango. Intente nuevamente.");
				continue;
			}

			if (optHab >= 1 && optHab <= habilidadesDisponibles.size()) {
				habilidadSeleccionada=habilidadesDisponibles.get(optHab - 1);

				if (habilidadSeleccionada.equals("Morder")) {
					System.out.println(((Mordedor) elegido).morder());
				} else if (habilidadSeleccionada.equals("Volar")) {
					System.out.println(((Volador) elegido).volar());
				} else if (habilidadSeleccionada.equals("Regenerarse")) {
					System.out.println(((Regenerable) elegido).regenerarse());
				} else if (habilidadSeleccionada.equals("Enfurecerse")) {
					System.out.println(((Enfurecerse) elegido).enfurecerse());
				}
			}
			break;
		}

		String premiada=sistema.getHabilidadPremiada(minijuegoOpt);
		if (!habilidadSeleccionada.equals("Ninguna")) {
			if (habilidadSeleccionada.equalsIgnoreCase(premiada)) {
				System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
			} else {
				System.out.println("La habilidad no tuvo efecto en este minijuego.");
			}
		}
		int puntajeObtenido=sistema.registrarResultadoMinijuego(minijuegoOpt, elegido, habilidadSeleccionada, potencial);
		System.out.println("\n>> Puntaje obtenido: " + puntajeObtenido + " puntos");
	}

	private static void mostrarCatalogoMonstruos(Sistema sistema) {
		System.out.println("\n------ CATÁLOGO DE MONSTRUOS ------");
		ArrayList<Monstruo> lista=sistema.getMonstruos();

		for(int i=0;i<lista.size();i++) {
			Monstruo m=lista.get(i);
			String estado=m.isUsadoEnSesion() ? " [USADO EN SESIÓN]" : " [DISPONIBLE]";
			System.out.print((i + 1) + ". " + m.getTipo() + estado + " | Vida " + m.getVida() + " | Vel " + m.getVelocidad() + " | Fue " + m.getFuerza() + " | Res " + m.getResistencia());

			if (m instanceof Zombi) {
				System.out.println(" | Mordida " + ((Zombi) m).getFuerzaDeMordida() + " | Habilidades: Morder, Regenerarse");
			} else if (m instanceof Vampiro) {
				Vampiro v=(Vampiro) m;
				System.out.println(" | Vuelo " + v.getVelocidadDeVuelo() + " | Sol " + v.getResistenciaAlSol() + " | Habilidades: Morder, Volar");
			} else if (m instanceof Momia) {
				System.out.println(" | Vendajes " + ((Momia) m).getLongitudVendajes() + " | Habilidades: Regenerarse, Enfurecerse");
			} else if (m instanceof HombreLobo) {
				HombreLobo h=(HombreLobo) m;
				System.out.println(" | Ferocidad " + h.getFerocidad() + " | Luna " + h.getFaseLunar() + " | Habilidades: Morder, Enfurecerse");
			}
		}
	}

	private static void mostrarHistorialSesion(Sistema sistema) {
		System.out.println("\n------ HISTORIAL DE LA SESIÓN ------");
		LinkedList<RegistroHistorial> historial=sistema.getHistorialSesion();

		if (historial.isEmpty()) {
			System.out.println("Aún no has jugado ningún minijuego en esta sesión.");
			return;
		}

		for(int i=0;i<historial.size();i++) {
			RegistroHistorial reg=historial.get(i);
			System.out.println((i + 1) + ". " + reg.getNombreMinijuego() + " | " + reg.getNombreMonstruo() + " | Habilidad: " + reg.getHabilidadUsada() + " | " + reg.getPuntaje() + " pts");
		}
		System.out.println("Puntaje acumulado: " + sistema.getPuntajeSesion() + " pts");
	}

	private static void mostrarRanking(Sistema sistema) {
		System.out.println("\n------ RANKING ------");
		ArrayList<Jugador> ranking=sistema.obtenerRankingOrdenado();

		int limite=Math.min(3, ranking.size());
		for(int i=0;i<limite;i++) {
			Jugador j=ranking.get(i);
			String nombreAlineado=String.format("%-15s", j.getNombre());
			System.out.println((i + 1) + ". " + nombreAlineado + j.getPuntajeMaximo());
		}
	}
}