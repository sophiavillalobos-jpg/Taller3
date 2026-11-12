package taller3;

public class Zombi extends Monstruos {
	private int fuerzaDeMordida;

	public Zombi(String tipo, int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}
	
}
