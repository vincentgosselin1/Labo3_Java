package Patrons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private ModelImage model = ModelImage.getInstance();
	private Path workingDirectory = Paths.get("").toAbsolutePath();
	
	private Controller(){}
	
	public static Controller getInstance(){
		return instance;
	}
	
	/**
	 * <b><i>ouvrirFichier</i></b> 
	 * permet d'ouvrir une boîte de dialogue dans le dossier Downloads de l'ordinateur 
	 * et filtrer tous les fichiers sauf les XML.
	 * 
	 */
	public void ouvrirFichier(){
		JFileChooser chooser = new JFileChooser(new File(workingDirectory.toString()) + File.separator + "Images");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", "jpg", "png", "bmp");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
			    model.setImage(ImageIO.read(chooser.getSelectedFile()));
			    model.notifyAllObservers();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}else{

		}
	}
	
	public void saveImage(){
		JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home") + File.separator + "Downloads"));

		int returnVal = chooser.showSaveDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
		}else{

		}
	}
	
	public static void start(){
		vue.add(new VueImage());
		vue.add(new VueDonnees());
		
		for(Vue vue : vue){
			vue.setVisible(true);
		}
	}
}
