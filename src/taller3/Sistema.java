package taller3;

import java.util.ArrayList;

public class Sistema {
	private ArrayList <Monstruo>monstruos=new ArrayList<>();
	private ArrayList<Jugador>jugadores=new ArrayList<>();
	private Jugador jugadorActual;

	public Sistema() {
		super();
	}

	public void añadirJugador(String nombre,int puntaje) {
		Jugador jugador=new Jugador(nombre,puntaje);
		jugadores.add(jugador);
	}

	public void añadirMonstruo(Monstruo monstruo) {
		monstruos.add(monstruo);
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

	// Requerimiento R2: Inicio de Sesion
	public String iniciarSesion(String nombre) {
		if(nombre == null || nombre.trim().isEmpty() || nombre.contains(";")) {
			return "[!] El nombre de usuario no es valido (no puede estar vacio ni contener ';').";
		}

		String nombreLimpio = nombre.trim();
		Jugador encontrado = null;

		for(Jugador j : jugadores) {
			if(j.getNombre().equalsIgnoreCase(nombreLimpio)) {
				encontrado = j;
				break;
			}
		}

		for(Monstruo m : monstruos) {
			m.setUsadoEnSesion(false);
		}

		if(encontrado != null) {
			this.jugadorActual = encontrado;
			return "¡Bienvenido de nuevo, " + encontrado.getNombre() + "! Tu récord actual es de " + encontrado.getPuntajeMaximo() + " puntos.";
		} else {
			Jugador nuevo = new Jugador(nombreLimpio, 0);
			jugadores.add(nuevo);
			this.jugadorActual = nuevo;
			return "¡Bienvenido, " + nombreLimpio + "! Es tu primera vez en el sistema.";
		}
	}
}