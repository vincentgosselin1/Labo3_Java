package Patrons.PController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



import Patrons.PModel.DataPacket;
import Patrons.PModel.DataPacketFactory;
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
		//On cree un datapacket a partir du model.
		DataPacket dataPacket = DataPacketFactory.CreateFrom(model);
		

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
