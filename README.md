# Taller3
DESCRIPCION DEL PROYECTO

Spooky Season es un sistema interactivo en Java desarrollado bajo el paquete taller3 para la asignatura de Programacion Orientada a Objetos. El sistema implementa la gestion de usuarios, carga dinamica de monstruos y registros desde archivos de texto, ejecucion de cuatro minijuegos tematicos con calculo de potencial y multiplicador por habilidades de monstruos, y la visualizacion del ranking Top 3 de mejores puntuaciones.


INTEGRANTES

Nombre: [Sophia Villalobos]
RUT: [21137863-8]
GitHub: [SophiaVillalobos-GitH]

ESTRUCTURA DEL PROYECTO

Paquete principal: taller3

Clases principales:
- Main.java: Punto de entrada del programa. Maneja la interfaz de consola, validacion de entradas del usuario y navegacion de menus.
- Sistema.java: Controladora central del sistema. Gestiona colecciones de datos (ArrayList y LinkedList), lectura/escritura de archivos, validaciones de negocio, filtrado de especies duplicadas y calculo de rankings.
- Jugador.java: Representa a los usuarios registrados y almacena su puntaje maximo.
- Monstruo.java: Clase abstracta base de las especies del juego.
  - Zombi.java: Subclase de Monstruo. Implementa interfaces Mordedor y Regenerable.
  - Vampiro.java: Subclase de Monstruo. Implementa interfaces Mordedor y Volador.
  - Momia.java: Subclase de Monstruo. Implementa interfaces Regenerable y Enfurecerse.
  - HombreLobo.java: Subclase de Monstruo. Implementa interfaces Mordedor y Enfurecerse.
- RegistroHistorial.java: Representa cada registro de minijuego jugado durante la sesion.

Interfaces de Habilidades:
- Mordedor.java: Interfaz para monstruos con habilidad de morder.
- Volador.java: Interfaz para monstruos con habilidad de volar.
- Regenerable.java: Interfaz para monstruos con habilidad de regenerarse.
- Enfurecerse.java: Interfaz para monstruos con habilidad de enfurecerse.

Archivos de datos:
- Monstruos.txt: Archivo de entrada con datos de monstruos separados por punto y coma (;).
- Registros.txt: Archivo de persistencia de jugadores y sus puntajes maximos (Nombre;PuntajeMaximo).


INSTRUCCIONES DE EJECUCION

1. Abrir una terminal en la carpeta raiz del proyecto.
2. Compilar todas las clases del paquete taller3:
   javac taller3/*.java
3. Ejecutar la clase principal:
   java taller3.Main

