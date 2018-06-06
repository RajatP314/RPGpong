import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.util.*;

public class Fortress extends Entity{

	public static int projectileEnergy = 40;

	private Paddle owner;
	private Color color;

	private int health;
	private int attack;
	private int defense;
	private int energy;
	private int maxEnergy;
	private int maxHealth;

	public Fortress(int health, int a, int d, Paddle p, boolean top){
		owner = p;
		owner.fortress = this;
		color = p.getColor().desaturate().darker();
		this.health = maxHealth = health;
		attack = a;
		defense = d;
		energy = 0;
		maxEnergy = 100;
		x = 0;
		w = 600;
		h = 100;
		if(top){
			y = 0;
		} else {
			y = 700;
		}
		stimuli = new ArrayList<Action>();
		for(int i=0;i<4;i++){
			stimuli.add(new Action(300));
		}
		effects = new ArrayList<Effect>();
	}

	public void draw(GameData data){
		data.f.setFill(this.color);
		data.f.fillRect(x, y, w, h);
	}

	public void updateConditions(GameData data){
		stimuli.get(0).setState( (owner instanceof Player) && (energy >= projectileEnergy) && (data.keyList[32] == true) );
	}

	public void doActions(GameData data){
		if(stimuli.get(0).ready()){
			stimuli.get(0).activate();
			Projectile pr = new Projectile(owner.x+owner.w/2, owner.y-6, 0.0, -10.0, 5, attack);
			pr.addToList(data.entityList);
			energy -= projectileEnergy;
		}
	}

	public ArrayList<Entity> getTouching(GameData data){return null;}
	public void collisions(GameData data){};

	public void damage(int attackPower, int movePower){
		double critChance = 0.05;
		int damage = (int)( (Math.random()*0.25 + 0.75) * movePower*attackPower/this.defense);
		if(Math.random() < critChance){
			damage *= 2;
			System.out.println("Critical hit!");
		}
		this.health -= damage;
	}

	public Color getColor(){return color;}
	public int getHealth(){return health;}
	public int getAttack(){return attack;}
	public int getEnergy(){return energy;}
	public int getMaxEnergy(){return maxEnergy;}
	public void changeEnergy(int e){energy += e;}

}