package taller3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	private ArrayList<Monstruo> monstruos=new ArrayList<>();
	private ArrayList<Jugador> jugadores=new ArrayList<>();
	private LinkedList<RegistroHistorial> historialSesion=new LinkedList<>();
	private Jugador jugadorActual;
	private int puntajeSesion;
	private boolean[] minijuegosJugados;
	private int[] puntajesMinijuegos;

	public Sistema() {
		super();
		this.puntajeSesion=0;
		this.minijuegosJugados=new boolean[4];
		this.puntajesMinijuegos=new int[4];
	}

	public void añadirJugador(String nombre,int puntaje) {
		Jugador jugador=new Jugador(nombre,puntaje);
		jugadores.add(jugador);
	}

	private boolean validarBase(int vida,int velocidad,int fuerza,int resistencia) {
		if (vida<1||vida>200) {
			return false;
		}
		if (velocidad<1||velocidad>100) {
			return false;
		}
		if (fuerza<1||fuerza>100) {
			return false;
		}
		if (resistencia<1||resistencia>100) {
			return false;
		}
		return true;
	}

	private boolean existeTipo(String tipo) {
		for (Monstruo m : monstruos) {
			if (m.getTipo().equalsIgnoreCase(tipo)) {
				return true;
			}
		}
		return false;
	}

	public boolean añadirZombi(String tipo, int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		if (existeTipo(tipo)) {
			return false;
		}
		if (!validarBase(vida, velocidad, fuerza, resistencia)) {
			return false;
		}
		if (fuerzaDeMordida < 1 || fuerzaDeMordida > 100) {
			return false;
		}
		Zombi zombi=new Zombi(tipo, vida, velocidad, fuerza, resistencia, fuerzaDeMordida);
		monstruos.add(zombi);
		return true;
	}

	public boolean añadirVampiro(String tipo, int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {
		if (existeTipo(tipo)) {
			return false;
		}
		if (!validarBase(vida, velocidad, fuerza, resistencia)) {
			return false;
		}
		if (velocidadDeVuelo < 1 || velocidadDeVuelo > 100) {
			return false;
		}
		if (resistenciaAlSol < 1 || resistenciaAlSol > 100) {
			return false;
		}
		Vampiro vampiro=new Vampiro(tipo, vida, velocidad, fuerza, resistencia, velocidadDeVuelo, resistenciaAlSol);
		monstruos.add(vampiro);
		return true;
	}

	public boolean añadirMomia(String tipo, int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
		if (existeTipo(tipo)) {
			return false;
		}
		if (!validarBase(vida, velocidad, fuerza, resistencia)) {
			return false;
		}
		if (longitudVendajes < 1 || longitudVendajes > 300) {
			return false;
		}
		Momia momia=new Momia(tipo, vida, velocidad, fuerza, resistencia, longitudVendajes);
		monstruos.add(momia);
		return true;
	}

	public boolean añadirHombreLobo(String tipo, int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		if (existeTipo(tipo)) {
			return false;
		}
		if (!validarBase(vida, velocidad, fuerza, resistencia)) {
			return false;
		}
		if (ferocidad < 1 || ferocidad > 100) {
			return false;
		}
		if (faseLunar < 1 || faseLunar > 4) {
			return false;
		}
		HombreLobo hombreLobo=new HombreLobo(tipo, vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);
		monstruos.add(hombreLobo);
		return true;
	}

	public ArrayList<Monstruo> getMonstruos() {
		return monstruos;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public LinkedList<RegistroHistorial> getHistorialSesion() {
		return historialSesion;
	}

	public int getPuntajeSesion() {
		return puntajeSesion;
	}

	public String iniciarSesion(String nombre) {
		if (nombre == null || nombre.trim().isEmpty() || nombre.contains(";")) {
			return "[!] El nombre de usuario no es válido.";
		}

		String nombreLimpio=nombre.trim();
		Jugador encontrado=null;

		for (Jugador j : jugadores) {
			if (j.getNombre().equalsIgnoreCase(nombreLimpio)) {
				encontrado=j;
				break;
			}
		}

		this.puntajeSesion=0;
		this.historialSesion.clear();
		for (int i=0; i < 4; i++) {
			this.minijuegosJugados[i]=false;
			this.puntajesMinijuegos[i]=0;
		}

		for (Monstruo m : monstruos) {
			m.setUsadoEnSesion(false);
		}

		if (encontrado != null) {
			this.jugadorActual=encontrado;
			return "¡Bienvenido de nuevo, " + encontrado.getNombre() + "! Tu récord actual es de " + encontrado.getPuntajeMaximo() + " puntos.";
		} else {
			Jugador nuevo=new Jugador(nombreLimpio, 0);
			jugadores.add(nuevo);
			this.jugadorActual=nuevo;
			return "¡Bienvenido, " + nombreLimpio + "! Es tu primera vez en el sistema.";
		}
	}

	public boolean isMinijuegoJugado(int opcionMinijuego) {
		if (opcionMinijuego >= 1 && opcionMinijuego <= 4) {
			return minijuegosJugados[opcionMinijuego - 1];
		}
		return false;
	}

	public int getPuntajeMinijuego(int opcionMinijuego) {
		if (opcionMinijuego >= 1 && opcionMinijuego <= 4) {
			return puntajesMinijuegos[opcionMinijuego - 1];
		}
		return 0;
	}

	public ArrayList<Monstruo> getMonstruosDisponibles() {
		ArrayList<Monstruo> disponibles=new ArrayList<>();
		for (Monstruo m : monstruos) {
			if (!m.isUsadoEnSesion()) {
				disponibles.add(m);
			}
		}
		return disponibles;
	}

	public double calcularPotencial(Monstruo m, int opcionMinijuego) {
		double potencial=0.0;
		switch (opcionMinijuego) {
		case 1:
			potencial=m.calcularPotencialCarrera();
			break;
		case 2:
			potencial=m.calcularPotencialCalabazas();
			break;
		case 3:
			potencial=m.calcularPotencialLapidas();
			break;
		case 4:
			potencial=m.calcularPotencialAmanecer();
			break;
		}
		return potencial;
	}

	public String getHabilidadPremiada(int opcionMinijuego) {
		switch (opcionMinijuego) {
		case 1:
			return "Volar";
		case 2:
			return "Morder";
		case 3:
			return "Enfurecerse";
		case 4:
			return "Regenerarse";
		default:
			return "";
		}
	}

	public int registrarResultadoMinijuego(int minijuegoOpt, Monstruo monstruo, String habilidadUsada, double potencial) {
		String premiada=getHabilidadPremiada(minijuegoOpt);
		double multiplicador=1.0;

		if (habilidadUsada.equalsIgnoreCase(premiada)) {
			multiplicador=1.5;
		}

		int puntajeObtenido=(int) Math.round(potencial * 10.0 * multiplicador);

		monstruo.setUsadoEnSesion(true);
		minijuegosJugados[minijuegoOpt - 1]=true;
		puntajesMinijuegos[minijuegoOpt - 1]=puntajeObtenido;
		puntajeSesion += puntajeObtenido;

		String nombreJuego="";
		switch (minijuegoOpt) {
		case 1:
			nombreJuego="Carrera del Cementerio";
			break;
		case 2:
			nombreJuego="Caza de Calabazas";
			break;
		case 3:
			nombreJuego="Lanzamiento de Lápidas";
			break;
		case 4:
			nombreJuego="Sobrevivir al Amanecer";
			break;
		}

		RegistroHistorial registro=new RegistroHistorial(nombreJuego, monstruo.getTipo(), habilidadUsada, puntajeObtenido);
		historialSesion.add(registro);

		return puntajeObtenido;
	}

	public ArrayList<Jugador> obtenerRankingOrdenado() {
		ArrayList<Jugador> ranking=new ArrayList<>(jugadores);
		int n=ranking.size();

		for (int i=0; i < n - 1; i++) {
			for (int j=0; j < n - i - 1; j++) {
				Jugador j1=ranking.get(j);
				Jugador j2=ranking.get(j + 1);

				boolean cambiar=false;
				if (j1.getPuntajeMaximo() < j2.getPuntajeMaximo()) {
					cambiar=true;
				} else if (j1.getPuntajeMaximo() == j2.getPuntajeMaximo()) {
					if (j1.getNombre().compareToIgnoreCase(j2.getNombre()) > 0) {
						cambiar=true;
					}
				}
				if (cambiar) {
					ranking.set(j, j2);
					ranking.set(j + 1, j1);
				}
			}
		}
		return ranking;
	}

	public String finalizarSesion() {
		if (jugadorActual == null) {
			return "No hay sesión activa.";
		}

		boolean jugoAlMenosUno=false;
		for (boolean b : minijuegosJugados) {
			if (b) {
				jugoAlMenosUno=true;
				break;
			}
		}
		if (!jugoAlMenosUno) {
			return "Sesión terminada. No jugaste ningún minijuego, no se actualizaron registros.";
		}
		String mensaje="Sesión terminada. Puntaje total obtenido: " + puntajeSesion + " pts.\n";
		if (puntajeSesion > jugadorActual.getPuntajeMaximo()) {
			jugadorActual.setPuntajeMaximo(puntajeSesion);
			mensaje += "¡Felicidades! Superaste tu récord personal.";
		} else {
			mensaje += "No superaste tu récord anterior (" + jugadorActual.getPuntajeMaximo() + " pts).";
		}
		return mensaje;
	}
}