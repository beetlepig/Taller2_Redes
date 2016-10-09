package nonSerializable;
import processing.core.PApplet;

public class Main extends PApplet {
Logica log;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}
	
	public void settings(){
	    size(50,50);
	}
	
	public void setup(){
	imageMode(CENTER);
	log= new Logica(this);
	surface.setResizable(true);
	if(log.com.getIdentifier()==1){
		surface.setSize(900, 700);
	}else if(log.com.getIdentifier()==2){
		surface.setSize(500, 500);
	} else if(log.com.getIdentifier()==3){
		surface.setSize(500, 500);
	}
	
	
	
	}
	
	public void draw(){
		background(255);
		log.pintar();
	}


}
