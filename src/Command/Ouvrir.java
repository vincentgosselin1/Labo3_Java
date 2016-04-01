package Command;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Commandable;
import Serialization.DataPacket;

public class Ouvrir implements Command{

	private BufferedImage image;
	private String imageName;

	public Ouvrir(){}

	public Ouvrir(String imageName){
		this.imageName = imageName;
	}

	@Override
	public boolean execute() {
		if(imageName == null){
			JFileChooser chooser = new JFileChooser(new File(workingDirectory.toString() + File.separator + "Images"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", "ser", "jpg", "png", "bmp");
			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);

			if(returnVal == JFileChooser.APPROVE_OPTION) {
				imageName=chooser.getSelectedFile().getName();
				return ouvrir(chooser.getSelectedFile());
			}else{
				return false;
			}
		}else{
			return ouvrir(new File(workingDirectory.toString() + File.separator + "Images" + File.separator + imageName));
		}

	}

	public boolean ouvrir(File file){
		try {
			if(imageName.contains(".ser"))
			{
				deSerialize(file);
			}
			//On ouvre un JPG normal.
			else{
				image = ImageIO.read(file);
				MODEL.setModelImage(image, imageName, 1.0, 0, 0);
			}

			return true;
		}catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void deSerialize(File file) throws IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		DataPacket dataPacket = (DataPacket) in.readObject();
		in.close();
		fileIn.close();
		//Verification des donnees du packet.
		System.out.println(dataPacket.getDragX());
		System.out.println(dataPacket.getDragY());
		System.out.println(dataPacket.getZoom());
		System.out.println(dataPacket.getImageName());
		// convert byte array back to BufferedImage
		InputStream image = new ByteArrayInputStream(dataPacket.getImageInByte());
		BufferedImage bImageFromConvert = ImageIO.read(image);
		MODEL.setModelImage(bImageFromConvert, dataPacket.getImageName() + ".ser", dataPacket.getZoom(), dataPacket.getDragX(), dataPacket.getDragY());
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
