package nonSerializable;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import serializable.Disparo;
import serializable.DisparoJugadorLienzoS;
import serializable.InstruccionAndroid;
import serializable.PosJugador;

public class Logica implements Observer{
PApplet app;
PersonajeUsuario usuario;
Comunicacion com;
public Canon diparadorIz;
Canon diparadorDer;
boolean moverCanones;
ArrayList<DisparoLienzo> disparitos;
ArrayList<DisparoCanon> disparosDecanon;


//-----------------------------------------IMAGES--------------------------------------
private ArrayList<PImage> fondoArbolesBancas;
private PImage perImg;
private PImage baseCanon;
private PImage canon;
private PImage fondoDeTierra;
public PImage cohete;
public PImage coheteEnemigo;
private PImage imgVida;
public InstruccionAndroid ins;
private PVector posPlayer;

//-------------------------------------------------------------------------------------
PVector posMap;
float xJu=250;
float yJu=250;
PVector posiciones= new PVector();
PVector aceleracion= new PVector();
PVector velocidad= new PVector();

	public Logica(PApplet app) {
		this.app=app;
		loadImages();
		this.com= new Comunicacion();
		new Thread(com).start();
		com.addObserver(this);
	
		if(com.getIdentifier()==1){
			float[] f= new float[2];
			f[0]=0;
			f[1]=1;
			ins= new InstruccionAndroid(0, 0, 0, 0, 0, f, false, "0");
		
		this.usuario= new PersonajeUsuario(this,perImg,imgVida);
	
		usuario.start();
		
		disparitos= new ArrayList<>();
		
		posMap=new PVector(841,750);
		new Thread(mandarposis()).start();
		new Thread(evaluarChoqueConJugador()).start();
		}else if(com.getIdentifier()==2){
			disparosDecanon= new ArrayList<>();
			posPlayer= new PVector();
			diparadorIz= new Canon(app,canon,com.getIdentifier());
		}else if (com.getIdentifier()==3){
			disparosDecanon= new ArrayList<>();
			posPlayer= new PVector();
			diparadorDer= new Canon(app, canon,com.getIdentifier());
			String s= "ComenzoEsto";
			com.sendObjectMessage(s);
          
			
		}
	}
	
	private void loadImages(){
		fondoArbolesBancas= new ArrayList<>();
		fondoArbolesBancas.add(app.loadImage("fondo-02.png"));
		perImg= app.loadImage("carrazo-04.png");
		fondoDeTierra= app.loadImage("fondoTierra-03.png");
		baseCanon= app.loadImage("baseChango-03.png");
		canon= app.loadImage("canon-03.png");
		cohete= app.loadImage("misolote-ali-04.png");
		coheteEnemigo= app.loadImage("misolote-04.png");
		imgVida= app.loadImage("barraVida-04.png");
	}
	
	public void pintar(){
		if(com.getIdentifier()==1){
		app.image(fondoArbolesBancas.get(0), posMap.x, posMap.y);
		usuario.pintar();
		
		if(disparitos!=null){
		
		for (int i = 0; i < disparitos.size(); i++) {
			disparitos.get(i).pintar();
		}
		
		}
		} else if(com.getIdentifier()==2){
			app.image(fondoDeTierra, 250, 250);
			app.image(baseCanon, 260, 250);
			
			
			diparadorIz.pintar(posPlayer);
			if(diparadorIz.disparar){
				  disparosDecanon.add(new DisparoCanon(posPlayer,this));
				 diparadorIz.disparar=false; 
			}
			
			for (int i = 0; i < disparosDecanon.size(); i++) {
				disparosDecanon.get(i).pintar();
				
			}
			
		} else if(com.getIdentifier()==3){
			app.image(fondoDeTierra, 250, 250);
			app.image(baseCanon, 250, 250);
			
			diparadorDer.pintar(posPlayer);
			if(diparadorDer.disparar){
				  disparosDecanon.add(new DisparoCanon(posPlayer,this));
				 diparadorDer.disparar=false; 
			}
			
			for (int i = 0; i < disparosDecanon.size(); i++) {
				disparosDecanon.get(i).pintar();
				
			}
		
		
			
		}
		
		
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
	 if(com.getIdentifier()==1){
		if(arg instanceof InstruccionAndroid){
		InstruccionAndroid	inss= (InstruccionAndroid) arg;
		

			this.ins=inss;
			if(ins.instrucccionExtra.equalsIgnoreCase("shoot")){
				usuario.dispararPapu();
			}
			
			
		}
		if(arg instanceof String){
			String aprovacion= (String) arg;
			if(aprovacion.equalsIgnoreCase("ComenzoEsto")){
				moverCanones=true;
				
			}
			
			
		}
		
		if(arg instanceof Disparo){
			Disparo dispi= (Disparo) arg;
			disparitos.add(new DisparoLienzo(dispi.xMapeado, dispi.yMapeado, usuario.posiciones.x, usuario.posiciones.y, app,dispi.sender));
		}
	 }
	 if(arg instanceof String){
		 String aprovacion= (String) arg;
		 if(aprovacion.equalsIgnoreCase("ComenzoEsto")){
	 if(com.getIdentifier()==2){
		 
			new Thread(diparadorIz.disparo()).start();
		}
	   if(com.getIdentifier()==3){
		   new Thread(diparadorDer.disparo()).start();
	   }
		 }
	 }
		
		
		if(com.getIdentifier()==2 || com.getIdentifier()==3){
			if(arg instanceof PosJugador){
				PosJugador posisi= (PosJugador) arg;
				posPlayer.set(posisi.x, posisi.y);
				
			}
			
		}
		
		if(com.getIdentifier()==2){
			if(arg instanceof DisparoJugadorLienzoS){
				DisparoJugadorLienzoS posisYDemas= (DisparoJugadorLienzoS) arg;
				if(posisYDemas.lado.equalsIgnoreCase("derecho")){
				PVector posis= new PVector(0,posisYDemas.posiciones.y-100);
				diparadorIz.disparos.add(new DisparoJugadorLienzo(this, posis, posisYDemas.velocidad, posisYDemas.angulo,2,cohete));
			}
			}
		}
		if(com.getIdentifier()==3){
			if(arg instanceof DisparoJugadorLienzoS){
				DisparoJugadorLienzoS posisYDemas= (DisparoJugadorLienzoS) arg;
				if(posisYDemas.lado.equalsIgnoreCase("izquierdo")){
				PVector posis= new PVector(510,posisYDemas.posiciones.y-100);
				diparadorDer.disparos.add(new DisparoJugadorLienzo(this, posis, posisYDemas.velocidad, posisYDemas.angulo,2,cohete));
			}
			}
		}
		
		
		
		
		
		
		
	}
	
	private Runnable mandarposis(){
		Runnable r= new Runnable() {
			
			
			public void run() {
				while(true){
					try{
						if(moverCanones){
					
				com.sendObjectMessage(new PosJugador(usuario.posiciones.x, usuario.posiciones.y));
						}
				Thread.sleep(33);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		return r;
	}
	
	private Runnable evaluarChoqueConJugador(){
		Runnable r =new Runnable() {
			
			
			public void run() {
				while(true){
					try{
				for (int i = 0; i < disparitos.size(); i++) {
					if(PApplet.dist(disparitos.get(i).posx, disparitos.get(i).posy, usuario.posiciones.x, usuario.posiciones.y)<40){
						if(usuario.vidas>0){
						usuario.vidas-=1;
						}
						disparitos.remove(i);
					}
					
				}
				Thread.sleep(20);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
				
			}
		};
		
		return r;
		
	}


	
	
	
	

}
