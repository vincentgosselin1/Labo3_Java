package Patrons;

import java.awt.MouseInfo;
import java.awt.Point;

public class ZoomIn implements Command {

	private double zoomValue;
	private Point mousePosition;
	private double nZoom;

	public ZoomIn (Point point, double zoomValue){
		setMousePosition(MouseInfo.getPointerInfo().getLocation());
		setZoomValue(zoomValue);
	}

	public ZoomIn(Point point) {
		setMousePosition(point);
		setZoomValue(DEFAULT_ZOOM);
	}

	@Override
	public boolean execute() {
		nZoom = model.getZoom()+zoomValue;
		
		return model.setModelImage(model.getImage(),nZoom, model.getX(), model.getY());
		
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		nZoom = model.getZoom()-zoomValue;
		
		model.setModelImage(model.getImage(),nZoom, model.getX(), model.getY());
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



