package dkeep.logic;

public class Ogre extends Enemy
{
	private boolean hasClub;
	private boolean isStun;
	private int roundsStun;
	private Club weapon;
	private Symbol[] cantWalkInto;
	
	//Constructor
	public Ogre(int startX, int startY,boolean hasClub){
		super(startX,startY,Symbol.OGRE);
		if(hasClub) {
			Club temp = new Club(startX,startY+1);
			this.weapon = temp;
		}
		isStun = false;
		roundsStun = 0;
		this.hasClub = hasClub;
		Symbol[] temp = {Symbol.WALL,Symbol.DOOR_CLOSED,Symbol.DOOR_OPEN};
		cantWalkInto = temp;
	}
	
	public boolean getIsStun() {
		return isStun;
	}

	public void stun() {
		isStun = true;
		roundsStun = 3;
		this.setSymb(Symbol.OGRE_STUNED);
	}
	
	//Methods
	private void updateStunStatus() {
		if(isStun && roundsStun > 1) {
			roundsStun -=1;
		}			
		else if(roundsStun == 1) {
			roundsStun = 0;
			isStun = false;
		}
	}
	
	private void moveOgre(Map map,Coord newCoord) {
		if(!isStun) {
			map.move(this, newCoord);
			this.setSymb(Symbol.OGRE);
		}
			
		if(hasClub)
			weapon.swing(map,this);
	}
	
	public void move(Map map)
	{
		Coord newCoord;

		do
		{
			newCoord = this.getCoord().getRandomAdjacentCoord();
		}while(map.isSymbolInCoord(newCoord, cantWalkInto));

		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.OGRE_ON_KEY);
		}		
		
		updateStunStatus();
		moveOgre(map,newCoord);
	}
}
