package cz.tchuba.lego.scorpion;

import java.io.IOException;
import java.io.OutputStream;

public class RobotSocketWriter extends TimeListener{
	
	private Robot robot;
	private Metrics metrics;
	private OutputStream output;
	
	public RobotSocketWriter(ConnectionManager manager, Robot robot, Timer timer) throws IOException{
		super(timer);
		output = manager.getOutputStream();
		this.robot = robot;
	}

	@Override
	public void notifyMe() {
		
		metrics = robot.getMetrics();
		try {
			output.write(encode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}

	private byte[] encode() {
		// TODO Auto-generated method stub
		return metrics.encodeSelf();
	}

}
