package siwc.magazyn.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.panels.BoxPanel;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.utils.MagazynUtils;

public class IOLogic {
	private Logger log = Logger.getLogger(IOLogic.class);
	@Deprecated 
	public MagazynTO getMagazynFromFile() { // bo konwertowane jest z mapy marcina ;d
		MagazynTO magazyn = new MagazynTO();
		magazyn.setWielkoscXMagazynu(4);
		magazyn.setWielkoscYMagazynu(4);
		
		PoleTO[][] pietro1 = new PoleTO[magazyn.getWielkoscXMagazynu()][magazyn.getWielkoscYMagazynu()];
		
		PoleTO emptyField = new PoleTO();
		emptyField.setId(-1); //empty field so id = -1 ?
		emptyField.setBox(false);
		emptyField.setPunktOdbioru(false);
		emptyField.setTowar(null);
		emptyField.setX(-1);
		emptyField.setY(-1);
		
		for (int i=0; i < magazyn.getWielkoscXMagazynu(); i++) {
			for (int j=0; j < magazyn.getWielkoscYMagazynu(); j++) {
				pietro1[i][j] = emptyField;
			}
		}
		
		TowarTO skarpety = new TowarTO();
		skarpety.setIdBoxu(1);
		skarpety.setIlosc("200szt.");
		skarpety.setKodTowaru("SKRPT001");
		skarpety.setNazwa("Skarpety Elżbiety");
		skarpety.setProducent("Pamiętniki z wakacji");
		
		TowarTO adidasy = new TowarTO();
		adidasy.setIdBoxu(2);
		adidasy.setIlosc("20par");
		adidasy.setKodTowaru("ADDS001");
		adidasy.setNazwa("Adidasa skarpetasa");
		adidasy.setProducent("Gienek i spółka");
		
		PoleTO box = new PoleTO();
		box.setId(1);
		box.setBox(true);
		box.setPunktOdbioru(false);
		box.setTowar(skarpety);
		box.setX(10);
		box.setY(4);
		
		
		PoleTO box2 = new PoleTO();
		box2.setId(2);
		box2.setBox(true);
		box2.setPunktOdbioru(false);
		box2.setTowar(adidasy);
		box2.setX(15);
		box2.setY(4);
		
		PoleTO box3 = new PoleTO();
		box3.setId(3);
		box3.setBox(false);
		box3.setPunktOdbioru(true);
		box3.setTowar(null);
		box3.setX(1);
		box3.setY(1);
		
		pietro1[4][4] = box;
		pietro1[3][4] = box2;
		pietro1[1][1] = box3;
		
		magazyn.getPietra().put(0, pietro1);
		
		return new MagazynTO();
		
	}
	
	public ArrayList<RegalPanel> readFileToRegalPanelArray(File file, ArrayList<RegalPanel> regaly) {
		
		FileReader fr = null;
		BufferedReader in = null;
		try {
			fr = new FileReader(file);
			in = new BufferedReader(fr);

			String line;
			while ((line = in.readLine()) != null) {
				if (line.startsWith("#") || line.equals("")) // komentarz - pusta linia
					continue;

				String[] tLine = null;
				if (line.contains(";"))
					tLine = line.split(";");
				else if (line.contains(","))
					tLine = line.split(",");
				
				if (tLine == null || tLine.length < MagazynUtils.liczbaKolumnPlikuZamowien - 1) {
					log.error("Problem przy wczytywaniu przedmiotów z pliku!");
					in.close();
					fr.close();
					return null;
				}
				
				try{
					int regalID = Integer.parseInt(tLine[0].trim())-1;
					int pietro = Integer.parseInt(tLine[1].trim());
					String pozycja = tLine[2].trim().toUpperCase();
					String nazwa = tLine[3].trim();
					String producent = tLine[4].trim();
					String kodTowaru = tLine[5].trim();
					
					if(regalID >= MagazynUtils.liczbaRegalow || pietro >= MagazynUtils.liczbaPieter) 
						continue;
					if( MagazynUtils.convertToRow(pozycja) >= MagazynUtils.rzedowWRegale || MagazynUtils.convertToColumn(pozycja) >= MagazynUtils.kolumnWRegale )
						continue;
					
					RegalPanel rp = regaly.get(regalID);
					if(rp.isMovable(pietro, pozycja))
						continue;
					
					rp.zmienKolorBoksu(pietro, pozycja, MagazynUtils.busyBoxBackground);
					
					TowarTO towar = rp.getTowar(pietro, pozycja);
					if (towar != null) {
						towar.setNazwa(nazwa);
						towar.setProducent(producent);
						towar.setKodTowaru(kodTowaru);
					}

				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			
			in.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return regaly;
	}

	public MagazynTO convertToMagazynTO(ArrayList<RegalPanel> regaly) {
		MagazynTO magazyn = new MagazynTO();
		magazyn.setWielkoscXMagazynu(MagazynUtils.mapWidth / MagazynUtils.boxSize);
		magazyn.setWielkoscYMagazynu(MagazynUtils.mapHeight / MagazynUtils.boxSize);
		
		TreeMap<Integer, PoleTO[][]> pietra = new TreeMap<>();

		for (int i = 0; i < MagazynUtils.liczbaPieter; i++) {
			PoleTO[][] pietro = new PoleTO[magazyn.getWielkoscYMagazynu()][magazyn.getWielkoscXMagazynu()];

			for(int j=0; j<MagazynUtils.liczbaRegalow; j++) {
				RegalPanel regal = regaly.get(j);
				int xRegalu = MagazynUtils.regalX/MagazynUtils.boxSize;
				int yRegalu = MagazynUtils.getRegalYPosition(j)/MagazynUtils.boxSize;

				TreeMap<String, BoxPanel> levelMap = regal.getLevelMap(i);

				for(String k: levelMap.keySet()) {
					
					int x = xRegalu + MagazynUtils.convertToColumn(k);
					int y = yRegalu + MagazynUtils.convertToRow(k);
					
					pietro[y][x] = levelMap.get(k).getPole();
					pietro[y][x].setBox(true);
					
				}
			}
			//punkt odbioru

			int odbiorX = MagazynUtils.odbiorX/MagazynUtils.boxSize;
			int odbiorY = MagazynUtils.odbiorY/MagazynUtils.boxSize;
			int odbiorSizeX =odbiorX + MagazynUtils.odbiorWidth/MagazynUtils.boxSize;
			int odbiorSizeY = odbiorY + MagazynUtils.odbiorHeight/MagazynUtils.boxSize;
			
			for(int y = odbiorY; y<odbiorSizeY; y++) {
				for(int x = odbiorX; x<odbiorSizeX; x++) {
					PoleTO p = new PoleTO(x, y);
					p.setPunktOdbioru(true);
	
					pietro[y][x] = p;
				}
			}
			pietra.put(i, pietro);
		}
		magazyn.setPietra(pietra);
		return magazyn;
	}

	public String saveMagazynToFile() {
		return "Wszystko ok";
	}
}
