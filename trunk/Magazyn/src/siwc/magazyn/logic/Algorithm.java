package siwc.magazyn.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public abstract void odswiezZamowienia(ZamowienieTO zamowienie);
	public abstract List<ZamowienieTO> getNoweZamowienia();
	public abstract int getTimeIntValue();
	public abstract void zwiekszLiczbeZamowienZrealizowanych(float czasRealizacjiZamowienia);
	
	private Logger log = Logger.getLogger(MapaMagazynu.class);
	
	private Long timeStart;
	private Long timeEnd;
	private Long timeStartTowar;
	private Long timeEndTowar;
	
	private int jakiSleep = 1200;
	private int buforNaRealizacjeZamowienia = 4000;//ile minut przed potrzebnym czasem zaczac wykonywac zamowienie
	private int stop = 0;
	
	MagazynTO magazyn;
	MapaMagazynu mapa;
	TowarTO aktualnyTowar;
	
	List<ZamowienieTO> zamowienia = new ArrayList<ZamowienieTO>();
	
	
	public Algorithm(MapaMagazynu mapa, MagazynTO magazyn) {
		this.mapa = mapa;
		this.magazyn = magazyn;
	}
	
	
	public void startAlgorithm() {
		
		while(stop == 0) {
			zamowienia = getNoweZamowienia();
			sortujZamowienia();
			
			log.info("\n\nWCZYTUJE ZAMOWIENIA ===================================================================");
			for (ZamowienieTO zamowienie : getNoweZamowienia()) {
				log.info("==== "+zamowienie.getNumerZamowienia()+", "+zamowienie.getDaneKlienta());
				for (TowarTO towar : zamowienie.getTowary()) {
					log.info(towar.getOpis());
				}
			}
			
			if (this.zamowienia == null || this.zamowienia.size() < 1) {
				log.info("KOLEGO! Brak zamówień!");
				Magazyn.dodajWpisDoKonsoli("Brak zamówień do przetworzenia, czekam...");
				MagazynUtils.sleep(5000);
			}
			else {
				timeStartCount();
				int size = zamowienia.size();
				for (int i=0; i < size; i++) {
					
					//todo posortowac zamowienia wg priorytetu
					ZamowienieTO zamowienie = zamowienia.get(0);
					
					log.info("getpriorytet: "+zamowienie.getPriorytet()+" time: "+getTimeIntValue()+" sleep: "+(zamowienie.getPriorytet() - (getTimeIntValue()+(buforNaRealizacjeZamowienia*zamowienie.getTowary().size()))));
					if (zamowienie.getPriorytet() - (getTimeIntValue()+(buforNaRealizacjeZamowienia*zamowienie.getTowary().size())) > 0) {
						Magazyn.dodajWpisDoKonsoli("Nic do zrobienia, czekam: "+(zamowienie.getPriorytet() - (getTimeIntValue()+(buforNaRealizacjeZamowienia*zamowienie.getTowary().size()))));
						MagazynUtils.sleep(zamowienie.getPriorytet() - (getTimeIntValue()+(buforNaRealizacjeZamowienia*zamowienie.getTowary().size())));
					}
					
					timeStartCount();
					log.info("Przetwarzam zamowienie: "+zamowienie.toString());
					Magazyn.dodajWpisDoKonsoli("=== Przetwarzam: "+zamowienie.toString());
					
					for (TowarTO towar : zamowienie.getTowary()) {
						if (stop != 0)
							break;
						
						timeStartCountTowar();
						PoleTO pole = znajdzPolePoId(towar.getIdBoxu());
						
						if (pole != null) {
							Magazyn.dodajWpisDoKonsoli("Wyruszam po towar: "+towar.getNazwa());
							przemiescWozek(pole.getX(), pole.getY(), pole.getZ(), pole.getPosition());
							if (!pole.isMovable()) {
								try {
									mapa.gonZBoksem(pole.getNrRegalu(), pole.getZ());
								} catch (Exception e) {
									log.info("o kuhwa");
									e.printStackTrace();
								}
							}
								
							log.info("Przemiescilem wozek na pole XY: "+pole.getX()+", "+pole.getY());
						
	//						//zabieramy na bary teraz towar i zawozimy do miejsca odbioru
							//magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().setIlosc(magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getIlosc()-1);
							if (magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar() == null)
								Magazyn.dodajWpisDoKonsoli("We wskazanym polu nie ma juz towaru: "+magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getNazwa()+" !!!");
							mapa.zmienKolorBoksu(pole.getNrRegalu(), pole.getZ(), pole.getPosition(), MagazynUtils.defaultBoxBackground);
							//else if (magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getIlosc() < 0) {
							//	Magazyn.dodajWpisDoKonsoli("We wskazanym polu nie ma juz towaru: "+magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar().getNazwa()+" !!!");
							//}
							aktualnyTowar = magazyn.getPietra().get(pole.getZ())[pole.getX()][pole.getY()].getTowar();
							//aktualnyTowar.setIlosc(1);
							
							PoleTO poleOdbioru = znajdzPierwszeLepszeWolnePoleOdbioru();
							
							if (poleOdbioru == null) {
								log.info("Brak wolnych miejsc w polu odbioru!");
								Magazyn.dodajWpisDoKonsoli("Brak wolnych miejsc w polu odbioru!");
								stop=1;
								break;
							} else {
								przemiescWozek(poleOdbioru.getX(), poleOdbioru.getY(), poleOdbioru.getZ(), poleOdbioru.getPosition());
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
					zwiekszLiczbeZamowienZrealizowanych((timeEnd-timeStart)/100F);
					odswiezZamowienia(zamowienie);
					zamowienia.remove(0);
				}
			}
		}
	}
	
	
	private void przemiescWozek(int xTo, int yTo, int zTo, String destination) {
		mapa.pokazPietro(zTo);
		for (int i = 0; i <= Math.abs(zTo-mapa.getLiftLeve()); i++) {
			if (mapa.getLiftLeve() < zTo)
				mapa.liftUp();
			else
				mapa.lifDown();
		}
		
		if (yTo < 18) {
			if (destination.charAt(0) == 'A' || destination.charAt(0) == 'B')
				yTo -= 2;
			else
				yTo += 2;
		}
		wycofajWozek();
		log.info("XY WOZKA: "+mapa.getLiftX()+", "+mapa.getLiftY()+" a nalezy przesunac na: "+xTo+", "+yTo);
		int index=0;
			while(Math.abs(mapa.getLiftX() - xTo) > 1 || Math.abs(mapa.getLiftY() - yTo) > 2) {
				if (index > 10)
					break;
				//log.info("Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
				
				if (mapa.getLiftY() - yTo < 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yTo - yWozka); i++) {
						//log.info("Lece w dol"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftDown();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							//log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
					}
				}
				else if (mapa.getLiftY() - yTo > 0) {
					int yWozka = mapa.getLiftY();
					for (int i=0; i < (yWozka - yTo); i++) {
						//log.info("Lece w gore"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftUp();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							//log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
					}
				}
				else if (mapa.getLiftX() - xTo < 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xTo - xWozka); i++) {
						//log.info("Lece w prawo"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftRight();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							log.info("Mapa liftY: "+mapa.getLiftY()+" yto: "+yTo);
							if (mapa.getLiftY()-yTo <= 0)
								yTo-=1;
							else
								yTo+=1;
							log.info(">>>>>> Zmienilem wartosc Y na: "+yTo);
							//log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
					}
				}
				else if (mapa.getLiftX() - xTo > 0) {
					int xWozka = mapa.getLiftX();
					for (int i=0; i < (xWozka - xTo); i++) {
						//log.info("Lece w lewo"+"-- Obecna pozycja: "+Math.abs(mapa.getLiftX())+", "+Math.abs(mapa.getLiftY()));
						try {
							mapa.moveLiftLeft();
							MagazynUtils.sleep(jakiSleep);
						} catch (Exception e) {
							//log.warn("PANIE GDZIE PAN JEDZIESZ?! chce jechac na: "+xTo+", "+yTo);
							break;
						}
					}
				}
				
				index++;
			}
			
	}
	
	private void wycofajWozek() {
		int xTo = 0;
		int xWozka = mapa.getLiftX();
		for (int i=0; i < (xWozka - xTo); i++) {
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
						log.info("Znalazlem pole odbioru X: "+j+" Y: "+k+" Z: "+i+" TOWAR = "+magazyn.getPietra().get(i)[j][k].getTowar());
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
	
	private void sortujZamowienia() {
		Collections.sort(zamowienia, new Comparator<ZamowienieTO>() {
			@Override
			public int compare(ZamowienieTO o1, ZamowienieTO o2) {
				Integer l1 = o1.getPriorytet() - getTimeIntValue();
				Integer l2 = o2.getPriorytet() - getTimeIntValue();
				
				return l1.compareTo(l2);
			}
		});
	}
}
