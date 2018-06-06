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

public class Ball extends Entity{

	private int r;
	public Paddle paddle;
	public int attackPower;

	public Ball(double x, double y, double xv, double yv, int r){
		this.x = x;
		this.y = y;
		this.r = r;
		this.w = this.h = 2*r;
		this.xv = xv;
		this.yv = yv;
		this.attackPower = 5;
		/////////////
		this.xa = 0;
		this.ya = 0;
		this.maxXV = 10;
		this.maxYV = 10;
		this.frictionX = 1;
		this.frictionY = 1;
		//////////////////////////////////
		stimuli = new ArrayList<Action>();
		for(int i=0;i<2;i++){
			stimuli.add(new Action(0));
		}
		effects = new ArrayList<Effect>();
		touching = new ArrayList<Entity>();
		paddle = null;
	}

	public void draw(GameData data){
		data.f.setFill(Color.BLACK);
		data.f.fillOval(x, y, (double)w, (double)h);
	}

	public void updateConditions(GameData data){
		stimuli.get(0).setState(x <= 0 || x+w >= data.c.getWidth());
		stimuli.get(1).setState(y <= 0 || y+h >= data.c.getHeight());
	}
	public void doActions(GameData data){
		if(stimuli.get(0).ready()){
			xv *= -1;
		}
		if(stimuli.get(1).ready()){
			yv *= -1;
		}
	}

	public ArrayList<Entity> getTouching(GameData data){
		touching.clear();
		for(Entity e : data.entityList){
			if(e != null && e != this){
				Rectangle2D self = new Rectangle2D(x, y, w, h);
				Rectangle2D other = new Rectangle2D(e.x, e.y, e.w, e.h);
				if(self.intersects(other)){
					touching.add(e);
				}
			}
		}
		return touching;
	}

	public void collisions(GameData data){
		for(Entity e : touching){
			if(e instanceof Paddle){
				if(e.y < y){
					y = e.y+e.h+1;
				} else {
					y = e.y-h-1;
				}
				if(x+w/2 >= e.x && x+w/2 <= e.x+e.w){
					yv *= -1;
					xv -= e.xv/2;
				} else {
					yv *=-1;
					xv *= -1;
				}
				this.paddle = (Paddle) e;
				this.attackPower = paddle.fortress.getAttack();
			} else if (e instanceof Fortress){
				if(e.y < y){
					y = e.y+e.h+1;
				} else {
					y = e.y-h-1;
				}
				((Fortress) e).damage(attackPower, 10);
				yv *= -1;

			} else if (e instanceof Item){
				System.out.println("hit");
				((Item) e).b = this;
				if(e instanceof GravitySwitch){
					if(paddle instanceof Player){
						((GravitySwitch)e).setDirection(1);
					} else {
						((GravitySwitch)e).setDirection(-1);
					}
				}
				((Item) e).itemEffect();
			} else if (e instanceof Projectile){
				e.deleteSelfFromList();
			}
		}
	}

}