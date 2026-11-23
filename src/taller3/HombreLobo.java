package taller3;

public class HombreLobo extends Monstruo implements Mordedor, Enfurecerse {
	private int ferocidad;
	private int faseLunar;

	public HombreLobo(String tipo,int vida,int velocidad,int fuerza,int resistencia,int ferocidad,int faseLunar) {
		super(tipo,vida,velocidad,fuerza,resistencia);
		this.ferocidad=ferocidad;
		this.faseLunar=faseLunar;
	}

	public int getFerocidad() {
		return ferocidad;
	}

	public int getFaseLunar() {
		return faseLunar;
	}

	@Override
	public String morder() {
		return "¡El Hombre Lobo muerde salvajemente!";
	}

	@Override
	public String enfurecerse() {
		return "¡El Hombre Lobo aúlla a la luna y entra en frenesí!";
	}

	@Override
	public double calcularPotencialCarrera() {
		double resultado=(getVelocidad()+(ferocidad/5.0))*(faseLunar/4.0);
		return resultado;
	}

	@Override
	public double calcularPotencialCalabazas() {
		double resultado=((ferocidad*faseLunar)/4.0)+(getVelocidad()/4.0);
		return resultado;
	}

	@Override
	public double calcularPotencialLapidas() {
		double resultado=getFuerza()+((ferocidad*faseLunar)/8.0);
		return resultado;
	}

	@Override
	public double calcularPotencialAmanecer() {
		double resultado=((getResistencia()+getVida())/2.0)*((5.0-faseLunar)/4.0);
		return resultado;
	}
}