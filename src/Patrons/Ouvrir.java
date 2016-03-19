package Patrons;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ouvrir implements Command{
	
	private Path workingDirectory = Paths.get("").toAbsolutePath();
	private BufferedImage image;
	
	private static Ouvrir instance = new Ouvrir();
	
	public Ouvrir(){}
	
	public static Ouvrir getInstance(){
		return instance;
	}
	
	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser(new File(workingDirectory.toString()) + File.separator + "Images");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", "jpg", "png", "bmp");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				image = ImageIO.read(chooser.getSelectedFile());	    
			    
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			model.setImage(image);
			model.setImageName(chooser.getSelectedFile().getName());
			model.setOrigine(new Point(image.getMinX(),image.getMinY()));
			model.setZoom(1.0);
			model.setHeight(image.getHeight());
			model.setWidth(image.getWidth());
		}else{

		}
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
