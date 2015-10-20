package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Thái
 * Feb 8, 2015
 */
public class JSpeedPanel extends JPanel{

	private static final long serialVersionUID = 0xA1B2L;
	
	private JSlider sl;
	
	public JSpeedPanel(){
		init();
	}
	
	private void init(){
		newComps();
		setMainLayout();
		event();
	}
	
	private void newComps(){
		sl = new JSlider(0, 1000, 150);
		sl.setBackground(Color.black);
		sl.setFocusable(false);
		sl.setPaintTicks(true);
	}
	
	private void setMainLayout(){
		setForeground(Color.white);
		setLayout(null);
		setPreferredSize(new Dimension(280,30));
		setBorder(BorderFactory.createTitledBorder(null, "SPEED", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.GREEN));
		setOpaque(false);
		
		this.add(sl);
		sl.setBounds(3,15,274,30);
		sl.setValue(sl.getMaximum() - JRubikPanel.getInstance().getSolver().getDelay());
	}
	
	private void event(){
		sl.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JRubikPanel.getInstance().getSolver().setDelay(sl.getMaximum() - sl.getValue());
				JRubikPanel.getInstance().getSolverA().setDelay(sl.getMaximum() - sl.getValue());
			}
		});
	}

}
