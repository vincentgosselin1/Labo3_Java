package Patrons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VueImage extends Vue{
	
	protected JScrollPane scrollPanel;

	public VueImage(){
		super();
		menuBar.add(Zoom);
		setJMenuBar(menuBar);
		setLocation(0, 0);
		panel = new MonPanel1();
		panel.setOpaque(false);
//        scrollPanel = new JScrollPane(panel);
//        setLayout(new BorderLayout());
        add(panel);
        setTitle("Vue de l'image");
        setSize(600, 600);
	}

	@Override
	public void update() {
		repaint();
	}
	
	public class MonPanel1 extends JPanel{
		private Image image;
		
		public void paintComponent(Graphics g) {
			image = model.getImage();
			if (image != null) {
				Graphics2D g2d = (Graphics2D) g.create();

				AffineTransform affineTransform = new AffineTransform();
				double centerX = (double)getWidth() / 2;
				double centerY = (double)getHeight() / 2;
				affineTransform.translate(centerX, centerY);
				affineTransform.scale(model.getZoom(),model.getZoom());
				affineTransform.translate(model.getX(), model.getY());
				g2d.drawImage(image,  affineTransform, this);
				//g2d.dispose();
				revalidate();
			}
		}
	}
}
