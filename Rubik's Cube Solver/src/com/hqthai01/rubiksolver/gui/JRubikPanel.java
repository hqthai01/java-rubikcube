package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.hqthai01.rubiksolver.ai.RubikCubeSolverAdvance;
import com.hqthai01.rubiksolver.ai.RubikCubeSolverBeginner;
import com.hqthai01.rubiksolver.constants.Message;
import com.hqthai01.rubiksolver.constants.RubikConstants;
import com.hqthai01.rubiksolver.model.RubikCube;
import com.hqthai01.rubiksolver.pattern.observer.Observable;
import com.hqthai01.rubiksolver.pattern.observer.Observer;

/**
 * @author Thái Feb 1, 2015
 */
public class JRubikPanel extends JPanel implements RubikConstants, KeyListener, Observable{

	private static final long serialVersionUID = 0xAEF1L;

	private static JRubikPanel instance;
	
	private int cubeSize = 3;
	private int angle = 30;
	private int moves = 0;

	private String keyPressed = "";
	private String action = "";
	private String scramble = "";
	
	private boolean isComplete = true;
	private boolean canScramble = true;
	private boolean canRotateCube = false;
	private boolean showKeyNotation = false;
	
	private boolean isMoveRight = false;
	private boolean isMoveLeft = false;
	private boolean isMoveUp = false;
	private boolean isMoveDown = false;
	private boolean isMoveFront = false;
	private boolean isMoveBack = false;

	private boolean isMoveRightStar = false;
	private boolean isMoveLeftStar = false;
	private boolean isMoveUpStar = false;
	private boolean isMoveDownStar = false;
	private boolean isMoveFrontStar = false;
	private boolean isMoveBackStar = false;
	
	private boolean isSolving = false;
	
	private ImageIcon right;
	private ImageIcon up;
	private ImageIcon front;
	
	private ImageIcon rightStar;
	private ImageIcon upStar;
	private ImageIcon frontStar;

	private final int MOVES = 25;
	private ImageIcon icon;
	

	private Random rd = new Random();
	private RubikCube cube;
	private RubikCubeSolverBeginner solver;
	private RubikCubeSolverAdvance solverA;
	private Main main;
	
	public static JRubikPanel getInstance() {
		synchronized (JRubikPanel.class) {
			if (instance == null)
				return instance = new JRubikPanel();
		}
		return instance;
	}
	
	public void setMain(Main main){
		this.main = main;
	}

	private JRubikPanel() {
		init();
	}

	private void init() {
		RubikCube.cubeSize = cubeSize;
		cube = RubikCube.getInstance();
		solver = new RubikCubeSolverBeginner();
		solverA = new RubikCubeSolverAdvance();
		
		listObs = new ArrayList<Observer>();
		icon = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/cubepreview/key-cubes.png"));

		right = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/r.png"));
		up = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/u.png"));
		front = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/f.png"));

		rightStar = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/rs.png"));
		upStar = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/us.png"));
		frontStar = new ImageIcon(JRubikPanel.class.getResource("/com/hqthai01/rubiksolver/icons/fs.png"));
		

		setPreferredSize(new Dimension(800, 600));
		setSize(getPreferredSize());
		setFocusable(true);

		addKeyListener(this);
		solver.setPn(this);
		solver.setRubikCube(cube);
		
		solverA.setPn(this);
		solverA.setRubikCube(cube);
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.drawString("KeyPressed: " + keyPressed, 50, 50);
		g.setColor(Color.red);
		g.drawString("Action: " + action, 50, 80);
		g.drawString("Moves: "+moves,getWidth()/2 + 250,80);
		g.setColor(Color.white);
		g.drawString("Complete: " + isComplete, 50, 110);
		if(showKeyNotation){
			g.drawString("Key notation", 50, 140);
			g.drawImage(icon.getImage(),50,150,icon.getIconWidth(),icon.getIconHeight(),this);
			g.setFont(new Font("Comic Sans MS", Font.BOLD,20));
		}
		
		g.setColor(Color.yellow);
		g.drawString("Scramble: " + scramble, 50, getHeight() - 10);

		drawFrontFace(g);
		drawLeftFace(g);
		drawRightFace(g);
		drawUpFace(g);
		drawBottomFace(g);
		drawBackFace(g);
		
		drawArrow(g);
		repaint();
	}

