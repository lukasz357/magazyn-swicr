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

	// Regal
	public static int liczbaRegalow = 3; // TODO - nie wykorzystywane chwilowo ;-)
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
	public static int mapWidth = getMapWidth();

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

		width += regalX;
		return width;
	}

	public static int getRegalY(int numerRegalu, boolean numbers) { // nie wiem jak to nazwac :D ale ma to liczyc wartosc Y do setbounds(...) ;-)
		int pos = 0;
		pos += liftSizeY;
		if (numbers && numerRegalu > 0)
			pos += boxSize;

		for (int i = 0; i < numerRegalu; i++) {
			pos += regalHeight;
			pos += liftSizeY;
		}

		return pos;
	}
}
