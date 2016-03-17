package Patrons;

import java.util.ArrayList;
import java.util.List;

public class Actions {

	private static Actions instance = new Actions();

	private Actions(){}

	public static Actions getinstance(){
		return instance;
	}

	private List<Command> record = new ArrayList<Command>();
	ModelImage model = ModelImage.getInstance();

	public void storeAndExecute(Command command){
		this.record.add(command);
		command.execute(command);
	}
}
