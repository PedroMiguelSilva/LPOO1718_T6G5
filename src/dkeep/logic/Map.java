package dkeep.logic;

import java.util.ArrayList;

public class Map
{
	//Attributes
	private int width, height;		//for size restriction purposes
	//private char[][] map;
	private Cell[][] map;

	//ao construir tenho de criar cells vazias (clear) em todos os locais que nao tenha nenhum objecto (criar so com clear e depois ir atualizando)
	public Map(int w, int h, ArrayList<Entity> entities)
	{
		this.width = w;
		this.height = h;
		
		map = new Cell[width][height];
		
		//initialize every value of map with clear
		for(int i = 0 ; i < height; i++){
			for(int j = 0; j < width; j++){
				Clear tempClearTop = new Clear(i,j);
				Clear tempClearBot = new Clear(i,j);
				Wall tempWall = new Wall(i,j);
				Cell cellWall = new Cell(tempClearTop,tempWall);
				Cell cellClear = new Cell(tempClearTop,tempClearBot);
				
				if(i == 0 || j == 0 || i == (height-1) || j == (width-1)) {
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
	
	public boolean isDangerous(Coord coord, Symbol symb, int xMax, int yMax)
	{
		if(symb == Symbol.GUARD_SLEEP)
			return false;
		
		Coord c1 = new Coord(coord.getX(),coord.getY());
		Coord c2 = new Coord(coord.getX(),coord.getY()+1);
		Coord c3 = new Coord(coord.getX()-1,coord.getY());
		Coord c4 = new Coord(coord.getX(),coord.getY()-1);

		if(!outOfBounds(c1,xMax,yMax) && getTopEnt(c1).getSymb() == symb)
			return true;
		
		if(!outOfBounds(c2,xMax,yMax) && getTopEnt(c2).getSymb() == symb)
			return true;
		
		if(!outOfBounds(c3,xMax,yMax) && getTopEnt(c3).getSymb() == symb)
			return true;
		
		if(!outOfBounds(c4,xMax,yMax) && getTopEnt(c4).getSymb() == symb)
			return true;
		
		return false;
	}

	public void move(Entity ent, Coord coord) {
		//verifica se ha uma parede no nivel de baixo
		if(getEnt(coord).getSymb() == Symbol.WALL)
			return;

		//passar o local anterior dele para limpo
		Clear temp = new Clear(ent.getCoord());
		this.setTopEnt(ent.getCoord(), temp);

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
}
