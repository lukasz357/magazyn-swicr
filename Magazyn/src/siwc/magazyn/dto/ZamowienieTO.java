package siwc.magazyn.dto;

import java.util.ArrayList;
import java.util.List;

public class ZamowienieTO {
	private int numerZamowienia;
	private String daneKlienta; //Imie i nazwisko
	private List<ListTowarTO> towary = new ArrayList<>();
	
	public int getNumerZamowienia() {
		return numerZamowienia;
	}
	public void setNumerZamowienia(int numerZamowienia) {
		this.numerZamowienia = numerZamowienia;
	}
	public String getDaneKlienta() {
		return daneKlienta;
	}
	public void setDaneKlienta(String daneKlienta) {
		this.daneKlienta = daneKlienta;
	}
	public List<ListTowarTO> getTowary() {
		return towary;
	}
	public void setTowary(List<ListTowarTO> towary) {
		this.towary = towary;
	}
}
