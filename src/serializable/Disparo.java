package serializable;

import java.io.Serializable;

public class Disparo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public float xMapeado;
public float yMapeado;
public float movimientoX;
public float movimientoY;
public int sender;
	public Disparo(float xmaped, float ymaped, float movimiX, float movimiY, int sender) {
		this.xMapeado=xmaped;
		this.yMapeado=ymaped;
		this.movimientoX= movimiX;
		this.movimientoX= movimiY;
		this.sender=sender;
	}

}
