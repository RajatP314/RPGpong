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

public class EnergyGauge extends HealthBar{

	public EnergyGauge(int x, int y, int l, int h, Color c, Fortress e){
		super(x, y, l, h, c, e);
		maxValue = e.getMaxEnergy();
		setValue(e.getEnergy());
		System.out.println(getValue());
	}

	public void update(){
		setValue(entity.getEnergy());
		if(getValue() < 0){
			setValue(0);
		} else if(getValue() > maxValue){
			setValue(maxValue);
		}
	}

}