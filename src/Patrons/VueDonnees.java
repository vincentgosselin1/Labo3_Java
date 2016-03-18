package Patrons;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	
	private JLabel imageName = new JLabel(); 
	private JPanel panel = new JPanel();
	
	public VueDonnees(){
		super();
		this.setSize(300, 300);
		this.setTitle("Vue du Model");
		super.setLocation(700, 0);
		imageName.setText("Veuillez ouvrir une image!");
		panel.add(imageName);
		this.add(panel);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void setImageName(String imageName){
		this.imageName.setText(imageName);
	}



	
}
