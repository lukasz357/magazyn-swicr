package siwc.magazyn.dto;

public class TowarTO {

	Integer idBoxu;
	
	String nazwa;
	String producent;
	Integer ilosc;
	String kodTowaru;
	

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
