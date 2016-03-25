package Patrons;

import java.awt.image.BufferedImage;
import java.util.List;

import Patrons.ObserverIF;

public interface Observable {
	public void addObserver(ObserverIF observer);
	public void deleteObserver(ObserverIF observer);
	public List<ObserverIF> getObservers();
	public double getZoom();
	public BufferedImage getImage();
	public double getX();
	public double getY();
}
