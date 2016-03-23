package Patrons;

import java.awt.Point;

public class Drag implements Command {

	private int dragX;
	private int dragY;
	private int dragXStart;
	private int dragYStart;
	
	public Drag (int dragX, int dragY){
		this.dragX = dragX;
		this.dragY = dragY;
	}
	
	public Drag (int dragX, int dragY, int dragXStart, int dragYStart){
		this.dragX = dragX;
		this.dragY = dragY;
		this.dragXStart = dragXStart;
		this.dragYStart = dragYStart;
	}

	@Override
	public boolean execute() {
		return model.setModelImage(model.getImage(), model.getZoom(), model.getHeight(), model.getWidth(), dragXStart - model.getX(), dragYStart - model.getY(), dragX, dragY);
	}

	@Override
	public void reDo() {
		
	}

	@Override
	public void unDo() {


	}

	public double getDragY() {
		return dragY;
	}


	public void setDragY(int dragY) {
		this.dragY = dragY;
	}


	public double getDragX() {
		return dragX;
	}


	public void setDragX(int dragX) {
		this.dragX = dragX;
	}

}



