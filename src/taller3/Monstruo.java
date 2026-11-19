package taller3;

public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resistencia;
	private boolean usadoEnSesion;

	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		this.usadoEnSesion = false;
	}

	public String getTipo() {
		return tipo;
	}

	public int getVida() {
		return vida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getResistencia() {
		return resistencia;
	}

	public boolean isUsadoEnSesion() {
		return usadoEnSesion;
	}

	public void setUsadoEnSesion(boolean usadoEnSesion) {
		this.usadoEnSesion = usadoEnSesion;
	}
}