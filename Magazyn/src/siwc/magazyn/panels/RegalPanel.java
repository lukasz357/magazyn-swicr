package siwc.magazyn.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.TreeMap;

import javax.swing.Box;
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
	private int liczbaPustychBoksow;
	
	private TreeMap<String, BoxPanel> visibleBoxes = new TreeMap<>();

	private TreeMap<String, BoxPanel> pietro0 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro1 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro2 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro3 = new TreeMap<>();
	private TreeMap<String, BoxPanel> pietro4 = new TreeMap<>();


	public RegalPanel(int liczbaPustychBoksow) {
		this.liczbaPustychBoksow = liczbaPustychBoksow;
		setLayout(new GridLayout(MagazynUtils.rzedowWRegale, MagazynUtils.kolumnWRegale, 0, 0));
		setBackground(Color.WHITE);

		dodajBoxy(pietro0);
		dodajBoxy(pietro1);
		dodajBoxy(pietro2);
		dodajBoxy(pietro3);
		dodajBoxy(pietro4);
		

		TreeMap<String, BoxPanel> level = getLevelMap();
		BoxPanel bp;
		BoxPanel mBox;

		for (int i = 0; i < rows; i++) {
			char c = (char) (65 + i);
			for (int j = 0; j < cols; j++) {
				String position = c + Integer.toString(j + 1);
				mBox = level.get(position);
				bp = new BoxPanel(j, i, new BoxTO());
				bp.setBackground(mBox.getBackground());
				bp.setBorder(mBox.getBorder());

				
				visibleBoxes.put(position, bp);
				add(bp);
			}
		}

	}

	public void zmienKolorBoksu(String position, Color c) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap();

		levelMap.get(position).setBackground(c);

		visibleBoxes.get(position).setBackground(levelMap.get(position).getBackground());
		visibleBoxes.get(position).revalidate();
	}

	public void pokazPietro(int level) {
		this.pietro = level;
		TreeMap<String, BoxPanel> levelMap = getLevelMap();

		if (levelMap != null) {
			for (String k : visibleBoxes.keySet()) {
				visibleBoxes.get(k).setBackground(levelMap.get(k).getBackground());
				visibleBoxes.get(k).revalidate();
			}
		}
	}

	public void moveBoxRight(int level) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap(level);
		
		if (levelMap != null) {
			BoxPanel actual = null;
			BoxPanel right = null;

			// A
			actual = levelMap.get("A1");
			for (int i = 1; i < cols; i++) {
				String rightKey = "A" + Integer.toString(i + 1);

				right = levelMap.get(rightKey);
				levelMap.put(rightKey, actual);

				actual = right;

			}
			// ostatnia kolumna
			for (int i = 0; i < rows - 1; i++) {
				String rightKey = (char) (65 + i + 1) + Integer.toString(cols);

				right = levelMap.get(rightKey);
				levelMap.put(rightKey, actual);

				actual = right;
			}
			// D
			for (int i = cols; i > 1; i--) {
				String rightKey = "D" + Integer.toString(i - 1);

				right = levelMap.get(rightKey);
				levelMap.put(rightKey, actual);

				actual = right;
			}
			// pierwsza kolumna
			for (int i = rows - 2; i >= 0; i--) {
				String rightKey = (char) (65 + i) + Integer.toString(1);

				right = levelMap.get(rightKey);
				levelMap.put(rightKey, actual);

				actual = right;
			}
		}
		if(pietro == level)
			pokazPietro(level);
	}

	public void moveBoxLeft(int level) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap(level);
//		if(liczbaPustychBoksow == 1) { 
//			
//		} else if(liczbaPustychBoksow == 2) {
//			
//		}
		if (levelMap != null) {
			BoxPanel actual = null;
			BoxPanel left = null;

			// pierwsza kolumna
			actual = levelMap.get("A1");
			for (int i = 1; i < rows; i++) {
				String leftKey = (char) (65 + i) + Integer.toString(1);

				left = levelMap.get(leftKey);
				levelMap.put(leftKey, actual);

				actual = left;
			}

			// D
			for (int i = 1; i < cols; i++) {
				String leftKey = "D" + Integer.toString(i + 1);

				left = levelMap.get(leftKey);
				levelMap.put(leftKey, actual);

				actual = left;
			}

			// ostatnia kolumna
			for (int i = rows - 2; i >= 0; i--) {
				String leftKey = (char) (65 + i) + Integer.toString(cols);

				left = levelMap.get(leftKey);
				levelMap.put(leftKey, actual);

				actual = left;
			}
			// A
			for (int i = cols; i > 1; i--) {
				String leftKey = "A" + Integer.toString(i - 1);

				left = levelMap.get(leftKey);
				levelMap.put(leftKey, actual);

				actual = left;
			}

		}
		if(pietro == level)
			pokazPietro(level);
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
	
	private TreeMap<String, BoxPanel> getLevelMap(int pietro) {
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


	private void dodajBoxy(TreeMap<String, BoxPanel> level) {
		BoxPanel bp;
		for (int i = 0; i < rows; i++) {
			char c = (char) (65 + i);
			for (int j = 0; j < cols; j++) {
				if(liczbaPustychBoksow == 1 && j == 0 && i == 0) {
					bp = new BoxPanel(j, i, new BoxTO());
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				} else if(liczbaPustychBoksow == 2 && (i == 0 || i == (rows-1)) && j == cols/2) {
					bp = new BoxPanel(j, i, new BoxTO());
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				} else {
					bp = new BoxPanel(j, i, new BoxTO());
					bp.setBackground(MagazynUtils.defaultBoxBackground);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				}
			}
		}

	}

}
