package Patrons.Command;

import java.awt.image.BufferedImage;

public class CouleurChange implements Command{

	private BufferedImage image = model.getImage();
	private int width = image.getWidth();
	private int height = image.getHeight();
	
	@Override
	public boolean execute() {
		
		
		for(int y=0; y< height; y++)
		{
			for(int x=0; x<width;x++)
			{
				int pixel = image.getRGB(x, y);
				int a =(pixel>>24)&0xFF;
				int g = (pixel>>8)&0xFF;
				//Set new RGB
				pixel = (a<<24) | (0<<16) | (g<<8) | 0;
				image.setRGB(x, y, pixel);
				
			}
		}
		model.setModelImage(image, model.getImageName(), model.getZoom(), model.getDragX(), model.getDragY());
		
		
		
		// TODO Auto-generated method stub
		return false;
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
