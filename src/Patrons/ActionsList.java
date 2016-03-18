package Patrons;

import java.util.ArrayList;
import java.util.List;

public class ActionsList {

	private static ActionsList instance = new ActionsList();

	private ActionsList(){}

	public static ActionsList getinstance(){
		return instance;
	}

	private List<Command> record = new ArrayList<Command>();
	private int index = 0;
	
	public List<Command> getRecord(){
		return this.record;
	}
	
	public void storeAndExecute(Command command){
		record.add(command);
		command.execute();
		setIndex(getIndex() + 1);
	}
	
	public void clearRecord(){
		record.clear();
		setIndex(0);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public boolean canReDo(){
		return record.size() > index;
	}
	
	public boolean canUnDo(){
		return record.size() > 0;
	}
	
	public void reDo(){
		if(canReDo()){
			
		}
	}
	
	public void unDo(){
		if(canUnDo()){
			
		}
	}
}
