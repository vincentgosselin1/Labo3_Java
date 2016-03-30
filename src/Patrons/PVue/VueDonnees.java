package Patrons.PVue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	//JTextField + JLabel.
	private JTextField tImage = new JTextField(){
		@Override
		public String toString(){
			return "tImage";
		}
	};
	public JTextField tZoom = new JTextField(){
		@Override
		public String toString(){
			return "tZoom";
		}
	};
	private JTextField tDragX = new JTextField(){
		@Override
		public String toString(){
			return "tDragX";
		}
	};
	private JTextField tDragY = new JTextField(){
		@Override
		public String toString(){
			return "tDragY";
		}
	};
	private JTextField tCommand = new JTextField();
	private JTextField tIndex = new JTextField();
	private JLabel lImageName = new JLabel("Image name :");
	private JLabel lZoom = new JLabel("Zoom :");
	private JLabel lDragX = new JLabel("Drag selon x :");
	private JLabel lDragY = new JLabel("Drag selon y :");
	private JLabel lCommand = new JLabel("Command count: ");
	private JLabel lIndex = new JLabel("List index :");

	private int nbCommand = 0;
	private int index = -1;
	private PanelDonnees panel;

	public VueDonnees(){
		super();
		panel = new PanelDonnees();
		setJMenuBar(menuBar);//Et voila c'est remis.
		//Alignement des JLabels avec JTextField avec le GroupLayout.
		//voir https://docs.oracle.com/javase/7/docs/api/javax/swing/GroupLayout.html
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		//pour ajouter des espaces entre JTextField et les JLabels.
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		//Le layout de la VueDonnee.
		// Create a sequential group for the horizontal axis.

		tCommand.setEditable(false);
		tIndex.setEditable(false);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the ls, the other the t fields.
		// Putting the ls in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		hGroup.addGroup(layout.createParallelGroup().addComponent(lImageName).
				addComponent(lZoom).addComponent(lDragX).addComponent(lDragY).
				addComponent(lCommand).addComponent(lIndex));
		hGroup.addGroup(layout.createParallelGroup().addComponent(tImage).addComponent(tZoom).
				addComponent(tDragX).addComponent(tDragY).addComponent(tCommand).addComponent(tIndex));
		layout.setHorizontalGroup(hGroup);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		// The sequential group contains two parallel groups that align
		// the contents along the baseline. The first parallel group contains
		// the first l and t field, and the second parallel group contains
		// the second l and t field. By using a sequential group
		// the ls and t fields are positioned vertically after one another.
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lImageName).addComponent(tImage));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lZoom).addComponent(tZoom));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lDragX).addComponent(tDragX));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lDragY).addComponent(tDragY));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lCommand).addComponent(tCommand));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lIndex).addComponent(tIndex));
		layout.setVerticalGroup(vGroup);

		scroll = new JScrollPane(panel);	
		add(scroll, BorderLayout.CENTER);
		setLocation(700, 0);
		setTitle("Vue des données");
		setSize(400, 270);
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void addButtonListenerOnChildren(ActionListener listenerButton) {
		tImage.addActionListener(listenerButton);
		tZoom.addActionListener(listenerButton);
		tDragX.addActionListener(listenerButton);
		tDragY.addActionListener(listenerButton);
	}

	@Override
	public void addMouseListeners(MouseAdapter mouseAdapter) {
		// Do nothing, no mouse action used here
	}

	public int getNbCommand() {
		return nbCommand;
	}

	public void setNbCommand(int nbCommand) {
		this.nbCommand = nbCommand;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private class PanelDonnees extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			tImage.setText(model.getImageName());
			tZoom.setText(String.valueOf(model.getZoom()));
			tDragX.setText(String.valueOf(Math.round(model.getDragX())));
			tDragY.setText(String.valueOf(Math.round(model.getDragY())));
			tCommand.setText(String.valueOf(nbCommand));
			tIndex.setText(String.valueOf(index));
		}
	}

}
