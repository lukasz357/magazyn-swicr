package siwc.magazyn.panels;

import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import siwc.magazyn.utils.MagazynUtils;

public class Lift extends JPanel {

	/**
	 * Create the panel.
	 */

	private static final long serialVersionUID = 3581127790689918702L;
	private final int XSIZE = MagazynUtils.boxSize;
	private final int YSIZE = MagazynUtils.boxSize * 2;
	private int x = 0;
	private int y = 0;
	private int dx = MagazynUtils.liftStepX;
	private int dy = MagazynUtils.liftStepY;

	public Lift() {

	}

	public void moveUp(Rectangle2D bounds) {
		// System.out.println("minX=" + bounds.getMinX() + " maxX=" + bounds.getMaxX() + " minY=" + bounds.getMinY() + " maxY=" + bounds.getMaxY());
		if (y > 0)
			y -= dy;
	}

	public void moveDown(Rectangle2D bounds) {
		// System.out.println("minX=" + bounds.getMinX() + " maxX=" + bounds.getMaxX() + " minY=" + bounds.getMinY() + " maxY=" + bounds.getMaxY());
		if (y + YSIZE + dx < bounds.getMaxY() - bounds.getMinY())
			y += dy;

	}

	public void moveLeft(Rectangle2D bounds) {
		// System.out.println("minX=" + bounds.getMinX() + " maxX=" + bounds.getMaxX() + " minY=" + bounds.getMinY() + " maxY=" + bounds.getMaxY());
		if (x > 0)
			x -= dx;
	}

	public void moveRight(Rectangle2D bounds) {
		// System.out.println("minX=" + bounds.getMinX() + " maxX=" + bounds.getMaxX() + " minY=" + bounds.getMinY() + " maxY=" + bounds.getMaxY());
		if (x + XSIZE + dx < bounds.getMaxX() - bounds.getMinX())
			x += dx;
	}

	int getXsize() {
		return XSIZE;
	}

	int getYsize() {
		return YSIZE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
