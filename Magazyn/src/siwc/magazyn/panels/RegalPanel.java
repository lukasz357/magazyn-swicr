package siwc.magazyn.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.TreeMap;

import javax.crypto.spec.PSource;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.BoxTO;
import siwc.magazyn.utils.MagazynUtils;

public class RegalPanel extends JPanel {
	private static final long serialVersionUID = 3160205159810161295L;

	private Logger log = Logger.getLogger(RegalPanel.class);

	private int rows = MagazynUtils.rzedowWRegale;
	private int cols = MagazynUtils.kolumnWRegale;
	private int pietro = 0;

	private TreeMap<String, BoxPanel> visibleBoxes = new TreeMap<>();

	private TreeMap<String, BoxPanel> pietro0 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro1 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro2 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro3 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro4 = new TreeMap<>();

	public RegalPanel(boolean litery, boolean numery) {
		if (numery && litery) { // czy sa wyswietlane numery kolumn - sadze by to wpisywac w pierwszym tylko rzedzie ;P
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale + 1, MagazynUtils.kolumnWRegale + 1, 0, 0));
			add(new JLabel());
			for (int i = 0; i < cols; i++)
				add(new JLabel(Integer.toString(i + 1)));

		} else if(numery) {
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale + 1, MagazynUtils.kolumnWRegale, 0, 0));
			for (int i = 0; i < cols; i++)
				add(new JLabel(Integer.toString(i + 1)));
		} else if(litery) {
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale , MagazynUtils.kolumnWRegale+ 1, 0, 0));
		} else {
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale, MagazynUtils.kolumnWRegale, 0, 0));
		}

		setBackground(Color.WHITE);

		BoxPanel bp;

		for (int i = 0; i < rows; i++) {
			char c = (char) (65 + i);
			if(litery)
				add(new JLabel(c + ""));
			
			for (int j = 0; j < cols; j++) {
				bp = new BoxPanel(j, i, new BoxTO());
				bp.setBackground(MagazynUtils.defaultBackground);
				bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));

				String position = c + Integer.toString(j + 1);
				visibleBoxes.put(position, bp);
				add(bp);
			}
		}

		dodajBoxy(pietro0);
		dodajBoxy(pietro1);
		dodajBoxy(pietro2);
		dodajBoxy(pietro3);
		dodajBoxy(pietro4);

	}

	private void dodajBoxy(TreeMap<String, BoxPanel> level) {
		BoxPanel bp;
		for (int i = 0; i < rows; i++) {
			char c = (char) (65 + i);
			for (int j = 0; j < cols; j++) {
				bp = new BoxPanel(j, i, new BoxTO());
				bp.setBackground(MagazynUtils.defaultBackground);
				bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
				String position = c + Integer.toString(j + 1);
				level.put(position, bp);
			}
		}

	}

	public void zmienKolorBoksu(String position, Color c) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap();
		
		levelMap.get(position).setBackground(c);
		
//		TODO  czy dodawac labele?
//		JLabel nr = new JLabel(position);
//		nr.setForeground(new Color(255, 200, 0));
//		levelMap.get(position).add(nr, BorderLayout.CENTER);
//		visibleBoxes.get(position).removeAll();
//		for(Component comp : levelMap.get(position).getComponents())
//			visibleBoxes.get(position).add(comp);
		
		visibleBoxes.get(position).setBackground(levelMap.get(position).getBackground());
		visibleBoxes.get(position).revalidate();
	}

	public void ustawPietro(int level) {
		this.pietro = level;
		TreeMap<String, BoxPanel> levelMap = getLevelMap();

		if (levelMap != null) {
			for (String k : visibleBoxes.keySet()) {
//				TODO  czy dodawac labele?
//				visibleBoxes.get(k).removeAll();
//				
//				for(Component comp : levelMap.get(k).getComponents()) {
//					visibleBoxes.get(k).add(comp);
//
//				}
				visibleBoxes.get(k).setBackground(levelMap.get(k).getBackground());
				visibleBoxes.get(k).revalidate();
			}

			
			log.info("Ustawilem pietro: " + this.pietro);

		} else {
			log.info("Nie udalo sie ustawic pietra: " + level);
		}
	}

	private TreeMap<String, BoxPanel> getLevelMap() {
		switch (pietro) {
		case 0:
			return pietro0;
		case 1:
			return pietro1;
		case 2:
			return pietro2;
		case 3:
			return pietro3;
		case 4:
			return pietro4;
		}
		return null;
	}
}
