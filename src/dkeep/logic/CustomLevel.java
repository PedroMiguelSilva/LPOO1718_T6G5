package dkeep.logic;

/** Represents the Custom Level
 * @version 1.0
 * @since 1.0
 */
public class CustomLevel {
	private Symbol[][] map;
	private int width;
	private int height;
	
	/** Create Default Level 7 by 7
	 */
	public CustomLevel() {
		this(7,7);
	}
	
	/** Create a Custom Level with specified width and height
	 * @param w
	 * 			width of the map
	 * @param h
	 * 			height of the map
	 */
	public CustomLevel(int w, int h) {
		this.width = w;
		this.height = h;
		this.map = new Symbol[h][w];
		initMap(); 
	} 
	
	/** Add element to the map
	 * @param symb
	 * 			Symbol of the element to be added
	 * @param x
	 * 			x coordinate where the element should go
	 * @param y
	 * 			y coordinate where the element should go
	 */
	public void editMap(Symbol symb, int x, int y) {
		if(!validPosition(symb,x,y))
			return;
		
		map[x][y] = symb;
	}
	
	/** @return Map of CostumeLevel
	 */
	public Symbol[][] getMap(){
		return this.map;
	}
	
	/** @return if the current map is a valid one
	 */
	public boolean isValid() {
		if(numEnt(Symbol.HERO) != 1)
			return false;
		if(numEnt(Symbol.DOOR_CLOSED) != 1)
			return false;
		if(numEnt(Symbol.KEY) != 1)
			return false;
		int nOgre = numEnt(Symbol.OGRE);
		if(nOgre < 1 )
			return false;
		if(nOgre > 4)
			return false;
		return true;
	}
	
	private void initMap() {
		for(int i = 0 ; i < height ; i++) {
			for(int j = 0; j < width ; j++) {
				if(i == 0 || j == 0 || i == (height-1) || j == (width-1)) {
					map[i][j] = Symbol.WALL;
				}
				else {
					map[i][j] = Symbol.CLEAR_SPACE;
				}
			}
		}
	}
	
	
	private boolean validPosition(Symbol symb, int x, int y) {
		if(symb != Symbol.DOOR_CLOSED)
			return !outOfBounds(x,y);
		
		if(outOfBounds(x,y) && !isACorner(x,y)) {
			return true;
		}
		else
			return false;
	}
	
	private boolean isACorner(int x, int y) {
		if(x == 0 && y == 0)
			return true;
		else if(x == 0 && y == width-1)
			return true;
		else if(x == height-1 && y == width-1)
			return true;
		else if(x == height-1 && y == 0)
			return true;
		else return false;
	}
	
	private boolean outOfBounds(int x,int y) {
		return x == 0 || y == 0 || y == (width -1) || x == (height-1);
	}
	
	private int numEnt(Symbol symb) {
		int count = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width ; j++) {
				if(map[i][j] == symb)
					count++;
			}
		}
		return count;
	}
}
