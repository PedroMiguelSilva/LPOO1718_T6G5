package dkeep.cli;

import dkeep.logic.Game;
import java.util.Scanner;

public class Start
{
	/*
	 * Returns one of the chars that are used for movement (WASD)
	 *
	 * @return either W, A, S or D
	 */
	public static char validChar()
	{
		System.out.println("Use W,A,S,D to move! \n");
		Scanner scan = new Scanner(System.in);
		char N;
		do
		{
			N = scan.next().charAt(0);
		} while(N != 'a' && N != 'w'&& N != 's'&& N != 'd'&& N!= 'q');

		return N;
	}



	public static void main(String[] args)
	{
		Game game = new Game();
		game.getLevel().update();

		boolean end = false;
		char current;

		game.getMap().printMap();

		//enter game cycle
		while(!end)
		{
			//read user input
			current = validChar();

			if(current == 'q')
			{
				System.out.println("Quitting");
				break;
			}
			//update the game
			end = game.update(current);
			

			//print the current state of the game to console
			game.getMap().printMap();
		}

		//game ended, do whatever necessary
	}
}
