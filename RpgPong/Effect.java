import java.util.*;

public abstract class Effect{

	public Entity owner;
	private int duration;
	private int timer;
	public int index;

	public abstract void effect();
	public abstract void undo();

	public Effect(Entity e, int d){
		owner = e;
		duration = d;
		timer = d;
		index = e.effects.size();
		e.effects.add(this);
		System.out.println(e.effects);
	}

	public void deleteSelfFromList(){
		System.out.println(owner.effects);
		owner.effects.set(this.index, null);
		System.out.println(owner.effects);
	}

	public void decrement(){
		timer--;
		if(timer <= 0){
			deleteSelfFromList();
			undo();
		}
	}

	public void setIndex(int i){index = i;}

}