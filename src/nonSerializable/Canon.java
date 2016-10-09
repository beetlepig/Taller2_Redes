package nonSerializable;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Canon {
	public PVector canonVector;
	PApplet app;
	PImage img;
	private int identificador;
	float angulo;
	public boolean disparar;
    int sleepHilo;
    ArrayList<DisparoJugadorLienzo> disparos;
public Canon(PApplet app, PImage img, int iden){
	this.app=app;
	this.img=img;
	canonVector= new PVector();
	canonVector.set(250,250);
	disparos= new ArrayList<>();
	this.identificador=iden;
	if(identificador==2){
		this.sleepHilo=8000;
	}else if(identificador==3){
		this.sleepHilo=4000;
	}

	
}

public void pintar(PVector posPlayer){
	float x= PApplet.map(posPlayer.x, 0, 900, 500, 1000);
	float y= PApplet.map(posPlayer.y, -10, 710, 0, 500);
	if(identificador==2){
	    angulo = PApplet.atan2(canonVector.y-y, x-canonVector.x);
	    
	} else if(identificador==3){
		angulo = PApplet.atan2(canonVector.y-y, canonVector.x-x);	
	}
	
	
	app.pushMatrix();
	app.translate(250, 250);
	app.rotate(angulo);
	app.image(img, -15, 0);
	app.popMatrix();
	for (int i = 0; i < disparos.size(); i++) {
		disparos.get(i).pintar();
		
	}

}

public Runnable disparo(){
	Runnable r= new Runnable() {
		
		@Override
		public void run() {
			while(true){
				try{
					disparar=true;
					Thread.sleep(sleepHilo);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}
	};
	
	return r;
	
}


}
