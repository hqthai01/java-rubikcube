package com.hqthai01.rubiksolver.ai;

import com.hqthai01.rubiksolver.constants.RubikConstants;
import com.hqthai01.rubiksolver.gui.JRubikPanel;
import com.hqthai01.rubiksolver.gui.Main;
import com.hqthai01.rubiksolver.model.RubikCube;

/**
 * @author Thái Feb 3, 2015
 */
public class RubikCubeSolverBeginner implements RubikConstants {

	private RubikCube cube;
	private JRubikPanel pn;
	private int DELAY = 150;
	private boolean isSolving = false;
	private Main main;
	
	public void setDelay(int delay){
		this.DELAY = delay;
	}
	
	public int getDelay(){
		return this.DELAY;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public void setPn(JRubikPanel pn){
		this.pn = pn;
	}
	
	public void setRubikCube(RubikCube cube){
		this.cube = cube;
	}
	
	public void solveTheCube(RubikCube cube){
		if(!isSolving){
			isSolving = true;
			this.cube = cube;
			main.setText("Rotate to default position:\n");
			rotateWhiteFaceIsBottom();
			rotateBlueFaceIsFront();
			main.setText("\n\nSolve bottom cross:\n");
			solveBottomCross();
			main.setText("\n\nSolve the first layer:\n");
			solveTheFirstLayer();
			main.setText("\n\nSolve the second layer:\n");
			solveTheSecondLayer();
			main.setText("\n\nSolve the third layer:\n");
			solveTheThirdLayer();
			isSolving = false;
		}
	}

	private void rotateWhiteFaceIsBottom() {
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

	private void rotateBlueFaceIsFront() {
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

	//========================SOLVE CROSS AT THE BOTTOM==========================
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
	
	//========================END SOLVE CROSS AT BOTTOM==========================
	
	//==========================SOLVE THE FIRST LAYER============================
	private void solveTheFirstLayer() {
		while(!isCorrectFirstLayer()){
			//case 1: white corner at up face
			whiteCornerAtUpFace();
			
			//case 2: white corner at front face
			whiteCornerAtFrontFace();
			
			//case 3: white corner at left face
			whiteCornerAtLeftFace();
			
			//case 4: white corner at right face
			whiteCornerAtRightFace();
			
			//case 5: white corner at back face
			whiteCornerAtBackFace();
			
			//case 6: white corner at bottom face
			whiteCornerAtBottomFace();
		}
	}
	
	private void whiteCornerAtFrontFace(){
		while(hasWhiteCornerAtFrontFace()){
			atFrontFaceBottomLeft();
			atFrontFaceBottomRight();
			
			atFrontFaceUpLeft();
			atFrontFaceUpRight();
		}
	}
	
	private void atFrontFaceBottomLeft(){
		int[][] front = cube.getFaceFront();
		
		if(front[2][0] == BOTTOM){
			LSUL();
		}
		atFrontFaceUpLeft();
	}
	
	private void atFrontFaceUpLeft(){
		int[][] front = cube.getFaceFront();
		if(front[0][0] == BOTTOM){
			int up = cube.getFaceUp()[2][0];
			
			if(up == FRONT){
				upStar();LSUL();
			}else if (up == RIGHT){
				upStar();RURS();
			}else if (up == BACK){
				up2();rotateUp();RURS();rotateUpStar();
			}else if (up == LEFT){
				up();LULS();
			}
		}
	}
	
	private void atFrontFaceBottomRight(){
		int[][] front = cube.getFaceFront();
		
		if(front[2][2] == BOTTOM){
			RUSRS();
		}
		
		atFrontFaceUpRight();
	}
	
	private void atFrontFaceUpRight(){
		int[][] front = cube.getFaceFront();
		if(front[0][2] == BOTTOM){
			int up = cube.getFaceUp()[2][2];
			
			if(up == FRONT){
				up();RUSRS();
			}else if (up == RIGHT){
				upStar();RSUSR();
			}else if (up == BACK){
				up2();rotateUpStar();LSUSL();rotateUp();
			}else if (up == LEFT){
				up();LSUSL();
			}
		}
	}
	
	private void whiteCornerAtLeftFace(){
		while(hasWhiteCornerAtLeftFace()){
			atLeftFaceUpFrontAndBack();
			atLeftFaceBottomFront();
			atLeftFaceBottomBack();
		}
	}
	
	private void atLeftFaceBottomFront(){
		int left[][] = cube.getFaceLeft();
		if(left[2][0] == BOTTOM){
			LULS();up2();
			whiteCornerAtFrontFace();
		}
	}
	
	private void atLeftFaceUpFrontAndBack(){
		int left[][] = cube.getFaceLeft();
		if(left[0][0] == BOTTOM || left[0][2] == BOTTOM){
			upStar();
			whiteCornerAtFrontFace();
		}
	}
	
	private void atLeftFaceBottomBack(){
		int left[][] = cube.getFaceLeft();
		if(left[2][2] == BOTTOM){
			LSUSL();
			whiteCornerAtFrontFace();
		}
	}
	
	private void whiteCornerAtRightFace(){
		while(hasWhiteCornerAtRightFace()){
			atRightFaceUpFrontAndBack();
			atRightFaceBottomBack();
			atRightFaceBottomFront();
		}
	}
	
	private void atRightFaceBottomFront(){
		int right[][] = cube.getFaceRight();
		if(right[2][0] == BOTTOM){
			RURS();
			whiteCornerAtFrontFace();
		}
	}
	
	private void atRightFaceUpFrontAndBack(){
		int right[][] = cube.getFaceRight();
		if(right[0][0] == BOTTOM || right[0][2] == BOTTOM){
			up();
			whiteCornerAtFrontFace();
		}
	}
	
	private void atRightFaceBottomBack(){
		int right[][] = cube.getFaceRight();
		if(right[2][2] == BOTTOM){
			RSUSR();up2();
			whiteCornerAtFrontFace();
		}
	}
	
	private void whiteCornerAtBackFace(){
		while(hasWhiteCornerAtBackFace()){
			atBackFaceUpLeftAndRight();
			atBackFaceBottomLeft();
			atBackFaceBottomRight();
		}
	}
	
	private void atBackFaceUpLeftAndRight(){
		int[][] back = cube.getFaceBack();
		if(back[0][0] == BOTTOM || back[0][2] == BOTTOM){
			up2();
			whiteCornerAtFrontFace();
		}
	}
	
	private void atBackFaceBottomLeft(){
		int[][] back = cube.getFaceBack();
		if(back[2][2] == BOTTOM){
			LULS();
			whiteCornerAtUpFace();
		}
	}
	
	private void atBackFaceBottomRight(){
		int[][] back = cube.getFaceBack();
		if(back[2][0] == BOTTOM){
			RSUSR();
			whiteCornerAtUpFace();
		}
	}
	
	private void whiteCornerAtUpFace(){
		while(hasWhiteCornerAtUpFace()){
			atUpFaceFrontLeft();
			atUpFaceFrontRight();
			atUpFaceBackLeftAndRight();
		}
	}
	
	private void atUpFaceFrontLeft(){
		int[][] up = cube.getFaceUp();
		if(up[2][0] == BOTTOM){
			int front = cube.getFaceFront()[0][0];
			if(front == LEFT){
				leftStar();up2();left();up();LSUSL();
			}else if (front == FRONT){
				upStar();right();up2();rightStar();upStar();RURS();
			}else if (front == RIGHT){
				up2();rotateUp();right();up2();rightStar();upStar();RURS();rotateUpStar();
			}else if (front == BACK){
				up();rotateUpStar();leftStar();up2();left();up();LSUSL();rotateUp();
			}
		}
	}
	
	private void atUpFaceFrontRight(){
		int[][] up = cube.getFaceUp();
		if(up[2][2] == BOTTOM){
			up();
			atUpFaceFrontLeft();
		}
	}
	
	private void atUpFaceBackLeftAndRight(){
		int[][] up = cube.getFaceUp();
		if(up[0][0] == BOTTOM){
			upStar();
			atUpFaceFrontLeft();
		}
		
		if(up[0][2] == BOTTOM){
			up2();
			atUpFaceFrontLeft();
		}
		
	}
	
	private void whiteCornerAtBottomFace(){
		int[][] front = cube.getFaceFront();
		int[][] back = cube.getFaceBack();
		int[][] left = cube.getFaceLeft();
		int[][] right = cube.getFaceRight();
		
		if(front[2][0] != FRONT){
			LSUL();
			return;
		}
		
		if(right[2][0] != RIGHT){
			RURS();
			return;
		}
		
		if(back[2][0] != BACK){
			RSUSR();
			return;
		}
		
		if(left[2][0] != LEFT){
			LULS();
			return;
		}
	}
	
	private boolean hasWhiteCornerAtUpFace(){
		int[][] 	up = cube.getFaceUp();
		return 	up[0][0] == BOTTOM || 
				up[0][2] == BOTTOM || 
				up[2][0] == BOTTOM || 
				up[2][2] == BOTTOM;
	}
	
	private boolean hasWhiteCornerAtFrontFace(){
		int[][] 	front = cube.getFaceFront();
		return 	front[0][0] == BOTTOM || 
				front[0][2] == BOTTOM || 
				front[2][0] == BOTTOM || 
				front[2][2] == BOTTOM;
	}

	private boolean hasWhiteCornerAtBackFace(){
		int[][]	back = cube.getFaceBack();
		return 	back[0][0] == BOTTOM || 
				back[0][2] == BOTTOM || 
				back[2][0] == BOTTOM || 
				back[2][2] == BOTTOM;
	}
	
	private boolean hasWhiteCornerAtLeftFace(){
		int[][]	left = cube.getFaceLeft();
		return 	left[0][0] == BOTTOM || 
				left[0][2] == BOTTOM || 
				left[2][0] == BOTTOM || 
				left[2][2] == BOTTOM;
	}
	
	private boolean hasWhiteCornerAtRightFace(){
		int[][]	right = cube.getFaceRight();
		return 	right[0][0] == BOTTOM || 
				right[0][2] == BOTTOM || 
				right[2][0] == BOTTOM || 
				right[2][2] == BOTTOM;
	}
	
	/**
	 * Method check whether the layer 1 has all the corner at the right position of it.
	 * @return true if the bottom face is white and the layer 1 is complete
	 */
	private boolean isCorrectFirstLayer(){
		int[][] bottom = 	cube.getFaceBottom();
		int[][] front 	=	cube.getFaceFront();
		int[][] left 	=	cube.getFaceLeft();
		int[][] back 	=	cube.getFaceBack();
		int[][] right 	=	cube.getFaceRight();
		boolean value 	= 	(bottom[0][0] 	== BOTTOM && front[2][0] == FRONT	&& front[2][2]	== FRONT)	&&
						(bottom[0][2] 	== BOTTOM && left[2][0] 	== LEFT	&& left[2][2]	== LEFT)	&&
						(bottom[2][0] 	== BOTTOM && back[2][0] 	== BACK 	&& back[2][2]	== BACK)	&& 
						(bottom[2][2] 	== BOTTOM	&& right[2][0]	== RIGHT	&& right[2][2]	== RIGHT) ;
		return value;
	}
	
	//=======================END SOLVE THE FIRST LAYER=======================
	
	//=======================SOLVE THE SECOND LAYER==========================

	private void solveTheSecondLayer() {
		while(!isSolveTheSecondLayer()){
			edgeAtLayer3();
			edgeAtLayer2();
		}
	}
	
	private void edgeAtLayer3(){
			int front = cube.getFaceFront()[0][1];
			int up = cube.getFaceUp()[2][1];
			
			if(front == UP || up == UP){
				up();
				return;
			}
			if(front == FRONT){
				if(up == RIGHT){
					move("U R U' R' Uc U' L' U L Uc'");
				}else if(up == LEFT){
					move("U' L' U L Uc' U R U' R' Uc");
				}
			}else if(front == RIGHT){
				if(up == FRONT){
					move("U2 Uc L' U L Uc' U R U' R'");
				}else if(up == BACK){
					move("Uc R U' R' Uc U' L' U L Uc2");
				}
			}else if(front == LEFT){
				if(up == FRONT){
					move("U2 Uc' R U' R' Uc U' L' U L");
				}else if(up == BACK){
					move("Uc' L' U L Uc' U R U' R' Uc2");
				}
			}else if (front == BACK){
				if(up == RIGHT){
					move("Uc2 U L' U L Uc' U R U' R' Uc'");
				}else if(up == LEFT){
					move("Uc2 U' R U' R' Uc U' L' U L Uc");
					
				}
			}
		
	}
	
	private void edgeAtLayer2(){
		int front = cube.getFaceFront()[1][0];
		int right = cube.getFaceRight()[1][0];
		int back = cube.getFaceBack()[1][0];
		int left = cube.getFaceLeft()[1][0];
		
		if(front != FRONT){
			if(cube.getFaceLeft()[1][2] != UP){
				move("L' U L F' L F L'");
				return;
			}
		}
		
		if(right != RIGHT){
			if(cube.getFaceFront()[1][2] != UP){
				move("Uc L' U L F' L F L' Uc'");
				return;
			}
		}
		
		if(back != BACK){
			if(cube.getFaceRight()[1][2] != UP){
				move("Uc2 L' U L F' L F L' Uc2");
				return;
			}
		}
		
		if(left != LEFT){
			if(cube.getFaceBack()[1][2] != UP){
				move("Uc' L' U L F' L F L' Uc");
				return;
			}
		}
	}
	
	private boolean isSolveTheSecondLayer(){
		int[][] front 	= 	cube.getFaceFront();
		int[][] right	= 	cube.getFaceRight();
		int[][] back 	= 	cube.getFaceBack();
		int[][] left 	= 	cube.getFaceLeft();
		
		boolean value 	= 	(front[1][0] 	== FRONT 	&& front[1][2] == FRONT) &&
						(right[1][0] 	== RIGHT 	&& right[1][2] == RIGHT) &&
						(back[1][0] 	== BACK 	&& back[1][2] 	== BACK) 	&&
						(left[1][0] 	== LEFT 	&& left[1][2] 	== LEFT)	;
		return value;
	}
	
	//=======================END SOLVE THE SECOND LAYER======================

	//=======================SOLVE THE THIRD LAYER===========================
	
	private void solveTheThirdLayer(){
		while(!isCubeSolved()){
			main.setText("\nSolve the top cross:\n");
			solveCrossThirdLayer();
			main.setText("\n\nCorrect the top cross:\n");
			correctTheCrossAtThirdLayer();
			main.setText("\n\nSolve the opposite face:\n");
			solveOppositeFace();
			main.setText("\n\nSolve the third layer:\n");
			solveThirdLayer();
		}
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

	
	private void correctTheCrossAtThirdLayer(){
		if(!isCrossCorrect()){
			
			quickFixBlueToCenter();

			//case 1: opposite correct
			if(isOppositeCorrect()){
				move("R U R' U' R' F R2 U' R' U' R U R' F'");
			}else{//case 2: only one edge correct
				int left 	= cube.getFaceLeft()[0][1];
				int right = cube.getFaceRight()[0][1];
				int front = cube.getFaceFront()[0][1];
				
				if(front == FRONT){
					if(left == LEFT){
						move("U L' U' L U' L' U2 L U2");
						return;
					}else if(left == BACK && right != RIGHT){
						move("L' U' L U' L' U2 L");
						return;
					}
					
					if(right == RIGHT){
						move("U' R U R' U R U2 R' U2");
						return;
					}else if(right == BACK){
						move("R U R' U R U2 R'");
						return;
					}
				}
			}
		}
	}
	
	private boolean isOppositeCorrect(){
		int back 	= cube.getFaceBack()[0][1];
		int front = cube.getFaceFront()[0][1];
//		boolean value = (back  == BACK  && front == FRONT) ||
//					 (back  == LEFT  && front == RIGHT) ||
//					 (back  == FRONT && front == BACK) ||
//					 (back  == RIGHT && front == LEFT) ;
		boolean value = back == BACK && front == FRONT;
		return value;
	}
	
	private boolean isCrossCorrect(){
		int back 	= cube.getFaceBack()[0][1];
		int left 	= cube.getFaceLeft()[0][1];
		int right = cube.getFaceRight()[0][1];
		int front = cube.getFaceFront()[0][1];
		boolean value = back  == BACK  &&
					 front == FRONT &&
					 left  == LEFT  &&
					 right == RIGHT ;
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

	private void solveThirdLayer() {
		//case 1: cross exchange
		crossExchange();
		//case 2: triangle of three corner
		triangleOfThreeCorner();
		//case 3: opposite corner exchange
		oppositeCornerExchange();
		if(isCubeSolved()){
			pn.setIsComplete(isCubeSolved());
			pn.cubeIsSolvedAction();
		}
	}
	
	private void crossExchange(){
		int frontLeft = cube.getFaceFront()[0][0];
		int frontRight = cube.getFaceFront()[0][2];

		int backLeft = cube.getFaceBack()[0][2];
		int backRight = cube.getFaceBack()[0][0];
		if(frontLeft == BACK  && frontRight == BACK){
			if(backLeft == FRONT && backRight == FRONT){
				move("U2 R L U2 R' L' F' B' U2 F B");
			}
		}
	}
	
	private void triangleOfThreeCorner(){
		for(int i = 0; i <4 ; i++){
			if(isTriangleLeft()){
				move("Rc' U' R U' L2 U R' U' L2 U2 Rc");
				break;
			}
			if(isTriangleRight()){
				move("Rc' U L' U R2 U' L U R2 U2 Rc");
				break;
			}
			up();
		}
		int front = cube.getFaceFront()[0][1];
		while(front != FRONT){
			up();
			front = cube.getFaceFront()[0][1];
		}
	}
	
	private void oppositeCornerExchange(){
		for(int i = 0 ; i < 4; i++){
			if(isOppositeCorner()){
				move("Rc U R' U' L U R U' Rc2 L2 U' R U L U' R' U Rc");
				break;
			}
			up();
		}
		
		quickFixBlueToCenter();
	}
	
	
	private boolean isTriangleRight(){
		int rightBack = cube.getFaceRight()[0][2];
		int rightFront = cube.getFaceRight()[0][0];
		int frontLeft = cube.getFaceFront()[0][0];
		int frontMiddle = cube.getFaceFront()[0][1];
		
		int back = cube.getFaceBack()[0][2];
		
		boolean value = rightBack == rightFront  &&
					 frontLeft == frontMiddle &&
					 frontLeft == back ;
		return value;
	}
	
	private boolean isTriangleLeft(){
		int leftBack = cube.getFaceLeft()[0][2];
		int leftFront = cube.getFaceLeft()[0][0];
		int frontRight = cube.getFaceFront()[0][2];
		int frontMiddle = cube.getFaceFront()[0][1];
		
		int back = cube.getFaceBack()[0][0];
		
		boolean value = leftBack   == leftFront   &&
					 frontRight == frontMiddle &&
					 frontRight == back;
		return value;
	}
	
	private boolean isOppositeCorner(){
		int left = cube.getFaceLeft()[0][2];
		int right = cube.getFaceRight()[0][0];
		int front = cube.getFaceFront()[0][1];
		boolean value = left == right &&
					 left == front;
		return value;
	}
	
	private boolean isCubeSolved(){
		return cube.isComplete();
	}
	
	//=======================END SOLVE THE THIRD LAYER======================
	
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
	 * Quick move Right Up Right'
	 */
	private void RURS(){
		right();
		up();
		rightStar();
	}
	
	/**
	 * Quick move Right Up' Right'
	 */
	private void RUSRS(){
		right();
		upStar();
		rightStar();
	}

	/**
	 * Quick move Right' Up' Right
	 */
	private void RSUSR(){
		rightStar();
		upStar();
		right();
	}
	
	
	/**
	 * Quick move Left' Up' Left
	 */
	private void LSUSL(){
		leftStar();
		upStar();
		left();
	}
	
	/**
	 * Quick move Left' Up Left
	 */
	private void LSUL(){
		leftStar();
		up();
		left();
	}
	
	/**
	 * Quick move Left Up Left'
	 */
	private void LULS(){
		left();
		up();
		leftStar();
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
	
	public void move(String value){
		String[] listMove = value.split(" ");
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
	
	public void testMove(){
		move("R U R' U' R' F R2 U' R' U' R U R' F'");
		pn.setIsComplete(cube.isComplete());
	}
}
