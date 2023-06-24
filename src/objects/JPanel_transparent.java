package objects;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class JPanel_transparent extends JPanel  {
	private static final long serialVersionUID = 1L;
	final Color c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");

	public JPanel_transparent() {
		setRequestFocusEnabled(false);
		setOpaque(false);
		setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
	}
}
