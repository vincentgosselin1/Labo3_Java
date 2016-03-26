package Patrons.PController;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ZoomOut implements Command {
	private double zoomValue;
	private Point2D dragZoom;
	private double oZoom;
	private double oHeight;
	private double oWidth;
	private double nZoom;
	private double nHeight;
	private double nWidth;
	private double oDragX;
	private double oDragY;

	public ZoomOut (Point point, double zoomValue){
		setZoomValue(zoomValue);
	}

	public ZoomOut(Point point) {
		setZoomValue(DEFAULT_ZOOM);
	}

	@Override
	public boolean execute() {	
		if(model.getImage() != null){
			oZoom = model.getZoom();
			oHeight = model.getImage().getHeight()*oZoom;
			oWidth = model.getImage().getWidth()*oZoom;
			nZoom = model.getZoom()-zoomValue;
			nHeight = model.getImage().getHeight()*nZoom;
			nWidth = model.getImage().getWidth()*nZoom;

			dragZoom = new Point2D.Double((nHeight-oHeight)/2,(nWidth-oWidth)/2);

			oDragX = model.getDragX();
			oDragY = model.getDragY();

			return model.changeModelImage(nZoom, model.getDragX() -dragZoom.getX(), model.getDragY() -dragZoom.getY());

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
		nZoom = model.getZoom()+zoomValue;
		model.changeModelImage(nZoom, 0, 0);
		model.changeModelImage(model.getZoom(), oDragX, oDragY);
	}

	public void setZoomValue(double zoomValue) {
		this.zoomValue = zoomValue;
	}
}