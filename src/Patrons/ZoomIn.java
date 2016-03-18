package Patrons;


public class ZoomIn implements Command {

	private static ZoomIn instance = new ZoomIn();

	public ZoomIn(){}

	public static ZoomIn getInstance(){
		return instance;
	}
	
	private double zoomValue;

	@Override
	public void execute() {

	}

	@Override
	public void reDo() {


	}

	@Override
	public void unDo() {


	}

	public void setZoomValue(double zoomValue) {
		this.zoomValue = zoomValue;
	}

}



