package Patrons.PController;

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

import Patrons.PModel.DataPacket;
import Patrons.PModel.ModelImage;

public class Ouvrir implements Command{

	private BufferedImage image;

	private static Ouvrir instance = new Ouvrir();

	public Ouvrir(){}

	public static Ouvrir getInstance(){
		return instance;
	}

	@Override
	public boolean execute() {
		JFileChooser chooser = new JFileChooser(new File(workingDirectory.toString()) + File.separator + "Images");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", "ser", "jpg", "png", "bmp");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				if(chooser.getSelectedFile().getPath().contains(".ser"))
				{
					try {
						DeSerialize(chooser.getSelectedFile(),ModelImage.getInstance());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						return false;
					}
				}
				//On ouvre un JPG normal.
				else{
					image = ImageIO.read(chooser.getSelectedFile());
					model.setModelImage(image, chooser.getSelectedFile().getName(), 1.0, 0, 0);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return false;
			}
		
			return true;
		}else{
			return true;
		}
		
	}
	public void DeSerialize(File file,ModelImage model) throws IOException, ClassNotFoundException{
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
		model.setModelImage(bImageFromConvert, dataPacket.getImageName(), dataPacket.getZoom(), dataPacket.getDragX(), dataPacket.getDragY());

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
