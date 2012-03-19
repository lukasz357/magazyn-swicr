package siwc.magazyn.panels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.Log4jEntityResolver;

import siwc.magazyn.utils.MagazynUtils;

public class MapaMagazynu extends JPanel {
	private static final long serialVersionUID = -8459627889558824665L;

	private Logger log = Logger.getLogger(MapaMagazynu.class);
	private RegalPanel regalPanel1;
	private RegalPanel regalPanel2;
	private RegalPanel regalPanel3;
	private ArrayList<RegalPanel> regaly = new ArrayList<>();
	private LiftPanel lift;

	public MapaMagazynu() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setSize(MagazynUtils.mapWidth, MagazynUtils.mapHeight);
		setBackground(Color.WHITE);
		setLayout(null);
		log.info("Rozmiar mapy: " + MagazynUtils.mapWidth + "x" + MagazynUtils.mapHeight);

		regalPanel1 = new RegalPanel(true);
		regalPanel1.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(0), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight + MagazynUtils.boxSize);
		add(regalPanel1);
		regaly.add(regalPanel1);

		regalPanel2 = new RegalPanel(false);
		regalPanel2.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(1), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight);
		add(regalPanel2);
		regaly.add(regalPanel2);

		regalPanel3 = new RegalPanel(false);
		regalPanel3.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(2), MagazynUtils.regalWidth + MagazynUtils.boxSize, MagazynUtils.regalHeight);
		add(regalPanel3);
		regaly.add(regalPanel3);

		lift = new LiftPanel();
		lift.setBackground(Color.red);
		lift.setBounds(lift.getX(), lift.getY(), lift.getXsize(), lift.getYsize());
		// lift.moveUP(getBounds());
		add(lift);

	}

	public void zmienKolorBoksu(String r, String position, Color c) {
		if (r.equals("regal1")) {
			regalPanel1.zmienKolorBoksu(position, c);
		} else if (r.equals("regal2")) {
			regalPanel2.zmienKolorBoksu(position, c);
		}
	}
	
	public void moveUp() {

		int xAktualne = lift.getX() + lift.getXsize();
		int yPo = lift.getY();
		boolean isEnteringShelf = false;

		/*
		 * 1 - czy x nalezy do x regalu 2- czy yWindy wejdzie na dolna krawedz regalu dla kazdego regalu
		 */

		for (RegalPanel it : regaly) {
			if (((xAktualne > it.getBounds().getMinX()) && (xAktualne <= it.getBounds().getMaxX())) && (yPo == it.getBounds().getMaxY())) {
				isEnteringShelf = true;
			}
		}

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else {
			lift.moveUp(this.getBounds());
			repaint();
		}
	}

	public void moveDown() {

		int xAktualne = lift.getX() + lift.getXsize();
		double yPo = lift.getY() + lift.getYsize();
		boolean isEnteringShelf = false;

		for (RegalPanel it : regaly) {
			if (((xAktualne > it.getBounds().getMinX()) && (xAktualne <= it.getBounds().getMaxX())) && (yPo == it.getBounds().getMinY())) {
				isEnteringShelf = true;
			}
		}

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else {
			lift.moveDown(getBounds());
			repaint();
		}
	}

	public void moveLeft() {

		int xPo = lift.getX();
		double yAktualne = lift.getY();
		boolean isEnteringShelf = false;

		System.out.println("xPo = " + xPo);
		System.out.println("regal 3 = " + regalPanel3.getBounds().getMaxX());

		for (RegalPanel it : regaly) {
			if ((yAktualne >= (it.getBounds().getMinY() - MagazynUtils.liftStepY)) && (yAktualne < it.getBounds().getMaxY()) && (xPo == it.getBounds().getMaxX())) {
				isEnteringShelf = true;
			}
		}

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else {
			lift.moveLeft(getBounds());
			repaint();
		}
	}

	public void moveRight() {

		int xPo = lift.getX() + lift.getXsize();
		double yAktualne = lift.getY();
		boolean isEnteringShelf = false;

		for (RegalPanel it : regaly) {
			if ((yAktualne >= (it.getBounds().getMinY() - MagazynUtils.liftStepY)) && (yAktualne < it.getBounds().getMaxY()) && (xPo == it.getBounds().getMinX())) {
				isEnteringShelf = true;
			}
		}

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else {
			lift.moveRight(getBounds());
			repaint();
		}
	}

	public void ustawPietro(int pietro) {
		for(RegalPanel r : regaly)
			r.ustawPietro(pietro);
		
	}

}
