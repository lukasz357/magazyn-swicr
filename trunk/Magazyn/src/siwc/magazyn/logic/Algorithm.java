package siwc.magazyn.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import siwc.magazyn.Magazyn;
import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.dto.ZamowienieTO;
import siwc.magazyn.panels.MapaMagazynu;
import siwc.magazyn.utils.MagazynUtils;

public abstract class Algorithm {

	public abstract void odswiezZamowienia(int indexZamowienia);
	public abstract List<ZamowienieTO> getNoweZamowienia();
	
	private Logger log = Logger.getLogger(MapaMagazynu.class);
	
	private Long timeStart;
	private Long timeEnd;
	private Long timeStartTowar;
	private Long timeEndTowar;
	
	private int jakiSleep = 2000;
	private int stop = 0;
	
	MagazynTO magazyn;
	MapaMagazynu mapa;
	TowarTO aktualnyTowar;
	
	List<ZamowienieTO> zamowienia = new ArrayList<ZamowienieTO>();
	
	
	public Algorithm(MapaMagazynu mapa, MagazynTO magazyn) {
		this.mapa = mapa;
		this.magazyn = magazyn;
		log.info("CZY JEST MAGAZYN? "+magazyn != null);
	}
	
	
	public void startAlgorithm() {
		
		while(stop == 0) {
			zamowienia = getNoweZamowienia();
			
			if (this.zamowienia == null || this.zamowienia.size() < 1) {
				log.info("KOLEGO! Brak zamówień!");
				Magazyn.dodajWpisDoKonsoli("Brak zamówień do przetworzenia, czekam...");
				MagazynUtils.sleep(jakiSleep);
			}
			else {
				timeStartCount();
				for (int i=0; i < this.zamowienia.size(); i++) {
					zamowienia = getNoweZamowienia();
					//todo posortowac zamowienia wg priorytetu
					
					ZamowienieTO zamowienie = zamowienia.get(i);
					
					timeStartCount();
					log.info("Przetwarzam zamowienie: "+zamowienie.toString());
					Magazyn.dodajWpisDoKonsoli("Przetwarzam zamowienie dla: "+zamowienie.toString());
					
					for (TowarTO towar : zamowienie.getTowary()) {
						
						timeStartCountTowar();
						PoleTO pole = znajdzPolePoId(towar.getIdBoxu());
						
						if (pole != null) {
							przemiescWozek(pole.getX(), pole.getY(), pole.getZ());
							//if (pole.get)
								
							log.info("Przemiescilem wozek na pole XY: "+pole.getX()+", "+pole.getY());
						
	//						//zabieramy na bary teraz towar i zawozimy do miejsca odbioru
							magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().setIlosc(magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getIlosc()-1);
							if (magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getIlosc() == 0)
								mapa.zmienKolorBoksu(pole.getNrRegalu(), pole.getZ(), pole.getPosition(), MagazynUtils.defaultBoxBackground);
							
							aktualnyTowar = magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar();
							aktualnyTowar.setIlosc(1);
							
							PoleTO poleOdbioru = znajdzPierwszeLepszeWolnePoleOdbioru();
							
							if (poleOdbioru == null) {
								log.info("Brak wolnych miejsc w polu odbioru!");
							} else {
								przemiescWozek(poleOdbioru.getX(), poleOdbioru.getY(), poleOdbioru.getZ());
								log.info("Przed wstawieniem: "+magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].getTowar());
								magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].setTowar(towar);
								log.info("Wstawilem towar na miejsce odbioru i jest rowny temu: "+magazyn.getPietra().get(poleOdbioru.getZ())[poleOdbioru.getX()][poleOdbioru.getY()].getTowar());
								aktualnyTowar = null;
								
								Magazyn.dodajWpisDoKonsoli("Towar dostarczony do punktu odbioru: "+poleOdbioru.getX()+", "+poleOdbioru.getY()+" w czasie: "+timeEndCountTowar());
								wycofajWozek();
							}
						}
						else {
							Magazyn.dodajWpisDoKonsoli("Nie odnaleziono boxu o ID: " + towar.getIdBoxu());
						}
						timeEndCountTowar();
					}
					Magazyn.dodajWpisDoKonsoli("Zamówienie przetworzone w czasie: "+timeEndCount());
					this.zamowienia.remove(i);
					odswiezZamowienia(i);
				}
			}
		}
	}
	
	
	private void przemiescWozek(int xTo, int yTo, int zTo) {
		mapa.pokazPietro(zTo);
		yTo -= 2;
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
						log.info("Lece w dol"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftDown();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
						//lift.setY(lift.getY()+1);
					}
				}
				else if (mapa.getLiftY() - yTo > 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yWozka - yTo); i++) {
						log.info("Lece w gore"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftUp();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
						//lift.setY(lift.getY()-1);
					}
				}
				else if (mapa.getLiftX() - xTo < 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xTo - xWozka); i++) {
						log.info("Lece w prawo"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftRight();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							if (mapa.getLiftY()-yTo < 0)
								yTo-=1;
							else
								yTo+=1;
							log.info(">>>>>> Zmienilem wartosc Y na: "+yTo);
							log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
						//lift.setX(lift.getX()+1);
					}
				}
				else if (mapa.getLiftX() - xTo > 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xWozka - xTo); i++) {
						log.info("Lece w lewo"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftLeft();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
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
				MagazynUtils.sleep(jakiSleep);
			} catch (Exception e) {
				log.info("Przyrznalem w polke przy cofaniu, sprobuje w dol.");
				try {
					mapa.moveLiftDown();
					MagazynUtils.sleep(jakiSleep);
				} catch (Exception f) {
					log.info("Przyrznalem w polke przy cofaniu, sprobuje w gore.");
					try {
						mapa.moveLiftUp();
						MagazynUtils.sleep(jakiSleep);
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
						if (magazyn.getPietra().get(i)[j][k].getId() != null &&magazyn.getPietra().get(i)[j][k].getId().equals(id)) {
							magazyn.getPietra().get(i)[j][k].setZ(i);
							
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
	
	private String timeEndCount() {
		timeEnd = System.currentTimeMillis();
		log.info("Stopuje mierzenie czasu: "+timeEnd+". Zmierzony czas: "+((timeEnd-timeStart)/100F)+" sekund.");
		return ((timeEnd-timeStart)/100F)+" sekund";
	}
	
	private void timeStartCountTowar() {
		timeStartTowar = System.currentTimeMillis();
		log.info("Startuje mierzenie czasu towaru: "+timeStartTowar);
	}
	
	private String timeEndCountTowar() {
		timeEndTowar = System.currentTimeMillis();
		log.info("Stopuje mierzenie czasu towaru: "+timeEndTowar+". Zmierzony czas: "+((timeEndTowar-timeStartTowar)/100F)+" sekund.");
		return ((timeEndTowar-timeStartTowar)/100F)+" sekund";
	}


	public int getJakiSleep() {
		return jakiSleep;
	}


	public void setJakiSleep(int jakiSleep) {
		this.jakiSleep = jakiSleep;
	}


	public int getStop() {
		return stop;
	}


	public void setStop(int stop) {
		this.stop = stop;
	}
}
