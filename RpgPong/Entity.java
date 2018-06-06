import javafx.animation.AnimationTimer;
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

public abstract class Entity{

	public double x, y; //Position
	public double xv, yv; //Velocity
	public double xa, ya; //Acceleration
	public int w, h; //Dimensions

	public int maxXV, maxYV; //Max velocity
	public double frictionX, frictionY;

	private ArrayList<Entity> list; //For identifying the entity
	private int index;

	public ArrayList<Action> stimuli; //Action objects help to regulate when actions occur
	public ArrayList<Effect> effects; //List of active effects
	public ArrayList<Entity> touching; //List of all entities this thing is touching

	public abstract void draw(GameData data);
	public abstract void updateConditions(GameData data); //Check which actions can be done
	public abstract void doActions(GameData data); //Methods associated with actions are unique to the entity
	public abstract ArrayList<Entity> getTouching(GameData data);
	public abstract void collisions(GameData data);

	public void kinematics(GameData data){
		xv+=xa;
		yv+=ya;
		if(xv > maxXV){
			xv = maxXV;
		}
		if(yv > maxYV){
			yv = maxYV;
		}
		x+=xv;
		y+=yv;
		xv*=frictionX;
		yv*=frictionY;
		xa = ya = 0;
		if(x+w > (int) data.c.getWidth()){
			x = (int) data.c.getWidth() - w;
		}
		if(x < 0){
			x = 0;
		}
		if(y+h > (int) data.c.getHeight()){
			y = (int) data.c.getHeight() - h;
		}
		if(y < 0){
			y = 0;
		}

	}

	public void addToList(ArrayList<Entity> entityList){
		this.index = entityList.size();
		entityList.add(this);
		this.list = entityList;
	}

	public void deleteSelfFromList(){
		this.list.set(this.index, null);
	}

	public ArrayList<Entity> getList(){ return list; }
	public int getIndex(){ return index; }

	public void setIndex(int i){index = i;}

}