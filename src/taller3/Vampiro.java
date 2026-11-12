package taller3;

public class Vampiro extends Monstruos {
	private int velocidadDeVuelo;
	private int resistenciaAlSol;
	public Vampiro(String tipo, int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo,
			int resistenciaAlSol) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.velocidadDeVuelo = velocidadDeVuelo;
		this.resistenciaAlSol = resistenciaAlSol;
	}
	
	
}
