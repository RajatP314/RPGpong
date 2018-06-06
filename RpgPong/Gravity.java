public class Gravity extends Effect{

	private double acceleration;

	public Gravity(Entity e, int d, double g){
		super(e, d);
		acceleration = g;
		effect();
	}

	public double getAcceleration(){return acceleration;}

	public void effect(){
		owner.ya = acceleration;
	}

	public void undo(){
		owner.ya = 0;
	}

}