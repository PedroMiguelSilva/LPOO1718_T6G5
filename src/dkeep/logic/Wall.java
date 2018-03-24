package dkeep.logic;

public class Wall extends Neutral{
	private Integer one;

	public Wall(int startX, int startY) {
		super(startX, startY, Symbol.WALL);
		one = new Integer(1);
	}
	
	public Wall(Coord coord) {
		super(coord.getX(),coord.getY(),Symbol.WALL);
		one = new Integer(1);
	}
}
