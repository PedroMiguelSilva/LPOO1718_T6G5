package dkeep.logic;

public class Coord {

	private int x;
	private int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void incX() {
		this.x +=1;
	}
	
	public void incY() {
		this.y += 1;
	}
	
	public void decX() {
		this.x -= 1;
	}
	
	public void decY() {
		this.y -= 1;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setCoord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(getX() == 0 && getY() == 0)
			return true;
		Coord coord = (Coord) obj;
		if(coord.getX() == 0 && coord.getY() == 0)
			return true;
		
		if(this.getX() == coord.getX() && this.getY() == coord.getY())
			return true;
		else
			return false;
	}
}
