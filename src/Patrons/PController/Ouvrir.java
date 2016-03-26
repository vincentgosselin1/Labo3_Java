package Patrons.PController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Patrons.PModel.DataPacket;
import Patrons.PModel.ModelImage;

public class Ouvrir implements Command{

	private Path workingDirectory = Paths.get("").toAbsolutePath();
	private BufferedImage image;

	private static Ouvrir instance = new Ouvrir();

	public Ouvrir(){}

	public static Ouvrir getInstance(){
		return instance;
	}

	@Override
	public boolean execute() {
		JFileChooser chooser = new JFileChooser(new File(workingDirectory.toString()) + File.separator + "Images");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", "jpg", "png", "bmp");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {


				if(chooser.getSelectedFile().getPath().contains(".ser"))
				{
					try {
						DeSerialize(chooser.getSelectedFile(),ModelImage.getInstance());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}
				//On ouvre un JPG normal.
				else{
					image = ImageIO.read(chooser.getSelectedFile());
					model.setModelImage(image, chooser.getSelectedFile().getName(), 1.0, -300, -300);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}


		}else{

		}
		return true;
	}
	public void DeSerialize(File file,ModelImage model) throws IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		DataPacket dataPacket = (DataPacket) in.readObject();
		in.close();
		fileIn.close();
		//Verification des donnees du packet.
		System.out.println(dataPacket.X);
		System.out.println(dataPacket.Y);
		System.out.println(dataPacket.zoom);
		System.out.println(dataPacket.imageName);
		// convert byte array back to BufferedImage
		InputStream image = new ByteArrayInputStream(dataPacket.imageInByte);
		BufferedImage bImageFromConvert = ImageIO.read(image);
		model.setModelImage(bImageFromConvert, dataPacket.imageName, dataPacket.zoom, dataPacket.X, dataPacket.Y);

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