	public void update(Graphics g) {
		paint(g);
	}
	
	private void drawArrow(Graphics g){
		if(isMoveRight)
			g.drawImage(right.getImage(), (getWidth() - right.getIconWidth())/2 + TILE_SIZE + GAP,(getHeight() - right.getIconHeight())/2,right.getIconWidth(),right.getIconHeight(),null);
		if(isMoveLeft)
			g.drawImage(rightStar.getImage(), (getWidth() - rightStar.getIconWidth())/2 - TILE_SIZE - GAP,(getHeight() - rightStar.getIconHeight())/2,rightStar.getIconWidth(),rightStar.getIconHeight(),null);
		if(isMoveUp)
			g.drawImage(up.getImage(), (getWidth() - up.getIconWidth())/2,(getHeight() - up.getIconHeight())/2 - TILE_SIZE - GAP,up.getIconWidth(),up.getIconHeight(),null);
		if(isMoveDown)
			g.drawImage(upStar.getImage(), (getWidth() - upStar.getIconWidth())/2, (getHeight() - upStar.getIconHeight())/2 + TILE_SIZE + GAP,upStar.getIconWidth(),upStar.getIconHeight(),null);
		if(isMoveFront)
			g.drawImage(front.getImage(), (getWidth() - front.getIconWidth())/2 + TILE_SIZE/2,(getHeight() - front.getIconHeight())/2 - TILE_SIZE * 2,front.getIconWidth(),front.getIconHeight(),null);
		if(isMoveBack)
			g.drawImage(frontStar.getImage(), (getWidth() - frontStar.getIconWidth())/2 + TILE_SIZE+TILE_SIZE/2,(getHeight() - frontStar.getIconHeight())/2 - TILE_SIZE * 3 - GAP/2,frontStar.getIconWidth(),frontStar.getIconHeight(),null);
		
		if(isMoveRightStar)
			g.drawImage(rightStar.getImage(), (getWidth() - rightStar.getIconWidth())/2 + TILE_SIZE + GAP,(getHeight() - rightStar.getIconHeight())/2,rightStar.getIconWidth(),rightStar.getIconHeight(),null);
		if(isMoveLeftStar)
			g.drawImage(right.getImage(), (getWidth() - right.getIconWidth())/2 - TILE_SIZE - GAP,(getHeight() - right.getIconHeight())/2,right.getIconWidth(),right.getIconHeight(),null);
		if(isMoveUpStar)
			g.drawImage(upStar.getImage(), (getWidth() - upStar.getIconWidth())/2,(getHeight() - upStar.getIconHeight())/2 - TILE_SIZE - GAP,upStar.getIconWidth(),upStar.getIconHeight(),null);
		if(isMoveDownStar)
			g.drawImage(up.getImage(), (getWidth() - up.getIconWidth())/2, (getHeight() - up.getIconHeight())/2 + TILE_SIZE + GAP,up.getIconWidth(),up.getIconHeight(),null);
		if(isMoveFrontStar)
			g.drawImage(frontStar.getImage(), (getWidth() - frontStar.getIconWidth())/2 + TILE_SIZE/2,(getHeight() - frontStar.getIconHeight())/2 - TILE_SIZE * 2,frontStar.getIconWidth(),frontStar.getIconHeight(),null);
		if(isMoveBackStar)
			g.drawImage(front.getImage(), (getWidth() - front.getIconWidth())/2 + TILE_SIZE+TILE_SIZE/2,(getHeight() - front.getIconHeight())/2 - TILE_SIZE * 3 - GAP/2,front.getIconWidth(),front.getIconHeight(),null);
	}

	private void drawFrontFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 - (GAP + TILE_SIZE);
		int topLeftY = (getHeight() - TILE_SIZE) / 2 - (GAP + TILE_SIZE);

