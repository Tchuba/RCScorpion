package cz.tchuba.lego.scorpion;

import java.io.IOException;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;


//test128
public class Robot {

	private final int standardSpeed = 200;
	private final int standardSteering = 300;
	private boolean distance = false, line = false, forward;
	private RobotWiring wiring;
	private Timer timer;
	//private HardReset hardReset;
	private DistanceSafety distanceSafety;
	private EV3LargeRegulatedMotor leftMotor, rightMotor;
	private DifferentialPilot pilot;
	public TextLCD lcd;
	private ConnectionManager connectionManager;
	private RobotSocketReader socketReader;
	private LineSafety lineSafety;

	public void start() {
		forward = true;
		wiring = new RobotWiring();
		lcd = wiring.lcd;
		timer = new Timer();
		
		//hardReset = new HardReset(wiring.touch, this, timer);
		new HardReset(wiring.touch, this, timer);
		
		distanceSafety = new DistanceSafety(wiring.ultrasonic, this, timer);
		lineSafety = new LineSafety(wiring.color, this, timer);
		leftMotor = wiring.leftMotor;
		rightMotor = wiring.rightMotor;
		pilot = new DifferentialPilot(56, 112, leftMotor, rightMotor);
		pilot.setTravelSpeed(standardSpeed);
		connectionManager = new ConnectionManager();
		
		try {
			lcd.drawString("join me at 8080", 1, 3);
			connectionManager.hostConnection();
			socketReader = new RobotSocketReader(connectionManager, this);
			
			new Thread(socketReader).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Thread(timer).start();
			lcd.clear();
			wiring.leds.setPattern(LEDPatterns.STATIC_GREEN);
			Sound.beepSequenceUp();
		}
	}

	public void quit(boolean hardReset) {
		if (Main.runs) {
			Main.runs = false;
			stop("0");			
			Delay.msDelay(100);
			wiring.closeUp();
			connectionManager.closeConnection();
			//lcd.clear();
		}
	}

	public Metrics getMetrics() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateMode(String string) {
		String[] split = string.split(Messages.CONTENT_DELIMITER);
		switch (split[0]) {
		case Messages.MODE_DISTANCE: {
			if (split[1].contains(Messages.ON)) {
				distance = true;
			} else {
				distance = false;
			}
			break;
		}
		case Messages.MODE_LINE: {
			if (split[1].contains(Messages.ON)) {
				line = true;
			} else {
				line = false;
			}
			break;
		}
		case Messages.MODE_PRECISION: {

		}
		}

	}

	public void updateDirections(String string) {
		//test
		if (!Main.runs)
			return;
		String[] split = string.split(":");
		switch (split[0]) {
		case Messages.FORWARD: {
			driveForward(split[1]);
			break;
		}
		case Messages.FORWARD_LEFT: {
			driveLeft(split[1]);
			break;
		}
		case Messages.FORWARD_RIGHT: {
			driveRight(split[1]);
			break;
		}
		case Messages.BACKWARDS: {
			driveBackwards(split[1]);
			break;
		}
		case Messages.BACKWARDS_LEFT: {
			driveBackLeft(split[1]);
			break;
		}
		case Messages.BACKWARDS_RIGHT: {
			driveBackRight(split[1]);
			break;
		}
		case Messages.STOP: {
			stop(split[1]);
			break;
		}
		}

	}

	private boolean checkModes() {
		if ((distance && !distanceSafety.canDrive) || (line && !lineSafety.canDrive)) {
			return false;
		}
		return true;
	}

	private void driveForward(String attribute) {
		// check modes
		forward = true;
		if (checkModes()) {

			pilot.forward();
			/*
			 * leftMotor.setSpeed(standardSpeed);
			 * rightMotor.setSpeed(standardSpeed); leftMotor.forward();
			 * rightMotor.forward();
			 */
		}
	}

	private void driveLeft(String attribute) {
		// check modes
		forward = true;
		if (checkModes()) {
			pilot.arcForward(standardSteering);
			/*
			 * leftMotor.setSpeed(standardSpeed - standardSteering);
			 * rightMotor.setSpeed(standardSpeed + standardSteering);
			 * leftMotor.forward(); rightMotor.forward();
			 */
		}
	}

	private void driveRight(String attribute) {
		// check modes
		forward = true;
		if (checkModes()) {

			pilot.arcForward(-standardSteering);
			/*
			 * leftMotor.setSpeed(standardSpeed + standardSteering);
			 * rightMotor.setSpeed(standardSpeed - standardSteering);
			 * leftMotor.forward(); rightMotor.forward();
			 */
		}
	}

	private void driveBackwards(String attribute) {
		forward = false;

		pilot.backward();
		/*
		 * leftMotor.setSpeed(standardSpeed);
		 * rightMotor.setSpeed(standardSpeed); leftMotor.backward();
		 * rightMotor.backward();
		 */
	}

	private void driveBackLeft(String attribute) {
		forward = false;

		pilot.arcBackward(standardSteering);
		/*
		 * leftMotor.setSpeed(standardSpeed - standardSteering);
		 * rightMotor.setSpeed(standardSpeed + standardSteering);
		 * leftMotor.backward(); rightMotor.backward();
		 */
	}

	private void driveBackRight(String attribute) {
		forward = false;
		pilot.arcBackward(-standardSteering);
		/*
		 * leftMotor.setSpeed(standardSpeed + standardSteering);
		 * rightMotor.setSpeed(standardSpeed - standardSteering);
		 * leftMotor.backward(); rightMotor.backward();
		 */
	}

	private void stop(String attribute) {
		forward = false;
		pilot.stop();
		/*
		 * leftMotor.stop(); rightMotor.stop();
		 */
	}

	public void stopForward() {
		if (forward && ((distance && !distanceSafety.canDrive) || (line && !lineSafety.canDrive))) {
			stop("0");
		}

	}

}
