package cz.tchuba.lego.scorpion;

import java.util.ArrayList;

public class Timer implements Runnable {
	
	private final int DELAY = 10;
	
	private ArrayList<TimeListener> listeners = new ArrayList<>();
	
	private void fireTimer(){
		for (TimeListener listener: listeners){
			listener.notifyMe();
		}
	}
	
	public void registerListener(TimeListener listener){
		listeners.add(listener);
	}

	@Override
	public void run() {
		while(Main.runs){
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {				
			}
			fireTimer();
		}
		
	}

}
