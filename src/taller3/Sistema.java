package taller3;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	private ArrayList <Monstruos>monstruos=new ArrayList<>();
	private ArrayList<Jugador>jugadores=new ArrayList<>();
	public Sistema() {
		super();
	}

	public void añadirJugador(String nombre,int puntaje) {
		Jugador jugador=new Jugador(nombre,puntaje);
		jugadores.add(jugador);
	}
	
}
