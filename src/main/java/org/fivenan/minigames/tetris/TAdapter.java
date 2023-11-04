package org.fivenan.minigames.tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {

	private Board board;

	public TAdapter(Board board) {
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Shape curPiece = board.getCurPiece();
		int curX = board.getCurX();
		int curY = board.getCurY();
		if (curPiece.getShape() == Tetrominoe.NO_SHAPE) {
			return;
		}
		int keycode = e.getKeyCode();

		switch (keycode) {
		case KeyEvent.VK_P -> board.pause();
		case KeyEvent.VK_LEFT -> board.tryMove(curPiece, curX - 1, curY);
		case KeyEvent.VK_RIGHT -> board.tryMove(curPiece, curX + 1, curY);
		case KeyEvent.VK_UP -> board.tryMove(curPiece.rotateLeft(), curX, curY);
		case KeyEvent.VK_DOWN -> board.tryMove(curPiece.rotateRight(), curX, curY);
		case KeyEvent.VK_SPACE -> board.dropDown();
		case KeyEvent.VK_D -> board.oneLineDown();
		}
	}
}
