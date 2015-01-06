package cz.tchuba.lego.scorpion;

public class Main {
	
	public static boolean runs = true;

	public static void main(String[] args) throws InterruptedException {
		
//		RobotWiring wiring = new RobotWiring();
//		float [] sample = new float[wiring.color.sampleSize()];
//		wiring.lcd.clear();
//		wiring.lcd.drawString("Color Sensor test", 1, 1);
//		wiring.lcd.drawString("Sample size: " + wiring.color.sampleSize(), 1, 2);
//		Delay.msDelay(1000);
//		for (int i=0; i<100;i++){
//			wiring.color.fetchSample(sample, 0);
//			wiring.lcd.drawString("Sample: " + sample[0] + "         ", 1, 4);
//			Delay.msDelay(100);
//		}
		
		Robot robot = new Robot();
		robot.start();
		
		
		
		
		
		
		
//		Thread.sleep(10000);
//		robot.updateMode(Messages.MODE_DISTANCE + ":" + Messages.ON);
//		
//		robot.updateDirections("fw:100");
//		Thread.sleep(2000);
//		robot.updateDirections("fr:100");
//		Thread.sleep(2000);
//		//robot.updateMode(Messages.MODE_DISTANCE + ":" + Messages.OFF);
//		robot.updateDirections("fl:100");
//		Thread.sleep(2000);
//		//robot.updateMode(Messages.MODE_DISTANCE + ":" + Messages.ON);
//		robot.updateDirections("bw:100");
//		Thread.sleep(2000);
//		//robot.updateDirections(Messages.STOP + ":100");
//		//robot.quit(false);
		
//		RobotWiring wiring = new RobotWiring();
//		float [] sample = new float[wiring.color.sampleSize()];
//		Delay.msDelay(2000);
//		wiring.lcd.clear();
//		wiring.lcd.drawString("Testing wheels LR", 1, 1);
//		wiring.leftMotor.setSpeed(500);
//		wiring.leftMotor.forward();
//		Delay.msDelay(500);
//		
//		wiring.rightMotor.setSpeed(500);
//		wiring.rightMotor.forward();
//		Delay.msDelay(1000);
//		wiring.leftMotor.stop();
//		wiring.rightMotor.stop();
//		
//		wiring.lcd.clear();
//		wiring.lcd.drawString("Color Sensor test", 1, 1);
//		wiring.lcd.drawString("Sample size: " + wiring.color.sampleSize(), 1, 2);
//		Delay.msDelay(1000);
//		for (int i=0; i<100;i++){
//			wiring.color.fetchSample(sample, 0);
//			wiring.lcd.drawString("Sample: " + sample[0] + "         ", 1, 4);
//			Delay.msDelay(100);
//		}
//		
//		wiring.lcd.clear();
//		wiring.lcd.drawString("Touch Sensor test", 1, 1);
//		wiring.lcd.drawString("Sample size: " + wiring.touch.sampleSize(), 1, 2);
//		Delay.msDelay(1000);
//		for (int i=0; i<100;i++){
//			wiring.touch.fetchSample(sample, 0);
//			wiring.lcd.drawString("Sample: " + sample[0] + "         ", 1, 4);
//			Delay.msDelay(100);
//		}
//		
//		wiring.lcd.clear();
//		wiring.lcd.drawString("Ultrasonic Sensor test", 1, 1);
//		wiring.lcd.drawString("Sample size: " + wiring.ultrasonic.sampleSize(), 1, 2);
//		Delay.msDelay(1000);
//		for (int i=0; i<100;i++){
//			wiring.ultrasonic.fetchSample(sample, 0);
//			wiring.lcd.drawString("Sample: " + sample[0] + "         ", 1, 4);
//			Delay.msDelay(100);
//		}
//		wiring.closeUp();
	}

}
