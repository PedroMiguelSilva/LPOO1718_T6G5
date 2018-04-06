package dkeep.logic;

public class Ogre extends Enemy
{
	private boolean hasClub;
	private boolean isStun;
	private int roundsStun;
	private Club weapon;
	private Symbol[] cantWalkInto;
	
	//Constructor
	public Ogre(Coord pos,boolean hasClub){
		super(pos,Symbol.OGRE);
		if(hasClub) {
			Coord club = new Coord(pos);
			Club temp = new Club(club);
			this.weapon = temp;
		}
		isStun = false;
		roundsStun = 0;
		this.hasClub = hasClub;
		Symbol[] temp = {Symbol.WALL,Symbol.DOOR_CLOSED,Symbol.DOOR_OPEN};
		cantWalkInto = temp;
	}
	
	/*
	public boolean getIsStun() {
		return isStun;
	}
	*/

	public void stun() {
		isStun = true;
		roundsStun = 3;
		this.setSymb(Symbol.OGRE_STUNED);
	}
	
	//Methods
	private void updateStunStatus() {
		if(roundsStun > 1) {
			roundsStun -=1;
		}			
		else{
			roundsStun = 0;
			isStun = false;
		}
	}
	
	private void moveOgre(Map map,Coord newCoord) {
		if(!isStun) {
			map.move(this, newCoord);
		}
		if(map.getBotEnt(newCoord).getSymb() != Symbol.KEY)
			this.setSymb(Symbol.OGRE);
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
		
		Symbol curr = this.getSymb();
		updateStunStatus();
		//curr = this.getSymb();
		moveOgre(map,newCoord);
		//curr = this.getSymb();
		if(hasClub)
			weapon.swing(map,this);
		//curr = this.getSymb();
	}
}
