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

public class GravitySwitch extends Item {

	private int direction;

	public GravitySwitch(double x, double y){
		this.x = x;
		this.y = y;
		this.w = 20;
		this.h = 20;
		this.setColor( Color.hsb(200, 0.9, 0.7) );
		////////////
		this.xv = 0;
		this.yv = 0;
		this.xa = 0;
		this.ya = 0;
		this.maxXV = 0;
		this.maxYV = 0;
		this.frictionX = 0;
		this.frictionY = 0;
		//////////////////////////////////
		stimuli = new ArrayList<Action>();
		effects = new ArrayList<Effect>();
	}

	public void itemEffect(){
		Gravity g = new Gravity(b, 600, direction*0.25);
		deleteSelfFromList();
	}

	public void setDirection(int d){direction = d;}

}