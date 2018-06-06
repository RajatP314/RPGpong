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

public class Projectile extends Ball{

	public Projectile(double x, double y, double xv, double yv, int r, int ap){
		super(x, y, xv, yv, r);
		attackPower = ap;
	}

	public void draw(GameData data){
		data.f.setFill(Color.rgb(15, 35, 55));
		data.f.fillOval((double)x, (double)y, (double)w, (double)h);
	}

	public void collisions(GameData data){
		for(Entity e : touching){
			if(e instanceof Paddle){
				deleteSelfFromList();
			} else if (e instanceof Fortress){
				((Fortress) e).damage(attackPower, 10);
				deleteSelfFromList();
			} else if (e instanceof Item){
				System.out.println("hit");
				((Item) e).b = this;
				((Item) e).itemEffect();
			}
		}
	}

}