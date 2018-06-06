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

public class Booster extends Item {

	private double boost;

	public Booster(double x, double y, double b){
		this.x = x;
		this.y = y;
		this.w = 20;
		this.h = 20;
		boost = b;
		this.setColor( Color.hsb(120, 0.5, 0.75*b/2.5) );
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
		SpeedBoost sb = new SpeedBoost(b, 600, boost);
		deleteSelfFromList();
	}

}