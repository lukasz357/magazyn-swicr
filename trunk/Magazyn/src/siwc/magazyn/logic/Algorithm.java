package siwc.magazyn.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import siwc.magazyn.Magazyn;
import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.dto.ZamowienieTO;
import siwc.magazyn.panels.MapaMagazynu;

public class Algorithm {

	//TODO pobierac aktualna pozycje wozka z mapy a nie z magazynu
	
	private Logger log = Logger.getLogger(MapaMagazynu.class);
	
	MagazynTO magazyn;
	MapaMagazynu mapa;

	List<ZamowienieTO> zamowienia = new ArrayList<ZamowienieTO>();
	
	public Algorithm(MapaMagazynu mapa, List<ZamowienieTO> zamowienia, MagazynTO magazyn) {
		this.mapa = mapa;
		this.zamowienia = zamowienia;
		this.magazyn = magazyn;
		log.info("CZY JEST MAGAZYN? "+magazyn);
	}
	
	public void startAlgorithm() {
		mapa.moveLiftRight();
		
		if (this.zamowienia == null || this.zamowienia.size() < 1) {
			log.info("KOLEGO! Brak zamówień!");
			Magazyn.dodajWpisDoKonsoli("Brak zamówień do przetworzenia, stopuję algorytm " + new Date().toString());
		}
		else {
			for (ZamowienieTO zamowienie : this.zamowienia) {
				log.info("Przetwarzam zamowienie dla: "+zamowienie.getDaneKlienta());
				Magazyn.dodajWpisDoKonsoli("Przetwarzam zamowienie dla: "+zamowienie.getDaneKlienta());
				
				for (TowarTO towar : zamowienie.getTowary()) {
					//PoleTO pole = znajdzPolePoId(towar.getIdBoxu());
					PoleTO pole = znajdzPolePoId(1);
					if (pole != null) {
						przemiescWozek(pole.getX(), pole.getY());
						log.info("Przemiescilem wozek na pole XY: "+pole.getX()+", "+pole.getY());
					}
					else {
						Magazyn.dodajWpisDoKonsoli("Nie odnaleziono boxu o ID: " + towar.getIdBoxu());
					}
				}
			}
		}
	}
	
	public String dodajTowarNaPierwseLepszeWolnePole(TowarTO paczka) {
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
	
	
	private void przemiescWozek(int xTo, int yTo) {
		wycofajWozek();
		log.info("XY WOZKA: "+mapa.getLiftX()+", "+mapa.getLiftY()+" a nalezy przesunac na: "+xTo+", "+yTo);
			while(Math.abs(mapa.getLiftX() - xTo) > 18 || Math.abs(mapa.getLiftY() - yTo) > 18) {
				log.info("1.: "+Math.abs(mapa.getLiftX() - xTo)+" .... 2.: "+Math.abs(mapa.getLiftY() - yTo));
				log.info("HERE");
				
				if (mapa.getLiftY() - yTo < 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yTo - yWozka)/18; i++) {
						log.info("PRZESUWAM w dol");
						mapa.moveLiftDown();
						//lift.setY(lift.getY()+1);
					}
				}
				else if (mapa.getLiftY() - yTo > 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yWozka - yTo)/18; i++) {
						log.info("PRZESUWAM w gore");
						mapa.moveLiftUp();
						//lift.setY(lift.getY()-1);
					}
				}
				else if (mapa.getLiftX() - xTo < 0) {
					int xWozka = mapa.getLiftX();
					log.info("TU");
					for (int i=0; i < (xTo - xWozka)/18; i++) {
						log.info("PRZESUWAM w prawo");
						mapa.moveLiftRight();
						//lift.setX(lift.getX()+1);
					}
				}
				else if (mapa.getLiftX() - xTo > 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xWozka - xTo)/18; i++) {
						log.info("PRZESUWAM w lewo");
						mapa.moveLiftLeft();
						//lift.setX(lift.getX()-1);
					}
				}
				
			}
			
	}
	
	private void wycofajWozek() {
		int xTo = 18;
		int xWozka = mapa.getLiftX();
		for (int i=0; i < (xWozka - xTo)/18; i++) {
			log.info("PRZESUWAM w lewo");
			mapa.moveLiftLeft();
			//lift.setX(lift.getX()-1);
		}
	}
	
	private PoleTO znajdzPolePoId(Integer id) {
		if (id != null) {
			log.info("MAGAZYN: "+this.magazyn);
			log.info("MAGAZYN.getpietra: "+this.magazyn.getPietra());
			log.info("MAGAZYN.getpietra.keyset: "+this.magazyn.getPietra().keySet());
			
			for(int i=0; i < this.magazyn.getPietra().keySet().size(); i++) {
				for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
					for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
						log.info("MAGAZYN PIETRO: "+i+" : "+magazyn.getPietra().get(i));
						log.info("MAGAZYN PIETRO: "+i+"element : "+j+", "+k+" : "+magazyn.getPietra().get(i)[j][k]);
						log.info("MAGAZYN PIETRO: "+i+"element : "+j+", "+k+" ID: "+magazyn.getPietra().get(i)[j][k].getId());
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
