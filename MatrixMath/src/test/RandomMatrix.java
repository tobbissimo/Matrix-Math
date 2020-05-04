package test;

import java.util.Random;

public class RandomMatrix {
	private int[][] matrix;
	private Random rnd;
	
	public RandomMatrix(int rows) {
        matrix = new int[rows][rows];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if(i == j) {
                    matrix[i][j] = 0;
                } else {
                    rnd = new Random();
                    matrix[i][j] = rnd.nextInt(2);
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        for(int i = 0; i < matrix.length; i++) {
        	 for(int j = 0; j < matrix.length; j++) {
        		 System.out.print(matrix[i][j] + ";");
        	 }
        	 System.out.println();
        }
    }
}
