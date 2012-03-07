package magazyn;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MagazynUtils {
	public static String frameTitle = "Magazyn";
	// Rozmiary okna 
	public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static final Dimension screenSize = toolkit.getScreenSize(); 
	public static final int frameWidth = (int) screenSize.getWidth() * 6 / 8;
	public static final int frameHeight = (int) screenSize.getHeight() * 6 / 8;
	
}
