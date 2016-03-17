package Patrons;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ModelImage {
	private static ModelImage instance = new ModelImage();

	private ModelImage(){}

	public static ModelImage getInstance(){
		return instance;
	}

	private BufferedImage image;
	private int zoom;
	private Point origine;
	private List<ObserverIF> observers = new ArrayList<ObserverIF>();

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public BufferedImage getImage(){
		return image;

	}

	public void setZoom(int zoom){
		this.zoom = zoom;
	}

	public int getZoom(){
		return zoom;

	}

	public void setOrigine(Point origine){
		this.origine = origine;
	}

	public Point getOrigine(){
		return origine;

	}
	
	public void addObserver(ObserverIF observer) {
		observers.add(observer);
	}
	
	public void deleteObserver(ObserverIF observer) {
		observers.remove(observer);
	}
	
	public List<ObserverIF> getObservers() {
		return observers;
	}
	
	public void notifyAllObservers(){
		for (ObserverIF observer : observers) {
	         observer.update();
	    }
	}


}
