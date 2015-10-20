package com.hqthai01.rubiksolver.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * @author Thái Feb 1, 2015
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 0xAEF1L;

	private static Main instance;
	private JMovePanel mp;
	private JSpeedPanel sp;
	private JImportMovePanel ip;

	public static Main getInstance() {
		synchronized (Main.class) {
			if (instance == null)
				return instance = new Main();
		}
		return instance;
	}

	private Main() {
		init();
	}

	private void init() {
		newComps();
		setMainLayout();

		event();
	}

	public void setText(String value) {
		this.mp.setText(value);
	}

	public JTextArea getTa() {
		return this.mp.getTa();
	}

	private void newComps() {
		mp = new JMovePanel();
		sp = new JSpeedPanel();
		ip = new JImportMovePanel();
	}

	private void event() {
		this.addKeyListener(JRubikPanel.getInstance());
	}

	private void setMainLayout() {
		setPreferredSize(new Dimension(1366, 700));
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		this.setLayout(null);


		this.add(JRubikPanel.getInstance());
		JRubikPanel.getInstance().setBounds((getWidth() - JRubikPanel.getInstance().getWidth()) / 2, 0, 1360, JRubikPanel.getInstance().getHeight());
		JRubikPanel.getInstance().setMain(this);

		this.add(JTimerPanel.getInstance());
		JTimerPanel.getInstance().setBounds((getWidth() - JTimerPanel.getInstance().getWidth()) / 2, 600, 1360, 120);

		JRubikPanel.getInstance().addObserver(JTimerPanel.getInstance());
		JRubikPanel.getInstance().getSolver().setMain(this);
		JRubikPanel.getInstance().getSolverA().setMain(this);
		
		
		this.add(mp);
		mp.setBounds(0, 0, 280, 240+285);
		this.add(sp);
		sp.setBounds(mp.getX(),mp.getY() + mp.getHeight()+10,280,50);
		this.add(ip);
		ip.setBounds(sp.getX(), sp.getY()+10 + sp.getHeight(), 280,120);
		
		ip.setSolver(JRubikPanel.getInstance().getSolver());

		// this.add(JRubikPanel.getInstance());
		// JRubikPanel.getInstance().setBounds(0, 0, 1366, JRubikPanel.getInstance().getHeight());
		//
		// this.add(JTimerPanel.getInstance());
		// JTimerPanel.getInstance().setBounds(0, 600, 1366, 120);
		//
		// JRubikPanel.getInstance().addObserver(JTimerPanel.getInstance());

		setIconImage(new ImageIcon(Main.class.getResource("/com/hqthai01/rubiksolver/icons/soft_icon.png")).getImage());
		setTitle("Rubik's Cube Solver - HQTHAI01");
		setFocusable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Main.getInstance().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
