package serializable;


import java.io.Serializable;

import processing.core.PApplet;
import processing.core.PVector;

public class DisparoJugadorLienzoS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public float x;
	public float y;
	public float xvel;
	public float yvel;
    public String lado;
	public PVector posiciones;
	public PVector velocidad;
	public float angulo;

	public DisparoJugadorLienzoS (PVector pos, PVector vel, float angulo,String lado){
		
		
		this.lado=lado;
		posiciones= new PVector();
		velocidad= new PVector();
		posiciones=pos;
		velocidad= vel;
		this.x=pos.x;
		this.y=pos.y;
		
		this.xvel=vel.x;
		this.yvel=vel.y;
		
	
		
		
		
		
		this.angulo= PApplet.degrees(angulo);
	
		
		
	
		
	}
	
	

}
