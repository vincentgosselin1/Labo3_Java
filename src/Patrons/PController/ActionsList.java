package Patrons.PController;

import java.util.ArrayList;
import java.util.List;

public class ActionsList {

	private static ActionsList instance = new ActionsList();

	private ActionsList(){}

	public static ActionsList getinstance(){
		return instance;
	}

	private List<Command> record = new ArrayList<Command>();
	private int index = -1;
	private ControllerIF notified;

	public List<Command> getRecord(){
		return this.record;
	}

	public void storeAndExecute(Command command){		
		if(haveToClear())
			clearRecord();

		if(command.execute()){
			setIndex(getIndex() + 1);
			record.add(command);
			if(notified == null)
				notified = Controller.getInstance();
			notified.notifyRecordSize(index, record.size());
		}
	}

	public void store(Command command) {
		if(haveToClear())
			clearRecord();

		setIndex(getIndex() + 1);
		record.add(command);
	}

	public void execute(Command command) {
		command.execute();
	}

	public boolean haveToClear(){
		return ((record.size() - index) > 1);
	}

	public void clearRecord(){
		record.clear();
		setIndex(-1);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean canReDo(){
		return record.size() > index + 1;
	}

	public boolean canUnDo(){
		return index + 1 > 0;
	}

	public void reDo(){
		if(canReDo()){	
			setIndex(getIndex() + 1);
			record.get(index).reDo();
			notified.notifyRecordSize(index, record.size());
		}
	}

	public void unDo(){
		if(canUnDo()){
			record.get(index).unDo();
			setIndex(getIndex() - 1);
			notified.notifyRecordSize(index, record.size());
		}
	}
}
