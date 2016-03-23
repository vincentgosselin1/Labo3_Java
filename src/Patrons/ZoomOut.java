package Patrons;

import java.awt.Point;

public class ZoomOut implements Command {
	private double zoomValue;
	private Point mousePosition;
	private double nZoom;
	private int nHeight;
	private int nWidth;
	
	public ZoomOut (Point point, double zoomValue){
		setMousePosition(point);
		setZoomValue(zoomValue);
	}

	public ZoomOut(Point point) {
		setMousePosition(point);
		setZoomValue(DEFAULT_ZOOM);
	}

	@Override
	public boolean execute() {
		
		nZoom = model.getZoom()-zoomValue;
		nHeight = (int) (model.getImage().getHeight()*nZoom);
		nWidth = (int) (model.getImage().getWidth()*nZoom);
		
		return model.setModelImage(model.getImage(),nZoom, nHeight, nWidth, model.getX(), model.getY(), model.getDragX(), model.getDragY());
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		nZoom = model.getZoom()+zoomValue;
		nHeight = (int) (model.getImage().getHeight()*nZoom);
		nWidth = (int) (model.getImage().getWidth()*nZoom);
		
		model.setModelImage(model.getImage(),nZoom, nHeight, nWidth, model.getX(), model.getY(), model.getDragX(), model.getDragY());
	}

	public void setZoomValue(double zoomValue) {
		this.zoomValue = zoomValue;
	}

	public Point getMousePosition() {
		return mousePosition;
	}

	public void setMousePosition(Point mousePosition) {
		this.mousePosition = mousePosition;
	}
	
}