package nonSerializable;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import serializable.PosJugador;

public class PersonajeUsuario extends Thread{
Logica mundo;
PImage img;
PVector posiciones;
boolean vivoHilo;
private boolean limiteDer;
private boolean limiteIz;
PVector aceleracion;
private boolean puedeDisparar;
public ArrayList <DisparoJugadorLienzo> disparos;
	public PersonajeUsuario(Logica mundo, PImage perimg) {
	vivoHilo=true;
	this.mundo=mundo;
	this.img=perimg;
	disparos= new ArrayList<>();
	posiciones= new PVector(300,340);
	aceleracion= new PVector();
	new Thread(hiloSleepDisparar()).start();
	}
	
	public void pintar(){
		
        mundo.app.pushMatrix();
        mundo.app.translate(posiciones.x, posiciones.y);
        mundo.app.rotate(PApplet.radians(-mundo.ins.angulo));
		mundo.app.image(img, 30, 0);
		mundo.app.popMatrix();
		for (int i = 0; i < disparos.size(); i++) {
			disparos.get(i).pintar();
		}
	}
	
	public void dispararPapu(){
		if(puedeDisparar){
		disparos.add(new DisparoJugadorLienzo(this.mundo, this.posiciones, this.aceleracion,mundo.ins.angulo,1));
		puedeDisparar=false;
		}
		
	}
	
	public void run(){
	 while(vivoHilo){
		 try {
			 
			 float ymap= PApplet.map(mundo.ins.y, -400, 400, -10, 10);
			 float xmap= PApplet.map(mundo.ins.y, -400, 400, -10, 10);
			 aceleracion.y=-mundo.ins.y;
			 aceleracion.x=mundo.ins.x;
			 posiciones.y+=-ymap;
				
				limitesYmovimiento();
				 
				
				
			 
			 
			sleep(33);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	 }
		
	}
	

	
	private void movimiento(){
		
	}
	
	private Runnable hiloSleepDisparar(){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
				puedeDisparar=true;
				Thread.sleep(500);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				
			}
		};
		
		return r;
	}
	
	private void limitesYmovimiento(){
		
			
			
		 if((posiciones.x>=mundo.app.width-130 && mundo.posMap.x>55) ) {
			
			//mover la posicion del mapa
			mundo.posMap.x-=5;
			
		
			
		} else if((posiciones.x<=145  && mundo.posMap.x<851)){
			mundo.posMap.x+=5;
			
		}
		float xmap= PApplet.map(mundo.ins.x, -400, 400, -10, 10);
		if(mundo.posMap.x-xmap>50 && xmap>0){
			 posiciones.x+=xmap;
			 limiteDer=false;
			 
			
		}else{
			limiteDer=true;
		}
		if (limiteDer && xmap>0){
			if (posiciones.x+xmap<mundo.app.width-130) {
				 posiciones.x+=xmap;
			}
		}
		
		
		
		if(mundo.posMap.x-xmap<841 && xmap<0){
			 posiciones.x+=xmap;
			 limiteIz=true;
			 
			
		}else{
			limiteIz=false;
		}
		if (!limiteIz && xmap<0){
			if (posiciones.x+xmap>50) {
				 posiciones.x+=xmap;
			}
		}
		
	
		
		
		
		if(posiciones.y>70){
			 if(mundo.app.keyCode==PApplet.UP){
				 posiciones.y-=5;
			 }
		} else if(posiciones.y<=70 && mundo.posMap.y<=740){
			mundo.posMap.y+=5;
		}
		
		if(posiciones.y<mundo.app.height-70){
			 if(mundo.app.keyCode==PApplet.DOWN){
				 posiciones.y+=5;
			 }
		}else if(posiciones.y>=mundo.app.height-70 && mundo.posMap.y>-50){
			mundo.posMap.y-=5;
		}
		
		
		
	}
	
	
	

}
