package taller3;

public class Vampiro extends Monstruo implements Mordedor, Volador {
	private int velocidadDeVuelo;
	private int resistenciaAlSol;

	public Vampiro(String tipo,int vida,int velocidad,int fuerza,int resistencia,int velocidadDeVuelo,int resistenciaAlSol) {
		super(tipo,vida,velocidad,fuerza,resistencia);
		this.velocidadDeVuelo=velocidadDeVuelo;
		this.resistenciaAlSol=resistenciaAlSol;
	}

	public int getVelocidadDeVuelo() {
		return velocidadDeVuelo;
	}

	public int getResistenciaAlSol() {
		return resistenciaAlSol;
	}

	@Override
	public String morder() {
		return "¡El Vampiro clava sus colmillos en la presa!";
	}

	@Override
	public String volar() {
		return "¡El Vampiro despliega sus alas de murciélago!";
	}

	@Override
	public double calcularPotencialCarrera() {
		double resultado=(getVelocidad()+2.0*velocidadDeVuelo)/2.0;
		return resultado;
	}

	@Override
	public double calcularPotencialCalabazas() {
		double resultado=(getFuerza()+getVelocidad())/3.0;
		return resultado;
	}

	@Override
	public double calcularPotencialLapidas() {
		double resultado=getFuerza()+(velocidadDeVuelo/4.0);
		return resultado;
	}

	@Override
	public double calcularPotencialAmanecer() {
		double resultado=(getResistencia()*resistenciaAlSol)/100.0;
		return resultado;
	}
}