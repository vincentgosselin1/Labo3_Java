package Command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Model.ModelImage;
import Serialization.DataPacket;
import Serialization.DataPacketFactory;

public class Save implements Command{

	private static Save instance = new Save();

	private Save(){}

	public static Save getInstance(){
		return instance;
	}

	@Override
	public boolean execute() {
		if(MODEL.getImage() != null){
			try {
				serialize(ModelImage.getInstance());
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;

	}

	public void serialize(ModelImage MODEL) throws IOException
	{
		//On cree un datapacket a partir du MODEL.
		DataPacket dataPacket = DataPacketFactory.createDataPacket(MODEL);

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
