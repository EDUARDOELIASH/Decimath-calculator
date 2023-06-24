package objects;

import javax.swing.JScrollPane;

public class JScrollPane_transparent  extends JScrollPane{

	private static final long serialVersionUID = 1L;

	public JScrollPane_transparent() {
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		setBorder(null);
		setRequestFocusEnabled(false);
		setFocusable(false);
		setOpaque(false);
	}

}
