package cz.tchuba.lego.scorpion;

import java.io.IOException;
import java.io.InputStream;

public class RobotSocketReader implements Runnable{

	private InputStream input;
	private byte[] buffer = new byte[1024];
	private String message;
	private Robot robot;
	
	public RobotSocketReader(ConnectionManager manager, Robot robot) throws IOException {
		input = manager.getInputStream();
		this.robot = robot;
	}
	
	@Override
	public void run() {
		while (Main.runs){
			try {
				input.read(buffer);
				message = new String (buffer);
				//Sound.beep();
				parseMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (Main.runs){
					e.printStackTrace();
				}else{
					return;
				}
			}
			
		}
		try {
			input.close();
		} catch (IOException e) {
			//error closing socket
			if (Main.runs){
				e.printStackTrace();
			}
		}
		robot.lcd.drawString("reader ended", 1, 6);
	}

	private void parseMessage() {
		String [] split;
		split = message.split(Messages.TYPE_DELIMITER);
		switch(split[0]){
		case Messages.MODE:{
			robot.updateMode(split[1]);
			break;
		}case Messages.DRIVE:{
			robot.updateDirections(split[1]);
			break;
		}case Messages.QUIT:{
			robot.quit(false);
			break;
		}
		default:{
			//unknown message
		}
		}
		
	}

}
