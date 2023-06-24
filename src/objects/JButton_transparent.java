package objects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class JButton_transparent extends JButton {
	private static final long serialVersionUID = 1L;
	
	final Color c;
	
	public JButton_transparent(String text) {
		c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");

		
		setText(text);
		
		setHorizontalTextPosition(SwingConstants.CENTER);
		
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFont(new Font("Arial", Font.ITALIC, 21));
        setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
	}
}
