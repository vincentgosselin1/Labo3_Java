package Patrons;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueDonnees extends Vue {
	public VueDonnees(){
		super();
		super.setLocation(700, 0);
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

			if (null != null) {
				Graphics2D g2d = (Graphics2D) g.create();

				g2d.dispose();
			}
		}
	}
}
