package Memento;

import java.util.ArrayList;

public class CouleurCaretaker {
	// Where all mementos are saved
	private static ArrayList<CouleurMemento> savedImages = new ArrayList<CouleurMemento>();

	public void addMemento(CouleurMemento m) { 
		savedImages.add(m); 
	}

	public static void delMementos(){ 
		savedImages.clear();
	}

	public ArrayList<CouleurMemento> getMementoList(){
		return savedImages;
	}

	public CouleurMemento getMemento(int index){
		return savedImages.get(index);
	}
}
