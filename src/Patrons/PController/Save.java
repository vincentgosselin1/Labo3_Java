package Patrons.PController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

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
		if(model.getImage() != null){
			try {
				Serialize(ModelImage.getInstance());
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;

	}

	public void Serialize(ModelImage model) throws IOException
	{
		DataPacket dataPacket = new DataPacket(model);//To send.
		//Pour image
		BufferedImage image = model.getImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", os);
		os.flush();
		dataPacket.setImageInByte(os.toByteArray());

		FileOutputStream fileOut = new FileOutputStream(workingDirectory.toString() + File.separator
														+ "Images" + File.separator + dataPacket.getImageName() + ".ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(dataPacket);
		out.close();
		fileOut.close();
		System.out.printf(workingDirectory.toString() + File.separator
				+ "Images" + File.separator + dataPacket.getImageName() + ".ser");
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
