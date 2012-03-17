package siwc.magazyn.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MagazynUtils {
	public static final String frameTitle = "Symulator magazynu";

	// Rozmiary okna
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = toolkit.getScreenSize();
	public static int frameWidth = (int) (screenSize.getWidth() * 0.75);
	public static int frameHeight = (int) (screenSize.getHeight() * 0.75);

	// Mapa
	public static int mapHeight = (int) (frameHeight * 0.6);
	public static int mapWidth = (int) (frameWidth * 0.6);

	// box
	public static int boxSize = 16;

	// Regal
	public static int liczbaRegalow = 2;		//TODO - nie wykorzystywane chwilowo ;-)
	public static int kolumnWRegale = 30;
	public static int rzedowWRegale = 4;
	public static int regalWidth = boxSize * kolumnWRegale;
	public static int regalHeight = boxSize * rzedowWRegale;
	public static int regalY = -1;
	public static int regalX = 100;

	// wozek
	public static int liftSizeX = boxSize;
	public static int liftSizeY = boxSize * 2;
	public static int liftStepX = 8;
	public static int liftStepY = 8;
	
	
	public static int getRegalY(int numerRegalu) { // nie wiem jak to nazwac :D ale ma to liczyc wartosc Y do setbounds(...) ;-)
		int pos=0;
			pos += liftSizeY;
			for(int i=0; i<numerRegalu; i++) {
				pos += regalHeight;
				pos += liftSizeY;
			}
			
		return pos; 
	}

}
