package Patrons.Command;

import java.awt.image.BufferedImage;

public class CouleurMemento{
	
	private BufferedImage image;
	
	public CouleurMemento(BufferedImage imageSaved)
	{
		this.image=imageSaved;
	}
	
	public BufferedImage getImageSaved()
	{
		return image;
	}

		
		
		
	

}
