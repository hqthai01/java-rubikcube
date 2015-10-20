package com.hqthai01.rubiksolver.ai;

import com.hqthai01.rubiksolver.constants.RubikConstants;
import com.hqthai01.rubiksolver.gui.JRubikPanel;
import com.hqthai01.rubiksolver.gui.Main;
import com.hqthai01.rubiksolver.model.RubikCube;

/**
 * @author Thái
 * Feb 10, 2015
 * 
 */
public class RubikCubeSolverAdvance implements RubikConstants{
	private RubikCube cube;
	private JRubikPanel pn;
	private Main main;

	private boolean isSolving = false;
	private boolean solutionFound = false;
	
	private int DELAY = 150;
	
	public RubikCubeSolverAdvance(){
	}
	
	public RubikCubeSolverAdvance(JRubikPanel pn){
		this.pn = pn;
	}
	
	public void setDelay(int delay){
		this.DELAY = delay;
	}

	/**
	 * Set the current rubik's cube panel
	 * @param pn is the JRubikPanel
	 */
	public void setPn(JRubikPanel pn){
		this.pn = pn;
	}
	
	/**
	 * Set the cube you want to solve
	 * @param cube
	 */
	public void setRubikCube(RubikCube cube){
		this.cube = cube;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	private void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void down(){
		pn.setAction("DOWN");
		pn.setMoveDown(true);
		main.setText("D ");
		cube.down();delay(DELAY);
	}
	
	private void downStar(){
		pn.setAction("DOWN'");
		pn.setMoveDownStar(true);
		main.setText("D' ");
		cube.downStar();delay(DELAY);
	}
	
	private void up(){
		pn.setAction("UP");
		pn.setMoveUp(true);
		main.setText("U ");
		cube.up();delay(DELAY);
	}
	
	private void upStar(){
		pn.setAction("UP'");
		pn.setMoveUpStar(true);
		main.setText("U' ");
		cube.upStar();delay(DELAY);
	}
	
	private void right(){
		pn.setAction("RIGHT");
		pn.setMoveRight(true);
		main.setText("R ");
		cube.right();delay(DELAY);
	}
	
	private void rightStar(){
		pn.setAction("RIGHT'");
		pn.setMoveRightStar(true);
		main.setText("R' ");
		cube.rightStar();delay(DELAY);
	}
	
	private void left(){
		pn.setAction("LEFT");
		pn.setMoveLeft(true);
		main.setText("L ");
		cube.left();delay(DELAY);
	}
	
	private void leftStar(){
		pn.setAction("LEFT'");
		pn.setMoveLeftStar(true);
		main.setText("L' ");
		cube.leftStar();delay(DELAY);
	}
	
	private void front(){
		pn.setAction("FRONT");
		pn.setMoveFront(true);
		main.setText("F ");
		cube.front();delay(DELAY);
	}
	
	private void frontStar(){
		pn.setAction("FRONT'");
		pn.setMoveFrontStar(true);
		main.setText("F' ");
		cube.frontStar();delay(DELAY);
	}
	
	private void back(){
		pn.setAction("BACK");
		pn.setMoveBack(true);
		main.setText("B ");
		cube.back();delay(DELAY);
	}
	
	private void backStar(){
		pn.setAction("BACK'");
		pn.setMoveBackStar(true);
		main.setText("B' ");
		cube.backStar();delay(DELAY);
	}
	
	private void rotateUp(){
		pn.setAction("ROTATE CUBE UP CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Uc ");
		cube.rotateUp();delay(DELAY);
	}
	
	private void rotateUpStar(){
		pn.setAction("ROTATE CUBE UP COUNTER-CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Uc' ");
		cube.rotateUpStar();delay(DELAY);
	}
	
	private void rotateRight(){
		pn.setAction("ROTATE CUBE RIGHT CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Rc ");
		cube.rotateRight();delay(DELAY);
	}
	
	private void rotateRightStar(){
		pn.setAction("ROTATE CUBE RIGHT COUNTER-CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Rc' ");
		cube.rotateRightStar();delay(DELAY);
	}
	
	private void rotateFront(){
		pn.setAction("ROTATE CUBE FRONT CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Fc ");
		cube.rotateFront();delay(DELAY);
	}
	
	private void rotateFrontStar(){
		pn.setAction("ROTATE CUBE FRONT COUNTER-CLOCKWISE");
		pn.setMoves(pn.getMoves()-1);
		main.setText("Fc' ");
		cube.rotateFrontStar();delay(DELAY);
	}
	
	private void up2(){
		up();
		up();
	}
	
	private void down2(){
		down();
		down();
	}
	
	private void front2(){
		front();
		front();
	}
	
	private void back2(){
		back();
		back();
	}
	
	private void right2(){
		right();
		right();
	}
	
	private void left2(){
		left();
		left();
	}
	
	
	/**
	 * Quick fish move at the right hand
	 */
	private void rightFishMove(){
		move("R U' L' U R' U' L U");
	}
	
	/**
	 * Quick fish move at the left hand
	 */
	private void leftFishMove(){
		move("L' U R U' L U R' U'");
	}
	
	/**
	 * Quick move to turn the FRONT edge at other face to the FRONT face
	 */
	private void quickFixBlueToCenter(){
		int front = cube.getFaceFront()[0][1];
		while(front != FRONT){
			up();
			front = cube.getFaceFront()[0][1];
		}
	}
	
	private void quickRotateUpToComplete(){
		while(!isCubeSolved()){
			up();
		}
	}
	
	private void move(String value){
		String[] listMove = value.trim().split(" ");
		for(String step : listMove){
			switch(step){
			case "U":
				up();
				break;
			case "U'":
				upStar();
				break;
			case "D":
				down();
				break;
			case "D'":
				downStar();
				break;
			case "L":
				left();
				break;
			case "L'":
				leftStar();
				break;
			case "R":
				right();
				break;
			case "R'":
				rightStar();
				break;
			case "F":
				front();
				break;
			case "F'":
				frontStar();
				break;
			case "B":
				back();
				break;
			case "B'":
				backStar();
				break;
			case "U2":
				up2();
				break;
			case "D2":
				down2();
				break;
			case "L2":
				left2();
				break;
			case "R2":
				right2();
				break;
			case "F2":
				front2();
				break;
			case "B2":
				back2();
				break;
			case "Fc":
				rotateFront();
				break;
			case "Fc'":
				rotateFrontStar();
				break;
			case "Rc":
				rotateRight();
				break;
			case "Rc'":
				rotateRightStar();
				break;
			case "Uc":
				rotateUp();
				break;
			case "Uc'":
				rotateUpStar();
				break;
			case "Rc2":
				rotateRight();
				rotateRight();
				break;
			case "Uc2":
				rotateUp();
				rotateUp();
				break;
			case "Fc2":
				rotateFront();
				rotateFront();
				break;
			}
		}
	}
	
	//========================SOLVE THE CUBE METHODS=========================
	public void solveTheCube(RubikCube cube){
		setRubikCube(cube);
		if(!isSolving){
			isSolving = true;
			main.setText("Rotate to default position:\n");
			rotateWhiteFaceToBottom();
			rotateBlueFaceToFront();
			main.setText("\n\nSolve bottom cross:\n");
			solveBottomCross();
			main.setText("\n\nSolve First Two Layer\n");
			F2L();
			main.setText("\n\nSolve Orientation of the Last Layer (OLL) (Two Look Method)\n");
			OLL();
			main.setText("\n\nSolve Permutation of the Last Layer (PLL)\n");
			PLL();
			isSolving = false;
		}
	}
	
	//========================ROTATE CUBE TO CORRECT PLACE===================
		private void rotateWhiteFaceToBottom() {
			int center = RubikCube.cubeSize / 2;
			int centerTile = cube.getFaceLeft()[center][center];
	
			if (centerTile == BOTTOM) {
				rotateFrontStar();
				return;
			}
	
			centerTile = cube.getFaceRight()[center][center];
			if (centerTile == BOTTOM) {
				rotateFront();
				return;
			}
	
			centerTile = cube.getFaceFront()[center][center];
			if (centerTile == BOTTOM) {
				rotateRightStar();
				return;
			}
	
			centerTile = cube.getFaceBack()[center][center];
			if (centerTile == BOTTOM) {
				rotateRight();
				return;
			}
	
			centerTile = cube.getFaceUp()[center][center];
			if (centerTile == BOTTOM) {
				rotateFront();
				rotateFront();
				return;
			}
	
		}
	
		private void rotateBlueFaceToFront() {
			int center = RubikCube.cubeSize / 2;
			int centerTile = cube.getFaceLeft()[center][center];
	
			if (centerTile == FRONT) {
				rotateUpStar();
				return;
			}
	
			centerTile = cube.getFaceRight()[center][center];
			if (centerTile == FRONT) {
				rotateUp();
				return;
			}
	
			centerTile = cube.getFaceBack()[center][center];
			if (centerTile == FRONT) {
				rotateUp();
				rotateUp();
				return;
			}
		}
		
		private void moveWhiteCornerToCorrectPositionFrontRightEdgeUp(){
			int right = cube.getFaceRight()[0][0];
			int centerR = cube.getFaceRight()[1][1];
			while(right != centerR){
				move("U Uc'");
				right = cube.getFaceRight()[0][0];
				centerR = cube.getFaceRight()[1][1];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionFrontRightEdgeRight(){
			int right = cube.getFaceRight()[0][0];
			int up = cube.getFaceUp()[2][2];
			int right2 = cube.getFaceRight()[1][0];
			int front = cube.getFaceFront()[1][2];
			
			while(!((right == right2 && up == front) || (right == front && up == right2))){
				move("U Uc'");
				right = cube.getFaceRight()[0][0];
				up = cube.getFaceUp()[2][2];
				right2 = cube.getFaceRight()[1][0];
				front = cube.getFaceFront()[1][2];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionUpRightEdgeUp(){
			int right = cube.getFaceRight()[0][0];
			int front = cube.getFaceFront()[0][2];
			int centerR = cube.getFaceRight()[1][1];
			int centerF = cube.getFaceFront()[1][1];
			
			while(right != centerF && front != centerR){
				move("U Uc'");
				right = cube.getFaceRight()[0][0];
				front = cube.getFaceFront()[0][2];
				centerR = cube.getFaceRight()[1][1];
				centerF = cube.getFaceFront()[1][1];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionUpRightEdgeRight(){
			int right = cube.getFaceRight()[0][0];
			int front = cube.getFaceFront()[0][2];
			int right2 = cube.getFaceRight()[1][0];
			int front2 = cube.getFaceFront()[1][2];
			
			while(!((right == right2 && front == front2) || (right == front2 && front == right2))){
				move("U Uc'");
				right = cube.getFaceRight()[0][0];
				front = cube.getFaceFront()[0][2];
				right2 = cube.getFaceRight()[1][0];
				front2 = cube.getFaceFront()[1][2];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionFrontLeftEdgeUp(){
			int left = cube.getFaceLeft()[0][2];
			int centerL = cube.getFaceLeft()[1][1];
			while(left != centerL){
				move("U Uc'");
				left = cube.getFaceLeft()[0][2];
				centerL = cube.getFaceLeft()[1][1];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionFrontLeftEdgeLeft(){
			int left = cube.getFaceLeft()[0][2];
			int up = cube.getFaceUp()[2][0];
			int left2 = cube.getFaceLeft()[1][2];
			int front = cube.getFaceFront()[1][0];
			
			while(!((left == left2 && up == front) || (left == front && up == left2))){
				move("U Uc'");
				left = cube.getFaceLeft()[0][2];
				up = cube.getFaceUp()[2][0];
				left2 = cube.getFaceLeft()[1][2];
				front = cube.getFaceFront()[1][0];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionUpLeftEdgeUp(){
			int left = cube.getFaceLeft()[0][2];
			int front = cube.getFaceFront()[0][0];
			int centerL = cube.getFaceLeft()[1][1];
			int centerF = cube.getFaceFront()[1][1];
			
			while(left != centerF && front != centerL){
				move("U Uc'");
				left = cube.getFaceLeft()[0][2];
				front = cube.getFaceFront()[0][0];
				centerL = cube.getFaceLeft()[1][1];
				centerF = cube.getFaceFront()[1][1];
			}
		}
		
		private void moveWhiteCornerToCorrectPositionUpLeftEdgeLeft(){
			int left = cube.getFaceLeft()[0][2];
			int front = cube.getFaceFront()[0][0];
			int left2 = cube.getFaceLeft()[1][2];
			int front2 = cube.getFaceFront()[1][0];
			
			while(!((left == left2 && front == front2) || (left == front2 && front == left2))){
				move("U Uc'");
				left = cube.getFaceLeft()[0][2];
				front = cube.getFaceFront()[0][0];
				left2 = cube.getFaceLeft()[1][2];
				front2 = cube.getFaceFront()[1][0];
			}
		}
		
	
	//========================END ROTATE CUBE TO CORRECT PLACE===============
	
	//========================SOLVE CROSS AT THE BOTTOM======================
		private void solveBottomCross() {
			while(!isCorrectCrossAtBottom()){
				// case 1: the edge at the front, back, left, right face
				whiteEdgeAtFrontFace();
				whiteEdgeAtLeftFace();
				whiteEdgeAtRightFace();
				whiteEdgeAtBackFace();

				// case 2: the edge at the bottom face
				whiteEdgeAtBottomFace();
				
				// case 3: the edge at the up face
				whiteEdgeAtUpFace();
			}
		}

		private void whiteEdgeAtBottomFace() {
			int color = -1;
			// face front
			if (cube.getFaceBottom()[0][1] == BOTTOM) {
				color = cube.getFaceFront()[2][1];
				if (color != FRONT) {
					front2();
					switch (color) {
					case LEFT:
						up();
						left2();
						break;
					case RIGHT:
						upStar();
						right2();
						break;
					case BACK:
						up2();
						back2();
						break;
					}
				}
			}

			// face left
			if (cube.getFaceBottom()[1][0] == BOTTOM) {
				color = cube.getFaceLeft()[2][1];
				if (color != LEFT) {
					left2();
					switch (color) {
					case BACK:
						up();
						back2();
						break;
					case FRONT:
						upStar();
						front2();
						break;
					case RIGHT:
						up2();
						right2();
						break;
					}
				}
			}
			// face back
			if (cube.getFaceBottom()[2][1] == BOTTOM) {
				color = cube.getFaceBack()[2][1];
				if (color != BACK) {
					back2();
					switch (color) {
					case LEFT:
						upStar();
						left2();
						break;
					case RIGHT:
						up();
						right2();
						break;
					case FRONT:
						up2();
						front2();
						break;
					}
				}
			}

			// face right
			if (cube.getFaceBottom()[1][2] == BOTTOM) {
				color = cube.getFaceRight()[2][1];
				if (color != RIGHT) {
					right2();
					switch (color) {
					case BACK:
						upStar();
						back2();
						break;
					case FRONT:
						up();
						front2();
						break;
					case LEFT:
						up2();
						left2();
						break;
					}
				}
			}
		}

		private void whiteEdgeAtUpFace(){
			int color = -1;
			while(upFaceHasWhiteEdge()){
				// face back
				if (cube.getFaceUp()[0][1] == BOTTOM) {
					color = cube.getFaceBack()[0][1];
					switch(color){
					case FRONT:
						up2();
						front2();
						break;
					case BACK:
						back2();
						break;
					case LEFT:
						upStar();
						left2();
						break;
					case RIGHT:
						up();
						right2();
						break;
					}
					
				}
		
				// face left
				if (cube.getFaceUp()[1][0] == BOTTOM) {
					color = cube.getFaceLeft()[0][1];
					switch(color){
					case FRONT:
						upStar();
						front2();
						break;
					case BACK:
						up();
						back2();
						break;
					case LEFT:
						left2();
						break;
					case RIGHT:
						up2();
						right2();
						break;
					}
				}
				// face front
				if (cube.getFaceUp()[2][1] == BOTTOM) {
					color = cube.getFaceFront()[0][1];
					switch(color){
					case FRONT:
						front2();
						break;
					case BACK:
						up2();
						back2();
						break;
					case LEFT:
						up();
						left2();
						break;
					case RIGHT:
						upStar();
						right2();
						break;
					}
				}
		
				// face right
				if (cube.getFaceUp()[1][2] == BOTTOM) {
					color = cube.getFaceRight()[0][1];
					switch(color){
					case FRONT:
						up();
						front2();
						break;
					case BACK:
						upStar();
						back2();
						break;
					case LEFT:
						up2();
						left2();
						break;
					case RIGHT:
						right2();
						break;
					}
				}
			}
		}
		
		private void whiteEdgeAtFrontFace(){
			while(frontFaceHasWhiteEdge()){
				int color = -1;
				if(cube.getFaceFront()[1][0] == BOTTOM){
					color = cube.getFaceLeft()[1][2];
					if(color == LEFT){
						left();
					}else{
						leftStar();
						upStar();
						left();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceFront()[1][2] == BOTTOM){
					color = cube.getFaceRight()[1][0];
					if(color == RIGHT){
						rightStar();
					}else{
						right();
						up();
						rightStar();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceFront()[2][1] == BOTTOM || cube.getFaceFront()[0][1] == BOTTOM){
					front();
				}
			}
			
		}
		
		private void whiteEdgeAtLeftFace(){
			while(leftHasWhiteEdge()){
				int color = -1;
				if(cube.getFaceLeft()[1][0] == BOTTOM){
					color = cube.getFaceBack()[1][2];
					if(color == BACK){
						back();
					}else{
						backStar();
						upStar();
						back();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceLeft()[1][2] == BOTTOM){
					color = cube.getFaceFront()[1][0];
					if(color == FRONT){
						frontStar();
					}else{
						front();
						up();
						frontStar();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceLeft()[2][1] == BOTTOM || cube.getFaceLeft()[0][1] == BOTTOM){
					left();
				}
			}
		}
		
		private void whiteEdgeAtRightFace(){
			while(rightHasWhiteEdge()){
				int color = -1;
				if(cube.getFaceRight()[1][0] == BOTTOM){
					color = cube.getFaceFront()[1][2];
					if(color == FRONT){
						front();
					}else{
						frontStar();
						upStar();
						front();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceRight()[1][2] == BOTTOM){
					color = cube.getFaceBack()[1][0];
					if(color == BACK){
						backStar();
					}else{
						back();
						up();
						backStar();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceRight()[2][1] == BOTTOM || cube.getFaceRight()[0][1] == BOTTOM){
					right();
				}
			}
		}
		
		private void whiteEdgeAtBackFace(){
			while(backHasWhiteEdge()){
				int color = -1;
				if(cube.getFaceBack()[1][0] == BOTTOM){
					color = cube.getFaceRight()[1][2];
					if(color == RIGHT){
						right();
					}else{
						rightStar();
						upStar();
						right();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceBack()[1][2] == BOTTOM){
					color = cube.getFaceLeft()[1][0];
					if(color == LEFT){
						leftStar();
					}else{
						left();
						up();
						leftStar();
						whiteEdgeAtUpFace();
					}
				}
				
				if(cube.getFaceBack()[2][1] == BOTTOM || cube.getFaceBack()[0][1] == BOTTOM){
					back();
				}
			}
		}
		
		private boolean upFaceHasWhiteEdge(){
			int[][] 	up = cube.getFaceUp();
			return 	up[0][1] == BOTTOM || 
					up[1][0] == BOTTOM || 
					up[1][2] == BOTTOM || 
					up[2][1] == BOTTOM;
		}
		
		private boolean frontFaceHasWhiteEdge(){
			int[][] 	front = cube.getFaceFront();
			return 	front[0][1] == BOTTOM || 
					front[1][0] == BOTTOM || 
					front[1][2] == BOTTOM || 
					front[2][1] == BOTTOM;
		}
		
		private boolean leftHasWhiteEdge(){
			int[][]	left = cube.getFaceLeft();
			return 	left[0][1] == BOTTOM || 
					left[1][0] == BOTTOM || 
					left[1][2] == BOTTOM || 
					left[2][1] == BOTTOM;
		}
		
		private boolean rightHasWhiteEdge(){
			int[][]	right = cube.getFaceRight();
			return 	right[0][1] == BOTTOM || 
					right[1][0] == BOTTOM || 
					right[1][2] == BOTTOM || 
					right[2][1] == BOTTOM;
		}
		
		private boolean backHasWhiteEdge(){
			int[][]	back = cube.getFaceBack();
			return 	back[0][1] == BOTTOM || 
					back[1][0] == BOTTOM || 
					back[1][2] == BOTTOM || 
					back[2][1] == BOTTOM;
		}
		
		private boolean isCorrectCrossAtBottom(){
			int[][] bottom = 	cube.getFaceBottom();
			int[][] left 	= 	cube.getFaceLeft();
			int[][] right 	= 	cube.getFaceRight();
			int[][] back 	= 	cube.getFaceBack();
			int[][] front 	= 	cube.getFaceFront();
			boolean value 	= 	bottom[0][1]	== BOTTOM && 
							bottom[1][0] 	== BOTTOM && 
							bottom[1][2] 	== BOTTOM && 
							bottom[2][1] 	== BOTTOM &&
							front[2][1] 	== FRONT 	&&
							left[2][1] 	== LEFT 	&&
							right[2][1] 	== RIGHT 	&&
							back[2][1] 	== BACK 	;
			return value;
		}
		
	//========================END SOLVE CROSS AT BOTTOM======================
	
	//========================FIRST TWO LAYER================================	
		private void F2L(){
			int counter = 0;
			while(!isTwoLayer()){
				solutionFound = false;
				//right hand
				if(recognizeRCase1F2L()){
					rCase1F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase2F2L()){
					rCase2F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase3F2L()){
					rCase3F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase4F2L()){
					rCase4F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase5F2L()){
					rCase5F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase6F2L()){
					rCase6F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase7F2L()){
					rCase7F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase8F2L()){
					rCase8F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase9F2L()){
					rCase9F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase10F2L()){
					rCase10F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase11F2L()){
					rCase11F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase12F2L()){
					rCase12F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase13F2L()){
					rCase13F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase14F2L()){
					rCase14F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase15F2L()){
					rCase15F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase16F2L()){
					rCase16F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase17F2L()){
					rCase17F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase18F2L()){
					rCase18F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase19F2L()){
					rCase19F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase20F2L()){
					rCase20F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase21F2L()){
					rCase21F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase22F2L()){
					rCase22F2L();solutionFound = true;counter=0;
				}else if(recognizeRCase23F2L()){
					rCase23F2L();solutionFound = true;counter=0;
				}
				//left hand
				else if(recognizeLCase1F2L()){
					lCase1F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase2F2L()){
					lCase2F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase3F2L()){
					lCase3F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase4F2L()){
					lCase4F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase5F2L()){
					lCase5F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase6F2L()){
					lCase6F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase7F2L()){
					lCase7F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase8F2L()){
					lCase8F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase9F2L()){
					lCase9F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase10F2L()){
					lCase10F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase11F2L()){
					lCase11F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase12F2L()){
					lCase12F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase13F2L()){
					lCase13F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase14F2L()){
					lCase14F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase15F2L()){
					lCase15F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase16F2L()){
					lCase16F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase17F2L()){
					lCase17F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase18F2L()){
					lCase18F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase19F2L()){
					lCase19F2L();solutionFound = true;counter=0;
				}else if(recognizeLCase20F2L()){
					lCase20F2L();solutionFound = true;counter=0;
				}
				//white at front face bottom
				else if(recognizeRCase30F2L()){
					move("R U' R'");solutionFound = true;counter=0;
				}else if(recognizeLCase30F2L()){
					move("L' U L");solutionFound = true;counter=0;
				}
				if(!solutionFound && counter==4){
					rotateUpStar();
					counter=0;
				}
				else if(!isTwoLayer()){
					up();
				}
				counter++;
			}
		}
		
		
		private void rCase1F2L(){
			System.out.println("RIGHT HAND F2L CASE 1 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("U R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase2F2L(){
			System.out.println("RIGHT HAND F2L CASE 2 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("U' R U2 R' U Uc L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase3F2L(){
			System.out.println("RIGHT HAND F2L CASE 3 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("Uc L' U L Uc' U2 R U R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase4F2L(){
			System.out.println("RIGHT HAND F2L CASE 4 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("Uc U L' U L U' L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase5F2L(){
			System.out.println("RIGHT HAND F2L CASE 5 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("U' R U2 R' U2 R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase6F2L(){
			System.out.println("RIGHT HAND F2L CASE 6 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("Uc L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase7F2L(){
			System.out.println("RIGHT HAND F2L CASE 7 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("U' R U R' U2 R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase8F2L(){
			System.out.println("RIGHT HAND F2L CASE 8 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeUp();
			
			move("U' R U' R' U Uc L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase9F2L(){
			System.out.println("RIGHT HAND F2L CASE 9 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeRight();
			
			move("U' R U' R' U");
			rCase1F2L();
		}
		
		private void rCase10F2L(){
			System.out.println("RIGHT HAND F2L CASE 10 DETECTED");
			moveWhiteCornerToCorrectPositionFrontRightEdgeRight();
			
			move("U' R U R' U");
			rCase6F2L();
		}
		
		private void rCase11F2L(){
			System.out.println("RIGHT HAND F2L CASE 11 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("R U2 R' U' R U R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase12F2L(){
			System.out.println("RIGHT HAND F2L CASE 12 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("R U R' U R U R' U2 Uc L' U L");
			rotateBlueFaceToFront();
		}
		
		private void rCase13F2L(){
			System.out.println("RIGHT HAND F2L CASE 13 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("R U R' U2 R U R' U' R U R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase14F2L(){
			System.out.println("RIGHT HAND F2L CASE 14 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("Uc L' U2 L U L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase15F2L(){
			System.out.println("RIGHT HAND F2L CASE 15 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("U2 R U R' U R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase16F2L(){
			System.out.println("RIGHT HAND F2L CASE 16 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("Uc U' L' U2 L U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void rCase17F2L(){
			System.out.println("RIGHT HAND F2L CASE 17 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("U R U2 R' U R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void rCase18F2L(){
			System.out.println("RIGHT HAND F2L CASE 18 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeUp();
			
			move("Uc U2 L' U' L U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void rCase19F2L(){
			System.out.println("RIGHT HAND F2L CASE 19 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeRight();
			
			move("Uc L' U2 L U Uc'");
			rCase1F2L();
		}
		
		private void rCase20F2L(){
			System.out.println("RIGHT HAND F2L CASE 20 DETECTED");
			moveWhiteCornerToCorrectPositionUpRightEdgeRight();
			move("Uc");
			for(int i =0; i <2; i++)
				move("L' U' L U");
			move("Uc'");
			rCase6F2L();
		}
		
		private void rCase21F2L(){
			System.out.println("RIGHT HAND F2L CASE 21 DETECTED");
			move("R U R' U2 R U2 R' U Uc L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void rCase22F2L(){
			System.out.println("RIGHT HAND F2L CASE 22 DETECTED");
			move("U R U' R' Uc U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void rCase23F2L(){
			System.out.println("RIGHT HAND F2L CASE 23 DETECTED");
			move("R U R' U'");
			rCase3F2L();
		}
//		
//		private void rCase24F2L(){
//			System.out.println("RIGHT HAND F2L CASE 24 DETECTED");
//		}
//		
//		private void rCase25F2L(){
//			System.out.println("RIGHT HAND F2L CASE 25 DETECTED");
//		}
//		
//		private void rCase26F2L(){
//			System.out.println("RIGHT HAND F2L CASE 26 DETECTED");
//		}
//		
//		private void rCase27F2L(){
//			System.out.println("RIGHT HAND F2L CASE 27 DETECTED");
//		}
//		
//		private void rCase28F2L(){
//			System.out.println("RIGHT HAND F2L CASE 28 DETECTED");
//		}
//		
//		private void rCase29F2L(){
//			System.out.println("RIGHT HAND F2L CASE 29 DETECTED");
//		}
		
		private boolean recognizeRCase1F2L(){
			int front = cube.getFaceFront()[0][2];
			int top1 = cube.getFaceUp()[2][2];
			int right1 = cube.getFaceRight()[0][0];
			
			int top2 = cube.getFaceUp()[1][2];
			int right2 = cube.getFaceRight()[0][1];
			boolean value = front  == BOTTOM &&
					top1   == top2   &&
					right1 == right2;
			return value;
		}
		
		private boolean recognizeRCase2F2L(){
			int front = cube.getFaceFront()[0][2];
			int top1 = cube.getFaceUp()[2][2];
			int right1 = cube.getFaceRight()[0][0];
			
			int top2 = cube.getFaceUp()[1][2];
			int right2 = cube.getFaceRight()[0][1];
			boolean value = front  == BOTTOM &&
					top1   == right2 &&
					right1 == top2;
			return value;
		}
		
		private boolean recognizeRCase3F2L(){
			int front = cube.getFaceFront()[0][2];
			int top1 = cube.getFaceUp()[2][2];
			int right1 = cube.getFaceRight()[0][0];
			
			int front1 = cube.getFaceFront()[0][1];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = front  == BOTTOM &&
					right1 == front1 &&
					top1   == top2;
			return value;
		}
		
		private boolean recognizeRCase4F2L(){
			int front = cube.getFaceFront()[0][2];
			int front1 = cube.getFaceFront()[0][1];
			int right1 = cube.getFaceRight()[0][0];
			
			int top1 = cube.getFaceUp()[2][2];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = front  == BOTTOM &&
					right1 == top2   &&
					top1   == front1;
			return value;
		}
		
		private boolean recognizeRCase5F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int left = cube.getFaceLeft()[0][1];
			int top2 = cube.getFaceUp()[1][0];
			
			boolean value = front  == BOTTOM &&
					right1 == left   &&
					top1   == top2;
			return value;
		}
		
		private boolean recognizeRCase6F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int left = cube.getFaceLeft()[0][1];
			int top2 = cube.getFaceUp()[1][0];
			
			boolean value = front  == BOTTOM &&
					right1 == top2   &&
					left   == top1;
			return value;
		}
		
		private boolean recognizeRCase7F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = front  == BOTTOM &&
					right1 == back   &&
					top1   == top2;
			return value;
		}
		
		private boolean recognizeRCase8F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = front  == BOTTOM &&
					right1 == top2   &&
					top1   == back;
			return value;
		}
		
		private boolean recognizeRCase9F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int right2 = cube.getFaceRight()[1][0];
			int front2 = cube.getFaceFront()[1][2];
			
			boolean value = front  == BOTTOM &&
					right1 == right2 &&
					top1   == front2;
			return value;
		}
		
		private boolean recognizeRCase10F2L(){
			int front = cube.getFaceFront()[0][2];
			int right1 = cube.getFaceRight()[0][0];
			int top1 = cube.getFaceUp()[2][2];
			
			int right2 = cube.getFaceRight()[1][0];
			int front2 = cube.getFaceFront()[1][2];
			
			boolean value = front  == BOTTOM &&
						 right1 == front2 &&
						 top1   == right2;
			return value;
		}
		
		private boolean recognizeRCase11F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int top2 = cube.getFaceUp()[1][2];
			int right2 = cube.getFaceRight()[0][1];
			
			boolean value = top   == BOTTOM &&
					front == right2 &&
					right == top2;
			
			return value;
		}
		
		private boolean recognizeRCase12F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int top2 = cube.getFaceUp()[1][2];
			int right2 = cube.getFaceRight()[0][1];
			
			boolean value = top   == BOTTOM &&
					front == top2   &&
					right == right2;
			
			return value;
		}
		
		private boolean recognizeRCase13F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int front2 = cube.getFaceFront()[0][1];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = top   == BOTTOM &&
					front == front2 &&
					right == top2;
			
			return value;
		}
		
		private boolean recognizeRCase14F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int front2 = cube.getFaceFront()[0][1];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = top   == BOTTOM &&
					front == top2   &&
					right == front2;
			
			return value;
		}
		
		private boolean recognizeRCase15F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int left = cube.getFaceLeft()[0][1];
			int top2 = cube.getFaceUp()[1][0];
			
			boolean value = top   == BOTTOM &&
					front == left   &&
					right == top2;
			
			return value;
		}
		
		private boolean recognizeRCase16F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int left = cube.getFaceLeft()[0][1];
			int top2 = cube.getFaceUp()[1][0];
			
			boolean value = top   == BOTTOM &&
					front == top2   &&
					right == left;
			
			return value;
		}
		
		private boolean recognizeRCase17F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = top   == BOTTOM &&
					front == back   &&
					right == top2;
			
			return value;
		}
		
		private boolean recognizeRCase18F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = top   == BOTTOM &&
					front == top2   &&
					right == back;
			
			return value;
		}
		
		private boolean recognizeRCase19F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int front2 = cube.getFaceFront()[1][2];
			int right2 = cube.getFaceRight()[1][0];
			
			boolean value = top   == BOTTOM &&
					front == front2 &&
					right == right2;
			
			return value;
		}
		
		private boolean recognizeRCase20F2L(){
			int top = cube.getFaceUp()[2][2];
			int front = cube.getFaceFront()[0][2];
			int right = cube.getFaceRight()[0][0];
			
			int front2 = cube.getFaceFront()[1][2];
			int right2 = cube.getFaceRight()[1][0];
			
			boolean value = top   == BOTTOM &&
					front == right2 &&
					right == front2;
			
			return value;
		}
		
		private boolean recognizeRCase21F2L(){
			int[] front = cube.getFaceFront()[2];
			for(int i = 1; i<front.length; i++){
				if(front[i] != front[0])
					return false;
			}
			
			int[] right = cube.getFaceRight()[2];
			for(int i = 1; i<right.length; i++){
				if(right[i] != right[0])
					return false;
			}
			
			int[] back = cube.getFaceBack()[2];
			for(int i = 1; i<back.length; i++){
				if(back[i] != back[0])
					return false;
			}
			
			int[] left = cube.getFaceLeft()[2];
			for(int i = 1; i<left.length; i++){
				if(left[i] != left[0])
					return false;
			}
			
			front = cube.getFaceFront()[1];
			right = cube.getFaceRight()[1];
			
//			return front[2] != UP && right[0] != UP;
			return front[2] == right[1] && front[1] == right[0];
		}
		
		private boolean recognizeRCase22F2L(){
			int[] front = cube.getFaceFront()[2];
			for(int i = 1; i<front.length; i++){
				if(front[i] != front[0])
					return false;
			}
			
			int[] right = cube.getFaceRight()[2];
			for(int i = 1; i<right.length; i++){
				if(right[i] != right[0])
					return false;
			}
			
			int[] back = cube.getFaceBack()[2];
			for(int i = 1; i<back.length; i++){
				if(back[i] != back[0])
					return false;
			}
			
			int[] left = cube.getFaceLeft()[2];
			for(int i = 1; i<left.length; i++){
				if(left[i] != left[0])
					return false;
			}
			int front1 = cube.getFaceFront()[0][1];
			int up = cube.getFaceUp()[2][1];
			
			return front[2] == front1 && right[0] == up;
		}
		
		private boolean recognizeRCase23F2L(){
			int[] front = cube.getFaceFront()[2];
			for(int i = 1; i<front.length; i++){
				if(front[i] != front[0])
					return false;
			}
			
			int[] right = cube.getFaceRight()[2];
			for(int i = 1; i<right.length; i++){
				if(right[i] != right[0])
					return false;
			}
			
			int[] back = cube.getFaceBack()[2];
			for(int i = 1; i<back.length; i++){
				if(back[i] != back[0])
					return false;
			}
			
			int[] left = cube.getFaceLeft()[2];
			for(int i = 1; i<left.length; i++){
				if(left[i] != left[0])
					return false;
			}
			int front1 = cube.getFaceFront()[0][1];
			int up = cube.getFaceUp()[2][1];
			
			return front[2] == up && right[0] == front1;
		}
//		
//		private boolean recognizeRCase24F2L(){
//			return false;
//		}
//		
//		private boolean recognizeRCase25F2L(){
//			return false;
//		}
//		
//		private boolean recognizeRCase26F2L(){
//			return false;
//		}
//		
//		private boolean recognizeRCase27F2L(){
//			return false;
//		}
//		
//		private boolean recognizeRCase28F2L(){
//			return false;
//		}
//		
//		private boolean recognizeRCase29F2L(){
//			return false;
//		}
		
		//left hand
		private void lCase1F2L(){
			System.out.println("LEFT HAND F2L CASE 1 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void lCase2F2L(){
			System.out.println("LEFT HAND F2L CASE 2 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("U L' U2 L U' Uc' R U R'");
			rotateBlueFaceToFront();
			
		}
		
		private void lCase3F2L(){
			System.out.println("LEFT HAND F2L CASE 3 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("Uc' R U' R' Uc U2 L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void lCase4F2L(){
			System.out.println("LEFT HAND F2L CASE 4 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("Uc' U' R U' R' U R U R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase5F2L(){
			System.out.println("LEFT HAND F2L CASE 5 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("U L' U2 L U2 L' U L");
			rotateBlueFaceToFront();
		}
		
		private void lCase6F2L(){
			System.out.println("LEFT HAND F2L CASE 6 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("Uc' R U R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase7F2L(){
			System.out.println("LEFT HAND F2L CASE 7 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("U L' U' L U2 L' U L");
			rotateBlueFaceToFront();
		}
		
		private void lCase8F2L(){
			System.out.println("LEFT HAND F2L CASE 8 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeUp();
			
			move("U L' U L U' Uc' R U R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase9F2L(){
			System.out.println("LEFT HAND F2L CASE 9 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeLeft();
			
			move("U L' U L U'");
			lCase1F2L();
		}
		
		private void lCase10F2L(){
			System.out.println("LEFT HAND F2L CASE 10 DETECTED");
			moveWhiteCornerToCorrectPositionFrontLeftEdgeLeft();
			
			move("U L' U' L U'");
			lCase6F2L();
		}
		
		private void lCase11F2L(){
			System.out.println("LEFT HAND F2L CASE 11 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("L' U2 L U L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void lCase12F2L(){
			System.out.println("LEFT HAND F2L CASE 12 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("L' U' L U' L' U' L U2 Uc' R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase13F2L(){
			System.out.println("LEFT HAND F2L CASE 13 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("L' U' L U2 L' U' L U L' U' L");
			rotateBlueFaceToFront();
		}
		
		private void lCase14F2L(){
			System.out.println("LEFT HAND F2L CASE 14 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("Uc' R U2 R' U' R U R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase15F2L(){
			System.out.println("LEFT HAND F2L CASE 15 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("U2 L' U' L U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void lCase16F2L(){
			System.out.println("LEFT HAND F2L CASE 16 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("Uc' U R U2 R' U R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase17F2L(){
			System.out.println("LEFT HAND F2L CASE 17 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("U' L' U2 L U' L' U L");
			rotateBlueFaceToFront();
		}
		
		private void lCase18F2L(){
			System.out.println("LEFT HAND F2L CASE 18 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeUp();
			
			move("Uc' U2 R U R' U R U' R'");
			rotateBlueFaceToFront();
		}
		
		private void lCase19F2L(){
			System.out.println("LEFT HAND F2L CASE 19 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeLeft();
			
			move("Uc' R U2 R' U' Uc");
			lCase1F2L();
		}
		
		private void lCase20F2L(){
			System.out.println("LEFT HAND F2L CASE 20 DETECTED");
			moveWhiteCornerToCorrectPositionUpLeftEdgeLeft();
			move("Uc'");
			for(int i =0; i <2; i++)
				move("R U R' U'");
			move("Uc");
			lCase6F2L();
		}
		
//		private void lCase21F2L(){
//			System.out.println("LEFT HAND F2L CASE 21 DETECTED");
//			move("Uc' R' U' R Uc");
//			lCase6F2L();
//		}
//		
//		private void lCase22F2L(){
//			System.out.println("LEFT HAND F2L CASE 22 DETECTED");
//			move("Uc' R' U' R Uc");
//			lCase1F2L();
//		}
		
		
		private boolean recognizeLCase1F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int top2 = cube.getFaceUp()[1][0];
			int left2 = cube.getFaceLeft()[0][1];
			boolean value = front  == BOTTOM &&
						 top    == top2   &&
						 left   == left2;
			return value;
		}
		
		private boolean recognizeLCase2F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int top2 = cube.getFaceUp()[1][0];
			int left2 = cube.getFaceLeft()[0][1];
			boolean value = front  == BOTTOM &&
						 top    == left2 &&
						 left   == top2;
			return value;
		}
		
		private boolean recognizeLCase3F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int front1 = cube.getFaceFront()[0][1];
			int top1 = cube.getFaceUp()[2][1];

			boolean value = front  == BOTTOM &&
						 top    == top1   &&
						 left   == front1;
			return value;
		}
		
		private boolean recognizeLCase4F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int front1 = cube.getFaceFront()[0][1];
			int top1 = cube.getFaceUp()[2][1];

			boolean value = front  == BOTTOM &&
						 top	   == front1  &&
						 left   == top1;
			return value;
		}
		
		private boolean recognizeLCase5F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int right = cube.getFaceRight()[0][1];
			int top1 = cube.getFaceUp()[1][2];

			boolean value = front  == BOTTOM &&
						 top    == top1   &&
						 left   == right;
			return value;
		}
		
		private boolean recognizeLCase6F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int right = cube.getFaceRight()[0][1];
			int top1 = cube.getFaceUp()[1][2];

			boolean value = front  == BOTTOM &&
						 top    == right  &&
						 left   == top1;
			return value;
		}
		
		private boolean recognizeLCase7F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int back = cube.getFaceBack()[0][1];
			int top1 = cube.getFaceUp()[0][1];

			boolean value = front  == BOTTOM &&
						 top    == top1   &&
						 left   == back;
			return value;
		}
		
		private boolean recognizeLCase8F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int back = cube.getFaceBack()[0][1];
			int top1 = cube.getFaceUp()[0][1];

			boolean value = front  == BOTTOM &&
						 top    == back   &&
						 left   == top1;
			return value;
		}
		
		private boolean recognizeLCase9F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int left1 = cube.getFaceLeft()[1][2];
			int front1 = cube.getFaceFront()[1][0];

			boolean value = front  == BOTTOM &&
					 	 top    == front1 &&
					 	 left   == left1;
			return value;
		}
		
		private boolean recognizeLCase10F2L(){
			int front = cube.getFaceFront()[0][0];
			int top = cube.getFaceUp()[2][0];
			int left = cube.getFaceLeft()[0][2];

			int left1 = cube.getFaceLeft()[1][2];
			int front1 = cube.getFaceFront()[1][0];

			boolean value = front  == BOTTOM &&
					 	 top    == left1  &&
					 	 left   == front1;
			return value;
		}
		
		private boolean recognizeLCase11F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int top1 = cube.getFaceUp()[1][0];
			int left1 = cube.getFaceLeft()[0][1];
			
			boolean value = top   == BOTTOM &&
						 front == left1 &&
						 left  == top1;
			
			return value;
		}
		
		private boolean recognizeLCase12F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int top1 = cube.getFaceUp()[1][0];
			int left1 = cube.getFaceLeft()[0][1];
			
			boolean value = top   == BOTTOM &&
						 front == top1 &&
						 left  == left1;
			
			return value;
		}
		
		private boolean recognizeLCase13F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int front2 = cube.getFaceFront()[0][1];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = top   == BOTTOM &&
					 	 front == front2 &&
					 	 left  == top2;
			
			return value;
		}
		
		private boolean recognizeLCase14F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int front2 = cube.getFaceFront()[0][1];
			int top2 = cube.getFaceUp()[2][1];
			
			boolean value = top   == BOTTOM &&
					 	 front == top2   &&
					 	 left  == front2;
			
			return value;
		}
		
		private boolean recognizeLCase15F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int right = cube.getFaceRight()[0][1];
			int top2 = cube.getFaceUp()[1][2];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == right  &&
				 	 	 left  == top2;
			
			return value;
		}
		
		private boolean recognizeLCase16F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int right = cube.getFaceRight()[0][1];
			int top2 = cube.getFaceUp()[1][2];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == top2   &&
				 	 	 left  == right;
			
			return value;
		}
		
		private boolean recognizeLCase17F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == back   &&
				 	 	 left  == top2;
			
			return value;
		}
		
		private boolean recognizeLCase18F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int back = cube.getFaceBack()[0][1];
			int top2 = cube.getFaceUp()[0][1];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == top2   &&
				 	 	 left  == back;
			
			return value;
		}
		
		private boolean recognizeLCase19F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int front2 = cube.getFaceFront()[1][0];
			int left2 = cube.getFaceLeft()[1][2];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == front2 &&
				 	 	 left  == left2;
			
			return value;
		}
		
		private boolean recognizeLCase20F2L(){
			int top = cube.getFaceUp()[2][0];
			int front = cube.getFaceFront()[0][0];
			int left = cube.getFaceLeft()[0][2];
			
			int front2 = cube.getFaceFront()[1][0];
			int left2 = cube.getFaceLeft()[1][2];
			
			boolean value = top   == BOTTOM &&
				 	 	 front == left2  &&
				 	 	 left  == front2;
			
			return value;
		}
		
		private boolean recognizeRCase30F2L(){
			int front = cube.getFaceFront()[2][2];
			
			return front == BOTTOM;
		}
		
		private boolean recognizeLCase30F2L(){
			int front = cube.getFaceFront()[2][0];
			
			return front == BOTTOM;
		}
		
		
		private boolean isTwoLayer(){
			int[][] front = cube.getFaceFront();
			int[][] left = cube.getFaceLeft();
			int[][] right = cube.getFaceRight();
			int[][] back = cube.getFaceBack();
			
			for(int i =1; i <RubikCube.cubeSize; i++){
				for (int j = 0; j < RubikCube.cubeSize; j++) {
					if(front[i][j] != FRONT){
						return false;
					}
					if(left[i][j] != LEFT){
						return false;
					}
					if(right[i][j] != RIGHT){
						return false;
					}
					if(back[i][j] != BACK){
						return false;
					}
					
				}
			}
			
			return true;
		}
		
	//========================END FIRST TWO LAYER============================
	
	//========================ORIENTATION LAST LAYER=========================
		private void OLL(){
			solveCrossThirdLayer();
			solveOppositeFace();
		}
		
		private void solveCrossThirdLayer() {
			if(!isCrossThirdLayer()){
				//case 1: don't have edge on the third layer
				if(isNotHaveAnyEdge()){
					dontHaveAnyEdge();
					return;
				}
				//case 2: have 2 edges on the third layer
				//case 2.1: 2 edge is opposite
				if(isOppositeEdge()){
					edgeIsOpposite();
					return;
				}
				
				//case 2.2: 2 edge is square
				edgeIsSquare();
			}
		}
		
		private void dontHaveAnyEdge(){
			for(int i = 0 ; i < 4; i++){
				move("L' R Rc' U");
			}
			
			for(int i = 0 ; i <4; i ++){
				move("L R' Rc U");
			}
		}
		
		private void edgeIsOpposite(){
			int[][]up = cube.getFaceUp();
			int back 	= up[0][1];
			int left 	= up[1][0];
			int right = up[1][2];
			int front = up[2][1];

			
			if(front == UP && back == UP){
				up();
				edgeIsOpposite();
			}
			
			if(left == UP && right == UP){
				move("F R U R' U' F'");
			}
		}
		
		private void edgeIsSquare(){
			int[][]up = cube.getFaceUp();
			int back 	= up[0][1];
			int left 	= up[1][0];
			int right = up[1][2];
			int front = up[2][1];

			
			if(left == UP && front == UP){
				up();
				move("F U R U' R' F'");
				return;
			}

			if(left == UP && back == UP){
				move("F U R U' R' F'");
				return;
			}
			
			if(right == UP && front == UP){
				up2();
				move("F U R U' R' F'");
				return;
			}
			
			if(right == UP && back == UP){
				upStar();
				move("F U R U' R' F'");
			}
			
		}
		
		private boolean isNotHaveAnyEdge(){
			int[][]up = cube.getFaceUp();
			int back 	= up[0][1];
			int left 	= up[1][0];
			int right = up[1][2];
			int front = up[2][1];
			boolean value = back  != UP &&
						 front != UP &&
						 left  != UP &&
						 right != UP ;
			return value;
		}
		
		private boolean isOppositeEdge(){
			int[][]up = cube.getFaceUp();
			int back 	= up[0][1];
			int left 	= up[1][0];
			int right = up[1][2];
			int front = up[2][1];
			boolean value = (back  == UP && front == UP) ||
						 (left  == UP && right == UP) ;
			
			return value;
		}
		
		private boolean isCrossThirdLayer(){
			int[][]up = cube.getFaceUp();
			int back 	= up[0][1];
			int left 	= up[1][0];
			int right = up[1][2];
			int front = up[2][1];
			
			boolean value = back  == UP &&
						 front == UP &&
						 left  == UP &&
						 right == UP ;
			return value;
		}
		
		private void solveOppositeFace() {
			while(!isOppositeFaceCompleted()){
				//case 1: don't have any corner
				if(isNotZeroCorner())
					doNotHaveAnyCorner();
				//case 2: only one corner
				if(isOnlyOneCorner())
					onlyOneCorner();
				//case 3: two corner
				if(isTwoCorner())
					twoCorner();
			}
		}
		
		private void doNotHaveAnyCorner(){
			//case 1: two opposite corner
			//case 2: only one opposite corner
			int frontL = cube.getFaceFront()[0][0];
			int frontR = cube.getFaceFront()[0][2];
			
			int backL = cube.getFaceBack()[0][2];
			int backR = cube.getFaceBack()[0][0];
			
			if(frontL == UP){
				if(frontR == UP ){
					if(backR == UP){//ok
						rightFishMove();
						move("L' Rc' U' R U L U' R' U Rc");
					}else if (backR != UP){//ok
						upStar();
						leftFishMove();
						up2();
						leftFishMove();
						upStar();
					}
				}else if(frontR != UP){//ok
					leftFishMove();
					up2();
					leftFishMove();
					up2();
				}
			}else if(frontL != UP){
				if(frontR == UP){//ok
					rightFishMove();up2();
					rightFishMove();up2();
				}else if(frontR != UP){
					if(backL == UP){//ok
						rightFishMove();up();
						rightFishMove();upStar();
					}else if (backL != UP){//ok
						rightFishMove();
						upStar();
						rightFishMove();
						up();
					}
				}
			}
			
		}
		
		private void onlyOneCorner(){
			if(cube.getFaceUp()[0][0] == UP){
				int back = cube.getFaceBack()[0][0];
				if(back == UP){
					up2();
					leftFishMove();
					up2();
				}else{
					upStar();
					rightFishMove();
					up();
				}
			}else if(cube.getFaceUp()[0][2] == UP){
				int back = cube.getFaceBack()[0][2];
				if(back == UP){
					up2();
					rightFishMove();
					up2();
				}else{
					up();
					leftFishMove();
					upStar();
				}
			}else if(cube.getFaceUp()[2][0] == UP){
				int front = cube.getFaceFront()[0][2];
				if(front == UP){
					rightFishMove();
				}else{
					upStar();
					leftFishMove();
					up();
				}
			}else if(cube.getFaceUp()[2][2] == UP){
				int front = cube.getFaceFront()[0][0];
				if(front == UP){
					leftFishMove();
				}else{
					up();
					rightFishMove();
					upStar();
				}
			}
		}
		
		private void twoCorner(){
			if(isWineBottle()){
				wineBottle();
				//case 1: wine bottle with 2 corner parallel
				//case 2: wine bottom with 2 corner opposite
			}else{
				//case 3: number eight
				int[][] up = cube.getFaceUp();
				int topLeft = up[0][0];
				
				if(topLeft == UP){
					int back = cube.getFaceBack()[0][0];
					if(back == UP){
						up2();
						move("L' Rc' U' R' U L U' R U Rc");
						up2();
					}else{
						move("L' Rc' U' R' U L U' R U Rc");
					}
				}else{
					int back = cube.getFaceBack()[0][2];
					if(back == UP){
						up();
						move("L' Rc' U' R' U L U' R U Rc");
						upStar();
					}else{
						upStar();
						move("L' Rc' U' R' U L U' R U Rc");
						up();
					}
				}
			}
		}
		
		private void wineBottle(){
			for(int i = 0; i < 4; i++){
				int topLeft = cube.getFaceUp()[0][0];
				int bottomLeft = cube.getFaceUp()[2][0];
				if(topLeft == UP  && bottomLeft == UP){
					int right = cube.getFaceRight()[0][0];
					if(right == UP){
						//case 1: wine bottle with 2 corner parallel
						move("Fc' U' R' U R U' R' U L U' R U R' U' R U Fc");
					}else{
						//case 2: wine bottom with 2 corner opposite
						move("Rc' R U L' U' R' U L U' Rc");
					}
					quickFixBlueToCenter();
					break;
				}
				up();
			}
		}
		
		private boolean isWineBottle(){
			int topLeft = cube.getFaceUp()[0][0];
			int bottomRight = cube.getFaceUp()[2][2];
			
			boolean value = (topLeft == UP && bottomRight != UP) ||
						 (topLeft != UP && bottomRight == UP);
			return value;
		}
		
		private boolean isNotZeroCorner(){
			int[][] up = cube.getFaceUp();
			int counter = 0;
			for(int i = 0 ; i < up.length; i+=2){
				for (int j = 0; j < up.length; j+=2) {
					if(up[i][j] == UP){
						counter++;
					}
				}
			}
			return counter == 0;
		}
		
		private boolean isOnlyOneCorner(){
			int[][] up = cube.getFaceUp();
			int counter = 0;
			for(int i = 0 ; i < up.length; i+=2){
				for (int j = 0; j < up.length; j+=2) {
					if(up[i][j] == UP){
						counter++;
					}
				}
			}
			return counter == 1;
		}
		
		private boolean isTwoCorner(){
			int[][] up = cube.getFaceUp();
			int counter = 0;
			for(int i = 0 ; i < up.length; i+=2){
				for (int j = 0; j < up.length; j+=2) {
					if(up[i][j] == UP){
						counter++;
					}
				}
			}
			return counter == 2;
		}
		
		private boolean isOppositeFaceCompleted(){
			int[][] up = cube.getFaceUp();
			for(int i = 0 ; i <up.length; i++){
				for (int j = 0; j < up.length; j++) {
					if(up[i][j]!= UP){
						return false;
					}
				}
			}
			return true;
		}
	
	//========================END ORIENTATION LAST LAYER=====================
	
	//========================PERMUTATION LAST LAYER=========================
		private void PLL(){
			while(!isCubeSolved()){
				if(recognizeCase1PLL()){
					case1PLL();break;
				}else if(recognizeCase2PLL()){
					case2PLL();break;
				}else if(recognizeCase3PLL()){
					case3PLL();break;
				}else if(recognizeCase4PLL()){
					case4PLL();break;
				}else if(recognizeCase5PLL()){
					case5PLL();break;
				}else if(recognizeCase6PLL()){
					case6PLL();break;
				}else if(recognizeCase7PLL()){
					case7PLL();break;
				}else if(recognizeCase8PLL()){
					case8PLL();break;
				}else if(recognizeCase9PLL()){
					case9PLL();break;
				}else if(recognizeCase10PLL()){
					case10PLL();break;
				}else if(recognizeCase11PLL()){
					case11PLL();break;
				}else if(recognizeCase12PLL()){
					case12PLL();break;
				}else if(recognizeCase13PLL()){
					case13PLL();break;
				}else if(recognizeCase14PLL()){
					case14PLL();break;
				}else if(recognizeCase15PLL()){
					case15PLL();break;
				}else if(recognizeCase16PLL()){
					case16PLL();break;
				}else if(recognizeCase17PLL()){
					case17PLL();break;
				}else if(recognizeCase18PLL()){
					case18PLL();break;
				}else if(recognizeCase19PLL()){
					case19PLL();break;
				}else if(recognizeCase20PLL()){
					case20PLL();break;
				}else if(recognizeCase21PLL()){
					case21PLL();break;
				}
				up();
			}
			
			if(isCubeSolved()){
				pn.setIsComplete(true);
				pn.cubeIsSolvedAction();
			}
		}
		
		private void case1PLL(){
			System.out.println("PLL CASE 1 DETECTED");
			move("R2 U' R' U' R U R U R U' R");
			quickRotateUpToComplete();
		}
		
		private void case2PLL(){
			System.out.println("PLL CASE 2 DETECTED");
			move("L2 U L U L' U' L' U' L' U L'");
			quickRotateUpToComplete();
		}
		
		private void case3PLL(){
			System.out.println("PLL CASE 3 DETECTED");
			move("Rc' U L' U R2 U' L U R2 U2 Rc");
			quickRotateUpToComplete();
		}
		
		private void case4PLL(){
			System.out.println("PLL CASE 4 DETECTED");
			move("Rc' U' R U' L2 U R' U' L2 U2 Rc");
			quickRotateUpToComplete();
		}
		
		private void case5PLL(){
			System.out.println("PLL CASE 5 DETECTED");
			move("R2 U' R' U' R U R U R L' U2 R U' R' U2 L R");
			quickRotateUpToComplete();
		}
		
		private void case6PLL(){
			System.out.println("PLL CASE 6 DETECTED");
			move("L' U R U' L U L' U R' U' L U2 R U2 R'");
			quickRotateUpToComplete();
		}
		
		private void case7PLL(){
			System.out.println("PLL CASE 7 DETECTED");
			move("Rc U R' U' L U R U' Rc2 L2 U' R U L U' R' U Rc");
			quickRotateUpToComplete();
		}
		
		private void case8PLL(){
			System.out.println("PLL CASE 8 DETECTED");
			move("L' U' L F2 D R' U R U' R D' F2");
			quickRotateUpToComplete();
		}
		
		private void case9PLL(){
			System.out.println("PLL CASE 9 DETECTED");
			move("R2 Uc D R' U R' U' R D' F2 L' U L");
			quickRotateUpToComplete();
		}
		
		private void case10PLL(){
			System.out.println("PLL CASE 10 DETECTED");
			move("R U R' F2 D' L U' L' U L' D F2");
			quickRotateUpToComplete();
		}
		
		private void case11PLL(){
			System.out.println("PLL CASE 11 DETECTED");
			move("L2 Uc' D' L U' L U L' D F2 R U' R'");
			quickRotateUpToComplete();
		}
		
		private void case12PLL(){
			System.out.println("PLL CASE 12 DETECTED");
			move("R U2 R' U' R U2 L' U R' U' L");
			quickRotateUpToComplete();
		}
		
		private void case13PLL(){
			System.out.println("PLL CASE 13 DETECTED");
			move("L' U2 L U L' U2 R U' L U R'");
			quickRotateUpToComplete();
		}
		
		private void case14PLL(){
			System.out.println("PLL CASE 14 DETECTED");
			move("F R U' R' U' R U R' F' R U R' U' R' F R F'");
			quickRotateUpToComplete();
		}
		
		private void case15PLL(){
			System.out.println("PLL CASE 15 DETECTED");
			move("R U R' U' R' F R2 U' R' U' R U R' F'");
			quickRotateUpToComplete();
		}
		
		private void case16PLL(){
			System.out.println("PLL CASE 16 DETECTED");
			move("L U2 L' U2 L F' L' U' L U L F L2");
//			move("L U2 L' U2 L F' L' U' L U F U L2 Rc' U L Rc B'");
			quickRotateUpToComplete();
		}
		
		private void case17PLL(){
			System.out.println("PLL CASE 17 DETECTED");
			move("R' U2 R U2 R' F R U R' U' R' F' R2");
			quickRotateUpToComplete();
		}
		
		private void case18PLL(){
			System.out.println("PLL CASE 18 DETECTED");
			move("R L U2 R' L' Uc L' R' U2 L R");
			quickRotateUpToComplete();
		}
		
		private void case19PLL(){
			System.out.println("PLL CASE 19 DETECTED");
			move("U2 R' U' R2 U R U R' U' R U R U' R U' R'");
			quickRotateUpToComplete();
		}
		
		private void case20PLL(){
			System.out.println("PLL CASE 20 DETECTED");
			move("R' U L' U2 R U' L");
			move("R' U L' U2 R U' L");
			quickRotateUpToComplete();
		}
		
		private void case21PLL(){
			System.out.println("PLL CASE 21 DETECTED");
			move("L U' R U2 L' U R'");
			move("L U' R U2 L' U R'");
			quickRotateUpToComplete();
		}
		
		private boolean recognizeCase1PLL(){
			int[] front = cube.getFaceFront()[0];
			for(int i = 1 ; i < front.length; i++){
				if(front[i] != front[0]){
					return false;
				}
			}
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][2];
			
			if(right1 != right2){
				return false;
			}
			
			int right = cube.getFaceRight()[0][1];
			int left = cube.getFaceLeft()[0][1];
			int back = cube.getFaceBack()[0][1];
			
			int left1 = cube.getFaceLeft()[0][0];
			int back1 = cube.getFaceBack()[0][0];
			
			if(right == back1 && back == left1 && left == right1)
				return true;
			return false;
		}
		
		private boolean recognizeCase2PLL(){
			int[] front = cube.getFaceFront()[0];
			for(int i = 1 ; i < front.length; i++){
				if(front[i] != front[0]){
					return false;
				}
			}
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][2];
			
			if(right1 != right2){
				return false;
			}
			
			int right = cube.getFaceRight()[0][1];
			int left = cube.getFaceLeft()[0][1];
			int back = cube.getFaceBack()[0][1];
			
			int left1 = cube.getFaceLeft()[0][0];
			int back1 = cube.getFaceBack()[0][0];
			
			if(right == left1 && left == back1 && back == right1)
				return true;
			return false;
		}
		
		private boolean recognizeCase3PLL(){
			int front1 = cube.getFaceFront()[0][0];
			int front2 = cube.getFaceFront()[0][1];
			if(front1 != front2){
				return false;
			}
			
			int left1 = cube.getFaceLeft()[0][2];
			int left2 = cube.getFaceLeft()[0][1];
			if(left1 != left2){
				return false;
			}
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][2];
			if(right1 != right2){
				return false;
			}
			
			int front = cube.getFaceFront()[0][2];
			if(front != front1){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase4PLL(){
			int front1 = cube.getFaceFront()[0][2];
			int front2 = cube.getFaceFront()[0][1];
			if(front1 != front2){
				return false;
			}
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][1];
			if(right1 != right2){
				return false;
			}

			int left1 = cube.getFaceLeft()[0][0];
			int left2 = cube.getFaceLeft()[0][2];
			if(left1 != left2){
				return false;
			}
			
			int front = cube.getFaceFront()[0][0];
			if(front != front1)
				return true;
			return false;
		}
		
		private boolean recognizeCase5PLL(){
			int[] front = cube.getFaceFront()[0];
			for(int i = 1; i<front.length; i++){
				if(front[i] != front[0]){
					return false;
				}
			}
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][1];
			
			int left1 = cube.getFaceLeft()[0][1];
			int left2 = cube.getFaceLeft()[0][2];
			
			if(right1 == left1 && right2 == left2){
				return true;
			}
			return false;
		}
		
		private boolean recognizeCase6PLL(){
			int left1 = cube.getFaceLeft()[0][0];
			int left2 = cube.getFaceLeft()[0][1];
			if(left1 != left2){
				return false;
			}
			
			int back1 = cube.getFaceBack()[0][2];
			int back2 = cube.getFaceBack()[0][1];
			if(back1 != back2){
				return false;
			}
			
			int front1 = cube.getFaceFront()[0][2];
			int front2 = cube.getFaceFront()[0][1];
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][1];
			
			if(front1 == right2 && front2 == right1){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase7PLL(){
			int left1 = cube.getFaceLeft()[0][2];
			int left2 = cube.getFaceLeft()[0][0];
			
			int front = cube.getFaceFront()[0][1];
			int back = cube.getFaceBack()[0][1];
			
			int right1 = cube.getFaceRight()[0][0];
			int right2 = cube.getFaceRight()[0][2];
			
			if((left1 == right1 && front == right1) &&(left2 == right2 && back == right2))
				return true;
			return false;
		}
		
		private boolean recognizeCase8PLL(){
			int[] left = cube.getFaceLeft()[0];
			if(left[2] != left[1]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[2]){
				return false;
			}
			
			int front = cube.getFaceFront()[0][1];
			if(front == right[0]){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase9PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[1] != front[2]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[2]){
				return false;
			}
			
			int right = cube.getFaceRight()[0][1];
			if(right == left[0]){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase10PLL(){
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[1]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[2]){
				return false;
			}
			
			int front = cube.getFaceFront()[0][1];
			if(front == left[2]){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase11PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[1]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[2]){
				return false;
			}
			
			int left = cube.getFaceLeft()[0][1];
			if(left == right[0]){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase12PLL(){
			int[] left = cube.getFaceLeft()[0];
			for(int i = 1 ; i < left.length; i++){
				if(left[i] != left[0]){
					return false;
				}
			}
			
			int[] front = cube.getFaceFront()[0];
			if(front[1] != front[2])
				return false;
			
			int[] right = cube.getFaceRight()[0];
			if(right[1] != right[2])
				return false;
			
			int[] back = cube.getFaceBack()[0];
			if(back[1] != back[2])
				return false;
			
			if(back[0] == front[1])
				return true;
			
			return false;
		}
		
		private boolean recognizeCase13PLL(){
			int[] right = cube.getFaceRight()[0];
			for(int i = 1 ; i < right.length; i++){
				if(right[i] != right[0]){
					return false;
				}
			}
			
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[1])
				return false;
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[1])
				return false;
			
			int[] back = cube.getFaceBack()[0];
			if(back[0] != back[1])
				return false;
			
			if(back[2] == front[1])
				return true;
			
			return false;
		}
		
		private boolean recognizeCase14PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[1]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[1] != right[2]){
				return false;
			}
			
			int left = cube.getFaceLeft()[0][1];
			int back = cube.getFaceBack()[0][1];
			
			if(front[2] == left && right[0] == back){
				return true;
			}
			
			
			return false;
		}
		
		private boolean recognizeCase15PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[1])
				return false;
			
			int[] back = cube.getFaceBack()[0];
			if(back[2] != back[1])
				return false;
			
			if(front[2] == back[0]){
				return true;
			}
			return false;
		}
		
		private boolean recognizeCase16PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[2]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[1]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[2] == front[1] && left[1] == front[0]){
				return true;
			}
			return false;
		}
		
		private boolean recognizeCase17PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[2]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[1] != left[2]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[1] == front[0] && front[1] == right[0]){
				return true;
			}
			
			return false;
		}
		
		private boolean recognizeCase18PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[2]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[2]){
				return false;
			}
			
			int[] back = cube.getFaceBack()[0];
			if(back[0] != back[2]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[2]){
				return false;
			}
			
			if(front[0] == back[1] && right[0] == left[1])
				return true;
			return false;
		}
		
		private boolean recognizeCase19PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[2]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[2]){
				return false;
			}
			
			int[] back = cube.getFaceBack()[0];
			if(back[0] != back[2]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[2]){
				return false;
			}
			
			if(front[1] == right[0] && back[1] == left[0]){
				return true;
			}
			return false;
		}
		
		private boolean recognizeCase20PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[0] != front[1]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[0] != right[1]){
				return false;
			}
			
			int[] back = cube.getFaceBack()[0];
			if(back[0] != back[1]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[0] != left[1]){
				return false;
			}
			
			if(back[2] == front[0])
				return true;
			
			return false;
		}
		
		private boolean recognizeCase21PLL(){
			int[] front = cube.getFaceFront()[0];
			if(front[2] != front[1]){
				return false;
			}
			
			int[] right = cube.getFaceRight()[0];
			if(right[2] != right[1]){
				return false;
			}
			
			int[] back = cube.getFaceBack()[0];
			if(back[2] != back[1]){
				return false;
			}
			
			int[] left = cube.getFaceLeft()[0];
			if(left[2] != left[1]){
				return false;
			}
			
			if(back[0] == front[2])
				return true;
			return false;
		}
		
		private boolean isCubeSolved(){
			return cube.isComplete();
		}
	
	//========================END PERMUTATION LAST LAYER=====================
	
	//========================END SOLVE THE CUBE=============================
}
