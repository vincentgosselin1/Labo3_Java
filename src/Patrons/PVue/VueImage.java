package Patrons.PVue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VueImage extends Vue{
	private PanelImage panel;

	public VueImage(){
		super();
		menuBar.add(zoom);
		setJMenuBar(menuBar);
		setLocation(0, 0);
		panel = new PanelImage();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		scroll = new JScrollPane(panel);	
        add(scroll, BorderLayout.CENTER);
        setTitle("Vue de l'image");
        setSize(600, 600);
	}
	
	public AffineTransform getCurrentTransform() {

		AffineTransform affineTransform = new AffineTransform();

		affineTransform.scale(model.getZoom(),model.getZoom());
		affineTransform.translate(model.getDragX(), model.getDragY());

		return affineTransform;
	}
	
	@Override
	public void update() {
		repaint();
	}
	
	@Override
	public void addButtonListenerOnChildren(ActionListener listenerButton) {
		//Do nothing, we don't expect an action here
	}
	
	@Override
	public void addMouseListeners(MouseAdapter mouseAdapter) {
		panel.addMouseListener(mouseAdapter);
		panel.addMouseMotionListener(mouseAdapter);
		panel.addMouseWheelListener(mouseAdapter);
	}
	
	public void setCursor(Cursor cursor){
		panel.setCursor(cursor);
	}
	
	private class PanelImage extends JPanel{
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (model.getImage() != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				AffineTransform affineTransform = getCurrentTransform();
				g2d.drawImage(model.getImage(),  affineTransform, null);
				g2d.dispose();
//				panel
				panel.setPreferredSize(new Dimension((int)(model.getImage().getHeight()*model.getZoom()),(int)(model.getImage().getWidth()*model.getZoom())));
				revalidate();
			}
		}
	}
}
