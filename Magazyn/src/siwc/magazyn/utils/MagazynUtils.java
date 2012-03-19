package siwc.magazyn.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MagazynUtils {
	public static final String frameTitle = "Symulator magazynu";

	// Rozmiary okna
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = toolkit.getScreenSize();
	
	public static Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); //maxymalne
	public static int frameWidth = (int) maxBounds.getWidth();
	public static int frameHeight = (int) maxBounds.getHeight();
//	public static int frameWidth = (int) ((int) screenSize.getWidth()*0.75);
//	public static int frameHeight = (int) ((int) screenSize.getHeight()*0.75);
	
	// box
	public static int boxSize = 18*frameWidth/1366;
	public static Color defaultBackground = Color.GRAY;
	
	// Regal
	public static int liczbaRegalow = 3;		//TODO - nie wykorzystywane chwilowo ;-)
	public static int kolumnWRegale = 30;
	public static int rzedowWRegale = 4;
	public static int regalWidth = boxSize * kolumnWRegale;
	public static int regalHeight = boxSize * rzedowWRegale;
	public static int regalY = -1;
	public static int regalX = boxSize * 7;
	public static int liczbaPieter = 5;

	// wozek
	public static int liftSizeX = boxSize;
	public static int liftSizeY = boxSize * 2;
	public static int liftStepX = boxSize;
	public static int liftStepY = boxSize;
	
	
	// Mapa
	public static int mapHeight = getMapHeight();
	public static int mapWidth = (int) (frameWidth * 0.5);

	private static int getMapHeight() {
		int height = 0;
		height += liftSizeY;
		for(int i=0; i<liczbaRegalow; i++)
			height += 3*liftSizeY;
		
		height += liftSizeY;
		
		return height * frameWidth/1366;
		
	}


	
	
	public static int getRegalY(int numerRegalu) { // nie wiem jak to nazwac :D ale ma to liczyc wartosc Y do setbounds(...) ;-)
		int pos=0;
			pos += liftSizeY - boxSize;
			if(numerRegalu > 0) pos += boxSize;
			for(int i=0; i<numerRegalu; i++) {
				pos += regalHeight;
				pos += liftSizeY;
			}
			
		return pos; 
	}

}
