package siwc.magazyn.dto;

public class TowarTO {

	Integer idBoxu;
	
	String nazwa;
	String producent;
	String kodTowaru;
	boolean zarezerwowany;
	
	public TowarTO(){
	}

	public TowarTO(int id){
		this.idBoxu = id;
		zarezerwowany = false;
	}
	
	public TowarTO(TowarTO towar) {
		this.nazwa = towar.getNazwa();
		this.producent = towar.getProducent();
		this.kodTowaru = towar.getKodTowaru();
		this.zarezerwowany = towar.isZarezerwowany();
	}

	public String getOpis() {
		String opis = "";//"<html>";
		opis += "Kod towaru: "+kodTowaru+"<br>";
		opis += "Nazwa: "+nazwa+"<br>";
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

	public boolean isZarezerwowany() {
		return zarezerwowany;
	}

	public void setZarezerwowany(boolean zarezerwowany) {
		this.zarezerwowany = zarezerwowany;
	}

	@Override
	public String toString() {
		return "TowarTO [idBoxu=" + idBoxu + ", nazwa=" + nazwa
				+ ", producent=" + producent
				+ ", kodTowaru=" + kodTowaru + ", zarezerwowany="
				+ zarezerwowany + "]";
	}

}
