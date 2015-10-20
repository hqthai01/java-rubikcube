package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.hqthai01.rubiksolver.constants.Message;
import com.hqthai01.rubiksolver.pattern.observer.Observable;
import com.hqthai01.rubiksolver.pattern.observer.Observer;

/**
 * @author Thái Feb 3, 2015
 */
public class JTimerPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 0xAB1CL;
	private int counter = 50;
	private boolean isStart = false;
	private boolean isCountDown = false;
	private String time = "00.0s";

	private Timer timer;

	private ActionListener timerAction = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int div = 10;
			if(isStart){
				isCountDown = false;
				counter++;
			}else{
				isCountDown = true;
				counter--;
				if(counter == 0){
					isStart = true;
					JRubikPanel.getInstance().setIsComplete(false);
				}
				time = counter / div < 10 ? "0" + counter / div +".0s": counter / div + ".0s";
				return;
			}
			String ms = counter % div +"";

			String s = counter / div < 10 ? "0" + counter / div : counter / div + "";
			time = s + "." + ms +"s";
		}
	};

	private static JTimerPanel instance;

	public static JTimerPanel getInstance() {
		synchronized (JTimerPanel.class) {
			if(instance == null)
				return instance = new JTimerPanel();
		}
		return instance;
	}

	private JTimerPanel() {
		setPreferredSize(new Dimension(800,100));
		setSize(getPreferredSize());
		timer = new Timer(100, timerAction);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(new Font("Courier New", Font.BOLD, 40));
		g.setColor(Color.white);
		if(isCountDown ) g.setColor(Color.red);
		
		g.drawString(time, getWidth()/2-60, getHeight()/2-20);
		repaint();
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void update(Observable obs, Object... objects) {
		if (objects == null)
			return;
		int command = Integer.parseInt(objects[0].toString());
		switch(command){
		case Message.RESET_TIMER:
			resetTimer();
			break;
		case Message.START_TIMER:
			timer.start();
			break;
		case Message.STOP_TIMER:
			timer.stop();
			break;
		}

	}
	
	private void resetTimer(){
		timer.stop();
		counter = 50;
		isStart = false;
		isCountDown = true;
		time = "00.0s";
	}

}
