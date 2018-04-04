package dkeep.logic;

import java.util.ArrayList;

public class Map
{
	//Attributes
	private int width, height;
	private Cell[][] map;

	private Cell[][] getClearMap(int w, int h) {
		Cell[][] temp = new Cell[w][h];

		for(int i = 0 ; i < h; i++){
			for(int j = 0; j < w; j++){
				Clear tempClearTop = new Clear(i,j);
				Clear tempClearBot = new Clear(i,j);
				Cell cellClear = new Cell(tempClearTop,tempClearBot);
				temp[i][j] = cellClear;
			}

		}
		return temp;
	}

	private static Symbol charToSymbol(char charSymb) {
		switch(charSymb) {
		case 'X':
			return Symbol.WALL;
		case 'i':
			return Symbol.DOOR_CLOSED;
		case 'k':
			return Symbol.KEY;
		case 'o':
			return Symbol.OGRE;
		case 'H':
			return Symbol.HERO;
		default: return Symbol.CLEAR_SPACE;
		}
	}

	private static Symbol[][] charMapToSymbolMap(char[][] charMap){
		int h = charMap.length, w = charMap[0].length; 
		Symbol[][] result = new Symbol[h][w];
		for(int x = 0; x < h; x++) {
			for(int y = 0 ; y < w ; y++) {
				result[x][y] = charToSymbol(charMap[x][y]);
			}
		}
		return result;
	}

	private void addEntitiesToMap(ArrayList<Entity> entities) {
		for(Entity ent : entities){
			if(ent instanceof Interactive || ent instanceof Neutral) {
				map[ent.getX()][ent.getY()].setBot(ent);
			}else {
				map[ent.getX()][ent.getY()].setTop(ent);
			}
		}
	}
	
	private void setWalls(){
		for(int i = 0; i < height ; i++) {
			for(int j = 0 ; j < width ;j++ ) {
				Clear clearTop = new Clear(i,j);
				Wall wallBot = new Wall(i,j);
				Cell wallCell = new Cell(clearTop,wallBot);
				if(i == 0 || j == 0 || i == (height-1) || j == (width-1)) {
					map[i][j] = wallCell;
				}
			}
		}
	}
	
	public Symbol[][] getSymbolMap(){
		Symbol[][] result = new Symbol[height][width];
		
		for(int i = 0 ; i < height ; i++) {
			for(int j = 0 ; j < width ; j++) {
				result[i][j] = map[i][j].getEnt().getSymb();
			}
		}
		return result;
	}
	
	public Map(int w, int h, ArrayList<Entity> entities)
	{
		this.width = w;
		this.height = h;

		map = getClearMap(w,h);
		setWalls();
		addEntitiesToMap(entities);
	}

	//conseguir um construtor atraves de um array de simbolos
	public Map(Symbol[][] bluePrint) {
		this.width = bluePrint.length;
		this.height = bluePrint[0].length;
		
		Cell[][] result = getClearMap(width,height);

		for(int x = 0; x < height; x++) {
			for(int y = 0; y < width ;y++) {
				addElementToMap(result,bluePrint,new Coord(x,y));
			}
		}
		this.map = result;
	}

	public Map(char[][] charMap) {
		this(charMapToSymbolMap(charMap));
	}
	
	private Entity entityToBeAdded(Symbol symb,Coord coord) {
		switch(symb) {
		case DOOR_CLOSED:
			return new Door(coord);
		case KEY:
			return new Key(coord);
		case OGRE:
			return new Ogre(coord,false);
		case HERO:
			return new Hero(coord,true);
		case WALL:
			return new Wall(coord);
		default:
			return new Clear(coord);
		}
	}
	
	private void addElementToMap(Cell[][] tempMap, Symbol[][] bluePrint,Coord coord) {
		Entity element = entityToBeAdded(bluePrint[coord.getX()][coord.getY()],coord);
		
		if(element instanceof Ogre || element instanceof Hero)
			tempMap[coord.getX()][coord.getY()].setTop(element);
		else
			tempMap[coord.getX()][coord.getY()].setBot(element);
	}

	/*
	 * @brief checks if ent c 	an move or if the projected movement is against a wall
	 * @param direction - direction in which the entity is moving
	 * @param ent		- entity which movement is being tested
	 * @return false if walking into wall, true otherwise
	 */
	public boolean canMove(Coord coord)
	{
		if(getBotEnt(coord).getSymb() == Symbol.WALL)
			return false;
		else
			return true;
	}

	public void setBotEnt(Coord coord, Entity bot)
	{
		map[coord.getX()][coord.getY()].setBot(bot);
	}

	public void setTopEnt(Coord coord, Entity top) {
		map[coord.getX()][coord.getY()].setTop(top);
	}

	public Entity getTopEnt(Coord coord){
		return map[coord.getX()][coord.getY()].getTop();
	}

	public Entity getBotEnt(Coord coord){
		return map[coord.getX()][coord.getY()].getBot();
	}

	/*
	 * @brief returns the entity on top, returns entity on bot if top is null
	 */
	public Entity getEnt(Coord coord) {
		if(getTopEnt(coord).getSymb() == Symbol.CLEAR_SPACE)
			return getBotEnt(coord);
		else
			return getTopEnt(coord);
	}

	public boolean outOfBounds(Coord coord, Coord outOfBounds) {
		if(coord.getX() < 0 || coord.getX() > outOfBounds.getX())
			return true;
		if(coord.getY() < 0 || coord.getY() > outOfBounds.getY())
			return true;

		return false;
	}

	public void move(Entity ent, Coord coord) {
		//verifica se ha uma parede no nivel de baixo
		if(getEnt(coord).getSymb() == Symbol.WALL)
			return;

		//passar o local anterior dele para limpo caso o objeto que estiver lá for ele
		if(this.getTopEnt(ent.getCoord()).getSymb() == ent.getSymb()) {
			Clear temp = new Clear(ent.getCoord());
			this.setTopEnt(ent.getCoord(), temp);
		}

		//coloca lo no sitio para onde ele quer ir
		this.setTopEnt(coord, ent);

		//atualizar o valor das suas coordenadas novas
		ent.setCoord(coord);

	}

	public Cell[][] getMap(){
		return this.map;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}
	
	public boolean isSymbolInCoord(Coord coord, Symbol[] symbArray) {
		Symbol temp = getEnt(coord).getSymb();
		for(int i = 0; i < symbArray.length;i++) {
			if(temp == symbArray[i])
				return true;
		}
		return false;
	}
}
