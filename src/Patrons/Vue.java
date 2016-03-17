package Patrons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Classe <b><i>Vue</i></b> <br><br>
 * 
 * Affichage est une classe qui permet d'afficher le contenu d'un fichier XML
 * dans une fenêtre selon une certaine mise en page. 
 */
@SuppressWarnings("serial")
public abstract class Vue extends JFrame implements  ActionListener, ObserverIF {
	//Objets nécessaires à l'affichage
	private Controller controller = Controller.getInstance();
	protected ModelImage model = ModelImage.getInstance();
	protected JPanel panel;
	
	private JMenuBar menuBar = new JMenuBar();

	private Actions actions = Actions.getinstance();
	private Command Do = new Do();
	private Command reDo = new reDo();
	private Command unDo = new unDo();

	//Boutons principaux du menu
	private JMenu Fichier = new JMenu("Fichier"), 
			Edition = new JMenu("Édition");

	//Sous-Boutons du menu
	private JMenuItem BoutonOuvrir = new JMenuItem("Ouvrir image"), 
			BoutonSave   = new JMenuItem("Sauvegarder"), 
			BoutonUnDo   = new JMenuItem("Annuler"),
			BoutonReDo   = new JMenuItem("Restaurer");
	/**
	 * Constructeur de la classe <b><i>Affichage</i></b> 
	 * initialise tout notre fenêtre.
	 */
	public Vue(){		
		BoutonOuvrir.addActionListener(this);
		BoutonSave.addActionListener(this);
		BoutonUnDo.addActionListener(this);
		BoutonReDo.addActionListener(this);

		this.setSize(600, 600);

		Fichier.add(BoutonOuvrir);
		Fichier.add(BoutonSave);
		Edition.add(BoutonUnDo);
		Edition.add(BoutonReDo);
		
		menuBar.add(Fichier);
		menuBar.add(Edition);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.addObserver(this);
	}

	/**
	 * <b><i>actionPerformed</i></b> 
	 * permet de récupérer l'action produite par l'utilisateur et la traiter.
	 * 
	 * @param action l'action produite par l'utilisateur
	 */
	public void actionPerformed(ActionEvent action) {
		//L'utilisateur a appuyé sur Ouvrir image
		if(action.getSource().equals(BoutonOuvrir)){
			controller.ouvrirFichier();
			actions.storeAndExecute(Do);

			//L'utilisateur a appuyé sur Sauvegarder
		}else if(action.getSource().equals(BoutonSave)){
			controller.saveImage();
			actions.storeAndExecute(Do);

			//L'utilisateur a appuyé sur Annuler
		}else if(action.getSource().equals(BoutonUnDo)){
			actions.storeAndExecute(unDo);

			//L'utilisateur a appuyé sur Restaurer
		}else if(action.getSource().equals(BoutonReDo)){
			actions.storeAndExecute(reDo);	
		}
	}

	@Override
	public abstract void update();
	
	
}

