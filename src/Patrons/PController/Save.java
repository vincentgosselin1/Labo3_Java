package Patrons.PController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import Patrons.PModel.DataPacket;
import Patrons.PModel.ModelImage;

public class Save implements Command{

	private static Save instance = new Save();

	public Save(){}

	public static Save getInstance(){
		return instance;
	}

	@Override
	public boolean execute() {
		try {
			Serialize(ModelImage.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void Serialize(ModelImage model) throws IOException
	{
		DataPacket dataPacket = new DataPacket();//To send.
		//Pour image
		BufferedImage image = model.getImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", os);
		os.flush();
		//Modification du DATAPACKET.
		dataPacket.imageInByte = os.toByteArray();
		dataPacket.zoom = model.getZoom();
		dataPacket.X = model.getDragX();
		dataPacket.Y = model.getDragY();
		dataPacket.imageName = model.getImageName();

		FileOutputStream fileOut = new FileOutputStream("/Users/vincentgosselin/Desktop/Jambon.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(dataPacket);
		out.close();
		fileOut.close();
		System.out.printf("Serialized data is saved in /Users/vincentgosselin/Desktop/");
	}
	@Override
	public void reDo() {
		//Do nothing
	}

	@Override
	public void unDo() {
		//Do nothing
	}
}
