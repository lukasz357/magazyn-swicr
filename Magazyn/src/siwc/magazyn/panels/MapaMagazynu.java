package siwc.magazyn.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import siwc.magazyn.utils.MagazynUtils;

public class MapaMagazynu extends JPanel {
	private static final long serialVersionUID = -8459627889558824665L;
	private RegalPanel regalPanel1;
	private RegalPanel regalPanel2;
	private Lift lift;

	public MapaMagazynu() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setSize(MagazynUtils.mapWidth, MagazynUtils.mapHeight);
		setBackground(Color.WHITE);
		setLayout(null);

		regalPanel1 = new RegalPanel();
		regalPanel1.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(0), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
		add(regalPanel1);

		regalPanel2 = new RegalPanel();
		regalPanel2.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(1), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
		add(regalPanel2);

		lift = new Lift();
		lift.setBackground(Color.red);
		lift.setBounds(lift.getX(), lift.getY(), lift.getXsize(), lift.getYsize());
		// lift.moveUP(getBounds());
		add(lift);

	}

	public void zmienKolorBoksu(String r, int x, int y, Color c) {
		if (r.equals("regal1")) {
			regalPanel1.zmienKolorBoksu(x, y, c);
		} else if (r.equals("regal2")) {
			regalPanel2.zmienKolorBoksu(x, y, c);
		}
	}
	

	public void moveUp() {
		lift.moveUp(this.getBounds());
		repaint();
	}

	public void moveDown() {
		lift.moveDown(getBounds());
		repaint();
	}

	public void moveLeft() {
		lift.moveLeft(getBounds());
		repaint();
	}

	public void moveRight() {
		lift.moveRight(getBounds());
		repaint();
	}


}

