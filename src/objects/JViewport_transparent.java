package objects;

import javax.swing.JViewport;

public class JViewport_transparent extends JViewport {

	private static final long serialVersionUID = 1L;

	public JViewport_transparent() {
		setFocusable(false);
		setRequestFocusEnabled(false);
		setOpaque(false);
	}

}
