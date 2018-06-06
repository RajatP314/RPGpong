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

public abstract class UIElement<T>{

	private T value;
	private int xpos, ypos;

	public abstract void draw(GameData data);
	public abstract void update();

	public int getX(){return xpos;}
	public int getY(){return ypos;}
	public T getValue(){return value;}
	public void setValue(T val){value = val;}
	public void setX(int x){xpos = x;}
	public void setY(int y){ypos = y;}

}