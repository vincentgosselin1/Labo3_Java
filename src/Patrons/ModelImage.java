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
	private int height = 600;
	private int width = 600;
	private int X = 0;
	private int Y = 0;
	private int dragX = 0;
	private int dragY = 0;
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

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getDragX() {
		return dragX;
	}

	public void setDragX(int dragX) {
		this.dragX = dragX;
	}

	public int getDragY() {
		return dragY;
	}

	public void setDragY(int dragY) {
		this.dragY = dragY;
	}

	public boolean setModelImage(BufferedImage image, double zoom, int height, int width, int x, int y, int dragX, int dragY){
		if(setZoom(zoom)){
			setImage(image);
			setHeight(height);
			setWidth(width);
			setX(x);
			setY(y);
			setDragX(dragX);
			setDragY(dragY);
			notifyAllObservers();
			return true;
		}else{
			return false;
		}
	}
}
