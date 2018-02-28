package dkeep.logic;

public class Guard extends Enemy
{
	//Attributes
	char xPos[];
	char yPos[];
	int guardIndex;
	
	//Constructor
	public Guard(int startX, int startY, char startSymb, char xPos[], char yPos[])
	{
		super(startX,startY,startSymb);
		this.xPos = xPos;
		this.yPos = yPos;
		guardIndex = 0;
	}

	//Methods
	public void move(Map map)
	{
		//local variables
		int x = this.xPos[guardIndex];
		int y = this.yPos[guardIndex];
		
		//delete previous location
		map.setChar(x, y, ' ');
		
		//update index
		if(guardIndex == xPos.length - 1)
		{
			guardIndex = 0;
		}
		else
		{
			guardIndex++;
		}

		x = this.xPos[guardIndex];
		y = this.yPos[guardIndex];
		
		//print new location
		map.setChar(x, y, this.getSymb());
	}
}
