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
			for(int i = 0; i < getWielkoscXMagazynu(); i++)
				for(int j = 0; j < getWielkoscYMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						if(t.getKodTowaru().equals(kod) && !t.isZarezerwowany())
							list.add(tab[i][j].getTowar());
				}
		}
		
		return list;
	}
	
	public ArrayList<String>getDokladnePozycjeByKod(String kod) {
		ArrayList<String> list = new ArrayList<>();
		for(PoleTO[][] tab : pietra.values()){
			for(int i = 0; i < getWielkoscXMagazynu(); i++)
				for(int j = 0; j < getWielkoscYMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						if(t.getKodTowaru().equals(kod) && !t.isZarezerwowany())
							list.add("Regał "+tab[i][j].getNrRegalu()+":Pietro "+tab[i][j].getPietro()+":Pozycja "+tab[i][j].getPosition());
				}
		}
		
		return list;
	}
	
	public int getLiczbaWszystkichProduktow(){
		int liczba = 0;
		for(PoleTO[][] tab : pietra.values()){
			for(int i = 0; i < getWielkoscXMagazynu(); i++)
				for(int j = 0; j < getWielkoscYMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						liczba++;
				}
		}
		return liczba;
	}
	
	public ArrayList<PoleTO> getPolaZDostepnymiTowaramiByKod(String kod){
		ArrayList<PoleTO> list = new ArrayList<>();
		int nrPietra = 0;
		for(PoleTO[][] tab : pietra.values()){
			for(int i = 0; i < getWielkoscXMagazynu(); i++)
				for(int j = 0; j < getWielkoscYMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						if(t.getKodTowaru().equals(kod) && !t.isZarezerwowany()){
							tab[i][j].setPietro(nrPietra);
							list.add(tab[i][j]);
						}
				}
			nrPietra++;
		}
		
		return list;
	}
	public ArrayList<String> getInfoODostepnychTowarachByKod(String kod){
		ArrayList<String> list = new ArrayList<>();
		int nrPietra = 0;
		for(PoleTO[][] tab : pietra.values()){
			for(int i = 0; i < getWielkoscXMagazynu(); i++)
				for(int j = 0; j < getWielkoscYMagazynu(); j++){
					TowarTO t = tab[i][j].getTowar();
					if(t != null && t.getKodTowaru() != null)
						if(t.getKodTowaru().equals(kod) && !t.isZarezerwowany())
							list.add(nrPietra + ":"+tab[i][j].getNrRegalu()+":"+tab[i][j].getPosition());
				}
			nrPietra++;
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
