package Memento;

import java.awt.image.BufferedImage;


public class CouleurOriginator {
	
	private static CouleurOriginator instance = new CouleurOriginator();

	private CouleurOriginator(){}

	public static CouleurOriginator getInstance(){
		return instance;
	}
	
	private BufferedImage newImage;
	
	public void set(BufferedImage newImage){
		this.newImage = new BufferedImage(newImage.getColorModel(),
				newImage.copyData(null),newImage.isAlphaPremultiplied(),null);
	}
	
	public CouleurMemento storeInMememto(){
		System.out.println("From Originator : Saving to Memento");
		return new CouleurMemento(newImage);
	}
	
	public BufferedImage restoreFromMemento(CouleurMemento memento)
	{
		newImage = memento.getImageSaved();
		return newImage;
	}
}

