package dkeep.logic;

public class CustomLevel {
	private Symbol[][] map;
	private int width;
	private int height;
	
	public CustomLevel() {
		this(6,6);
	}
	
	public CustomLevel(int w, int h) {
		this.width = w;
		this.height = h;
		this.map = new Symbol[h][w];
		initMap();
	}
	
	private void initMap() {
		for(int i = 0 ; i < height ; i++) {
			for(int j = 0; j < width ; j++) {
				if(i == 0 || j == 0 || i == (height-1) || j == (width-1)) {
					map[i][j] = Symbol.CLEAR_SPACE;
				}
				else {
					map[i][j] = Symbol.WALL;
				}
			}
		}
	}
	
	
	public void editMap(Symbol symb, int x, int y) {
		map[x][y] = symb;
	}
	
	public Symbol[][] getMap(){
		return this.map;
	}
	
	public boolean isValid() {
		if(!hasAllComponents() || !isPossible())
			return false;
		else
			return true;
	}
	
	private boolean isPossible() {
		//ALGORITMO DE CALL PARA LABIRINTO ??
		return true;
	}
	
	private boolean hasAllComponents() {
		Symbol[] list = {
				Symbol.DOOR_CLOSED,
				Symbol.KEY,
				Symbol.OGRE,
				Symbol.HERO
				};
		
		for(int i = 0; i < list.length; i++) {
			if(!hasEntityWithSymbol(list[i]))
				return false;
		}
		return true;
	}
	
	private boolean hasEntityWithSymbol(Symbol symb) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width ; j++) {
				if(map[i][j] == symb)
					return true;
			}
		}
		return false;
	}
}
