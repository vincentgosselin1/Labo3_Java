package Patrons.PModel;

import java.io.Serializable;

public class DataPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double zoom = 0;//
	private double dragX;
	private double dragY;
	private byte[] imageInByte;
	private String imageName;
	
	public DataPacket(){}

	public byte[] getImageInByte() {
		return imageInByte;
	}

	public void setImageInByte(byte[] imageInByte) {
		this.imageInByte = imageInByte;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getDragY() {
		return dragY;
	}

	public void setDragY(double dragY) {
		this.dragY = dragY;
	}

	public double getDragX() {
		return dragX;
	}

	public void setDragX(double dragX) {
		this.dragX = dragX;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
}