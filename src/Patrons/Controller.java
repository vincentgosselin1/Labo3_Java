package Patrons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private ActionsList actions = ActionsList.getinstance();
	private Command ouvrir = new Ouvrir();
	private Command sauvegarder = new Save();
	private String typeZoom = "";
	private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor arrow = new Cursor(Cursor.DEFAULT_CURSOR);
	
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
			vue.addButtonListener(getInstance().new ButtonListener());
			vue.setVisible(true);
		}
	}
	
	private class ButtonListener implements ActionListener{
		
		private static final double DEFAULT_ZOOM = 0.25;
		private ZoomIn zoomIn;
		
		/**
		 * <b><i>actionPerformed</i></b> 
		 * permet de récupérer l'action produite par l'utilisateur et la traiter.
		 * 
		 * @param action l'action produite par l'utilisateur
		 */
		public void actionPerformed(ActionEvent event) {
			vue.get(0).setCursor(arrow);
			
			//L'utilisateur a appuyé sur Ouvrir imag
			if(event.getActionCommand().equals("Ouvrir image")){
				ouvrirFichier();
				actions.clearRecord();

				//L'utilisateur a appuyé sur Sauvegarder
			}else if(event.getActionCommand().equals("Sauvegarder")){
				saveImage();
				actions.clearRecord();

				//L'utilisateur a appuyé sur Annuler
			}else if(event.getActionCommand().equals("Annuler")){
				actions.unDo();

				//L'utilisateur a appuyé sur Restaurer
			}else if(event.getActionCommand().equals("Restaurer")){
				actions.reDo();	
				
				//L'utilisateur a appuyé sur Zoom in
			}else if(event.getActionCommand().equals("Zoom in")){
				zoomIn = new ZoomIn(DEFAULT_ZOOM);
				typeZoom = "in";
				vue.get(0).setCursor(crossHair);
				
				//L'utilisateur a appuyé sur Zoom out
			}else if(event.getActionCommand().equals("Zoom out")){
				typeZoom = "ou";
				vue.get(0).setCursor(crossHair);
			}
		}
	}
}
