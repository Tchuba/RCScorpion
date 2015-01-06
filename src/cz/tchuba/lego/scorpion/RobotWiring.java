package cz.tchuba.lego.scorpion;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.LED;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class RobotWiring {

	private final Port leftMotorPort = MotorPort.B;
	private final Port rightMotorPort = MotorPort.C;
	private final Port touchPort = SensorPort.S1;
	private final Port ultrasonicPort = SensorPort.S4;
	private final Port colorPort = SensorPort.S3;
	//private final Port gyroPort = SensorPort.S2;

	public EV3LargeRegulatedMotor leftMotor, rightMotor;
	
	//private EV3GyroSensor gyroSensor;
	private EV3ColorSensor colorSensor;
	private EV3TouchSensor touchSensor;
	private EV3UltrasonicSensor ultrasonicSensor;

	public SampleProvider gyro, color, touch, ultrasonic;

	public EV3 robot;
	public TextLCD lcd;
	public Keys keys;
	public LED leds;

	public RobotWiring() {
		robot = (EV3) BrickFinder.getLocal();
		lcd = robot.getTextLCD();
		//Sound.beep();
		lcd.drawString("Initializing...", 1, 1);
		leds = robot.getLED();
		leds.setPattern(LEDPatterns.SLOW_RED);
		keys = robot.getKeys();

		leftMotor = new EV3LargeRegulatedMotor(leftMotorPort);
		leftMotor.setAcceleration(3000);
		rightMotor = new EV3LargeRegulatedMotor(rightMotorPort);

		touchSensor = new EV3TouchSensor(touchPort);
		ultrasonicSensor = new EV3UltrasonicSensor(ultrasonicPort);
		colorSensor = new EV3ColorSensor(colorPort);
		//gyroSensor = new EV3GyroSensor(gyroPort);
		
		//gyro = gyroSensor.getAngleMode();
		color = colorSensor.getRedMode();
		touch = touchSensor.getTouchMode();		
		ultrasonic = ultrasonicSensor.getDistanceMode();
		//Sound.beepSequenceUp();
		leds.setPattern(LEDPatterns.SLOW_YELLOW);
		lcd.drawString("Robot initialized!", 1, 2);

	}
	
	public void closeUp(){
		//gyroSensor.close();
		colorSensor.close();
		touchSensor.close();
		ultrasonicSensor.close();
	}

}
