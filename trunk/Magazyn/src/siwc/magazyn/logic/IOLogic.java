package siwc.magazyn.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.ListTowarTO;
import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.dto.ZamowienieTO;
import siwc.magazyn.panels.BoxPanel;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.utils.MagazynUtils;

public class IOLogic {
	private Logger log = Logger.getLogger(IOLogic.class);

	public String readFileToRegalPanelArray(File file, ArrayList<RegalPanel> regaly, HashMap<String, ListTowarTO> towaryNaMagazynie) {

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

				if (tLine == null || tLine.length < MagazynUtils.liczbaKolumnPlikuProduktow - 1) {
					log.error("Problem przy wczytywaniu przedmiotów z pliku!");
					in.close();
					fr.close();
					return null;
				}

				try {
					int regalID = Integer.parseInt(tLine[0].trim()) - 1;
					int pietro = Integer.parseInt(tLine[1].trim());
					String pozycja = tLine[2].trim().toUpperCase();

					String nazwa = tLine[3].trim();
					String producent = tLine[4].trim();
					String kodTowaru = tLine[5].trim();

					if (regalID >= MagazynUtils.liczbaRegalow || pietro >= MagazynUtils.liczbaPieter)
						continue;
					if (MagazynUtils.convertToRow(pozycja) >= MagazynUtils.rzedowWRegale || MagazynUtils.convertToColumn(pozycja) > MagazynUtils.kolumnWRegale)
						continue;
					RegalPanel rp = regaly.get(regalID);

					if (rp.getBoxColor(pietro, pozycja).equals(MagazynUtils.freeBoxBackround))
						continue;

					rp.zmienKolorBoksu(pietro, pozycja, MagazynUtils.busyBoxBackground);
//					rp.getLevelMap(pietro).get(pozycja).setFree(false);
					
					TowarTO towar = rp.getTowar(pietro, pozycja);
					if (towar != null) {
						towar.setNazwa(nazwa);
						towar.setProducent(producent);
						towar.setKodTowaru(kodTowaru);
						towar.setZarezerwowany(false);
					}
					

					rp.zmienToolTipTextBoxu(pietro, pozycja, "<html>" + pozycja + "<br>" + towar.getOpis());
					if (towaryNaMagazynie.containsKey(kodTowaru)) {
						int staraLiczbaPaczek = towaryNaMagazynie.get(kodTowaru).getIlePaczek();
						towaryNaMagazynie.get(kodTowaru).setIlePaczek(staraLiczbaPaczek + 1);
					} else {
						towaryNaMagazynie.put(kodTowaru, new ListTowarTO(towar, 1));
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}

			in.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


		return "OK";
	}

	public void readOrdersFromFile(File file, HashMap<Integer, ZamowienieTO> zamowienia, ArrayList<RegalPanel> regaly, MagazynTO magazyn, HashMap<String, ListTowarTO> towaryNaMagazynie) {

		FileReader fr = null;
		BufferedReader in = null;
		int index = 1;
		zamowienia.clear();
		try {
			fr = new FileReader(file);
			in = new BufferedReader(fr);

			String line, itemLine;
			while ((line = in.readLine()) != null) {
				String[] tLine = null;
				if (line.startsWith("#") || line.equals("")) // komentarz - pusta linia
					continue;
				if (line.contains(";"))
					tLine = line.split(";");
				else if (line.contains(","))
					tLine = line.split(",");
				String daneKlienta = tLine[0].trim();
				int priorytet = -1;
				int h = -1;
				int m = -1;
				int s = -1;
				String terminRealizacji = tLine[1].trim();
				try {
					String[] czas = terminRealizacji.split(":");
					if (czas.length > 3) {
						log.error("Coś nie tak przy parsowaniu terminu realizacji: " + terminRealizacji);
					}
					h = Integer.parseInt(czas[0]);
					m = Integer.parseInt(czas[1]);
					s = Integer.parseInt(czas[2]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				priorytet = h * 86400 + m * 3600 + s;
				ZamowienieTO zamowienie = new ZamowienieTO();
				zamowienie.setDaneKlienta(daneKlienta);
				zamowienie.setTerminRealizacji(terminRealizacji);
				zamowienie.setPriorytet(priorytet);
				zamowienie.setNumerZamowienia(index);
				while (!(itemLine = in.readLine()).equals("$") && itemLine != null) {
					tLine = null;
					if (itemLine.startsWith("#") || itemLine.equals("")) // komentarz - pusta linia
						continue;
					if (itemLine.contains(";"))
						tLine = itemLine.split(";");
					else if (itemLine.contains(","))
						tLine = itemLine.split(",");

					if (tLine == null) {
						log.error("Problem przy wczytywaniu zamówień z pliku!");
						in.close();
						fr.close();
						return;
					}
					try {
						String kodTowaru = tLine[0].trim();
						String nrRegalu = tLine[1].trim();
						String nrPietra = tLine[2].trim();
						String pozycja = tLine[3].trim();
						int nrRg = Integer.parseInt(nrRegalu);
						int nrPtr = Integer.parseInt(nrPietra);
						TowarTO towar = regaly.get(nrRg - 1).getTowar(nrPtr, pozycja);
						if(regaly.get(nrRg - 1).getBoxColor(nrPtr, pozycja).equals(MagazynUtils.freeBoxBackround)) {
							log.error("Tam na pewno nie ma towaru - wolny box: Regał: "+nrRg +" Pietro: "+nrPtr +" Pozycja: "+pozycja);
							continue;
						}else if(towar == null || towar.getKodTowaru() == null) {
							log.error("Nullowy towar: Regał: "+nrRg +" Pietro: "+nrPtr +" Pozycja: "+pozycja);
							continue;
						}else if(towar.isZarezerwowany()){
							log.error("Towar na pozycji: Regał: "+nrRg +" Pietro: "+nrPtr +" Pozycja: "+pozycja + " był już zarezerwowany. Pomijam..");
							continue;
						}else{
							towar.setZarezerwowany(true);
							towaryNaMagazynie.get(kodTowaru).zmniejszIlosc();
							zamowienie.getTowary().add(towar);
							regaly.get(nrRg - 1).zmienKolorBoksu(nrPtr, pozycja, MagazynUtils.reservedBoxBackground);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				zamowienia.put(index, zamowienie);
				index++;
			}

			in.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MagazynTO convertToMagazynTO(ArrayList<RegalPanel> regaly) {
		MagazynTO magazyn = new MagazynTO();
		magazyn.setWielkoscXMagazynu(MagazynUtils.mapWidth / MagazynUtils.boxSize);
		magazyn.setWielkoscYMagazynu(MagazynUtils.mapHeight / MagazynUtils.boxSize);

		TreeMap<Integer, PoleTO[][]> pietra = new TreeMap<>();

		for (int i = 0; i < MagazynUtils.liczbaPieter; i++) {
			PoleTO[][] pietro = new PoleTO[magazyn.getWielkoscXMagazynu()][magazyn.getWielkoscYMagazynu()];

			for (int x = 0; x < magazyn.getWielkoscXMagazynu(); x++) {
				for (int y = 0; y < magazyn.getWielkoscYMagazynu(); y++) {
					pietro[x][y] = new PoleTO(x, y);

				}
			}

			for (int j = 0; j < MagazynUtils.liczbaRegalow; j++) {
				RegalPanel regal = regaly.get(j);
				int xRegalu = MagazynUtils.regalX / MagazynUtils.boxSize;
				int yRegalu = MagazynUtils.getRegalYPosition(j) / MagazynUtils.boxSize;

				TreeMap<String, BoxPanel> levelMap = regal.getLevelMap(i);

				for (String k : levelMap.keySet()) {

					int x = xRegalu + MagazynUtils.convertToColumn(k);
					int y = yRegalu + MagazynUtils.convertToRow(k);
					PoleTO p = levelMap.get(k).getPole();
					p.setPosition(k);
					p.setNrRegalu(j);
					p.setX(x);
					p.setY(y);
					p.setBox(true);
					pietro[x][y] = p;
					// System.out.println(pietro[y][x].getTowar().toString());
				}
			}
			// punkt odbioru

			int odbiorX = MagazynUtils.odbiorX / MagazynUtils.boxSize;
			int odbiorY = MagazynUtils.odbiorY / MagazynUtils.boxSize;
			int odbiorSizeX = odbiorX + MagazynUtils.odbiorWidth / MagazynUtils.boxSize;
			int odbiorSizeY = odbiorY + MagazynUtils.odbiorHeight / MagazynUtils.boxSize;

			for (int y = odbiorY; y < odbiorSizeY; y++) {
				for (int x = odbiorX; x < odbiorSizeX; x++) {
					PoleTO p = new PoleTO(x, y);
					p.setPunktOdbioru(true);

					pietro[x][y] = p;
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

	public void saveToFile(File file, ArrayList<RegalPanel> regaly) {
		log.info("zaimplementuj zapisywanie w iologic");

	}

}
