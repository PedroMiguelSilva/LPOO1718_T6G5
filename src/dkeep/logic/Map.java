package dkeep.logic;

public class Map
{
	//Attributes
	private int width, height;
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
	public boolean canMove(int x, int y)
	{
		if(map[x][y] == 'x')
			return false;
		else
			return true;
	}

	public void setChar(int x, int y, char newChar)
	{
		map[x][y] = newChar;
	}

	public char getChar(int x, int y)
	{
		return map[x][y];
	}

	public boolean isDangerous(int x, int y, char symb)
	{
		if(		getChar(x+1,y) == symb ||
				getChar(x,y+1) == symb ||
				getChar(x-1,y) == symb ||
				getChar(x,y-1) == symb)
			return true;
		else return false;
	}
	public char [][] getMap(){
		return this.map;
	}
}
