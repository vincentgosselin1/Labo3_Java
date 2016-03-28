package Patrons.Command;

import java.awt.image.BufferedImage;

public class CouleurOriginator {
	
	private BufferedImage image;

	public void set(BufferedImage newImage){
		this.image=newImage;
	}
	
	public CouleurMemento storeInMememto(){
		System.out.println("From Originator : Saving to Memento");
		return new CouleurMemento(image);
	}
	
	public BufferedImage restoreFromMemento(CouleurMemento memento)
	{
		image = memento.getImageSaved();
		return image;
	}
}

