package dkeep.logic;

import java.util.ArrayList;

public class Lever extends Interactive
{
	//Attributes
	private boolean isActive;
	private int xPos[];
	private int yPos[];
	
	//Constructor
	public Lever(int startX, int startY, char startSymb,int newXPos[],int newYPos[])
	{
		super(startX,startY,startSymb);
		isActive = false;
		this.xPos = newXPos;
		this.yPos = newYPos;
	}
	
	//Methods
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		for(int i = 0; i < xPos.length;i++)
		{
			Interactive current = interactives.get(i);
			if(current instanceof Door && current.getX() == xPos[i] && current.getY() == yPos[i])
			{
				((Door) current).toggleDoor(map);
			}
		}
		
		//toggle the lever
		isActive = !isActive;
	}
}
