package siwc.magazyn.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import siwc.magazyn.utils.MagazynUtils;

public class LiftPanel extends JPanel {
	private static final long serialVersionUID = 3581127790689918702L;
	
	private final int XSIZE = MagazynUtils.boxSize;
	private final int YSIZE = MagazynUtils.boxSize * 2;
	private int x = 0;
	private int y = 0;
	private int dx = MagazynUtils.liftStepX;
	private int dy = MagazynUtils.liftStepY;
	private int level = 0;
	private Font font = new Font("Tahoma", Font.BOLD, 12); 

	public LiftPanel() {
		setLayout(new BorderLayout(0, 0));
		ustawPoziom();
	}

	public void moveUp() {
		y -= dy;
	}

	public void moveDown() {
			y += dy;
	}

	public void moveLeft() {
		x -= dx;
	}

	public void moveRight(Rectangle2D bounds) throws Exception {
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void ustawPoziom() {
		removeAll();
		JLabel levelLabel = new JLabel(Integer.toString(level));
		levelLabel.setFont(font);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(levelLabel);
		setBounds(x, y, XSIZE, YSIZE);
		revalidate();
	}

}
