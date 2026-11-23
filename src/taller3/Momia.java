package taller3;

public class Momia extends Monstruo implements Regenerable, Enfurecerse {
	private int longitudVendajes;

	public Momia(String tipo,int vida,int velocidad,int fuerza,int resistencia,int longitudVendajes) {
		super(tipo,vida,velocidad,fuerza,resistencia);
		this.longitudVendajes=longitudVendajes;
	}

	public int getLongitudVendajes() {
		return longitudVendajes;
	}

	@Override
	public String regenerarse() {
		return "¡La Momia ajusta sus vendajes sagrados!";
	}

	@Override
	public String enfurecerse() {
		return "¡La Momia desata la maldición de los faraones!";
	}

	@Override
	public double calcularPotencialCarrera() {
		double resultado=(getVelocidad()*100.0)/(100.0+longitudVendajes);
		return resultado;
	}

	@Override
	public double calcularPotencialCalabazas() {
		double resultado=(longitudVendajes*getFuerza())/200.0;
		return resultado;
	}

	@Override
	public double calcularPotencialLapidas() {
		double resultado=getFuerza()+(longitudVendajes/10.0);
		return resultado;
	}

	@Override
	public double calcularPotencialAmanecer() {
		double resultado=((getResistencia()+getVida())/2.0)*(1.0+(longitudVendajes/300.0));
		return resultado;
	}
}