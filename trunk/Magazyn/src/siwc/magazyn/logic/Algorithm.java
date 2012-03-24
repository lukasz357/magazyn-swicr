package siwc.magazyn.logic;

import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.panels.MapaMagazynu;

public class Algorithm {

	//TODO pobierac aktualna pozycje wozka z mapy a nie z magazynu
	
	MagazynTO magazyn;
	MapaMagazynu mapa;

	public Algorithm(MagazynTO magazyn,  int wozekStartX, int wozekStartY) {
		this.magazyn = magazyn;
	}
	
	public String dodajTowar(TowarTO paczka) {
			//TODO albo znajdz pierwsze wolne pole
			//PoleTO pole = znajdzPolePoId(paczka.getIdBoxu());
			PoleTO pole = znajdzPierwszeLepszeWolnePole();
			
			if (pole != null) {
				while(magazyn.getxWozka() - pole.getX() > 1 && magazyn.getyWozka() - pole.getY() > 1) {
					if (magazyn.getxWozka() - pole.getX() < 0) {
						int xWozka = magazyn.getxWozka();
						for (int i=0; i < pole.getX() - xWozka; i++) {
							mapa.moveLiftRight();
							magazyn.setxWozka(magazyn.getxWozka()+1);
						}
					}
					if (magazyn.getxWozka() - pole.getX() > 0) {
						int xWozka = magazyn.getxWozka();
						for (int i=0; i < xWozka - pole.getX(); i++) {
							mapa.moveLiftLeft();
							magazyn.setxWozka(magazyn.getxWozka()-1);
						}
					}
					if (magazyn.getyWozka() - pole.getY() < 0) {
						int yWozka = magazyn.getyWozka();
						for (int i=0; i < pole.getY() - yWozka; i++) {
							mapa.moveLiftUp();
							magazyn.setyWozka(magazyn.getyWozka()+1);
						}
					}
					if (magazyn.getyWozka() - pole.getY() > 0) {
						int yWozka = magazyn.getyWozka();
						for (int i=0; i < yWozka - pole.getY(); i++) {
							mapa.moveLiftDown();
							magazyn.setyWozka(magazyn.getyWozka()-1);
						}
					}
				}
				
				pole.setTowar(paczka);
			}
			else
				return "Nie odnaleziono pola o ID: "+paczka.getIdBoxu();
		//else
			//return "Nie wiem gdzie wstawić paczkę. Nie przekazano idPola.";
		return null;
	}
	
	
	private PoleTO znajdzPolePoId(Integer id) {
		if (id != null) {
			for(int i=0; i < magazyn.getPietra().keySet().size(); i++) {
				for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
					for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
						if (magazyn.getPietra().get(i)[j][k].getId().equals(id)) {
							return magazyn.getPietra().get(i)[j][k];
						}
					}
				}
			}
			return null;
		}
		else
			return null;
	}
	
	private PoleTO znajdzPierwszeLepszeWolnePole() {
			for(int i=0; i < magazyn.getPietra().keySet().size(); i++) {
				for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
					for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
						if (magazyn.getPietra().get(i)[j][k].getTowar() == null) {
							return magazyn.getPietra().get(i)[j][k];
						}
					}
				}
			}
			return null;
	}
	void setMapa(MapaMagazynu mapa) {
		this.mapa = mapa;
	}
}
