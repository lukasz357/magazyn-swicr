package siwc.magazyn.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
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

	private int idPola;

	public RegalPanel(int liczbaPustychBoksow, int idPolaCounter) {
		this.setLiczbaPustychBoksow(liczbaPustychBoksow);
		setLayout(new GridLayout(MagazynUtils.rzedowWRegale, MagazynUtils.kolumnWRegale, 0, 0));
		setBackground(Color.WHITE);
		this.idPola = idPolaCounter;
		dodajBoxy(pietro0);
		dodajBoxy(pietro1);
		dodajBoxy(pietro2);
		dodajBoxy(pietro3);
		dodajBoxy(pietro4);

		TreeMap<String, BoxPanel> level = getLevelMap();
		BoxPanel bp;
		BoxPanel mBox;
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				char c = (char) (65 + j);
				String position = c + Integer.toString(i + 1);
				mBox = level.get(position);
				bp = new BoxPanel(j, i, new PoleTO(i, j));
				bp.setBackground(mBox.getBackground());
				bp.setBorder(mBox.getBorder());
				bp.setToolTipText("Pusty");
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
		visibleBoxes.get(position).setToolTipText(levelMap.get(position).getToolTipText());
		visibleBoxes.get(position).revalidate();
	}
	public void zmienToolTipText(String position, String text) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap();
		if(levelMap.get(position).isFree())
			return;
		levelMap.get(position).setToolTipText(text);
		visibleBoxes.get(position).setToolTipText(levelMap.get(position).getToolTipText());
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
				visibleBoxes.get(k).setToolTipText(levelMap.get(k).getToolTipText());
				visibleBoxes.get(k).revalidate();
			}
		}
	}

	public void moveBoxRight(int level, boolean bottom) {
		TreeMap<String, BoxPanel> levelMap = getLevelMap(level);

		if (getLiczbaPustychBoksow() == 1) {
			
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

		} else if (getLiczbaPustychBoksow() == 2) {
			
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
		if (getLiczbaPustychBoksow() == 1) {
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
			
		} else if (getLiczbaPustychBoksow() == 2) {
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
	

	public void setFreeBoxes(int freeBoxes, int nrRegalu) {
		idPola = nrRegalu * MagazynUtils.liczbaBoxowWRegale;
		
		setLiczbaPustychBoksow(freeBoxes);
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
		return getLiczbaPustychBoksow();
	}

	private TreeMap<String, BoxPanel> getLevelMap() {
		return getLevelMap(pietro);
	}

	public TreeMap<String, BoxPanel> getLevelMap(int pietro) {
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

	public ArrayList<BoxPanel> getLevelMapAsArrayList(int pietro){
		ArrayList<BoxPanel> list = new ArrayList<>();
		TreeMap<String, BoxPanel> map = getLevelMap(pietro);
		
		for(String k: map.keySet()) {
			System.out.println(k);
			list.add(map.get(k));
		}
		
		return list;
		
	}
	private void dodajBoxy(TreeMap<String, BoxPanel> level) {
		BoxPanel bp;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				char c = (char) (65 + j);
				PoleTO p = new PoleTO();
				p.setId(idPola++);
				p.setBox(true);
//				System.out.println(idPola);
				if (liczbaPustychBoksow == 1 && j == 0 && i == 0) { // gorny pusty box
					p.setMovable(true);
					bp = new BoxPanel(i, j, p);
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					bp.setToolTipText("Pusty");
					String position = c + Integer.toString(i + 1);
					level.put(position, bp);
				} else if (liczbaPustychBoksow == 2 && (j == 0 || j == rows-1) && i == cols / 2) { // gorny lub dolny pusty box
					p.setMovable(true);
					bp = new BoxPanel(i, j, p);
					bp.setFree(true);
					bp.setBackground(MagazynUtils.freeBoxBackround);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					bp.setToolTipText("Pusty");
					String position = c + Integer.toString(i + 1);
					level.put(position, bp);
				} else if (liczbaPustychBoksow == 2 && (j == 0 || j == rows)) { // gorny lub dolny poruszajacy sie box
					p.setMovable(true);
					bp = new BoxPanel(i, j, p);
					bp.setBackground(MagazynUtils.defaultBoxBackground);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					bp.setToolTipText("Pusty");
					String position = c + Integer.toString(i + 1);
					level.put(position, bp);
				} else if (liczbaPustychBoksow == 1 && (j == 0 || j == rows-1 || i == 0 || i == cols)) {
					p.setMovable(true);
					bp = new BoxPanel(i, j, p);
					bp.setBackground(MagazynUtils.defaultBoxBackground);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					bp.setToolTipText("Pusty");
					String position = c + Integer.toString(i + 1);
					level.put(position, bp);
				} else {
					bp = new BoxPanel(i, j, p);
					bp.setBackground(MagazynUtils.defaultBoxBackground);
					bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
					bp.setToolTipText("Pusty");
					String position = c + Integer.toString(i + 1);
					level.put(position, bp);
				}
				
			}
		}

	}

	public String getFreeBoxKey(TreeMap<String, BoxPanel> levelMap) {
		for(String k : levelMap.keySet()) {
			if(levelMap.get(k).isFree())
				return k;
		}
		return null;
			
	}

	public String getFreeBoxKey(TreeMap<String, BoxPanel> levelMap, boolean bottom) {
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

	public TowarTO getTowar(int level, String position){
		return getLevelMap(level).get(position).getPole().getTowar();
	}
	
	public void zmienKolorBoksu(int level, String position, Color color) {
		if(this.pietro == level) 
			zmienKolorBoksu(position, color);
		else
			getLevelMap(level).get(position).setBackground(color);
	}
	
	public void zmienToolTipTextBoxu(int level, String position, String text) {
		if(this.pietro == level) 
			zmienToolTipText(position, text);
		else
			getLevelMap(level).get(position).setToolTipText(text);
	}
	
	public Color getBoxColor(int level, String position){
		return getLevelMap(level).get(position).getBackground();
	}

	public int getLiczbaPustychBoksow() {
		return liczbaPustychBoksow;
	}

	public void setLiczbaPustychBoksow(int liczbaPustychBoksow) {
		this.liczbaPustychBoksow = liczbaPustychBoksow;
	}

	public String getFreeBoxKey(int level) {
		return getFreeBoxKey(getLevelMap(level));
	}
	
	public String getFreeBoxKey(int level, boolean bottom) {
		return getFreeBoxKey(getLevelMap(level), bottom);
	}
	
}
