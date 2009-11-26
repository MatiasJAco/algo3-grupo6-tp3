package modeloTests;

import junit.framework.TestCase;
import modelo.CargadorDeNiveles;
import modelo.Juego;
import modelo.Laberinto;
import modelo.Nivel;
import modelo.Posicion;
import modelo.Punto;

public class PuntoTests extends TestCase {

	private Juego juego;
	private Nivel nivel;
	private CargadorDeNiveles cargador;
	private Laberinto laberinto;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUp();
		this.juego = new Juego();
		this.nivel = null;
		this.laberinto = null;
		this.cargador = new CargadorDeNiveles(juego, this.laberinto);
		this.nivel = cargador.getSiguiente();
		this.laberinto = new Laberinto(nivel, juego);
	}
	
	// Testea que un punto creado en una posicion valida tenga asignada la
	// posicion una vez creado
	public void testCrearPuntoEnPosicionValida() {
		Punto punto = new Punto(new Posicion(1, 1), this.laberinto);
		assertTrue(punto.getPosicion().getX() == 1);
		assertTrue(punto.getPosicion().getY() == 1);
	}

	// Testea que un punto creado en una posicion invalida lance una excepcion
	public void testCrearPuntoEnPosicionInvalida() {
		try {
			Punto punto = new Punto(new Posicion(0, -1), this.laberinto);
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	
	// Testea que un punto recien creado no esta comido
	public void testPuntoRecienCreadoNoEstaComido() {
		Punto punto = new Punto(new Posicion(1, 1), this.laberinto);
		assertTrue(punto.comido() == false);
	}
	
	// Testea que un punto pueda ser ocupado
	public void testPuntoEsOcupable() {
		Punto punto = new Punto(new Posicion(1, 1), this.laberinto);
		assertTrue(punto.esOcupable());
	}
	
	// Testea que comer un punto cambie el estado correctamente
	public void testComerPuntoCambiaElEstado() {
		Punto punto = new Punto(new Posicion(1, 1), this.laberinto);
		punto.comer();
		assertTrue(punto.comido() == true);
	}

}