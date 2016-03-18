package Patrons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueImage extends Vue{

	public VueImage(){
		super();
		super.menuBar.add(Zoom);
		this.setJMenuBar(menuBar);
		super.setLocation(0, 0);
		panel = new MonPanel();		
		this.add(panel);
	}

	@Override
	public void update() {
		repaint();
	}
	
	protected class MonPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			BufferedImage image = model.getImage();
			
			if (image != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawImage(image, 0, 0, 600, 600, this);
				g2d.dispose();
			}
		}
	}
}
