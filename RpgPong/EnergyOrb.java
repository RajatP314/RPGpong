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

public class EnergyOrb extends Item {

	private int energy;

	public EnergyOrb(double x, double y, int e){
		this.x = x;
		this.y = y;
		this.w = 30-e/2;
		this.h = 30-e/2;
		energy = e;
		this.setColor( Color.hsb(300, 0.5, ((double)e)/100) );
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
		if(b.paddle != null){
			b.paddle.fortress.changeEnergy(energy);
			if( b.paddle.fortress.getEnergy() > b.paddle.fortress.getMaxEnergy()){
				b.paddle.fortress.changeEnergy( b.paddle.fortress.getMaxEnergy() - b.paddle.fortress.getEnergy());
			}
		}
		deleteSelfFromList();
	}

}