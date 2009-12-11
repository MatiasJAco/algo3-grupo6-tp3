package vista;

import java.awt.Color;

import titiriteroModificado.Imagen;

import modelo.Posicion;


import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Cuadrado;

public class VistaEstadoJuego extends Cuadrado {
	
	Imagen pacman;
	private EstadoJuego estadoJuego;

	public VistaEstadoJuego(EstadoJuego estado) {
		super(estado.getAncho(),estado.getAlto());
		this.setColor(Color.black);
		this.setPosicionable(estado);
		
		pacman=new Imagen();
		pacman.setNombreArchivoImagen("/vista/pacmanFrame2_24.png");
		this.estadoJuego=estado;
		
		// TODO Auto-generated constructor stub
	}	
	
	public void dibujar(SuperficieDeDibujo superfice){
		super.dibujar(superfice);
		Posicion pos=new Posicion(this.estadoJuego.getX()+10,this.estadoJuego.getY()+10);
		if(estadoJuego.vidas()>0){
			pacman.setPosicionable(pos);
			pacman.dibujar(superfice);
		}
		if(estadoJuego.vidas()>1){
			pos.avanzarHorizontal(25);
			pacman.dibujar(superfice);
		}
		if(estadoJuego.vidas()>2){
			pos.avanzarHorizontal(25);
			pacman.dibujar(superfice);
		}
	}


}
