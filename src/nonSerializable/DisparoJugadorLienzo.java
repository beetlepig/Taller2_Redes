package nonSerializable;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import serializable.DisparoJugadorLienzoS;

public class DisparoJugadorLienzo {
	public PVector posi;
	int contador=0;
	float x;
	float y;
	float xvel;
	float yvel;
	PVector velo;
	PVector velBackup;
	private Logica mundo;
	float angulo;
	private float cmabioY;
	private float cambioX;
	int invocador;
	PImage img;
	public DisparoJugadorLienzo (Logica mundo,PVector pos, PVector vel, float angulo, int invocador,PImage img){
		this.invocador=invocador;
		this.mundo=mundo;
		this.img=img;
		posi= new PVector();
		posi.set(pos);
		velo= new PVector();
		velo.set(vel);
		velBackup= new PVector();
		
		this.x=pos.x;
		this.y=pos.y;
		
		this.xvel=vel.x;
		this.yvel=vel.y;
		this.mundo.app.fill(255,0,0);
		velo.setMag(20);
		if(invocador==1){
		if(mundo.ins.isReleased){
			velBackup.set(mundo.ins.posRelease[0],- mundo.ins.posRelease[1]);
			velBackup.setMag(20);
			
		}else{
		velBackup.set(velo);
		}
		}else{
			velBackup.set(velo);	
		}
		if(invocador==1){
		this.angulo= PApplet.radians(-angulo);
		}else{
			this.angulo= PApplet.radians(angulo);
		}
	
		
		evaluarDisparoMuerto();
	
		
	}
	
	public void pintar(){
		
		x+=xvel*2;
		y+=yvel*2;
		
	
		this.mundo.app.pushMatrix();
		this.mundo.app.translate(posi.x+cambioX, posi.y+cmabioY);
		this.mundo.app.rotate(angulo);
		this.mundo.app.image(img,0,0);
		this.mundo.app.popMatrix();
		
	   
		
		
		posi.add(velBackup);
		evaluarChoque();
		evaluarSalidaDelLienzo();
		
		if(contador==2){
			mundo.usuario.disparos.remove(this);
		}
		
		
	}
	private void evaluarChoque(){
		
		
		
		
		/*
		
		for (int i = 0; i < mundo.piedronas.size(); i++) {
			
			
				
				
				
			
		if(PVector.dist(mundo.piedronas.get(i).posiciones, posi)<40){
			mundo.piedronas.remove(i);
			mundo.jugador.disparos.remove(this);
		}
			
			
		
		}
		
		*/
	
	





}
	
	private void evaluarDisparoMuerto(){
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			while(true){
				
				try {
					if(velBackup.x==0 && velBackup.y==0){
						contador++;
					}
					
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}).start();;
		
		
		
		
	}
	
	private void evaluarSalidaDelLienzo(){
		if(invocador==1){
		if(posi.x>905 && (posi.y>100 && posi.y<600)){
			//salida del limite derecho, hacer algo
			mundo.com.sendObjectMessage(new DisparoJugadorLienzoS(posi, velo, angulo, "derecho"));
			
		}
		if(posi.x<-10 && (posi.y>100 && posi.y<600)){
			mundo.com.sendObjectMessage(new DisparoJugadorLienzoS(posi, velo, angulo, "izquierdo"));
		}
		if(posi.x>910 || posi.x<-20 || posi.y>700 || posi.y<-20){
			mundo.usuario.disparos.remove(this);
		}
		}
	}
		

}
