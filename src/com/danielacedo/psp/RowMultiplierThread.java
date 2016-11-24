package com.danielacedo.psp;

import javax.sql.RowSet;

public class RowMultiplierThread extends Thread{
	
	private int[][] rowMatrix;	//Row to multiply
	private int[][] columnMatrix; //Target matrix for multiplication
	private int from;
	private int until;
	
	private Matrix targetMatrix; //Matrix to add the row to
	
	public RowMultiplierThread(int from, int until, Matrix targetMatrix, int[][] rowMatrix, int[][] columnMatrix){
		this.targetMatrix = targetMatrix;
		this.rowMatrix = rowMatrix;
		this.columnMatrix = columnMatrix;
		this.from = from;
		this.until = until;
	}
	
	@Override
	public void run(){
		for(int i = from; i < until; i++){
			targetMatrix.setRow(i, multiplyRow(rowMatrix[i], columnMatrix));
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
}
