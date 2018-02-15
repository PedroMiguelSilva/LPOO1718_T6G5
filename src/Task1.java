import java.util.Scanner;

public class Task1
{
	public static void main(String args[]) {
		char matrix [][] = {
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

		printMatrix(matrix);
		char quit = 'q';
		char current;
		int xHold = 1;
		int yHold = 1;
		int xHnew = 1;
		int yHnew = 1;
		
		
		do {
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
			
			if (validMove(matrix, xHnew, yHnew) ) {
				matrix[xHold][yHold] = ' ';
				matrix[xHnew][yHnew] = 'h';
				xHold = xHnew;
				yHold = yHnew;
				
				printMatrix(matrix);
			}
			else {
				xHnew = xHold;
				yHnew = yHold;
			}
			

		}while(current != quit);
	}

	public static void printMatrix(char matrix[][] ) {
		for(int i=0; i<10;i++) 
			for(int j=0; j<10;j++) {
				System.out.print(matrix[i][j] + " ");
				if(j== 9) {
					System.out.println();
				}
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
	
	public static boolean validMove(char matrix [][], int x, int y) {
		if( matrix[x][y] == 'x' || matrix [x][y] == 'i')
			return false;
		return true;
		
	}

}