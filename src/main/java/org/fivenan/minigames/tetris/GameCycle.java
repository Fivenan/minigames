package org.fivenan.minigames.tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCycle implements ActionListener {

	private Board board;

	public GameCycle(Board board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		board.repaint();
	}

	private void update() {
		if (board.isPaused()) {
			return;
		}

		if (board.isFallingFinished()) {
			board.setFallingFinished(false);
			board.newPiece();
		} else {
			board.oneLineDown();
		}
	}
}
