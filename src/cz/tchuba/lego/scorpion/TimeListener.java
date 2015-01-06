package cz.tchuba.lego.scorpion;

public abstract class TimeListener {
	
	public abstract void notifyMe();
	
	public TimeListener(Timer timer){
		timer.registerListener(this);
	}
}
