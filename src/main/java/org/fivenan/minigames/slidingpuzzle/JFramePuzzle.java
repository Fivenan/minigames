package org.fivenan.minigames.slidingpuzzle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JFramePuzzle extends JFrame implements ActionListener {

	JButton[] button;
	JButton next;

	public JFramePuzzle() {
		super("JFrame Puzzle");
		button = new JButton[9];
		for (int i = 0; i < 9; i++) {
			button[i] = new JButton("" + (i + 1));
		}
		button[7] = new JButton("");
		button[8] = new JButton("8");
		next = new JButton("next");

		button[0].setBounds(10, 30, 50, 40);
		button[1].setBounds(70, 30, 50, 40);
		button[2].setBounds(130, 30, 50, 40);
		button[3].setBounds(10, 80, 50, 40);
		button[4].setBounds(70, 80, 50, 40);
		button[5].setBounds(130, 80, 50, 40);
		button[6].setBounds(10, 130, 50, 40);
		button[7].setBounds(70, 130, 50, 40);
		button[8].setBounds(130, 130, 50, 40);
		next.setBounds(70, 200, 50, 40);

		for (int i = 0; i < 9; i++) {
			button[i].addActionListener(this);
			add(button[i]);
		}
		next.addActionListener(this);
		add(next);

		next.setBackground(Color.black);
		next.setForeground(Color.green);
		setSize(250, 300);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			swap(3, 8);
			swap(0, 4);
			swap(1, 6);
		}
		
		if (e.getSource() == button[0]) {
			swapToEmpty(0, 1);
			swapToEmpty(0, 3);
		}
		if (e.getSource() == button[1]) {
			swapToEmpty(1, 0);
			swapToEmpty(1, 2);
			swapToEmpty(1, 4);
		}
		if (e.getSource() == button[2]) {
			swapToEmpty(2, 1);
			swapToEmpty(2, 5);
		}
		if (e.getSource() == button[3]) {
			swapToEmpty(3, 0);
			swapToEmpty(3, 4);
			swapToEmpty(3, 6);
		}
		if (e.getSource() == button[4]) {
			swapToEmpty(4, 1);
			swapToEmpty(4, 3);
			swapToEmpty(4, 5);
			swapToEmpty(4, 7);
		}
		if (e.getSource() == button[5]) {
			swapToEmpty(5, 2);
			swapToEmpty(5, 4);
			swapToEmpty(5, 8);
		}
		if (e.getSource() == button[6]) {
			swapToEmpty(6, 3);
			swapToEmpty(6, 7);
		}
		if (e.getSource() == button[7]) {
			swapToEmpty(7, 4);
			swapToEmpty(7, 6);
			swapToEmpty(7, 8);
		}
		if (e.getSource() == button[8]) {
			swapToEmpty(8, 5);
			swapToEmpty(8, 7);
		}

		if (isInTheRightPosition()) {
			JOptionPane.showMessageDialog(JFramePuzzle.this, "You won!");
		}
	}

	private void swap(int startIndex, int targetIndex) {
		String s = button[startIndex].getText();
		button[startIndex].setText(button[targetIndex].getText());
		button[targetIndex].setText(s);
	}

	private void swapToEmpty(int startIndex, int targetIndex) {
		if (button[targetIndex].getText().equals("")) {
			if (button[targetIndex].getText().equals("")) {
				button[targetIndex].setText(button[startIndex].getText());
				button[startIndex].setText("");
			}
		}
	}

	private boolean isInTheRightPosition() {
		boolean result = true;
		for (int i = 0; i < 8; i++) {
			result &= button[i].getText().equals("" + (i + 1));
		}
		return result;
	}

	public static void main(String[] args) {
		new JFramePuzzle();
	}
}
