package Patrons.PVue;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Patrons.PModel.ModelImage;
import Patrons.PModel.Observable;


/**
 * Classe <b><i>Vue</i></b> <br><br>
 * 
 * Affichage est une classe qui permet d'afficher le contenu d'un fichier XML
 * dans une fenêtre selon une certaine mise en page. 
 */
@SuppressWarnings("serial")
public abstract class Vue extends JFrame implements ObserverIF {
	//Objets nécessaires à l'affichage
	protected Observable model = ModelImage.getInstance();
	protected JScrollPane scroll;
	protected JMenuBar menuBar = new JMenuBar();

	//s principaux du menu
	protected JMenu fichier = new JMenu("Fichier"), 
			edition = new JMenu("Édition"),
			affichage = new JMenu("Affichage"),
			zoom	  = new JMenu("Zoom");

	//Sous-s du menu
	protected JMenuItem ouvrir = new JMenuItem("Ouvrir image"), 
			save   = new JMenuItem("Sauvegarder"), 
			unDo   = new JMenuItem("Annuler"),
			reDo   = new JMenuItem("Restaurer"),
			toggleVueImage = new JMenuItem("Toggle la vue de l'image"),
			toggleVueDonnees = new JMenuItem("Toggle la vue des données"),
			zoomIn	   = new JMenuItem("Zoom in"),
			zoomOut	   = new JMenuItem("Zoom out");
	/**
	 * Constructeur de la classe <b><i>Affichage</i></b> 
	 * initialise tout notre fenêtre.
	 */
	public Vue(){	
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		unDo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		reDo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		toggleVueImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		toggleVueDonnees.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		fichier.add(ouvrir);
		fichier.add(save);
		edition.add(unDo);
		edition.add(reDo);
		zoom.add(zoomIn);
		zoom.add(zoomOut);
		affichage.add(toggleVueDonnees);
		affichage.add(toggleVueImage);

		menuBar.add(fichier);
		menuBar.add(edition);
		menuBar.add(affichage);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		model.addObserver(this);
	}

	public void addButtonListener(ActionListener listenerButton){
		ouvrir.addActionListener(listenerButton);
		save.addActionListener(listenerButton);
		unDo.addActionListener(listenerButton);
		reDo.addActionListener(listenerButton);
		zoomIn.addActionListener(listenerButton);
		zoomOut.addActionListener(listenerButton);
		toggleVueImage.addActionListener(listenerButton);
		toggleVueDonnees.addActionListener(listenerButton);
		addButtonListenerOnChildren(listenerButton);
	}
	
	public abstract void addButtonListenerOnChildren(ActionListener listenerButton);
	
	public abstract void addMouseListeners(MouseAdapter mouseAdapter);

	@Override
	public abstract void update();
}

