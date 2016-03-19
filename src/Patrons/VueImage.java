package Patrons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setPreferredSize(new Dimension(700, 700));
        scrollPanel = new JScrollPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			super.getHeight();

			if (model.getImage() != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				AffineTransform affineTransform = new AffineTransform(); 
				affineTransform.scale(model.getZoom(),model.getZoom());
				affineTransform.translate((int)(model.getDragX()), (int)(model.getDragY()));
				g2d.drawImage(model.getImage(), affineTransform, this);
				setPreferredSize(new Dimension(model.getHeight(),model.getWidth()));
				revalidate();
			}
		}
	}
}
