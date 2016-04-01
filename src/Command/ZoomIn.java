package Command;

public class ZoomIn implements Command {
	private double zoomValue;
	private double nZoom;
	private double oDragX;
	private double oDragY;

	public ZoomIn (double zoomValue){
		setZoomValue(zoomValue-MODEL.getZoom());
	}

	public ZoomIn() {
		setZoomValue(DEFAULT_ZOOM);
	}

	@Override
	public boolean execute() {
		if(MODEL.getImage() != null){
			nZoom = MODEL.getZoom()+zoomValue;
			oDragX = MODEL.getDragX();
			oDragY = MODEL.getDragY();
			
			return MODEL.changeModelImage(nZoom, oDragX, oDragY);
		}else{
			return false;
		}
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		nZoom = MODEL.getZoom()-zoomValue;
		MODEL.changeModelImage(nZoom, 0, 0);
		MODEL.changeModelImage(MODEL.getZoom(), oDragX, oDragY);
	}

	public void setZoomValue(double zoomValue) {
		this.zoomValue = zoomValue;
	}
}



