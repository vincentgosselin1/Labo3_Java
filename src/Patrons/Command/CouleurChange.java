package Patrons.Command;

import java.awt.image.BufferedImage;
import java.util.Random;

import Patrons.PModel.DataPacketFactory;
import Patrons.PModel.ModelImage;

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
		}
		//On enleve L'element 1 si il y en avait un.
		if(caretaker.getMementoList().size()>1){
			caretaker.delMemento(1);
		}
		//On cree un nouveau memento A partir de l'image originale.
		originator.set(caretaker.getMemento(0).getImageSaved());
		CouleurMemento newMemento = originator.storeInMememto();
		caretaker.addMemento(newMemento);
		//On prend le nouveau Memento cree et on soutire son image saved.
		BufferedImage imageSaved = newMemento.getImageSaved();
		
		
		switch(numberOfClicks)
		{
		case 0 : Vert(imageSaved);	break;
		case 1 : Bleu(imageSaved);	break;
		case 2 : Rouge(imageSaved);	break;
		case 3 : newMemento = caretaker.getMemento(1);	break; //O_O
		}
		
		
		
		model.setModelImage(newMemento.getImageSaved(), model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());
		
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void unDo() {
		// TODO Auto-generated method stub

	}
}	

