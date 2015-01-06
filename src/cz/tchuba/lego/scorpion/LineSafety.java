package cz.tchuba.lego.scorpion;

import lejos.robotics.SampleProvider;

public class LineSafety extends TimeListener {

	public boolean canDrive = true;

	private SampleProvider color;
	private Robot robot;
	private float[] sample;
	private final float BLACK_SPECTRUM = 0.6F;

	public LineSafety(SampleProvider color, Robot robot, Timer timer) {
		super(timer);
		this.color = color;
		this.robot = robot;
		sample = new float[1];
	}

	@Override
	public void notifyMe() {
		if (Main.runs){
			color.fetchSample(sample, 0);
			if (sample[0]<BLACK_SPECTRUM){
				robot.stopForward();
				canDrive = false;
			}else{
				canDrive = true;
			}
		}
		

	}

}
