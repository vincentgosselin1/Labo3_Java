package Patrons.Command;

import java.awt.image.BufferedImage;

public class CouleurChange implements Command{

	private CouleurCaretaker caretaker;
	private CouleurOriginator originator;
	private static boolean firstTime = true;
	
	
	private static int numberOfClicks=0;
	

	public CouleurChange(CouleurCaretaker caretaker,CouleurOriginator originator)
	{
		this.caretaker=caretaker;
		this.originator=originator;
	}

	private void Vert(BufferedImage imageSaved){
		int height = imageSaved.getHeight();
		int width = imageSaved.getWidth();
		for(int y=0; y< height; y++)
		{
			for(int x=0; x<width;x++)
			{
				int pixel = imageSaved.getRGB(x, y);
				int a =(pixel>>24)&0xff;
				int g = (pixel>>8)&0xff;
				//Set new RGB
				pixel = (a<<24) | (0<<16) | (g<<8) | 0;
				imageSaved.setRGB(x, y, pixel);
			}
		}
	}
	private void Rouge(BufferedImage imageSaved){
		int height = imageSaved.getHeight();
		int width = imageSaved.getWidth();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int p = imageSaved.getRGB(x,y);

				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;

				//set new RGB
				p = (a<<24) | (r<<16) | (0<<8) | 0;

				imageSaved.setRGB(x, y, p);
			}
		}
	}
	private void Bleu(BufferedImage imageSaved){
		int height = imageSaved.getHeight();
		int width = imageSaved.getWidth();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
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
		if(firstTime)
		{
			originator.set(model.getImage());
			caretaker.addMemento(originator.storeInMememto());//Cree le memento et on le met dans la liste.
			firstTime=false;//On a save l'image originale.
			
			//init de la liste des mementos au complets.
			//Memento 1 VERT
			originator.set(caretaker.getMemento(0).getImageSaved());//On cree un nouveau memento A partir de l'image originale.
			CouleurMemento newMemento1 = originator.storeInMememto();
			caretaker.addMemento(newMemento1);
			BufferedImage imageSaved1 = newMemento1.getImageSaved();//On prend le nouveau Memento cree et on soutire son image saved.
			Vert(imageSaved1);
			//Memento 2 Bleu
			originator.set(caretaker.getMemento(0).getImageSaved());
			CouleurMemento newMemento2 = originator.storeInMememto();
			caretaker.addMemento(newMemento2);
			BufferedImage imageSaved2 = newMemento2.getImageSaved();
			Bleu(imageSaved2);
			//Memento 3 Rouge
			originator.set(caretaker.getMemento(0).getImageSaved());
			CouleurMemento newMemento3 = originator.storeInMememto();
			caretaker.addMemento(newMemento3);
			BufferedImage imageSaved3 = newMemento3.getImageSaved();
			Rouge(imageSaved3);
		}

		
		BufferedImage imageToModel=null;
		
		switch(numberOfClicks)
		{
		case 0 : imageToModel = caretaker.getMemento(1).getImageSaved();	break;
		case 1 : imageToModel = caretaker.getMemento(2).getImageSaved();	break;
		case 2 : imageToModel = caretaker.getMemento(3).getImageSaved();	break;
		case 3 : imageToModel = caretaker.getMemento(0).getImageSaved();	break; 
		}
		
		
		//Set le model
		model.setModelImage(imageToModel, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());
		
		
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
		if(numberOfClicks>3){numberOfClicks=0;}
		imageToModel = caretaker.getMemento(numberOfClicks).getImageSaved();
		model.setModelImage(imageToModel, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());

	}

	@Override
	public void unDo() {
		BufferedImage imageToModel=null;
		numberOfClicks = numberOfClicks-1;
		if(numberOfClicks<0){numberOfClicks=3;}
		imageToModel = caretaker.getMemento(numberOfClicks).getImageSaved();
		model.setModelImage(imageToModel, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());

	}
}	

