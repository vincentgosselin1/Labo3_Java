package Patrons;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImageOp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VueImage extends Vue{
	
	protected JScrollPane scrollPanel;

	public VueImage(){
		super();
		setJMenuBar(menuBar);
		setLocation(0, 0);
		panel = new MonPanel1();
		panel.setPreferredSize(new Dimension(900,900));
        scrollPanel = new JScrollPane(panel);
        setLayout(new BorderLayout());
        add(scrollPanel, BorderLayout.CENTER);
        setSize(600, 600);
	}

	@Override
	public void update() {
		repaint();
	}
	
	public class MonPanel1 extends JPanel{
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);

			if (model.getImage() != null) {
				Graphics2D g2d = (Graphics2D) g;
				AffineTransform affineTransform = new AffineTransform(); 
				affineTransform.scale(model.getZoom(),model.getZoom());
				affineTransform.translate(model.getDragX(), model.getDragY());
				g2d.drawImage(model.getImage(),  affineTransform, this);

				revalidate();
				
			}
			
		}
	}
}
