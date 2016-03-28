package Patrons.PController;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Patrons.Command.CouleurChange;

import Patrons.Command.ActionsList;
import Patrons.Command.CommandFactory;
import Patrons.PModel.ModelImage;
import Patrons.PModel.Observable;
import Patrons.PVue.Vue;
import Patrons.PVue.VueDonnees;
import Patrons.PVue.VueImage;

public class Controller implements DownLoadDataFromList, InformationNeeded{
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private static ActionsList actions = ActionsList.getinstance();
	private String typeZoom = "";
	private Cursor crossHair = new Cursor(Cursor.CROSSHAIR_CURSOR);
	private Cursor arrow = new Cursor(Cursor.HAND_CURSOR);
	private Observable model = ModelImage.getInstance();
	private double trueX;
	private double trueY;
	private double newX;
	private double newY;
	private double startX;
	private double startY;
	private double previousX;
	private double previousY;
	private double zoom;
	private String imageName;
	private int nbVueCachee = 0;

	private Controller(){}

	public static Controller getInstance(){
		return instance;
	}

	public static void start(){
		vue.add(new VueImage());
		vue.add(new VueDonnees());

		for(Vue frame : vue){
			frame.addWindowListener(getInstance().new ManageWindows());
			frame.addButtonListener(getInstance().new ManageButtons());
			frame.addMouseListeners(getInstance().new ManageMouse());
			frame.setVisible(true);
		}
	}

	public void displaying(String vueString){
		for(Vue view : vue){
			if(view.getTitle().equals(vueString)){
				if(view.isVisible()){
					view.setVisible(false);
					nbVueCachee++;
				}else{
					view.setVisible(true);
					nbVueCachee--;
				}
				if(nbVueCachee == vue.size()){
					view.dispose();
					System.exit(0);
				}
			}
		}
	}

