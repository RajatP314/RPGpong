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
/////////////////////////////

public class Pong extends Application implements EventHandler<InputEvent>{

	Group root;

	public static Canvas c;
	public static GraphicsContext f;
	public static boolean[] keyList;
	public static ArrayList<Entity> entityList;
	public static ArrayList<UIElement> ui;

	public static GameData data;

	Player p = new Player(10.0, 680.0, 70, 20, Color.rgb(165, 75, 12));
	EnemyPaddle ep = new EnemyPaddle(10.0, 100.0, 70, 20, Color.rgb(12, 75, 165), 0.2);
	Ball b = new Ball(300.0, 400.0, 0.0, 4.0, 10);
	Fortress pf = new Fortress(100, 50, 75, p, false);
	Fortress epf = new Fortress(120, 75, 40, ep, true);

	Action itemSpawner = new Action(600);

	Animator animator;
	int state;

	public static void main(String[] args){

		launch();
	}
	public void start(Stage stage){
		state = 0;
		keyList = new boolean[128];
		stage.setTitle("Test JavaFX");
		root = new Group();
		c = new Canvas(600, 800);
		root.getChildren().add(c);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		f = c.getGraphicsContext2D();
		animator = new Animator();
		animator.start();
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, this);
		URL resource = getClass().getResource("test.wav");
		AudioClip clip = new AudioClip(resource.toString());

		entityList = new ArrayList<Entity>();
		p.addToList(entityList);
		b.addToList(entityList);
		ep.addToList(entityList);
		pf.addToList(entityList);
		epf.addToList(entityList);

		Booster q = new Booster(400, 300, 2);
		EnergyOrb eq = new EnergyOrb(200, 200, 40);
		eq.addToList(entityList);
		q.addToList(entityList);

		data = new GameData(c, f, keyList, entityList);
	//	b.effects.add(new SpeedBoost(b, 600, 3));

		HealthBar playerHealth = new HealthBar(0, 780, 600, 20, Color.rgb(180,100,25), pf);
		HealthBar enemyHealth = new HealthBar(0, 0, 600, 20, Color.rgb(55, 80, 180), epf);
		EnergyGauge playerEnergy = new EnergyGauge(0, 770, 600, 10, Color.rgb(180,100,170), pf);
		EnergyGauge enemyEnergy = new EnergyGauge(0, 20, 600, 10, Color.rgb(180, 100, 170), epf);

		ui = new ArrayList<UIElement>();
		ui.add(playerHealth);
		ui.add(enemyHealth);
		ui.add(playerEnergy);
		ui.add(enemyEnergy);

		itemSpawner.setState(true);

