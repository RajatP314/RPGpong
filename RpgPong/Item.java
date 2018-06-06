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

public abstract class Item extends Entity{

	private Color color;
	public Ball b;

	public abstract void itemEffect();

	public void draw(GameData data){
		data.f.setFill(color);
		data.f.fillRect((int)x, (int)y, w, h);
	}

	public Color getColor(){return color;}
	public void setColor(Color c){color = c;}

	public void updateConditions(GameData data){}
	public void doActions(GameData data){}
	public ArrayList<Entity> getTouching(GameData data){return null;}
	public void collisions(GameData data){};

}