	private class ManageWindows extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			JFrame jFrame = (JFrame)(e.getSource());
			displaying(jFrame.getTitle());
		}
	}

	private class ManageMouse extends MouseAdapter{
		private boolean isDragging = false;

		public void mouseClicked(MouseEvent event){
			if(typeZoom == "in"){
				actions.storeAndExecute(CommandFactory.createCommand("ZoomIn"));
			}else if(typeZoom == "out"){
				actions.storeAndExecute(CommandFactory.createCommand("ZoomOut"));
			}else{
				typeZoom = "";
			}
		}

		public void mousePressed(MouseEvent event){
			//			if(model.getImage() != null)
			//				if(trueX < event.getX() && event.getX() < trueX + model.getImage().getWidth()*model.getZoom() 
			//				&& trueY < event.getY() && event.getY() < trueY + model.getImage().getHeight()*model.getZoom()){
			startX = event.getX();
			startY = event.getY();
			previousX = event.getX();
			previousY = event.getY();
			isDragging = true;
			//				}
		}


		public void mouseReleased(MouseEvent event){
			if(isDragging){
				getNewPoint(startX, startY, event);
				actions.store(CommandFactory.createCommand("Drag"));
				isDragging = false;
				trueX = newX;
				trueY = newY;
			}
		}

		public void mouseWheelMoved(MouseWheelEvent event){
			//On scrool le mouseWheel vers nous.
			if(event.isControlDown()){
				if(event.getWheelRotation()>-1)
				{
					actions.storeAndExecute(CommandFactory.createCommand("ZoomOut"));
				}else{
					actions.storeAndExecute(CommandFactory.createCommand("ZoomIn"));
				}
			}else
				//On renvoie l'event au parent (on scroll en gros)
				event.getComponent().getParent().dispatchEvent(event);
		}

		public void mouseDragged(MouseEvent event){
			if(isDragging){
				getNewPoint(previousX, previousY, event);
				actions.execute(CommandFactory.createCommand("Drag"));
				previousX = event.getX();
				previousY = event.getY();
			}
		}
	}

	private class ManageButtons implements ActionListener{
		/**
		 * <b><i>actionPerformed</i></b> 
		 * permet de r�cup�rer l'action produite par l'utilisateur et la traiter.
		 * 
		 * @param action l'action produite par l'utilisateur
		 */
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() instanceof JMenuItem){
				vue.get(0).setCursor(arrow);

				//L'utilisateur a appuy� sur Ouvrir imag
				if(event.getActionCommand().equals("Ouvrir image")){
					actions.execute(CommandFactory.createCommand("Ouvrir"));
					actions.clearRecord();

					//L'utilisateur a appuy� sur Sauvegarder
				}else if(event.getActionCommand().equals("Sauvegarder")){
					actions.execute(CommandFactory.createCommand("Save"));
					actions.clearRecord();

					//L'utilisateur a appuy� sur Annuler
				}else if(event.getActionCommand().equals("Annuler")){
					actions.unDo();

					//L'utilisateur a appuy� sur Restaurer
				}else if(event.getActionCommand().equals("Restaurer")){
					actions.reDo();	

					//L'utilisateur a appuy� sur Zoom in
				}else if(event.getActionCommand().equals("Zoom in")){
					typeZoom = "in";
					vue.get(0).setCursor(crossHair);

					//L'utilisateur a appuy� sur Zoom out
				}else if(event.getActionCommand().equals("Zoom out")){
					typeZoom = "out";
					vue.get(0).setCursor(crossHair);

				}else if(event.getActionCommand().equals("Toggle la vue de l'image")){
					displaying("Vue de l'image");

				}else if(event.getActionCommand().equals("Toggle la vue des donn�es")){
					displaying("Vue des donn�es");
				}else if(event.getActionCommand().equals("Change les couleurs!")){
					System.out.println("Almost there!");
					CouleurChange couleurChange = new CouleurChange();
					couleurChange.execute();
					//Color Change!
					
				}
			}else if(event.getSource() instanceof JTextField){
				System.out.println(event.getSource().toString());
				JTextField textField = (JTextField)event.getSource();

				if(validNumber(textField.getText()) && model.getImage() != null){
					switch(textField.toString()){
					case "tZoom" 	: zoom = Double.valueOf(textField.getText());
									  actions.storeAndExecute(CommandFactory.createCommand("ZoomInFromVueDonnees"));	break;
					case "tDragX" 	: newX += Double.valueOf(textField.getText());
									  actions.storeAndExecute(CommandFactory.createCommand("Drag"));					break;
					case "tDragY" 	: newY += Double.valueOf(textField.getText());
					  				  actions.storeAndExecute(CommandFactory.createCommand("Drag"));					break;
					}
				}else if(textField.toString() == "tImage"){
					imageName = textField.getText();
	  				actions.storeAndExecute(CommandFactory.createCommand("OuvrirSpecial"));
	  				actions.clearRecord();
				}
			}
		}
	}

	public boolean validNumber(String text){
		try{
			Double.parseDouble(text);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void notifyRecordSize(int index, int recordSize) {
		VueDonnees vueDonnees = (VueDonnees)vue.get(1);
		vueDonnees.setIndex(index);
		vueDonnees.setnbCommand(recordSize);
	}

	public void getNewPoint(double panelX, double panelY, MouseEvent event){
		Point2D adjPreviousPoint = getTranslatedPoint(panelX, panelY);
		Point2D adjNewPoint = getTranslatedPoint(event.getX(), event.getY());
		
		newX = adjNewPoint.getX() - adjPreviousPoint.getX();
		newY = adjNewPoint.getY() - adjPreviousPoint.getY();
	}

	// Convert the panel coordinates into the cooresponding coordinates on the translated image.
	public Point2D getTranslatedPoint(double panelX, double panelY) {
		VueImage vueImage = (VueImage)(vue.get(0));
		AffineTransform affineTransform = vueImage.getCurrentTransform();

		Point2D point2d = new Point2D.Double(panelX, panelY);
		try {
			return affineTransform.inverseTransform(point2d, null);
		} catch (NoninvertibleTransformException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public double getZoom() {
		return zoom;
	}

	@Override
	public double getNewX() {
		return newX;
	}

	@Override
	public double getNewY() {
		return newY;
	}

	public String getImageName() {
		return imageName;
	}
}
