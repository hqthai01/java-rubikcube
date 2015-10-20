package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * @author Thái
 * Feb 8, 2015
 */
public class JMovePanel extends JPanel{

	private static final long serialVersionUID = 0xA1B2L;
	private JTextArea ta;
	private JScrollPane sc;
	private JButton btnExport;
	private JCheckBox ckWrap;
	private JFileChooser chooser;
	
	public void setText(String value) {
		this.ta.setText(this.ta.getText() + value);
	}
	
	public JTextArea getTa() {
		return this.ta;
	}
	
	public JMovePanel (){
		init();
	}
	
	private void init(){
		newComps();
		setMainLayout();
		
		event();
	}
	
	private void newComps(){
		ta = new JTextArea();
		// ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setFocusable(false);
		ta.setEditable(false);
		ta.setFont(new Font("Courier New", Font.BOLD, 20));
		ta.setBackground(Color.black);
		ta.setForeground(Color.GREEN);
		
		sc = new JScrollPane();
		sc.setViewportView(ta);
		
		btnExport = new JButton("Export");
		btnExport.setOpaque(false);
		btnExport.setFont(ta.getFont());
		btnExport.setFocusable(false);
		
		ckWrap = new JCheckBox("Wrap text");
		ckWrap.setOpaque(false);
		ckWrap.setForeground(Color.white);
		ckWrap.setFont(ta.getFont());
		ckWrap.setFocusable(false);
		
		chooser = new JFileChooser();
	}
	
	private void setMainLayout(){
		
		setPreferredSize(new Dimension(280,340+285));
		setSize(getPreferredSize());
		setBorder(BorderFactory.createTitledBorder(null, "ACTION LOG", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.GREEN));
		setOpaque(false);
		setLayout(null);
		
		this.add(sc);
		sc.setBounds(2, 20, 276, 170+285);
		this.add(ckWrap);
		ckWrap.setBounds(sc.getX() + 2,sc.getY() + sc.getHeight() + 10, 140, 30);
		this.add(btnExport);
		btnExport.setBounds(ckWrap.getX() + ckWrap.getWidth()+5,ckWrap.getY(), 120, 30);
	}
	
	private void event(){
		ckWrap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ckWrap.isSelected()){
					ta.setLineWrap(true);
				}else{
					ta.setLineWrap(false);
				}
			}
		});
		
		btnExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
	}
	
	private void export(){
		int choice = chooser.showSaveDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION){
			if(!ta.getText().equals("")){
				File file = chooser.getSelectedFile();
				try {
					PrintWriter writer = new PrintWriter(file);
					writer.write(ta.getText());
					writer.flush();
					writer.close();
					JOptionPane.showMessageDialog(null, "The solution is exported!!!", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
