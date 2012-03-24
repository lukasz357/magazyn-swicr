package siwc.magazyn.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.utils.MagazynUtils;

public class RegalPanel extends JPanel {
	private static final long serialVersionUID = 3160205159810161295L;

	@SuppressWarnings("unused")
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
				bp = new BoxPanel(j, i, new PoleTO());
				bp.setBackground(mBox.getBackground());
				bp.setBorder(mBox.getBorder());

				visibleBoxes.put(position, bp);
				add(bp);
			}
		}

	}

	public void zmienKolorBoksu(String position, Color c) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap();
		if(levelMap.get(position).isFree())
			return;
		levelMap.get(position).setBackground(c);

		visibleBoxes.get(position).setBackground(levelMap.get(position).getBackground());
		visibleBoxes.get(position).revalidate();
	}

	public void pokazPietro(int level) {
		this.pietro = level;
		TreeMap<String, BoxPanel> levelMap = getLevelMap();

		if (levelMap != null) {
			for (String k : visibleBoxes.keySet()) {
				if(visibleBoxes.get(k) == null || levelMap.get(k) == null) 
					System.out.println("null " + k + " " + (visibleBoxes.get(k) == null) + " " + (levelMap.get(k) == null) );
				visibleBoxes.get(k).setBackground(levelMap.get(k).getBackground());
				visibleBoxes.get(k).revalidate();
			}
		}
	}

	public void moveBoxRight(int level, boolean bottom) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap(level);

		if (liczbaPustychBoksow == 1) {
			
			String freeBoxKey = getFreeBoxKey(levelMap);
			if(freeBoxKey == null) 
				return;
			String rightBoxKey = getRightBoxKey(freeBoxKey);
			if(rightBoxKey == null )
				return;
			
			BoxPanel freeBox = levelMap.get(freeBoxKey);
			BoxPanel rightBox = levelMap.get(rightBoxKey);
			
			levelMap.put(freeBoxKey, rightBox);
			levelMap.put(rightBoxKey, freeBox);

		} else if (liczbaPustychBoksow == 2) {
			
			String freeBoxKey = getFreeBoxKey(levelMap, bottom);
			if(freeBoxKey == null) 
				return;
			String rightBoxKey = getRightBoxKey(freeBoxKey, bottom);
			if(rightBoxKey == null )
				return;
			
			BoxPanel freeBox = levelMap.get(freeBoxKey);
			BoxPanel rightBox = levelMap.get(rightBoxKey);
			levelMap.put(freeBoxKey, rightBox);
			levelMap.put(rightBoxKey, freeBox);
			
		} else 
			return;
		
		if (pietro == level)
			pokazPietro(level);
	}



	public void moveBoxLeft(int level, boolean bottom) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap(level);
		if (liczbaPustychBoksow == 1) {
			String freeBoxKey = getFreeBoxKey(levelMap);
			if(freeBoxKey == null) 
				return;
			String rightBoxKey = getLeftBoxKey(freeBoxKey);
			if(rightBoxKey == null) 
				return;
			
			BoxPanel freeBox = levelMap.get(freeBoxKey);
			BoxPanel rightBox = levelMap.get(rightBoxKey);
			levelMap.put(freeBoxKey, rightBox);
			levelMap.put(rightBoxKey, freeBox);
			
		} else if (liczbaPustychBoksow == 2) {
			String freeBoxKey = getFreeBoxKey(levelMap, bottom);
			if(freeBoxKey == null) 
				return;
			String rightBoxKey = getLeftBoxKey(freeBoxKey, bottom);
			if(rightBoxKey == null) 
				return;
			
			BoxPanel freeBox = levelMap.get(freeBoxKey);
			BoxPanel rightBox = levelMap.get(rightBoxKey);
			levelMap.put(freeBoxKey, rightBox);
			levelMap.put(rightBoxKey, freeBox);
			
		} else 
			return;
		
		if (pietro == level)
			pokazPietro(level);
	}
	

	public void setFreeBoxes(int freeBoxes) {
		liczbaPustychBoksow = freeBoxes;
		pietro0.clear();
		pietro1.clear();
		pietro2.clear();
		pietro3.clear();
		pietro4.clear();

		dodajBoxy(pietro0);
		dodajBoxy(pietro1);
		dodajBoxy(pietro2);
		dodajBoxy(pietro3);
		dodajBoxy(pietro4);

		pokazPietro(pietro);
	}
	
	public int getFreeBoxes() {
		return liczbaPustychBoksow;
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
				if (liczbaPustychBoksow == 1 && j == 0 && i == 0) {
					bp = new BoxPanel(j, i, new PoleTO());
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				} else if (liczbaPustychBoksow == 2 && (i == 0 || i == (rows - 1)) && j == cols / 2) {
					bp = new BoxPanel(j, i, new PoleTO());
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				} else {
					bp = new BoxPanel(j, i, new PoleTO());
					bp.setBackground(MagazynUtils.defaultBoxBackground);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					String position = c + Integer.toString(j + 1);
					level.put(position, bp);
				}
			}
		}

	}

	private String getFreeBoxKey(TreeMap<String, BoxPanel> levelMap) {
		for(String k : levelMap.keySet()) {
			if(levelMap.get(k).isFree())
				return k;
		}
		return null;
			
	}

	private String getFreeBoxKey(TreeMap<String, BoxPanel> levelMap, boolean bottom) {
		if (bottom == false) {
			for (int i = 1; i <= MagazynUtils.kolumnWRegale; i++) {
				if (levelMap.get("A" + i).isFree())
					return "A" + i;
			}
		} else if (bottom == true) {
			for (int i = 1; i <= MagazynUtils.kolumnWRegale; i++) {
				String letter = Character.toString((char) (65 + MagazynUtils.rzedowWRegale - 1));
				if (levelMap.get(letter + i).isFree())
					return letter + i;
			}
		}
		return null;
	}


	private String getRightBoxKey(String freeKey) {
		String letter = Character.toString(freeKey.charAt(0));
		int number = Integer.parseInt(freeKey.substring(1));

		if (number == MagazynUtils.kolumnWRegale && "ABC".contains(letter)) {

			letter = Character.toString((char) (freeKey.charAt(0) + 1));
			return letter + number;

		} else if (number == 1 && "BCD".contains(letter)) {
			
			letter = Character.toString((char) (freeKey.charAt(0) - 1));
			return letter + number;
		
		} else if (letter.equals("D")) {
			return letter + (number - 1);
		
		} else if (letter.equals("A")) {
			return letter + (number+1);
		}
		
		return null;
	}
	
	
	private String getRightBoxKey(String freeKey, boolean bottom) {
		String letter = Character.toString(freeKey.charAt(0));
		int number = Integer.parseInt(freeKey.substring(1));
		
		if(bottom == false && letter.equals("A") && number < MagazynUtils.kolumnWRegale) {
			return letter + (number + 1);
		} else if(bottom == true && letter.equals("D") && number < MagazynUtils.kolumnWRegale) {
			return letter + (number + 1);
		} 
		
		return null;
	}
	
	private String getLeftBoxKey(String freeKey) {
		String letter = Character.toString(freeKey.charAt(0));
		int number = Integer.parseInt(freeKey.substring(1));

		if (number == 1 && "ABC".contains(letter)) {

			letter = Character.toString((char) (freeKey.charAt(0) + 1));
			return letter + number;

		} else if (number == MagazynUtils.kolumnWRegale && "BCD".contains(letter)) {
			
			letter = Character.toString((char) (freeKey.charAt(0)  - 1));
			return letter + number;
		
		} else if (letter.equals("D")) {
			return letter + (number + 1);
		
		} else if (letter.equals("A")) {
			return letter + (number-1);
		}
		
		return null;
	}
	
	
	private String getLeftBoxKey(String freeKey, boolean bottom) {
		String letter = Character.toString(freeKey.charAt(0));
		int number = Integer.parseInt(freeKey.substring(1));
		
		if(bottom == false && letter.equals("A") && number > 1) {
			return letter + (number - 1);
		} else if(bottom == true && letter.equals("D") && number > 1) {
			return letter + (number - 1);
		} 
		
		return null;
	}


	
