package com.hqthai01.rubiksolver.model;

import com.hqthai01.rubiksolver.constants.RubikConstants;

/**
 * @author Thái Feb 1, 2015
 */
public class RubikCube implements RubikConstants {

	private static RubikCube instance;
	
	public static RubikCube getInstance() {
		synchronized (RubikCube.class) {
			if (instance == null)
				return instance = new RubikCube();
		}
		return instance;
	}

	public static int cubeSize;
	private int[][] faceFront;
	private int[][] faceBack;
	private int[][] faceUp;
	private int[][] faceBottom;
	private int[][] faceLeft;
	private int[][] faceRight;
	
	public int[][] getFaceUp() {
		return faceUp;
	}

	public void setFaceUp(int[][] faceUp) {
		this.faceUp = faceUp;
	}

	public int[][] getFaceBottom() {
		return faceBottom;
	}

	public void setFaceBottom(int[][] faceBottom) {
		this.faceBottom = faceBottom;
	}

	public int[][] getFaceLeft() {
		return faceLeft;
	}

	public void setFaceLeft(int[][] faceLeft) {
		this.faceLeft = faceLeft;
	}

	public int[][] getFaceRight() {
		return faceRight;
	}

	public void setFaceRight(int[][] faceRight) {
		this.faceRight = faceRight;
	}

	public int[][] getFaceFront() {
		return faceFront;
	}

	public void setFaceFront(int[][] faceFront) {
		this.faceFront = faceFront;
	}

	public int[][] getFaceBack() {
		return faceBack;
	}

	public void setFaceBack(int[][] faceBack) {
		this.faceBack = faceBack;
	}

	private RubikCube() {
		resetCube(cubeSize);
	}

	public void resetCube(int cubeSize) {
		faceFront = new int[cubeSize][cubeSize];
		faceBack = new int[cubeSize][cubeSize];
		faceUp = new int[cubeSize][cubeSize];
		faceBottom = new int[cubeSize][cubeSize];
		faceLeft = new int[cubeSize][cubeSize];
		faceRight = new int[cubeSize][cubeSize];
		
		for (int i = 0; i < cubeSize; i++) {
			for (int j = 0; j < cubeSize; j++) {
				faceFront[i][j] = FRONT;
				faceBack[i][j] = BACK;
				faceUp[i][j] = UP;
				faceBottom[i][j] = BOTTOM;
				faceLeft[i][j] = LEFT;
				faceRight[i][j] = RIGHT;
			}
		}
		
		
	}
	
	
	public void up() {
		// front->left->back->right->front rotateClockwise(up)
		
		int[] tempLeft = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempFront = new int[cubeSize];
		
		for(int i = 0 ; i < cubeSize; i++){
			tempLeft[i] = faceLeft[0][i];
			tempBack[i] = faceBack[0][i];
			tempRight[i] = faceRight[0][i];
			tempFront[i] = faceFront[0][i];
		}

		faceLeft[0] = tempFront;
		faceFront[0] = tempRight;
		faceBack[0] = tempLeft;
		faceRight[0] = tempBack;

		faceUp = rotateClockwise(faceUp);
	}

	public void upStar() {
		// front->right->back->left->front rotateCounterClockwise(up)
		int[] tempLeft = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempFront = new int[cubeSize];
		
		for(int i = 0 ; i < cubeSize; i++){
			tempLeft[i] = faceLeft[0][i];
			tempBack[i] = faceBack[0][i];
			tempRight[i] = faceRight[0][i];
			tempFront[i] = faceFront[0][i];
		}

		faceLeft[0] = tempBack;
		faceBack[0] = tempRight;
		faceRight[0] = tempFront;
		faceFront[0] = tempLeft;

		faceUp = rotateCounterClockwise(faceUp);
	}

	public void down() {
		// front->right->back->left->front rotateCounterClockwise(down)
		int[] tempLeft = faceLeft[cubeSize - 1];
		int[] tempBack = faceBack[cubeSize - 1];
		int[] tempRight = faceRight[cubeSize - 1];
		int[] tempFront = faceFront[cubeSize - 1];

		faceLeft[cubeSize - 1] = tempBack;
		faceBack[cubeSize - 1] = tempRight;
		faceRight[cubeSize - 1] = tempFront;
		faceFront[cubeSize - 1] = tempLeft;

		faceBottom = rotateClockwise(faceBottom);
	}

