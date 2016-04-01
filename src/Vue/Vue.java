package Vue;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Model.ModelImage;
import Model.Observable;


/**
 * Classe <b><i>Vue</i></b> <br><br>
 * 
 * Affichage est une classe qui permet d'afficher le contenu d'un fichier XML
 * dans une fenétre selon une certaine mise en page. 
 */
@SuppressWarnings("serial")
public abstract class Vue extends JFrame implements ObserverIF {
	//Objets nécessaires é l'affichage
	protected Observable model = ModelImage.getInstance();
	protected JScrollPane scroll;
	protected JMenuBar menuBar = new JMenuBar();

	//s principaux du menu
	protected JMenu Fichier = new JMenu("Fichier"), 
			Edition = new JMenu("Édition"),
			Affichage = new JMenu("Affichage"),
			Zoom	  = new JMenu("Zoom"),
			Couleur = new JMenu("Couleur");

	//Sous-s du menu
	protected JMenuItem Ouvrir = new JMenuItem("Ouvrir image"), 
			Save   = new JMenuItem("Sauvegarder"), 
			UnDo   = new JMenuItem("Annuler"),
			ReDo   = new JMenuItem("Restaurer"),
			ToggleVueImage = new JMenuItem("Toggle la vue de l'image"),
			ToggleVueDonnees = new JMenuItem("Toggle la vue des données"),
			ZoomIn	   = new JMenuItem("Zoom in"),
			ZoomOut	   = new JMenuItem("Zoom out"),
			CouleurChange = new JMenuItem("Change les couleurs!");
	/**
	 * Constructeur de la classe <b><i>Affichage</i></b> 
	 * initialise tout notre fenétre.
	 */
	public Vue(){	
		Ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		UnDo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		ReDo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		ToggleVueImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		ToggleVueDonnees.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		//A rajouter pour bouton Couleur.,
		
		Fichier.add(Ouvrir);
		Fichier.add(Save);
		Edition.add(UnDo);
		Edition.add(ReDo);
		Zoom.add(ZoomIn);
		Zoom.add(ZoomOut);
		Affichage.add(ToggleVueDonnees);
		Affichage.add(ToggleVueImage);
		Couleur.add(CouleurChange);

		menuBar.add(Fichier);
		menuBar.add(Edition);
		menuBar.add(Affichage);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		model.addObserver(this);
	}

	public void addButtonListener(ActionListener listenerButton){
		Ouvrir.addActionListener(listenerButton);
		Save.addActionListener(listenerButton);
		UnDo.addActionListener(listenerButton);
		ReDo.addActionListener(listenerButton);
		ZoomIn.addActionListener(listenerButton);
		ZoomOut.addActionListener(listenerButton);
		ToggleVueImage.addActionListener(listenerButton);
		ToggleVueDonnees.addActionListener(listenerButton);
		CouleurChange.addActionListener(listenerButton);
		addButtonListenerOnChildren(listenerButton);
	}
	
	public abstract void addButtonListenerOnChildren(ActionListener listenerButton);
	
	public abstract void addMouseListeners(MouseAdapter mouseAdapter);

	@Override
	public abstract void update();
}

