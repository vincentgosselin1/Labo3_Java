package Command;

public class Drag implements Command {
	private double newX;
	private double newY;
	private double oDragX;
	private double oDragY;
	
	public Drag (double newX, double newY, double oDragX, double oDragY){
		this.newX = newX;
		this.newY = newY;
		this.oDragX = oDragX;
		this.oDragY = oDragY;
	}

	@Override
	public boolean execute() {
		if(MODEL.getImage() != null)
			return MODEL.changeModelImage(MODEL.getZoom(), MODEL.getDragX() + newX, MODEL.getDragY() + newY);
		else
			return false;
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		MODEL.changeModelImage(MODEL.getZoom(), 0, 0);
		MODEL.changeModelImage(MODEL.getZoom(), oDragX, oDragY);
	}
}



