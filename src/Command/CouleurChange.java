package Command;

import java.awt.image.BufferedImage;

import Memento.CouleurCaretaker;
import Memento.CouleurMemento;
import Memento.CouleurOriginator;

public class CouleurChange implements Command{

	private static CouleurCaretaker careTaker = new CouleurCaretaker();
	private static CouleurOriginator originator = new CouleurOriginator();
	private static int numberOfClicks=0;

	private void vert(BufferedImage imageSaved){
		for(int y=0; y< imageSaved.getWidth(); y++){
			for(int x=0; x<imageSaved.getWidth();x++){
				int p = imageSaved.getRGB(x, y);
				int a = (p>>24)&0xff;
				int g = (p>>8)&0xff;
				//Set new RGB
				p = (a<<24) | (0<<16) | (g<<8) | 0;
				imageSaved.setRGB(x, y, p);
			}
		}
	}
	
	private void rouge(BufferedImage imageSaved){
		for(int y = 0; y < imageSaved.getHeight(); y++){
			for(int x = 0; x < imageSaved.getWidth(); x++){
				int p = imageSaved.getRGB(x,y);
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				//set new RGB
				p = (a<<24) | (r<<16) | (0<<8) | 0;
				imageSaved.setRGB(x, y, p);
			}
		}
	}
	
	private void bleu(BufferedImage imageSaved){
		for(int y = 0; y < imageSaved.getHeight(); y++){
			for(int x = 0; x < imageSaved.getWidth(); x++){
				int p = imageSaved.getRGB(x,y);
				int a = (p>>24)&0xff;
				int b = p&0xff;
				//set new RGB
				p = (a<<24) | (0<<16) | (0<<8) | b;
				imageSaved.setRGB(x, y, p);
			}
		}
	}

	@Override
	public boolean execute() {
		if(careTaker.getMementoList().isEmpty())
		{
			originator.set(MODEL.getImage());
			careTaker.addMemento(originator.storeInMememto());//Cree le memento et on le met dans la liste.
			
			//init de la liste des mementos au complet.
			//Memento 1 VERT
			originator.set(careTaker.getMemento(0).getImageSaved());//On cree un nouveau memento A partir de l'image originale.
			CouleurMemento newMemento1 = originator.storeInMememto();
			careTaker.addMemento(newMemento1);
			BufferedImage imageSaved1 = newMemento1.getImageSaved();//On prend le nouveau Memento cree et on soutire son image saved.
			vert(imageSaved1);
			
			//Memento 2 Bleu
			originator.set(careTaker.getMemento(0).getImageSaved());
			CouleurMemento newMemento2 = originator.storeInMememto();
			careTaker.addMemento(newMemento2);
			BufferedImage imageSaved2 = newMemento2.getImageSaved();
			bleu(imageSaved2);
			
			//Memento 3 Rouge
			originator.set(careTaker.getMemento(0).getImageSaved());
			CouleurMemento newMemento3 = originator.storeInMememto();
			careTaker.addMemento(newMemento3);
			BufferedImage imageSaved3 = newMemento3.getImageSaved();
			rouge(imageSaved3);
		}
		
		BufferedImage imageToModel=null;
		
		switch(numberOfClicks)
		{
		case 0 : imageToModel = careTaker.getMemento(1).getImageSaved();	break;
		case 1 : imageToModel = careTaker.getMemento(2).getImageSaved();	break;
		case 2 : imageToModel = careTaker.getMemento(3).getImageSaved();	break;
		case 3 : imageToModel = careTaker.getMemento(0).getImageSaved();	break; 
		}
		
		//Set le MODEL
		MODEL.changeCouleurImage(imageToModel);
		
		//On avance le click
		numberOfClicks++;
		if(numberOfClicks==4)
		{
			//Reset le click!
			numberOfClicks=0;
		}
		
		return true;
	}

	@Override
	public void reDo() {
		BufferedImage imageToModel=null;
		numberOfClicks = numberOfClicks+1;
		if(numberOfClicks>3)
			numberOfClicks=0;
		imageToModel = careTaker.getMemento(numberOfClicks).getImageSaved();
		MODEL.changeCouleurImage(imageToModel);
	}

	@Override
	public void unDo() {
		BufferedImage imageToModel=null;
		numberOfClicks = numberOfClicks-1;
		if(numberOfClicks<0)
			numberOfClicks=3;
		imageToModel = careTaker.getMemento(numberOfClicks).getImageSaved();
		MODEL.changeCouleurImage(imageToModel);
	}
}	

