package siwc.magazyn.panels;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import siwc.magazyn.dto.PoleTO;

public class BoxPanel extends JPanel {
	private static final long serialVersionUID = 7627361909398731419L;

	private int positionX = 0; 				// pozycja X w regale
	private int positionY = 0; 				// pozycja Y w regale
	private boolean shelf = false; 			// czy dany kwadrat magazynu jest regałem
	private boolean entryPoint = false;		// czy jest punktem odbioru
	private boolean movable = false; 		// czy możliwe jest przesuwanie
	private boolean free = false;
	private PoleTO box = null;
	private ImageIcon icon = null;

	public BoxPanel(int x, int y, PoleTO b) {
		System.out.println("W konstruktorze BoxPanel()");
		System.out.println("X:"+ x+" Y: "+y);
		positionX = x;
		positionY = y;
		box = b;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public boolean isShelf() {
		return shelf;
	}

	public void setShelf(boolean shelf) {
		this.shelf = shelf;
	}

	public boolean isEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(boolean entryPoint) {
		this.entryPoint = entryPoint;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public PoleTO getBox() {
		return box;
	}

	public void setBox(PoleTO box) {
		this.box = box;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
