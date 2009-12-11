package modelo;

public abstract class Comestible extends Bloque {
	
	public Comestible(Posicion posicion, Laberinto laberinto) {
		super(posicion, laberinto);
	}
	
	public boolean esOcupable(Fantasma unFantasma) {
		return true;
	}
	
	public void comer() {
		this.comido = true;
	}

}
