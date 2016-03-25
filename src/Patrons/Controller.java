package Patrons;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Controller {
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private String typeZoom = "";
	private ZoomIn zoomIn;
	private ZoomOut zoomOut;
	private Drag drag;
	private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor arrow = new Cursor(Cursor.HAND_CURSOR);
	private Command ouvrir = Ouvrir.getInstance();
	private Command sauvegarder = Save.getInstance();
	private ActionsList actions = ActionsList.getinstance();
	private Observable model = ModelImage.getInstance();
	private double currentX;
	private double currentY;
	private double previousX;
	private double previousY;
	private int nbVueCachee=0;

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
			if(vue.panel != null) {
				vue.panel.addMouseListener(getInstance().new ManageMouse());
				vue.panel.addMouseMotionListener(getInstance().new ManageMouse());
			}

			vue.setVisible(true);
		}
	}
	
	public void displaying(String vueString){
		for(Vue vue : this.vue){
			if(vue.getTitle().equals(vueString)){
				if(vue.isVisible()){
					vue.setVisible(false);
					nbVueCachee++;
				}else{
					vue.setVisible(true);
					nbVueCachee--;
				}
				if(nbVueCachee == this.vue.size()){
					vue.dispose();
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
		}

		public void mousePressed(MouseEvent event){
			previousX = event.getX();
			previousY = event.getY();
		}
		
		public void mouseReleased(MouseEvent event){
//			newDragX = event.getX() - dragXStart - oldDragX;
//			newDragY = event.getY() - dragYStart - oldDragY;
//			drag = new Drag(newDragX, newDragY);
//			actions.store(drag);
		}

		public void mouseDragged(MouseEvent event){
			vue.get(0).removeMouseMotionListener(null);
			Point2D adjPreviousPoint = getTranslatedPoint(previousX, previousY);
			Point2D adjNewPoint = getTranslatedPoint(event.getX(), event.getY());

			double newX = adjNewPoint.getX() - adjPreviousPoint.getX();
			double newY = adjNewPoint.getY() - adjPreviousPoint.getY();

			previousX = event.getX();
			previousY = event.getY();

			currentX += newX;
			currentY += newY;
			
			drag = new Drag(newX, newY);
			actions.execute(drag);
		}
		
		// Convert the panel coordinates into the cooresponding coordinates on the translated image.
		private Point2D getTranslatedPoint(double panelX, double panelY) {

			AffineTransform affineTransform = new AffineTransform();
			double centerX = (double)vue.get(0).panel.getWidth() / 2;
			double centerY = (double)vue.get(0).panel.getHeight() / 2;
			affineTransform.translate(centerX, centerY);
			affineTransform.scale(model.getZoom(),model.getZoom());
			affineTransform.translate(model.getX(), model.getY());
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
				
			}else if(event.getActionCommand().equals("Toggle la vue de l'image")){
				displaying("Vue de l'image");
				
			}else if(event.getActionCommand().equals("Toggle la vue des données")){
				displaying("Vue des données");
			}
		}
	}
}
