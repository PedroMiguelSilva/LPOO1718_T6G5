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

	private static Symbol[][] charMapToSymbolMap(char[][] charMap, int w, int h){
		Symbol[][] result = new Symbol[h][w];
		Symbol add;
		for(int x = 0; x < h; x++) {
			for(int y = 0 ; y < w ; y++) {
				switch(charMap[x][y]) {
				case 'X':
					add = Symbol.WALL;
					break;
				case 'i':
					add = Symbol.DOOR_CLOSED;
					break;
				case 'k':
					add = Symbol.KEY;
					break;
				case 'o':
					add = Symbol.OGRE;
					break;
					
				case 'H':
					add = Symbol.HERO;
					break;
				default:
					add = Symbol.CLEAR_SPACE;
				}
				result[x][y] = add;
			}
		}
		return result;
	}

	//ao construir tenho de criar cells vazias (clear) em todos os locais que nao tenha nenhum objecto (criar so com clear e depois ir atualizando)
	public Map(int w, int h, ArrayList<Entity> entities)
	{
		this.width = w;
		this.height = h;

		map = new Cell[h][w];

		//initialize every value of map with clear
		for(int i = 0 ; i < h; i++){
			for(int j = 0; j < w; j++){
				Clear tempClearTop = new Clear(i,j);
				Clear tempClearBot = new Clear(i,j);
				Wall tempWall = new Wall(i,j);
				Cell cellWall = new Cell(tempClearTop,tempWall);
				Cell cellClear = new Cell(tempClearTop,tempClearBot);

				if(i == 0 || j == 0 || i == (h-1) || j == (w-1)) {
					map[i][j] = cellWall;
				}else {
					map[i][j] = cellClear;
				}

			}
		}

		for(Entity ent : entities){
			if(ent instanceof Interactive || ent instanceof Neutral) {
				map[ent.getX()][ent.getY()].setBot(ent);
			}else {
				map[ent.getX()][ent.getY()].setTop(ent);
			}
		}
	}

	//conseguir um construtor atraves de um array de simbolos
	public Map(int w, int h, Symbol[][] bluePrint) {
		this.width = w;
		this.height = h;
		
		Cell[][] result = getClearMap(w,h);

		for(int x = 0; x < h; x++) {
			for(int y = 0; y < w ;y++) {
				addElementToMap(result,bluePrint,x,y);
			}
		}
		this.map = result;
	}

	public Map(int w, int h, char[][] charMap) {
		this(w,h,charMapToSymbolMap(charMap,w,h));
	}
	
	private void addElementToMap(Cell[][] tempMap, Symbol[][] bluePrint,int x, int y) {
		
		switch(bluePrint[x][y]) {
		case WALL:
			Wall wall = new Wall(x,y);
			tempMap[x][y].setBot(wall);
			break;
		case DOOR_CLOSED:
			Door door = new Door(x,y);
			tempMap[x][y].setBot(door);
			break;
		case KEY:
			Key key = new Key(x,y);
			tempMap[x][y].setBot(key);
			break;
		case OGRE:
			Ogre ogre = new Ogre(x,y,false);
			tempMap[x][y].setTop(ogre);
			break;
		case HERO:
			Hero hero = new Hero(x,y,true);
			tempMap[x][y].setTop(hero);
			break;
		default:
		}
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

	public boolean outOfBounds(Coord coord, int xMax, int yMax) {
		if(coord.getX() < 0 || coord.getX() > xMax)
			return true;
		if(coord.getY() < 0 || coord.getY() > yMax)
			return true;

		return false;
	}

	public void move(Entity ent, Coord coord) {
		//verifica se ha uma parede no nivel de baixo
		if(getEnt(coord).getSymb() == Symbol.WALL)
			return;

		//passar o local anterior dele para limpo caso o objeto que estiver l� for ele
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
