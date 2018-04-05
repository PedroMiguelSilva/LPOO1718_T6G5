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
		if(numEnt(Symbol.HERO) != 1)
			return false;
		if(numEnt(Symbol.DOOR_CLOSED) != 1)
			return false;
		if(numEnt(Symbol.KEY) != 1)
			return false;
		int nOgre = numEnt(Symbol.OGRE);
		if(nOgre < 1 || nOgre > 4)
			return false;
		return true;
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
