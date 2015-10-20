package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.hqthai01.rubiksolver.ai.RubikCubeSolverBeginner;

/**
 * @author Thái
 * Feb 10, 2015
 */
public class JImportMovePanel extends JPanel{

	private static final long serialVersionUID = 0xA1B3L;
	private JTextArea ta;
	private JScrollPane sc;
	private JButton btnImport;
	private JButton btnAction;
	
	private RubikCubeSolverBeginner solverBeginner;
	
	private String moves = "";
	
	public JImportMovePanel(){
		init();
	}
	
	private void init(){
		newComps();
		setMainLayout();
		event();
	}
	
	private void newComps(){
		ta = new JTextArea();
		ta.setEditable(false);
		ta.setFocusable(false);
		ta.setWrapStyleWord(true);
		ta.setBackground(Color.black);
		ta.setForeground(Color.green);
		ta.setFont(new Font("Courier New",Font.BOLD, 20));
		
		sc = new JScrollPane();
		sc.setViewportView(ta);
		
		btnImport = new JButton("Import Moves");
		btnImport.setFocusable(false);
		btnAction = new JButton("Action");
		btnAction.setFocusable(false);
	}
	
	private void setMainLayout(){
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder(null, "IMPORT MOVES", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.GREEN));
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(280,100));
		
		this.add(sc);
		sc.setBounds(5,20,270,50);
		
		this.add(btnImport);
		btnImport.setBounds(sc.getX(),sc.getY() + 8 + sc.getHeight(),135,30);
		
		this.add(btnAction);
		btnAction.setBounds(btnImport.getX() + btnImport.getWidth() + 6, btnImport.getY(), 128,30);
		
	}
	
	private void event(){
		btnImport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moves = JOptionPane.showInputDialog("Enter your moves here!!");
				ta.setText(moves);
			}
		});
		
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						btnAction.setEnabled(false);
						btnImport.setEnabled(false);
						solverBeginner.move(moves);
						btnImport.setEnabled(true);
						btnAction.setEnabled(true);
					}
				}.start();
				
			}
		});
	}
	
	public void setSolver(RubikCubeSolverBeginner solver){
		this.solverBeginner = solver;
	}
}
