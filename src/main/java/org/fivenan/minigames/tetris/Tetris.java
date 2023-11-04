package org.fivenan.minigames.tetris;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame {

	private JLabel statusBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Tetris game = new Tetris();
			game.setVisible(true);
		});
	}

	public Tetris() {
		initUI();
	}

	private void initUI() {
		statusBar = new JLabel(" 0");
		add(statusBar, BorderLayout.SOUTH);
		
		Board board = new Board(this);
		add(board);
		board.start();
		
		setTitle("Tetris");
		setSize(200, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public JLabel getStatusBar() {
		return statusBar;
	}
}
