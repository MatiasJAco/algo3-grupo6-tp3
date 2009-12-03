package modelo;




public abstract class Fantasma extends Vivo {

	protected IEstrategia estrategiaNativa;
	protected IEstrategia estrategiaActual;
	private boolean muerto;

		
	public void setEstrategiaActual(IEstrategia unaEstrategia){
		this.estrategiaActual=unaEstrategia;
	}
	
	public IEstrategia getEstrategiaActual(){
		return estrategiaActual;
		
	}
	
	public void restablecerEstrategiaNativa(){
		this.estrategiaActual=estrategiaNativa;
				
	}
	
	public void vivir(){
		if(muerto&&estoyEnLaPuerta()){
			entrarACasa();
		}
		else{
		if (laberinto.cambioDeDireccionPermitido(this.posicion)){
			this.moverse(this.estrategiaActual.getDestino());			
		}
		else {
			mover();
		}
		if (this.laberinto.mismoBloque(this.posicion,this.laberinto.getPacman().getPosicion()))
				if(this.laberinto.getJuego().puntoDePoderActivo()){ 
					this.comer();}
				else{
					this.laberinto.getPacman().comer();
				}
					
		}				
	}
	
	private void entrarACasa() {
		int difX=laberinto.obtenerPosicionCasa().getX()-posicion.getX();
		int difY=laberinto.obtenerPosicionCasa().getY()-posicion.getY();
		if(difX>0)
			posicion.avanzarHorizontal(velocidad);
		if(difX<0)
			posicion.retrocederHorizontal(velocidad);
		if(difY>0)
			posicion.avanzarVertical(velocidad);
		if(difY<0)
			posicion.retrocederVertical(velocidad);
		if(difX==0&&difY==0){
			muerto=false;
			this.restablecerEstrategiaNativa();
		}
		
	}

	private boolean estoyEnLaPuerta() {
		int d=(int)laberinto.distancia(posicion,laberinto.obtenerPosicionCasa());
		if(d<=Laberinto.getTamanoDelBloque()*2) return true;
		return false;
	}

	/* Le informa al juego que el fantasma fue comido.
	  
	 */
	
	public void comer(){
		muerto=true;
		Juego juegoActual=this.laberinto.getJuego();
		juegoActual.fantasmaComido(this);
		
	}

	public Laberinto getLaberinto() {
		return this.laberinto;
	}
	

	/* Los fantasmas se mueven eligiendo el casillero que menor distancia tiene a su objetivo
	  
	 */
	
	public void moverse(Posicion destino){
		

		Posicion posicionPosibleIzquierda=laberinto.getPosicionBloqueAnteriorHorizontal(posicion);
		Posicion posicionPosibleAbajo=laberinto.getPosicionBloqueAnteriorVertical(posicion);
		Posicion posicionPosibleDerecha=laberinto.getPosicionBloqueSiguienteHorizontal(posicion);
		Posicion posicionPosibleArriba=laberinto.getPosicionBloqueSiguienteVertical(posicion);
		double distanciaMinima=1000;
		int numeroDeOrdenDireccionInicial=this.direccion.ordinal();
		if ((numeroDeOrdenDireccionInicial!=Direccion.Abajo.ordinal())&&(esAdecuadoMoverse(destino, posicionPosibleArriba, distanciaMinima))){
			distanciaMinima=laberinto.distancia(laberinto.getBloqueEnPosicion(posicionPosibleArriba),laberinto.getBloqueEnPosicion(destino));
			direccion=Direccion.Arriba;
		}
		if ((numeroDeOrdenDireccionInicial!=Direccion.Arriba.ordinal())&& esAdecuadoMoverse(destino, posicionPosibleAbajo, distanciaMinima)){
			distanciaMinima=laberinto.distancia(laberinto.getBloqueEnPosicion(posicionPosibleAbajo),laberinto.getBloqueEnPosicion(destino));
			direccion=Direccion.Abajo;			
		}
		if ((numeroDeOrdenDireccionInicial!=Direccion.Izquierda.ordinal())&& esAdecuadoMoverse(destino, posicionPosibleDerecha, distanciaMinima)){
			distanciaMinima=laberinto.distancia(laberinto.getBloqueEnPosicion(posicionPosibleDerecha),laberinto.getBloqueEnPosicion(destino));
			direccion=Direccion.Derecha;
		}
		if ((numeroDeOrdenDireccionInicial!=Direccion.Derecha.ordinal())&& esAdecuadoMoverse(destino, posicionPosibleIzquierda, distanciaMinima)){
			distanciaMinima=laberinto.distancia(laberinto.getBloqueEnPosicion(posicionPosibleIzquierda),laberinto.getBloqueEnPosicion(destino));
			direccion=Direccion.Izquierda;
		}
		this.mover();	
				
	}

	private boolean esAdecuadoMoverse(Posicion destino,
			Posicion posicionPosible, double distanciaMinima) {
		
		return ((laberinto.getBloqueEnPosicion(posicionPosible).esOcupable())&& (laberinto.distancia(laberinto.getBloqueEnPosicion(posicionPosible),laberinto.getBloqueEnPosicion(destino))<distanciaMinima));
			
		
	}

	public void setVelocidad(int i) {
		this.velocidad=1;
		
	}
	
}

	