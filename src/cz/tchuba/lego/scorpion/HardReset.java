package cz.tchuba.lego.scorpion;

import lejos.robotics.SampleProvider;

public class HardReset extends TimeListener {

	private final int maxTics = 50;
	private int ticks;
	private SampleProvider button;
	private float[] sample = new float[1];
	private Robot robot;

	public HardReset(SampleProvider button, Robot robot, Timer timer) {
		super(timer);
		this.robot = robot;
		this.button = button;
		ticks = 0;
	}

	@Override
	public void notifyMe() {
		if (Main.runs) {
			button.fetchSample(sample, 0);
			if (sample[0] == 0) {
				ticks = 0;
				
			} else {
				ticks++;

				robot.lcd.drawString("Ticks on pressed:", 1, 4);
				robot.lcd.drawString("" + ticks, 4, 5);

				if (ticks == maxTics) {
					robot.quit(true);
				}
			}
		}

	}

}
