package Patrons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.event.MenuEvent;

@SuppressWarnings("serial")
public class VueImage extends Vue{
	private BufferedImage image;

	public VueImage(){
		super();
		super.setLocation(0, 0);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(model.getImage(), 0, 0, this);
	}

	@Override
	public void update() {
		this.paintComponent(this.pane.getGraphics());
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		if (model.getImage() != null)
			update();
	}

}
