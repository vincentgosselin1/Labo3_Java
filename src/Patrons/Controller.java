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
	private static VueDonnees vueDonnees = new VueDonnees();
	private static VueImage vueImage = new VueImage();
	private static ModelImage model = ModelImage.getInstance();

	private Controller(){}

	public static Controller getInstance(){
		return instance;
	}

	public static void start(){

		//A changer!
		vueImage.addButtonListener(getInstance().new ButtonListener());
		vueImage.setVisible(true);

		vueDonnees.addButtonListener(getInstance().new ButtonListener());
		vueDonnees.setVisible(true);

	}

	private class ButtonListener implements ActionListener{
		private ActionsList actions = ActionsList.getinstance();
		private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
		private Cursor arrow = new Cursor(Cursor.DEFAULT_CURSOR);

		private ZoomIn zoomIn;
		private ZoomOut zoomOut;
		private Command ouvrir = Ouvrir.getInstance();
		private Command sauvegarder = Save.getInstance();
		private String typeZoom = "";

		/**
		 * <b><i>actionPerformed</i></b> 
		 * permet de recuperer l'action produite par l'utilisateur et la traiter.
		 * 
		 * @param action l'action produite par l'utilisateur
		 */
		public void actionPerformed(ActionEvent event) {
			vueImage.setCursor(arrow);

			//L'utilisateur a appuye sur Ouvrir image
			if(event.getActionCommand().equals("Ouvrir image")){
				actions.storeAndExecute(ouvrir);
				actions.clearRecord();
				vueDonnees.setImageName(model.getImageName());
				vueDonnees.setImageDimensions(model.getWidth(), model.getHeight());

				//L'utilisateur a appuye sur Sauvegarder
			}else if(event.getActionCommand().equals("Sauvegarder")){
				actions.storeAndExecute(ouvrir);
				actions.clearRecord();

				//L'utilisateur a appuye sur Annuler
			}else if(event.getActionCommand().equals("Annuler")){
				actions.unDo();

				//L'utilisateur a appuye sur Restaurer
			}else if(event.getActionCommand().equals("Restaurer")){
				actions.reDo();	

				//L'utilisateur a appuye sur Zoom in
			}else if(event.getActionCommand().equals("Zoom in")){
				zoomIn = new ZoomIn();
				typeZoom = "in";
				vueImage.setCursor(crossHair);
				actions.storeAndExecute(zoomIn);

				//L'utilisateur a appuye sur Zoom out
			}else if(event.getActionCommand().equals("Zoom out")){
				zoomOut = new ZoomOut();
				typeZoom = "out";
				vueImage.setCursor(crossHair);
				actions.storeAndExecute(zoomOut);
			}

			model.notifyAllObservers();
		}
	}
}
