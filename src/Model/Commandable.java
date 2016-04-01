package Model;

import java.awt.image.BufferedImage;

public interface Commandable {
	public boolean setModelImage(BufferedImage image, String imageName, double zoom, double newX, double newY);
	public boolean changeModelImage(double zoom, double newX, double newY);
	public boolean changeCouleurImage(BufferedImage image);
	public BufferedImage getImage();
	public String getImageName();
	public double getZoom();
	public double getDragX();
	public double getDragY();
}
