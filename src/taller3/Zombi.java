package taller3;

public class Zombi extends Monstruo implements Mordedor, Regenerable {
	private int fuerzaDeMordida;

	public Zombi(String tipo,int vida,int velocidad,int fuerza,int resistencia,int fuerzaDeMordida) {
		super(tipo,vida,velocidad,fuerza,resistencia);
		this.fuerzaDeMordida=fuerzaDeMordida;
	}

	public int getFuerzaDeMordida() {
		return fuerzaDeMordida;
	}

	@Override
	public String morder() {
		return "¡El Zombi lanza una mordida feroz!";
	}

	@Override
	public String regenerarse() {
		return "¡El Zombi regenera sus tejidos dañados!";
	}

	@Override
	public double calcularPotencialCarrera() {
		double resultado=(getVelocidad()*getResistencia())/100.0;
		return resultado;
	}

	@Override
	public double calcularPotencialCalabazas() {
		double resultado=(fuerzaDeMordida*getVida())/100.0+(getResistencia()/2.0);
		return resultado;
	}

	@Override
	public double calcularPotencialLapidas() {
		double resultado=(getFuerza()*getResistencia())/100.0;
		return resultado;
	}

	@Override
	public double calcularPotencialAmanecer() {
		double resultado=(getResistencia()*getVida())/100.0;
		return resultado;
	}
}