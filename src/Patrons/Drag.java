package Patrons;

public class Drag implements Command {

	private double dragX;
	private double dragY;
	
	public Drag (double dragX, double dragY){
		this.dragX = dragX;
		this.dragY = dragY;
	}

	@Override
	public boolean execute() {
		model.setDragX(dragX);
		model.setDragY(dragY);
		return true;
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


	public void setDragY(double dragY) {
		this.dragY = dragY;
	}


	public double getDragX() {
		return dragX;
	}


	public void setDragX(double dragX) {
		this.dragX = dragX;
	}

}



