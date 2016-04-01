package Command;

public class ZoomOut implements Command {
	private double zoomValue;
	private double nZoom;
	private double oDragX;
	private double oDragY;

	public ZoomOut(double zoomValue){
		setZoomValue(zoomValue);
	}

	public ZoomOut() {
		setZoomValue(DEFAULT_ZOOM);
	}

	@Override
	public boolean execute() {	
		if(MODEL.getImage() != null){
			nZoom = MODEL.getZoom()-zoomValue;
			oDragX = MODEL.getDragX();
			oDragY = MODEL.getDragY();

			return MODEL.changeModelImage(nZoom, MODEL.getDragX(), MODEL.getDragY());

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
		nZoom = MODEL.getZoom()+zoomValue;
		MODEL.changeModelImage(nZoom, 0, 0);
		MODEL.changeModelImage(MODEL.getZoom(), oDragX, oDragY);
	}

	public void setZoomValue(double zoomValue) {
		this.zoomValue = zoomValue;
	}
}