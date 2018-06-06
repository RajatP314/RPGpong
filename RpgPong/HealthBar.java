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

public class HealthBar extends UIElement<Integer>{

	private int length;
	private int height;
	private Color color;
	public Fortress entity;

	public int maxValue;

	public HealthBar(int x, int y, int l, int h, Color c, Fortress e){
		setX(x);
		setY(y);
		length = l;
		height = h;
		color = c;
		entity = e;
		maxValue = e.getHealth();
		setValue(maxValue);
	}

	public void draw(GameData data){
		data.f.setFill(color);
		data.f.fillRect(getX(), getY(), length*getValue()/maxValue, height);
	}

	public void update(){
		setValue(entity.getHealth());
		if(getValue() < 0){
			setValue(0);
		} else if(getValue() > maxValue){
			setValue(maxValue);
		}
	}

}