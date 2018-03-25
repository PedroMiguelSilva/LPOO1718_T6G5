package dkeep.logic;

public class Wall extends Neutral{

	public Wall(int startX, int startY) {
		super(startX, startY, Symbol.WALL);
	}
	
	public Wall(Coord coord) {
		super(coord.getX(),coord.getY(),Symbol.WALL);
	}
}
