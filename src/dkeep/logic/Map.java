package dkeep.logic;

public class Map
{
	//Attributes
	private int width, height;		//for size restriction purposes
	private char[][] map;

	//Constructor
	public Map(int width, int height, char[][] map)
	{
		this.width = width;
		this.height = height;
		this.map = map;
	}

	//methods

	public void printMap()
	{
		for(int i=0; i< map.length;i++)
		{
			for(int j=0; j<map[i].length;j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}		
	}

	/*
	 * @brief checks if ent c 	an move or if the projected movement is against a wall
	 * @param direction - direction in which the entity is moving
	 * @param ent		- entity which movement is being tested
	 * @return false if walking into wall, true otherwise
	 */
	public boolean canMove(Coord coord)
	{
		if(getChar(coord) == 'x')
			return false;
		else
			return true;
	}

	public void setChar(Coord coord, char newChar)
	{
		map[coord.getX()][coord.getY()] = newChar;
	}

	public char getChar(Coord coord)
	{
		return map[coord.getX()][coord.getY()];
	}

	public boolean isDangerous(Coord coord, char symb)
	{
		Coord c1 = new Coord(coord.getX(),coord.getY());
		Coord c2 = new Coord(coord.getX(),coord.getY()+1);
		Coord c3 = new Coord(coord.getX()-1,coord.getY());
		Coord c4 = new Coord(coord.getX(),coord.getY()-1);
		
		
		if(		getChar(c1) == symb ||
				getChar(c2) == symb ||
				getChar(c3) == symb ||
				getChar(c4) == symb)
			return true;
		else return false;
	}
}
