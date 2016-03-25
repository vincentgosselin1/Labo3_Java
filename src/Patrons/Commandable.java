package Patrons;

import java.awt.image.BufferedImage;

public interface Commandable {
	public boolean setModelImage(BufferedImage image, double zoom, double newX, double newY);
	public double getZoom();
	public BufferedImage getImage();
	public double getX();
	public double getY();
}
