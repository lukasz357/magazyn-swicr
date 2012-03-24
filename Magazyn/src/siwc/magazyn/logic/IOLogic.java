package siwc.magazyn.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.utils.MagazynUtils;

public class IOLogic {
	private Logger log = Logger.getLogger(IOLogic.class);
	
	public MagazynTO getMagazynFromFile() {
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
	
	public ArrayList<RegalPanel> readFileAsRegalPanelArray(File file) {
		ArrayList<RegalPanel> regaly = new ArrayList<>();

		for (int i = 0; i < MagazynUtils.liczbaRegalow; i++)
			regaly.add(new RegalPanel(1));

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || line.equals("")) // komentarz - pusta linia
					continue;

				String[] tLine = null;
				if (line.contains(";"))
					tLine = line.split(";");
				else if (line.contains(","))
					tLine = line.split(",");
				if (tLine == null || tLine.length < MagazynUtils.liczbaKolumnPlikuZamowien - 1) {
					log.error("Problem przy wczytywaniu pliku!");
					return null;
				}
				// dla pliku: REGAL;PIETRO;POZYCJA;NAZWA;PRODUCENT;KOD TOWARU
				String regal = tLine[0];
				String pietro = tLine[1];
				String pozycja = tLine[2];
				String nazwa = tLine[3];
				String producent = tLine[4];
				String kodTowaru = tLine[6];

				// TODO 

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return regaly;
	}

	public MagazynTO convertToMagazynTO(ArrayList<RegalPanel> regaly) {
		MagazynTO magazyn = new MagazynTO();
		magazyn.setWielkoscXMagazynu(MagazynUtils.kolumnWRegale);
		magazyn.setWielkoscYMagazynu(MagazynUtils.rzedowWRegale);

		TreeMap<Integer, PoleTO[][]> pietra = new TreeMap<>();
		for (int i = 0; i < MagazynUtils.liczbaPieter; i++) {
			pietra.put(i, new PoleTO[magazyn.getWielkoscXMagazynu()][magazyn.getWielkoscYMagazynu()]);
		}
		//TODO
		return magazyn;
	}
	

	public String saveMagazynToFile() {
		return "Wszystko ok";
	}
	
	public static void main(String ... strings ) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Plik tekstowy (*.txt)", "txt");
		fileChooser.setFileFilter(txtFilter);
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV (*.csv)", "csv"));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(true);
		
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			System.out.println(fileChooser.getSelectedFile());
		}
	}
	

	
}
