package siwc.magazyn.dto;

public class ListTowarTO extends TowarTO{
	private int ilePaczek;

	public ListTowarTO(){
		super();
	}
	
	public ListTowarTO(TowarTO towar, int ilePaczek) {
		super(towar);
		this.ilePaczek = ilePaczek;
	}
	
	public ListTowarTO(TowarTO towar) {
		super(towar);
		ilePaczek = 1;
	}
	public int getIlePaczek() {
		return ilePaczek;
	}

	public void setIlePaczek(int ilePaczek) {
		this.ilePaczek = ilePaczek;
	}
	
	public void zwiekszIlosc(){
		this.ilePaczek++;
	}
	
	public void zmniejszIlosc() {
		this.ilePaczek--;
	}
}
