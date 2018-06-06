public class Action{

	private boolean canExecute;
	private int recharge;
	private int timer;

	public Action(int recharge){
		this.recharge = recharge;
		this.timer = 0;
		this.canExecute = false;
	}

	public boolean ready(){
		return timer == 0 && canExecute == true;
	}

	public void setState(boolean b){
		canExecute = b;
	}

	public void updateTimer(){
		if(timer > 0){
			timer--;
		}
	}

	public void activate(){
		timer = recharge;
	}

	public int getRecharge(){return recharge;}

}

