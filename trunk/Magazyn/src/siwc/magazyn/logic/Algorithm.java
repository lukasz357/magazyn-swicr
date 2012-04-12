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
import siwc.magazyn.utils.MagazynUtils;

public class Algorithm {

	private Logger log = Logger.getLogger(MapaMagazynu.class);
	
	private Long timeStart;
	private Long timeEnd;
	private Long timeStartTowar;
	private Long timeEndTowar;
	
	MagazynTO magazyn;
	MapaMagazynu mapa;
	TowarTO aktualnyTowar;
	
	List<ZamowienieTO> zamowienia = new ArrayList<ZamowienieTO>();
	
	
	public Algorithm(MapaMagazynu mapa, List<ZamowienieTO> zamowienia, MagazynTO magazyn) {
		this.mapa = mapa;
		this.zamowienia = zamowienia;
		this.magazyn = magazyn;
		log.info("CZY JEST MAGAZYN? "+magazyn != null);
	}
	
	
	public void startAlgorithm() {
		
		
		if (this.zamowienia == null || this.zamowienia.size() < 1) {
			log.info("KOLEGO! Brak zamówień!");
			Magazyn.dodajWpisDoKonsoli("Brak zamówień do przetworzenia, czekam...");
		}
		else {
			timeStartCount();
			int index = 0;
			for (ZamowienieTO zamowienie : this.zamowienia) {

				timeStartCountTowar();
				log.info("Przetwarzam zamowienie: "+zamowienie.toString());
				Magazyn.dodajWpisDoKonsoli("Przetwarzam zamowienie dla: "+zamowienie.toString());
				
				for (TowarTO towar : zamowienie.getTowary()) {
					if (index > 1)
						break;
					
					timeStartCount();
					PoleTO pole = znajdzPolePoId(towar.getIdBoxu());
					if (pole != null) {
						przemiescWozek(pole.getX(), pole.getY()-2);
						log.info("Przemiescilem wozek na pole XY: "+pole.getX()+", "+pole.getY());
					
//						//zabieramy na bary teraz towar i zawozimy do miejsca odbioru
						magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].setTowar(null);
						aktualnyTowar = towar;
						PoleTO poleOdbioru = znajdzPierwszeLepszeWolnePoleOdbioru();
						if (poleOdbioru == null) {
							log.info("Brak wolnych miejsc w polu odbioru!");
						} else {
							przemiescWozek(poleOdbioru.getX(), poleOdbioru.getY());
							log.info("Przed wstawieniem: "+magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].getTowar());
							magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].setTowar(towar);
							log.info("Wstawilem towar na miejsce odbioru i jest rowny temu: "+magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].getTowar());
							aktualnyTowar = null;
							wycofajWozek();
						}
					}
					else {
						Magazyn.dodajWpisDoKonsoli("Nie odnaleziono boxu o ID: " + towar.getIdBoxu());
					}
					index++;
					timeEndCountTowar();
				}
				timeEndCount();
			}
		}
	}
	
	
	private void przemiescWozek(int xTo, int yTo) {
		wycofajWozek();
		log.info("XY WOZKA: "+mapa.getLiftX()+", "+mapa.getLiftY()+" a nalezy przesunac na: "+xTo+", "+yTo);
		int index=0;
			while(Math.abs(mapa.getLiftX() - xTo) > 2 || Math.abs(mapa.getLiftY() - yTo) > 2) {
				if (index > 10)
					break;
				log.info("Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
				
				if (mapa.getLiftY() - yTo < 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yTo - yWozka); i++) {
						log.info("Lece w dol");
						try {
							mapa.moveLiftDown();
							MagazynUtils.sleep(200);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setY(lift.getY()+1);
					}
				}
				else if (mapa.getLiftY() - yTo > 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yWozka - yTo); i++) {
						log.info("Lece w gore");
						try {
							mapa.moveLiftUp();
							MagazynUtils.sleep(200);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setY(lift.getY()-1);
					}
				}
				else if (mapa.getLiftX() - xTo < 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xTo - xWozka); i++) {
						log.info("Lece w prawo");
						try {
							mapa.moveLiftRight();
							MagazynUtils.sleep(200);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setX(lift.getX()+1);
					}
				}
				else if (mapa.getLiftX() - xTo > 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xWozka - xTo); i++) {
						log.info("Lece w lewo");
						try {
							mapa.moveLiftLeft();
							MagazynUtils.sleep(200);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setX(lift.getX()-1);
						
					}
				}
				
				index++;
			}
			
	}
	
	private void wycofajWozek() {
		int xTo = 0;
		int xWozka = mapa.getLiftX();
		for (int i=0; i < (xWozka - xTo); i++) {
			log.info("PRZESUWAM w lewo");
			try {
				mapa.moveLiftLeft();
			} catch (Exception e) {
				log.info("Przyrznalem w polke przy cofaniu, sprobuje w dol.");
				try {
					mapa.moveLiftDown();
				} catch (Exception f) {
					log.info("Przyrznalem w polke przy cofaniu, sprobuje w gore.");
					try {
						mapa.moveLiftUp();
					} catch (Exception g) {
						log.info("Zaklinowalem sie, nie moge sie wycofac.");
					}
				}
			}
		}
	}
	
	private PoleTO znajdzPolePoId(Integer id) {
		log.info("Szukam pola o ID: "+id);
		if (id != null) {
			
			log.info("X MAGAZYNU: "+magazyn.getWielkoscXMagazynu()+" Y: "+magazyn.getWielkoscYMagazynu()+" length: "+magazyn.getPietra().get(0).length+" length2: "+magazyn.getPietra().get(0)[0].length);
			
			for(int i=0; i < magazyn.getPietra().keySet().size(); i++) {
				for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
					for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
						if (magazyn.getPietra().get(i)[j][k].getId() == null)
							log.info("ID POLA == NULL :(((((((((((((");
						else if (magazyn.getPietra().get(i)[j][k].getId().equals(id)) {
							magazyn.getPietra().get(i)[j][k].setZ(i);
							
							//magazyn.getPietra().get(i)[j][k].setX(j);
							//magazyn.getPietra().get(i)[j][k].setY(k-2);
							log.info("=================== ZNALAZLEM ================= "+j+", "+k+", "+i+" WSP: "+magazyn.getPietra().get(i)[j][k].getX()+", "+magazyn.getPietra().get(i)[j][k].getY());
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
							magazyn.getPietra().get(i)[j][k].setZ(i);
							return magazyn.getPietra().get(i)[j][k];
						}
					}
				}
			}
			return null;
	}
	
	private PoleTO znajdzPierwszeLepszeWolnePoleOdbioru() {
		for(int i=0; i < magazyn.getPietra().keySet().size(); i++) {
			for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
				for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
					if (magazyn.getPietra().get(i)[j][k].isPunktOdbioru() && magazyn.getPietra().get(i)[j][k].getTowar() == null) {
						magazyn.getPietra().get(i)[j][k].setZ(i);
						log.info("Znalazlem pole odbioru X: "+j+" Y: "+k+" TOWAR = "+magazyn.getPietra().get(i)[j][k].getTowar());
						return magazyn.getPietra().get(i)[j][k];
					}
				}
			}
		}
		return null;
	}
	
	private void timeStartCount() {
		timeStart = System.currentTimeMillis();
		log.info("Startuje mierzenie czasu: "+timeStart);
	}
	
	private void timeEndCount() {
		timeEnd = System.currentTimeMillis();
		log.info("Stopuje mierzenie czasu: "+timeEnd+". Zmierzony czas: "+((timeEnd-timeStart)/1000F)+" sekund.");
	}
	
	private void timeStartCountTowar() {
		timeStartTowar = System.currentTimeMillis();
		log.info("Startuje mierzenie czasu towaru: "+timeStartTowar);
	}
	
	private void timeEndCountTowar() {
		timeEndTowar = System.currentTimeMillis();
		log.info("Stopuje mierzenie czasu towaru: "+timeEndTowar+". Zmierzony czas: "+((timeEndTowar-timeStartTowar)/1000F)+" sekund.");
	}
}
