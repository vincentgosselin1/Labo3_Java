package Patrons;

import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

/**
 * Classe <b><i>Vue</i></b> <br><br>
 * 
 * Affichage est une classe qui permet d'afficher le contenu d'un fichier XML
 * dans une fenetre selon une certaine mise en page. 
 */
@SuppressWarnings("serial")
public abstract class Vue extends JFrame implements ObserverIF {
	//Objets necessaires a l'affichage
	protected ModelImage model = ModelImage.getInstance();
	protected JPanel panel;
	protected JScrollPane scrollPane;
	protected JMenuBar menuBar = new JMenuBar();

	//Boutons principaux du menu
	protected JMenu Fichier = new JMenu("Fichier"), 
				  Edition = new JMenu("Edition"),
				  Zoom	  = new JMenu("Zoom");

	//Sous-Boutons du menu
	protected JMenuItem BoutonOuvrir = new JMenuItem("Ouvrir image"), 
					  BoutonSave   = new JMenuItem("Sauvegarder"), 
					  BoutonUnDo   = new JMenuItem("Annuler"),
					  BoutonReDo   = new JMenuItem("Restaurer"),
					  ZoomIn	   = new JMenuItem("Zoom in"),
					  ZoomOut	   = new JMenuItem("Zoom out");
	/**
	 * Constructeur de la classe <b><i>Affichage</i></b> 
	 * initialise tout notre fen�tre.
	 */
	public Vue(){		

		Fichier.add(BoutonOuvrir);
		Fichier.add(BoutonSave);
		Edition.add(BoutonUnDo);
		Edition.add(BoutonReDo);
		Zoom.add(ZoomIn);
		Zoom.add(ZoomOut);
		
		menuBar.add(Fichier);
		menuBar.add(Edition);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.addObserver(this);
	}

	void addButtonListener(ActionListener listenerButton){
		BoutonOuvrir.addActionListener(listenerButton);
		BoutonSave.addActionListener(listenerButton);
		BoutonUnDo.addActionListener(listenerButton);
		BoutonReDo.addActionListener(listenerButton);
		ZoomIn.addActionListener(listenerButton);
		ZoomOut.addActionListener(listenerButton);
	}
	
	@Override
	public abstract void update();
	
	public void setCursor(Cursor cursor){
		panel.setCursor(cursor);
	}
	
}

