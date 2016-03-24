package Patrons;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ZoomIn implements Command {

	private double zoomValue;
	private Point mousePosition;
	private double nZoom;
	private int nHeight;
	private int nWidth;

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
		if(model.getImage()!=null)//si il y a une image en memoire.
		{
		nZoom = model.getZoom()+zoomValue;
		nHeight = (int) (model.getImage().getHeight()*nZoom);
		nWidth = (int) (model.getImage().getWidth()*nZoom);
		
		return model.setModelImage(model.getImage(),nZoom, nHeight, nWidth, model.getX(), model.getY(), model.getDragX(), model.getDragY());
		}
		else
			return false;
	}

	@Override
	public void reDo() {
		execute();
	}

	@Override
	public void unDo() {
		nZoom = model.getZoom()-zoomValue;
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



