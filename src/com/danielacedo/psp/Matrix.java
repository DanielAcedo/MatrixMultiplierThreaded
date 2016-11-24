package com.danielacedo.psp;

public class Matrix {
	private int nCol;
	private int nRows;
	
	private int[][] innerMatrix;
	

	public Matrix(int[][] matrix){
		innerMatrix = matrix;
		this.nCol = matrix[0].length;
		this.nRows = matrix.length;
	}
	
	public Matrix(int row, int col){
		this.nRows = row;
		this.nCol = col;
		this.innerMatrix = new int[row][col];
	}
	
	public int getValue(int row, int col){
		return innerMatrix[row][col];
	}
	
	public void setValue(int row, int col, int value){
		innerMatrix[row][col] = value;
	}
	
	public int[][] getInnerMatrix() {
		return innerMatrix;
	}
	
	public void setRow(int index, int[] row){
		innerMatrix[index] = row;
	}
	
	public int[] getRow(int index){
		return innerMatrix[index];
	}
	
	public int getnCol() {
		return nCol;
	}

	public int getnRows() {
		return nRows;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < innerMatrix.length; i++){
			for(int j = 0; j < innerMatrix[i].length; j++){
				builder.append(innerMatrix[i][j]+" ");
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}
}
