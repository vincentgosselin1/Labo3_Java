package Controller;

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
import javax.swing.SwingUtilities;

import Command.ActionsList;
import Command.CommandFactory;
import Memento.CouleurCaretaker;
import Model.ModelImage;
import Model.Observable;
import Vue.Vue;
import Vue.VueDonnees;
import Vue.VueImage;

public class Controller implements DownLoadDataFromList, InformationNeeded{
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private static ActionsList actions = ActionsList.getinstance();
	private static Observable model = ModelImage.getInstance();
	
	private static final Cursor CROSS = new Cursor(Cursor.CROSSHAIR_CURSOR);
	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
	private static final Cursor DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

	private String typeZoom = "";
	private double newX;
	private double newY;
	private double startX;
	private double startY;
	private double previousX;
	private double previousY;
	private double oDragX;
	private double oDragY;
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

	private void displaying(String vueString){
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
			if(SwingUtilities.isLeftMouseButton(event)){
				if(typeZoom == "in"){
					actions.storeAndExecute(CommandFactory.createCommand("ZoomIn"));
				}else if(typeZoom == "out"){
					actions.storeAndExecute(CommandFactory.createCommand("ZoomOut"));
				}else{
					typeZoom = "";
				}
			}else if(SwingUtilities.isRightMouseButton(event)){
				typeZoom = "";
				vue.get(0).setCursor(DEFAULT);
			}
		}

		public void mousePressed(MouseEvent event){
			startX = event.getX();
			startY = event.getY();
			previousX = event.getX();
			previousY = event.getY();
		}


		public void mouseReleased(MouseEvent event){
			if(isDragging){
				getNewPoint(startX, startY, event.getX(), event.getY());
				oDragX = model.getDragX() - newX;
				oDragY = model.getDragY() - newY;
				actions.store(CommandFactory.createCommand("Drag"));
				isDragging = false;
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
			isDragging = true;
			vue.get(0).setCursor(HAND);
			getNewPoint(previousX, previousY, event.getX(), event.getY());
			actions.execute(CommandFactory.createCommand("Drag"));
			previousX = event.getX();
			previousY = event.getY();
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
			vue.get(0).setCursor(DEFAULT);
			typeZoom = "";

			if(event.getSource() instanceof JMenuItem){
				//L'utilisateur a appuyé sur Ouvrir imag
				if(event.getActionCommand().equals("Ouvrir image")){
					actions.execute(CommandFactory.createCommand("Ouvrir"));
					actions.clearRecord();
					CouleurCaretaker.delMementos();

					//L'utilisateur a appuyé sur Sauvegarder
				}else if(event.getActionCommand().equals("Sauvegarder")){
					actions.execute(CommandFactory.createCommand("Save"));
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
					vue.get(0).setCursor(CROSS);

					//L'utilisateur a appuyé sur Zoom out
				}else if(event.getActionCommand().equals("Zoom out")){
					typeZoom = "out";
					vue.get(0).setCursor(CROSS);

				}else if(event.getActionCommand().equals("Toggle la vue de l'image")){
					displaying("Vue de l'image");

				}else if(event.getActionCommand().equals("Toggle la vue des données")){
					displaying("Vue des données");

				}else if(event.getActionCommand().equals("Change les couleurs!")){
					actions.storeAndExecute(CommandFactory.createCommand("CouleurChange"));
				}
			}else if(event.getSource() instanceof JTextField){
				System.out.println(event.getSource().toString());
				JTextField textField = (JTextField)event.getSource();

				if(validNumber(textField.getText()) && model.getImage() != null){
					switch(textField.toString()){
					case "tZoom" 	: zoom = Double.valueOf(textField.getText());
					actions.storeAndExecute(CommandFactory.createCommand("ZoomInFromVueDonnees"));	break;
					case "tDragX" 	: newX = (Double.valueOf(textField.getText()) - (model.getDragX()));
					newY = 0;
					oDragX = model.getDragX();
					oDragY = model.getDragY();
					actions.storeAndExecute(CommandFactory.createCommand("Drag"));					break;
					case "tDragY" 	: newY = (Double.valueOf(textField.getText()) - (model.getDragY()));
					newX = 0;
					oDragX = model.getDragX();
					oDragY = model.getDragY();
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

	private boolean validNumber(String text){
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
		vueDonnees.setNbCommand(recordSize);
		vueDonnees.update();
	}

	private void getNewPoint(double panelX, double panelY, double pointeurX, double pointeurY){
		Point2D adjPreviousPoint = getTranslatedPoint(panelX, panelY);
		Point2D adjNewPoint = getTranslatedPoint(pointeurX, pointeurY);

		newX = adjNewPoint.getX() - adjPreviousPoint.getX();
		newY = adjNewPoint.getY() - adjPreviousPoint.getY();
	}

	// Convert the panel coordinates into the cooresponding coordinates on the translated image.
	private Point2D getTranslatedPoint(double panelX, double panelY) {
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

	@Override
	public String getImageName() {
		return imageName;
	}
	
	@Override
	public double getoDragX() {
		return oDragX;
	}
	
	@Override
	public double getoDragY() {
		return oDragY;
	}

}
