package Memento;

import java.util.ArrayList;
import java.util.List;

public class CouleurCaretaker {
	
	private static CouleurCaretaker instance = new CouleurCaretaker();

	private CouleurCaretaker(){}

	public static CouleurCaretaker getInstance(){
		return instance;
	}
	
	// Where all mementos are saved
	private static List<CouleurMemento> savedImages = new ArrayList<CouleurMemento>();

	public void addMemento(CouleurMemento m) { 
		savedImages.add(m); 
	}

	public static void delMementos(){ 
		savedImages.clear();
	}

	public List<CouleurMemento> getMementoList(){
		return savedImages;
	}

	public CouleurMemento getMemento(int index){
		return savedImages.get(index);
	}
}
