package dkeep.logic;

public abstract class Movable extends Entity{
	
	protected Symbol[] cantWalkInto;
	protected Symbol[] canWalkInto;
	
	public Movable(Coord pos,Symbol symb) {
		super(pos,symb);
	}
	
	protected boolean noPossibleMove(Map map, Coord coord) {
		for(int i = 0; i < canWalkInto.length;i++) {
			if(map.isNearBy(coord, canWalkInto[i]))
				return false;
		}

		return true;
	}
}
