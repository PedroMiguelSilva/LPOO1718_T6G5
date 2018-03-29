package dkeep.logic;

abstract public class Entity
{
	//Attributes
	private Coord coord;
	private Symbol symb;
		
	
	//Constructor
	public Entity(int startX, int startY, Symbol startSymb)
	{
		this.coord = new Coord(startX,startY);
		symb = startSymb;
	}
	
	
	//Methods
	public int getX()
	{
		return coord.getX();
	}
	
	public int getY()
	{
		return coord.getY();
	}
	
	public Symbol getSymb()
	{
		return this.symb;
	}
	
	public void setSymb(Symbol symb)
	{
		this.symb = symb;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void setCoord(Coord newCoord) {
		this.coord = newCoord;
	}
}
