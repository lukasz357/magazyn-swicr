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
		try {
			mapa.moveLiftDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mapa.moveLiftDown();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mapa.moveLiftDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.zamowienia == null || this.zamowienia.size() < 1) {
			log.info("KOLEGO! Brak zamówień!");
			Magazyn.dodajWpisDoKonsoli("Brak zamówień do przetworzenia, stopuję algorytm " + new Date().toString());
		}
		else {
			timeStartCount();
			int index = 0;
			for (ZamowienieTO zamowienie : this.zamowienia) {

				timeStartCountTowar();
				log.info("Przetwarzam zamowienie dla: "+zamowienie.getDaneKlienta());
				Magazyn.dodajWpisDoKonsoli("Przetwarzam zamowienie dla: "+zamowienie.getDaneKlienta());
				
				for (TowarTO towar : zamowienie.getTowary()) {
					if (index > 1)
						break;
					
					timeStartCount();
					PoleTO pole = znajdzPolePoId(towar.getIdBoxu());
					if (pole != null) {
						przemiescWozek(pole.getX(), pole.getY());
						log.info("Przemiescilem wozek na pole XY: "+pole.getX()+", "+pole.getY());
						
						//zabieramy na bary teraz towar i zawozimy do miejsca odbioru
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
			while(Math.abs(mapa.getLiftX() - xTo) > 36 || Math.abs(mapa.getLiftY() - yTo) > 36) {
				log.info("1.: "+Math.abs(mapa.getLiftX() - xTo)+" .... 2.: "+Math.abs(mapa.getLiftY() - yTo));
				log.info("HERE");
				
				if (mapa.getLiftY() - yTo < 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yTo - yWozka)/18; i++) {
						log.info("PRZESUWAM w dol");
						try {
							mapa.moveLiftDown();
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setY(lift.getY()+1);
					}
				}
				else if (mapa.getLiftY() - yTo > 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yWozka - yTo)/18; i++) {
						log.info("PRZESUWAM w gore");
						try {
							mapa.moveLiftUp();
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setY(lift.getY()-1);
					}
				}
				else if (mapa.getLiftX() - xTo < 0) {
					int xWozka = mapa.getLiftX();
					log.info("TU");
					for (int i=0; i < (xTo - xWozka)/18; i++) {
						log.info("PRZESUWAM w prawo");
						try {
							mapa.moveLiftRight();
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
						//lift.setX(lift.getX()+1);
					}
				}
				else if (mapa.getLiftX() - xTo > 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xWozka - xTo)/18; i++) {
						log.info("PRZESUWAM w lewo");
						try {
							mapa.moveLiftLeft();
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?!");
						}
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
							log.info("=================== ZNALAZLEM =================");
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
