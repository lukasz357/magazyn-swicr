package siwc.magazyn.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ZamowienieTO {
	private int numerZamowienia;
	private int priorytet;
	private String terminRealizacji;
	private String daneKlienta; //Imie i nazwisko
	private List<TowarTO> towary = new ArrayList<>();
	
	
	public Collection<ListTowarTO> getTowaryDoListy(){
		HashMap<String, ListTowarTO>list = new HashMap<>();
		for(TowarTO t : towary) {
			if(!list.containsKey(t.getKodTowaru())){
				list.put(t.getKodTowaru(), new ListTowarTO(t));
			}
			else
				list.get(t.getKodTowaru()).zwiekszIlosc();
		}
		return list.values();
	}
	
	public int getNumerZamowienia() {
		return numerZamowienia;
	}
	public void setNumerZamowienia(int numerZamowienia) {
		this.numerZamowienia = numerZamowienia;
	}
	public int getPriorytet() {
		return priorytet;
	}
	public void setPriorytet(int priorytet) {
		this.priorytet = priorytet;
	}
	public String getDaneKlienta() {
		return daneKlienta;
	}
	public void setDaneKlienta(String daneKlienta) {
		this.daneKlienta = daneKlienta;
	}
	public List<TowarTO> getTowary() {
		return towary;
	}
	public void setTowary(List<TowarTO> towary) {
		this.towary = towary;
	}
	
	public String towaryToString() {
		String s = "";
		for(TowarTO t: towary) {
			s += ("Zamowienie: "+numerZamowienia+", towar - idBoxu: "+t.idBoxu+" kod towaru: "+t.kodTowaru+"X: \n");
		}
		return s;
	}

	public String getTerminRealizacji() {
		return terminRealizacji;
	}

	public void setTerminRealizacji(String terminRealizacji) {
		this.terminRealizacji = terminRealizacji;
	}
	
	@Override
	public String toString() {
		return "Zamówienie numer: "+numerZamowienia+", termin realizacji: "+terminRealizacji+", dane klienta: "+daneKlienta+".";
	}
}
