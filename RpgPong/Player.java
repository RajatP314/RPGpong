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

public class Player extends Paddle{



	public Player(double x, double y, int w, int h, Color color){
		super(x, y, w, h, color);
		for(int i=0;i<4;i++){
			stimuli.add(new Action(0));
		}
	}

	public void draw(GameData data){
		data.f.setFill(this.getColor());
		data.f.fillRect(x, y, w, h);
	}

	public void updateConditions(GameData data){
		for(int i=0;i<4;i++){
			stimuli.get(i).setState(data.keyList[37+i]);
		}
	}

	public void doActions(GameData data){
		if(stimuli.get(0).ready()){
			xa = -2;
		}
		if(stimuli.get(1).ready()){
			ya = -2;
		}
		if(stimuli.get(2).ready()){
			xa = 2;
		}
		if(stimuli.get(3).ready()){
			ya = 2;
		}
	}

	public ArrayList<Entity> getTouching(GameData data){return null;}

	public void collisions(GameData data){};

}