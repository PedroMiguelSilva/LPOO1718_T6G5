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
		for(int i=0; i<10;i++) 
			for(int j=0; j<10;j++) {
				System.out.print(matrix[i][j] + " ");
				if(j== 9) {
					System.out.println();
				}
		}
	}
}