package Patrons;

public class ZoomOut implements Command {
	
	private double zoomValue;
	
	@Override
	public void execute() {
		model.setZoom(model.getZoom()-DEFAULT_ZOOM);
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