package siwc.magazyn.dto;

public class TowarTO {

	Integer idBoxu;
	
	String nazwa;
	String producent;
	Integer ilosc;
	String kodTowaru;
	
	public TowarTO(){
	}

	public TowarTO(int id){
		this.idBoxu = id;
	}
	
	public TowarTO(TowarTO towar) {
		this.nazwa = towar.getNazwa();
		this.producent = towar.getProducent();
		this.ilosc = towar.getIlosc();
		this.kodTowaru = towar.getKodTowaru();
	}

	public String getOpis() {
		String opis = "";//"<html>";
		opis += "Kod towaru: "+kodTowaru+"<br>";
		opis += "Nazwa: "+nazwa+"<br>";
		opis += "Ilosc: "+ilosc+"<br>";
		opis += "Producent: "+producent;
		opis += "</html>";
		
		return opis;
	}
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getProducent() {
		return producent;
	}

	public void setProducent(String producent) {
		this.producent = producent;
	}

	public Integer getIlosc() {
		return ilosc;
	}

	public void setIlosc(Integer ilosc) {
		this.ilosc = ilosc;
	}

	public String getKodTowaru() {
		return kodTowaru;
	}

	public void setKodTowaru(String kodTowaru) {
		this.kodTowaru = kodTowaru;
	}

	public Integer getIdBoxu() {
		return idBoxu;
	}

	public void setIdBoxu(Integer idBoxu) {
		this.idBoxu = idBoxu;
	}

}
