package Patrons;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	//JTextField + JLabel.
	private JTextField textImage = new JTextField();
	private JTextField textZoom = new JTextField();;
	private JTextField textCommand = new JTextField();;
	private JLabel labelImageDatapath = new JLabel("Image Datapath : ");
	private JLabel labelZoom = new JLabel("Zoom : ");
	private JLabel labelCommand = new JLabel("Command count : ");
	//Le Panel.
	private JPanel panel;

	public VueDonnees(){
		super();
		panel = new MonPanel2();

		//Alignement des JLabels avec JTextField avec le GroupLayout.
		//voir https://docs.oracle.com/javase/7/docs/api/javax/swing/GroupLayout.html
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		//pour ajouter des espaces entre JTextField et les JLabels.
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		//Le layout de la VueDonnee.
		// Create a sequential group for the horizontal axis.

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the labels, the other the text fields.
		// Putting the labels in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		hGroup.addGroup(layout.createParallelGroup().
				addComponent(labelImageDatapath).addComponent(labelZoom).addComponent(labelCommand));
		hGroup.addGroup(layout.createParallelGroup().
				addComponent(textImage).addComponent(textZoom).addComponent(textCommand));
		layout.setHorizontalGroup(hGroup);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		// The sequential group contains two parallel groups that align
		// the contents along the baseline. The first parallel group contains
		// the first label and text field, and the second parallel group contains
		// the second label and text field. By using a sequential group
		// the labels and text fields are positioned vertically after one another.
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(labelImageDatapath).addComponent(textImage));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(labelZoom).addComponent(textZoom));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(labelCommand).addComponent(textCommand));
		layout.setVerticalGroup(vGroup);


		setContentPane(panel);
		setLocation(700, 0);
		setSize(400, 150);
	}

	@Override
	public void update() {
		repaint();
	}

	public class MonPanel2 extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			textZoom.setText(String.valueOf(model.getZoom()));
			textImage.setText(model.getImageDatapath());
		}
	}
}
