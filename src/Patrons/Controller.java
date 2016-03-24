package Patrons;



import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private static Controller instance = new Controller();
	private static List<Vue> vue = new ArrayList<Vue>();
	private ZoomIn zoomIn;
	private ZoomOut zoomOut;
	private Drag drag;
	private Cursor crossHair = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor arrow = new Cursor(Cursor.HAND_CURSOR);
	private Command ouvrir = Ouvrir.getInstance();
	private Command sauvegarder = Save.getInstance();
	private ActionsList actions = ActionsList.getinstance();
	private int dragXStart;
	private int dragYStart;
	private int oldDragX;
	private int oldDragY;
	private int newDragX;
	private int newDragY;

	private Controller(){}

	public static Controller getInstance(){
		return instance;
	}

	public static void start(){
		vue.add(new VueImage());
		vue.add(new VueDonnees());
		
		//Seulement sur VueImage.
		vue.get(0).addButtonListener(getInstance().new ManageButtons());
		//le panel de vueImage doit etre listenable.
		vue.get(0).panel.addMouseListener(getInstance().new ManageMouse());
		vue.get(0).panel.addMouseMotionListener(getInstance().new ManageMouse());
		vue.get(0).panel.addMouseWheelListener(getInstance().new ManageMouse());
		
		for(Vue vue : vue){
			vue.addWindowListener(getInstance().new ManageWindows());
			vue.setVisible(true);
		}
	}
	
	private class ManageWindows extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			for(Vue window : vue){
				if(e.getSource().equals(window)){
					window.closing();
				}
			}
		}
	}

	private class ManageMouse extends MouseAdapter{

		public void mouseWheelMoved(MouseWheelEvent event){
			
			//On scrool le mouseWheel vers nous.
			if(event.getWheelRotation()>-1)
			{
				zoomOut = new ZoomOut(event.getPoint());
				actions.storeAndExecute(zoomOut);
			}
			else{
				zoomIn = new ZoomIn(event.getPoint());
				actions.storeAndExecute(zoomIn);
			}

		}

		public void mousePressed(MouseEvent event){
			dragXStart = event.getX();
			dragYStart = event.getY();
		}
		
		public void mouseReleased(MouseEvent event){
			newDragX = event.getX() - dragXStart;
			newDragY = event.getY() - dragYStart;
			drag = new Drag(newDragX, newDragY);
			actions.store(drag);
		}

		public void mouseDragged(MouseEvent event){
			newDragX = event.getX() - oldDragX;
			newDragY = event.getY() - oldDragY;
			drag = new Drag(newDragX, newDragY);
			actions.execute(drag);
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
			vue.get(0).setCursor(arrow);

			//L'utilisateur a appuy� sur Ouvrir imag
			if(event.getActionCommand().equals("Ouvrir image")){
				actions.storeAndExecute(ouvrir);
				actions.clearRecord();

				//L'utilisateur a appuy� sur Sauvegarder
			}else if(event.getActionCommand().equals("Sauvegarder")){
				actions.storeAndExecute(sauvegarder);
				actions.clearRecord();

				//L'utilisateur a appuy� sur Annuler
			}else if(event.getActionCommand().equals("Annuler")){
				actions.unDo();

				//L'utilisateur a appuy� sur Restaurer
			}else if(event.getActionCommand().equals("Restaurer")){
				actions.reDo();	

		}
	}
}
}
