package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.GuardType;
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
	
	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO: return 'H';
		case HERO_WITH_KEY:return 'K';	case HERO_WITH_CLUB:return 'A';
		case GUARD:return 'G';			case GUARD_SLEEP:return 'g';
		case WALL:return 'X';			case CLUB_ON_KEY:return '$';
		case OGRE:return 'o';			case OGRE_ON_KEY:return '$';
		case OGRE_WEAPON:return '*';		case OGRE_STUNED:return '8';
		case LEVER:return 'k';			case KEY:return 'k';
		case DOOR_CLOSED:return 'i'; 	case DOOR_OPEN:return 'S';
		case CLEAR_SPACE:
		default:return ' ';
		}
	}

	public static void printMap(Symbol[][] map) {
		for(int i = 0 ; i < map.length;i++) {
			for(int j = 0; j < map[0].length; j++) {
				System.out.print(symbolToChar(map[i][j]) + " ");
			}
			System.out.println();
		}
	}

	
	public static void main(String[] args){
		Game game = new Game(GuardType.SUSPICIOUS,0,2);
		
		Cmd cmd = Cmd.START;						
		Scanner scan = new Scanner(System.in);
		boolean firstCall = true;
		
		do {
			if(!firstCall) {
				cmd = validChar(scan);
			}
			firstCall = false;
			printMap(game.getSymbolMap());
		}while(!game.gameEnded());
		
		System.out.println(game.endingMessage());
		scan.close();
	}
}
