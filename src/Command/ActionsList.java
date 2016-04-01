package Command;

import java.util.ArrayList;
import java.util.List;

import Controller.Controller;
import Controller.DownLoadDataFromList;

public class ActionsList {

	private static ActionsList instance = new ActionsList();

	private ActionsList(){}

	public static ActionsList getinstance(){
		return instance;
	}

	private List<Command> record = new ArrayList<Command>();
	private int index = -1;
	private DownLoadDataFromList notified;

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
			notified.notifyRecordSize(getIndex(), record.size());
		}
	}

	public void store(Command command) {
		if(haveToClear())
			clearRecord();

		setIndex(getIndex() + 1);
		record.add(command);
		if(notified == null)
			notified = Controller.getInstance();
		notified.notifyRecordSize(getIndex(), record.size());
	}

	public void execute(Command command) {
		command.execute();
	}

	private boolean haveToClear(){
		return ((record.size() - getIndex()) > 1);
	}

	public void clearRecord(){
		record.clear();
		setIndex(-1);
		if(notified == null)
			notified = Controller.getInstance();
		notified.notifyRecordSize(getIndex(), record.size());
	}

	private int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	private boolean canReDo(){
		return record.size() > getIndex() + 1;
	}

	private boolean canUnDo(){
		return getIndex() + 1 > 0;
	}

	public void reDo(){
		if(canReDo()){	
			setIndex(getIndex() + 1);
			record.get(index).reDo();
			notified.notifyRecordSize(getIndex(), record.size());
		}
	}

	public void unDo(){
		if(canUnDo()){
			record.get(index).unDo();
			setIndex(getIndex() - 1);
			notified.notifyRecordSize(getIndex(), record.size());
		}
	}
}
