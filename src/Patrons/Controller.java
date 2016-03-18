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
	
	private Controller(){}
	
	public static Controller getInstance(){
		return instance;
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
		private ActionsList actions = ActionsList.getinstance();
		private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
		private Cursor arrow = new Cursor(Cursor.DEFAULT_CURSOR);
		
		private static final double DEFAULT_ZOOM = 0.25;
		private ZoomIn zoomIn  = ZoomIn.getInstance();
		private ZoomIn zoomOut = ZoomIn.getInstance();;
		private Command ouvrir = Ouvrir.getInstance();
		private Command sauvegarder = Save.getInstance();
		private String typeZoom = "";
		
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
				actions.storeAndExecute(ouvrir);
				actions.clearRecord();

				//L'utilisateur a appuyé sur Sauvegarder
			}else if(event.getActionCommand().equals("Sauvegarder")){
				actions.storeAndExecute(ouvrir);
				actions.clearRecord();

				//L'utilisateur a appuyé sur Annuler
			}else if(event.getActionCommand().equals("Annuler")){
				actions.unDo();

				//L'utilisateur a appuyé sur Restaurer
			}else if(event.getActionCommand().equals("Restaurer")){
				actions.reDo();	
				
				//L'utilisateur a appuyé sur Zoom in
			}else if(event.getActionCommand().equals("Zoom in")){
				zoomIn.setZoomValue(DEFAULT_ZOOM);
				typeZoom = "in";
				vue.get(0).setCursor(crossHair);
				actions.storeAndExecute(zoomIn);
				
				//L'utilisateur a appuyé sur Zoom out
			}else if(event.getActionCommand().equals("Zoom out")){
				zoomOut.setZoomValue(DEFAULT_ZOOM);
				typeZoom = "out";
				vue.get(0).setCursor(crossHair);
				actions.storeAndExecute(zoomOut);
			}
		}
	}
}
