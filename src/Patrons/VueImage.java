package Patrons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VueImage extends Vue{

	public VueImage(){
		super();
		this.menuBar.add(Zoom);
		this.setJMenuBar(menuBar);
		this.setLocation(0, 0);
		panel = new MonPanel();		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setPreferredSize(new Dimension(600, 600));

        scrollPanel = new JScrollPane(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scrollPanel, BorderLayout.CENTER);
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
				panel.setPreferredSize(new Dimension((int)(model.getZoom()*model.getWidth()), (int)(model.getZoom()*model.getHeight())));
			}
		}
	}
}
