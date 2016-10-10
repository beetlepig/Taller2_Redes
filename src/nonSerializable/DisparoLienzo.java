package nonSerializable;

import processing.core.PApplet;

public class DisparoLienzo {
float xSpaw;
float ySpaw;
float xadd;
float yadd;
float posx;
float posy;
int sender;
PApplet app;
	public DisparoLienzo(float Xuno, float Yuno, float Xdos, float Ydos, PApplet app, int sender) {
		this.xSpaw=Xuno;
		this.ySpaw=Yuno;
		float posXDerJugadorMap= PApplet.map(Xdos-20, 0, 900, 7, 20);
		float posYDerJugadorMap= PApplet.map(Ydos, 0, 700, -5.5F, 5.5F);
		this.xadd=posXDerJugadorMap;
		this.yadd=posYDerJugadorMap;
		this.sender=sender;
		if(this.sender==3){
		this.posx=0;
		}else if(this.sender==2){
		this.posx=900;
		}
		this.posy=PApplet.map(Yuno, 0, 500, 0, 700);
		this.app=app;
		
		
	}
	
	public void pintar(){
		app.fill(255);
		app.ellipse(posx, posy, 15, 15);
		if(sender==3){
		posx+=xadd;
		posy+=yadd;
		}else if(sender==2){
			posx-=xadd;
			posy+=yadd;
		}
	}
	
	

}
