package Command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Serialization.DataPacket;
import Serialization.DataPacketFactory;

public class Save implements Command{

	private static Save instance = new Save();

	private Save(){}

	public static Save getInstance(){
		return instance;
	}
	
	private String path= "";
	
	public String getPath() {
		return path;
	}

	@Override
	public boolean execute() {
		if(MODEL.getImage() != null){
			try {
				serialize();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;
	}

	public void serialize() throws IOException
	{
		//On cree un datapacket a partir du MODEL.
		DataPacket dataPacket = DataPacketFactory.createDataPacket(MODEL);
		path = workingDirectory.toString() + File.separator
				+ "Images" + File.separator + dataPacket.getImageName() + ".ser";
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(dataPacket);
		out.close();
		fileOut.close();
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
