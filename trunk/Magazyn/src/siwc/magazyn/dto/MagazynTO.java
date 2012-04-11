package siwc.magazyn.dto;

import java.util.ArrayList;
import java.util.TreeMap;

public class MagazynTO {

	int id;
	int wielkoscXMagazynu;
	int wielkoscYMagazynu;
	TreeMap<Integer, PoleTO[][]> pietra = new TreeMap<>();
	
	int xWozka = 0;
	int yWozka = 0;
	int zWozka = 0;
	
	public ArrayList<TowarTO>getDostepneTowaryByKod(String kod) {
		ArrayList<TowarTO> list = new ArrayList<>();
		for(PoleTO[][] tab : pietra.values()){
			for(int i = 0; i < getWielkoscYMagazynu(); i++)
				for(int j = 0; j < getWielkoscXMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						if(t.getKodTowaru().equals(kod) && !t.isZarezerwowany())
							list.add(tab[i][j].getTowar());
				}
		}
		
		return list;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TreeMap<Integer, PoleTO[][]> getPietra() {
		return pietra;
	}
	public void setPietra(TreeMap<Integer, PoleTO[][]> pietro) {
		this.pietra = pietro;
	}
	public int getWielkoscXMagazynu() {
		return wielkoscXMagazynu;
	}
	public void setWielkoscXMagazynu(int wielkoscXMagazynu) {
		this.wielkoscXMagazynu = wielkoscXMagazynu;
	}
	public int getWielkoscYMagazynu() {
		return wielkoscYMagazynu;
	}
	public void setWielkoscYMagazynu(int wielkoscYMagazynu) {
		this.wielkoscYMagazynu = wielkoscYMagazynu;
	}
	public int getxWozka() {
		return xWozka;
	}
	public void setxWozka(int xWozka) {
		this.xWozka = xWozka;
	}
	public int getyWozka() {
		return yWozka;
	}
	public void setyWozka(int yWozka) {
		this.yWozka = yWozka;
	}
	public int getzWozka() {
		return zWozka;
	}
	public void setzWozka(int zWozka) {
		this.zWozka = zWozka;
	}
	
	
	
}