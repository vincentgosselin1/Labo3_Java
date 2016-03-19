package Patrons;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	
	private JLabel imageName = new JLabel();
	private JTextField imageDimensions = new JTextField(20); 
	private JPanel panel = new JPanel();
	
	public VueDonnees(){
		super();
		this.setSize(400, 400);
		this.setTitle("Vue du Model");
		super.setLocation(700, 0);
		imageName.setText("Veuillez ouvrir une image!");
		panel.add(imageName);
		panel.add(imageDimensions);
		this.add(panel);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void setImageName(String imageName){
		this.imageName.setText("Image ouverte : " + imageName);
	}
	public void setImageDimensions(int width, int height){
		String dimensions = Integer.toString(width) + " de largeur et " + Integer.toString(height) + " de longueur";
		this.imageDimensions.setText(dimensions);
	}



	
}
