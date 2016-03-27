package Patrons.PModel;

import java.io.Serializable;

public class DataPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public double zoom=0;//
	public double X;
	public double Y;
	public byte[] imageInByte;
	public String imageName;
	//New stuffff
	
	public DataPacket(){}
}