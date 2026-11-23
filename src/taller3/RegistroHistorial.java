package taller3;

public class RegistroHistorial {
	private String nombreMinijuego;
	private String nombreMonstruo;
	private String habilidadUsada;
	private int puntaje;

	public RegistroHistorial(String nombreMinijuego,String nombreMonstruo,String habilidadUsada,int puntaje) {
		this.nombreMinijuego=nombreMinijuego;
		this.nombreMonstruo=nombreMonstruo;
		this.habilidadUsada=habilidadUsada;
		this.puntaje=puntaje;
	}

	public String getNombreMinijuego() {
		return nombreMinijuego;
	}

	public String getNombreMonstruo() {
		return nombreMonstruo;
	}

	public String getHabilidadUsada() {
		return habilidadUsada;
	}

	public int getPuntaje() {
		return puntaje;
	}
}
