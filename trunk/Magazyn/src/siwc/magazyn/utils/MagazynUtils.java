package siwc.magazyn.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MagazynUtils {
	public static final String frameTitle = "Symulator magazynu";
	
	// Rozmiary okna
	public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static final Dimension screenSize = toolkit.getScreenSize(); 
	public static final int frameWidth = (int) screenSize.getWidth() * 3 / 4;
	public static final int frameHeight = (int) screenSize.getHeight() * 3 / 4;
}
