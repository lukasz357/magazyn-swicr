package siwc.magazyn.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.xml.Log4jEntityResolver;

import siwc.magazyn.utils.MagazynUtils;

public class MapaMagazynu extends JPanel {
	private static final long serialVersionUID = -8459627889558824665L;
	private RegalPanel regalPanel1;
	private RegalPanel regalPanel2;
	private RegalPanel regalPanel3;
	private LiftPanel lift;

	public MapaMagazynu() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setSize(MagazynUtils.mapWidth, MagazynUtils.mapHeight);
		setBackground(Color.WHITE);
		setLayout(null);

		regalPanel1 = new RegalPanel(true);
		regalPanel1.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(0), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight + MagazynUtils.boxSize);
		add(regalPanel1);

		regalPanel2 = new RegalPanel(false);
		regalPanel2.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(1), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight);
		add(regalPanel2);

		regalPanel3 = new RegalPanel(false);
		regalPanel3.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(2), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight);
		add(regalPanel3);

		lift = new LiftPanel();
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

	/* sprawdzanie czy nie wjezdzam na regal */

	public void moveUp() {
		System.out.println("x = " + (lift.getX() + lift.getXsize()) + "y = " + (lift.getY() + lift.getYsize()));
		System.out.println("regal Panel1 = " + regalPanel1.getBounds());
		// int xPo = lift.getX() + lift.getDx();
		int xAktualne = lift.getX() + lift.getXsize();
		int yPo = lift.getY() - lift.getDy();
		System.out.println("yPo = " + yPo);
		System.out.println("regal 1 = " + regalPanel1.getBounds().getMinX() + " x max = " + regalPanel1.getBounds().getMaxX());
		System.out.println("regal 1 :y " + regalPanel1.getBounds().getMinY() + ", " + regalPanel1.getBounds().getMaxY());
		//sprawdzenie czy w 1 regale
		if ( ( xAktualne >= regalPanel1.getBounds().getMinX() && xAktualne <= regalPanel1.getBounds().getMaxX() ) && (yPo >= regalPanel1.getBounds().getMinY() && yPo <= regalPanel1.getBounds().getMaxY()) ) {
			System.err.println("argh !");
			System.out.println("Panie, co Pan robi ? Na regal wjechac chce Pan ?!");
		} else {
			lift.moveUp(this.getBounds());
			repaint();
			
		}
		System.out.println("po: x = " + lift.getX() + "y  = " + lift.getY());
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
