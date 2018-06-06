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

public class Paddle extends Entity{

	private Color color;
	public Fortress fortress;

	public Paddle(double x, double y, int w, int h, Color color){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		////////////
		this.xv = 0;
		this.yv = 0;
		this.xa = 0;
		this.ya = 0;
		this.maxXV = 10;
		this.maxYV = 10;
		this.frictionX = 0.9;
		this.frictionY = 0.9;
		//////////////////////////////////
		stimuli = new ArrayList<Action>();
		effects = new ArrayList<Effect>();
	}

	public void draw(GameData data){
		data.f.setFill(this.color);
		data.f.fillRect((int)x, (int)y, w, h);
	}

	public void updateConditions(GameData data){}

	public void doActions(GameData data){}

	public ArrayList<Entity> getTouching(GameData data){return null;}

	public void collisions(GameData data){};

	public Color getColor(){return color;}

}