package Patrons;

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
	private double zoom = 1.0;
	private int height = 600;
	private int width = 600;
	private double X = 0;
	private double Y = 0;
	private double dragX = 0;
	private double dragY = 0;
	private List<ObserverIF> observers = new ArrayList<ObserverIF>();

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public BufferedImage getImage(){
		return image;

	}

	public boolean setZoom(double zoom){
		if(0 < zoom && zoom < 3){
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	public double getDragX() {
		return dragX;
	}

	public void setDragX(double dragX) {
		this.dragX = dragX;
	}

	public double getDragY() {
		return dragY;
	}

	public void setDragY(double dragY) {
		this.dragY = dragY;
	}
}
