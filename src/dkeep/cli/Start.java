package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.Map;
import dkeep.logic.GuardType;
import dkeep.logic.Entity;
import dkeep.logic.Coord;
import dkeep.logic.Clear;
import dkeep.logic.Cmd;
import dkeep.logic.Symbol;

import java.util.Scanner;

public class Start
{

	public static Cmd parseCharToCmd(char n) {
		switch(n) {
		case 'a':
			return Cmd.LEFT;
		case 's':
			return Cmd.DOWN;
		case 'd':
			return Cmd.RIGHT;
		case 'w':
			return Cmd.UP;
		default:
			return Cmd.QUIT;
		}
	}

	public static void sendFinalMessage(Game game)
	{
		if(game.isGameOver())
		{
			System.out.println("Game Over");
		}
		else if(game.getWonGame())
		{
			System.out.println("Congratz, you won game");
		}
		else if(game.getQuit())
		{
			System.out.println("Quit Game");
		}
	}
	
	private static char minimize(char N) {
		if(N == 'A')return 'a';
		if(N == 'W')return 'w';
		if(N == 'S')return 's';
		if(N == 'D')return 'd';
		if(N == 'Q')return 'q';
		return N;
	}
	/*
	 * @brief Returns one of the chars that are used for movement (WASD)
	 *
	 * @return either W, A, S or D
	 */
	public static Cmd validChar(Scanner scan)
	{
		System.out.println("Use W,A,S,D to move! \n");
		char N;
		do
		{
			N = scan.next().charAt(0);
			N = minimize(N);
		} while(N != 'a' &&
				N != 's' &&
				N != 'd' &&
				N != 'w' &&
				N != 'q');

		return parseCharToCmd(N);
	}

	public static Entity entToPrint(Map map, int i, int j) {
		Coord coord = new Coord(i,j);
		Entity entTop = map.getTopEnt(coord);
		Entity entBot = map.getBotEnt(coord);

		if(entTop instanceof Clear)
			return entBot;
		else
			return entTop;
	}

	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO: return 'H';
		case HERO_WITH_KEY:return 'K';	case HERO_WITH_CLUB:return 'A';
		case GUARD:return 'G';			case GUARD_SLEEP:return 'g';
		case WALL:return 'X';			case CLUB_ON_KEY:return '$';
		case OGRE:return 'o';			case OGRE_ON_KEY:return '$';
		case OGRE_WEAPON:return '*';	case OGRE_STUNED:return '8';
		case LEVER:return 'k';			case KEY:return 'k';
		case DOOR_CLOSED:return 'i'; 	case DOOR_OPEN:return 'S';
		case CLEAR_SPACE:
		default:return ' ';
		}
	}

	public static void printMap(Map map) {
		for(int i = 0 ; i < map.getHeight();i++) {
			for(int j = 0; j < map.getWidth(); j++) {
				Entity ent = entToPrint(map,i,j);
				char charToPrint = symbolToChar(ent.getSymb());
				System.out.print(charToPrint + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		Game game = new Game(GuardType.DRUNKEN,2,2);
		Cmd cmd = Cmd.START, quit = Cmd.QUIT;						
		Scanner scan = new Scanner(System.in);	

		printMap(game.getLevel().getMap());
		while(!(game.isGameOver() || game.getWonGame()) && cmd != quit) {
			cmd = validChar(scan);
			game.moveHero(cmd);
			if(!game.getWonGame())
				printMap(game.getLevel().getMap());
		}
		sendFinalMessage(game);
		scan.close();
	}
}
