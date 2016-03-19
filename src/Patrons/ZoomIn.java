package Patrons;

import java.awt.MouseInfo;
import java.awt.Point;

public class ZoomIn implements Command {

	private double zoomValue;
	private Point mousePosition;

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
		if(model.setZoom(model.getZoom()+zoomValue)){
			model.setHeight((int) (model.getImage().getHeight()*model.getZoom()));
			model.setWidth((int) (model.getImage().getWidth()*model.getZoom()));
			return true;
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
		model.setZoom(model.getZoom()-zoomValue);
		model.setHeight((int) (model.getImage().getHeight()*model.getZoom()));
		model.setWidth((int) (model.getImage().getWidth()*model.getZoom()));
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



