package siwc.magazyn.panels;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;

public class BoxPanel extends JPanel {
	private static final long serialVersionUID = 7627361909398731419L;

	private int positionX = 0; 				// pozycja X w regale
	private int positionY = 0; 				// pozycja Y w regale
	private boolean shelf = false; 			// czy dany kwadrat magazynu jest regałem
	private boolean entryPoint = false;		// czy jest punktem odbioru
	private boolean movable = false; 		// czy możliwe jest przesuwanie
	private boolean free = false;
	private PoleTO pole = null;
	private ImageIcon icon = null;

	public BoxPanel(int x, int y, PoleTO b) {
		positionX = x;
		positionY = y;
		pole = b;
		pole.setTowar(b.getId() == null ? new TowarTO(): new TowarTO(b.getId()));
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

	public PoleTO getPole() {
		return pole;
	}

	public void setBox(PoleTO box) {
		this.pole = box;
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
