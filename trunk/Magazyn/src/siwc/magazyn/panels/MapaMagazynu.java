package siwc.magazyn.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import siwc.magazyn.utils.MagazynUtils;
import javax.swing.SwingConstants;

public class MapaMagazynu extends JPanel {
	private static final long serialVersionUID = -8459627889558824665L;

	private Logger log = Logger.getLogger(MapaMagazynu.class);
	private RegalPanel regalPanel1;
	private RegalPanel regalPanel2;
	private RegalPanel regalPanel3;
	private ArrayList<RegalPanel> regaly = new ArrayList<>();
	private LiftPanel lift;
	private JPanel odbior;

	public MapaMagazynu() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setSize(MagazynUtils.mapWidth, MagazynUtils.mapHeight);
		setBackground(Color.WHITE);
		setLayout(null);
		log.info("Rozmiar mapy: " + MagazynUtils.mapWidth + "x" + MagazynUtils.mapHeight);

		regalPanel1 = new RegalPanel(false, false);
		regalPanel1.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(0, false), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
		add(regalPanel1);
		regaly.add(regalPanel1);

		regalPanel2 = new RegalPanel(false, false);
		regalPanel2.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(1, false), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
		add(regalPanel2);
		regaly.add(regalPanel2);

		regalPanel3 = new RegalPanel(false, false);
		regalPanel3.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalY(2, false), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
		add(regalPanel3);
		regaly.add(regalPanel3);

		lift = new LiftPanel();
		lift.setBackground(Color.red);
		lift.setBounds(lift.getX(), lift.getY(), lift.getXsize(), lift.getYsize());
		add(lift);

		odbior = new JPanel();
		odbior.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		odbior.setBackground(Color.YELLOW);
		JLabel label = new JLabel("Odbiór");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		odbior.add(label, BorderLayout.CENTER);
		int odbiorPozycjaY = MagazynUtils.mapHeight - MagazynUtils.liftSizeY;
		odbior.setBounds(0, odbiorPozycjaY, 4 * MagazynUtils.liftSizeX, MagazynUtils.liftSizeY);
		add(odbior);

	}

	public void zmienKolorBoksu(String r, String position, Color c) {
		if (r.equals("regal1")) {
			regalPanel1.zmienKolorBoksu(position, c);
		} else if (r.equals("regal2")) {
			regalPanel2.zmienKolorBoksu(position, c);
		} else if (r.equals("regal3")) {
			regalPanel3.zmienKolorBoksu(position, c);
		}
	}

	public void moveUp() {

		int xAktualne = lift.getX() + lift.getXsize();
		int yPo = lift.getY();
		boolean isEnteringShelf = false;
		boolean isEnteringReceivePoint = false;
		/*
		 * 1 - czy x nalezy do x regalu 2- czy yWindy wejdzie na dolna krawedz regalu dla kazdego regalu
		 */

		for (RegalPanel it : regaly) {
			if (((xAktualne > it.getBounds().getMinX()) && (xAktualne <= it.getBounds().getMaxX())) && (yPo == it.getBounds().getMaxY())) {
				isEnteringShelf = true;
			}
		}
		// punkt odbioru
		// if (xAktualne <= odbior.getBounds().getMaxX() && lift.getX() >= odbior.getBounds().getMinX() && lift.getY() >= odbior.getBounds().getMaxY())
		// isEnteringReceivePoint = true;

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else if (isEnteringReceivePoint) {
			System.err.println("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");
		} else {
			lift.moveUp(this.getBounds());
			repaint();
		}
	}

	public void moveDown() {

		int xAktualne = lift.getX() + lift.getXsize();
		double yPo = lift.getY() + lift.getYsize();
		boolean isEnteringShelf = false;
		boolean isEnteringReceivePoint = false;
		for (RegalPanel it : regaly) {
			if (((xAktualne > it.getBounds().getMinX()) && (xAktualne <= it.getBounds().getMaxX() + 1)) && (yPo == it.getBounds().getMinY())) {
				isEnteringShelf = true;
			}
		}
		// punkt odbioru
		// if (xAktualne <= odbior.getBounds().getMaxX() && lift.getX() >= odbior.getBounds().getMinX() && yPo >= odbior.getBounds().getMinY())
		// isEnteringReceivePoint = true;

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else if (isEnteringReceivePoint) {
			System.err.println("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");
		} else {
			lift.moveDown(getBounds());
			repaint();
		}
	}

	public void moveLeft() {

		int xPo = lift.getX();
		double yAktualne = lift.getY();
		boolean isEnteringShelf = false;
		boolean isEnteringReceivePoint = false;
		System.out.println("xPo = " + xPo);
		System.out.println("regal 3 = " + regalPanel3.getBounds().getMaxX());

		for (RegalPanel it : regaly) {
			if ((yAktualne >= (it.getBounds().getMinY() - MagazynUtils.liftStepY)) && (yAktualne < it.getBounds().getMaxY()) && (xPo == it.getBounds().getMaxX())) {
				isEnteringShelf = true;
			}
		}

		// zle dziala
		// if ((lift.getY() +lift.getYsize() <= odbior.getBounds().getMaxY() || lift.getY() >= odbior.getBounds().getMinY()) && lift.getX() == odbior.getBounds().getMaxX())
		// isEnteringReceivePoint = true;

		if (isEnteringShelf) {
			System.err.println("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
		} else if (isEnteringReceivePoint) {
			System.err.println("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");
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

	public void pokazPietro(int pietro) {
		for (RegalPanel r : regaly)
			r.pokazPietro(pietro);

	}

	public void obrocWPrawo(String regal) {
		if (regal.equals("regal1")) {
			regalPanel1.obrocWPrawo();
		} else if (regal.equals("regal2")) {
			regalPanel2.obrocWPrawo();
		} else if (regal.equals("regal3")) {
			regalPanel3.obrocWPrawo();
		}

	}

	public void obrocWLewo(String regal) {
		if (regal.equals("regal1")) {
			regalPanel1.obrocWLewo();
		} else if (regal.equals("regal2")) {
			regalPanel2.obrocWLewo();
		} else if (regal.equals("regal3")) {
			regalPanel3.obrocWLewo();
		}
	}

	public void lifDown() {
		int level = lift.getLevel();
		if (level > 0) {
			lift.setLevel(--level);
			lift.ustawPoziom();
		}
	}

	public void liftUp() {
		int level = lift.getLevel();
		if (level < MagazynUtils.liczbaPieter - 1) {
			lift.setLevel(++level);
			lift.ustawPoziom();
		}
	}

}
