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

public class MapaMagazynu extends JPanel {
	private static final long serialVersionUID = -8459627889558824665L;

	private Logger log = Logger.getLogger(MapaMagazynu.class);
	private ArrayList<RegalPanel> regaly;
	private LiftPanel lift;
	private JPanel odbior;

	public MapaMagazynu(int idPolaCounter) {
		regaly = new ArrayList<>();

		for (int i = 0; i < MagazynUtils.liczbaRegalow; i++) {
			regaly.add(new RegalPanel(MagazynUtils.defaultFreeBoxes, idPolaCounter));
		}

		initialize();

	}

	public MapaMagazynu(ArrayList<RegalPanel> regaly) {
		this.regaly = regaly;
		initialize();
	}

	public void initialize() {

		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setSize(MagazynUtils.mapWidth, MagazynUtils.mapHeight);
		setBackground(Color.WHITE);
		setLayout(null);
		log.info("Rozmiar mapy: " + MagazynUtils.mapWidth + "x" + MagazynUtils.mapHeight);

		for (int i = 0; i < MagazynUtils.liczbaRegalow; i++) {
			RegalPanel regal = regaly.get(i);
			regal.setBounds(MagazynUtils.regalX, MagazynUtils.getRegalYPosition(i), MagazynUtils.regalWidth, MagazynUtils.regalHeight);
			add(regal);
		}

		lift = new LiftPanel();
		lift.setBackground(MagazynUtils.liftBackground);
		lift.setBounds(lift.getX(), lift.getY(), lift.getXsize(), lift.getYsize());
		add(lift);

		odbior = new JPanel();
		odbior.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		odbior.setBackground(Color.YELLOW);
		JLabel label = new JLabel("Odbiór");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		odbior.add(label, BorderLayout.CENTER);

		odbior.setBounds(MagazynUtils.odbiorX, MagazynUtils.odbiorY, MagazynUtils.odbiorWidth, MagazynUtils.odbiorHeight);
		add(odbior);

	}

	public void zmienKolorBoksu(int numer, String position, Color c) {
			regaly.get(numer).zmienKolorBoksu(position, c);
			regaly.get(numer).zmienKolorBoksu(position, c);
			regaly.get(numer).zmienKolorBoksu(position, c);
	}

	public void moveLiftUp() {

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

	public void moveLiftDown() {

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

	public void moveLiftLeft() {

		int xPo = lift.getX();
		double yAktualne = lift.getY();
		boolean isEnteringShelf = false;
		boolean isEnteringReceivePoint = false;

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

	public void moveLiftRight() {
		log.info("MOVE LIFT RIGHT Przesuwam w prawo");
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

	public void przesunBoxWPrawo(String regal, int level, boolean czyDolny) {
		int regalIndex = Integer.parseInt(regal.substring(5))-1;
		regaly.get(regalIndex).moveBoxRight(level, czyDolny);

	}

	public void przesunBoxWLewo(String regal, int level, boolean czyDolny) {
		int regalIndex = Integer.parseInt(regal.substring(5))-1;
		regaly.get(regalIndex).moveBoxLeft(level, czyDolny);
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

	public void setRegalFreeBoxes(int nrRegalu, int freeBoxes) {
		regaly.get(nrRegalu).setFreeBoxes(freeBoxes, nrRegalu);
	}

	public int getRegalFreeBoxes(int nrRegalu) {
		return regaly.get(nrRegalu).getFreeBoxes();
	}

	public int getLiftX() {
		return lift.getX();
	}

	public int getLiftY() {
		return lift.getY();
	}

	public int getLiftLeve() {
		return lift.getLevel();
	}

	public LiftPanel getLift() {
		return lift;
	}

}
