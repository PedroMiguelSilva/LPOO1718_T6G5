package dkeep.logic;

abstract public class Enemy extends Entity implements Move
{
	//Attributes
	
	
	//Constructor
	public Enemy(int startX, int startY, Symbol startSymb)
	{
		super(startX,startY,startSymb);
	}
	
	public int getNextIndex(int index, int size, boolean reverse) {
		if(reverse)
			index -= 1;
		else
			index += 1;
		
		if(index == -1)
			return size-1;
		else if(index == size)
			return 0;
		else
			return index;
		
	}
}
