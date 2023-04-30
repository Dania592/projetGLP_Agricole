package gui.gestionnaire;

import java.awt.Dimension;

import javax.swing.JScrollBar;

public class CustomizedScrollBar extends JScrollBar{

	private static final long serialVersionUID = 1L;

	public CustomizedScrollBar() {
		super();
		setUI(new ModernScrollBarUI());
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setForeground(GeneralPaintStrategy.DARK_BROWN);
		setPreferredSize(new Dimension(8,8));
	}
	
}