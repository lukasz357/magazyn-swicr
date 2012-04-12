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

	public void zmienKolorBoksu(int numer, int level, String position, Color c) {
			regaly.get(numer).zmienKolorBoksu(level, position, c);
	}

	public void moveLiftUp() throws Exception {
		int liftMaxX = lift.getX() + lift.getXsize();
		int liftMinY = lift.getY();
		
		if(lift.getY() <= 0)
			throw new Exception("argh !  Panie, co Pan robi ? Poza mape chcesz wyjechac ?!");

		for (RegalPanel r : regaly) {
			if (((liftMaxX > r.getBounds().getMinX()) && (liftMaxX <= r.getBounds().getMaxX())) && (liftMinY == r.getBounds().getMaxY())) {
				throw new Exception("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
			}
		}
		double odbMaxX = odbior.getBounds().getMaxX();
		double odbMinX = odbior.getBounds().getMinX();
		double odbMaxY = odbior.getBounds().getMaxY();
		if (((liftMaxX > odbMinX) && (liftMaxX <= odbMaxX)) && (liftMinY == odbMaxY))
			throw new Exception("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");

		lift.moveUp();
		repaint();

	}

	public void moveLiftDown() throws Exception {
		int liftMaxX = lift.getX() + lift.getXsize();
		int liftMaxY = lift.getY() + lift.getYsize();
		
		double mapMaxY = getBounds().getMaxY();
		double mapMinY = getBounds().getMinY();
		if (liftMaxY >= mapMaxY - mapMinY )
			throw new Exception("argh !  Panie, co Pan robi ? Poza mape chcesz wyjechac ?!");

		for (RegalPanel r : regaly) {
			if (((liftMaxX > r.getBounds().getMinX()) && (liftMaxX <= r.getBounds().getMaxX() + 1)) && (liftMaxY == r.getBounds().getMinY())) {
				throw new Exception("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
			}
		}
		double odbMaxX = odbior.getBounds().getMaxX();
		double odbMinX = odbior.getBounds().getMinX();
		double odbMinY = odbior.getBounds().getMinY();
		if ((liftMaxX > odbMinX) && (liftMaxX <= odbMaxX+1) && liftMaxY == odbMinY)
			throw new Exception("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");
		
		lift.moveDown();
		repaint();
	}

	public void moveLiftLeft() throws Exception {
		int liftMinX = lift.getX();
		int liftMinY = lift.getY();		
		
		if (lift.getX() <= 0)
			throw new Exception("argh !  Panie, co Pan robi ? Poza mape chcesz wyjechac ?!");
		
		for (RegalPanel r : regaly) {
			if ((liftMinY >= (r.getBounds().getMinY() - MagazynUtils.liftStepY)) && (liftMinY < r.getBounds().getMaxY()) && (liftMinX == r.getBounds().getMaxX())) {
				throw new Exception("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
			}
		}
		
		double odbMaxX = odbior.getBounds().getMaxX();
		double odbMinY = odbior.getBounds().getMinY();
		double odbMaxY = odbior.getBounds().getMaxY();
		if ((liftMinY >= odbMinY - MagazynUtils.liftStepY) && (liftMinY < odbMaxY) && (liftMinX == odbMaxX))
			throw new Exception("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");


		lift.moveLeft();
		repaint();
	}

	public void moveLiftRight() throws Exception {	
		int liftMaxX = lift.getX() + lift.getXsize();
		int liftMinY = lift.getY();
		
		double mapMaxX = getBounds().getMaxX();
		double mapMinX = getBounds().getMinX();
		
		if (liftMaxX >= mapMaxX - mapMinX)
			throw new Exception("argh !  Panie, co Pan robi ? Poza mape chcesz wyjechac ?!");

		for (RegalPanel r : regaly) {
			if ((liftMinY >= (r.getBounds().getMinY() - MagazynUtils.liftStepY)) && (liftMinY < r.getBounds().getMaxY()) && (liftMaxX == r.getBounds().getMinX())) {
				throw new Exception("argh !  Panie, co Pan robi ? Na regal chce Pan wjechac ?!");
			}
		}
		
		double odbMinX = odbior.getBounds().getMinX();
		double odbMinY = odbior.getBounds().getMinY();
		double odbMaxY = odbior.getBounds().getMaxY();
		if ((liftMinY >= (odbMinY - MagazynUtils.liftStepY)) && (liftMinY < odbMaxY) && (liftMaxX == odbMinX))
			throw new Exception("argh !  Panie, co Pan robi ? Do odbioru chce Pan wjechac ?!");

		lift.moveRight(getBounds());
		repaint();
	}

	public void pokazPietro(int pietro) {
		for (RegalPanel r : regaly)
			r.pokazPietro(pietro);

	}

	public void przesunBoxWPrawo(int regal, int level, boolean czyDolny) {
			regaly.get(regal).moveBoxRight(level, czyDolny);

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
		return lift.getX()/18;
	}

	public int getLiftY() {
		return lift.getY()/18;
	}

	public int getLiftLeve() {
		return lift.getLevel();
	}

	public LiftPanel getLift() {
		return lift;
	}

}
