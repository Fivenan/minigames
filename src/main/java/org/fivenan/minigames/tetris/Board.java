package org.fivenan.minigames.tetris;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;
	private final int PERIOD_INTERVAL = 300;

	private Timer timer;
	private boolean isFallingFinished = false;
	private boolean isPaused = false;
	private int numLinesRemoved = 0;
	private int curX = 0;
	private int curY = 0;
	private JLabel statusbar;
	private Shape curPiece;
	private Tetrominoe[] board;

	public Board(Tetris parent) {
		initBoard(parent);
	}

	private void initBoard(Tetris parent) {
		setFocusable(true);
		statusbar = parent.getStatusBar();

		addKeyListener(new TAdapter(this));
	}

	private int squareWidth() {
		return (int) getSize().getWidth() / BOARD_WIDTH;
	}

	private int squareHeight() {
		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}

	private Tetrominoe shapeAt(int x, int y) {
		return board[(y * BOARD_WIDTH) + x];
	}

	void start() {

		curPiece = new Shape();
		board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];

		clearBoard();
		newPiece();

		timer = new Timer(PERIOD_INTERVAL, new GameCycle(this));
		timer.start();
	}

	void pause() {
		isPaused = !isPaused;
		if (isPaused) {
			statusbar.setText("Game Paused");
		} else {
			statusbar.setText(String.valueOf(numLinesRemoved));
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		var size = getSize();
		int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

		drawBoard(g, boardTop);
		drawCurrentPiece(g, boardTop);
	}

	private void drawBoard(Graphics g, int boardTop) {
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);
				if (shape != Tetrominoe.NO_SHAPE) {
					drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
				}
			}
		}
	}

	private void drawCurrentPiece(Graphics g, int boardTop) {
		if (curPiece.getShape() != Tetrominoe.NO_SHAPE) {
			for (int i = 0; i < 4; i++) {
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);

				drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}
	}

	void dropDown() {
		int newY = curY;
		while (newY > 0) {
			if (!tryMove(curPiece, curX, newY - 1)) {
				break;
			}
			newY--;
		}
		pieceDropped();
	}

	void oneLineDown() {
		if (!tryMove(curPiece, curX, curY - 1)) {
			pieceDropped();
		}
	}

	private void clearBoard() {
		for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
			board[i] = Tetrominoe.NO_SHAPE;
		}
	}

	private void pieceDropped() {
		for (int i = 0; i < 4; i++) {
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
		}
		removeFullLines();
		if (!isFallingFinished) {
			newPiece();
		}
	}

	void newPiece() {

		curPiece.setRandomShape();
		curX = BOARD_WIDTH / 2 + 1;
		curY = BOARD_HEIGHT - 1 + curPiece.minY();

		if (!tryMove(curPiece, curX, curY)) {
			curPiece.setShape(Tetrominoe.NO_SHAPE);
			timer.stop();

			var msg = String.format("Game over. Score: %d", numLinesRemoved);
			statusbar.setText(msg);
		}
	}

	boolean tryMove(Shape newPiece, int newX, int newY) {

		for (int i = 0; i < 4; i++) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT || (shapeAt(x, y) != Tetrominoe.NO_SHAPE)) {
				return false;
			}
		}
		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();

		return true;
	}

	private void removeFullLines() {
		int numFullLines = 0;
		for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
			boolean lineIsFull = true;
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (shapeAt(j, i) == Tetrominoe.NO_SHAPE) {
					lineIsFull = false;
					break;
				}
			}
			if (lineIsFull) {
				numFullLines++;
				for (int k = i; k < BOARD_HEIGHT - 1; k++) {
					for (int j = 0; j < BOARD_WIDTH; j++) {
						board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
					}
				}
			}
		}
		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			statusbar.setText(String.valueOf(numLinesRemoved));
			isFallingFinished = true;
			curPiece.setShape(Tetrominoe.NO_SHAPE);
		}
	}

	private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {

		Color color = shape.getColor();

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isFallingFinished() {
		return isFallingFinished;
	}

	public void setFallingFinished(boolean isFallingFinished) {
		this.isFallingFinished = isFallingFinished;
	}

	public int getCurX() {
		return curX;
	}

	public void setCurX(int curX) {
		this.curX = curX;
	}

	public int getCurY() {
		return curY;
	}

	public void setCurY(int curY) {
		this.curY = curY;
	}

	public Shape getCurPiece() {
		return curPiece;
	}

	public void setCurPiece(Shape curPiece) {
		this.curPiece = curPiece;
	}

}
