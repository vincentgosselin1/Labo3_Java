package Patrons.PModel;

import java.awt.image.BufferedImage;
import java.util.List;

import Patrons.PVue.ObserverIF;

public interface Observable {
	public void addObserver(ObserverIF observer);
	public void deleteObserver(ObserverIF observer);
	public List<ObserverIF> getObservers();
	public BufferedImage getImage();
	public String getImageName();
	public double getZoom();
	public double getDragX();
	public double getDragY();
}
