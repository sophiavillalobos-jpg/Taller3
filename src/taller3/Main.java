package taller3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner scanner =new Scanner (System.in);
	
	
	public static void main(String[] args) {
		Sistema sistema=new Sistema();
		System.out.println("Cargando archivos...");
		leerMonstruos(sistema);
		leerRegitros(sistema);
	}

	private static void leerRegitros(Sistema sistema) {
		try {
			File arch=new File ("Registros.txt");
			Scanner lector=new Scanner (arch);
			
			while(lector.hasNextLine()) {
				String linea=lector.nextLine();
				String []partes=linea.split(";");
				String nombrejugador=partes[0];
				int puntajeMaximo= Integer.parseInt(partes[1]);
				
				sistema.añadirJugador(nombrejugador, puntajeMaximo); 
			
			}
			System.out.println(" > " + jugadores.size() + " jugadores cargados desde Registros.txt");	
		}catch(FileNotFoundException e) {
			
		}
	}

	private static void leerMonstruos(Sistema sistema) {
		try {
			File arch=new File ("Monstruos.txt");
			Scanner lector=new Scanner (arch);
			
			while(lector.hasNextLine()) {
				String linea=lector.nextLine();
				String []partes=linea.split(";");
				String tipo=partes[0];
				int vida= Integer.parseInt(partes[1]);
				int velocidad= Integer.parseInt(partes[2]);
				int fuerza= Integer.parseInt(partes[3]);
				int resistencia= Integer.parseInt(partes[4]);
				
				switch(tipo) {
				case"Zombi":
					int fuerzaDeMordida=Integer.parseInt(partes[5]);
					Zombi zombi=new Zombi(tipo,vida,velocidad,fuerza,resistencia,fuerzaDeMordida);
					monstruos.add(zombi);
					break;
				case"Vampiro":
					int velocidadDeVuelo=Integer.parseInt(partes[5]);
				
					int resistenciaAlSol=Integer.parseInt(partes[6]);
					Vampiro vampiro=new Vampiro(tipo,vida,velocidad,fuerza,resistencia,velocidadDeVuelo,resistenciaAlSol);
					monstruos.add(vampiro);
					break;
				case"Momia":
					int longitudVendajes=Integer.parseInt(partes[5]);
					Momia momia=new Momia(tipo,vida,velocidad,fuerza,resistencia,longitudVendajes);
					monstruos.add(momia);
					break;
				case"HombreLobo":
					int ferocidad=Integer.parseInt(partes[5]);
					int faseLunar=Integer.parseInt(partes[6]);
					HombreLobo hombreLobo=new HombreLobo(tipo,vida,velocidad,fuerza,resistencia,ferocidad,faseLunar);
					monstruos.add(hombreLobo);
					break;
				default:
					System.out.print("el tipo no existeee");
				
				}
			}
			System.out.println(" > " + monstruos.size() + " monstruos cargados desde Monstruos.txt");
		}catch(FileNotFoundException e) {
			
		}
	}

}