		clip.play();
		stage.show();
	}

	public void init(){
		p.x = 10;
		p.y = 680;
		ep.x = 10;
		ep.y = 100;
		entityList = new ArrayList<Entity>();
		p = new Player(10.0, 680.0, 70, 20, Color.rgb(165, 75, 12));
		ep = new EnemyPaddle(10.0, 100.0, 70, 20, Color.rgb(12, 75, 165), 0.2);
		b = new Ball(300.0, 400.0, 0.0, 4.0, 10);
		pf = new Fortress(100, 50, 75, p, false);
		epf = new Fortress(120, 75, 40, ep, true);
		p.addToList(entityList);
		b.addToList(entityList);
		ep.addToList(entityList);
		pf.addToList(entityList);
		epf.addToList(entityList);
		HealthBar playerHealth = new HealthBar(0, 780, 600, 20, Color.rgb(180,100,25), pf);
		HealthBar enemyHealth = new HealthBar(0, 0, 600, 20, Color.rgb(55, 80, 180), epf);
		EnergyGauge playerEnergy = new EnergyGauge(0, 770, 600, 10, Color.rgb(180,100,170), pf);
		EnergyGauge enemyEnergy = new EnergyGauge(0, 20, 600, 10, Color.rgb(180, 100, 170), epf);
		ui = new ArrayList<UIElement>();
		ui.add(playerHealth);
		ui.add(enemyHealth);
		ui.add(playerEnergy);
		ui.add(enemyEnergy);
		data = new GameData(c, f, keyList, entityList);
		itemSpawner.setState(true);
	}

	public void handle(final InputEvent event){
		char key = 0;
		KeyCode code = ((KeyEvent)event).getCode();
		if(code == KeyCode.LEFT){
			key = 37;
		} else if(code == KeyCode.RIGHT){
			key = 39;
		} else if(code == KeyCode.UP){
			key = 38;
		} else if(code == KeyCode.DOWN){
			key = 40;
		} else {
			key = ((KeyEvent)event).getText().charAt(0);
		}
		if(((KeyEvent)event).getEventType() == KeyEvent.KEY_PRESSED){
			keyList[key] = true;
			if(state == 0 && keyList[32]){
				state = 1;
			}
			if((state == 2 || state == 3) && keyList[32]){
				state = 1;
				init();
			}
		}
		if(((KeyEvent)event).getEventType() == KeyEvent.KEY_RELEASED){
			keyList[key] = false;
		}
	}

	public class Animator extends AnimationTimer{
		public void handle(long now){
			f.clearRect(0,0,c.getWidth(), c.getHeight());
			switch(state){
				case 0: //Start Menu
					f.setFill(Color.BLUE);
					f.setFont( new Font("Impact", 32) );
					f.fillText("Press space to begin", 100, 50);
					break;
				case 1: //Actual Gameplay
					//pf.draw(data);
					for(Entity e : entityList){
						if(e != null){
							e.updateConditions(data);
							e.doActions(data);
							for(Action a : e.stimuli){
								if(a.getRecharge() != 0){
									a.updateTimer();
								}
							}
							e.effects = cleanList(e.effects);
							for(Effect ef : e.effects){
								if(ef != null){
									ef.effect();
									ef.decrement();
								}
							}
							e.kinematics(data);
							e.touching = e.getTouching(data);
							e.collisions(data);
							e.draw(data);
							if(e instanceof Player && ((Player)e).fortress.getHealth() <= 0){
								state = 2;
							} else if(e instanceof EnemyPaddle && ((EnemyPaddle)e).fortress.getHealth() <= 0){
								state = 3;
							}
						}
					}
					for(UIElement u : ui){
						u.update();
						u.draw(data);
					}
					itemSpawner.updateTimer();
					if(itemSpawner.ready()){
						itemSpawner.activate();
						spawnItem(entityList);
					}
					break;
				case 2: //Lose
					f.setFill(Color.RED);
					f.setFont( new Font("Times New Roman", 30) );
					f.fillText("You lose! Press space to restart", 100, 200);
					break;
				case 3: //Win
					f.setFill(Color.GREEN);
					f.setFont( new Font("Times New Roman", 30) );
					f.fillText("You win! Press space to restart", 100, 200);
					break;
				default: break;
			}
		}
	}

	public <T> ArrayList<T> cleanList(ArrayList<T> list){
		for(int i=0;i<list.size();i++){
			if(list.get(i) == null){
				list.remove(i);
				i--;
			}
		}
		for(int i=0;i<list.size();i++){
			if(list.get(i) instanceof Entity){
				((Entity)list.get(i)).setIndex(i);
			} else if(list.get(i) instanceof Effect){
				((Effect)list.get(i)).setIndex(i);
			}
		}
		return list;
	}

	public void spawnItem(ArrayList<Entity> entityList){
		int type = (int)(Math.random()*3);
		System.out.println(type);
		int x, y;
		Item item;
		switch(type){
			case 0: //Booster
				x = (int)(Math.random()*570);
				y = (int)(Math.random()*500)+135;
				double boost = Math.random()*1.5 + 1;
				item = new Booster(x, y, boost);
				break;
			case 1: //Energy Orb
				int energy = (int)(Math.random()*20)+20;
				x = (int)(Math.random()*(600-energy));
				y = (int)(Math.random()*(545-energy)) + 135;
				item = new EnergyOrb(x, y, energy);
				break;
			case 2: //Gravity Switch
				x = (int)(Math.random()*570);
				y = (int)(Math.random()*500)+135;
				item = new GravitySwitch(x, y);
				break;
			default:
				item = null;
				break;
		}
		item.addToList(entityList);
	}

}