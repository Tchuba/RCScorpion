package cz.tchuba.lego.scorpion;

import lejos.robotics.SampleProvider;

public class DistanceSafety extends TimeListener{

	public boolean canDrive = true;
	
	private SampleProvider ultrasonic;
	private Robot robot;
	private float[] sample;
	private final float SAFE_DISTANCE = 0.30F;
	
	public DistanceSafety(SampleProvider ultrasonic, Robot robot, Timer timer) {
		super(timer);
		this.ultrasonic = ultrasonic;
		this.robot = robot;
		sample = new float[1];
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifyMe() {
		if (Main.runs){
			ultrasonic.fetchSample(sample, 0);
			if (sample[0]<SAFE_DISTANCE){
				robot.stopForward();
				canDrive = false;
			}else{
				canDrive = true;
			}
		}
		
	}

}
