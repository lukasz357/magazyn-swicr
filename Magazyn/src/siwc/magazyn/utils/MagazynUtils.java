package siwc.magazyn.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MagazynUtils {
	public static final String frameTitle = "Magazyn";

	// Rozmiary okna
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = toolkit.getScreenSize();

	public static Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); // maxymalne
	public static int frameWidth = maxBounds.getWidth() > 1366 ? 1366 : (int) maxBounds.getWidth();
	public static int frameHeight = maxBounds.getWidth() > 1366 ? 750 : (int) maxBounds.getHeight();


	// box
	public static int boxSize = 18 * frameWidth / 1366;
	public static Color defaultBoxBackground = Color.GRAY;
	public static Color freeBoxBackround = Color.BLUE;
	public static Color liftBackground = Color.RED;
	public static Color busyBoxBackground = Color.YELLOW;
	public static int boxMovingSleepTime = 100;
	public static int defaultFreeBoxes = 1;
	

	// Regal
	public static int liczbaRegalow = 3; 
	public static int kolumnWRegale = 38;
	public static int rzedowWRegale = 4;
	public static int regalWidth = boxSize * kolumnWRegale;
	public static int regalHeight = boxSize * rzedowWRegale;
	
	@Deprecated
	public static int regalY = -1; // kazdy regla ma inna!
	public static int regalX = boxSize * 4;
	public static int liczbaPieter = 5;

	// wozek
	public static int liftSizeX = boxSize;
	public static int liftSizeY = boxSize * 2;
	public static int liftStepX = boxSize;
	public static int liftStepY = boxSize;
	
	// Mapa
	public static int mapHeight = getMapHeight();
	public static int mapWidth = getMapWidth();
	
	// odbior
	public static int odbiorX = 0;
	public static int odbiorY = mapHeight - liftSizeY;
	public static int odbiorWidth = 4 * boxSize;
	public static int odbiorHeight = liftSizeY;

	//pliki
	public static int liczbaKolumnPlikuZamowien = 6;

	



	private static int getMapHeight() {
		int height = 0;
		height += liftSizeY;
		for (int i = 0; i < liczbaRegalow; i++)
			height += 3 * liftSizeY;

		height += liftSizeY;

		return height * frameWidth / 1366;

	}

	private static int getMapWidth() {
		int width = 0;

		width += regalX;

		for (int i = 0; i < kolumnWRegale; i++)
			width += liftSizeX;

		width += 2*boxSize;
		return width;
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static int getRegalYPosition(int numerRegalu) {
		int pos = 0;
		pos += MagazynUtils.liftSizeY;

		for (int i = 0; i < numerRegalu; i++) {
			pos += MagazynUtils.regalHeight;
			pos += MagazynUtils.liftSizeY;
		}

		return pos;
	}
	public static int convertToRow(String position) {
		return position.charAt(0) - 65;
	}
	public static int convertToColumn(String position) {
		return Integer.parseInt(position.substring(1));
	}
}
