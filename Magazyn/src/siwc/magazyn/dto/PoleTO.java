package siwc.magazyn.dto;

public class PoleTO {

	private Integer id;
	private TowarTO towar;
	private int nrRegalu;
	private String position;
	
	private int x;
	private int y;
	private int z;
	
	boolean isPunktOdbioru;
	boolean isBox;
	private boolean isMovable;
	
	public PoleTO(int x, int y) {
		this.x = x; this.y = y;
	}
	public PoleTO() {
	}
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
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getNrRegalu() {
		return nrRegalu;
	}
	public void setNrRegalu(int nrRegalu) {
		this.nrRegalu = nrRegalu;
	}
	public boolean isMovable() {
		return isMovable;
	}
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}
	
}
