package Patrons.PModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Patrons.PVue.ObserverIF;

public class ModelImage implements Observable, Commandable{
	private static ModelImage instance = new ModelImage();

	private ModelImage(){}

	public static ModelImage getInstance(){
		return instance;
	}

	private BufferedImage image;
	private double zoom = 1.0;
	private double dragX = 0;
	private double dragY = 0;
	private String imageName = "";
	private List<ObserverIF> observers = new ArrayList<ObserverIF>();

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public BufferedImage getImage(){
		return image;
	}

	public void setImageName(String imageName){
		this.imageName  = imageName;
	}
	
	public	String getImageName(){
		return imageName;
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
	
	public void setDragX(double dragX) {
		this.dragX = dragX;
	}
	
	public double getDragX() {
		return dragX;
	}
	
	public void setDragY(double dragY) {
		this.dragY = dragY;
	}
	
	public double getDragY() {
		return dragY;
	}
	
	public boolean setModelImage(BufferedImage image, String imageName, double zoom, double x, double y){
		if(setZoom(zoom)){
			setImage(image);
			setImageName(imageName);
			setDragX(x);
			setDragY(y);
			notifyAllObservers();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean changeModelImage(double zoom, double x, double y){
		if(setZoom(zoom)){
			setDragX(x);
			setDragY(y);
			notifyAllObservers();
			return true;
		}else{
			return false;
		}
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
