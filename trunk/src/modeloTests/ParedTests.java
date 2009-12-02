package modeloTests;

import modelo.Juego;
import modelo.Laberinto;
import modelo.Posicion;
import modelo.Pared;
import junit.framework.TestCase;

public class ParedTests extends TestCase {

	private Juego juego;
	private Laberinto laberinto;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUp();
		this.juego = new Juego();
		this.laberinto = juego.getLaberinto();

	}
	
	// Testea que una pared creada en una posicion valida tenga asignada la
	// posicion una vez creada
	public void testCrearParedEnPosicionValida() {
		Pared pared = new Pared(new Posicion(1, 1), this.laberinto);
		assertTrue(pared.getPosicion().getX() == 1);
		assertTrue(pared.getPosicion().getY() == 1);
	}

	// Testea que una pared creada en una posicion invalida lance una excepcion
	public void testCrearParedEnPosicionInvalida() {
		try {
			@SuppressWarnings("unused")
			Pared pared = new Pared(new Posicion(0, -1), this.laberinto);
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	
	// Testea que una pared recien creada no este comida
	public void testParedRecienCreadaNoEstaComida() {
		Pared pared = new Pared(new Posicion(1, 1), this.laberinto);
		assertTrue(pared.comido() == false);
	}
	
	// Testea que una pared no pueda ser ocupada
	public void testParedNoEsOcupable() {
		Pared pared = new Pared(new Posicion(1, 1), this.laberinto);
		assertFalse(pared.esOcupable());
	}
	
	// Testea que comer una pared no pueda ser comida
	public void testComerParedCambiaElEstado() {
		Pared pared = new Pared(new Posicion(1, 1), this.laberinto);
		pared.comer();
		assertFalse(pared.comido());
	}

}
