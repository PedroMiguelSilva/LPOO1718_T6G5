package dkeep.logic;

public abstract class Movable extends Entity{
	
	protected Symbol[] cantWalkInto;
	
	public Movable(Coord pos,Symbol symb) {
		super(pos,symb);
	}
}
