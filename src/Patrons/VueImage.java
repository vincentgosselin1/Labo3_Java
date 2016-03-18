package Patrons;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VueImage extends Vue{

	public VueImage(){
		super();
		this.setSize(600, 600);
		this.setTitle("Vue du Image");
		super.menuBar.add(Zoom);
		this.setJMenuBar(menuBar);
		super.setLocation(0, 0);
		panel = new MonPanel();		
		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(600,600));
		this.add(scrollPane);
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
				g2d.drawImage(image, model.getOrigine().x, model.getOrigine().y, (int)(model.getZoom()*model.getWidth()), (int)(model.getZoom()*model.getHeight()), this);
				g2d.dispose();
			}
		}
	}
}
