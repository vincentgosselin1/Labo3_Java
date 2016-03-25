package Patrons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Patrons.ObserverIF;

public class ModelImage implements Observable, Commandable{
	private static ModelImage instance = new ModelImage();

	private ModelImage(){}

	public static ModelImage getInstance(){
		return instance;
	}

	private BufferedImage image;
	private double zoom = 1.0;
	private double X = 0;
	private double Y = 0;
	private List<ObserverIF> observers = new ArrayList<ObserverIF>();

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public BufferedImage getImage(){
		return image;

	}

	public boolean setZoom(double zoom){
		if(0 < zoom && zoom <= 3){
			this.zoom = zoom;
			return true;
		}else
			return false;
	}

	public double getZoom(){
		return zoom;

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

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public boolean setModelImage(BufferedImage image, double zoom, double x, double y){
		if(setZoom(zoom)){
			setImage(image);
			setX(x);
			setY(y);
			notifyAllObservers();
			return true;
		}else{
			return false;
		}
	}
}
