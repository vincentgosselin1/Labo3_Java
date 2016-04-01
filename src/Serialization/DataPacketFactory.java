package Serialization;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.ModelImage;

public class DataPacketFactory {
	
	public static DataPacket createFrom(ModelImage model) throws IOException
	{
		//Cree le DataPacket
		DataPacket newDataPacket = new DataPacket();
		//Init du Zoom + Drag du datapacket
		newDataPacket.setZoom(model.getZoom());
		newDataPacket.setDragX(model.getDragX());
		newDataPacket.setDragY(model.getDragY());
		//Init nom de l'image du datapacket
		String[] imageNameWithOutExt = model.getImageName().split("[.]");
		newDataPacket.setImageName(imageNameWithOutExt[0]);	
		//Init de l'image en byte du datapacket
		BufferedImage image = model.getImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", os);
		os.flush();
		newDataPacket.setImageInByte(os.toByteArray());
		//On retourne le DataPacket cree
		return newDataPacket;
	}
}
