package modelo;

public class ParedVertical extends NoComestible {

	public ParedVertical(Posicion posicion,Laberinto laberinto) {
		super(posicion, laberinto);
	}

	public boolean esOcupablePorFantasma(Fantasma unFantasma) {
		return false;
	}

	@Override
	public boolean esOcupablePorPacman() {
		return false;
	}

	
}