		for (int i = 0; i < cubeSize; i++) {
			int temp = topLeftX;
			for (int j = 0; j < cubeSize; j++) {
				g.setColor(getColor(cube.getFaceFront()[i][j]));
				g.fillRect(temp, topLeftY, TILE_SIZE, TILE_SIZE);
				temp += TILE_SIZE + GAP;
			}
			topLeftY += GAP + TILE_SIZE;
		}
	}

	private void drawBackFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 + (GAP / 2 + TILE_SIZE) * (cubeSize + 1);
		int topLeftY = (getHeight() - TILE_SIZE) / 2 - (GAP * GAP + (GAP + TILE_SIZE) * (cubeSize % 2 == 0 ? cubeSize : cubeSize + 1) / 2);

		for (int i = 0; i < cubeSize; i++) {
			int temp = topLeftX;
			for (int j = 0; j < cubeSize; j++) {
				g.setColor(getColor(cube.getFaceBack()[i][j]));
				g.fillRect(temp, topLeftY, TILE_SIZE, TILE_SIZE);
				temp += TILE_SIZE + GAP;
			}
			topLeftY += GAP + TILE_SIZE;
		}
	}

	private void drawLeftFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 - (GAP + (GAP + TILE_SIZE) * (cubeSize + 1));
		int topLeftY = (getHeight() - TILE_SIZE) / 2 - (GAP + TILE_SIZE);

		for (int i = 0; i < cubeSize; i++) {
			int temp = topLeftX;
			for (int j = 0; j < cubeSize; j++) {
				g.setColor(getColor(cube.getFaceLeft()[i][j]));
				g.fillRect(temp, topLeftY, TILE_SIZE, TILE_SIZE);
				temp += TILE_SIZE + GAP;
			}
			topLeftY += GAP + TILE_SIZE;
		}
	}

	private void drawRightFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 + GAP * 2 + (GAP + TILE_SIZE) * (cubeSize);
		int topLeftY = (getHeight() - TILE_SIZE) / 2 - (GAP + TILE_SIZE) * (cubeSize % 2 == 0 ? cubeSize : cubeSize + 1) / 2;
		for (int i = 0; i < cubeSize; i++) {
			int tempX = topLeftX - GAP / 2;
			int tempY = topLeftY + GAP / 2;
			for (int j = cubeSize - 1; j >= 0; j--) {
				int[] pointX = new int[] { tempX, tempX, tempX + TILE_SIZE / 2 + GAP - j - 1, tempX + TILE_SIZE / 2 + GAP - j - 1, tempX };
				int[] pointY = new int[] { tempY, tempY + TILE_SIZE, tempY + TILE_SIZE / 2, tempY - TILE_SIZE / 2, tempY };
				g.setColor(getColor(cube.getFaceRight()[i][j]));
				g.fillPolygon(pointX, pointY, pointX.length);

				tempX -= TILE_SIZE / 2 + GAP;
				tempY += angle - 4;
			}
			topLeftY += GAP + TILE_SIZE;
		}
	}

	private void drawUpFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 + GAP * 2;
		int topLeftY = (getHeight() - TILE_SIZE) / 2 - (GAP + (GAP + TILE_SIZE) * (cubeSize % 2 == 0 ? cubeSize : cubeSize + 1) / 2);

		for (int i = 0; i < cubeSize; i++) {
			int temp = topLeftX;
			for (int j = 0; j < cubeSize; j++) {
				int[] pointX = new int[] { temp, temp + angle, temp + TILE_SIZE + angle, temp + TILE_SIZE, temp };
				int[] pointY = new int[] { topLeftY, topLeftY - TILE_SIZE / 2, topLeftY - TILE_SIZE / 2, topLeftY, topLeftY };
				g.setColor(getColor(cube.getFaceUp()[i][j]));
				g.fillPolygon(pointX, pointY, pointX.length);
				temp += TILE_SIZE + GAP;
			}
			topLeftX -= angle + 2;
			topLeftY += GAP / 2 + TILE_SIZE / 2;
		}
	}

	private void drawBottomFace(Graphics g) {
		int topLeftX = (getWidth() - TILE_SIZE) / 2 - (GAP + TILE_SIZE);
		int topLeftY = (getHeight() - TILE_SIZE) / 2 + (GAP + (GAP + TILE_SIZE) * (cubeSize % 2 == 0 ? cubeSize : cubeSize + 1) / 2);

		for (int i = 0; i < cubeSize; i++) {
			int temp = topLeftX;
			for (int j = 0; j < cubeSize; j++) {
				g.setColor(getColor(cube.getFaceBottom()[i][j]));
				g.fillRect(temp, topLeftY, TILE_SIZE, TILE_SIZE);
				temp += TILE_SIZE + GAP;
			}
			topLeftY += GAP + TILE_SIZE;
		}
	}

	private Color getColor(int face) {
		switch (face) {
		case FRONT:
			return Color.BLUE;
		case BACK:
			return Color.GREEN;
		case LEFT:
			return Color.MAGENTA;// new Color(0xFF6A00);// Orange
		case RIGHT:
			return Color.RED;
		case UP:
			return Color.YELLOW;
		case BOTTOM:
			return Color.WHITE;
		}
		return Color.GRAY;
	}
	
	public void resetIsMove(){
		isMoveRight = false;
		isMoveLeft = false;
		isMoveUp = false;
		isMoveDown = false;
		isMoveFront = false;
		isMoveBack = false;
		
		isMoveRightStar = false;
		isMoveLeftStar = false;
		isMoveUpStar = false;
		isMoveDownStar = false;
		isMoveFrontStar = false;
		isMoveBackStar = false;
	}
	
	public boolean isMoveRight() {
		return isMoveRight;
	}

	public void setMoveRight(boolean isMoveRight) {
		resetIsMove();
		this.isMoveRight = isMoveRight;
	}

	public boolean isMoveLeft() {
		return isMoveLeft;
	}

	public void setMoveLeft(boolean isMoveLeft) {
		resetIsMove();
		this.isMoveLeft = isMoveLeft;
	}

	public boolean isMoveUp() {
		return isMoveUp;
	}

	public void setMoveUp(boolean isMoveUp) {
		resetIsMove();
		this.isMoveUp = isMoveUp;
	}

	public boolean isMoveDown() {
		return isMoveDown;
	}

	public void setMoveDown(boolean isMoveDown) {
		resetIsMove();
		this.isMoveDown = isMoveDown;
	}

	public boolean isMoveFront() {
		return isMoveFront;
	}

	public void setMoveFront(boolean isMoveFront) {
		resetIsMove();
		this.isMoveFront = isMoveFront;
	}

	public boolean isMoveBack() {
		return isMoveBack;
	}

	public void setMoveBack(boolean isMoveBack) {
		resetIsMove();
		this.isMoveBack = isMoveBack;
	}

	public boolean isMoveRightStar() {
		return isMoveRightStar;
	}

	public void setMoveRightStar(boolean isMoveRightStar) {
		resetIsMove();
		this.isMoveRightStar = isMoveRightStar;
	}

	public boolean isMoveLeftStar() {
		return isMoveLeftStar;
	}

	public void setMoveLeftStar(boolean isMoveLeftStar) {
		resetIsMove();
		this.isMoveLeftStar = isMoveLeftStar;
	}

	public boolean isMoveUpStar() {
		return isMoveUpStar;
	}

	public void setMoveUpStar(boolean isMoveUpStar) {
		resetIsMove();
		this.isMoveUpStar = isMoveUpStar;
	}

	public boolean isMoveDownStar() {
		return isMoveDownStar;
	}

	public void setMoveDownStar(boolean isMoveDownStar) {
		resetIsMove();
		this.isMoveDownStar = isMoveDownStar;
	}

	public boolean isMoveFrontStar() {
		return isMoveFrontStar;
	}

	public void setMoveFrontStar(boolean isMoveFrontStar) {
		resetIsMove();
		this.isMoveFrontStar = isMoveFrontStar;
	}

	public boolean isMoveBackStar() {
		return isMoveBackStar;
	}

	public void setMoveBackStar(boolean isMoveBackStar) {
		resetIsMove();
		this.isMoveBackStar = isMoveBackStar;
	}

	public void setIsComplete(boolean value){
		this.isComplete = value;
	}
	
	public boolean getIsComplete(){
		return this.isComplete;
	}
	
	public void setAction(String action){
		moves++;
		this.action = action;
	}
	
	public String getAction(){
		return this.action;
	}
	
	public void setMoves(int moves){
		this.moves = moves;
	}
	
	public int getMoves(){
		return this.moves;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		handleKey(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		resetIsMove();
	}

	private void handleKey(KeyEvent e) {
		
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			new Thread(){
				public void run() {
					solver.testMove();
//					solverA.solveTheCube(cube);
				};
			}.start();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			keyPressed = "Backspace";
			action = "SHOW/HIDE KEY NOTATION";
			if(showKeyNotation)
				showKeyNotation = false;
			else if(!showKeyNotation)
				showKeyNotation = true;
		}
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			keyPressed = "Escape";
			setAction("Reset Cube");
			scramble = "";
			cube.resetCube(cubeSize);
			moves = 0;
			
			isComplete = true;
			canScramble = true;
			canRotateCube = false;
			
			setChanged();
			notifyObservers(Message.RESET_TIMER);
			break;
		case KeyEvent.VK_SPACE:
			if (canScramble) {
				setChanged();
				notifyObservers(Message.RESET_TIMER);
				keyPressed = "Space";
				setAction("Scramble Cube");
				moves=0;
				canScramble = false;
				new Thread() {
					public void run() {
						scramble = scramble(MOVES);
						setChanged();
						notifyObservers(Message.START_TIMER);
						canRotateCube = true;
					};
				}.start();
			}
			break;
		}
		
		if (canRotateCube && !isSolving) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_SEMICOLON:// Uc
				action ="ROTATE CUBE UP CLOCKWISE";
				main.setText("Uc ");
				cube.rotateUp();
				break;
			case KeyEvent.VK_A:// Uc'
				action ="ROTATE CUBE UP COUNTER-CLOCKWISE";
				main.setText("Uc' ");
				cube.rotateUpStar();
				break;
			case KeyEvent.VK_P:// Fc
				action ="ROTATE CUBE FRONT CLOCKWISE";
				main.setText("Fc ");
				cube.rotateFront();
				break;
			case KeyEvent.VK_Q:// Fc'
				action ="ROTATE CUBE FRONT COUNTER-CLOCKWISE";
				main.setText("Fc' ");
				cube.rotateFrontStar();
				break;
			case KeyEvent.VK_B:// Lc
				action ="ROTATE CUBE LEFT CLOCKWISE";
				main.setText("Rc' ");
				cube.rotateRightStar();
				break;
			case KeyEvent.VK_T:// Lc'
				action ="ROTATE CUBE LEFT COUNTER-CLOCKWISE";
				main.setText("Rc ");
				cube.rotateRight();
				break;
			case KeyEvent.VK_Y:// Rc
				action ="ROTATE CUBE RIGHT CLOCKWISE";
				main.setText("Rc ");
				cube.rotateRight();
				break;
			case KeyEvent.VK_N:// Rc'
				action ="ROTATE CUBE RIGHT COUNTER-CLOCKWISE";
				main.setText("Rc' ");
				cube.rotateRightStar();
				break;
			}
		}
		if (!isComplete) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				if(!isSolving){
					isSolving = true;
					new Thread(){
						public void run() {
							solver.solveTheCube(cube);
							isSolving = false;
							try {
								this.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
					}.start();
				}
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(!isSolving){
					isSolving = true;
					new Thread(){
						public void run() {
							solverA.solveTheCube(cube);
							isSolving = false;
							try {
								this.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
					}.start();
				}
			}
			if(!isSolving){
				switch (e.getKeyCode()) {
				case KeyEvent.VK_J:// U
					setAction("UP");
					isMoveUp = true;
					main.setText("U ");
					cube.up();
					break;
				case KeyEvent.VK_F:// U'
					setAction("UP'");
					isMoveUpStar = true;
					main.setText("U' ");
					cube.upStar();
					break;
				case KeyEvent.VK_S:// D
					setAction("DOWN");
					isMoveDown = true;
					main.setText("D ");
					cube.down();
					break;
				case KeyEvent.VK_L:// D'
					setAction("DOWN'");
					isMoveDownStar = true;
					main.setText("D' ");
					cube.downStar();
					break;
				case KeyEvent.VK_D:// L
					setAction("LEFT");
					isMoveLeft = true;
					main.setText("L ");
					cube.left();
					break;
				case KeyEvent.VK_E:// L'
					setAction("LEFT'");
					isMoveLeftStar = true;
					main.setText("L' ");
					cube.leftStar();
					break;
				case KeyEvent.VK_I:// R
					setAction("RIGHT");
					isMoveRight = true;
					main.setText("R ");
					cube.right();
					break;
				case KeyEvent.VK_K:// R'
					setAction("RIGHT'");
					isMoveRightStar = true;
					main.setText("R' ");
					cube.rightStar();
					break;
				case KeyEvent.VK_H:// F
					setAction("FRONT");
					isMoveFront = true;
					main.setText("F ");
					cube.front();
					break;
				case KeyEvent.VK_G:// F'
					setAction("FRONT'");
					isMoveFrontStar = true;
					main.setText("F' ");
					cube.frontStar();
					break;
				case KeyEvent.VK_W:// B
					setAction("BACK");
					isMoveBack = true;
					main.setText("B ");
					cube.back();
					break;
				case KeyEvent.VK_O:// B'
					setAction("BACK'");
					isMoveBackStar = true;
					main.setText("B' ");
					cube.backStar();
					break;
	
				case KeyEvent.VK_V:// l
					setAction("two line left");
					main.setText("l ");
					cube.doubleLeft();
					break;
				case KeyEvent.VK_R:// l'
					setAction("two line left'");
					main.setText("l' ");
					cube.doubleLeftStar();
					break;
				case KeyEvent.VK_U:// r
					setAction("two line right");
					main.setText("r ");
					cube.doubleRight();
					break;
				case KeyEvent.VK_M:// r'
					setAction("two line right'");
					main.setText("r' ");
					cube.doubleRightStar();
					break;
				}
				isComplete = cube.isComplete();
				if(isComplete){
					cubeIsSolvedAction();
				}
			}
		}
	}
	
	public void cubeIsSolvedAction(){
		canScramble = true;
		canRotateCube = false;
		setChanged();
		notifyObservers(Message.STOP_TIMER);
	}
	
	public RubikCubeSolverBeginner getSolver(){
		return this.solver;
	}
	
	public RubikCubeSolverAdvance getSolverA(){
		return this.solverA;
	}

	public String scramble(int move) {
		scramble = "";
		for (int i = 0; i < move; i++) {

			switch (rd.nextInt(22)) {
			case 0:
				scramble += "U ";
				cube.up();
				break;
			case 1:
				scramble += "U' ";
				cube.upStar();
				break;
			case 2:
				scramble += "L ";
				cube.left();
				break;
			case 3:
				scramble += "L' ";
				cube.leftStar();
				break;
			case 4:
				scramble += "R ";
				cube.right();
				break;
			case 5:
				scramble += "R' ";
				cube.rightStar();
				break;
			case 6:
				scramble += "B ";
				cube.back();
				break;
			case 7:
				scramble += "B' ";
				cube.backStar();
				break;
			case 8:
				scramble += "F ";
				cube.front();
				break;
			case 9:
				scramble += "F' ";
				cube.frontStar();
				break;
			case 10:
				scramble += "D ";
				cube.down();
				break;
			case 11:
				scramble += "D' ";
				cube.downStar();
				break;
			case 12:
				scramble += "U2 ";
				cube.up();
				cube.up();
				break;
			case 13:
				scramble += "B2 ";
				cube.back();
				cube.back();
				break;
			case 14:
				scramble += "D2 ";
				cube.down();
				cube.down();
				break;
			case 15:
				scramble += "R2 ";
				cube.right();
				cube.right();
				break;
			case 16:
				scramble += "L2 ";
				cube.left();
				cube.left();
				break;
			case 17:
				scramble += "F2 ";
				cube.front();
				cube.front();
				break;
			case 18:
				scramble += "Uc ";
				cube.rotateUp();
				break;
			case 19:
				scramble += "Uc' ";
				cube.rotateUpStar();
				break;
			case 20:
				scramble += "Fc ";
				cube.rotateFront();
				break;
			case 21:
				scramble += "Fc' ";
				cube.rotateFrontStar();
				break;
			}
			main.getTa().setText("Scramble: " + scramble);
			delay(100);
		}
		main.getTa().setText("Scramble: " + scramble+"\n\n");
		return scramble;
	}

	public void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =================Observable=================
	private boolean change = false;
	private List<Observer> listObs;

	@Override
	public void addObserver(Observer observer) {
		listObs.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		listObs.remove(observer);
	}

	@Override
	public void notifyObservers(Object obj) {
		if (change) {
			for (Observer obs : listObs) {
				obs.update(this, obj);
			}
		}
		change = false;
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}

	@Override
	public void setChanged() {
		change = true;
	}
	
	
}
