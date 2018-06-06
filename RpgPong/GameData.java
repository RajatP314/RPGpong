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

public class GameData{

	public Canvas c;
	public GraphicsContext f;
	public boolean[] keyList;
	public ArrayList<Entity> entityList;

	public GameData(Canvas c, GraphicsContext f, boolean[] kl, ArrayList<Entity> el){
		this.c = c;
		this.f = f;
		this.keyList = kl;
		this.entityList = el;
	}

}