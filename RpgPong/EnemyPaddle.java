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

public class EnemyPaddle extends Paddle{

	private double difficulty; //Between 0 and 1;

	public EnemyPaddle(double x, double y, int w, int h, Color color, double d){
		super(x, y, w, h, color);
		difficulty = d;
		for(int i=0;i<4;i++){
			stimuli.add(new Action(0));
		}
	}

	public void draw(GameData data){
		data.f.setFill(this.getColor());
		data.f.fillRect(x, y, w, h);
	}

	public void updateConditions(GameData data){
		int index = ballIndex(data);
		boolean shouldMove = Math.abs(data.entityList.get(index).x - x) > w/3 &&
			Math.random() < difficulty;
		stimuli.get(0).setState(shouldMove);
	}

	public void doActions(GameData data){
		if(stimuli.get(0).ready()){
			Entity ball = data.entityList.get(ballIndex(data));
			xa = (int)(difficulty*(ball.x + ball.w/2 - x-w/2)/3);
		}
	}

	public ArrayList<Entity> getTouching(GameData data){return null;}

	public void collisions(GameData data){}

	public int ballIndex(GameData data){
		for(Entity e : data.entityList){
			if(e instanceof Ball){
				return e.getIndex();
			}
		}
		return -1;
	}

}