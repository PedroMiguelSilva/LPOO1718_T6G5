package dkeep.logic;

public class Cell {
	private Entity top;		//movable objects
	private Entity bot;		//fixed objects
	
	public Cell(Entity top, Entity bot) {
		this.top = top;
		this.bot = bot;
	}
	
	public Entity getTop() {
		return top;
	}
	
	public Entity getBot() {
		return bot;
	}
	
	public void setBot(Entity bot) {		
		this.bot = bot;
	}
	
	public void setTop(Entity top) {
		this.top = top;
	}
}
