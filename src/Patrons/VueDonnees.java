package Patrons;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	private JTextField textImage;
	private JTextField textZoom;
	private JLabel lblNumbreOfCommand;
	private JTextField textCommand;
	private JPanel panel;
	
	public VueDonnees(){
		super();
		this.setJMenuBar(menuBar);
		panel = new MonPanel2();
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Zoom : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 36, 95, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Image (DataPath) :");
		lblNewLabel_1.setBounds(10, 11, 95, 14);
		panel.add(lblNewLabel_1);

		textImage = new JTextField();
		textImage.setBounds(105, 8, 350, 20);
		textImage.setColumns(10);
		panel.add(textImage);

		textZoom = new JTextField();
		textZoom.setBounds(105, 36, 86, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		textZoom.setColumns(10);
		panel.add(textZoom);

		lblNumbreOfCommand = new JLabel("Nb of Command : ");
		lblNumbreOfCommand.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumbreOfCommand.setBounds(10, 64, 95, 20);
		panel.add(lblNumbreOfCommand);

		textCommand = new JTextField();
		textCommand.setColumns(10);
		textCommand.setBounds(105, 64, 86, 20);
		panel.add(textCommand);
		setContentPane(panel);
		setLocation(700, 0);
		setSize(481, 193);
	}

	@Override
	public void update() {
		repaint();
	}

	public class MonPanel2 extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			textZoom.setText(String.valueOf(model.getZoom()));
			g2d.dispose();
			
		}
	}
}
