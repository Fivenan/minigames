package org.fivenan.minigames.slidingpuzzle;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class FramePuzzle extends Frame implements ActionListener {

	Button[] button;

	public FramePuzzle() {
		super("Puzzle Frame");
		button = new Button[9];
		for (int i = 0; i < 9; i++) {
			button[i] = new Button("" + (i + 1));
		}
		button[7] = new Button("");
		button[8] = new Button("8");

		button[0].setBounds(50, 100, 40, 40);
		button[1].setBounds(100, 100, 40, 40);
		button[2].setBounds(150, 100, 40, 40);
		button[3].setBounds(50, 150, 40, 40);
		button[4].setBounds(100, 150, 40, 40);
		button[5].setBounds(150, 150, 40, 40);
		button[6].setBounds(50, 200, 40, 40);
		button[7].setBounds(100, 200, 40, 40);
		button[8].setBounds(150, 200, 40, 40);

		for (int i = 0; i < 9; i++) {
			button[i].addActionListener(this);
			add(button[i]);
		}

		setSize(400, 400);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FramePuzzle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
			JOptionPane.showMessageDialog(this, "You won!");
		}
	}

	private void swapToEmpty(int startIndex, int targetIndex) {
		if (button[targetIndex].getLabel().equals("")) {
			button[targetIndex].setLabel(button[startIndex].getLabel());
			button[startIndex].setLabel("");
		}
	}

	private boolean isInTheRightPosition() {
		boolean result = true;
		for (int i = 0; i < 8; i++) {
			result &= button[i].getLabel().equals("" + (i + 1));
		}
		return result;
	}
}
