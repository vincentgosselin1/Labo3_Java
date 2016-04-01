package Memento;

import java.util.ArrayList;

public class CouleurCaretaker {
	// Where all mementos are saved
	ArrayList<CouleurMemento> savedImages = new ArrayList<CouleurMemento>();
	
	public void addMemento(CouleurMemento m) { 
		savedImages.add(m); 
	}
	
	public void delMemento(int index){ 
		savedImages.remove(index);
	}
	
	public ArrayList<CouleurMemento> getMementoList(){
		return savedImages;
	}
	
	public CouleurMemento getMemento(int index){
		return savedImages.get(index);
	}
}
