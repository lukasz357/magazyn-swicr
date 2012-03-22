package siwc.magazyn.logic;

import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;

public class IOLogic {

	public MagazynTO getMagazynFromFile() {
		MagazynTO magazyn = new MagazynTO();
		magazyn.setWielkoscXMagazynu(4);
		magazyn.setWielkoscYMagazynu(4);
		
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
		
		PoleTO emptyField = new PoleTO();
		emptyField.setId(-1); //empty field so id = -1 ?
		emptyField.setBox(false);
		emptyField.setPunktOdbioru(false);
		emptyField.setTowar(null);
		emptyField.setX(-1);
		emptyField.setY(-1);
		
		PoleTO[][] pietro1 = new PoleTO[100][100];
		pietro1[4][4] = box;
		pietro1[3][4] = box2;
		pietro1[1][1] = box3;
		
		//Box sie powtarza, chyba nie powi
		pietro1[0][0] = emptyField;
		pietro1[0][1] = emptyField;
		pietro1[0][2] = emptyField;
		pietro1[0][3] = emptyField;
		pietro1[0][4] = emptyField;
		pietro1[1][0] = emptyField;
		pietro1[1][2] = emptyField;
		pietro1[1][3] = emptyField;
		pietro1[1][4] = emptyField;
		pietro1[2][0] = emptyField;
		pietro1[2][1] = emptyField;
		pietro1[2][2] = emptyField;
		pietro1[2][3] = emptyField;
		pietro1[2][4] = emptyField;
		pietro1[3][0] = emptyField;
		pietro1[3][1] = emptyField;
		pietro1[3][2] = emptyField;
		pietro1[3][3] = emptyField;
		pietro1[3][4] = emptyField;
		pietro1[4][0] = emptyField;
		pietro1[4][1] = emptyField;
		pietro1[4][2] = emptyField;
		pietro1[4][3] = emptyField;
		pietro1[4][4] = emptyField;
		
		magazyn.getPietra().put(0, pietro1);
		
		return new MagazynTO();
		
	}
	
	public String saveMagazynToFile() {
		return "Wszystko ok";
	}
	
}
