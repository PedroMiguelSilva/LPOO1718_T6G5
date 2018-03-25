package dkeep.logic;

public class Clear extends Neutral{

	public Clear(int startX, int startY) {
		super(startX, startY, Symbol.CLEAR_SPACE);
	}
	
	public Clear(Coord coord) {
		super(coord.getX(),coord.getY(),Symbol.CLEAR_SPACE);
	}

}
