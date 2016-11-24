package com.danielacedo.psp;


public class Test {

	public static void main(String[] args) {
		int[][] mat1 = new int[1000][1000];
		
		mat1 = initializeMatrix(mat1);
		
		Matrix matrix1 = new Matrix(mat1);
		Matrix matrix2 = new Matrix(mat1);
		
		//Short initialization for watching the result
		
		/*Matrix matrix1 = new Matrix(new int[][]{
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
			{1,1,2,3,4,5,6,7},
		});
		
		Matrix matrix2 = new Matrix(new int[][]{
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
				{1,1,2,3,4,5,6,7},
		});*/
		
		Matrix result = new Matrix(matrix1.getnRows(), matrix2.getnCol());

		long time = System.currentTimeMillis();
		
		//singleThreadMethod(matrix1, matrix2, result);
		
		threadingMethod(matrix1, matrix2, result);
		
		System.out.println((System.currentTimeMillis()-time)+" ms");	//Show time taken 
		
		//System.out.println(result);
	}	
	
	
	private static void singleThreadMethod(Matrix matrix1, Matrix matrix2, Matrix result){
		for(int i = 0; i < matrix1.getnRows(); i++){
			result.setRow(i, multiplyRow(matrix1.getRow(i), matrix2.getInnerMatrix()));
		}
	}
	
	private static void threadingMethod(Matrix matrix1, Matrix matrix2, Matrix result){
		int nThreads = 4; //Number of threads we want it to be sliced into
		
		int firstRow = 0; //Row for the thread to start calculating
		
		RowMultiplierThread threads[] = new RowMultiplierThread[nThreads];
		for(int i = 0; i < nThreads; i++){
			threads[i] = new RowMultiplierThread(firstRow, firstRow+(matrix1.getnRows()/nThreads), result, matrix1.getInnerMatrix(), matrix2.getInnerMatrix());
			threads[i].start();
			firstRow += matrix1.getnRows()/nThreads;
		}
		
		for(int i = 0 ; i < threads.length; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int[] multiplyRow(int[] row, int[][] matrix){
		int value = 0;
		int[] resultRow = new int[matrix[0].length];
		
		for(int k = 0; k<matrix[0].length; k++){
			for(int i = 0; i < row.length; i++){
				value+=row[i] * matrix[i][k];
			}
			
			resultRow[k] = value;
			value = 0;
		}
		
		return resultRow;
	}
	
	private static int[][] initializeMatrix(int[][] matrix){
		long cont = 0;
		
		for(int i = 0; i<matrix.length; i++){
			for(int j = 0; j<matrix[i].length; j++){
				matrix[i][j] = i*j; 
			}
		}
		
		return matrix;
	}

}