//	public static void main(String ... args) {
//		RegalPanel r = new RegalPanel(2);
//		r.dodajBoxy(r.pietro0);
////		r.moveBoxRight(0,false);
//		
//		System.out.println();
//		System.out.println(r.getLeftBoxKey("A15"));
//		System.out.println(r.getLeftBoxKey("A30"));
//		System.out.println(r.getLeftBoxKey("B30"));
//		System.out.println(r.getLeftBoxKey("D30"));
//		System.out.println(r.getLeftBoxKey("D15"));
//		System.out.println(r.getLeftBoxKey("D0"));
//		System.out.println(r.getLeftBoxKey("C0"));
//		System.out.println(r.getLeftBoxKey("B0"));
//		System.out.println(r.getLeftBoxKey("A0"));
//		
//		System.out.println();
//		System.out.println(r.getLeftBoxKey("A15", true));
//		System.out.println(r.getLeftBoxKey("A30", false));
//		System.out.println(r.getLeftBoxKey("B30", false));
//		System.out.println(r.getLeftBoxKey("D30", true));
//		System.out.println(r.getLeftBoxKey("D15",false));
//		System.out.println(r.getLeftBoxKey("D5", true));
//
//		System.out.println(r.getLeftBoxKey("A0",false));
//		System.out.println(r.getLeftBoxKey("D1",true));
//		System.out.println(r.getLeftBoxKey("A1",false));
//	}
}
