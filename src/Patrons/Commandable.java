package Patrons;

import java.awt.image.BufferedImage;

public interface Commandable {

	public boolean setModelImage(BufferedImage image, double zoom, int height, 
							  int width, int x, int y, int dragX, int dragY);
	public void setImageDatapath(String imageDatapath);
	public	String getImageDatapath();
	public double getZoom();
	public BufferedImage getImage();
	public int getHeight();
	public int getWidth();
	public int getX();
	public int getY();
	public int getDragX();
	public int getDragY();
}
