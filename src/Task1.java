import java.util.Scanner;
import java.util.Random;

public class Task1
{
	public static void main2(String args[]) {
		char map1 [][] = {
				{'x','x','x','x','x','x','x','x','x','x'},
				{'x','h',' ',' ','i',' ','x',' ','g','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'x',' ','i',' ','i',' ','x',' ',' ','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x',' ','x','x','x','x',' ','x'},
				{'x',' ','i',' ','i',' ','x','k',' ','x'},
				{'x','x','x','x','x','x','x','x','x','x'}
		};

		char map2 [][] = {
				{'x','x','x','x','x','x','x','x','x'},
				{'i',' ',' ',' ','o','*',' ','k','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','h',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x','x','x','x','x','x','x'}
		};

		//initial settings
		char matrix[][] = map1;
		printMatrix(matrix);
		char quit = 'q';
		char current;
		int xHold = 1;
		int yHold = 1;
		int xHnew = 1;
		int yHnew = 1;
		int map = 1;
		boolean heroHasKey = false;
		boolean ogreHasKey = false;
		char cuRep= 'h';

		//guard movement
		char xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		char yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
		int guardMovIndex = 0;

		do 
		{
			current = validChar();

			switch(current) {
			case 'w' : xHnew = xHold - 1;
			break;

			case 'a' : yHnew = yHold - 1;
			break;

			case 's' : xHnew = xHold + 1;
			break;

			case 'd' : yHnew = yHold + 1;
			break;
			}

			//guard movement
			if(map == 1)
			{
				moveGuard(xPos,yPos,guardMovIndex,matrix);
				if(guardMovIndex == xPos.length -1)
				{
					guardMovIndex = 0;
				}
				else
					guardMovIndex++;
			}
			else {
				moveOgre(matrix,heroHasKey,map,ogreHasKey);
				deleteAst(matrix);
				moveWeapon(matrix, heroHasKey, map);
				
			
			}


			//verificar se o passo é valido. Se for move. Depois de mover verifica se 
			//é um k, um S ou se o Guarda esta perto e trata de cada uma das situaçoes
			//Se nao for nenhuma destas mantem-se
			if (validMove(matrix, xHnew, yHnew, heroHasKey, map))
			{
				if(matrix[xHnew][yHnew] == 'k') 
				{
					if(map == 1)
					{
						//open gates
						matrix[5][0]= 'S';
						matrix[6][0]= 'S';	
					}
					else {
						heroHasKey = true;
						cuRep= 'K';
						move(matrix,xHold, yHold, xHnew,yHnew, cuRep,ogreHasKey);
						xHold = xHnew;
						yHold = yHnew;
					}

				}
				else if(matrix[xHnew][yHnew] == 'i' && map == 2 && heroHasKey)
				{
					matrix[1][0] = 'S';
				}							
				else if(matrix[xHnew][yHnew] == 'S')
				{
					if(map == 1) 
					{
						matrix = map2;
						xHold = 7;
						yHold = 1;
						xHnew = 7;
						yHnew = 1;
						map = 2;
					}
					else {
						System.out.println("The hero has escaped. You Win");
						return;
					}
				}
				else
				{
					//normal movement
					move(matrix,xHold, yHold, xHnew,yHnew, cuRep,ogreHasKey);
					xHold = xHnew;
					yHold = yHnew;
				}
			}
			else {
				xHnew = xHold;
				yHnew = yHold;
			}


			printMatrix(matrix);

			//movimento do heroi acaba, testar guarda
			if(dangerNearBy(xHold,yHnew,matrix))
			{
				current = quit;
				System.out.println("Game Over");
			}


		}while(current != quit);
	}

	public static void moveGuard(char xPos[],char yPos[],int i,char matrix[][])
	{
		//delete previous position
		int x = xPos[i];
		int y = yPos[i];

		matrix[x][y] = ' ';

		//update postion

		if(i == xPos.length -1)
		{
			i = 0;
		}
		else
			i++;

		//print new position
		matrix[xPos[i]][yPos[i]] = 'g';

	}

	public static boolean dangerNearBy(int xHero, int yHero, char matrix[][])
	{
		if(matrix[xHero+1][yHero] == 'g' ||
				matrix[xHero][yHero+1] == 'g' ||
				matrix[xHero-1][yHero] == 'g' ||
				matrix[xHero][yHero-1] == 'g' ||
				matrix[xHero+1][yHero] == 'o' ||
				matrix[xHero][yHero+1] == 'o' ||
				matrix[xHero-1][yHero] == 'o' ||
				matrix[xHero][yHero-1] == 'o')
			return true;
		else
			return false;
	}



	public static char move(char matrix[][], int xHold, int yHold, int xHnew, int yHnew, char rep, boolean ogreHasKey)
	{
		char nextChar = matrix[xHnew][yHnew];

		if (matrix[xHnew][yHnew] == 'k' && rep == 'o')
		{
			matrix[xHnew][yHnew] = '$';
			matrix[xHold][yHold] = ' ';
			xHold = xHnew;
			yHold = yHnew;
		}

		else
		{
			//regular movement
			if(ogreHasKey)
			{
				matrix[xHold][yHold] = 'k';
			}
			else
			{
				matrix[xHold][yHold] = ' ';
			}
			matrix[xHnew][yHnew] = rep;
			xHold = xHnew;
			yHold = yHnew;
			//printMatrix(matrix);
		}

		return nextChar;
	}

	public static void printMatrix(char matrix[][] )
	{
		for(int i=0; i< matrix.length;i++)
		{
			for(int j=0; j<matrix[i].length;j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}		
	}

	public static char validChar() {
		System.out.println("Use W,A,S,D to move! \n");
		Scanner scan = new Scanner(System.in);
		char N;
		do {
			N = scan.next().charAt(0);
		} while(N != 'a' && N != 'w'&& N != 's'&& N != 'd'&& N!= 'q');

		return N;
	}

	public static boolean validMove(char matrix [][], int x, int y, boolean heroHasKey, int map) {

		if(matrix[x][y] == 'i' && map == 2 && heroHasKey)
		{
			matrix[x][y] = 'S';
			return false;
		}
		if( matrix[x][y] == 'x' || matrix[x][y] == 'i')
			return false;

		return true;

	}

	public static int findOgreX(char matrix[][]) {
		for (int i=0; i<matrix.length ; i++) {
			for (int j=0; j<matrix[0].length; j++) {
				if(matrix[i][j] =='o' || matrix[i][j] == '$') 
					return i;
			}
		}
		return 0;
	}

	public static int findOgreY(char matrix[][]) {

		for (int i=0; i<matrix.length ; i++) {
			for (int j=0; j<matrix[0].length; j++) {
				if(matrix[i][j] =='o' || matrix[i][j] == '$') 
					return j;
			}
		}
		return 0;
	}
	
	public static void deleteAst(char matrix[][]) {

		for (int i=0; i<matrix.length ; i++) {
			for (int j=0; j<matrix[0].length; j++) {
				if(matrix[i][j] =='o' || matrix[i][j] == '$') 
					matrix[i][j] = ' ';
			}
		}
	}

	public static void moveOgre(char matrix[][],boolean heroHasKey,int map, boolean ogreHasKey) {

		int xPosOld = findOgreX(matrix);
		int yPosOld = findOgreY(matrix);
		int xPos,yPos;

		Random  rand = new Random();	

		do {
			xPos = findOgreX(matrix);
			yPos = findOgreY(matrix);
			int move = rand.nextInt(4);

			switch(move) {
			case 0 : xPos++;
			break;
			case 1 : yPos++;
			break;

			case 2 : xPos--;
			break;

			case 3 : yPos--;
			break;

			}
		}while(!validMove(matrix,xPos,yPos,heroHasKey,map));


		move(matrix, xPosOld, yPosOld,xPos,yPos,'o',ogreHasKey);

		if(matrix[1][7] == ' ' && heroHasKey == false){
			matrix[1][7] = 'k';
		}


	}

	public static void moveWeapon(char matrix[][], boolean heroHasKey, int map) {
		int xPos,yPos;
		Random  rand = new Random();	

		do {
			xPos = findOgreX(matrix);
			yPos = findOgreY(matrix);
			int move = rand.nextInt(4);

			switch(move) {
			case 0 : xPos++;
			break;
			case 1 : yPos++;
			break;

			case 2 : xPos--;
			break;

			case 3 : yPos--;
			break;

			}
		}while(!validMove(matrix,xPos,yPos,heroHasKey,map));

		if(xPos == 1 && yPos == 7) {
			matrix[xPos][yPos] = '$';

		}
		else
			matrix[xPos][yPos] = '*';


		if(matrix[1][7] == ' ' && heroHasKey == false){
			matrix[1][7] = 'k';
		}


	}

}