public class SpeedBoost extends Effect{

	private double multiplier;
	private double m;

	public SpeedBoost(Entity e, int d, double m){
		super(e, d);
		this.m = m;
		this.multiplier = m;
		effect();
		this.m = 1;
	}

	public double getMultiplier(){return multiplier;}

	public void effect(){
		owner.xv *= m;
		owner.yv *= m;
	}

	public void undo(){
		owner.xv /= multiplier;
		owner.yv /= multiplier;
	}

}