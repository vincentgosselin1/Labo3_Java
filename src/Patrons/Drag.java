package Patrons;

public class Drag implements Command {
	private double newX;
	private double newY;
	
	public Drag (double newX, double newY){
		this.newX = newX;
		this.newY = newY;
	}
	
//	public Drag (double newX, double newY, double newXStart, double newYStart){
//		this.newX = newX;
//		this.newY = newY;
//		this.newXStart = newXStart;
//		this.newYStart = newYStart;
//	}

	@Override
	public boolean execute() {
		return model.setModelImage(model.getImage(), model.getZoom(), newX, newY);
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		model.setModelImage(model.getImage(), model.getZoom(), newX, newY);
	}

	public double getY() {
		return newY;
	}

	public void setY(double newY) {
		this.newY = newY;
	}


	public double getX() {
		return newX;
	}

	public void setX(double newX) {
		this.newX = newX;
	}
}



