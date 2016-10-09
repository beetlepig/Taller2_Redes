package nonSerializable;

import processing.core.PApplet;
import processing.core.PVector;
import serializable.Disparo;

public class DisparoCanon {
	float xJu=250;
	float yJu=250;
	float xJuji;
	float yJuji;
	float xJujidos;
	float yJujidos;
	Logica mundo;
	public DisparoCanon(PVector posplayer, Logica mundo) {
		xJuji= PApplet.map(posplayer.x, 0, 900, 300, 600); 
		yJuji= PApplet.map(posplayer.y, 0, 700, 0,500);
		xJujidos= PApplet.map(xJuji, 300, 600, 10, 11);
		yJujidos= PApplet.map(yJuji, 0, 500, -6F, 6F);
		this.mundo=mundo;
	}
	
	public void pintar(){
	
			if(mundo.com.getIdentifier()==2){
			
			
			
			xJu-=xJujidos;
			yJu+= yJujidos;
			if(xJu<0){
				
				float ysinmap=yJu;
				mundo.com.sendObjectMessage(new Disparo(xJuji, ysinmap, xJujidos, yJujidos, 2));
			
			mundo.disparosDecanon.remove(this);
				}
			
			mundo.app.fill(255);
			mundo.app.ellipse(xJu, yJu, 15, 15);

			}else if(mundo.com.getIdentifier()==3){
				xJu+=xJujidos;
				yJu+= yJujidos;
				if(xJu>500){
					
					float ysinmap=yJu;
					mundo.com.sendObjectMessage(new Disparo(xJuji, ysinmap, xJujidos, yJujidos, 3));
				
				mundo.disparosDecanon.remove(this);
					}
				
				mundo.app.fill(255);
				mundo.app.ellipse(xJu, yJu, 15, 15);
				
				
			}
			
			
			
		
	
	}

}
