package serializable;

import java.io.Serializable;

public class PosJugador implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public float x;
public float y;

	public PosJugador(float x, float y) {
	this.x=x;
	this.y=y;
	}

}
