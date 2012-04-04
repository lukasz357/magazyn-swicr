package siwc.magazyn.dto;

public class ListTowarTO extends TowarTO{
	private int ilePaczek;

	public ListTowarTO(TowarTO towar, int ilePaczek) {
		super(towar);
		this.ilePaczek = ilePaczek;
	}

	public int getIlePaczek() {
		return ilePaczek;
	}

	public void setIlePaczek(int ilePaczek) {
		this.ilePaczek = ilePaczek;
	}
	
}
