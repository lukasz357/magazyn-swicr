package siwc.magazyn.dto;

public class PoleTO {

	Integer id;
	TowarTO towar;
	
	int x;
	int y;
	
	boolean isPunktOdbioru;
	boolean isBox;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TowarTO getTowar() {
		return towar;
	}
	public void setTowar(TowarTO towar) {
		this.towar = towar;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isPunktOdbioru() {
		return isPunktOdbioru;
	}
	public void setPunktOdbioru(boolean isPunktOdbioru) {
		this.isPunktOdbioru = isPunktOdbioru;
	}
	public boolean isBox() {
		return isBox;
	}
	public void setBox(boolean isBox) {
		this.isBox = isBox;
	}
	
}
