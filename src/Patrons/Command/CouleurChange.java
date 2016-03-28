package Patrons.Command;

import java.awt.image.BufferedImage;
import java.util.Random;

public class CouleurChange implements Command{

	private CouleurCaretaker caretaker;
	private CouleurOriginator originator;

	//Pour mettre filtre Aleatoire
	private static Random nbAleatoire;

	public CouleurChange(CouleurCaretaker caretaker,CouleurOriginator originator)
	{
		this.caretaker=caretaker;
		this.originator=originator;
	}


	@Override
	public boolean execute() {
		//On enleve L'element 1 si il y en avait un.
		if(caretaker.getMementoList().size()>1){
			caretaker.delMemento(1);
		}
		//On cree un nouveau memento A partir de l'image originale.
		originator.set(caretaker.getMemento(0).getImageSaved());
		caretaker.addMemento(originator.storeInMememto());
		//On prend le nouveau Memento cree et on soutire son image saved.
		BufferedImage imageSaved = caretaker.getMemento(1).getImageSaved();
		//On y applique un filtre au hasard
		nbAleatoire = new Random();
		int filtreAleatoire = nbAleatoire.nextInt(2);

		//Parametres de l'image
		int width = imageSaved.getWidth();
		int height = imageSaved.getHeight();

		switch(filtreAleatoire){
		case 0 ://Filtre Vert
			for(int y=0; y< height; y++)
			{
				for(int x=0; x<width;x++)
				{
					int pixel = imageSaved.getRGB(x, y);
					int a =(pixel>>24)&0xFF;
					int g = (pixel>>8)&0xFF;
					//Set new RGB
					pixel = (a<<24) | (0<<16) | (g<<8) | 0;
					imageSaved.setRGB(x, y, pixel);
				}
			}
			break;
		case 1://Filtre rouge
			//convert to red image
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
			break;
		case 2://Filtre Bleau 
			//convert to blue image
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
			break;
		}
		model.setModelImage(imageSaved, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());
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


//		for(int y=0; y< height; y++)
//			{
//				for(int x=0; x<width;x++)
//				{
//					int pixel = image.getRGB(x, y);
//					int a =(pixel>>24)&0xFF;
//					int g = (pixel>>8)&0xFF;
//					//Set new RGB
//					pixel = (a<<24) | (0<<16) | (g<<8) | 0;
//					image.setRGB(x, y, pixel);
//				
//			
//			}
//			model.setModelImage(image, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());
//	}
