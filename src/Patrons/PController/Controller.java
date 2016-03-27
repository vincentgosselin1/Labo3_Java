package Patrons.PController;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

import Patrons.PVue.Vue;
import Patrons.PVue.VueDonnees;
import Patrons.PVue.VueImage;

public class Controller implements ControllerIF{
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private String typeZoom = "";
	private ZoomIn zoomIn;
	private ZoomOut zoomOut;
	private Cursor crossHair = new Cursor(Cursor.CROSSHAIR_CURSOR);
	private Cursor arrow = new Cursor(Cursor.HAND_CURSOR);
	private Command ouvrir = Ouvrir.getInstance();
	private Command sauvegarder = Save.getInstance();
	private ActionsList actions = ActionsList.getinstance();
	private double newX;
	private double newY;
	private double startX;
	private double startY;
	private double previousX;
	private double previousY;
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
				zoomIn = new ZoomIn();
				actions.storeAndExecute(zoomIn);
			}else if(typeZoom == "out"){
				zoomOut = new ZoomOut();
				actions.storeAndExecute(zoomOut);
			}else{
				typeZoom = "";
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
				actions.store(getDrag(startX, startY, event));
				isDragging = false;
			}
		}

		public void mouseWheelMoved(MouseWheelEvent event){
			//On scrool le mouseWheel vers nous.
			if(event.isControlDown()){
				if(event.getWheelRotation()>-1)
				{
					zoomOut = new ZoomOut();
					actions.storeAndExecute(zoomOut);
				}else{
					zoomIn = new ZoomIn();
					actions.storeAndExecute(zoomIn);
				}
			}else
				//On renvoie l'event au parent (on scroll en gros)
				event.getComponent().getParent().dispatchEvent(event);
		}

		public void mouseDragged(MouseEvent event){
			isDragging = true;
			actions.execute(getDrag(previousX, previousY, event));
			previousX = event.getX();
			previousY = event.getY();
		}

		public Drag getDrag(double panelX, double panelY, MouseEvent event){
			Point2D adjPreviousPoint = getTranslatedPoint(panelX, panelY);
			Point2D adjNewPoint = getTranslatedPoint(event.getX(), event.getY());

			newX = adjNewPoint.getX() - adjPreviousPoint.getX();
			newY = adjNewPoint.getY() - adjPreviousPoint.getY();

			return new Drag(newX, newY);
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
	}

	private class ManageButtons implements ActionListener{
		/**
		 * <b><i>actionPerformed</i></b> 
		 * permet de récupérer l'action produite par l'utilisateur et la traiter.
		 * 
		 * @param action l'action produite par l'utilisateur
		 */
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() instanceof JMenuItem){
				vue.get(0).setCursor(arrow);

				//L'utilisateur a appuyé sur Ouvrir imag
				if(event.getActionCommand().equals("Ouvrir image")){
					actions.execute(ouvrir);
					actions.clearRecord();

					//L'utilisateur a appuyé sur Sauvegarder
				}else if(event.getActionCommand().equals("Sauvegarder")){
					actions.execute(sauvegarder);
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

				}else if(event.getActionCommand().equals("Toggle la vue de l'image")){
					displaying("Vue de l'image");

				}else if(event.getActionCommand().equals("Toggle la vue des données")){
					displaying("Vue des données");
				}
			}else if(event.getSource() instanceof JTextField){
				System.out.println(event.getSource().toString());
			}
		}
	}

	@Override
	public void notifyRecordSize(int index, int recordSize) {
		VueDonnees vueDonnees = (VueDonnees)vue.get(1);
		vueDonnees.setIndex(index);
		vueDonnees.setnbCommand(recordSize);
	}
}
