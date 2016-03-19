package Patrons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private static ModelImage model = ModelImage.getInstance();
	private String typeZoom = "";
	private ZoomIn zoomIn;
	private ZoomOut zoomOut;
	private Drag drag;
	private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor arrow = new Cursor(Cursor.HAND_CURSOR);
	private Command ouvrir = Ouvrir.getInstance();
	private Command sauvegarder = Save.getInstance();
	private ActionsList actions = ActionsList.getinstance();
	private double dragXStart;
	private double dragYStart;

	private Controller(){}

	public static Controller getInstance(){
		return instance;
	}

	public static void start(){
		vue.add(new VueImage());
		vue.add(new VueDonnees());

		for(Vue vue : vue){
			vue.addWindowListener(getInstance().new ManageWindows());
			vue.addButtonListener(getInstance().new ManageButtons());
			if(vue.panel !=null) {
				vue.panel.addMouseListener(getInstance().new ManageMouse());
				vue.panel.addMouseMotionListener(getInstance().new ManageMouse());
			}
			vue.setVisible(true);
		}
	}

	private class ManageWindows extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {

		}

	}

	private class ManageMouse extends MouseAdapter{

		public void mouseClicked(MouseEvent event){
			if(typeZoom == "in"){
				zoomIn = new ZoomIn(event.getPoint());
				actions.storeAndExecute(zoomIn);
			}else if(typeZoom == "out"){
				zoomOut = new ZoomOut(event.getPoint());
				actions.storeAndExecute(zoomOut);
			}else{
				typeZoom = "";
			}

			model.notifyAllObservers();
		}

		public void mousePressed(MouseEvent event){
			dragXStart = event.getX();
			dragYStart = event.getY();

		}

		public void mouseDragged(MouseEvent event){
			drag = new Drag(event.getX() - dragXStart, event.getY() - dragYStart);
			actions.storeAndExecute(drag);
			model.notifyAllObservers();
		}
	}

	private class ManageButtons implements ActionListener{
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
				actions.storeAndExecute(sauvegarder);
				actions.clearRecord();

				//L'utilisateur a appuyé sur Annuler
			}else if(event.getActionCommand().equals("Annuler")){
				actions.unDo();

				//L'utilisateur a appuyé sur Restaurer
			}else if(event.getActionCommand().equals("Restaurer")){
				actions.reDo();	

				//L'utilisateur a appuyé sur Zoom in
			}else if(event.getActionCommand().equals("Zoom in")){
				typeZoom = "in";
				vue.get(0).setCursor(crossHair);

				//L'utilisateur a appuyé sur Zoom out
			}else if(event.getActionCommand().equals("Zoom out")){
				typeZoom = "out";
				vue.get(0).setCursor(crossHair);
			}

			model.notifyAllObservers();
		}
	}
}
