package Command;

public class Drag implements Command {
	private double newX;
	private double newY;
	private double oDragX;
	private double oDragY;
	
	public Drag (double newX, double newY){
		this.newX = newX;
		this.newY = newY;
		oDragX = model.getDragX() - newX;
		oDragY = model.getDragY() - newY;
	}

	@Override
	public boolean execute() {
		if(model.getImage() != null)
			return model.changeModelImage(model.getZoom(), model.getDragX() + newX, model.getDragY() + newY);
		else
			return false;
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		model.changeModelImage(model.getZoom(), 0, 0);
		model.changeModelImage(model.getZoom(), oDragX, oDragY);
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