	public void downStar() {
		// front->left->back->right->front rotateClockwise(down)
		int[] tempLeft = faceLeft[cubeSize - 1];
		int[] tempBack = faceBack[cubeSize - 1];
		int[] tempRight = faceRight[cubeSize - 1];
		int[] tempFront = faceFront[cubeSize - 1];

		faceLeft[cubeSize - 1] = tempFront;
		faceBack[cubeSize - 1] = tempLeft;
		faceRight[cubeSize - 1] = tempBack;
		faceFront[cubeSize - 1] = tempRight;

		faceBottom = rotateCounterClockwise(faceBottom);
	}

	public void left() {
		// front->bottom->back->up->front rotateCounterClockwise(right)
		int[] tempFront = new int[cubeSize];
		int[] tempUp = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempFront[i] = faceFront[i][0];
			tempUp[i] = faceUp[i][0];
			tempBack[i] = faceBack[i][cubeSize - 1];
			tempBottom[i] = faceBottom[i][0];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceFront[i][0] = tempUp[i];
			faceUp[i][0] = tempBack[cubeSize - i - 1];
			faceBack[i][cubeSize - 1] = tempBottom[cubeSize - i - 1];
			faceBottom[i][0] = tempFront[i];
		}

		faceLeft = rotateClockwise(faceLeft);

	}

	public void leftStar() {
		// front->up->back->bottom->front rotateClockwise(right)
		int[] tempFront = new int[cubeSize];
		int[] tempUp = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempFront[i] = faceFront[i][0];
			tempUp[i] = faceUp[i][0];
			tempBack[i] = faceBack[i][cubeSize - 1];
			tempBottom[i] = faceBottom[i][0];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceFront[i][0] = tempBottom[i];
			faceUp[i][0] = tempFront[i];
			faceBack[i][cubeSize - 1] = tempUp[cubeSize - i - 1];
			faceBottom[i][0] = tempBack[cubeSize - i - 1];
		}

		faceLeft = rotateCounterClockwise(faceLeft);
	}

	public void right() {
		// front->up->back->bottom->front rotateClockwise(right)
		int[] tempFront = new int[cubeSize];
		int[] tempUp = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempFront[i] = faceFront[i][cubeSize - 1];
			tempUp[i] = faceUp[i][cubeSize - 1];
			tempBack[i] = faceBack[i][0];
			tempBottom[i] = faceBottom[i][cubeSize - 1];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceFront[i][cubeSize - 1] = tempBottom[i];
			faceUp[i][cubeSize - 1] = tempFront[i];
			faceBack[i][0] = tempUp[cubeSize - i - 1];
			faceBottom[i][cubeSize - 1] = tempBack[cubeSize - i - 1];
		}

		faceRight = rotateClockwise(faceRight);
	}

	public void rightStar() {
		// front->bottom->back->up->front rotateCounterClockwise(right)
		int[] tempFront = new int[cubeSize];
		int[] tempUp = new int[cubeSize];
		int[] tempBack = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempFront[i] = faceFront[i][cubeSize - 1];
			tempUp[i] = faceUp[i][cubeSize - 1];
			tempBack[i] = faceBack[i][0];
			tempBottom[i] = faceBottom[i][cubeSize - 1];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceFront[i][cubeSize - 1] = tempUp[i];
			faceUp[i][cubeSize - 1] = tempBack[cubeSize - 1 - i];
			faceBack[i][0] = tempBottom[cubeSize - i - 1];
			faceBottom[i][cubeSize - 1] = tempFront[i];
		}

		faceRight = rotateCounterClockwise(faceRight);
	}

	public void front() {
		// up->right->bottom->left->up rotateClockwise(front)
		int[] tempUp = new int[cubeSize];
		int[] tempLeft = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempUp[i] = faceUp[cubeSize - 1][i];
			tempLeft[i] = faceLeft[i][cubeSize - 1];
			tempRight[i] = faceRight[i][0];
			tempBottom[i] = faceBottom[0][i];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceBottom[0][i] = tempRight[cubeSize - i - 1];
			faceRight[i][0] = tempUp[i];
			faceUp[cubeSize - 1][i] = tempLeft[cubeSize - i - 1];
			faceLeft[i][cubeSize - 1] = tempBottom[i];
		}

		faceFront = rotateClockwise(faceFront);
	}

	public void frontStar() {
		// up->left->bottom->right->up rotateCounterClockwise(front)
		int[] tempUp = new int[cubeSize];
		int[] tempLeft = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempUp[i] = faceUp[cubeSize - 1][i];
			tempLeft[i] = faceLeft[i][cubeSize - 1];
			tempRight[i] = faceRight[i][0];
			tempBottom[i] = faceBottom[0][i];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceBottom[0][i] = tempLeft[i];
			faceUp[cubeSize - 1][i] = tempRight[i];
			faceRight[i][0] = tempBottom[cubeSize - i - 1];
			faceLeft[i][cubeSize - 1] = tempUp[cubeSize - i - 1];
		}

		faceFront = rotateCounterClockwise(faceFront);
	}

	public void back() {
		// up->left->bottom->right->up rotateClockwise(back)
		int[] tempUp = new int[cubeSize];
		int[] tempLeft = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempUp[i] = faceUp[0][i];
			tempLeft[i] = faceLeft[i][0];
			tempRight[i] = faceRight[i][cubeSize - 1];
			tempBottom[i] = faceBottom[cubeSize - 1][i];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceBottom[cubeSize - 1][i] = tempLeft[i];
			faceUp[0][i] = tempRight[i];
			faceRight[i][cubeSize - 1] = tempBottom[cubeSize - i - 1];
			faceLeft[i][0] = tempUp[cubeSize - i - 1];
		}

		faceBack = rotateClockwise(faceBack);
	}

	public void backStar() {
		// up->right->bottom->left->up rotateClockwise(back)
		int[] tempUp = new int[cubeSize];
		int[] tempLeft = new int[cubeSize];
		int[] tempRight = new int[cubeSize];
		int[] tempBottom = new int[cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			tempUp[i] = faceUp[0][i];
			tempLeft[i] = faceLeft[i][0];
			tempRight[i] = faceRight[i][cubeSize - 1];
			tempBottom[i] = faceBottom[cubeSize - 1][i];
		}

		for (int i = 0; i < cubeSize; i++) {
			faceBottom[cubeSize - 1][i] = tempRight[cubeSize - i - 1];
			faceRight[i][cubeSize - 1] = tempUp[i];
			faceUp[0][i] = tempLeft[cubeSize - i - 1];
			faceLeft[i][0] = tempBottom[i];
		}

		faceBack = rotateCounterClockwise(faceBack);
	}

	public void rotateUp() {
		// faceFront->faceLeft->faceBack->faceRight->faceFront rotateClockwise(up) rotateCounterClockwise(bottom)
		int[][] tempFront = faceFront;
		int[][] tempLeft = faceLeft;
		int[][] tempBack = faceBack;
		int[][] tempRight = faceRight;

		faceLeft = tempFront;
		faceBack = tempLeft;
		faceRight = tempBack;
		faceFront = tempRight;

		faceUp = rotateClockwise(faceUp);
		faceBottom = rotateCounterClockwise(faceBottom);
	}

	public void rotateUpStar() {
		// faceFront->faceRight->faceBack->faceLeft->faceFront rotateCounterClockwise(up) rotateClockwise(bottom)
		int[][] tempFront = faceFront;
		int[][] tempLeft = faceLeft;
		int[][] tempBack = faceBack;
		int[][] tempRight = faceRight;

		faceRight = tempFront;
		faceBack = tempRight;
		faceLeft = tempBack;
		faceFront = tempLeft;

		faceUp = rotateCounterClockwise(faceUp);
		faceBottom = rotateClockwise(faceBottom);
	}

	public void rotateRight() {
		// faceFront->faceUp->faceBack->faceBottom->faceFront rotateClockwise(right) rotateCounterClockwise(left)
		int[][] tempFront = new int[cubeSize][cubeSize];
		int[][] tempUp = new int[cubeSize][cubeSize];
		int[][] tempBack = new int[cubeSize][cubeSize];
		int[][] tempBottom = new int[cubeSize][cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			for (int j = 0; j < cubeSize; j++) {
				tempUp[i][j] = faceUp[cubeSize - i - 1][cubeSize - j - 1];
				tempBack[i][j] = faceBack[cubeSize - i - 1][cubeSize - j - 1];
				tempFront[i][j] = faceFront[i][j];
				tempBottom[i][j] = faceBottom[i][j];
			}
		}

		faceUp = tempFront;
		faceBack = tempUp;
		faceBottom = tempBack;
		faceFront = tempBottom;

		faceRight = rotateClockwise(faceRight);
		faceLeft = rotateCounterClockwise(faceLeft);
	}

	public void rotateRightStar() {
		// faceFront->faceBottom->faceBack->faceUp->faceFront rotateCounterClockwise(right) rotateClockwise(left)
		int[][] tempFront = new int[cubeSize][cubeSize];
		int[][] tempUp = new int[cubeSize][cubeSize];
		int[][] tempBack = new int[cubeSize][cubeSize];
		int[][] tempBottom = new int[cubeSize][cubeSize];

		for (int i = 0; i < cubeSize; i++) {
			for (int j = 0; j < cubeSize; j++) {
				tempUp[i][j] = faceUp[i][j];
				tempBack[i][j] = faceBack[cubeSize - i - 1][cubeSize - j - 1];
				tempBottom[i][j] = faceBottom[cubeSize - i - 1][cubeSize - j - 1];
				tempFront[i][j] = faceFront[i][j];
			}
		}

		faceUp = tempBack;
		faceBack = tempBottom;
		faceBottom = tempFront;
		faceFront = tempUp;

		faceRight = rotateCounterClockwise(faceRight);
		faceLeft = rotateClockwise(faceLeft);
	}

	public void rotateFront() {
		rotateUpStar();
		rotateRight();
		rotateUp();
	}

	public void rotateFrontStar() {
		rotateUpStar();
		rotateRightStar();
		rotateUp();
	}

	public void doubleRight() {
		rotateRight();
		left();
	}

	public void doubleRightStar() {
		rotateRightStar();
		leftStar();
	}

	public void doubleLeft() {
		rotateRightStar();
		right();
	}

	public void doubleLeftStar() {
		rotateRight();
		rightStar();
	}

	private int[][] rotateClockwise(int[][] face) {
		int[][] out = new int[face.length][face[0].length];
		for (int i = 0; i < out.length; i++) {
			for (int j = out[i].length - 1; j >= 0; j--) {
				out[i][out.length - j - 1] = face[j][i];
			}
		}

		return out;
	}

	private int[][] rotateCounterClockwise(int[][] face) {
		int[][] out = new int[face.length][face[0].length];

		for (int i = out.length - 1; i >= 0; i--) {
			for (int j = 0; j < out[i].length; j++) {
				out[out.length - i - 1][j] = face[j][i];
			}
		}

		return out;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isComplete(){
		for(int i = 0 ; i < cubeSize; i++){
			for(int j = 0; j<cubeSize; j++){
				if(	faceUp[i][j] != faceUp[0][0] || 
					faceBottom[i][j] != faceBottom[0][0] ||
					faceFront[i][j] != faceFront[0][0] ||
					faceBack[i][j] != faceBack[0][0] ||
					faceRight[i][j] != faceRight[0][0] ||
					faceLeft[i][j] != faceLeft[0][0]){
					return false;
				}
			}
		}
		return true;
	}
	
	public String printAllFace(){
		String out = "";
		
		for(int i = 0; i < cubeSize; i++){
			out+="        ";
			for (int j = 0; j < cubeSize; j++) {
				out += faceUp[i][j] +" ";
			}
			out+="\n";
		}
		
		for(int i = 0; i < cubeSize; i++){
			String left = "";
			String front = "";
			String right = "";
			String back = "";
			for (int j = 0; j < cubeSize; j++) {
				left += faceLeft[i][j] +" ";
				front += faceFront[i][j] +" ";
				right += faceRight[i][j] +" ";
				back += faceBack[i][j] +" ";
			}
			out+=left +"  " + front +"  " + right +"  " + back +"\n";
		}
		
		for(int i = 0; i < cubeSize; i++){
			out+="        ";
			for (int j = 0; j < cubeSize; j++) {
				out += faceBottom[i][j] +" ";
			}
			out+="\n";
		}
		return out;
	}
}
