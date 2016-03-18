package Patrons;


public class ZoomIn implements Command {
	
	private static final double DEFAULT_ZOOM = 0.25;
	private double zoomValue;

	@Override
	public void execute() {
		model.setZoom(model.getZoom()+DEFAULT_ZOOM);
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



