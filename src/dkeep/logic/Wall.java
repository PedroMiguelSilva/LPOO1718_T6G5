package dkeep.logic;

public class Wall extends Neutral{
	
	public Wall(int startX, int startY) {
		super(new Coord(startX,startY),Symbol.WALL);
	}
	
	public Wall(Coord coord) {
		super(coord,Symbol.WALL);
	}
	

}
