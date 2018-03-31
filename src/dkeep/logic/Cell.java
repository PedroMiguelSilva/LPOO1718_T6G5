package dkeep.logic;

/*
 * Represents a Cell
 * @version 1.0
 * @since 1.0
 */

public class Cell {
	private Entity top;
	private Entity bot;	
	
	/* Creates a Cell with specified bottom and top Entity
	 * @param top
	 * 			The Cell's top Entity
	 * @param bot
	 * 			The Cell's bottom Entity
	 */
	public Cell(Entity top, Entity bot) {
		this.top = top;
		this.bot = bot;
	}
	
	/* Gets the Cell's top Entity
	 * @return A Entity representing the Cell's top Entity 
	 */
	public Entity getTop() {
		return top;
	}
	
	/* Gets the Cell's bottom Entity
	 * @return A Entity representing the Cell's bottom Entity 
	 */
	public Entity getBot() {
		return bot;
	}
	
	/* Sets the Cell's bottom Entity
	 * @param bot
	 * 			Cell's bottom Entity
	 */
	public void setBot(Entity bot) {		
		this.bot = bot;
	}
	
	/* Sets the Cell's top Entity
	 * @param top
	 * 			Cell's top Entity
	 */
	public void setTop(Entity top) {
		this.top = top;
	}
}
